package org.eclipse.reqcycle.repository.data.ui;

import java.util.ArrayList;
import java.util.Collection;

import javax.inject.Inject;
import javax.inject.Singleton;

import org.eclipse.jface.layout.TableColumnLayout;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ColumnLabelProvider;
import org.eclipse.jface.viewers.ColumnWeightData;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.reqcycle.repository.data.IDataModelManager;
import org.eclipse.reqcycle.repository.data.types.DataModel;
import org.eclipse.reqcycle.repository.data.ui.preference.PreferenceUiUtil;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Table;

@Singleton
public class DataModelUiManager implements IDataModelUiManager {

	protected Listener listener;
	protected Collection<DataModel> inputModels;
	protected Button btnAddModel;
	protected Button btnEditModel;
	protected Collection<Listener> listeners = new ArrayList<Listener>();
	protected Collection<TableViewer> viewers = new ArrayList<TableViewer>();
	
	@Inject
	IDataModelManager dataModelManager;
	
	@Inject
	public DataModelUiManager(IDataModelManager dataModelManager) {
		this.dataModelManager = dataModelManager;
		inputModels = new ArrayList<DataModel>();
		inputModels.addAll(dataModelManager.getAllDataModels());
	}
	
	
	public TableViewer createDataModelTableViewer(Composite parent, TableColumnLayout packagesTVLayout) {

		//Table Viewer
		TableViewer tvModels = new TableViewer(parent);
		tvModels.setContentProvider(ArrayContentProvider.getInstance());
		Table tModels = tvModels.getTable();
		tModels.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		tModels.setLinesVisible(true);

		//Columns
		TableViewerColumn tvcModelsNames = PreferenceUiUtil.createTableViewerColumn(tvModels, "Name", SWT.None);
		tvcModelsNames.setLabelProvider(new ColumnLabelProvider() {

			@Override
			public String getText(Object element) {
				if(element instanceof DataModel) {
					return ((DataModel)element).getName();
				}
				return super.getText(element);
			}
		});
		packagesTVLayout.setColumnData(tvcModelsNames.getColumn(), new ColumnWeightData(20, 100, true));

		tvModels.setInput(inputModels);

		viewers.add(tvModels);
		
		return tvModels;
	}
	


	public void addListener(Listener listener) {
		listeners.add(listener);
	}
	
	public void removeListener(Listener listener) {
		listeners.remove(listener);
	}

	public void addDataModels(DataModel... models) {
		for(DataModel model : models) {
			inputModels.add(model);
		}
		notifyListeners(new Event());
	}


	public void notifyListeners(Event event) {
		for(Listener listener : listeners) {
			listener.handleEvent(event);
		}
	}


	public void clear() {
		inputModels.clear();
		notifyListeners(new Event());
	}

}
