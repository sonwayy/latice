package latice.model;

public enum Shape {
	BIRD("bird"), 
	DOLPHIN("dolphin"), 
	FLOWER("flower"), 
	feather("feather"), 
	GECKO("gecko"), 
	TURTLE("turtle");
	
	private String stringShape;
	
	Shape(String stringShape) {
		this.stringShape = stringShape;
	}
	
	public String getStringShape() {
		return this.stringShape;
	}
}
