/*******************************************************************************
 * * Copyright (c) 2013 AtoS
 * * All rights reserved. This program and the accompanying materials
 * * are made available under the terms of the Eclipse Public License v1.0
 * * which accompanies this distribution, and is available at
 * * http://www.eclipse.org/legal/epl-v10.html *
 * * Contributors:
 * * Tristan Faure (AtoS) - initial API and implementation and/or initial documentation
 *******************************************************************************/
package org.polarsys.reqcycle.utils.inject;

import java.lang.annotation.Annotation;
import java.util.Map;
import java.util.Map.Entry;

import org.eclipse.e4.core.contexts.ContextInjectionFactory;
import org.eclipse.e4.core.contexts.EclipseContextFactory;
import org.eclipse.e4.core.contexts.IEclipseContext;
import org.eclipse.e4.core.di.IInjector;
import org.eclipse.e4.core.di.InjectionException;
import org.eclipse.e4.core.di.InjectorFactory;
import org.eclipse.e4.core.internal.contexts.ContextObjectSupplier;
import org.eclipse.e4.core.services.contributions.IContributionFactory;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.osgi.framework.BundleContext;

/**
 * The activator class controls the plug-in life cycle
 */
@SuppressWarnings("restriction")
public class ZigguratInject extends AbstractUIPlugin {

	public static final String PLUGIN_ID = "org.polarsys.reqcycle.utils.inject"; //$NON-NLS-1$

	// The shared instance
	private static ZigguratInject plugin;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.ui.plugin.AbstractUIPlugin#start(org.osgi.framework.BundleContext
	 * )
	 */
	public void start(BundleContext context) throws Exception {
		super.start(context);
		BindingManager.registerBindings();
		plugin = this;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.ui.plugin.AbstractUIPlugin#stop(org.osgi.framework.BundleContext
	 * )
	 */
	public void stop(BundleContext context) throws Exception {
		plugin = null;
		super.stop(context);
	}

	/**
	 * Returns the shared instance
	 * 
	 * @return the shared instance
	 */
	public static ZigguratInject getDefault() {
		return plugin;
	}

	/**
	 * The constructor
	 */
	public ZigguratInject() {
	}

	public static <T> T make(Class<T> theInterface) {
		IEclipseContext context = (IEclipseContext) PlatformUI.getWorkbench()
				.getService(IEclipseContext.class);
		return make(theInterface, context);
	}

	/**
	 * Injects data into an object, using the eclipse context.
	 * 
	 * @param object
	 *            the object to perform injection on
	 */
	private static void doInject(Object object) {
		int cpt = 0;
		while (!PlatformUI.isWorkbenchRunning()) {
			try {
				Thread.currentThread().sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			if (cpt >= 5) {
				break;
			}
			cpt++;
		}
		IEclipseContext context = (IEclipseContext) PlatformUI.getWorkbench()
				.getService(IEclipseContext.class);
		inject(object, context);
	}

	/**
	 * Injects data into an object, using a specified context.
	 * 
	 * @param object
	 *            the object to perform injection on
	 */
	public static void inject(Object object, IEclipseContext context) {
		InjectorFactory.getDefault().inject(
				object,
				ContextObjectSupplier.getObjectSupplier(context,
						InjectorFactory.getDefault()));
	}

	/**
	 * Retrieves an instance of a given class. This instance will be injected
	 * with data found in the given context.
	 * 
	 * @param theInterface
	 * @param context
	 * @return
	 */
	public static <T> T make(Class<T> theInterface, IEclipseContext context) {
		return ContextInjectionFactory.make(theInterface, context);
	}

	/**
	 * Call the annotated method on an object, injecting the parameters from the
	 * workbench contect or the given values.
	 * <p>
	 * If no matching method is found on the class, null will be returned.
	 * </p>
	 * 
	 * @param object
	 *            the object on which the method should be called
	 * @param qualifier
	 *            the annotation tagging method to be called
	 * @param parameters
	 *            that will be retrievable thanks to the annotation "Named"
	 * @return the return value of the method call, might be <code>null</code>
	 * @throws InjectionException
	 *             if an exception occurred while performing this operation
	 */
	public static Object invoke(Object object,
			Class<? extends Annotation> qualifier,
			Map<String, Object> stringParameters,
			Map<Class<?>, Object> classParameters) {
		IEclipseContext context = (IEclipseContext) PlatformUI.getWorkbench()
				.getService(IEclipseContext.class);
		return invoke(object, qualifier, context, stringParameters,
				classParameters);
	}

	/**
	 * Call the annotated method on an object, injecting the parameters from the
	 * context or the given values.
	 * <p>
	 * If no matching method is found on the class, null will be returned.
	 * </p>
	 * 
	 * @param object
	 *            the object on which the method should be called
	 * @param qualifier
	 *            the annotation tagging method to be called
	 * @param context
	 *            the eclipse context from which some of the values should be
	 *            returned.
	 * @param stringParameters
	 *            that will be retrievable thanks to the annotation "Named"
	 * @return the return value of the method call, might be <code>null</code>
	 * @throws InjectionException
	 *             if an exception occurred while performing this operation
	 */
	@SuppressWarnings("unchecked")
	public static Object invoke(Object object,
			Class<? extends Annotation> qualifier, IEclipseContext context,
			Map<String, Object> stringParameters,
			Map<Class<?>, Object> classParameters) {
		IEclipseContext temporaryContext = EclipseContextFactory.create();
		if (stringParameters != null) {
			for (Entry<String, Object> entry : stringParameters.entrySet()) {
				temporaryContext.set(entry.getKey(), entry.getValue());
			}
		}
		if (classParameters != null) {
			for (Entry<Class<?>, Object> entry : classParameters.entrySet()) {
				@SuppressWarnings("rawtypes")
				Class key = entry.getKey();
				Object value = entry.getValue();
				if (key.isInstance(value)) {
					temporaryContext.set(key, value);
				}
			}
		}
		IInjector defaultInjector = InjectorFactory.getDefault();
		return defaultInjector.invoke(object, qualifier, null,
				ContextObjectSupplier.getObjectSupplier(context,
						defaultInjector), ContextObjectSupplier
						.getObjectSupplier(temporaryContext, defaultInjector));
	}

	/**
	 * Creates an object from a bundleclass uri, and injects it with values
	 * present in the given context.
	 * <p>
	 * The format of the uriString should be
	 * "bundleclass://plugin_id/class_qualified_name".
	 * </p>
	 */
	public static Object createInstance(String uriString,
			IEclipseContext context) {
		IContributionFactory contributionFactory = context
				.get(IContributionFactory.class);
		return contributionFactory.create(uriString, context);
	}

	/**
	 * Creates an object from a bundleclass uri, and injects it with values
	 * present in the workbench context.
	 * <p>
	 * The format of the uriString should be
	 * "bundleclass://plugin_id/class_qualified_name".
	 * </p>
	 */
	public static Object createInstance(String uriString) {
		IEclipseContext context = (IEclipseContext) PlatformUI.getWorkbench()
				.getService(IEclipseContext.class);
		return createInstance(uriString, context);
	}

	public static void inject(Object... objs) {
		for (Object o : objs) {
			doInject(o);
		}
	}

}
