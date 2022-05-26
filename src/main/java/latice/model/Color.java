package latice.model;

public enum Color {
	YELLOW("y", "Y"), 
	NAVYBLUE("n", "N"), 
	RED("r", "R"), 
	MAGENTA("m", "M"), 
	GREEN("g", "G"), 
	TURQUOISE("t", "T");
	
	private String stringColor;
	private String stringColorConsole;
	
	Color(String stringColor, String stringColorConsole) {
		this.stringColor = stringColor;
		this.stringColorConsole = stringColorConsole;
	}
	
	public String getStringColor() {
		return this.stringColor;
	}
	
	public String getStringColorConsole() {
		return this.stringColorConsole;
	}
}
