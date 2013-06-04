package org.eclipse.reqcycle.traceability.ui.providers;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Set;

import javax.inject.Inject;

import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.reqcycle.traceability.builder.IBuildingTraceabilityEngine;
import org.eclipse.reqcycle.traceability.engine.ITraceabilityEngine;
import org.eclipse.reqcycle.traceability.engine.Request;
import org.eclipse.reqcycle.traceability.engine.Request.Couple;
import org.eclipse.reqcycle.traceability.engine.Request.DEPTH;
import org.eclipse.reqcycle.traceability.exceptions.EngineException;
import org.eclipse.reqcycle.traceability.model.Link;
import org.eclipse.reqcycle.traceability.model.Pair;
import org.eclipse.reqcycle.traceability.types.configuration.typeconfiguration.Configuration;
import org.eclipse.reqcycle.traceability.types.engine.ITypedTraceabilityEngine;
import org.eclipse.reqcycle.traceability.utils.EngineUtils;
import org.eclipse.reqcycle.uri.IReachableListener;
import org.eclipse.reqcycle.uri.IReachableListenerManager;
import org.eclipse.reqcycle.uri.model.Reachable;
import org.eclipse.swt.widgets.Display;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Lists;
import com.google.common.collect.Multimap;

public class RequestContentProvider implements ITreeContentProvider,
		IReachableListener {

	public static final String CONF_KEY = "conf";
	@Inject
	ITraceabilityEngine defaultEngine;
	@Inject
	ITypedTraceabilityEngine typedEngine;
	@Inject
	IReachableListenerManager listenerManger;
	Multimap<Reachable, Link> links = ArrayListMultimap.create();
	private Collection<Request> requests = new LinkedList<Request>();
	private TreeViewer viewer;
	private Object newInput;
	private Request baseRequest = null;

	@Override
	public void dispose() {
		listenerManger.removeReachableListener(this);
	}

	@Override
	public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
		this.viewer = (TreeViewer) viewer;
		this.newInput = newInput;
		listenerManger.removeReachableListener(this);
	}

	@Override
	public Object[] getElements(Object inputElement) {
		if (newInput instanceof Request) {
			links.clear();
			requests.clear();
			baseRequest = (Request) newInput;
			requests.add(baseRequest);
		} else if (newInput instanceof Collection) {
			links.clear();
			requests.clear();
			Collection<? extends Request> newInput2 = (Collection<? extends Request>) newInput;
			if (!newInput2.isEmpty()) {
				requests.addAll(newInput2);
				baseRequest = requests.iterator().next();
			}
		}
		Collection<Object> result = new LinkedList<Object>();
		for (Request r : requests) {
			for (Couple c : r.getCouples()) {
				Reachable source = c.getSource();
				listenerManger.addReachableListener(source, this);
				listenerManger
						.addReachableListener(source.trimFragment(), this);
				result.add(source);
			}
		}
		return result.toArray();
	}

	@Override
	public Object[] getChildren(Object parentElement) {
		Collection<Object> result = new LinkedList<Object>();
		if (parentElement instanceof Reachable) {
			Reachable reachable = (Reachable) parentElement;
			Collection<Link> c = links.get(reachable);
			if (c.isEmpty()) {
				Request r = new Request()
						.addProperty(
								IBuildingTraceabilityEngine.OPTION_CHECK_CACHE,
								false)

						.setDepth(DEPTH.ONE)
						.setDirection(baseRequest.getDirection())
						.setScope(baseRequest.getScope())
						.addProperty(CONF_KEY,
								baseRequest.getProperty(CONF_KEY));
				ArrayList<Couple> listOfCouples = Lists
						.newArrayList(baseRequest.getCouples());
				if (listOfCouples.size() == 1) {
					Couple couple = listOfCouples.get(0);
					r.addSourceAndCondition(reachable,
							couple.getStopCondition());
					if (couple.getStopCondition() != null) {
						r.setDepth(DEPTH.INFINITE);
					}

				} else {
					r.addSource(reachable);
				}
				try {
					Iterator<Pair<Link, Reachable>> traceIterator = getTraceability(r);
					links.putAll(EngineUtils.toFollowingMap(traceIterator));
					c = links.get(reachable);
				} catch (EngineException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			result.addAll(c);
		} else if (parentElement instanceof Link) {
			// if (request.getDirection() == DIRECTION.UPWARD) {
			Set<Reachable> targets = ((Link) parentElement).getTargets();
			for (Reachable r : targets) {
				listenerManger.addReachableListener(r, this);
				listenerManger.addReachableListener(r.trimFragment(), this);
				result.add(r);
			}
			// } else {
			// result.addAll(((Link) parentElement).getSources());
			// }
		}
		return result.toArray();
	}

	private Iterator<Pair<Link, Reachable>> getTraceability(Request r)
			throws EngineException {
		Object conf = r.getProperty(CONF_KEY);
		if (conf instanceof Configuration) {
			return typedEngine.getTraceability((Configuration) conf, r);
		} else {
			return defaultEngine.getTraceability(r);
		}
	}

	@Override
	public Object getParent(Object element) {
		return null;
	}

	@Override
	public boolean hasChildren(Object element) {
		return true;
	}

	@Override
	public void hasChanged(final Reachable reachable) {
		Display.getDefault().syncExec(new Runnable() {

			@Override
			public void run() {
				// to avoid infinite reflexive call
				if (viewer != null) {
					Object[] objects = viewer.getExpandedElements();
					viewer.refresh();
					viewer.setExpandedElements(objects);
				}
			}
		});
	}
}
