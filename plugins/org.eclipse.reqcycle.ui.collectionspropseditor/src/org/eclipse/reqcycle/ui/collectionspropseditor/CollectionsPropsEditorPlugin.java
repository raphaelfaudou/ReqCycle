package org.eclipse.reqcycle.ui.collectionspropseditor;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

public class CollectionsPropsEditorPlugin implements BundleActivator {

    private static BundleContext context;

    static BundleContext getContext() {
        return context;
    }

    public void start(BundleContext bundleContext) throws Exception {
        CollectionsPropsEditorPlugin.context = bundleContext;
    }

    public void stop(BundleContext bundleContext) throws Exception {
        CollectionsPropsEditorPlugin.context = null;
    }

}
