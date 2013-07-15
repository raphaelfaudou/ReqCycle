package org.eclipse.reqcycle.ui.components;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

public class ReqCycleUIComponentsPlugin implements BundleActivator {

    private static BundleContext context;

    static BundleContext getContext() {
        return context;
    }

    public void start(BundleContext bundleContext) throws Exception {
        ReqCycleUIComponentsPlugin.context = bundleContext;
    }

    public void stop(BundleContext bundleContext) throws Exception {
        ReqCycleUIComponentsPlugin.context = null;
    }

}
