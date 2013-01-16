/**
 * 
 */
package appeon.aje.webengine;

import java.lang.reflect.Constructor;

import appeon.aje.webengine.prototype.Model;

/**
 *通过配置文件webEngine.properties进行生成类
 *
 */
public class Engineer {
	private Context context;
	public Engineer(){
		
	}
	public void init(String name){
		context = new Context(name);
	}
	public void run(Model[] models){
		Sapper sapper = null;
		String[] sappers = context.porperties.getStringArray("sapper");
		for (int i = 0; i < sappers.length; i++) {
			String name = sappers[i];
			sapper = newSapper(context.porperties.getString(name +".class"));
			sapper.start();
			sapper.process(models);
			sapper.end();
			
		}
		
	}
	private Sapper newSapper(String clazz){
		Constructor creator;
		try {
			creator = Class.forName(clazz).getConstructor(new Class[]{Context.class});
			return (Sapper)creator.newInstance(new Object[]{context});
		} catch (Exception e) {
			throw new EngineException("create sapper error, className:" + clazz, e);
		}
	}
	public void end(){
		context = null;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
