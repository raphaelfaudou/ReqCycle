package org.eclipse.reqcycle.traceability.builder.impl;

import java.util.Arrays;
import java.util.Set;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.Platform;
import org.eclipse.reqcycle.traceability.builder.Activator;
import org.eclipse.reqcycle.traceability.builder.IBuildingDecoration;
import org.eclipse.ziggurat.inject.ZigguratInject;

import com.google.common.base.Function;
import com.google.common.base.Predicates;
import com.google.common.collect.Sets;

import static com.google.common.collect.Iterables.filter;
import static com.google.common.collect.Iterables.transform;

public class ExtensionPointReader {
	private static String EXT_ID = "newUpwardDecoration";

	public Set<IBuildingDecoration> read() {
		IConfigurationElement[] elements = Platform.getExtensionRegistry()
				.getConfigurationElementsFor(Activator.PLUGIN_ID, EXT_ID);
		return Sets
				.newHashSet(filter(
						transform(
								Arrays.asList(elements),
								new Function<IConfigurationElement, IBuildingDecoration>() {

									@Override
									public IBuildingDecoration apply(
											IConfigurationElement arg0) {
										try {
											IBuildingDecoration createExecutableExtension = (IBuildingDecoration) arg0
													.createExecutableExtension("decorate");
											ZigguratInject
													.inject(createExecutableExtension);
											return createExecutableExtension;
										} catch (CoreException e) {
											e.printStackTrace();
										}
										return null;
									}

								}), Predicates.notNull()));
	}
}
