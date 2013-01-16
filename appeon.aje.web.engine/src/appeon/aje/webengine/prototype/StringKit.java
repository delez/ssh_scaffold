/**
 * 
 */
package appeon.aje.webengine.prototype;


/**
 *
 */
public class StringKit {

	public static String getTitleName(String name){
		if(name == null || name.length() == 0)
			return null;
		return Character.toUpperCase(name.charAt(0)) + name.substring(1);
	}
	public static String getVariableName(String name){
		if(name == null || name.length() == 0)
			return null;
		return Character.toLowerCase(name.charAt(0)) + name.substring(1);
	}
	/**
	 * 将ClassName分成package和type name
	 * @param name
	 * @return
	 */
	public static String[] splitClassName(String type){
		int dotLastIndex = type.lastIndexOf('.');
		if(dotLastIndex > 0){
			return new String[]{type.substring(0, dotLastIndex), type.substring(dotLastIndex + 1)};
		}else{
			return new String[]{null, type};
		}
		
	}

}
