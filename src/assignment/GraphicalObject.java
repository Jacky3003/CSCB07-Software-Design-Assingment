package assignment;

public abstract class GraphicalObject {
	public abstract void transform(double [][] matrix);
	
	public void rotateXAxis(double ang) {
		double [][] matrix = {
				{1.0, 0, 0},
				{0, (double)Math.round(Math.cos(ang) * 1000000) / 1000000, (double)-Math.round(Math.sin(ang) * 1000000) / 1000000},
				{0, (double)Math.round(Math.sin(ang) * 1000000) / 1000000, (double)Math.round(Math.cos(ang) * 1000000) / 1000000}
		};
		this.transform(matrix);
	}
	
	public void rotateYAxis(double ang) {
		double [][] matrix = {
				{(double)Math.round(Math.cos(ang) * 1000000) / 1000000, 0, (double)Math.round(Math.sin(ang) * 1000000) / 1000000},
				{0, 1.0, 0},
				{(double)-Math.round(Math.sin(ang) * 1000000) / 1000000, 0, (double)Math.round(Math.cos(ang) * 1000000) / 1000000}
		};
		this.transform(matrix);
	}
	
	public void rotateZAxis(double ang) {
		double [][] matrix = {
				{(double)Math.round(Math.cos(ang) * 1000000) / 1000000, (double)-Math.round(Math.sin(ang) * 1000000) / 1000000, 0},
				{(double)Math.round(Math.sin(ang) * 1000000) / 1000000, (double)Math.round(Math.cos(ang) * 1000000) / 1000000, 0},
				{0, 0, 1.0}
		};
		this.transform(matrix);
	}
}
