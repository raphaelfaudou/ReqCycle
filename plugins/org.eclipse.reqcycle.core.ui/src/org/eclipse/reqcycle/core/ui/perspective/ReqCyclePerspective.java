package org.eclipse.reqcycle.core.ui.perspective;

import org.eclipse.ui.IFolderLayout;
import org.eclipse.ui.IPageLayout;
import org.eclipse.ui.IPerspectiveFactory;


public class ReqCyclePerspective implements IPerspectiveFactory {

	/**
	 * Creates the initial layout for a page.
	 */
	public void createInitialLayout(IPageLayout layout) {
		//FIXME : Check that the views exist before adding them 
		String editorArea = layout.getEditorArea();
		addFastViews(layout);
		addViewShortcuts(layout);
		addPerspectiveShortcuts(layout);
		{
			IFolderLayout folderLayout = layout.createFolder("folder", IPageLayout.LEFT, 0.5f, IPageLayout.ID_EDITOR_AREA);
			folderLayout.addView("org.eclipse.jdt.ui.PackageExplorer");
			
			folderLayout.addView("org.eclipse.reqcycle.repository.ui.views.requirements");
		}
		{
			IFolderLayout folderLayout = layout.createFolder("folder_2", IPageLayout.RIGHT, 0.5f, IPageLayout.ID_EDITOR_AREA);
			folderLayout.addView("org.eclipse.reqcycle.traceability.ui.views.TraceabilityViewer");
			folderLayout.addView("org.eclipse.reqcycle.traceability.table.partdescriptor.traceability.table");
		}
		{
			IFolderLayout folderLayout = layout.createFolder("folder_1", IPageLayout.BOTTOM, 0.5f, "folder");
			folderLayout.addView("org.eclipse.reqcycle.repository.ui.views.sources");
			folderLayout.addView("org.eclipse.papyrus.views.modelexplorer.modelexplorer");
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
