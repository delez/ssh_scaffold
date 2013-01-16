/**
 * 
 */
package appeon.aje.webengine;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


/**
 *
 */
public class ConnectionManager {
	public static Connection conn;
	
	public static Connection getConnection(Context ctx){
		if(conn == null){
			String drv = ctx.porperties.getString("driverClass");
			String url = ctx.porperties.getString("connectionURL");
			String user = ctx.porperties.getString("userId");
			String pwd = ctx.porperties.getString("password");
			try {
				Class.forName(drv);
				conn = DriverManager.getConnection(url, user, pwd);
			} catch (ClassNotFoundException e) {
				throw new EngineException("the drv class not found:" + drv);
			} catch (SQLException e) {
				throw new EngineException("new Connect fail, url = " + url, e);
			}
		}
		return conn;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
