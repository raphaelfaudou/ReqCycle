package org.eclipse.reqcycle.emf.traceability.sysml.types.traceability;

import java.util.Arrays;
import java.util.Map;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.papyrus.sysml.allocations.AllocationsPackage;
import org.eclipse.papyrus.sysml.requirements.RequirementsPackage;
import org.eclipse.reqcycle.traceability.model.TType;
import org.eclipse.reqcycle.traceability.types.TTypeProvider;
import org.eclipse.uml2.uml.profile.l2.L2Package;

import com.google.common.base.Function;
import com.google.common.collect.Maps;

public class SysMLTTypeProvider implements TTypeProvider {

	private static TType[] TYPES = new SysMLType[] {
			new SysMLType(RequirementsPackage.Literals.COPY),
			new SysMLType(RequirementsPackage.Literals.DERIVE_REQT),
			new SysMLType(RequirementsPackage.Literals.SATISFY),
			new SysMLType(RequirementsPackage.Literals.VERIFY),
			new SysMLType(L2Package.Literals.TRACE),
			new SysMLType(AllocationsPackage.Literals.ALLOCATE) };

	private static Map<EClass, TType> MAP = Maps.uniqueIndex(
			Arrays.asList(TYPES), new Function<TType, EClass>() {
				@Override
				public EClass apply(TType arg0) {
					return (((SysMLType) arg0).getEClass());
				}
			});

	public static TType get(EClass eclass) {
		TType result = MAP.get(eclass);
		if (result == null) {
			result = MAP.get(L2Package.Literals.TRACE);
		}
		return result;
	}

	@Override
	public Iterable<TType> getTTypes() {
		return Arrays.asList(TYPES);
	}

}
