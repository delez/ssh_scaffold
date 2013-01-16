package appeon.aje.webengine.meta;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import appeon.aje.webengine.Context;
import appeon.aje.webengine.EngineException;
import appeon.aje.webengine.FileHandler;
import appeon.aje.webengine.Sapper;
import appeon.aje.webengine.prototype.Field;
import appeon.aje.webengine.prototype.Model;
import appeon.aje.webengine.prototype.Reference;

public class XMLSapper extends Sapper {
	public static final String EXTRACT_TYPE  = "extract.type";
	public static final String EXTRACT_TYPE_CLASS  = "class";//classload 加载资源
	public static final String EXTRACT_TYPE_FILE = "file";
	public static final String EXTRACT_SOURCE = "extract.source";

	public XMLSapper(Context ctx) {
		super(ctx);
	}
	/**
	 * 
	 */
	public void start() {
		log("building xml meta... ");
	}
	/**
	 * 
	 */
	public void process(Model[] models) {
		String name = "meta.xml";
		
		Writer writer = context.getFileHandler().getWriter(getCategory(), name);
        context.builder.out(writer, TEMPLATE, "models", models);
        try {
			writer.close();
		} catch (IOException e) {
			throw new EngineException("close writer fail, name = " + name);
		}
	}
	private static final String TEMPLATE = "meta.vm";
	/**
	 * 
	 */
	public void end() {
		log("builded xml meta... ");
	}
	/**
	 * 
	 */
	public Model[] extract() {
		InputStream is = null;
		try {
			String type = context.porperties.getString(EXTRACT_TYPE);
			String src = context.porperties.getString(EXTRACT_SOURCE);
			if("class".equals(type)){
				is = XMLSapper.class.getResourceAsStream(src);
			}else{
				is = new FileInputStream(src);
			}
			if(is == null){
				System.err.println("the resource " +src+" don't found from " + type);
				return super.extract();
			}
			DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
			Document doc = builder.parse(is);
			Element root = doc.getDocumentElement();
			NodeList nodes = root.getChildNodes();
			int len = nodes.getLength();
			List<Model> models = new ArrayList<Model>(len);
			for (int i = 0; i < len; i++) {
				Node node = nodes.item(i);
				if(node.getNodeType() == Node.ELEMENT_NODE){
					Element ele = (Element)node;
					String name = ele.getAttribute("name");
					String modeType = ele.getAttribute("type");
					Model model = new Model(name, modeType);
					models.add(model);
					String dbName = ele.getAttribute("dbName");
					if(dbName.length() > 0)
						model.setDbName(dbName);
					model.setDescription(getOrphansContent(ele, "description"));
					model.setRef(Reference.parse(getOrphansContent(ele, "ref")));
					Element fields = (Element)(ele.getElementsByTagName("fields").item(0));
					addFields(model, fields);
					
				}
			}
			return (Model[])models.toArray(new Model[models.size()]);
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			if(is != null)
				try {
					is.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
		}
		return super.extract();
	}
	private static String getOrphansContent(Element parent, String name){
		NodeList nl = parent.getElementsByTagName("description");
		if(nl.getLength() > 0){
			return  ((Element)nl.item(0)).getTextContent();
		}
		return null;
	}
	private void addFields(Model model, Element fields) {
		NodeList nodes = fields.getChildNodes();
		int len = nodes.getLength();
		for (int i = 0; i < len; i++) {
			Node node = nodes.item(i);
			if(node.getNodeType() == Node.ELEMENT_NODE){
				Element ele = (Element)node;
				String name = ele.getAttribute("name");
				String type = ele.getAttribute("type");
				Field field = model.addField(name, Integer.parseInt(type));
				String dbName = ele.getAttribute("dbName");
				if(dbName.length() > 0)
					field.setDbName(dbName);
				field.setLen(Integer.parseInt(ele.getAttribute("len")));
				field.setPrecision(getChildIntContent(ele, "precision"));
				field.setScale(getChildIntContent(ele, "scale"));				field.setKey(getChildBoolContent(ele, "key"));
				field.setIdentity(getChildBoolContent(ele," identity"));
				field.setNull(getChildBoolContent(ele, "isnull"));
				field.setPassword(getChildBoolContent(ele, "password"));
				String values = getChildContent(ele, "values");
				if(values != null && values.trim().length() > 0)
					field.setValues(values.split(","));
				field.setTitle(getChildContent(ele, "title"));
				field.setCaption(getChildContent(ele, "caption"));
				
			}
		}
		
	}
	private boolean getChildBoolContent(Element ele, String child){
		String content = getChildContent(ele, child);
		if(content == null)
			return false;
		else
			return Boolean.parseBoolean(content);
		
	}	
	private int getChildIntContent(Element ele, String child){
		String content = getChildContent(ele, child);
		if(content == null)
			return 0;
		else
			return Integer.parseInt(content);
		
	}
	private String getChildContent(Element ele, String child){
		NodeList nodes = ele.getElementsByTagName(child);
		if(nodes.getLength() > 0){
			return nodes.item(0).getTextContent();
		}else{
			return null;
		}
		
	}
	/**
	 * 
	 */
	public int getCategory() {
		return FileHandler.CATEGORY_META;
	}
}
