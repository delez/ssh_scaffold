/**
 * 
 */
package appeon.aje.webengine;

import java.io.IOException;
import java.io.Writer;
import java.util.Properties;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import org.apache.velocity.exception.MethodInvocationException;
import org.apache.velocity.exception.ParseErrorException;
import org.apache.velocity.exception.ResourceNotFoundException;


/**
 * 
 */
public class EngineerBuilder {
	private Context context;
	public EngineerBuilder(Context ctx) throws EngineException {
		this.context = ctx;
		try {
			Properties properties = new Properties();
			properties.load(EngineerBuilder.class.getResourceAsStream("/velocity.properties"));
			Velocity.init(properties);//
		} catch (Exception e) {
			throw new EngineException(e);
		}

	}
	/**
	 * 
	 * @param writer
	 * @param templateFile
	 * @param model
	 * @throws EngineException
	 */
	public void out(Writer writer, String templateFile, Object model)
			throws EngineException {

       out(writer, templateFile, "model", model);
	}
	/**
	 * 
	 * @param writer
	 * @param templateFile
	 * @param model
	 * @throws EngineException
	 */
	public void out(Writer writer, String templateFile, String name, Object model)
			throws EngineException {

        VelocityContext vc = new VelocityContext();
        vc.put(name, model);
        vc.put("context", context);
        out(writer, templateFile, vc);
	}
	/**
	 * 
	 * @param writer
	 * @param templateFile
	 * @param model
	 * @throws EngineException
	 */
	public void out(Writer writer, String templateFile, Object model, Sapper sapper)
			throws EngineException {

        VelocityContext vc = new VelocityContext();
        vc.put("model", model);
        vc.put("sapper", sapper);
        vc.put("context", context);
        out(writer, templateFile, vc);
		
	}
	public static void out(Writer writer, String templateFile, Properties props){
		out(writer, templateFile, new VelocityContext(props));
	}
	/**
	 * 
	 * @param writer
	 * @param templateFile
	 * @param context
	 * @throws EngineException
	 */
	public static void out(Writer writer, String templateFile, VelocityContext context)
			throws EngineException {

		Template template = null;
		try {
			template = Velocity.getTemplate(templateFile);
			if (template != null)
				template.merge(context, writer);
		} catch (ResourceNotFoundException rnfe) {
			throw new EngineException("error : cannot find template "
					+ templateFile + " : " + rnfe);
		} catch (ParseErrorException pee) {
			throw new EngineException("Syntax error in template "
					+ templateFile + ":" + pee);
		} catch (MethodInvocationException e) {
			throw new EngineException(e);
		} catch (Exception e) {
			throw new EngineException(e);
		}

		/*
		 * flush and cleanup
		 */

		try {
			writer.flush();
		} catch (IOException e) {
			throw new EngineException("close writer error", e);
		}
	}
}
