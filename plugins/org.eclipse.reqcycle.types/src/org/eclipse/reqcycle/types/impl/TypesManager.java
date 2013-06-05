package org.eclipse.reqcycle.types.impl;

import java.lang.reflect.Field;
import java.util.ArrayDeque;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.inject.Singleton;

import org.eclipse.reqcycle.types.IInjectedTypeChecker.IValueInjecter;
import org.eclipse.reqcycle.types.IInjectedTypeProvider;
import org.eclipse.reqcycle.types.IType;
import org.eclipse.reqcycle.types.ITypeChecker;
import org.eclipse.reqcycle.types.ITypesManager;
import org.eclipse.reqcycle.uri.model.Reachable;
import org.eclipse.ziggurat.inject.ZigguratInject;

import com.google.common.base.Function;
import com.google.common.base.Predicate;
import com.google.common.collect.Iterables;

@Singleton
public class TypesManager implements ITypesManager {

	static Map<String, IType> allTypes = new ExtensionPointReader().read();
	Set<IInjectedTypeProvider> providers = new HashSet<IInjectedTypeProvider>();

	@Override
	public Iterable<IType> getAllTypes() {
		Iterable<Iterable<IType>> transform = Iterables.transform(providers,
				new ProviderToITypes());
		Iterable<IType> result = Iterables.concat(transform);
		return Iterables.unmodifiableIterable(Iterables.concat(
				allTypes.values(), result));
	}

	@Override
	public IType getType(final String id) {
		IType result = allTypes.get(id);
		if (result == null) {
			result = Iterables.find(Iterables.concat(Iterables.transform(
					providers, new ProviderToITypes())),
					new Predicate<IType>() {
						public boolean apply(IType t) {
							return id.equals(t.getId());
						}
					}, null);
		}
		return result;
	}

	@Override
	public Iterable<IType> getAllApplicableTypes(Reachable reachable) {
		ArrayDeque<IType> result = new ArrayDeque<IType>();
		Iterable<IType> iterable = getAllTypes();
		for (IType t : iterable) {
			if (t.is(reachable)) {
				result.add(t);
			}
		}
		return result;
	}

	@Override
	public IType newInjectedType(String id, IType parent,
			final IValueInjecter injecter) {
		IType newType = new InjectedType(id, parent, injecter);
		return newType;
	}

	private class InjectedType extends Type {
		private IValueInjecter injecter;

		public InjectedType(String id, IType parent, IValueInjecter injecter) {
			super(id, parent);
			this.injecter = injecter;
		}

		@Override
		public String getLabel() {
			String superLabel = super.getLabel();
			return superLabel + " (" + getId() + ")";
		}

		@Override
		protected ITypeChecker getChecker() {
			if (instance == null) {
				if (getSuperType().isExtensible()) {
					try {
						ITypeChecker iTypeChecker = instance = getCheckerClass()
								.newInstance();
						ZigguratInject.inject(iTypeChecker);
						List<Field> fields = getFieldsToInject();
						for (Field f : fields) {
							boolean oldAccess = f.isAccessible();
							if (!oldAccess) {
								f.setAccessible(true);
							}
							f.set(instance,
									injecter.getValue(getId(), f.getName(),
											f.getType()));
							if (!oldAccess) {
								f.setAccessible(false);
							}
						}
					} catch (InstantiationException e) {
					} catch (IllegalAccessException e) {
					}

				} else {
					instance = super.getChecker();
				}
			}
			return instance;
		}

		@Override
		public boolean is(Reachable reachable) {
			return super.is(reachable);
		}

		@Override
		public boolean isExtensible() {
			return false;
		}
	}

	@Override
	public void addTypeProvider(IInjectedTypeProvider provider) {
		providers.add(provider);
	}

	@Override
	public void removeTypeProvider(IInjectedTypeProvider provider) {
		providers.remove(provider);
	}

	private class ProviderToITypes implements
			Function<IInjectedTypeProvider, Iterable<IType>> {
		public Iterable<IType> apply(IInjectedTypeProvider p) {
			return p.getTypes();
		}
	}

}
