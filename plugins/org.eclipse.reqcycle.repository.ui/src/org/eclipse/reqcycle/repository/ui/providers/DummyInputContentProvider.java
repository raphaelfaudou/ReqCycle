package org.eclipse.reqcycle.repository.ui.providers;

import java.util.Collection;
import java.util.Iterator;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.edit.provider.ComposedAdapterFactory;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryContentProvider;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.reqcycle.predicates.core.api.IPredicate;

import DataModel.RequirementSource;

import com.google.common.base.Function;
import com.google.common.base.Predicates;
import com.google.common.collect.Collections2;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;

public class DummyInputContentProvider extends AdapterFactoryContentProvider {

    public DummyInputContentProvider(ComposedAdapterFactory adapterFactory) {
        super(adapterFactory);
    }

    @Override
    public Object[] getElements(Object inputElement) {
        if (inputElement instanceof Collection) {
            Iterator<?> iter = ((Collection<?>) inputElement).iterator();
            if (iter.hasNext()) {
                Object obj = iter.next();
                if (obj instanceof DummyInput) {
                    return ArrayContentProvider.getInstance().getElements(inputElement);
                } else {
                    return super.getElements(inputElement);
                }
            }
        }
        return super.getElements(inputElement);
    }

    @Override
    public Object[] getChildren(final Object object) {
        if (object instanceof DummyInput) {
            Object[] children = Collections2.transform(((DummyInput) object).getInput(),
                    new Function<RequirementSource, DummyObject>() {
                        public DummyObject apply(RequirementSource reqSource) {
                            return new DummyObject(((DummyInput) object).getPredicate(), reqSource);
                        };
                    }).toArray();
            return children;
        }
        if (object instanceof DummyObject) {
            final DummyObject dummyObject = (DummyObject) object;
            Collection<Object> defaultChildren = Lists.newArrayList(super.getChildren(dummyObject.getEobj()));
            Collection<EObject> transformedChildren = Collections2.transform(defaultChildren,
                    new Function<Object, EObject>() {
                        @Override
                        public EObject apply(Object from) {
                            return (EObject) from;
                        }
                    });
            Iterable<DummyObject> result = Iterables.filter(
                    Collections2.transform(transformedChildren, new Function<EObject, DummyObject>() {
                        @Override
                        public DummyObject apply(EObject eObj) {
                            DummyObject dObj = new DummyObject(dummyObject.getPredicate(), eObj);
                            return dObj.getPredicate().match(eObj) ? dObj : null;
                        }
                    }), Predicates.notNull());
            return Iterables.toArray(result, DummyObject.class);
        }
        return super.getChildren(object);
    }

    @Override
    public Object getParent(Object object) {
        if (object instanceof DummyInput) {
            return null;
        } else if (object instanceof DummyObject) {
            return super.getParent(((DummyObject) object).getEobj());
        }
        return super.getParent(object);
    }

    @Override
    public boolean hasChildren(Object object) {
        if (object instanceof DummyInput) {
            return true;
        } else if (object instanceof DummyObject) {
            return super.hasChildren(((DummyObject) object).getEobj());
        }
        return super.hasChildren(object);
    }

    public static class DummyInput {

        private final Collection<RequirementSource> input;
        private IPredicate                          predicate;

        public DummyInput(Collection<RequirementSource> input) {
            this.input = input;
        }

        public Collection<RequirementSource> getInput() {
            return this.input;
        }

        public IPredicate getPredicate() {
            return predicate;
        }

        public void setPredicate(IPredicate predicate) {
            this.predicate = predicate;
        }

        public String getDisplayName() {
            return predicate == null ? null : predicate.getDisplayName();
        }
    }

    public class DummyObject {

        private IPredicate predicate;
        private EObject    eobj;

        public EObject getEobj() {
            return this.eobj;
        }

        public void setEobj(EObject eobj) {
            this.eobj = eobj;
        }

        public DummyObject(IPredicate predicate, EObject eobj) {
            this.predicate = predicate;
            this.eobj = eobj;
        }

        public IPredicate getPredicate() {
            return predicate;
        }
    }

}
