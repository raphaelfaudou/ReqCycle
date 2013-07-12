package org.eclipse.reqcycle.ui.datepropseditor;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

public class DatePropsEditorPlugin implements BundleActivator {

    private static BundleContext context;

    static BundleContext getContext() {
        return context;
    }

    public void start(BundleContext bundleContext) throws Exception {
        DatePropsEditorPlugin.context = bundleContext;
    }

    public void stop(BundleContext bundleContext) throws Exception {
        DatePropsEditorPlugin.context = null;
    }

}
