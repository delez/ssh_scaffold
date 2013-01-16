/**
 * 
 */
package appeon.aje.webengine.prototype;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.ListIterator;

/**
 *这个一个Java模型，用户只要定义这个模型，就可以完成一个Web应用的完整可运行流程
 *包括生成数据库, 事件管理，JavaModel, 页面， 控制器等。
 */
public class Model {
	private final String name;
	private final String variableName;
	private String dbName;
	private final String type;
	private final List<Field> fields = new ArrayList<Field>();
	private Reference ref;//外键
	private String description;

	public Model(String name, String type){
		this.name = name;
		this.type = type;
		variableName = StringKit.getVariableName(name);
		dbName = StringKit.getTitleName(name);
	}
	public Model(String name){
		this(name, null);
	}
	public void addField(Field field){
		fields.add(field);
	}
	public Field addField(String name, int type){
		Field field = new Field(name, type); 
		fields.add(field);
		return field;
	}
	public Collection getFields(){
		return fields;
//		return (Field[])fields.toArray(new Field[fields.size()]);
	}
	public List getPrimaryKeys(){
		List<Field> keys = new ArrayList<Field>();
		for (int i = 0; i < fields.size(); i++) {
			Field field = (Field)fields.get(i);
			if(field.isKey()){
				keys.add(field);
			}
		}
		return keys;
	}
	public String toString(){
		return name;
	}
	/**
	 * @return Returns the name.
	 */
	public String getName() {
		return this.name;
	}
	/**
	 * @return Returns the variableName.
	 */
	public String getVariableName() {
		return this.variableName;
	}
	/**
	 * @return Returns the type.
	 */
	public String getType() {
		return this.type;
	}
	/**
	 * @return Returns the ref.
	 */
	public Reference getRef() {
		return this.ref;
	}
	/**
	 * @param ref The ref to set.
	 */
	public void setRef(Reference ref) {
		this.ref = ref;
	}
	
	public Field find(String col){
		for(ListIterator ite = fields.listIterator(); ite.hasNext();){
			Field field = (Field)ite.next();
			if(col.equals(field.name))
					return field;
		}
		return null;
	}
	public void setDescription(String description) {
		this.description = description;
		
	}
	public String getDescription() {
		return this.description;
		
	}
	public String getDbName() {
		if(dbName == null || dbName.length() == 0)
			return name;
		else
			return dbName;
	}
	public void setDbName(String dbName) {
		this.dbName = dbName == null ?  null : dbName.trim();
	}
}
