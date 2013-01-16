package appeon.aje.webengine.test;

import appeon.aje.webengine.Context;
import appeon.aje.webengine.db.SQLSapper;
import appeon.aje.webengine.meta.XMLSapper;

public class TestDBExtract {
	public static Context context = new Context("TestDBExtract");
	public static void main(String[] args) {
		SQLSapper sqlsapper = new SQLSapper(context);
		XMLSapper xmlsapper = new XMLSapper(context);
		sqlsapper.start();
		xmlsapper.start();
		xmlsapper.process(sqlsapper.extract());
		xmlsapper.end();
		sqlsapper.end();
	}

}
