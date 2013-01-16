/**
 * 
 */
package appeon.aje.webengine.test;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;

import appeon.aje.webengine.Sapper;
import appeon.aje.webengine.model.ModelSapper;
import appeon.aje.webengine.prototype.Model;

/**
 *
 */
public class TestModel {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
        BufferedWriter writer = writer = new BufferedWriter(
                new OutputStreamWriter(System.out));
        Model model = Stake.getModel();
        Sapper sapper = new ModelSapper(Stake.context);
        Stake.context.builder.out(writer, "appeon/aje/webengine/model/form.vm", model, sapper);
        try {
			writer.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
