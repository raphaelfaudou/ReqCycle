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
package RequirementSourceConf;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Requirement Sources</b></em>'.
 * <!-- end-user-doc -->
 * 
 * <p>
 * The following features are supported:
 * <ul>
 * <li>{@link RequirementSourceConf.RequirementSources#getRequirementSources <em>Requirement Sources</em>}</li>
 * </ul>
 * </p>
 * 
 * @see RequirementSourceConf.RequirementSourceConfPackage#getRequirementSources()
 * @model
 * @generated
 */
public interface RequirementSources extends EObject {

	/**
	 * Returns the value of the '<em><b>Requirement Sources</b></em>' containment reference list.
	 * The list contents are of type {@link RequirementSourceConf.RequirementSource}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Requirement Sources</em>' containment reference list isn't clear, there really should be more of a description
	 * here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>Requirement Sources</em>' containment reference list.
	 * @see RequirementSourceConf.RequirementSourceConfPackage#getRequirementSources_RequirementSources()
	 * @model containment="true"
	 * @generated
	 */
	EList<RequirementSource> getRequirementSources();

	void removeRequirementSource(RequirementSource requirementSource);

} // RequirementSources
