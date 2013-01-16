package appeon.aje.webengine.test.buildernet;

import java.io.IOException;
import java.io.Reader;
import java.io.StringWriter;
import java.io.Writer;

import appeon.aje.webengine.Context;
import appeon.aje.webengine.FileHandler;

public class StreamHandler implements FileHandler{

	public Reader getReader(int category, String path) {
		return null;
	}

	public Writer getWriter(int category, final String path) {
		return new StringWriter(){

			public void close() throws IOException {
				super.close();
			}

			public void flush() {
				System.out.println("------------------" + path);;
				System.out.println(this.getBuffer());
				super.flush();
			}
			
		};
	}

	public void setContext(Context ctx) {
		
	}

}