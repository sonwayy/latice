package latice.model;

public enum Shape {
	BIRD("bird", "B"), 
	DOLPHIN("dolphin", "D"), 
	FLOWER("flower", "Fl"), 
	FEATHER("feather", "F"), 
	GECKO("gecko", "G"), 
	TURTLE("turtle", "T");
	
	private String stringShape;
	private String stringShapeConsole;
	
	Shape(String stringShape, String stringShapeConsole) {
		this.stringShape = stringShape;
		this.stringShapeConsole = stringShapeConsole;
	}
	
	public String getStringShape() {
		return this.stringShape;
	}
	
	public String getStringShapeConsole() {
		return this.stringShapeConsole;
	}
}
