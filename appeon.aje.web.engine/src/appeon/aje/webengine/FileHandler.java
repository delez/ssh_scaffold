/**
 * 
 */
package appeon.aje.webengine;

import java.io.Reader;
import java.io.Writer;


/**
 *
 */
public interface FileHandler {
	int CATEGORY_CLASS = 0;
	int CATEGORY_DB = 1;
	int CATEGORY_PAGE = 2;
	int CATEGORY_CONFIG = 3;
	int CATEGORY_META = 4;
	
	void setContext(Context ctx);
	/**
	 * 
	 * @param category
	 * @param name 含有相对于工程结构的全路径
	 * @return
	 */
	Writer getWriter(int category, String path);
	/**
	 * 
	 * @param category
	 * @param name
	 * @return
	 */
	Reader getReader(int category, String path);

}
