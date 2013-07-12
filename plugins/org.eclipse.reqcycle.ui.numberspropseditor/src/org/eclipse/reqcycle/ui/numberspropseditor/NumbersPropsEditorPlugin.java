package org.eclipse.reqcycle.ui.numberspropseditor;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

public class NumbersPropsEditorPlugin implements BundleActivator {

    private static BundleContext context;

    static BundleContext getContext() {
        return context;
    }

    public void start(BundleContext bundleContext) throws Exception {
        NumbersPropsEditorPlugin.context = bundleContext;
    }

    public void stop(BundleContext bundleContext) throws Exception {
        NumbersPropsEditorPlugin.context = null;
    }

}
