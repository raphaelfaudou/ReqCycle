package org.eclipse.reqcycle.traceability.types.configuration.preferences;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerFilter;
import org.eclipse.reqcycle.traceability.types.configuration.typeconfiguration.Configuration;
import org.eclipse.reqcycle.traceability.types.configuration.typeconfiguration.Type;
import org.eclipse.reqcycle.traceability.types.configuration.typeconfiguration.TypeConfigContainer;

public class TraceabilityTypePreferencePage extends AbstractPreferencePage {

	@Override
	protected ViewerFilter getFilter() {
		return new ViewerFilter() {

			@Override
			public boolean select(Viewer viewer, Object parentElement,
					Object element) {
				return !(element instanceof Type);
			}
		};
	}

	@Override
	protected boolean removeCondition(EObject firstElement) {
		return !(firstElement instanceof TypeConfigContainer)
				&& firstElement != null;
	}

	@Override
	protected void addAction() {
		if (treeViewer.getSelection() instanceof IStructuredSelection) {
			IStructuredSelection selec = (IStructuredSelection) treeViewer
					.getSelection();
			Object firstElement = selec.getFirstElement();
			if (firstElement == null) {
				createConfiguration();
			} else if (firstElement instanceof TypeConfigContainer) {
				createConfiguration();
			} else if (firstElement instanceof Configuration) {
				createRelation((Configuration) firstElement);
			}
		} else {
			createConfiguration();
		}

	}

	@Override
	protected void removeAction() {
		if (treeViewer.getSelection() instanceof IStructuredSelection) {
			IStructuredSelection new_name = (IStructuredSelection) treeViewer
					.getSelection();
			EObject firstElement = (EObject) new_name.getFirstElement();
			if (removeCondition(firstElement)) {
				EcoreUtil.delete(firstElement, true);
			}
		}
	}

}
