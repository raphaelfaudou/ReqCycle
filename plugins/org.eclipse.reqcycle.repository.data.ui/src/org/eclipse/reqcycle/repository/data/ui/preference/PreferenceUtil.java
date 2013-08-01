package org.eclipse.reqcycle.repository.data.ui.preference;

import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.reqcycle.repository.data.ui.Activator;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.TableColumn;


public class PreferenceUtil {

	public static Group createGroup(Composite parent, String name) {
		Group grpElements = new Group(parent, SWT.NONE);
		grpElements.setLayout(new GridLayout(2, false));
		grpElements.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		grpElements.setText(name);
		return grpElements;
	}
	
	public static TableViewerColumn createTableViewerColumn(TableViewer viewer, String title, int bound, int colNumber, int style) {
		TableViewerColumn viewerColumn = new TableViewerColumn(viewer, style);
		TableColumn column = viewerColumn.getColumn();
		column.setText(title);
		column.setWidth(bound);
		column.setResizable(true);
		column.setMoveable(true);
		return viewerColumn;
	}
	
	public static Button createAddButton(Composite parent, String toolTip) {
		Button btn = new Button(parent, SWT.PUSH);
		btn.setLayoutData(new GridData(SWT.LEFT, SWT.TOP, false, false));
		btn.setToolTipText(toolTip);
		btn.setImage(Activator.getImageDescriptor("/icons/add.gif").createImage());
		return btn;
	}
	
}
