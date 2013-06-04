package org.eclipse.reqcycle.traceability.cache.hypergraph.utils;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeSet;

import org.eclipse.reqcycle.uri.model.Reachable;
import org.hypergraphdb.HGHandle;
import org.hypergraphdb.HGPersistentHandle;
import org.hypergraphdb.HGQuery.hg;
import org.hypergraphdb.HGSearchResult;
import org.hypergraphdb.HGValueLink;
import org.hypergraphdb.HyperGraph;
import org.hypergraphdb.algorithms.GraphClassics;
import org.hypergraphdb.algorithms.HGALGenerator;
import org.hypergraphdb.atom.HGAtomSet;
import org.hypergraphdb.handle.HGLiveHandle;
import org.hypergraphdb.util.Mapping;
import org.hypergraphdb.util.Pair;

import com.google.common.base.Predicate;

public class GraphUtils {

	public static void insertTraceability(HyperGraph graph, Reachable source,
			Reachable target, String traceability) {
		// Activator.log (String.format("Traceability %s -> %s [%s]", source,
		// target, traceability));
		HGHandle handleSource = hg.assertAtom(graph, source);
		HGHandle handleTarget = hg.assertAtom(graph, target);
		HGValueLink link = new HGValueLink(traceability, handleSource,
				handleTarget);
		graph.add(link);
	}

	public static void insertRequirement(HyperGraph graph, Reachable req) {
		// Activator.log (String.format("Requirement %s", req));
		hg.assertAtom(graph, req);
	}

	/**
	 * copy of {@link GraphClassics}
	 * 
	 * @param start
	 * @param stopPredicate
	 * @param adjacencyGenerator
	 * @param weight
	 * @param distanceMatrix
	 * @param predecessorMatrix
	 * @return
	 */
	public static HGHandle dijkstra(final HGHandle start,
			final Predicate<HGHandle> stopPredicate,
			final HGALGenerator adjacencyGenerator,
			Map<HGHandle, Pair<HGHandle, HGHandle>> predecessorMatrix,
			Mapping<HGHandle, Double> weight,
			Map<HGHandle, Double> distanceMatrix) {
		final Map<HGHandle, Double> dm = distanceMatrix == null ? new HashMap<HGHandle, Double>()
				: distanceMatrix;
		dm.put(start, 0.0);
		Comparator<HGHandle> comp = new Comparator<HGHandle>() {
			private int compareHandles(HGHandle left, HGHandle right) {
				HGPersistentHandle x = left instanceof HGPersistentHandle ? (HGPersistentHandle) left
						: ((HGLiveHandle) left).getPersistent();
				HGPersistentHandle y = right instanceof HGPersistentHandle ? (HGPersistentHandle) right
						: ((HGLiveHandle) right).getPersistent();
				return x.compareTo(y);
			}

			public int compare(HGHandle left, HGHandle right) {
				Double l = dm.get(left);
				Double r = dm.get(right);
				if (l == null)
					if (r == null)
						return compareHandles(left, right);
					else
						return 1;
				else if (r == null)
					return -1;
				else {
					int c = l.compareTo(r);
					if (c == 0)
						c = compareHandles(left, right);
					return c;
				}
			}
		};
		if (weight == null)
			weight = new Mapping<HGHandle, Double>() {
				public Double eval(HGHandle link) {
					return 1.0;
				}
			};

		HGAtomSet settled = new HGAtomSet();
		TreeSet<HGHandle> unsettled = new TreeSet<HGHandle>(comp);
		unsettled.add(start);
		while (!unsettled.isEmpty()) {
			HGHandle a = unsettled.first();
			unsettled.remove(a);
			if (stopPredicate.apply(a))
				return a;
			settled.add(a);
			HGSearchResult<Pair<HGHandle, HGHandle>> neighbors = adjacencyGenerator
					.generate(a);
			double weightCurrent = dm.get(a).doubleValue();
			while (neighbors.hasNext()) {
				Pair<HGHandle, HGHandle> n = neighbors.next();
				if (settled.contains(n.getSecond()))
					continue;
				Double weightN = dm.get(n.getSecond());
				Double weightAN = weight.eval(n.getFirst());
				if (weightN == null) {
					dm.put(n.getSecond(), weightCurrent + weightAN);
					unsettled.add(n.getSecond());
					if (predecessorMatrix != null) {
						predecessorMatrix.put(n.getSecond(),
								new Pair<HGHandle, HGHandle>(n.getFirst(), a));
					}
				} else if (weightN > weightCurrent + weightAN) {
					// new distance found for n, re-insert at appropriate
					// position
					unsettled.remove(n.getSecond());
					dm.put(n.getSecond(), weightCurrent + weightAN);
					unsettled.add(n.getSecond());
					if (predecessorMatrix != null) {
						predecessorMatrix.put(n.getSecond(),
								new Pair<HGHandle, HGHandle>(n.getFirst(), a));
					}
				}
			}
			neighbors.close();
		}
		return null;
	}

	public static HGHandle dijkstra(final HGHandle start, final HGHandle goal,
			final HGALGenerator adjacencyGenerator,
			Mapping<HGHandle, Double> weight,
			Map<HGHandle, Double> distanceMatrix,
			Map<HGHandle, HGHandle> predecessorMatrix) {
		return dijkstra(start, new Predicate<HGHandle>() {

			@Override
			public boolean apply(HGHandle arg0) {
				return goal.equals(arg0);
			}

		}, adjacencyGenerator, new DelegatedMap(predecessorMatrix), weight,
				distanceMatrix);
	}

	private static class DelegatedMap extends
			HashMap<HGHandle, Pair<HGHandle, HGHandle>> {
		private Map<HGHandle, HGHandle> delegate;

		public DelegatedMap(Map<HGHandle, HGHandle> delegate) {
			this.delegate = delegate;
		}

		@Override
		public Pair<HGHandle, HGHandle> put(HGHandle arg0,
				Pair<HGHandle, HGHandle> arg1) {
			Pair<HGHandle, HGHandle> result = super.put(arg0, arg1);
			delegate.put(arg0, arg1.getSecond());
			return result;
		}

	}

}
