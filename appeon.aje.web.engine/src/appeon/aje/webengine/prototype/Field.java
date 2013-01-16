/**
 * 
 */
package appeon.aje.webengine.prototype;

import java.util.Arrays;
import java.util.Collection;

import appeon.aje.webengine.EngineException;

/**
 *Model中的一个字段
 */
public class Field {
	public final String name;
	private String dbName;
	public final String titleName;
	public final int type;
	
	private int len;
	private int precision;
	private int scale;

	private boolean key;
	private boolean identity;
	private boolean isnull = true;
	
	private boolean password;
	
	private String initial;
	
	private String title;//grid header title
	private String caption;//free form input caption
	private String[] values;//edit code table

	public Field(String name, int type){
		if(name == null)
			throw new EngineException("the field name couldn't empty!!");
		this.name = name.trim();
		if(name.length() == 0)
			throw new EngineException("the field name couldn't empty!!");
		this.titleName = StringKit.getTitleName(name);
		this.type = type;
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
	 * @return Returns the name.
	 */
	public String getTitleName() {
		
		return this.titleName;
	}
	/**
	 * @return Returns the identity.
	 */
	public boolean isIdentity() {
		return this.identity;
	}
	/**
	 * @param identity The identity to set.
	 */
	public void setIdentity(boolean identity) {
		this.identity = identity;
	}
	/**
	 * @return Returns the initial.
	 */
	public String getInitial() {
		return this.initial;
	}
	/**
	 * @param initial The initial to set.
	 */
	public void setInitial(String initial) {
		this.initial = initial;
	}
	/**
	 * @return Returns the key.
	 */
	public boolean isKey() {
		return this.key;
	}
	/**
	 * @param key The key to set.
	 */
	public void setKey(boolean key) {
		this.key = key;
		this.isnull = false;
	}
	/**
	 * @return Returns the values.
	 */
	public Collection getValues() {
		if(values == null)
			return null;
		return Arrays.asList(this.values);
	}
	/**
	 * @param values The values to set.
	 */
	public void setValues(String[] values) {
		this.values = values;
	}
	/**
	 * @return Returns the isnull.
	 */
	public boolean isNull() {
		return this.isnull;
	}
	/**
	 * @param isnull The isnull to set.
	 */
	public void setNull(boolean isnull) {
		this.isnull = isnull;
	}
	/**
	 * @return Returns the len.
	 */
	public int getLen() {
		return this.len;
	}
	/**
	 * @param len The len to set.
	 */
	public void setLen(int len) {
		this.len = len;
	}
	/**
	 * @return Returns the precision.
	 */
	public int getPrecision() {
		return this.precision;
	}
	/**
	 * @param precision The precision to set.
	 */
	public void setPrecision(int precision) {
		this.precision = precision;
	}
	/**
	 * @return Returns the type.
	 */
	public int getType() {
		return this.type;
	}
	/**
	 * @return Returns the scale.
	 */
	public int getScale() {
		return this.scale;
	}
	/**
	 * @param scale The scale to set.
	 */
	public void setScale(int scale) {
		this.scale = scale;
	}
	/**
	 * @return Returns the password.
	 */
	public boolean isPassword() {
		return this.password;
	}
	/**
	 * @param password The password to set.
	 */
	public void setPassword(boolean password) {
		this.password = password;
	}
	public String getCaption() {
		return caption;
	}
	public void setCaption(String caption) {
		this.caption = caption;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
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
