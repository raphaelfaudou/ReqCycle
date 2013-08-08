package org.eclipse.reqcycle.predicates.ui.util;

import java.util.ArrayList;
import java.util.Collection;

import org.eclipse.jface.window.Window;
import org.eclipse.reqcycle.predicates.core.api.IPredicate;
import org.eclipse.reqcycle.predicates.persistance.util.PredicatesConfManager;
import org.eclipse.reqcycle.predicates.ui.providers.PredicatesTableLabelProvider;
import org.eclipse.reqcycle.ui.components.dialogs.CheckBoxInputDialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ziggurat.inject.ZigguratInject;

/**
 * This class contains common UI utilities related to predicates such as for example : opening a dialog chooser in order
 * to select desired predicates.
 * 
 * @author Papa Issa DIAKHATE
 */
public class PredicatesUIHelper {

    private PredicatesUIHelper() {
    }

    /**
     * Opens a dialog which proposes to select a predicate to apply for filtering.
     * 
     * @return The selected predicates or an empty list if nothing is selected and null if cancelled.
     */
    public static Collection<IPredicate> openPredicatesChooser(Collection<IPredicate> init) {
        Display display = Display.getCurrent();
        if (display == null) display = Display.getDefault();
        
        PredicatesConfManager predicatesConfManager = new PredicatesConfManager();
        ZigguratInject.inject(predicatesConfManager);
        
        Collection<IPredicate> storedPredicates = predicatesConfManager.getStoredPredicates();
        final CheckBoxInputDialog dialog = new CheckBoxInputDialog(display.getActiveShell(), "Requirement filtering", //$NON-NLS-1$
                "Select a predicate to apply or press OK to continue without filtering.", storedPredicates, null, (Collection)init); //$NON-NLS-1$

        dialog.setLabelProvider(new PredicatesTableLabelProvider());
        if (Window.OK == dialog.open()) {
        	Collection<IPredicate> predicates = new ArrayList<IPredicate>();
            for (Object obj : dialog.getSelectedItems()) {
                if (obj instanceof IPredicate) {
                    predicates.add((IPredicate) obj);
                }
            }
            return predicates;
        }
        return null;
    }
}
