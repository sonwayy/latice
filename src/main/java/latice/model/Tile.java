package latice.model;

public class Tile {
	private final Color color;
	private final Shape shape;
	
	public Tile(Color color, Shape shape) {
		this.color = color;
		this.shape = shape;
	}
	
	public Color getColor() {
		return this.color;
	}
	
	public Shape getShape() {
		return this.shape;
	}
}
