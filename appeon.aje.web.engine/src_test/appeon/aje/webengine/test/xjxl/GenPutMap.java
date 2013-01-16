package appeon.aje.webengine.test.xjxl;

import appeon.aje.webengine.dao.ibatis.PutMapSapper;

public class GenPutMap extends BaseGen {

	public GenPutMap() {
	}
	public static void main(String[] args) {
		gen(PutMapSapper.class);
	}

}
