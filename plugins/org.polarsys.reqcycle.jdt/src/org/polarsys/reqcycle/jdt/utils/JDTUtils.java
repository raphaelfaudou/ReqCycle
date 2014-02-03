package org.polarsys.reqcycle.jdt.utils;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.LinkedList;
import java.util.List;

import org.eclipse.core.resources.IFile;
import org.eclipse.jdt.core.IJavaElement;
import org.eclipse.jdt.internal.core.CompilationUnit;
import org.polarsys.reqcycle.jdt.model.JDTReachableObject;
import org.polarsys.reqcycle.uri.IReachableCreator;
import org.polarsys.reqcycle.uri.model.ReachableObject;
import org.eclipse.ziggurat.inject.ZigguratInject;

import com.google.common.base.Joiner;

public class JDTUtils {
	public static final String PLATFORM = "platform:";
	public static final String SEPARATOR = "::";
	private static IReachableCreator creator = ZigguratInject
			.make(IReachableCreator.class);

	public static ReachableObject getReachable(IFile cu) {
		if ("java".equalsIgnoreCase(cu.getFileExtension())) {
			try {
				JDTReachableObject jdtReachableObject = new JDTReachableObject(
						creator.getReachable(new URI(PLATFORM
								+ cu.getFullPath().toString()), cu));
				ZigguratInject.inject(jdtReachableObject);
				return jdtReachableObject;
			} catch (URISyntaxException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	public static ReachableObject getReachable(IJavaElement cu) {
		URI uri;
		try {
			uri = new URI(getQualifiedURI(cu));
			JDTReachableObject object = new JDTReachableObject(
					creator.getReachable(uri, cu));
			ZigguratInject.inject(object);
			return object;
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	protected static String getQualifiedURI(IJavaElement element) {
		StringBuilder result = new StringBuilder();
		result.append(PLATFORM)
				.append(element.getResource().getFullPath().toString())
				.append("#");
		List<String> names = new LinkedList<String>();
		names.add(element.getElementName());
		IJavaElement parent = element.getParent();
		while (parent != null && !(parent instanceof CompilationUnit)) {
			names.add(0, parent.getElementName());
			parent = parent.getParent();
		}
		result.append(Joiner.on(SEPARATOR).join(names));
		return result.toString();
	}
}
