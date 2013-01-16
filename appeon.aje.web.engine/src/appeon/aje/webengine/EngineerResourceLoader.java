/**
 * 
 */
package appeon.aje.webengine;

import java.io.InputStream;

import org.apache.commons.collections.ExtendedProperties;
import org.apache.velocity.exception.ResourceNotFoundException;
import org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader;

/**
 * 
 */
public class EngineerResourceLoader extends ClasspathResourceLoader {

	String paths[];

	public void init(ExtendedProperties configuration) {
		rsvc.info("EngineerResourceLoader : initialization starting.");

		paths = (String[])configuration.getVector("path").toArray(new String[0]);

		/*
		 * lets tell people what paths we will be using
		 */

		int sz = paths.length;
		String path;
		for (int i = 0; i < sz; i++) {
			path = paths[i];
			rsvc.info("EngineerResourceLoader : adding path '"
					+ path + "'");
			if(!path.endsWith("/")){
				paths[i] = path + "/";
			}
		}

		rsvc.info("EngineerResourceLoader : initialization complete.");
	}

	public synchronized InputStream getResourceStream(String name)
			throws ResourceNotFoundException {
		InputStream result = null;

		if (name == null || name.length() == 0) {
			throw new ResourceNotFoundException("No template name provided");
		}

		int sz = paths.length;

		try {
			ClassLoader classLoader = this.getClass().getClassLoader();
			result = classLoader.getResourceAsStream(name);
			if(result == null)
				for (int i = 0; i < sz; i++) {
					String path = paths[i] + name;
					result = classLoader.getResourceAsStream(path);
					if(result != null)
						break;
				}
		} catch (Exception fnfe) {
			/*
			 * log and convert to a general Velocity ResourceNotFoundException
			 */

			throw new ResourceNotFoundException(fnfe.getMessage());
		}

		return result;
	}
}
