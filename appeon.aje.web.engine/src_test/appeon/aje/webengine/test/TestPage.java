/**
 * 
 */
package appeon.aje.webengine.test;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.lang.reflect.Method;
import java.sql.Types;

import appeon.aje.webengine.prototype.Field;
import appeon.aje.webengine.prototype.Model;

/**
 *
 */
public class TestPage {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
        BufferedWriter writer = writer = new BufferedWriter(
                new OutputStreamWriter(System.out));
		Method[] methods = TestPage.class.getDeclaredMethods();
		Method method = null;
		try {
			for (int i = 0; i < methods.length; i++) {
				method= methods[i];
				if(!"main".equals(method.getName()))
					method.invoke(null, new Object[]{writer});
			}
		} catch (Exception e) {
			if(method != null)
				System.out.println(method.getName());
			e.printStackTrace();
		}
        try {
			writer.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static void testShow(Writer writer){
        Model model = Stake.getModel();
        Stake.context.builder.out(writer, "appeon/aje/webengine/page/jsp_show.vm", model);
	}
	public static void testForm(Writer writer){
        Field field;
        field = new Field("name", Types.VARCHAR);
        Stake.context.builder.out(writer, "appeon/aje/webengine/page/jsp_form.vm", "field", field);
        field = new Field("gender", Types.BIT);
        field.setValues(new String[]{"男", "女"});
        Stake.context.builder.out(writer, "appeon/aje/webengine/page/jsp_form.vm", "field", field);
        field = new Field("interest", Types.VARCHAR);
        field.setValues(new String[]{"游泳", "蓝球", "象棋", "旅游"});
        Stake.context.builder.out(writer, "appeon/aje/webengine/page/jsp_form.vm", "field", field);
        field = new Field("password", Types.VARCHAR);
        field.setPassword(true);
        Stake.context.builder.out(writer, "appeon/aje/webengine/page/jsp_form.vm", "field", field);
        field = new Field("memo", Types.VARCHAR);
        field.setLen(256);
        Stake.context.builder.out(writer, "appeon/aje/webengine/page/jsp_form.vm", "field", field);
        field = new Field("marry", Types.BIT);
        Stake.context.builder.out(writer, "appeon/aje/webengine/page/jsp_form.vm", "field", field);
	}
	public static void testEdit(Writer writer){
        Model model = Stake.getModel();
        Stake.context.builder.out(writer, "appeon/aje/webengine/page/jsp_edit.vm", model);
		
	}
	public static void testList(Writer writer){
        Model model = Stake.getModel();
        Stake.context.builder.out(writer, "appeon/aje/webengine/page/jsp_list.vm", model);
	}
	public static void testNew(Writer writer){
        Model model = Stake.getModel();
        Stake.context.builder.out(writer, "appeon/aje/webengine/page/jsp_new.vm", model);
		
	}

}
