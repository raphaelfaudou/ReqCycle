package org.eclipse.reqcycle.xcos.model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

import javax.xml.namespace.QName;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMResult;
import javax.xml.transform.stream.StreamSource;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.eclipse.core.internal.resources.Resource;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.Path;
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class XcosModel extends XcosElement {
	
	//private static DocumentBuilder builder = initializeDocBuilder();
	
	private List<XcosBlock> blocks = new ArrayList<XcosBlock>();
	private List<XcosTrace> traces = new ArrayList<XcosTrace>();
	


	public XcosModel(String aName, IResource res) {
		super(aName, res);
		
		if (res instanceof IFile) {
			parseResourceFrom((IFile) res);
			
		} 
		
	}
	
	public static void main(String[] args) {
		
		//String location = "/Users/faudouraphael/git/ReqCycle/plugins/org.eclipse.reqcycle.xcos/src/org/eclipse/reqcycle/xcos/model/AllSum.xcos";

		File file = new File(args[0]);
		IFile f = ResourcesPlugin.getWorkspace().getRoot().getFile(new Path(file.getPath()));
		
		XcosModel m = new XcosModel("test", f);
	}
	
	private void parseResourceFrom(IFile file) {
		
		final TransformerFactory tranFactory = TransformerFactory.newInstance();
		Transformer aTransformer;
		InputStream in = null;
		try {
			aTransformer = tranFactory.newTransformer();
			
		
			try {
				in = file.getContents();
			} catch (CoreException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			final StreamSource src = new StreamSource(in);
			final DOMResult result = new DOMResult();

			
			aTransformer.transform(src, result);
			
			final Document document = (Document) result.getNode();
			
			XPath xPath =  XPathFactory.newInstance().newXPath();
			 
			String exp = "//BasicBlock";
			
			XPathExpression xpathExp;
	
			xpathExp = xPath.compile(exp);
			NodeList list = (NodeList) xpathExp.evaluate(document, XPathConstants.NODESET);
			
			for(int i=0; i<list.getLength();i++){
                Node n = list.item(i);
            	
                final Node id = n.getAttributes().getNamedItem("id") ;
                String blocLabel = n.getAttributes().getNamedItem("interfaceFunctionName").getTextContent();
                XcosBlock block = new XcosBlock(blocLabel,file);
                
                if (n.hasChildNodes()) {
                	NodeList children = n.getChildNodes();
                	for(int j=0; j<children.getLength();j++){
                		Node child = children.item(j);
                		// looking for TraceExtRef element
                		if ("TraceExtRef".equals(child.getNodeName())) {
                			NamedNodeMap atts = child.getAttributes();
                			String traceType = atts.getNamedItem("traceType").getTextContent();
                			String extRef = atts.getNamedItem("extRef").getTextContent();
                			
                			XcosTrace trace = new XcosTrace(traceType,block,extRef,traceType,file);
                			blocks.add(block);
                			traces.add(trace);
                			
                		}//if
                	} // for
                } //if
            }  //for 
                

			
		} catch (XPathExpressionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		
			
		} catch (TransformerConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		
		} catch (TransformerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		
		} finally {
			try {
				in.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    }
		
		
	}


	
	public List<XcosBlock> getBlocks() {
		return blocks;
	}
	
	public List<XcosTrace> getTraces() {
		return traces;
	}

}
