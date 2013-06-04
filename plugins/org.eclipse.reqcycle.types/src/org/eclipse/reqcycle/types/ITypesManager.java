package org.eclipse.reqcycle.types;

import org.eclipse.reqcycle.types.IInjectedTypeChecker.IValueInjecter;
import org.eclipse.reqcycle.uri.model.Reachable;

public interface ITypesManager {
	Iterable<IType> getAllTypes();

	IType getType(String id);

	Iterable<IType> getAllApplicableTypes(Reachable reachable);

	IType newInjectedType(String id, IType parent, IValueInjecter injecter);

	void addTypeProvider(IInjectedTypeProvider provider);

	void removeTypeProvider(IInjectedTypeProvider provider);
}
