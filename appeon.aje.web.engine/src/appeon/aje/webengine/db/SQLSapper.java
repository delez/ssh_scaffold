/**
 * 
 */
package appeon.aje.webengine.db;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import appeon.aje.webengine.ConnectionManager;
import appeon.aje.webengine.Context;
import appeon.aje.webengine.EngineException;
import appeon.aje.webengine.FileHandler;
import appeon.aje.webengine.Sapper;
import appeon.aje.webengine.prototype.Field;
import appeon.aje.webengine.prototype.Model;

/**
 * 这个Sapper格式为有三个SQL模板，分别是 ***_create.vm, ***_drop.vm, ***_alter.vm
 * 并且再附加一个***.vm合成这三个vm
 * 
 * 分模板的原因是因为外健的主表需要先存在，而这需要确定SQL顺序。 所以系统采用先建表，再建约束的方法
 */
public class SQLSapper extends Sapper {
	private static final int category = FileHandler.CATEGORY_DB;

	private static final String dbName = "db.name";

	private final boolean supportsBatch = false;

	/**
	 * @param ctx
	 */
	public SQLSapper(Context ctx) {
		super(ctx);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see appeon.aje.webengine.Engineer#start()
	 */
	public void start() {
		log("start preocess sql...");

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see appeon.aje.webengine.Engineer#process()
	 */
	public void process(Model[] models) {
		String[][] sqls = new String[models.length][3];
		for (int i = 0; i < models.length; i++) {
			sqls[i] = buildSQL(models[i]);
		}
		buildDB(sqls);
	}

	/**
	 * @return
	 */
	private String[] buildSQL(Model model) {
		String name = model.getName() + ".sql";
		final String type = context.porperties.getString(dbName);
		String[] sqls = new String[3];
		log("build SQL Script...");
		StringWriter sw = new StringWriter();
		String vm = type + "_drop.vm";
		builder.out(sw, vm, model, this);
		sw.flush();
		sqls[0] = sw.toString();
		sw = new StringWriter();
		vm = type + "_create.vm";
		builder.out(sw, vm, model, this);
		sw.flush();
		sqls[1] = sw.toString();
		if (model.getRef() != null) {
			sw = new StringWriter();
			vm = type + "_alter.vm";
			builder.out(sw, vm, model, this);
			sw.flush();
			sqls[2] = sw.toString();
		}

		vm = type + ".vm";
		Writer writer = context.getFileHandler().getWriter(getCategory(), name);
		builder.out(writer, vm, model, this);
		try {
			writer.close();
		} catch (IOException e) {
			throw new EngineException("close writer fail, name = " + vm);
		}

		return sqls;
	}

	/**
	 * @param sql
	 */
	private void buildDB(String[][] sqls) {
		Connection conn = ConnectionManager.getConnection(context);
		String sql = null;
		try {
			// supportsBatch = conn.getMetaData().supportsBatchUpdates()
			Statement stat = conn.createStatement();
			if (supportsBatch) {
				for (int i = 0; i < sqls.length; i++) {
					stat.addBatch(sqls[i][0]);
					stat.addBatch(sqls[i][1]);
					stat.addBatch(sqls[i][2]);
				}
				stat.executeBatch();
			} else {
				for (int i = 0; i < sqls.length; i++) {
					sql = sqls[i][0];
					stat.execute(sql);
				}
				for (int i = 0; i < sqls.length; i++) {
					sql = sqls[i][1];
					stat.execute(sql);
				}
				for (int i = 0; i < sqls.length; i++) {
					sql = sqls[i][2];
					if (sql != null)
						stat.execute(sql);
				}

			}
			stat.close();
		} catch (SQLException e) {
			throw new EngineException("execute sql fail" + sql == null ? ""
					: ", sql = " + sql, e);
		}

	}

	public SQLTypeMap getSqlMap() {
		final String type = context.porperties.getString(dbName);

		return SQLTypeMap.getInstance(type);
	}

	/**
	 * 
	 */
	public Model[] extract() {
		log("extract models from db");
		Connection conn = ConnectionManager.getConnection(context);
		if(conn == null)
			return new Model[0];
		ResultSet rs = null;
		try {
			rs = conn.getMetaData().getTables("", "", "",
					new String[] { "TABLE" });
			List<Model> models = new ArrayList<Model>();
			while (rs.next()) {
				String catalog = rs.getString("TABLE_CAT");
				String schema = rs.getString("TABLE_SCHEM");
				if(catalog == null) catalog = "";
				if(schema == null) schema = "";
				rs.getString("TABLE_TYPE");
				rs.getString("REMARKS");
//				rs.getString("TYPE_CAT");
//				rs.getString("TYPE_SCHEM");
//				rs.getString("TYPE_NAME");
//				rs.getString("SELF_REFERENCING_COL_NAME");
//				rs.getString("REF_GENERATION");
				String name = rs.getString("TABLE_NAME");
				Model model = createModel(catalog, schema, name, conn);
				setKeys(catalog, schema, name, conn, model);
				models.add(model);
			}
			return models.toArray(new Model[models.size()]);
		} catch (Exception e) {
			throw new EngineException(e);
		}finally{
			if(rs != null)
				try {
					rs.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	/**
	 * 
	 * @param tablename
	 * @param rs
	 * @return
	 * @throws SQLException
	 */
	private Model createModel(String catalog, String schema, String tablename, Connection conn) throws SQLException {
		String name = tablename;
		if (tablename.startsWith("t_"))
			name = name.substring(2);
		Model model = new Model(name);
		ResultSet rs = null;
		try {
			rs = conn.getMetaData().getColumns(catalog, schema, tablename, "");
			while(rs.next()){
				String fieldName = rs.getString("COLUMN_NAME");
				int type = rs.getInt("DATA_TYPE");
				Field field = new Field(fieldName, type);
				
				rs.getString("TYPE_NAME");
				field.setLen(rs.getInt("COLUMN_SIZE"));
				field.setPrecision(rs.getInt("BUFFER_LENGTH"));
				field.setScale(rs.getInt("DECIMAL_DIGITS"));
				boolean isnull = rs.getInt("NULLABLE") == DatabaseMetaData.columnNullable;//columnNoNulls, columnNullable, columnNullableUnknown
				//boolean isnull = "YES".equalsIgnoreCase(rs.getString("IS_NULLABLE"));//"YES" or "NO"
				
				field.setNull(isnull);
				field.setInitial(rs.getString("COLUMN_DEF"));
	//			field.setDescribe(rs.getString("REMARKS"));//maybe get auto_increment
//				System.err.println(rs.getString("REMARKS"));
	
				rs.getInt("NUM_PREC_RADIX");
				field.setIdentity(false);
	
				model.addField(field);
			}
		}finally{
			if(rs != null)
				rs.close();
		}
		return model;

	}
	/**
	 * 
	 * @param cat
	 * @param schem
	 * @param name
	 * @param conn
	 * @param model
	 * @throws SQLException 
	 */
	private void setKeys(String catalog, String schema, String table, Connection conn, Model model) throws SQLException {
		ResultSet rs = conn.getMetaData().getPrimaryKeys(catalog, schema, table);
		while(rs.next()){
			String col = rs.getString("COLUMN_NAME");
			Field field = model.find(col);
			if(field != null){
				field.setKey(true);
			}
		}
		
	}


	/*
	 * (non-Javadoc)
	 * 
	 * @see appeon.aje.webengine.Engineer#end()
	 */
	public void end() {
		log("end preocess sql...");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see appeon.aje.webengine.Sapper#getCategory()
	 */
	public int getCategory() {
		return category;
	}
}
