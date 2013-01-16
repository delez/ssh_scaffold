/**
 * 
 */
package appeon.aje.webengine.test;

import java.sql.Types;

import appeon.aje.webengine.Context;
import appeon.aje.webengine.prototype.Field;
import appeon.aje.webengine.prototype.Model;
import appeon.aje.webengine.prototype.Reference;

/**
 *stub
 */
public class Stake {
	public static Context context = new Context("WebEngineTest");
	public Stake(){
		
	}
	public static Model getModel(){
        Model model = new Model("Test");
        Field field;
        field = model.addField("name", Types.VARCHAR);
        field.setLen(10);
        field.setKey(true);
        model.addField("type", Types.INTEGER);
        model.addField("flag", Types.BIT);
        model.addField("list", Types.CHAR);
        field = model.addField("date", Types.DATE);
        field = model.addField("salary", Types.DECIMAL);
        field.setPrecision(8);
        field.setScale(2);
        
        Reference ref = new Reference("Test", new String[]{"list"}, "List", new String[]{"id"});
        model.setRef(ref);
        return model;
	}
}
