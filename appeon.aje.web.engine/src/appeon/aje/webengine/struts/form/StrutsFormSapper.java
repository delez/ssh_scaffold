package appeon.aje.webengine.struts.form;

import appeon.aje.webengine.Context;
import appeon.aje.webengine.model.ModelSapper;

public class StrutsFormSapper extends ModelSapper {

	public StrutsFormSapper(Context ctx) {
		super(ctx);
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

	private static final String SUFFIX_NAME = "Form";

	private static final String TEMPLATE = "form.vm";

	private static final String SUB_PACKAGE = "form";

}
