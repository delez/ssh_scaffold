/**
 * 
 */
package appeon.aje.webengine.test;

import java.sql.Types;

import appeon.aje.webengine.Context;
import appeon.aje.webengine.Engineer;
import appeon.aje.webengine.db.SQLSapper;
import appeon.aje.webengine.model.ModelSapper;
import appeon.aje.webengine.page.PageSapper;
import appeon.aje.webengine.prototype.Field;
import appeon.aje.webengine.prototype.Model;

/**
 *
 */
public class Test {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Model list = new Model("List");
		Field field = list.addField("id", Types.CHAR);
		field.setKey(true);
		Model[] models = new Model[]{Stake.getModel(), list};
		Engineer engineer = new Engineer();
		engineer.init("test");
		engineer.run(models);
		engineer.end();
		
//		Context ctx = new Context("test");
//		SQLSapper ss = new SQLSapper(ctx);
//		ss.start();
//		ss.process(models);
//		ss.end();
//		FormSapper fs = new FormSapper(ctx);
//		fs.start();
//		fs.process(models);
//		fs.end();
//		
//		PageSapper ps = new PageSapper(ctx);
//		ps.start();
//		ps.process(models);
//		ps.end();
	}

}
