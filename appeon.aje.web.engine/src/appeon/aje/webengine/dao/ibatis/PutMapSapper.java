package appeon.aje.webengine.dao.ibatis;

import java.io.IOException;
import java.io.Writer;

import appeon.aje.webengine.Context;
import appeon.aje.webengine.EngineException;
import appeon.aje.webengine.FileHandler;
import appeon.aje.webengine.Sapper;
import appeon.aje.webengine.prototype.Model;

public class PutMapSapper extends Sapper {

	public PutMapSapper(Context ctx) {
		super(ctx);
	}

	public void end() {

	}

	public int getCategory() {
		return FileHandler.CATEGORY_CLASS;
	}

	public void process(Model[] models) {
		for (int i = 0; i < models.length; i++) {
			process(models[i]);
		}
	}

	private void process(Model model) {
		Writer writer = context.getFileHandler().getWriter(getCategory(), "");
        context.builder.out(writer, TEMPLATE, "model", model);
        try {
			writer.close();
		} catch (IOException e) {
			throw new EngineException("close writer fail, name = " + null);
		}
		
	}
	private static final String TEMPLATE = "putMap.vm";

	public void start() {

	}

}
