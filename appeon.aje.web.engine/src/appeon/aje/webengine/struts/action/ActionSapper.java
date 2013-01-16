/**
 * 
 */
package appeon.aje.webengine.struts.action;

import appeon.aje.webengine.Context;
import appeon.aje.webengine.JavaSapper;

/**
 *
 */
public class ActionSapper extends JavaSapper{

	/**
	 * @param ctx
	 */
	public ActionSapper(Context ctx) {
		super(ctx);
		// TODO Auto-generated constructor stub
	}

	/* (non-Javadoc)
	 * @see appeon.aje.webengine.Sapper#start()
	 */
	public void start() {
		this.log("start preocess Action...");
		
	}
	/* (non-Javadoc)
	 * @see appeon.aje.webengine.Sapper#end()
	 */
	public void end() {
		this.log("end preocess Action...");
		
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
		return SUFFIX_NAME;
	}

	/**
	 * 
	 */
	protected String getTemplate() {
		return TEMPLATE;
	}
	private static final String SUB_PACKAGE = "actions";
	private static final String SUFFIX_NAME = "Action";
	private static final String TEMPLATE = "action.vm";



}
