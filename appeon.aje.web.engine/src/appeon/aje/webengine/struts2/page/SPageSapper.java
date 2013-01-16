/**
 * 
 */
package appeon.aje.webengine.struts2.page;

import java.io.File;
import java.io.IOException;
import java.io.Writer;

import appeon.aje.webengine.Context;
import appeon.aje.webengine.EngineException;
import appeon.aje.webengine.FileHandler;
import appeon.aje.webengine.Sapper;
import appeon.aje.webengine.prototype.Model;

/**
 * 这个Sapper格式为有四个模板， 分别是 ***_show.vm, ***_list.vm, ***_edit.vm, ***_new.vm
 */
public class SPageSapper extends Sapper {
	/**
	 * @param ctx
	 */
	public SPageSapper(Context ctx) {
		super(ctx);
		// TODO Auto-generated constructor stub
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see appeon.aje.webengine.Engineer#start()
	 */
	public void start() {
		log("start preocess page...");

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see appeon.aje.webengine.Engineer#process()
	 */
	public void process(Model[] models) {
		try {
			for (int i = 0; i < models.length; i++) {
				process(models[i]);
			}
		} catch (IOException e) {
			throw new EngineException("close writer fail ");
		}

	}

	/**
	 * @param model
	 * @throws IOException 
	 */
	private void process(Model model) throws IOException {
		
		outTemplate("struts_edit.vm", model, "edit.jsp");
		outTemplate("struts_show.vm", model, "show.jsp");
		outTemplate("struts_list.vm", model, "list.jsp");
		outTemplate("struts_new.vm", model, "new.jsp");

	}
	protected void outTemplate(String vm, Model model, String pageName) throws IOException{
		String name = model.getName() + File.separatorChar + pageName;
		FileHandler handler = context.getFileHandler();
		Writer writer = handler.getWriter(getCategory(), name);
		context.builder.out(writer, vm, model);
		writer.close();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see appeon.aje.webengine.Engineer#end()
	 */
	public void end() {
		log("end preocess page...");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see appeon.aje.webengine.Sapper#getCategory()
	 */
	public int getCategory() {
		return FileHandler.CATEGORY_PAGE;
	}
}
