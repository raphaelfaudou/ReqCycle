package org.eclipse.reqcycle.traceability.types.lifecycle.providers;

import java.util.Arrays;

import org.eclipse.reqcycle.traceability.types.TAttributeProvider;
import org.eclipse.reqcycle.traceability.types.configuration.typeconfiguration.AttributeType;
import org.eclipse.reqcycle.traceability.types.configuration.typeconfiguration.RegisteredAttribute;
import org.eclipse.reqcycle.traceability.types.configuration.typeconfiguration.TypeconfigurationFactory;
import org.eclipse.reqcycle.traceability.types.lifecycle.Activator;

public class LifeCycleProvider implements TAttributeProvider {

	private static final String LIFECYCLE_ID = Activator.PLUGIN_ID
			+ ".lifecycle";
	private static RegisteredAttribute[] ATTRIBUTES = new RegisteredAttribute[] { createRA(
			LIFECYCLE_ID, "lifecycle", AttributeType.STRING, new String[] {
					"DEFINED", "VALIDATED", "TO-CHECK" }) };

	@Override
	public Iterable<RegisteredAttribute> getAttributes() {
		return Arrays.asList(ATTRIBUTES);
	}

	private static RegisteredAttribute createRA(String id, String name,
			AttributeType type, String[] values) {
		RegisteredAttribute result = TypeconfigurationFactory.eINSTANCE
				.createRegisteredAttribute();
		result.setId(id);
		result.setName(name);
		result.setType(type);
		result.getPossibleValues().addAll(Arrays.asList(values));
		return result;
	}
}
