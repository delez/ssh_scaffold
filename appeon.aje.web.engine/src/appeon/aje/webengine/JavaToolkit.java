/**
 * 
 */
package appeon.aje.webengine;

import java.sql.Types;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Properties;
import java.util.Set;

import appeon.aje.webengine.prototype.BaseTypeMap;
import appeon.aje.webengine.prototype.Field;
import appeon.aje.webengine.prototype.Model;
import appeon.aje.webengine.prototype.StringKit;

/**
 *
 */
public class JavaToolkit extends BaseTypeMap{
	private static Properties name_type_map = new Properties();
	static{
		try {
			name_type_map.load(JavaToolkit.class.getResourceAsStream("java-type-map.properties"));
		} catch (Exception e) {
			new EngineException("load map.properties fail!!!");
		}
	}
	public static JavaToolkit instance = new JavaToolkit();
	private JavaToolkit(){
		
	}

	/**
	 * @return Returns the type.
	 */
	public String getShortType(String type) {
		if(type == null) return null;
		return StringKit.splitClassName(type)[1];
	}
	/**
	 * 得到模型的数据类型
	 * @return field type, 也就是JDBC类型
	 */
	public static String getType(int type){
		return name_type_map.getProperty(getTypeName(type));
	}	
	/**
	 * 将SQL类型转换成JDBC类型
	 * @param sql_type
	 * @return
	 */
	public static int convertType(String sql_type){
		if(sql_type == null) return  Types.VARCHAR;
		int index = sql_type.indexOf('(');
		if(index > 0){
			sql_type = sql_type.substring(0, index);
		}
		sql_type = sql_type.toUpperCase();
		if("INT".equals(sql_type))
			sql_type = "INTEGER";
		else if("DATETIME".equals(sql_type)){
			sql_type="TIMESTAMP";
		}
		 java.lang.reflect.Field f;
		try {
			f = Types.class.getField(sql_type);
			return f.getInt(null);
		} catch (Exception e) {
			e.printStackTrace();
		} 
		return  Types.VARCHAR;
	}	
	/**
	 * @return Returns the _package.
	 */
	public String getPackage(String type) {
		if(type == null) return null;
		return StringKit.splitClassName(type)[0];
	}
	/**
	 * 
	 * @return
	 */
	public Collection getClassPaths(Model model){
		Collection fields = model.getFields();
		Set types = new HashSet();
		for (Iterator ite = fields.iterator(); ite.hasNext();) {
			Field field = (Field)ite.next();
			String fieldtype = getType(field.type);
			String[] ts = StringKit.splitClassName(fieldtype);
			if(ts[0] != null && !"java.lang".equals(ts[0]) && !types.contains(fieldtype)){
				types.add(fieldtype);
			}
		}
		return types;
//		return (String[])types.toArray(new String[types.size()]);
	}
	public String getParameters(Collection parameters){
		StringBuffer sb = new StringBuffer();
		for (Iterator ite = parameters.iterator(); ite.hasNext(); ) {
			Field field = (Field)ite.next();
			sb.append(getShortType(getType(field.type)));
			sb.append(" ");
			sb.append(field.getName().toLowerCase());
			if(ite.hasNext()){
				sb.append(", ");
			}
		}
		return sb.toString();
	}
	/**
	 * 
	 * @param v
	 * @return
	 */
	public String toLowerCase(String v){
		return v.toLowerCase();
	}
}
