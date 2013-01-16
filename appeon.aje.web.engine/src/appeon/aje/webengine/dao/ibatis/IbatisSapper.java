package appeon.aje.webengine.dao.ibatis;

import java.io.IOException;
import java.io.Writer;

import appeon.aje.webengine.Context;
import appeon.aje.webengine.EngineException;
import appeon.aje.webengine.FileHandler;
import appeon.aje.webengine.Sapper;
import appeon.aje.webengine.prototype.Model;

public class IbatisSapper extends Sapper {

	public IbatisSapper(Context ctx) {
		super(ctx);
		// TODO Auto-generated constructor stub
	}

	/**
	 * 
	 */
	public void process(Model[] models) {
		for (int i = 0; i < models.length; i++) {
			process(models[i]);
		}
	}

	private void process(Model model) {
		String name = model.getName() + ".xml";
		
		Writer writer = context.getFileHandler().getWriter(getCategory(), name);
        context.builder.out(writer, TEMPLATE, "model", model);
        try {
			writer.close();
		} catch (IOException e) {
			throw new EngineException("close writer fail, name = " + name);
		}
		
	}
	private static final String TEMPLATE = "maps.vm";

	/**
	 * 
	 */
	public void start() {
		log("start Itatis ......");

	}
	/**
	 * 
	 */
	public void end() {
		log("end Itatis ......");

	}

	/**
	 * 
	 */
	public int getCategory() {
		return FileHandler.CATEGORY_CLASS;
	}


}
