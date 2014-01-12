package org.eclipse.reqcycle.xcos.model;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class XcosModel extends XcosElement {
	
	private static DocumentBuilder builder = initializeDocBuilder();
	
	private List<XcosBlock> blocks = new ArrayList<XcosBlock>();
	private List<XcosTrace> traces = new ArrayList<XcosTrace>();
	


	public XcosModel(String aName, IResource res) {
		super(aName, res);
		System.out.println("creating Xcos Model for res " + res);
		
		if (res instanceof IFile) {
				String req ="platform:/resource/Test1/R.reqcycle#_ZlcYUm9EEeONE5235LORwQ";
			String req3="platform:/resource/Test1/R.reqcycle#_ZlcYUm9EEeONE5235LORwQ";
			String uml = "platform:/resource/Test1/model.uml#_nWhKwHo1EeO35oHDOqTskA";
			String uml2="platform:/resource/Test1/model.uml#_nWhKwHo1EeO35oHDOqTskA";
			String req2 = "platform:/resource/Test1/System.reqcycle#_h2rOtG9EEeONE5235LORwQ";
			//traces.add(new XcosTrace("COVERS",new XcosBlock(res.getName(),res),req,"COVERS",res));
			traces.add(new XcosTrace("COVERS",new XcosBlock(res.getName()+"A",res),req,"COVERS",res));
			traces.add(new XcosTrace("COVERS",new XcosBlock(res.getName()+"B",res),uml,"COVERS2",res));
			/*
			IFile file = (IFile) res;
			InputStream in = null;
			try {
				
			    		//ifile.getFullPath().toString();
				Document xmlDocument;
				XPath xPath =  XPathFactory.newInstance().newXPath();
				in = file.getContents(true);
				
				xmlDocument = builder.parse( in);
				System.out.println(" doc version" + xmlDocument.getXmlVersion());
				
				//FIXME
				//read model title
				String modelNameExpression = "/XcosDiagram[@title]";
						
				String title = xPath.compile(modelNameExpression).evaluate(xmlDocument);
				System.out.println(" diagram name " + title);
				 
				//read basic blocs
				String TraceExpression = "//TraceExtRef";
				NodeList nodeList = (NodeList) xPath.compile(TraceExpression).evaluate(xmlDocument, XPathConstants.NODESET);
			
				//FIXME parse file to retrieve Xcos blocs and traces
				for (int i = 0; i < nodeList.getLength(); i++) {
				    Node node = nodeList.item(i);
					System.out.println(node.getFirstChild().getNodeValue()); 
					if(null != node) {
					    NodeList attributeList = node.getChildNodes();
					    String traceType = "";
					    String extRef = "";
					    for (int k = 0;null!=attributeList && k < attributeList.getLength(); k++) {
					        Node att = attributeList.item(k);
					        if(att.getNodeType() == Node.ELEMENT_NODE) {
					            System.out.println(att.getNodeName() + " : " + att.getFirstChild().getNodeValue()); 
					            if ("type".equals(att.getNodeName())) 
					            		traceType = att.getFirstChild().getNodeValue();
					            if ("extRef".equals(att.getNodeName())) 
					            	extRef = att.getFirstChild().getNodeValue();
					        }
					    }
					    // creating trace
					    String parentElement = node.getParentNode().getNodeValue();
					    XcosElement source = new XcosElement(parentElement,res);
					    traces.add(new XcosTrace(traceType,source,extRef,traceType,res));
					} */
				    
					
				} 
				

				/*
				 XcosBlock b1b = new XcosBlock(res.getName(),res);
				
				XcosBlock b2 =new XcosBlock("WHAHH",res);
				XcosBlock b3 =new XcosBlock("BIBI",res);

				 
				blocks.add(b1);
				blocks.add(b2);
				blocks.add(b3); 
				
				//traces.add(new XcosTrace("COVERS",new ,"REQ900","COVERS2",res));
			
			
			
			
			} catch (CoreException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (XPathExpressionException xpe) {
				// TODO Auto-generated catch block
				xpe.printStackTrace();
			
			} catch (SAXException se) {
			    se.printStackTrace();
			} catch (IOException ioee) {
			    ioee.printStackTrace();
			} finally {
				try {
					in.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} 
			
			
			
			
			String req ="platform:/resource/Test1/R.reqcycle#_ZlcYUm9EEeONE5235LORwQ";
			
			
			
			
		} */
		
	}
	
	private static DocumentBuilder initializeDocBuilder() {
		DocumentBuilderFactory builderFactory =
		        DocumentBuilderFactory.newInstance();
		 
		try {
		    return builderFactory.newDocumentBuilder();
		} catch (ParserConfigurationException e) {
		    e.printStackTrace(); 
		    return null;
		}
	}
	
	public List<XcosBlock> getBlocks() {
		return blocks;
	}
	
	public List<XcosTrace> getTraces() {
		return traces;
	}

}
