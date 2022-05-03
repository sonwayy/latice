package latice.model;

public enum Color {
	YELLOW("y"), 
	NAVYBLUE("n"), 
	RED("r"), 
	MAGENTA("m"), 
	GREEN("g"), 
	TURQUOISE("t"); //TODO find what is  the color turchese, and write it's color code 
	
	private String stringColor;
	
	Color(String stringColor) {
		this.stringColor = stringColor;
	}
	
	public String getStringColor() {
		return this.stringColor;
	}
}
