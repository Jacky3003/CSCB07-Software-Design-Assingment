package assignment;

import java.io.FileNotFoundException;
import java.util.HashSet;

public class Mesh extends GraphicalObject{
	
	HashSet<Polygon> polygons;
	MeshReader reader;
	MeshWriter writer;
	
	public void setReader(MeshReader a) {
		reader = a;
	}
	
	public void setWriter(MeshWriter b) {
		writer = b;
	}
	
	public void readFromFile(String filename) {
		try {
			this.polygons = this.reader.read(filename);
		} catch (WrongFileFormatException e) {
			System.out.println("File format is wrong");
		}
	}

	public void writeToFile(String filename) throws FileNotFoundException{
		try {
			if((filename.endsWith(".obj") && (this.writer instanceof OBJMeshWriter))||(filename.endsWith(".ply") && (this.writer instanceof PLYMeshWriter))||(filename.endsWith(".off") && (this.writer instanceof OFFMeshWriter))) {
				this.writer.write(filename, polygons);
			}
			else {
				throw new WrongFileFormatException("File format is wrong");
			}
		} catch (WrongFileFormatException e) {
			System.out.println("File format is wrong");
		}
	}

	@Override
	public void transform(double[][] matrix) {
		for(Polygon each:polygons) {
			each.transform(matrix);
		}
	}
	
	@Override
	public int hashCode() {
		int x = 0;
		for(Polygon each:polygons) {
			x+= each.hashCode();
		}
		return x;
	}
	
	@Override
	public boolean equals(Object Obj) {
		
		if(!(Obj instanceof Mesh)) {return false;}
		Mesh newObj = (Mesh)Obj;
		if(this.polygons.size() != newObj.polygons.size()) {return false;}
		
		int x = 0;
		for(Polygon eachOne:this.polygons) {
			for(Polygon eachTwo:newObj.polygons) {
				if(eachOne.equals(eachTwo)) {
					x++;
				}
			}
		}
		if(x == this.polygons.size()) {return true;}
		else{return false;}
	}
}
