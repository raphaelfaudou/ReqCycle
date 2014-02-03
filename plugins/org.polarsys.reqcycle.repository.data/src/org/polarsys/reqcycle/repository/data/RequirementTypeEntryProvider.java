package org.polarsys.reqcycle.repository.data;

import org.polarsys.reqcycle.repository.data.util.EntryUtil;
import org.polarsys.reqcycle.traceability.types.configuration.typeconfiguration.Entry;
import org.polarsys.reqcycle.traceability.types.ui.IEntryCompositeProvider;
import org.polarsys.reqcycle.types.IType.FieldDescriptor;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ziggurat.inject.ZigguratInject;


public class RequirementTypeEntryProvider implements IEntryCompositeProvider {

	IDataModelManager dataModelManager = ZigguratInject.make(IDataModelManager.class);

	/**
	 * @wbp.parser.entryPoint
	 */
	@Override
	public Entry createEntryComposite(Composite parent, int style, FieldDescriptor fd) {

		return EntryUtil.createComboViewer(parent, fd, dataModelManager.getAllRequirementTypes());
	}

}
