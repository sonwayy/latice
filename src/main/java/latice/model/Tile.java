package latice.model;

public class Tile {
	Color color;
	Shape shape;
	
	public Tile(Color color, Shape shape) {
		this.color = color;
		this.shape = shape;
	}

	public Color getColor() {
		return color;
	}

	public Shape getShape() {
		return shape;
	}
}
