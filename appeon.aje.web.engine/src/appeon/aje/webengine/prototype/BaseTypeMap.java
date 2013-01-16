/**
 * 
 */
package appeon.aje.webengine.prototype;

import java.sql.Types;
import java.util.HashMap;
import java.util.Map;

import appeon.aje.webengine.EngineException;

/**
 *
 */
public class BaseTypeMap {
	private static Map value_name_maps = new HashMap();
	static{
		java.lang.reflect.Field[] fields = Types.class.getFields();
		try {
			for (int i = 0; i < fields.length; i++) {
				java.lang.reflect.Field field = fields[i];
				value_name_maps.put(field.get(null), field.getName());
			}
		} catch (Exception e) {
			new EngineException("load java type to name map fail!!!");
		}
	}
	public static int size(Object[] array){
		if(array == null)
			return -1;
		return array.length;
	}
	/**
	 * 得到模型的数据类型
	 * @return field type, 也就是JDBC类型
	 */
	public static String getTypeName(int type){
		return (String)value_name_maps.get(new Integer(type));
	}
}
