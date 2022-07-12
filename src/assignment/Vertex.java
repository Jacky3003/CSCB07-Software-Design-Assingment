package assignment;

public class Vertex extends GraphicalObject{
	double xCor;
	double yCor;
	double zCor;
	public Vertex(double x, double y, double z) {
		xCor = x;
		yCor = y;
		zCor = z;
	}
	
	@Override
	public void transform(double[][] matrix) {
		double currX = this.xCor;
		double currY = this.yCor;
		double currZ = this.zCor;
		double [] corArr = {0, 0, 0};
		for(int i = 0; i < matrix.length; i++) {
			corArr[i] = (currX*matrix[i][0]) + (currY*matrix[i][1]) + (currZ*matrix[i][2]);
		}
		this.xCor = corArr[0];
		this.yCor = corArr[1];
		this.zCor = corArr[2];
	}
	
	@Override
	public boolean equals(Object obj) {
		if(!(obj instanceof Vertex)) {return false;}
		Vertex newObj = (Vertex)obj;
		if(newObj.xCor == this.xCor && newObj.yCor == this.yCor && newObj.zCor == this.zCor) {return true;}
		else {return false;}
	}
	
	@Override 
	public int hashCode() {
		return (int)((int)17*(this.xCor + this.yCor + this.zCor));
	}
	
	@Override
	public String toString() {
		return this.xCor + " " + this.yCor + " " + this.zCor;
	}
}
