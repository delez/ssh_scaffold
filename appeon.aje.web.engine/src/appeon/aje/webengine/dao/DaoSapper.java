package appeon.aje.webengine.dao;

import appeon.aje.webengine.Context;
import appeon.aje.webengine.JavaSapper;

public class DaoSapper extends JavaSapper {

	public DaoSapper(Context ctx) {
		super(ctx);
	}

	protected String getSubPackage() {
		return "dao";
	}

	protected String getSuffix() {
		return "Dao";
	}

	protected String getTemplate() {
		return TEMPLATE;
	}
	private static final String TEMPLATE = "dao.vm"; 

}
