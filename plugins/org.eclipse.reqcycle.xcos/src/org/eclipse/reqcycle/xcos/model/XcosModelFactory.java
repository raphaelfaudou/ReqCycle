package org.eclipse.reqcycle.xcos.model;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.core.resources.IResource;

public class XcosModelFactory {

	private static Map<IResource, XcosModel> listModels = new HashMap<IResource, XcosModel>();
	
	public static XcosModel getModel(IResource res) {
		if (listModels.containsKey(res)) {
			return (XcosModel) listModels.get(res);
		}
		else {
			XcosModel model = new XcosModel(res.getName(), res);
			listModels.put (res, model);
			return model;
		}
			
	}

}
