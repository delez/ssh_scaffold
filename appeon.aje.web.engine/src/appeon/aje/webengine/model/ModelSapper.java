/**
 * 
 */
package appeon.aje.webengine.model;

import appeon.aje.webengine.Context;
import appeon.aje.webengine.JavaSapper;

/**
 *
 */
public class ModelSapper extends JavaSapper {

	/**
	 * @param ctx
	 */
	public ModelSapper(Context ctx) {
		super(ctx);
	}

	/* (non-Javadoc)
	 * @see appeon.aje.webengine.Sapper#start()
	 */
	public void start() {
		log("start preocess model...");
	}

	/* (non-Javadoc)
	 * @see appeon.aje.webengine.Sapper#end()
	 */
	public void end() {
		log("end preocess model...");
	}

	/**
	 * 
	 */
	protected String getSubPackage() {
		return SUB_PACKAGE;
	}
	/**
	 * 
	 */
	protected String getSuffix() {
		return "";
	}

	private static final String SUB_PACKAGE = "domain";
	private static final String TEMPLATE = "domain.vm";
	/**
	 * 
	 */
	protected String getTemplate() {
		return TEMPLATE;
	}

}
