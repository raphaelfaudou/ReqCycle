package org.eclipse.reqcycle.repository.ui.providers;

import java.util.Collection;
import java.util.Iterator;

import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.Viewer;

import DataModel.RequirementSource;

public class ComposedRequirementContentProvider implements ITreeContentProvider {

    private final RequirementContentProvider reqContentProvider;

    public ComposedRequirementContentProvider(RequirementContentProvider reqContentProvider) {
        this.reqContentProvider = reqContentProvider;
    }

    @Override
    public void dispose() {
    }

    @Override
    public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
    }

    @SuppressWarnings("unchecked")
    @Override
    public Object[] getElements(Object inputElement) {
        if (inputElement instanceof Collection) {
            Iterator<?> iter = ((Collection<?>) inputElement).iterator();
            if (iter.hasNext()) {
                Object obj = iter.next();
                if (obj instanceof DummyInput) {
                    return ((Collection<DummyInput>) inputElement).toArray();
                } else {
                    return this.reqContentProvider.getElements(inputElement);
                }
            }
        }
        return this.reqContentProvider.getElements(inputElement);
    }

    @Override
    public Object[] getChildren(Object parentElement) {
        if (parentElement instanceof DummyInput) {
            return ((DummyInput) parentElement).getInput().toArray();
        }
        return this.reqContentProvider.getChildren(parentElement);
    }

    @Override
    public Object getParent(Object element) {
        if (element instanceof DummyInput) {
            return null;
        }
        return this.reqContentProvider.getParent(element);
    }

    @Override
    public boolean hasChildren(Object element) {
        if (element instanceof DummyInput) {
            return true;
        }
        return this.reqContentProvider.hasChildren(element);
    }

    public static class DummyInput {

        private final Collection<RequirementSource> input;

        private String                              name;

        public DummyInput(Collection<RequirementSource> input) {
            this.input = input;
        }

        public Collection<RequirementSource> getInput() {
            return this.input;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

}
