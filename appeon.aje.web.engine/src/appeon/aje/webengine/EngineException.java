/**
 * 
 */
package appeon.aje.webengine;

/**
 *
 */
public class EngineException extends RuntimeException {


	/**
	 * 
	 */
	private static final long serialVersionUID = -2766223359380266898L;

	/**
	 * @param message
	 * @param cause
	 */
	public EngineException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * @param message
	 */
	public EngineException(String message) {
		super(message);
	}

	/**
	 * 
	 */
	public EngineException() {
		super();
	}

	/**
	 * @param cause
	 */
	public EngineException(Throwable cause) {
		super(cause);
	}

}
