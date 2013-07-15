package org.eclipse.reqcycle.predicates.ui.util;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;

public class SWTUtil {

    private SWTUtil() {
    }

    public static void recursiveSetEnabled(Control control, boolean enabled) {
        if (control instanceof Composite) {
            for (final Control c : ((Composite) control).getChildren()) {
                recursiveSetEnabled(c, enabled);
            }
        } else {
            control.setEnabled(enabled);
        }
    }
}
