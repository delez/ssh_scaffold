package appeon.aje.webengine.test.buildernet;

import appeon.aje.webengine.struts2.page.SPageSapper;

public class GenPage extends BaseGen {
	public GenPage(String meta) {
		super(meta);
	}

	public static void main(String[] args) {
		GenPage gen = new GenPage("/appeon/aje/webengine/test/buildernet/meta.xml");
		gen.setFilter("receive");
		gen.gen(SPageSapper.class);
	}
}
