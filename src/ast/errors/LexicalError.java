package ast.errors;

/**
 * Represents a lexical error.
 */
public class LexicalError extends Error {
	private static final long serialVersionUID = 1L;

	protected String character;
	protected int errorLine;
	protected int errorColumn;

	public LexicalError(String character, int errorLine, int errorColumn) {
		this.character = character;
		this.errorLine = errorLine;
		this.errorColumn = errorColumn;
	}

	/**
	 * Gets position of an error.
	 *
	 * @return string - a string with the line and the column where there is an
	 *         error
	 */
	protected String getPosition() {
		/** asserting that, if "errorLine" is set, then "errorColumn" is set */
		return "[ " + errorLine + " : " + errorColumn + " ]";
	}

	@Override
	public String getMessage() {
		return toString();
	}

	@Override
	public String toString() {
		return this.getPosition() + " - unsupported char: " + character + ".";
	}
}
