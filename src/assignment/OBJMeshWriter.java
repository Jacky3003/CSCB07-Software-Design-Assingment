package assignment;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashSet;

public class OBJMeshWriter implements MeshWriter{

	@Override
	public void write(String filename, HashSet<Polygon> polygons) throws WrongFileFormatException, FileNotFoundException{
		// TODO Auto-generated method stub
		
		FileOutputStream file = null;
		file = new FileOutputStream(filename);
		PrintStream objWriter = new PrintStream(file);
		LinkedHashSet <Vertex> verticesList = new LinkedHashSet<Vertex>();
		for(Polygon polygon: polygons) {
			for (Vertex vertex: polygon.vertices) {
				int x = verticesList.size();
				verticesList.add(vertex);
				if(x < verticesList.size()) {
					objWriter.println("v " + Double.toString(vertex.xCor) + " " + Double.toString(vertex.yCor) + " " + Double.toString(vertex.zCor));
				}
			}
		}
		for(Polygon polygon: polygons) {
			ArrayList<Integer> countList = new ArrayList<Integer>();
			for(Vertex vertex1: polygon.vertices) {
				int count = 1;
				for(Vertex vertex2: verticesList) {
					if(vertex1.equals(vertex2)) {
						countList.add(count);
						count++;
					}
					else {count++;}
				}
			}
			objWriter.println("f " + countList.get(0) + " " + countList.get(1) + " " + countList.get(2));
		}
		objWriter.close();
	}
}
