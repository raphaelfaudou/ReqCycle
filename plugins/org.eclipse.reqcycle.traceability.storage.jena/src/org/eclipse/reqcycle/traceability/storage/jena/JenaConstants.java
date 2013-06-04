package org.eclipse.reqcycle.traceability.storage.jena;

public interface JenaConstants {
	// NAMESPACES
	public static String REQCYCLE_NS = "http://www.eclipse.org/reqcycle/";

	// TYPES
	public static String TYPE_REACHABLE = REQCYCLE_NS + "Reachable";
	public static String TYPE_PROPERTY = REQCYCLE_NS + "Property";
	public static String TYPE_TRACE = REQCYCLE_NS + "Traceability";

	// SEMANTIC
	public static String SEM_CONTAINER = REQCYCLE_NS + "Container";
	public static String SEM_CHILDREN = REQCYCLE_NS + "Children";
	public static String SEM_PROPERTIES = TYPE_PROPERTY + "/Properties";
	public static String SEM_PROPERTIES_KEY = SEM_PROPERTIES + "/Key";
	public static String SEM_PROPERTIES_VALUE = SEM_PROPERTIES + "/Value";
	public static String SEM_REVISION = REQCYCLE_NS + "Revision";

	// TRACEABILITY
	public static String TRAC_SOURCE = TYPE_TRACE + "/Source";
	public static String TRAC_TARGET = TYPE_TRACE + "/Target";
	public static String TRAC_INCOMING = TYPE_TRACE + "/Incoming";
	public static String TRAC_OUTGOING = TYPE_TRACE + "/Outgoing";

	public static String TRAC_KIND = TYPE_TRACE + "/Kind";
}
