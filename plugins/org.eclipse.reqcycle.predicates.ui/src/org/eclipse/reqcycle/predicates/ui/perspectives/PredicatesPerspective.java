package org.eclipse.reqcycle.predicates.ui.perspectives;

import org.eclipse.ui.IFolderLayout;
import org.eclipse.ui.IPageLayout;
import org.eclipse.ui.IPerspectiveFactory;

public class PredicatesPerspective implements IPerspectiveFactory {

    public static final String PERSPECTIVE_ID = "org.eclipse.reqcycle.predicates.ui.perspectives.PredicatesPerspective"; //$NON-NLS-1$

    public void createInitialLayout(IPageLayout layout) {
        final String editorArea = layout.getEditorArea();
        layout.setEditorAreaVisible(true);
        this.addViews(layout, editorArea);
        this.addFastViews(layout);
        this.addViewShortcuts(layout);
        this.addPerspectiveShortcuts(layout);
    }

    private void addViews(IPageLayout layout, final String refId) {
        {
            final IFolderLayout rightFolder = layout.createFolder("right", IPageLayout.RIGHT, 0.75f, refId);
            rightFolder.addView(IPageLayout.ID_OUTLINE);
        }
        {
            final IFolderLayout bottomFolder = layout.createFolder("bottom", IPageLayout.BOTTOM, 0.75f, refId);
            bottomFolder.addView(IPageLayout.ID_PROBLEM_VIEW);
            bottomFolder.addView(IPageLayout.ID_TASK_LIST);
            bottomFolder.addView(IPageLayout.ID_PROP_SHEET);
        }
    }

    /**
     * Add fast views to the perspective.
     */
    private void addFastViews(IPageLayout layout) {
    }

    /**
     * Add view shortcuts to the perspective.
     */
    private void addViewShortcuts(IPageLayout layout) {
    }

    /**
     * Add perspective shortcuts to the perspective.
     */
    private void addPerspectiveShortcuts(IPageLayout layout) {
    }

}
