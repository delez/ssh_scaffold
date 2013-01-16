/**
 * 
 */
package appeon.aje.webengine.db;

import java.io.IOException;
import java.io.InputStream;
import java.io.StringBufferInputStream;
import java.io.StringWriter;
import java.util.Properties;

import appeon.aje.webengine.EngineException;
import appeon.aje.webengine.EngineerBuilder;
import appeon.aje.webengine.prototype.BaseTypeMap;
import appeon.aje.webengine.prototype.Field;

/**
 *
 */
public class SQLTypeMap  extends BaseTypeMap{
	private static SQLTypeMap instance;
	private final String template;
	private Properties maps;
	private SQLTypeMap(String dbType){
		template =  dbType +"type.vm";
	}
	public static SQLTypeMap getInstance(String dbType){
		if(instance == null)
			instance = new SQLTypeMap(dbType);
		return instance;
	}
	
	public String getType(Field field){
		StringWriter sw = new StringWriter();
		maps = new Properties();
		Properties properties = new Properties();
		properties.put("field", field);
		EngineerBuilder.out(sw, template, properties);
		InputStream is = new StringBufferInputStream(sw.getBuffer().toString());
		try {
			maps.load(is);
			is.close();
			sw.close();
		} catch (IOException e) {
			throw new EngineException("load type map from " + template + " fail!");
		}
		String typeName = getTypeName(field.getType());
		return (String)maps.get(typeName);
		
	}
}
