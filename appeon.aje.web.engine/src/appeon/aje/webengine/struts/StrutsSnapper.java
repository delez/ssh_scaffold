/**
 * 
 */
package appeon.aje.webengine.struts;

import appeon.aje.webengine.CompositeSapper;
import appeon.aje.webengine.Context;
import appeon.aje.webengine.struts.action.ActionSapper;
import appeon.aje.webengine.struts.page.StrutsPageSapper;

/**
 *
 */
public class StrutsSnapper extends CompositeSapper {

	/**
	 * @param ctx
	 */
	public StrutsSnapper(Context ctx) {
		super(ctx);
		init();
	}
	protected void init(){
		this.add(new ActionSapper(context));
		this.add(new StrutsPageSapper(context));
	}
}
