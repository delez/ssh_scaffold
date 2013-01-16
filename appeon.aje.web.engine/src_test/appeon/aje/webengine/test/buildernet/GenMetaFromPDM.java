package appeon.aje.webengine.test.buildernet;

import java.io.File;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import appeon.aje.webengine.Context;
import appeon.aje.webengine.JavaToolkit;
import appeon.aje.webengine.Sapper;
import appeon.aje.webengine.meta.XMLSapper;
import appeon.aje.webengine.prototype.Field;
import appeon.aje.webengine.prototype.Model;


public class GenMetaFromPDM extends BaseGen {
	public GenMetaFromPDM(String meta) {
		super(meta);
	}
	public Model[] parse() throws Exception{
		File pdm = new File("F:\\workspace\\BuilderNet\\design\\BuilderNet.pdm");
		DocumentBuilder db = DocumentBuilderFactory.newInstance().newDocumentBuilder();
		Document doc = db.parse(pdm);
		NodeList tableslist = doc.getElementsByTagName("c:Tables");
		if(tableslist.getLength() == 0){
			return new Model[0];
		}
		Element tables = (Element)tableslist.item(0);
		NodeList tablelist = tables.getElementsByTagName("o:Table");//or tables.getChildNode();
		int len = tablelist.getLength();
		Model[] models = new Model[len];
		for (int i = 0; i < len; i++) {
			Element table = (Element)tablelist.item(i);
			String name = getOrphanText(table, "a:Name");
			System.out.println("----table----:" + name);
			String code = getOrphanText(table, "a:Code");
			Model model = new Model(code, name);
			model.setDescription(name);
			Set<String> keys = getKeys(table);
			addField(getOrphan(table, "c:Columns"), model, keys);
			models[i] = model;
			System.out.println();
		}
		return models;
	}
	private static Set<String> getKeys(Element keys){
		if(keys == null) return (Set<String>)Collections.EMPTY_SET;
		Set< String> keyMap = new HashSet<String>();
		NodeList key_list = keys.getElementsByTagName("o:Key");
		for (int i = 0; i < key_list.getLength(); i++) {
			Element key_cols = getOrphan((Element)key_list.item(i),  "c:Key.Columns");
			if(key_cols == null) continue;
			NodeList col_list = key_cols.getChildNodes();
			int len = col_list.getLength();
			for (int j = 0; j < len; j++) {
				Node node = col_list.item(j);
				if(!"o:Column".equals(node.getNodeName())) continue;
				Element col = (Element)node;
				keyMap.add(col.getAttribute("Ref"));
			}
			
		}
		return keyMap;
		
	}
	private static void addField(Element columns, Model model, Set<String> keys) {
		if(columns == null) return;
		NodeList list = columns.getChildNodes();
		int len = list.getLength();
		for (int i = 0; i < len; i++) {
			Node node =list.item(i);
			if(!"o:Column".equals(node.getNodeName()))
				continue;
			Element col = (Element)node;
			String id = col.getAttribute("Id");
			String name = getOrphanText(col, "a:Name");
			String code = getOrphanText(col, "a:Code");
			System.out.print(".");
			String sqlType = getOrphanText(col, "a:DataType");
			String length = getOrphanText(col, "a:Length");
			String identity = getOrphanText(col, "a:Identity");
			String mondatory = getOrphanText(col, "a:Mandatory");
			Field f = new Field(code, getJDBCType(sqlType));
			f.setCaption(name);
			f.setTitle(name);
			f.setLen(length == null ? 0 :Integer.parseInt(length) );
			f.setNull(mondatory == null ? true : !"1".equals(mondatory));
			f.setIdentity(identity == null ? false : "1".equals(identity));
			if(keys.contains(id)) f.setKey(true);
			model.addField(f);
		}
	}
	private static int getJDBCType(String sqlType){
		return JavaToolkit.convertType(sqlType);
	}
	private static  Element getOrphan(Element ele, String tagName){
		NodeList list = ele.getElementsByTagName(tagName);
		return (list.getLength() == 0) ?  null :(Element) list.item(0);
	}
	private static String getOrphanText(Element ele, String tagName){
		NodeList list = ele.getElementsByTagName(tagName);
		if(list.getLength() == 0)
			return null;
		return list.item(0).getTextContent();
	}
	public static void main(String[] args) {
		GenMetaFromPDM gm = new GenMetaFromPDM(null);
		Context context = gm.getContext(XMLSapper.EXTRACT_TYPE_CLASS,   "");
		Sapper xmlsapper = new XMLSapper(context);
		try {
			Model[] models = gm.parse();
			xmlsapper.process(models);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}
