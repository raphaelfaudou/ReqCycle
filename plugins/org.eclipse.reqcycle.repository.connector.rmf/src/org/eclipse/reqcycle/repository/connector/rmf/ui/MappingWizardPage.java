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

import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.widgets.Composite;


public class MappingWizardPage extends WizardPage {

	/**
	 * @param pageName
	 */
	protected MappingWizardPage(String pageName) {
		super(pageName);
		// TODO Auto-generated constructor stub
	}
//
//	private MappingComposite mappingComposite;
//
//	private Object sourceInput;
//
//	private ILabelProvider sourceLabelProvider;
//
//	private IStructuredContentProvider sourceContentProvider;
//
//	private Object targetInput;
//
//	private ILabelProvider targetLabelProvider;
//
//	private IStructuredContentProvider targetContentProvider;
//
//	private ILabelProvider resultLabelProvider;
//
//	private IStructuredContentProvider resultContentProvider;
//	
//	private String targetDetail;
//	
//	private String sourceDetail;
//
//	private Button autoMappingBtn;
//
//	private String connectorID;
//
//	
//	/**
//	 * @param pageName
//	 */
//	public MappingWizardPage(String title, String description) {
//		super(title);
//		setTitle(title);
//		setDescription(description);
//	}
//
//	
//	@Override
//	public void createControl(final Composite parent) {
//		mappingComposite = new MappingComposite(parent, SWT.NONE, this) {
//			@Override
//			public ElementMapping linkElements(Object sourceSelection, Object targetSelection) {
//				return mapElements(parent, sourceSelection, targetSelection);
//			}
//
//			@Override
//			protected String getResultDetail() {
//				return null;
//			}
//
//			@Override
//			protected Object getTargetInput() {
//				return null;
//			}
//
//			@Override
//			protected Object getSourceInput() {
//				return null;
//			}
//
//			@Override
//			protected IContentProvider getSourceContentProvider() {
//				return null;
//			}
//
//			@Override
//			protected IBaseLabelProvider getSourceLabelProvider() {
//				return null;
//			}
//
//			@Override
//			protected String getTargetDetail() {
//				return "ReqCycle";
//			}
//
//			@Override
//			protected String getSourceDetail() {
//				return "ReqIF";
//			}
//
//			@Override
//			protected boolean getCanEditLink() {
//				return true;
//			}
//
//			
//		};
//
//		hookListeners();
//		
//		setControl(mappingComposite);
//
//	}
//	
//	private void hookListeners() {
//		
//	}
//
//
//	protected void generateMapping() {
//		if (sourceInput instanceof Collection<?> && targetInput instanceof Collection<?>) {
//			for (Object inputElement : (Collection<?>)sourceInput) {
//				if (inputElement instanceof SpecType) {
//					final String inputName = ((SpecType) inputElement).getLongName();
//					EClass element = Iterators.find(((Collection)targetInput).iterator(), new Predicate<EClass>() {
//						@Override
//						public boolean apply(EClass arg0) {
//							return inputName.equals(arg0.getName());
//						}
//					});
//					if (element != null) {
//						ElementMapping elementMapping = MappingModelFactory.eINSTANCE.createElementMapping();
//						elementMapping.getAttributes().addAll(mapAttributes(((SpecType) inputElement).getSpecAttributes(), element.getEAttributes()));
//						elementMapping.setSourceQualifier(((SpecType) inputElement).getIdentifier());
//						elementMapping.setDescription(((SpecType) inputElement).getLongName());
//						elementMapping.setTargetElement((EClass)element);
//						if (mappingComposite != null) {
//							mappingComposite.addToResult(elementMapping);
//						}
//					}
//				}
//			}
//		}
//	}
//	
//	private Collection<AttributeMapping> mapAttributes(Collection<AttributeDefinition> source, Collection<EAttribute> target)
//	{
//		Collection<AttributeMapping> result = Sets.newHashSet();
//		
//		for (final AttributeDefinition attribute : source) {
//			EAttribute eAttribute = Iterators.find(target.iterator(), new Predicate<EAttribute>() {
//				@Override
//				public boolean apply(EAttribute arg0) {
//					return arg0.getName().equals(attribute.getLongName());
//				}
//			});
//			AttributeMapping attributeMapping = MappingModelFactory.eINSTANCE.createAttributeMapping();
//			attributeMapping.setTargetAttribute((EAttribute)eAttribute);
//			attributeMapping.setSourceId(((AttributeDefinition)attribute).getIdentifier());
//			attributeMapping.setDescription(((AttributeDefinition)attribute).getLongName());
//			result.add(attributeMapping);
//		}
//		return result;
//	}
//
//
//	/**
//	 * @param parent
//	 * @param sourceSelection
//	 * @param targetSelection
//	 * @return
//	 */
//	private ElementMapping mapElements(final Composite parent, Object sourceSelection, Object targetSelection) {
//		
//		if(targetSelection instanceof EClass && sourceSelection instanceof SpecType) {
//
//			SpecType specType = (SpecType)sourceSelection;
//			
//
//			MappingDialog mappingDialog = new MappingDialog(parent.getShell()){
//				@Override
//				protected AttributeMapping linkElements(Object sourceSelection,
//						Object targetSelection) {
//					if(sourceSelection instanceof AttributeDefinition && targetSelection instanceof EAttribute) {
//					AttributeMapping attributeMapping = MappingModelFactory.eINSTANCE.createAttributeMapping();
//					attributeMapping.setTargetAttribute((EAttribute)targetSelection);
//					attributeMapping.setSourceId(((AttributeDefinition)sourceSelection).getIdentifier());
//					attributeMapping.setDescription(((AttributeDefinition)sourceSelection).getLongName());
//					return attributeMapping;
//				}
//				return null;
//				}
//			};
//
//			mappingDialog.setSourceLabelProvider(new LabelProvider() {
//
//				@Override
//				public String getText(Object element) {
//					if(element instanceof AttributeDefinition) {
//						DatatypeDefinition dataTypeDef = ReqIF10Util.getDatatypeDefinition((AttributeDefinition)element);
//						return ((AttributeDefinition)element).getLongName() + " : " + dataTypeDef.getLongName() + " : " + dataTypeDef.getClass().getSimpleName();
//					}
//					return super.getText(element);
//				}
//			});
//			mappingDialog.setSourceContentProvider(DataUtil.CollectionTreeContentProvider);
//			mappingDialog.setSourceInput(specType.getSpecAttributes());
//
//			mappingDialog.setTargetLabelProvider(new LabelProvider() {
//
//				@Override
//				public String getText(Object element) {
//					if(element instanceof EAttribute) {
//						return ((EAttribute)element).getName() + " : " + ((EAttribute)element).getEType().getName();
//					}
//					return super.getText(element);
//				}
//			});
//			mappingDialog.setTargetContentProvider(DataUtil.CollectionTreeContentProvider);
//			EList<EAttribute> attibutes = ((EClass)targetSelection).getEAttributes();
//			Collection<EAttribute> filteredAttribute = Collections2.filter(attibutes, new Predicate<EAttribute>() {
//				@Override
//				public boolean apply(EAttribute arg0) {
//					return true;
//				}
//			});
//			mappingDialog.setTargetInput(filteredAttribute);
//			mappingDialog.setResultLabelProvider(new LabelProvider() {
//				@Override
//				public String getText(Object element) {
//					if(element instanceof AttributeMapping) {
//						return ((AttributeMapping)element).getDescription() + " --> " + ((AttributeMapping)element).getTargetAttribute().getName();
//					}
//					return super.getText(element);
//				}
//			});
//			mappingDialog.setResultContentProvider(DataUtil.CollectionTreeContentProvider);
//
//			if(mappingDialog.open() == Window.OK) {
//				if(mappingDialog.getResult() != null) {
//					ElementMapping element = MappingModelFactory.eINSTANCE.createElementMapping();
//					element.getAttributes().addAll((Collection<? extends AttributeMapping>)mappingDialog.getResult());
//					element.setSourceQualifier(specType.getIdentifier());
//					element.setDescription(specType.getLongName());
//					element.setTargetElement((EClass)targetSelection);
//					return element;
//				}
//			}
//
//		}
//
//		return null;
//	}
//	
//	
//	@Override
//	public boolean isPageComplete() {
//		return mappingComposite != null && mappingComposite.getResult() != null && !mappingComposite.getResult().isEmpty();
//	}
//
//	public Collection<EObject> getResult(){
//		if(mappingComposite != null) {
//			return mappingComposite.getResult();
//		}
//		return null;
//	}
//
//	

	/* (non-Javadoc)
	 * @see org.eclipse.jface.dialogs.IDialogPage#createControl(org.eclipse.swt.widgets.Composite)
	 */
	@Override
	public void createControl(Composite parent) {
		// TODO Auto-generated method stub
		
	}
}
