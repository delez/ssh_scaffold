package appeon.aje.webengine.test.xjxl;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import appeon.aje.webengine.Context;
import appeon.aje.webengine.Sapper;
import appeon.aje.webengine.meta.XMLSapper;
import appeon.aje.webengine.prototype.Model;

public class BaseGen {
	protected static void gen(Class clazz){
		Context context = new Context("PatrolInspection");
		context.setFileHandler(new StreamHandler());
		context.setProps(XMLSapper.EXTRACT_TYPE, XMLSapper.EXTRACT_TYPE_CLASS);
		context.setProps(XMLSapper.EXTRACT_SOURCE, "/appeon/aje/webengine/test/xjxl/PlanDetail.xml");
		context.setProps("path.package", "pvt.dlz.inspection");
		XMLSapper xmlsapper = new XMLSapper(context);
		Model[] models = xmlsapper.extract();
		Constructor creator;
		try {
			creator = clazz.getConstructor(new Class[]{Context.class});
			Sapper sapper = (Sapper)creator.newInstance(new Object[]{context});
			sapper.process(models);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
	}

}
