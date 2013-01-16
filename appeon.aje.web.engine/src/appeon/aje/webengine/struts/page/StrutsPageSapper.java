package appeon.aje.webengine.struts.page;

import java.io.IOException;
import java.io.Writer;

import appeon.aje.webengine.Context;
import appeon.aje.webengine.FileHandler;
import appeon.aje.webengine.Sapper;
import appeon.aje.webengine.prototype.Model;

public class StrutsPageSapper extends Sapper {

	public StrutsPageSapper(Context ctx) {
		super(ctx);
		// TODO Auto-generated constructor stub
	}
	/**
	 * 
	 */
	public void start() {
		log("start struts page gen...");

	}
	/**
	 * 
	 */
	public void process(Model[] models) {
		for (int i = 0; i < models.length; i++) {
			precess(models[i]);
		}

	}
	private void precess(Model model) {
		
		Writer writer = null;
		try {
			writer = context.getFileHandler().getWriter(FileHandler.CATEGORY_PAGE, "");
			context.builder.out(writer, "struts_list.vm", model);
			
		}finally{
			if(writer != null)
				try {
					writer.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
		
	}
	/**
	 * 
	 */
	public void end() {
		log("end struts page gen...");

	}

	/**
	 * 
	 */
	public int getCategory() {
		return FileHandler.CATEGORY_PAGE;
	}

}
