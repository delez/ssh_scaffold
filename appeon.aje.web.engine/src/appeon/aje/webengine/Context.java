/**
 * 
 */
package appeon.aje.webengine;

import java.io.File;
import java.io.InputStream;
import java.net.URL;

import org.apache.commons.collections.ExtendedProperties;


/**
 *
 */
public class Context {
	public final String projectName;
	private FileHandler fileHandler;
	public final EngineerBuilder builder;
	public final ExtendedProperties porperties = new ExtendedProperties();
	private String basePackage;
	private String basePackagePath;
	private final static String properties_file = "webengine.properties";
	
	public Context(String project){
		this.projectName = project;
		try {
			URL url = Engineer.class.getClassLoader().getResource(properties_file);
			if(url == null)
				url = Engineer.class.getResource(properties_file);
			InputStream is = url.openStream(); 
			porperties.load(is);
			is.close();
		} catch (Exception e) {
			throw new EngineException("load configure(webengine.properties) fail!!!!");
		}
		String path = porperties.getString("file.handler");
		try {
			fileHandler = (FileHandler) Class.forName(path).newInstance();
			fileHandler.setContext(this);
		} catch (Exception e) {
			throw new EngineException("Initialize file handler "+path+" fail");
		} 
		builder = new EngineerBuilder(this);
	}
	/**
	 * 
	 * @param subPackage 空时返回BasePackage
	 * @return
	 */
	public String getPackage(String subPackage) {
		if(basePackage == null){
			basePackage = porperties.getString("path.package");
		}
		if(subPackage == null || subPackage.length() == 0)
			return basePackage;
		else if(basePackage == null || basePackage.length() == 0)
			return subPackage;
		else
			return basePackage + "." + subPackage;
	}
	/**
	 * 
	 * @param subPath
	 * @return
	 */
	public String getClassPath(String subPath){
		if(basePackagePath == null){
			String bag = getPackage(null);
			System.out.println(bag);
			if(bag.length() == 0)
				basePackagePath = null;
			else{
				basePackagePath = bag.replace('.', File.separatorChar);
			}
		}
		if(subPath == null || subPath.length() == 0)
			return basePackagePath;
		if(basePackagePath == null)
			return subPath;
		else
			return basePackagePath + File.separator + subPath;
	}

	/**
	 * @return Returns the projectName.
	 */
	public String getProjectName() {
		return this.projectName;
	}
	

	/**
	 * @param props The props to set.
	 */
	public void setProps(String key, String value) {
		this.porperties.setProperty(key, value);
	}
	/**
	 * @return Returns the fileHandler.
	 */
	public FileHandler getFileHandler() {
		return this.fileHandler;
	}
	/**
	 * @param fileHandler The fileHandler to set.
	 */
	public void setFileHandler(FileHandler fileHandler) {
		this.fileHandler = fileHandler;
	}
}
