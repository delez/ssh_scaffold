/**
 * 
 */
package appeon.aje.webengine;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.Writer;


/**
 *File Writer
 */
public class FileHandlerImpl implements FileHandler {
	
	private Context context;
	private final String charsetName;

	public FileHandlerImpl(){
		charsetName = "utf-8";
	}

	public FileHandlerImpl(Context ctx, String charsetName){
		this.context = ctx;
		this.charsetName = charsetName;
	}

	/* (non-Javadoc)
	 * @see appeon.aje.webengine.FileHandler#getWriter(java.lang.String, java.lang.String)
	 */
	public Writer getWriter(int category, String path) {
		String root = context.porperties.getString("path.root");
		StringBuffer sb = new StringBuffer(root);
		switch(category){
		case CATEGORY_CLASS:
			sb.append(File.separator);
			sb.append(context.porperties.getString("path.source"));
			String pg = context.getClassPath(path);
			if(pg != null){
				sb.append(File.separator);
				sb.append(pg);
			}

			break;
		case CATEGORY_DB:
			sb.append(File.separator);
			sb.append(context.porperties.getString("path.sql"));
			sb.append(File.separator);
			sb.append(path);
			break;
		case CATEGORY_PAGE:
			sb.append(File.separator);
			sb.append(context.porperties.getString("path.web"));
			sb.append(File.separator);
			sb.append(path);
			break;
		case CATEGORY_META:
			sb.append(File.separator);
			sb.append(context.porperties.getString("path.meta"));
			sb.append(File.separator);
			sb.append(path);
			break;
		default:
			throw new EngineException("the engine don't found type " + category);
			
		}
		String fileName = sb.toString();
		try {
			File file = new File(fileName);
			if(!file.exists()){
				file.getParentFile().mkdirs();
				file.createNewFile();
			}
			return new OutputStreamWriter(new FileOutputStream(file), charsetName);
		} catch (IOException e) {
			throw new EngineException("create file "+fileName+" error", e);
		}
	}

	/* (non-Javadoc)
	 * @see appeon.aje.webengine.FileHandler#getReader(java.lang.String, java.lang.String)
	 */
	public Reader getReader(int category, String name) {
		return null;
	}

	/* (non-Javadoc)
	 * @see appeon.aje.webengine.FileHandler#setContext(appeon.aje.webengine.prototype.Context)
	 */
	public void setContext(Context ctx) {
		this.context = ctx;
		
	}

}
