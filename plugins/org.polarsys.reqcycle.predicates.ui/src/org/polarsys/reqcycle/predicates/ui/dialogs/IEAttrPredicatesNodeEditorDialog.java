/*******************************************************************************
 * * Copyright (c) 2013 AtoS
 * * All rights reserved. This program and the accompanying materials
 * * are made available under the terms of the Eclipse Public License v1.0
 * * which accompanies this distribution, and is available at
 * * http://www.eclipse.org/legal/epl-v10.html *
 * * Contributors:
 * * Papa Issa Diakhate (AtoS) - initial API and implementation and/or initial documentation
 *******************************************************************************/
package org.polarsys.reqcycle.predicates.ui.dialogs;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.lang.ClassUtils;
import org.eclipse.emf.common.util.Enumerator;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EGenericType;
import org.eclipse.emf.ecore.ENamedElement;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EOperation;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerFilter;
import org.polarsys.reqcycle.predicates.core.api.IEAttrPredicate;
import org.polarsys.reqcycle.predicates.core.util.PredicatesUtil;
import org.polarsys.reqcycle.predicates.ui.components.PredicatePropsEditor;
import org.polarsys.reqcycle.predicates.ui.util.SWTUtil;
import org.polarsys.reqcycle.ui.eattrpropseditor.EAttrPropsEditorPlugin;
import org.polarsys.reqcycle.ui.eattrpropseditor.GenericEAttrPropsEditor;
import org.polarsys.reqcycle.ui.eattrpropseditor.api.IEAttrPropsEditor;
import org.polarsys.reqcycle.ui.eattrpropseditor.api.IEditionResult;
import org.polarsys.reqcycle.ui.eattrpropseditor.impl.SimpleEditionResult;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Tree;

public class IEAttrPredicatesNodeEditorDialog extends Dialog {

	/** The TreeViewer that will contain the model to edit (the model to which the predicate is going to be applied). */
	private TreeViewer treeViewer;

	/** The collection of EClass of the model to which we are going to apply the predicate. */
	private final Collection<EClass> eClassesOfModelToEdit;

	private final boolean useExtendedFeature;

	/** The predicate to edit. */
	private final IEAttrPredicate eAttrPredicate;

	/** The "input" attribute of the predicate if it is of type {@link UnaryPredicate} */
	private EAttribute predicateInputAttribute;

	/** The {@link PredicatePropsEditor}. */
	private PredicatePropsEditor predicatePropsEditor;

	public IEAttrPredicatesNodeEditorDialog(final Shell parentShell, final IEAttrPredicate eAttrPredicate, final Collection<EClass> eClassesOfModelToEdit, final boolean useExtendedFeature) {
		super(parentShell);
		this.eAttrPredicate = eAttrPredicate;
		this.eClassesOfModelToEdit = eClassesOfModelToEdit;
		this.useExtendedFeature = useExtendedFeature;
		this.predicateInputAttribute = (EAttribute)this.eAttrPredicate.eClass().getEStructuralFeature("input");
	}

	@Override
	protected Control createDialogArea(Composite parent) {

		final Composite container = (Composite)super.createDialogArea(parent);
		container.setLayout(new GridLayout(1, false));

		this.initTreeViewer(container);

		this.initPredicatePropsEditor(container);

		return container;
	}

	private void initTreeViewer(final Composite container) {
		this.treeViewer = new TreeViewer(container, SWT.BORDER);
		final Tree tree = treeViewer.getTree();
		tree.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		treeViewer.setContentProvider(new EClassContentProvider());
		treeViewer.setLabelProvider(new EClassLabelProvider());
		treeViewer.addSelectionChangedListener(new SelectionChangedListenerImpl());
		treeViewer.addFilter(new ModelAttributesViewerFilter());
		treeViewer.setInput(this.eClassesOfModelToEdit);
	}

	private void initPredicatePropsEditor(final Composite container) {
		this.predicatePropsEditor = new PredicatePropsEditor(this.eAttrPredicate, container, SWT.BORDER);
		this.predicatePropsEditor.setLayout(new GridLayout(1, false));
		this.predicatePropsEditor.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		SWTUtil.recursiveSetEnabled(predicatePropsEditor, false);
	}

	@Override
	protected boolean isResizable() {
		return true;
	}

	@Override
	protected void createButtonsForButtonBar(Composite parent) {
		createButton(parent, IDialogConstants.OK_ID, IDialogConstants.OK_LABEL, true);
		createButton(parent, IDialogConstants.CANCEL_ID, IDialogConstants.CANCEL_LABEL, false);
	}

	@Override
	protected Point getInitialSize() {
		return new Point(450, 300);
	}

	/**
	 * @return A new instance of {@link UnaryPredicate} representing the edited predicate.
	 */
	public IEditionResult getEditionResult() {
		final IEditionResult result = new SimpleEditionResult(this.eAttrPredicate);
		final Collection<GenericEAttrPropsEditor> editors = this.predicatePropsEditor.getEditors().values();
		for(final GenericEAttrPropsEditor editor : editors) {
			final EAttribute attr = editor.getEAttribute();
			final Object attrValue = editor.getEnteredValue();
			result.getEditedEntries().put(attr, attrValue);
		}
		return result;
	}

	@Override
	protected void buttonPressed(int buttonId) {
		if(buttonId == IDialogConstants.OK_ID) {
			if(!editionValid()) {
				// Verify the validity of the edition
				MessageDialog.openError(getShell(), "Error", "Some errors occured during the edition of one or more properties.");
				return; // do not accept the OK ...
			}
		}
		super.buttonPressed(buttonId);
	}

	/**
	 * @return <code>true</code> if and only if all properties editors return <code>true</code> for {@link GenericEAttrPropsEditor#isEditionValid()}.
	 */
	private boolean editionValid() {
		final Collection<GenericEAttrPropsEditor> editors = this.predicatePropsEditor.getEditors().values();
		for(final GenericEAttrPropsEditor editor : editors) {
			if(!editor.isEditionValid()) {
				return false;
			}
		}
		return true;
	}

	private class EClassContentProvider implements ITreeContentProvider {

		public EClassContentProvider() {
		}

		@Override
		public void dispose() {
		}

		@Override
		public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
		}

		@Override
		public Object[] getElements(Object inputElement) {
			return ArrayContentProvider.getInstance().getElements(inputElement);
		}

		@Override
		public Object[] getChildren(Object parentElement) {
			if(!useExtendedFeature) {
				if(parentElement instanceof EClass) {
					return ((EClass)parentElement).getEAllAttributes().toArray();
				}
			} else {
				if(parentElement instanceof EClass) {
					return ((EClass)parentElement).getEAllStructuralFeatures().toArray();

				} else if(parentElement instanceof EReference) {
					return ((EReference)parentElement).eContents().toArray();
				} else if(parentElement instanceof EGenericType) {
					final EClassifier classifier = ((EGenericType)parentElement).getEClassifier();
					if(classifier != null && (classifier instanceof EClass)) {
						return ((EClass)classifier).getEAllStructuralFeatures().toArray();
					}
				}
			}

			return null;
		}

		@Override
		public Object getParent(Object element) {
			if(element instanceof EClass) {
				return ((EClass)element).eContainer();
			}
			return null;
		}

		@Override
		public boolean hasChildren(Object element) {
			if(element instanceof EClass) {
				final List<EObject> original = ((EClass)element).eContents();
				final Collection<EObject> copy = EcoreUtil.copyAll(original);
				final Iterator<EObject> iter = copy.iterator();
				while(iter.hasNext()) {
					final EObject eObj = iter.next();
					if(eObj instanceof EOperation)
						iter.remove();
				}
				return copy.size() > 0;

			} else if(useExtendedFeature) {
				return ((element instanceof EReference) || (element instanceof EGenericType));
			}

			return false;
		}
	}

	private class EClassLabelProvider extends LabelProvider {

		@Override
		public String getText(Object element) {
			if(element instanceof ENamedElement) {
				return ((ENamedElement)element).getName();
			} else if(element instanceof EGenericType) {
				return this.getText(((EGenericType)element).getEClassifier());
			}
			return super.getText(element);
		}
	}

	/**
	 * This ISelectionChangedListener shows the correct IEAttrPropsEditor to use when the current selection is
	 * positioned onto an attribute. If the selection is not an attribute, no properties editor will be shown.
	 * 
	 * @see ModelAttributesViewerFilter
	 */
	private class SelectionChangedListenerImpl implements ISelectionChangedListener {

		private EAttribute previousSelectedAttribute;

		public SelectionChangedListenerImpl() {
		}

		@Override
		public void selectionChanged(SelectionChangedEvent event) {
			boolean editorEnabled = false;
			if(event.getSelection() instanceof IStructuredSelection) {

				final IStructuredSelection selection = (IStructuredSelection)event.getSelection();
				final Object selectedObject = selection.getFirstElement();

				if(selectedObject instanceof EAttribute) {

					predicatePropsEditor.removeEditor(previousSelectedAttribute);
					final EAttribute selectedAttr = (EAttribute)selectedObject;
					this.previousSelectedAttribute = selectedAttr;

					final EAttribute specificInputAttr = PredicatesUtil.getUserSpecificInputAttribute(eAttrPredicate);
					if(PredicatesUtil.isAdaptable(specificInputAttr)) {
						String javaClassName = EAttrPropsEditorPlugin.getEditorType(specificInputAttr.getEType());
						predicatePropsEditor.addEditor(selectedAttr, javaClassName);

					} else if(Collection.class.equals(PredicatesUtil.getCastClassForInput(eAttrPredicate))) {
						predicatePropsEditor.addEditor(selectedAttr, Collection.class.getName());

					} else {
						predicatePropsEditor.addEditor(selectedAttr);
					}

					EClassifier eType = selectedAttr.getEType();
					Class<?> selectedClass = eType.getInstanceClass();
					if(selectedClass == null && eType instanceof EEnum) {
						selectedClass = Enumerator.class;
					}
					//TODO : [EnumLiteral] Add Literal (String), not the EnumLiteral
					editorEnabled = PredicatesUtil.isSubType(predicateInputAttribute, selectedClass);
				} else {
					predicatePropsEditor.removeEditor(previousSelectedAttribute);
				}
			} else {
				predicatePropsEditor.removeEditor(previousSelectedAttribute);
			}

			SWTUtil.recursiveSetEnabled(predicatePropsEditor, editorEnabled);
		}
	}

	/**
	 * A ViewerFilter that shows only compatible attributes that can be edited by the {@link IEAttrPropsEditor} which is
	 * going to use it. For example, if we choose a CharSequence Properties Editor, only the attributes which are
	 * CharSequence or sub types of CharSequence will be shown into the TreeViewer.
	 */
	private class ModelAttributesViewerFilter extends ViewerFilter {

		@Override
		public boolean select(Viewer viewer, Object parentElement, Object element) {
			if(element instanceof EStructuralFeature) {
				final EClassifier eType = ((EStructuralFeature)element).getEType();
				Class<?> selectedClass = eType.getInstanceClass();
				if(selectedClass != null) {
					if(selectedClass.isPrimitive()) {
						selectedClass = ClassUtils.primitiveToWrapper(selectedClass);
					}
					boolean isSubType = false;
					EClass c1 = eAttrPredicate.eClass();
					EClass c2 = predicateInputAttribute.getEContainingClass();
					if(c1.equals(c2)) {
						isSubType = PredicatesUtil.isSubType(predicateInputAttribute, selectedClass);
					} else {
						Class<?> inputClassCast = PredicatesUtil.getCastClassForInput(eAttrPredicate);
						if(Collection.class.equals(inputClassCast)) {
							inputClassCast = PredicatesUtil.getObjectClassForInput(eAttrPredicate);
						}
						isSubType = inputClassCast.isAssignableFrom(selectedClass);
					}
					return isSubType;
				} else if(selectedClass == null && eType instanceof EEnum) {
					// It the selected class is an Enumerator (EEnum) ... getInstanceClass() tends to return null.
					// Thus, we have to do the following in order to verify whether or not the selected EAttribute
					// is to be filtered :)
					Class<?> inputClassCast = PredicatesUtil.getCastClassForInput(eAttrPredicate);
					if(Enumerator.class.isAssignableFrom(inputClassCast)) {
						return true;
					}
				}
			}
			return true;
		}
	}

}
