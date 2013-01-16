package appeon.aje.webengine.test.buildernet;

import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import appeon.aje.webengine.Context;
import appeon.aje.webengine.FileHandlerImpl;
import appeon.aje.webengine.Sapper;
import appeon.aje.webengine.meta.XMLSapper;
import appeon.aje.webengine.prototype.Model;

public class BaseGen {
	protected final String meta;
	private String filter;
	
	public BaseGen(String meta){
		this.meta = meta;
	}
	
	protected  void gen(Class clazz){
		gen( clazz, "class",  "net.builder");
	}
	protected  void gen(Class clazz, String type, String _package){
		Context context = getContext(type,  _package);
		Sapper xmlsapper = new XMLSapper(context);
		Model[] models = xmlsapper.extract();
		Constructor creator;
		try {
			creator = clazz.getConstructor(new Class[]{Context.class});
			Sapper sapper = (Sapper)creator.newInstance(new Object[]{context});
			models = filter(models);
			sapper.process(models);
		} catch (Exception e) {
			e.printStackTrace();
		} 
		
	}
	private Model[] filter(Model[] models){
		if(filter == null || models == null )
			return models;
		List<Model> list = new ArrayList<Model>();
		Pattern pattern =  Pattern.compile(filter);
		for (int i = 0; i < models.length; i++) {
			if(pattern.matcher(models[i].getName()).matches()){
				list.add(models[i]);
			}
		}
		return list.toArray(new Model[list.size()]);
	}
	protected  Context getContext( String type,  String _package){
		Context context = new Context("builderNet");
		context.setFileHandler(new FileHandlerImpl(context, "utf-8"));
		context.setProps(XMLSapper.EXTRACT_TYPE,  type);
		if(meta != null){
			context.setProps(XMLSapper.EXTRACT_SOURCE, meta);
		}
		context.setProps("path.package", _package);
		return context ;
	}

	public String getFilter() {
		return filter;
	}

	public void setFilter(String filter) {
		this.filter = filter;
	}
}
