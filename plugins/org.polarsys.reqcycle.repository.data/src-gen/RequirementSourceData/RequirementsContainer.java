/*******************************************************************************
 * * Copyright (c) 2013 AtoS
 * * All rights reserved. This program and the accompanying materials
 * * are made available under the terms of the Eclipse Public License v1.0
 * * which accompanies this distribution, and is available at
 * * http://www.eclipse.org/legal/epl-v10.html *
 * * Contributors:
 * * Anass Radouani (AtoS) - initial API and implementation and/or initial documentation
 *******************************************************************************/
/**
 */
package RequirementSourceData;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;

import RequirementSourceConf.RequirementSource;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Requirements Container</b></em>'.
 * <!-- end-user-doc -->
 * 
 * <p>
 * The following features are supported:
 * <ul>
 * <li>{@link RequirementSourceData.RequirementsContainer#getRequirements <em>Requirements</em>}</li>
 * </ul>
 * </p>
 * 
 * @see RequirementSourceData.RequirementSourceDataPackage#getRequirementsContainer()
 * @model
 * @generated
 */
public interface RequirementsContainer extends EObject {

	/**
	 * Returns the value of the '<em><b>Requirements</b></em>' containment reference list.
	 * The list contents are of type {@link RequirementSourceData.AbstractElement}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Requirements</em>' containment reference isn't clear, there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>Requirements</em>' containment reference list.
	 * @see RequirementSourceData.RequirementSourceDataPackage#getRequirementsContainer_Requirements()
	 * @model containment="true"
	 * @generated
	 */
	EList<AbstractElement> getRequirements();

	RequirementSource getRequirementSource();

} // RequirementsContainer
