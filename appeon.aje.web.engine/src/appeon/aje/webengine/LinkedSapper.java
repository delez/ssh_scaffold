package appeon.aje.webengine;

import appeon.aje.webengine.prototype.Model;

public abstract class LinkedSapper extends Sapper {
	private LinkedSapper next;
	public LinkedSapper(Context ctx) {
		super(ctx);
	}
	protected void addNext(LinkedSapper next){
		if(next == this)
			throw new RuntimeException("this next couldn't pointer self");
		if(next != null){
			next.addNext(this.next);
			
		}
		this.next = next;
	}
	protected void removeNext(){
		if(this.next != null)
			this.next = this.next.next;
	}
	protected LinkedSapper getNext(){
		return this.next;
	}
	/**
	 * 
	 */
	public void start() {
		if(this.next != null)
			this.next.start();
	}
	/**
	 * 
	 */
	public final void process(Model[] models) {
		processNode(models);
		if(this.next != null)
			this.next.process(models);
	}
	protected abstract void processNode(Model[] models);
	/**
	 * 
	 */
	public void end() {
		if(this.next != null)
			this.next.end();
	}
	/**
	 * 
	 */
	public int getCategory() {
		return 0;
	}

}
