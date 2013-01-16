/**
 * 
 */
package appeon.aje.webengine;

import appeon.aje.webengine.prototype.Model;

/**
 *
 */
public abstract class Sapper {
	protected final Context context;
	protected final EngineerBuilder builder;
	public Sapper(Context ctx){
		context = ctx;
		builder = ctx.builder;
	}
	public abstract void start();
	/**
	 * 最后将自己实现一个专门的resource.loader，进行vm的查找，
	 * 如通过数据库类型，就可查到相应的模板
	 * 如通过mssql, 并通过resource.loader.path就可查找相应的包下面的msql.vm
	 * @param model
	 */
	public abstract void process(Model[] models);
	public abstract void end();
	public abstract int getCategory();
	
	public Model[] extract(){
		return new Model[0];
	}
	
	protected void log(String log){
		System.out.println("WebEngine Info:" + log);
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
