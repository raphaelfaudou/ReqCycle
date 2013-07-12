package org.eclipse.reqcycle.ui.eenumpropseditor;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

public class EEnumPropsEditorPlugin implements BundleActivator {

    private static BundleContext context;

    static BundleContext getContext() {
        return context;
    }

    public void start(BundleContext bundleContext) throws Exception {
        EEnumPropsEditorPlugin.context = bundleContext;
    }

    public void stop(BundleContext bundleContext) throws Exception {
        EEnumPropsEditorPlugin.context = null;
    }

}
