package appeon.aje.webengine;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import appeon.aje.webengine.prototype.Model;

public class CompositeSapper extends Sapper {

	public CompositeSapper(Context ctx) {
		super(ctx);
		// TODO Auto-generated constructor stub
	}
	private List<Sapper> children = new ArrayList<Sapper>(4);
	protected void add(Sapper child){
		if(child != null){
			children.add(child);
				
		}
	}
	protected void remove(Sapper child){
		children.remove(child);
	}
	protected void remove(int index){
		children.remove(index);
	}
	protected Sapper[] getChilde(){
		return (Sapper[])children.toArray(new Sapper[0]);
	}
	/**
	 * 
	 */
	public void start() {
		for (Iterator ite = children.iterator(); ite.hasNext();) {
			((Sapper)ite.next()).start();
		}

	}
	/**
	 * 
	 */
	public void process(Model[] models) {
		for (Iterator ite = children.iterator(); ite.hasNext();) {
			((Sapper)ite.next()).process(models);
		}

	}
	/**
	 * 
	 */
	public void end() {
		for (Iterator ite = children.iterator(); ite.hasNext();) {
			((Sapper)ite.next()).end();
		}

	}
	/**
	 * 
	 */
	public int getCategory() {
		// TODO Auto-generated method stub
		return 0;
	}


}
