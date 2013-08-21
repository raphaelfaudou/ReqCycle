package org.eclipse.reqcycle.repository.data.impl.internal;

import org.eclipse.reqcycle.repository.data.IDataModelManager;
import org.eclipse.ui.IStartup;
import org.eclipse.ziggurat.inject.ZigguratInject;

public class InitManagers implements IStartup {

	@Override
	public void earlyStartup() {
		ZigguratInject.make(IDataModelManager.class);
	}

}
