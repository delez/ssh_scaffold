package appeon.aje.webengine.test.xjxl;

import appeon.aje.webengine.dao.ibatis.AdoImplSapper;

public class AdoImplGen extends BaseGen {
	public static void main(String[] args) {
		gen(AdoImplSapper.class);
	}

}
