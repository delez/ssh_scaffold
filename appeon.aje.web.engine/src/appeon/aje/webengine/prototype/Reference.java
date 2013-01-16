/**
 * 
 */
package appeon.aje.webengine.prototype;

import java.util.Arrays;
import java.util.Collection;

/**
 *
 */
public class Reference {
	private final String foreignTable;
	private final String[] foreignFields;

	private final String primaryTable;
	private final String[] primaryFields;
	/**
	 * @param foreignTable
	 * @param foreignFields
	 * @param primaryTable
	 * @param primaryFields
	 */
	public Reference(String foreignTable, String[] foreignFields, String primaryTable, String[] primaryFields) {
		super();
		// TODO Auto-generated constructor stub
		this.foreignTable = foreignTable;
		this.foreignFields = foreignFields;
		this.primaryTable = primaryTable;
		this.primaryFields = primaryFields;
	}
	/**
	 * @param foreignTable
	 * @param foreignField
	 * @param primaryTable
	 * @param primaryField
	 */
	public Reference(String foreignTable, String foreignField, String primaryTable, String primaryField) {
		this(foreignTable, new String[]{foreignField}, primaryTable, new String[]{primaryField});
	}
	/**
	 * @return Returns the foreignFields.
	 */
	public Collection getForeignFields() {
		return Arrays.asList(this.foreignFields);
	}
	/**
	 * @return Returns the foreignTable.
	 */
	public String getForeignTable() {
		return this.foreignTable;
	}
	/**
	 * @return Returns the primaryFields.
	 */
	public Collection getPrimaryFields() {
		return Arrays.asList(this.primaryFields);
	}
	/**
	 * @return Returns the primaryTable.
	 */
	public String getPrimaryTable() {
		return this.primaryTable;
	}
	public static Reference parse(String textContent) {
		// TODO Auto-generated method stub
		return null;
	}
}
