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
 *  Anass RADOUANI (AtoS) anass.radouani@atos.net - Initial API and implementation
 *
 *****************************************************************************/
package org.eclipse.reqcycle.repository.connector.rmf.ui;

import java.util.Collection;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.IBaseLabelProvider;
import org.eclipse.jface.viewers.IContentProvider;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.window.Window;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.reqcycle.repository.connector.ui.wizard.IRequirementSourceSettingPage;
import org.eclipse.reqcycle.repository.connector.ui.wizard.MappingComposite;
import org.eclipse.reqcycle.repository.connector.ui.wizard.MappingDialogPage;
import org.eclipse.rmf.reqif10.AttributeDefinition;
import org.eclipse.rmf.reqif10.DatatypeDefinition;
import org.eclipse.rmf.reqif10.SpecType;
import org.eclipse.rmf.reqif10.common.util.ReqIF10Util;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;

import DataModel.RequirementSource;
import MappingModel.AttributeMapping;
import MappingModel.ElementMapping;
import MappingModel.MappingModelFactory;

import com.google.common.base.Predicate;
import com.google.common.collect.Collections2;
import com.google.common.collect.Iterators;
import com.google.common.collect.Sets;


public abstract class RMFRepositoryMappingPage extends WizardPage implements IRequirementSourceSettingPage {

	private MappingComposite mappingComposite;

	private Button autoMappingBtn;

	private EObject toAdd;

	static ITreeContentProvider contentProvider = new ITreeContentProvider() {
		
		@Override
		public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
		}
		
		@Override
		public void dispose() {
		}
		
		@Override
		public boolean hasChildren(Object element) {
			return false;
		}
		
		@Override
		public Object getParent(Object element) {
			return null;
		}
		
		@Override
		public Object[] getElements(Object inputElement) {
			return ArrayContentProvider.getInstance().getElements(inputElement);
		}
		
		@Override
		public Object[] getChildren(Object parentElement) {
			return null;
		}
	};
	
	
	/**
	 * @param pageName
	 */
	public RMFRepositoryMappingPage(String title, String description) {
		super(title);
		setTitle(title);
		setDescription(description);
	}

	@Override
	public void createControl(final Composite parent) {
		mappingComposite = new MappingComposite(parent, SWT.NONE, this) {

			@Override
			public ElementMapping linkElements(Object sourceSelection, Object targetSelection) {
				return mapElements(parent, sourceSelection, targetSelection);
			}

			@Override
			protected String getResultDetail() {
				return RMFRepositoryMappingPage.this.getResultDetail();
			}

			@Override
			protected Object getTargetInput() {
				return RMFRepositoryMappingPage.this.getTargetInput();
			}

			@Override
			protected Object getSourceInput() {
				return RMFRepositoryMappingPage.this.getSourceInput();
			}

			@Override
			protected IContentProvider getSourceContentProvider() {
				return RMFRepositoryMappingPage.this.getSourceContentProvider();
			}

			@Override
			protected IBaseLabelProvider getSourceLabelProvider() {
				return RMFRepositoryMappingPage.this.getSourceLabelProvider();
			}

			@Override
			protected String getTargetDetail() {
				return RMFRepositoryMappingPage.this.getTargetDetail();
			}

			@Override
			protected String getSourceDetail() {
				return RMFRepositoryMappingPage.this.getSourceDetail();
			}

			@Override
			protected boolean getCanEditLink() {
				return false;
			}

			@Override
			protected void init(Collection<EObject> result) {
				Collection<EObject> mapping = addToMapping();
				if(mapping != null && !mapping.isEmpty()) {
					result.addAll(mapping);
				}
			}
			
		};


		autoMappingBtn = new Button(mappingComposite, SWT.PUSH);
		autoMappingBtn.setLayoutData(new GridData(1, 1, false, false));
		autoMappingBtn.setText("Map using names");
		autoMappingBtn.setEnabled(true);
		
		hookListeners();
		
		setControl(mappingComposite);

	}
	
	/**
	 * @return
	 */
	protected abstract Collection<EObject> addToMapping();

	protected abstract String getSourceDetail();

	protected abstract String getTargetDetail();

	protected abstract IBaseLabelProvider getSourceLabelProvider();

	protected abstract IContentProvider getSourceContentProvider();

	protected abstract Object getSourceInput();

	protected abstract Object getTargetInput();

	protected abstract String getResultDetail();

	private void hookListeners() {
		autoMappingBtn.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				RMFRepositoryMappingPage.this.generateMapping();
			}
		});
		
	}


	protected void generateMapping() {
		if (getSourceInput() instanceof Collection<?> && getTargetInput() instanceof Collection<?>) {
			for (Object inputElement : (Collection<?>)getSourceInput()) {
				if (inputElement instanceof SpecType) {
					final String inputName = ((SpecType) inputElement).getLongName();
					
					EClass element = Iterators.find(((Collection)getTargetInput()).iterator(), new Predicate<EClass>() {
						@Override
						public boolean apply(EClass arg0) {
							String ii = inputName;
							return ii.equalsIgnoreCase(arg0.getName());
						}
					});
					if (element != null) {
						ElementMapping elementMapping = MappingModelFactory.eINSTANCE.createElementMapping();
						EList<EAttribute> allAttributes = element.getEAllAttributes();
						Collection<EAttribute> filtered = Collections2.filter(allAttributes, new Predicate<EAttribute>() {

							@Override
							public boolean apply(EAttribute arg0) {
								return arg0.getEAnnotation("hidden") == null;
							}
						});
						elementMapping.getAttributes().addAll(mapAttributes(((SpecType) inputElement).getSpecAttributes(), filtered));
						elementMapping.setSourceQualifier(((SpecType) inputElement).getIdentifier());
						elementMapping.setDescription(((SpecType) inputElement).getLongName());
						elementMapping.setTargetElement((EClass)element);
						if (mappingComposite != null) {
							mappingComposite.addToResult(elementMapping);
						}
					}
				}
			}
		}
	}
	
	private Collection<AttributeMapping> mapAttributes(Collection<AttributeDefinition> source, Collection<EAttribute> target)
	{
		Collection<AttributeMapping> result = Sets.newHashSet();
		
		for (final AttributeDefinition attribute : source) {
			EAttribute eAttribute = Iterators.find(target.iterator(), new Predicate<EAttribute>() {
				@Override
				public boolean apply(EAttribute arg0) {
					String name = attribute.getLongName();
					return arg0.getName().equalsIgnoreCase(name);
				}
			});
			AttributeMapping attributeMapping = MappingModelFactory.eINSTANCE.createAttributeMapping();
			attributeMapping.setTargetAttribute((EAttribute)eAttribute);
			attributeMapping.setSourceId(((AttributeDefinition)attribute).getIdentifier());
			attributeMapping.setDescription(((AttributeDefinition)attribute).getLongName());
			result.add(attributeMapping);
		}
		return result;
	}


	/**
	 * @param parent
	 * @param sourceSelection
	 * @param targetSelection
	 * @return
	 */
	private ElementMapping mapElements(final Composite parent, Object sourceSelection, final Object targetSelection) {
		
		
		if(targetSelection instanceof EClass && sourceSelection instanceof SpecType) {

			final SpecType specType = (SpecType)sourceSelection;
			

			MappingDialogPage mappingDialog = new MappingDialogPage(parent.getShell()){
				@Override
				protected AttributeMapping linkElements(Object sourceSelection,
						Object targetSelection) {
					if(sourceSelection instanceof AttributeDefinition && targetSelection instanceof EAttribute) {
					AttributeMapping attributeMapping = MappingModelFactory.eINSTANCE.createAttributeMapping();
					attributeMapping.setTargetAttribute((EAttribute)targetSelection);
					attributeMapping.setSourceId(((AttributeDefinition)sourceSelection).getIdentifier());
					attributeMapping.setDescription(((AttributeDefinition)sourceSelection).getLongName());
					return attributeMapping;
				}
				return null;
				}

				@Override
				protected String getResultDetail() {
					return null;
				}

				@Override
				protected Object getTargetInput() {
					EList<EAttribute> allAttributes = ((EClass)targetSelection).getEAllAttributes();
					Collection<EAttribute> filteredAttribute = Collections2.filter(allAttributes, new Predicate<EAttribute>() {
						@Override
						public boolean apply(EAttribute arg0) {
							if (arg0.getEAnnotation("hidden") != null) {
								return false;
							}
							return true;
						}
					});
					return filteredAttribute;
				}

				@Override
				protected Object getSourceInput() {
					return specType.getSpecAttributes();
				}

				@Override
				protected boolean getCanEditLink() {
					return false;
				}

				@Override
				protected String getSourceDetail() {
					return null;
				}

				@Override
				protected String getTargetDetail() {
					return null;
				}

				@Override
				protected IContentProvider getSourceContentProvider() {
					return contentProvider;
				}

				@Override
				public ILabelProvider getSourceLabelProvider() {
					return new LabelProvider() {

						@Override
						public String getText(Object element) {
							if(element instanceof AttributeDefinition) {
								DatatypeDefinition dataTypeDef = ReqIF10Util.getDatatypeDefinition((AttributeDefinition)element);
								return ((AttributeDefinition)element).getLongName() + " : " + dataTypeDef.getClass().getSimpleName();
							}
							return super.getText(element);
						}
					};
				}
			};

			if(mappingDialog.open() == Window.OK) {
				if(mappingDialog.getResult() != null) {
					ElementMapping element = MappingModelFactory.eINSTANCE.createElementMapping();
					element.getAttributes().addAll((Collection<? extends AttributeMapping>)mappingDialog.getResult());
					element.setSourceQualifier(specType.getIdentifier());
					element.setDescription(specType.getLongName());
					element.setTargetElement((EClass)targetSelection);
					return element;
				}
			}

		}

		return null;
	}
	
	@Override
	public boolean preFinish(RequirementSource repository) {
		return true;
	}
	
	@Override
	public boolean isPageComplete() {
		return mappingComposite != null && mappingComposite.getResult() != null && !mappingComposite.getResult().isEmpty();
	}

	public Collection<EObject> getResult(){
		if(mappingComposite != null) {
			return mappingComposite.getResult();
		}
		return null;
	}


	@Override
	public boolean skipMapping() {
		return true;
	}
	
}
