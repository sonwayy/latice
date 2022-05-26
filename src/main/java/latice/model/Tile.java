package latice.model;

public class Tile {
	private final Color color;
	private final Shape shape;
	private Position position;
	
	public Tile(Color color, Shape shape) {
		this.color = color;
		this.shape = shape;
	}
	
	public void setPosition(Position position) {
		this.position = position;
	}
	
	public Color getColor() {
		return this.color;
	}
	
	public String getColorConsole() {
		return this.color.getStringColorConsole();
	}
	
	public Shape getShape() {
		return this.shape;
	}
	
	public String getShapeConsole() {
		return this.shape.getStringShapeConsole();
	}
	
	public Position getPosition() {
		return this.position;
	}
}
