package assignment;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashSet;

public class PLYMeshWriter implements MeshWriter{

	@Override
	public void write(String filename, HashSet<Polygon> polygons) throws FileNotFoundException {
		FileOutputStream file = null;
		file = new FileOutputStream(filename);
		
		LinkedHashSet<Vertex> verticesList = new LinkedHashSet<Vertex>();
		for(Polygon each:polygons) {
			for(Vertex vertex:each.vertices) {
				verticesList.add(vertex);
			}
		}
		
		PrintStream objWriter = new PrintStream(file);
		objWriter.println("ply");
		objWriter.println("format ascii 1.0");
		objWriter.println("element vertex " + verticesList.size());
		objWriter.println("property float32 x");
		objWriter.println("property float32 y");
		objWriter.println("property float32 z");
		objWriter.println("element face " + polygons.size());
		objWriter.println("property list uint8 int32 vertex_indices");
		objWriter.println("end_header");
		
		for(Vertex vertex:verticesList) {
			objWriter.println(vertex.xCor+ " " + vertex.yCor + " " + vertex.zCor);
		}
		
		for(Polygon polygon: polygons) {
			ArrayList<Integer> countList = new ArrayList<Integer>();
			int verticesNum = 0;
			for(Vertex vertex1: polygon.vertices) {
				int count = 0;
				for(Vertex vertex2: verticesList) {
					if(vertex1.equals(vertex2)) {
						countList.add(count);
						count++;
						verticesNum++;
					}
					else {count++;}
				}
			}
			objWriter.println(verticesNum + " " + countList.get(0) + " " + countList.get(1) + " " + countList.get(2));
		}
		objWriter.close();
	}

}
