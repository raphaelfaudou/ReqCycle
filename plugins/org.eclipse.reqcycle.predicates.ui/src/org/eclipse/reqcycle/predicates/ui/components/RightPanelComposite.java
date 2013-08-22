/*****************************************************************************
 * Copyright (c) 2013 AtoS.
 *
 *    
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *  Papa Issa DIAKHATE (AtoS) papa-issa.diakhate@atos.net - Initial API and implementation
 *
 *****************************************************************************/
package org.eclipse.reqcycle.predicates.ui.components;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.edit.provider.ComposedAdapterFactory;
import org.eclipse.emf.edit.ui.dnd.LocalTransfer;
import org.eclipse.emf.edit.ui.dnd.ViewerDragAdapter;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryLabelProvider;
import org.eclipse.jface.dialogs.InputDialog;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.ColumnWeightData;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.TableLayout;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.window.Window;
import org.eclipse.reqcycle.predicates.core.api.IPredicate;
import org.eclipse.reqcycle.predicates.core.util.PredicatesUtil;
import org.eclipse.reqcycle.predicates.persistance.util.IPredicatesConfManager;
import org.eclipse.reqcycle.predicates.ui.PredicatesUIPlugin;
import org.eclipse.reqcycle.predicates.ui.dialogs.NewPredicateDialog;
import org.eclipse.reqcycle.predicates.ui.listeners.CustomPredicatesTreeViewerDragAdapter;
import org.eclipse.reqcycle.predicates.ui.presentation.PredicatesEditor;
import org.eclipse.reqcycle.predicates.ui.providers.PredicatesTableLabelProvider;
import org.eclipse.reqcycle.predicates.ui.util.PredicatesUIHelper;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.dnd.DND;
import org.eclipse.swt.dnd.Transfer;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.eclipse.ziggurat.inject.ZigguratInject;

public class RightPanelComposite extends Composite {

	private ComposedAdapterFactory adapterFactory = new ComposedAdapterFactory(ComposedAdapterFactory.Descriptor.Registry.INSTANCE);

	private final PredicatesEditor predicatesEditor;

	private TableViewer tableViewerOfDefautPredicates;

	private TableViewer tableViewerOfCustomPredicates;
	
	private InputDialog savePredicateDialog;

	IPredicatesConfManager predicatesConfManager = ZigguratInject.make(IPredicatesConfManager.class);

	private Button buttonRemove;

	private Button buttonAdd;

	private Button buttonEdit;

	private Button buttonSave;

	private Button buttonFinish;

	protected TableItem editedItem;

	protected boolean editionMode = false;

	public RightPanelComposite(Composite parent, PredicatesEditor editor) {

		super(parent, SWT.None);
		setLayout(new GridLayout(1, false));

		this.predicatesEditor = editor;
		
		SashForm form = new SashForm(this, SWT.VERTICAL);
		form.setLayout(new GridLayout());
		form.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
		this.createGroupOfDefaultPredicates(form);
		this.createGroupOfCustomPredicates(form);
		
	}


	protected Collection<EPackage> getAllPackages(Resource resource)
    {
      List<EPackage> result = new ArrayList<EPackage>();
      for (TreeIterator<?> j =
             new EcoreUtil.ContentTreeIterator<Object>(resource.getContents())
             {
               private static final long serialVersionUID = 1L;

               @Override
               protected Iterator<? extends EObject> getEObjectChildren(EObject eObject)
               {
                 return
                   eObject instanceof EPackage ?
                     ((EPackage)eObject).getESubpackages().iterator() :
                       Collections.<EObject>emptyList().iterator();
               }
             };
           j.hasNext(); )
      {
        Object content = j.next();
        if (content instanceof EPackage)
        {
          result.add((EPackage)content);
        }
      }
      return result;
    }
	
	private void createGroupOfDefaultPredicates(Composite parent) {

		final Group grpDefaultPredicates = new Group(parent, SWT.NONE);
		grpDefaultPredicates.setText("Default Predicates");
		grpDefaultPredicates.setLayout(new GridLayout(1, false));
		grpDefaultPredicates.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));

		tableViewerOfDefautPredicates = new TableViewer(grpDefaultPredicates);

		final Table tableOfDefaultPredicates = tableViewerOfDefautPredicates.getTable();
		TableLayout tableLayout = new TableLayout();
		tableOfDefaultPredicates.setLayout(tableLayout);
		tableLayout.addColumnData(new ColumnWeightData(3, 100, true));

		tableOfDefaultPredicates.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		tableOfDefaultPredicates.setHeaderVisible(false);
		tableOfDefaultPredicates.setLinesVisible(false);

		TableViewerColumn column = new TableViewerColumn(tableViewerOfDefautPredicates, SWT.None);
		column.getColumn().setResizable(true);

		tableViewerOfDefautPredicates.setContentProvider(new IStructuredContentProvider() {

			@Override
			public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
			}

			@Override
			public void dispose() {
			}

			@Override
			public Object[] getElements(Object inputElement) {
				return PredicatesUtil.getDefaultPredicates().toArray();
			}
		});
		tableViewerOfDefautPredicates.setLabelProvider(new AdapterFactoryLabelProvider(adapterFactory));
		tableViewerOfDefautPredicates.setInput(new Object());

		final Transfer[] transferTypes = new Transfer[]{ LocalTransfer.getInstance() };
		final int dndOperations = DND.DROP_COPY | DND.DROP_MOVE;

		tableViewerOfDefautPredicates.addDragSupport(dndOperations, transferTypes, new ViewerDragAdapter(tableViewerOfDefautPredicates) {
		});

	}

	private void createGroupOfCustomPredicates(Composite parent) {

		final Group grpCustomPredicates = new Group(parent, SWT.NONE);
		grpCustomPredicates.setLayout(new GridLayout(1, false));
		grpCustomPredicates.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		grpCustomPredicates.setText("Custom Predicates");

		tableViewerOfCustomPredicates = new TableViewer(grpCustomPredicates, SWT.BORDER | SWT.FULL_SELECTION | SWT.MULTI);
		Table tableOfCustomPredicates = tableViewerOfCustomPredicates.getTable();
		tableOfCustomPredicates.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));

		TableLayout tableLayout = new TableLayout();
		tableLayout.addColumnData(new ColumnWeightData(3, 100, true));
		tableOfCustomPredicates.setLayout(tableLayout);

		TableViewerColumn column = new TableViewerColumn(tableViewerOfCustomPredicates, SWT.None);
		column.getColumn().setResizable(true);

		tableViewerOfCustomPredicates.setContentProvider(new IStructuredContentProvider() {

			@Override
			public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
			}

			@Override
			public void dispose() {
			}

			@Override
			public Object[] getElements(Object inputElement) {
				return predicatesConfManager.getPredicates(false).toArray();
			}
		});
		tableViewerOfCustomPredicates.setLabelProvider(new PredicatesTableLabelProvider());

		tableViewerOfCustomPredicates.setInput(predicatesConfManager.getPredicates(false));
		tableViewerOfCustomPredicates.addSelectionChangedListener(new ISelectionChangedListener() {
			
			@Override
			public void selectionChanged(SelectionChangedEvent event) {
				if(!editionMode) {
					ISelection selection = event.getSelection();
					boolean isEmptySelection = selection == null || event.getSelection().isEmpty();
					boolean isMultySelection = false;
					if(!isEmptySelection && selection instanceof IStructuredSelection) {
						isMultySelection = ((IStructuredSelection)selection).size() > 1;
					}
					if(buttonRemove != null)
						buttonRemove.setEnabled(!isEmptySelection);
					if(buttonEdit != null)
						buttonEdit.setEnabled(!isEmptySelection && !isMultySelection);
				}
			}
		});

		final Transfer[] transferTypes = new Transfer[]{ LocalTransfer.getInstance() };
		final int dndOperations = DND.DROP_COPY | DND.DROP_MOVE;

		tableViewerOfCustomPredicates.addDragSupport(dndOperations, transferTypes, new CustomPredicatesTreeViewerDragAdapter(tableViewerOfCustomPredicates) {
		});

		this.initTableMenuOfCustomPredicates(tableOfCustomPredicates);

		Composite compositeButtons = new Composite(grpCustomPredicates, SWT.NONE);
		compositeButtons.setLayout(new GridLayout(5, false));
		compositeButtons.setLayoutData(new GridData(SWT.CENTER, SWT.CENTER, false, false, 1, 1));

		
		buttonAdd = new Button(compositeButtons, SWT.NONE);
		buttonAdd.setLayoutData(new GridData(SWT.LEFT, SWT.BOTTOM, false, false, 1, 1));
		buttonAdd.setToolTipText("Add new predicate");
		buttonAdd.setImage(AbstractUIPlugin.imageDescriptorFromPlugin(PredicatesUIPlugin.PLUGIN_ID, "/icons/full/obj16/add_obj.gif").createImage());
		buttonAdd.addSelectionListener(new SelectionAdapter() {

			@Override
			public void widgetSelected(SelectionEvent e) {
					if(!createNewPredicate(getShell())) {
						return;
					}
					editionMode = true;
					buttonAdd.setEnabled(false);
					if(buttonRemove != null)
						buttonRemove.setEnabled(false);
					if(buttonEdit != null)
						buttonEdit.setEnabled(false);
					if(buttonSave != null)
						buttonSave.setEnabled(true);
					if(buttonFinish != null)
						buttonFinish.setEnabled(true);
			}
		});

		buttonRemove = new Button(compositeButtons, SWT.NONE);
		buttonRemove.setLayoutData(new GridData(SWT.LEFT, SWT.BOTTOM, false, false, 1, 1));
		buttonRemove.setToolTipText("Remove the selected predicates");
		buttonRemove.setImage(AbstractUIPlugin.imageDescriptorFromPlugin(PredicatesUIPlugin.PLUGIN_ID, "/icons/full/obj16/delete_obj.gif").createImage());
		buttonRemove.setEnabled(false);
		buttonRemove.addSelectionListener(new SelectionAdapter() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				if(tableViewerOfCustomPredicates.getSelection() instanceof IStructuredSelection) {
					IStructuredSelection selection = (IStructuredSelection)tableViewerOfCustomPredicates.getSelection();
					if(selection != null && !selection.isEmpty()) {
						List<IPredicate> predicatesToRemove = new ArrayList<IPredicate>();
						@SuppressWarnings("unchecked")
						Iterator<IStructuredSelection> iter = selection.iterator();
						while(iter.hasNext()) {
							Object currentObj = iter.next();
							if(currentObj instanceof IPredicate) {
								IPredicate predicate = (IPredicate)currentObj;
								predicatesToRemove.add(predicate);
							}
						}
						
						final StringBuilder confirmMessage = new StringBuilder("Do you really want to remove the following predicates :");
						final String lineSeparator = System.getProperty("line.separator");
						confirmMessage.append(lineSeparator).append(lineSeparator);
						for(IPredicate p : predicatesToRemove) {
							confirmMessage.append(" - ").append(p.getDisplayName()).append(lineSeparator);
						}
						boolean confirmRemoval = MessageDialog.openConfirm(getShell(), "Remove predicates", confirmMessage.toString());
						if(confirmRemoval) {
							for(IPredicate p : predicatesToRemove) {
								boolean removed = predicatesConfManager.removePredicate(p);
								if(removed) {
									predicatesConfManager.save();
									tableViewerOfCustomPredicates.remove(p);
								} else {
									MessageDialog.openError(getShell(), "Removal Error", "Unable to remove the predicate : " + p.getDisplayName());
								}
							}
						}
					}
				}
			}
		});
		
		
		
		buttonEdit = new Button(compositeButtons, SWT.NONE);
		buttonEdit.setLayoutData(new GridData(SWT.LEFT, SWT.BOTTOM, false, false, 1, 1));
		buttonEdit.setToolTipText("Edit the selected predicate");
		buttonEdit.setImage(AbstractUIPlugin.imageDescriptorFromPlugin(PredicatesUIPlugin.PLUGIN_ID, "/icons/full/obj16/edit_obj.png").createImage());
		buttonEdit.setEnabled(false);
		buttonEdit.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				TableItem item = tableViewerOfCustomPredicates.getTable().getItem(tableViewerOfCustomPredicates.getTable().getSelectionIndex());
				item.setBackground(new Color(getDisplay(), 255, 255, 255));
				tableViewerOfCustomPredicates.refresh();
				editedItem = item;
				Object data = item.getData();
				if (predicatesEditor != null && data instanceof IPredicate) {
					if (predicatesEditor.setRootPredicate(EcoreUtil.copy((IPredicate)data))) {
						predicatesEditor.setEditorTitle(((IPredicate)data).getDisplayName());
						editionMode = true;
						buttonEdit.setEnabled(false);
						if(buttonAdd != null)
							buttonAdd.setEnabled(false);
						if(buttonRemove != null)
							buttonRemove.setEnabled(false);
						if(buttonSave != null)
							buttonSave.setEnabled(true);
						if (buttonFinish != null)
							buttonFinish.setEnabled(true);
					}

				} 
			}
		});
		
		buttonSave = new Button(compositeButtons, SWT.NONE);
		buttonSave.setLayoutData(new GridData(SWT.LEFT, SWT.BOTTOM, false, false, 1, 1));
		buttonSave.setToolTipText("Save the edited predicate");
		buttonSave.setImage(AbstractUIPlugin.imageDescriptorFromPlugin(PredicatesUIPlugin.PLUGIN_ID, "/icons/full/obj16/Save_obj.gif").createImage());
		buttonSave.setEnabled(false);
		buttonSave.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				if (editedItem != null) {
					editedItem.setBackground(new Color (getDisplay(), 255, 255, 255));
					editedItem = null;
					tableViewerOfCustomPredicates.refresh();
				}
				
				//FIXME : set a real null progress monitor
				predicatesEditor.doSave(new NullProgressMonitor());

				buttonSave.setEnabled(true);
				if(buttonAdd != null)
					buttonAdd.setEnabled(false);
				if(buttonRemove != null)
					buttonRemove.setEnabled(false);
				if (buttonEdit != null)
					buttonEdit.setEnabled(false);
				if (buttonFinish != null)
					buttonFinish.setEnabled(true);
			}
		});
		
		buttonFinish = new Button(compositeButtons, SWT.NONE);
		buttonFinish.setLayoutData(new GridData(SWT.LEFT, SWT.BOTTOM, false, false, 1, 1));
		buttonFinish.setToolTipText("Finish the current edition and clear the editor");
		buttonFinish.setImage(AbstractUIPlugin.imageDescriptorFromPlugin(PredicatesUIPlugin.PLUGIN_ID, "/icons/full/obj16/clear_obj.png").createImage());
		buttonFinish.setEnabled(false);
		buttonFinish.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				if (predicatesEditor != null) {
					if (predicatesEditor.setRootPredicate(null)) {
						predicatesEditor.setEditorTitle("");
						editionMode = false;
						buttonFinish.setEnabled(false);
						if(buttonAdd != null)
							buttonAdd.setEnabled(true);
						if(buttonRemove != null)
							buttonRemove.setEnabled(true);
						if(buttonEdit != null)
							buttonEdit.setEnabled(true);
						if(buttonSave != null)
							buttonSave.setEnabled(false);
					}
					
					
				}
			}
		});
		
		
	}

	private void initTableMenuOfCustomPredicates(final Table table) {
		Menu menu = new Menu(table);
		table.setMenu(menu);

		MenuItem menuItemEdit = new MenuItem(menu, SWT.NONE);
		menuItemEdit.setText("Edit");
		menuItemEdit.addSelectionListener(new SelectionAdapter() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				int selectionIndex = tableViewerOfCustomPredicates.getTable().getSelectionIndex();
				IPredicate predicateToEdit = (IPredicate)tableViewerOfCustomPredicates.getElementAt(selectionIndex);
				openPredicateForEdition(predicateToEdit);
			}
		});
	}

	private void openPredicateForEdition(IPredicate predicate) {
		PredicatesUIHelper.openEditor(null, predicate);
	}

	public void addPredicate(IPredicate newPredicate) {
		if (tableViewerOfCustomPredicates != null) {
			tableViewerOfCustomPredicates.add(newPredicate);
			tableViewerOfCustomPredicates.setSelection(new StructuredSelection(newPredicate));
		}
	}
	
	public boolean createNewPredicate(Shell shell) {
		NewPredicateDialog dialog = new NewPredicateDialog(shell);
		boolean added = false;
		if(Window.OK == dialog.open()) {
			String name = dialog.getName();
			IPredicate rootPredicate = dialog.getRootPredicate();
			IPredicate newPredicate = EcoreUtil.copy(rootPredicate);
			newPredicate.setDisplayName(name);
			predicatesEditor.setRootPredicate(newPredicate);
			predicatesEditor.setDirty(false);
			added = predicatesConfManager.storePredicate(newPredicate);
			if(added) {
				addPredicate(newPredicate);
			} else if (!added) {
				MessageDialog.openError(shell, "Error adding predicate", "Unable to add the predicate : " + newPredicate.getDisplayName());
			}
		}
		return added;
	}

}
