package org.eclipse.reqcycle.uri.model;

import java.io.Serializable;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.eclipse.reqcycle.uri.IReachableCreator;

public final class Reachable implements Serializable{
	private static final long serialVersionUID = -2197337862552450878L;
	private String scheme;
	private String path;
	private String fragment;
	private String schemeSpecificPart;
	private String authority;
	private String userInfo;
	private String host;
	private String query;
	private int port;
	private Map<String, String> properties = new HashMap<String, String>();

	/**
	 * This class is not intended to not be instantiate please use an instanceof
	 * {@link IReachableCreator}
	 */
	public Reachable() {
	}

	public void setPort(int port) {
		this.port = port;
	}

	public String toString() {
		return getURI().toString();
	}

	public int getPort() {
		return port;
	}

	public URI getURI() {
		try {
			return new URI(scheme, userInfo, host, port, path, query, fragment);
		} catch (URISyntaxException e) {
			return null;
		}
	}

	public void setQuery(String query) {
		this.query = query;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public void setUserInfo(String userInfo) {
		this.userInfo = userInfo;
	}

	public void setAuthority(String authority) {
		this.authority = authority;
	}

	/**
	 * If this URI has a non-null {@link #fragment fragment}, returns the URI
	 * formed by removing it; this URI unchanged, otherwise.
	 */
	public Reachable trimFragment() {
		if (fragment == null) {
			return this;
		}
		Reachable result = new Reachable();
		result.setAuthority(authority);
		result.setFragment(null);
		result.setHost(host);
		result.setPath(path);
		result.setPort(port);
		result.setQuery(query);
		result.setScheme(scheme);
		result.setSchemeSpecificPart(schemeSpecificPart);
		result.setUserInfo(userInfo);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Reachable) {
			Reachable t = (Reachable) obj;
			return t.getURI().equals(this.getURI());
		}
		return super.equals(obj);
	}

	@Override
	public int hashCode() {
		return this.getURI().hashCode();
	}

	public void setSchemeSpecificPart(String schemeSpecificPart) {
		this.schemeSpecificPart = schemeSpecificPart;
	}

	public void setScheme(String scheme) {
		this.scheme = scheme;
	}

	public void setFragment(String fragment) {
		this.fragment = fragment;
	}

	public String getScheme() {
		return scheme;
	}

	public String getPath() {
		return path;
	}

	public String getFragment() {
		return fragment;
	}

	public String getSchemeSpecificPart() {
		return schemeSpecificPart;
	}

	public String getAuthority() {
		return authority;
	}

	public String getUserInfo() {
		return userInfo;
	}

	public String getHost() {
		return host;
	}

	public String getQuery() {
		return query;
	}

	public String get(String key) {
		return getProperties().get(key);
	}

	public void put(String key, String value) {
		getProperties().put(key, value);
	}

	public void putAll(Map<String, String> map) {
		getProperties().putAll(map);
	}

	public Set<String> extraKeys() {
		return getProperties().keySet();
	}

	public Map<String, String> getProperties() {
		return properties;
	}

	public void setProperties(Map<String, String> properties) {
		this.properties = properties;
	}

}