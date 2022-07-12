package assignment;

import java.util.LinkedHashSet;

public class Polygon extends GraphicalObject{

	LinkedHashSet <Vertex> vertices;
	
	public Polygon(LinkedHashSet <Vertex> setVertices) {
		this.vertices = setVertices;
	}

	@Override
	public void transform(double[][] matrix) {
		for(Vertex vertex: vertices) {
			vertex.transform(matrix);
		}
	}
	
	@Override
	public int hashCode() {
		int x = 0;
		int y = 0;
		int z = 0;
		for(Vertex vertex: vertices) {
			x += (int)vertex.xCor;
			y += (int)vertex.yCor;
			z += (int)vertex.zCor;
		}
		return (x + y + z) * 17;
	}
	
	@Override
	public boolean equals(Object obj) {
		if(!(obj instanceof Polygon)) {return false;}
		Polygon newObj = (Polygon)obj;
		if(this.vertices.size() != newObj.vertices.size()) {return false;}
		
		int i = 0;
		for(Vertex vertexOne: this.vertices) {
			for(Vertex vertexTwo: newObj.vertices) {
				if(vertexOne.equals(vertexTwo)) {
					i++;
				}
			}
		}
		
		if(i == newObj.vertices.size()) {return true;}
		else {return false;}
	}
	
	
	
	
}
