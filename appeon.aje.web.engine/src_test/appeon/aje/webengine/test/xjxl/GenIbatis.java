package appeon.aje.webengine.test.xjxl;

import appeon.aje.webengine.dao.ibatis.IbatisSapper;

public class GenIbatis extends BaseGen{
	public static void main(String[] args) {
		gen(IbatisSapper.class);
	}
}

