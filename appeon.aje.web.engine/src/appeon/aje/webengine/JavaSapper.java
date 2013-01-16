package appeon.aje.webengine;

import java.io.File;
import java.io.IOException;
import java.io.Writer;

import appeon.aje.webengine.prototype.Model;

public abstract class JavaSapper extends Sapper {

	public JavaSapper(Context ctx) {
		super(ctx);
	}
	/**
	 * 
	 */
	public void end() {

	}
	/**
	 * 
	 */
	public int getCategory() {
		return FileHandler.CATEGORY_CLASS;
	}
	/* (non-Javadoc)
	 * @see appeon.aje.webengine.Sapper#process(appeon.aje.webengine.prototype.Model[])
	 */
	public void process(Model[] models) {
		for (int i = 0; i < models.length; i++) {
			process(models[i]);
		}
	}

	private void process(Model model) {
		StringBuffer name = new StringBuffer();
		name.append(context.getClassPath(getSubPackage()));
		name.append(File.separator);
		name.append(model.getName());
		name.append(getSuffix());
		name.append(".java");
		
		Writer writer = context.getFileHandler().getWriter(getCategory(), name.toString());
        context.builder.out(writer, getTemplate(), model, this);
        try {
			writer.close();
		} catch (IOException e) {
			throw new EngineException("close writer fail, name = " + name);
		}
	}
	protected abstract String getTemplate() ;
	protected abstract String getSuffix() ;
	protected abstract String getSubPackage();
	
	/**
	 * 
	 */
	public void start() {
		// TODO Auto-generated method stub

	}
	public JavaToolkit getToolkit(){
		return JavaToolkit.instance;
	}
}
