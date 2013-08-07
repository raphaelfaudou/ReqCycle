package org.eclipse.reqcycle.repository.data.ui;

import org.eclipse.jface.layout.TableColumnLayout;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.reqcycle.repository.data.types.DataTypePackage;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;


public interface IDataModelUiManager {

	public TableViewer createDataModelTableViewer(Composite parent, TableColumnLayout packagesTVLayout);

	public void addListener(Listener listener);
		
	public void removeListener(Listener listener);

	public void addDataModels(DataTypePackage... models);

	public void notifyListeners(Event event);

	public void clear();
}
