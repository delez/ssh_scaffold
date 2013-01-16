/**
 * 
 */
package appeon.aje.webengine.test;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;

import appeon.aje.webengine.Sapper;
import appeon.aje.webengine.db.SQLSapper;
import appeon.aje.webengine.db.SQLTypeMap;
import appeon.aje.webengine.prototype.Model;

/**
 *
 */
public class TestMSSQL {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
        BufferedWriter writer = writer = new BufferedWriter(
                new OutputStreamWriter(System.out));
        Model model = Stake.getModel();
        Sapper sapper = new SQLSapper(Stake.context){
        	public SQLTypeMap getSqlMap(){

        		return SQLTypeMap.getInstance("mssql");
        	}        	
        };
        
        Stake.context.builder.out(writer, "mssql.vm", model, sapper);

        try {
			writer.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}

}
