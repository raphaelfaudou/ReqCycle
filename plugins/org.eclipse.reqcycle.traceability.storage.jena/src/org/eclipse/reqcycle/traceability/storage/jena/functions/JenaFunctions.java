package org.eclipse.reqcycle.traceability.storage.jena.functions;

import org.eclipse.reqcycle.traceability.model.Link;
import org.eclipse.reqcycle.traceability.model.Pair;
import org.eclipse.reqcycle.traceability.storage.jena.functions.Statement2Pair.DownwardStatement2Pair;
import org.eclipse.reqcycle.traceability.storage.jena.functions.Statement2Pair.UpwardStatement2Pair;
import org.eclipse.reqcycle.uri.model.Reachable;
import org.eclipse.ziggurat.inject.ZigguratInject;

import com.google.common.base.Function;
import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.Property;
import com.hp.hpl.jena.rdf.model.Resource;
import com.hp.hpl.jena.rdf.model.Statement;

public class JenaFunctions {
	private static Resource2Reachable resource2Reachable;
	private static DownwardStatement2Pair downwardStatement2Pair;
	private static UpwardStatement2Pair upwardStatement2Pair;
	static {
		resource2Reachable = new Resource2Reachable();
		downwardStatement2Pair = new DownwardStatement2Pair();
		upwardStatement2Pair = new UpwardStatement2Pair();
		ZigguratInject.inject(resource2Reachable, downwardStatement2Pair,
				upwardStatement2Pair);
	}

	public static Function<Statement, Pair<Link, Reachable>> newStatement2Pair(
			Property nodeToSearch) {
		Statement2Pair result = new Statement2Pair(nodeToSearch);
		ZigguratInject.inject(result);
		return result;
	}

	public static Function<Statement, Pair<Link, Reachable>> newDownwardStatement2Pair() {
		return downwardStatement2Pair;
	}

	public static Function<Statement, Pair<Link, Reachable>> newUpwardStatement2Pair() {
		return upwardStatement2Pair;
	}

	public static Function<String, Reachable> newString2Reachable(Model m) {
		String2Reachable result = new String2Reachable(m);
		ZigguratInject.inject(result);
		return result;
	}

	public static Function<Resource, Reachable> newResource2Reachable() {
		return resource2Reachable;
	}
}
