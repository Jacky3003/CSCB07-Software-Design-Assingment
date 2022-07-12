package assignment;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashSet;
import java.util.LinkedHashSet;

public class Driver {
	@Test
	void TestEqualsVertex() throws Exception{ // Vertex test cases
		Vertex a = new Vertex(2.3, 4.2, 4.1);
		Vertex b = new Vertex(2.3, 4.2, 4.1);
		assertTrue(a.equals(b));
	}
	
	@Test
	void TestNotEqualsVertex() throws Exception{
		Vertex a = new Vertex(2.3, 4.2, 4.1);
		Vertex b = new Vertex(2.2, 4.2, 4.1);
		assertFalse(a.equals(b));
	}
	
	@Test
	void TestNotEqualsVertexTwo() throws Exception{
		Vertex a = new Vertex(-2.234123, -4.123412, 2);
		Vertex b = new Vertex(-2.234123, -4, 2);
		assertFalse(a.equals(b));
	}
	
	@Test
	void TestNotEqualsVertexThree() {
		Vertex a = new Vertex(0, 0, 0.00000000001);
		Vertex b = new Vertex(0, 0, 0.0000000001);
		assertFalse(a.equals(b));
	}

	@Test
	void TestEqualsForObjVertex() throws Exception{
		Vertex a = new Vertex(2.3, 4.2, 4.1);
		Object b = new Object();
		assertFalse(a.equals(b));
	}
	
	@Test
	void TestToStringVertex() throws Exception{
		Vertex a = new Vertex(2.3, 4.2, 4.1);
		assertEquals("2.3 4.2 4.1", a.toString());
	}
	
	@Test
	void TestSameHashCodeVertex() throws Exception{ 
		Vertex a = new Vertex(2.3, 4.2, 4.1);
		Vertex b = new Vertex(2.3, 4.2, 4.1);
		assertEquals(b.hashCode(), a.hashCode());
	}
	
	@Test
	void TestNotSameHashCodeVertex() throws Exception{
		Vertex a = new Vertex(2.3, 4.2, 4.1);
		Vertex b = new Vertex(2.2, 4.2, 4.1);
		assertNotSame(b.hashCode(), a.hashCode());
	}
	
	@Test
	void TestTransformVertex() throws Exception{
		Vertex a = new Vertex(1.0, 1.0, 1.0);
		a.rotateXAxis(Math.toRadians(90.0));
		Vertex b = new Vertex(1.0, -1.0, 1.0); //Fix this line
		assertTrue(a.equals(b));
	}
	
	@Test
	void TestTransformVertexNotEquals() throws Exception{
		Vertex a = new Vertex(1.0, 1.0, 1.0);
		a.rotateZAxis(Math.toRadians(45.0));
		Vertex b = new Vertex(1.0, -1.0, 1.0); //Fix this line
		assertFalse(a.equals(b));
	}
	
	// end of vertex test cases (how to fix floating point, how to count coverage)
	//Start of Polygon tests

	@Test
	void TestEqualsPolygon() {
		Vertex a = new Vertex(1.1, 2.3, 4.5);
		Vertex b = new Vertex(0.3, 1.4, -9.3);
		LinkedHashSet <Vertex> vertices = new LinkedHashSet<Vertex>();
		vertices.add(b);
		Polygon newOne = new Polygon(vertices);
		vertices.add(a);
		Polygon newTwo = new Polygon(vertices);
		assertTrue(newOne.equals(newTwo));
	}
	
	@Test
	void TestNotEqualsPolygon() {
		Vertex a = new Vertex(1.1, 2.3, 4.5);
		Vertex b = new Vertex(0.3, 1.4, -9.3);
		Vertex c = new Vertex(1.0, 2.3, 4.5);
		Vertex d = new Vertex(0.2, 1.4, -9.3);
		LinkedHashSet <Vertex> verticesOne = new LinkedHashSet<Vertex>();
		verticesOne.add(b);
		Polygon newOne = new Polygon(verticesOne);
		verticesOne.add(a);
		LinkedHashSet <Vertex> verticesTwo = new LinkedHashSet<Vertex>();
		verticesTwo.add(c);
		verticesTwo.add(d);
		Polygon newTwo = new Polygon(verticesTwo);
		assertFalse(newOne.equals(newTwo));
	}
	
	@Test
	void TestNotEqualsPolygonLength() {
		Vertex a = new Vertex(1.1, 2.3, 4.5);
		Vertex b = new Vertex(0.3, 1.4, -9.3);
		Vertex d = new Vertex(0.2, 1.4, -9.3);
		LinkedHashSet <Vertex> verticesOne = new LinkedHashSet<Vertex>();
		verticesOne.add(b);
		Polygon newOne = new Polygon(verticesOne);
		verticesOne.add(a);
		LinkedHashSet <Vertex> verticesTwo = new LinkedHashSet<Vertex>();
		verticesTwo.add(d);
		Polygon newTwo = new Polygon(verticesTwo);
		assertFalse(newOne.equals(newTwo));
	}
	
	@Test
	void TestNotEqualsPolygonObj() {
		Vertex a = new Vertex(1.1, 2.3, 4.5);
		Vertex b = new Vertex(0.3, 1.4, -9.3);
		Vertex d = new Vertex(0.2, 1.4, -9.3);
		LinkedHashSet <Vertex> verticesOne = new LinkedHashSet<Vertex>();
		verticesOne.add(b);
		verticesOne.add(d);
		verticesOne.add(a);
		Polygon newOne = new Polygon(verticesOne);
		Object newTwo = new Object();
		assertFalse(newOne.equals(newTwo));
	}
	
	@Test
	void TestTransformPolygon() {
		Vertex a = new Vertex(1.0, 2.0, 3.0);
		Vertex b = new Vertex(2.0, 1.0, 3.0);
		Vertex c = new Vertex(3.0, 1.0, 2.0);
		Vertex d = new Vertex(1.0, 2.0, 3.0);
		Vertex e = new Vertex(2.0, 1.0, 3.0);
		Vertex f = new Vertex(3.0, 1.0, 2.0);
		LinkedHashSet <Vertex> verticesOne = new LinkedHashSet<Vertex>();
		verticesOne.add(b);
		verticesOne.add(c);
		verticesOne.add(a);
		LinkedHashSet <Vertex> verticesTwo = new LinkedHashSet<Vertex>();
		verticesTwo.add(d);
		verticesTwo.add(e);
		verticesTwo.add(f);
		Polygon newOne = new Polygon(verticesOne);
		Polygon newTwo = new Polygon(verticesTwo);
		newOne.rotateYAxis(Math.toRadians(360.0));
		assertEquals(newOne, newTwo);
	}
	
	@Test 
	void TestSameHashCodePolygon(){
		Vertex a = new Vertex(1.0, 2.0, 3.0);
		Vertex b = new Vertex(2.0, 1.0, 3.0);
		Vertex c = new Vertex(3.0, 1.0, 2.0);
		Vertex d = new Vertex(1.0, 2.0, 3.0);
		Vertex e = new Vertex(2.0, 1.0, 3.0);
		Vertex f = new Vertex(3.0, 1.0, 2.0);
		LinkedHashSet <Vertex> verticesOne = new LinkedHashSet<Vertex>();
		verticesOne.add(b);
		verticesOne.add(c);
		verticesOne.add(a);
		LinkedHashSet <Vertex> verticesTwo = new LinkedHashSet<Vertex>();
		verticesTwo.add(d);
		verticesTwo.add(e);
		verticesTwo.add(f);
		Polygon newOne = new Polygon(verticesOne);
		Polygon newTwo = new Polygon(verticesTwo);
		assertEquals(newOne.hashCode(), newTwo.hashCode());
	}
	//End of Polygon Tests 
	//Start of Mesh Tests
	
	@Test
	void TestSameHashCodeMesh() {
		Mesh meshOne = new Mesh();
		Mesh meshTwo = new Mesh();
		meshOne.setReader(new OBJMeshReader());
		meshTwo.setReader(new OBJMeshReader());
		meshOne.readFromFile("C:\\Users\\jacky\\OneDrive\\Desktop\\b07\\assignment\\src\\assignment\\Obj_sample.obj");
		meshTwo.readFromFile("C:\\Users\\jacky\\OneDrive\\Desktop\\b07\\assignment\\src\\assignment\\Obj_sample.obj");
		assertEquals(meshOne.hashCode(), meshTwo.hashCode());
	}
	
	@Test
	void TestWrongFileMesh() {
		Mesh mesh = new Mesh();
		mesh.setReader(new OBJMeshReader());
		mesh.readFromFile("C:\\Users\\jacky\\OneDrive\\Desktop\\b07\\assignment\\src\\assignment\\foo.txt");
		assertFalse(mesh.polygons instanceof HashSet<Polygon>);
	}
	
	@Test
	void TestEqualsMesh() {
		Mesh meshOne = new Mesh();
		Mesh meshTwo = new Mesh();
		meshOne.setReader(new OBJMeshReader());
		meshTwo.setReader(new OBJMeshReader());
		meshOne.readFromFile("C:\\Users\\jacky\\OneDrive\\Desktop\\b07\\assignment\\src\\assignment\\Obj_sample.obj");
		meshTwo.readFromFile("C:\\Users\\jacky\\OneDrive\\Desktop\\b07\\assignment\\src\\assignment\\Obj_sample.obj");
		assertTrue(meshOne.equals(meshTwo));
	}
	
	@Test
	void TestEqualsMeshObj() {
		Mesh meshOne = new Mesh();
		Object meshTwo = new Object();
		assertFalse(meshOne.equals(meshTwo));
	}
	
	@Test
	void TestNotEqualsMesh() {
		Mesh meshOne = new Mesh();
		Mesh meshTwo = new Mesh();
		meshOne.setReader(new OBJMeshReader());
		meshTwo.setReader(new OBJMeshReader());
		meshOne.readFromFile("C:\\Users\\jacky\\OneDrive\\Desktop\\b07\\assignment\\src\\assignment\\Obj_sample.obj");
		meshTwo.readFromFile("C:\\Users\\jacky\\OneDrive\\Desktop\\b07\\assignment\\src\\assignment\\Obj_sample.obj");
		meshTwo.rotateZAxis(Math.toRadians(60));
		assertFalse(meshOne.equals(meshTwo));
	}
	
	@Test
	void TestNotEqualsMeshLength() {
		Mesh meshOne = new Mesh();
		Mesh meshTwo = new Mesh();
		meshOne.setReader(new OBJMeshReader());
		meshTwo.setReader(new OBJMeshReader());
		meshOne.readFromFile("C:\\Users\\jacky\\OneDrive\\Desktop\\b07\\assignment\\src\\assignment\\Obj_sample.obj");
		meshTwo.readFromFile("C:\\Users\\jacky\\OneDrive\\Desktop\\b07\\assignment\\src\\assignment\\Obj_sample_two.obj");
		assertFalse(meshOne.equals(meshTwo));
	}
	
	@Test
	void TestMeshReaderWrongFormat() {
		Mesh mesh = new Mesh();
		mesh.setReader(new OBJMeshReader());
		mesh.readFromFile("C:\\Users\\jacky\\OneDrive\\Desktop\\b07\\assignment\\src\\assignment\\Obj_sample_three.obj");
		assertEquals(mesh.polygons, null);
	}
	
	@Test
	void TestMeshWriterOBJ() throws FileNotFoundException {
		Mesh mesh = new Mesh();
		mesh.setWriter(new OBJMeshWriter());
		Vertex a = new Vertex(1.0, 2.0, 3.0);
		Vertex b = new Vertex(2.0, 1.0, 3.0);
		Vertex c = new Vertex(3.0, 1.0, 2.0);
		Vertex d = new Vertex(1.0, 2.0, 3.0);
		Vertex e = new Vertex(2.0, 1.0, 3.0);
		Vertex f = new Vertex(3.0, 1.0, 2.0);
		LinkedHashSet <Vertex> verticesOne = new LinkedHashSet<Vertex>();
		verticesOne.add(b);
		verticesOne.add(c);
		verticesOne.add(a);
		LinkedHashSet <Vertex> verticesTwo = new LinkedHashSet<Vertex>();
		verticesTwo.add(d);
		verticesTwo.add(e);
		verticesTwo.add(f);
		Polygon newOne = new Polygon(verticesOne);
		Polygon newTwo = new Polygon(verticesTwo);
		newOne.rotateYAxis(Math.toRadians(180.0));
		HashSet<Polygon> polygons = new HashSet<Polygon>();
		polygons.add(newOne);
		polygons.add(newTwo);
		mesh.polygons = polygons;
		mesh.writeToFile("C:\\Users\\jacky\\OneDrive\\Desktop\\b07\\assignment\\src\\assignment\\Obj_sample_four.obj");
		File file = new File("C:\\Users\\jacky\\OneDrive\\Desktop\\b07\\assignment\\src\\assignment\\Obj_sample_four.obj");
		assertTrue(file.exists());
	}
	
	@Test
	void TestMeshWriterOBJFormat() throws FileNotFoundException {
		Mesh mesh = new Mesh();
		mesh.setWriter(new OFFMeshWriter());
		Vertex a = new Vertex(1.0, 2.0, 3.0);
		Vertex b = new Vertex(2.0, 1.0, 3.0);
		Vertex c = new Vertex(3.0, 1.0, 2.0);
		Vertex d = new Vertex(1.0, 2.0, 3.0);
		Vertex e = new Vertex(2.0, 1.0, 3.0);
		Vertex f = new Vertex(3.0, 1.0, 2.0);
		LinkedHashSet <Vertex> verticesOne = new LinkedHashSet<Vertex>();
		verticesOne.add(b);
		verticesOne.add(c);
		verticesOne.add(a);
		LinkedHashSet <Vertex> verticesTwo = new LinkedHashSet<Vertex>();
		verticesTwo.add(d);
		verticesTwo.add(e);
		verticesTwo.add(f);
		Polygon newOne = new Polygon(verticesOne);
		Polygon newTwo = new Polygon(verticesTwo);
		newOne.rotateYAxis(Math.toRadians(180.0));
		HashSet<Polygon> polygons = new HashSet<Polygon>();
		polygons.add(newOne);
		polygons.add(newTwo);
		mesh.polygons = polygons;
		mesh.writeToFile("C:\\Users\\jacky\\OneDrive\\Desktop\\b07\\assignment\\src\\assignment\\Obj_sample_five.mp4");
		File file = new File("C:\\Users\\jacky\\OneDrive\\Desktop\\b07\\assignment\\src\\assignment\\Obj_sample_five.obj");
		assertFalse(file.exists());
	}
	
	@Test
	void TestMeshWriterOFF() throws FileNotFoundException {
		Mesh mesh = new Mesh();
		mesh.setWriter(new OFFMeshWriter());
		Vertex a = new Vertex(1.0, 2.0, 3.0);
		Vertex b = new Vertex(2.0, 1.0, 3.0);
		Vertex c = new Vertex(3.0, 1.0, 2.0);
		Vertex d = new Vertex(1.0, 2.0, 3.0);
		Vertex e = new Vertex(2.0, 1.0, 3.0);
		Vertex f = new Vertex(3.0, 1.0, 2.0);
		LinkedHashSet <Vertex> verticesOne = new LinkedHashSet<Vertex>();
		verticesOne.add(b);
		verticesOne.add(c);
		verticesOne.add(a);
		LinkedHashSet <Vertex> verticesTwo = new LinkedHashSet<Vertex>();
		verticesTwo.add(d);
		verticesTwo.add(e);
		verticesTwo.add(f);
		Polygon newOne = new Polygon(verticesOne);
		Polygon newTwo = new Polygon(verticesTwo);
		newOne.rotateYAxis(Math.toRadians(180.0));
		HashSet<Polygon> polygons = new HashSet<Polygon>();
		polygons.add(newOne);
		polygons.add(newTwo);
		mesh.polygons = polygons;
		mesh.writeToFile("C:\\Users\\jacky\\OneDrive\\Desktop\\b07\\assignment\\src\\assignment\\Obj_sample_four.off");
		File file = new File("C:\\Users\\jacky\\OneDrive\\Desktop\\b07\\assignment\\src\\assignment\\Obj_sample_four.off");
		assertTrue(file.exists());
	}
	
	@Test
	void TestMeshWriterPLY() throws FileNotFoundException {
		Mesh mesh = new Mesh();
		mesh.setWriter(new PLYMeshWriter());
		Vertex a = new Vertex(1.0, 2.0, 3.0);
		Vertex b = new Vertex(2.0, 1.0, 3.0);
		Vertex c = new Vertex(3.0, 1.0, 2.0);
		Vertex d = new Vertex(1.0, 2.0, 3.0);
		Vertex e = new Vertex(2.0, 1.0, 3.0);
		Vertex f = new Vertex(3.0, 1.0, 2.0);
		LinkedHashSet <Vertex> verticesOne = new LinkedHashSet<Vertex>();
		verticesOne.add(b);
		verticesOne.add(c);
		verticesOne.add(a);
		LinkedHashSet <Vertex> verticesTwo = new LinkedHashSet<Vertex>();
		verticesTwo.add(d);
		verticesTwo.add(e);
		verticesTwo.add(f);
		Polygon newOne = new Polygon(verticesOne);
		Polygon newTwo = new Polygon(verticesTwo);
		newOne.rotateYAxis(Math.toRadians(180.0));
		HashSet<Polygon> polygons = new HashSet<Polygon>();
		polygons.add(newOne);
		polygons.add(newTwo);
		mesh.polygons = polygons;
		mesh.writeToFile("C:\\Users\\jacky\\OneDrive\\Desktop\\b07\\assignment\\src\\assignment\\Obj_sample_four.ply");
		File file = new File("C:\\Users\\jacky\\OneDrive\\Desktop\\b07\\assignment\\src\\assignment\\Obj_sample_four.ply");
		assertTrue(file.exists());
	}
	
	@Test
	void TestMeshWriterPLYWrongFormat() throws FileNotFoundException {
		Mesh mesh = new Mesh();
		mesh.setWriter(new PLYMeshWriter());
		Vertex a = new Vertex(1.0, 2.0, 3.0);
		Vertex b = new Vertex(2.0, 1.0, 3.0);
		Vertex c = new Vertex(3.0, 1.0, 2.0);
		Vertex d = new Vertex(1.0, 2.0, 3.0);
		Vertex e = new Vertex(2.0, 1.0, 3.0);
		Vertex f = new Vertex(3.0, 1.0, 2.0);
		LinkedHashSet <Vertex> verticesOne = new LinkedHashSet<Vertex>();
		verticesOne.add(b);
		verticesOne.add(c);
		verticesOne.add(a);
		LinkedHashSet <Vertex> verticesTwo = new LinkedHashSet<Vertex>();
		verticesTwo.add(d);
		verticesTwo.add(e);
		verticesTwo.add(f);
		Polygon newOne = new Polygon(verticesOne);
		Polygon newTwo = new Polygon(verticesTwo);
		newOne.rotateYAxis(Math.toRadians(180.0));
		HashSet<Polygon> polygons = new HashSet<Polygon>();
		polygons.add(newOne);
		polygons.add(newTwo);
		mesh.polygons = polygons;
		mesh.writeToFile("C:\\Users\\jacky\\OneDrive\\Desktop\\b07\\assignment\\src\\assignment\\Obj_sample_six.mp4");
		File file = new File("C:\\Users\\jacky\\OneDrive\\Desktop\\b07\\assignment\\src\\assignment\\Obj_sample_six.ply");
		assertFalse(file.exists());
	}
	
	@Test
	void TestMeshWriterOFFWrongFormat() throws FileNotFoundException {
		Mesh mesh = new Mesh();
		mesh.setWriter(new OFFMeshWriter());
		Vertex a = new Vertex(1.0, 2.0, 3.0);
		Vertex b = new Vertex(2.0, 1.0, 3.0);
		Vertex c = new Vertex(3.0, 1.0, 2.0);
		Vertex d = new Vertex(1.0, 2.0, 3.0);
		Vertex e = new Vertex(2.0, 1.0, 3.0);
		Vertex f = new Vertex(3.0, 1.0, 2.0);
		LinkedHashSet <Vertex> verticesOne = new LinkedHashSet<Vertex>();
		verticesOne.add(b);
		verticesOne.add(c);
		verticesOne.add(a);
		LinkedHashSet <Vertex> verticesTwo = new LinkedHashSet<Vertex>();
		verticesTwo.add(d);
		verticesTwo.add(e);
		verticesTwo.add(f);
		Polygon newOne = new Polygon(verticesOne);
		Polygon newTwo = new Polygon(verticesTwo);
		newOne.rotateYAxis(Math.toRadians(180.0));
		HashSet<Polygon> polygons = new HashSet<Polygon>();
		polygons.add(newOne);
		polygons.add(newTwo);
		mesh.polygons = polygons;
		mesh.writeToFile("C:\\Users\\jacky\\OneDrive\\Desktop\\b07\\assignment\\src\\assignment\\Obj_sample_seven.mp4");
		File file = new File("C:\\Users\\jacky\\OneDrive\\Desktop\\b07\\assignment\\src\\assignment\\Obj_sample_seven.off");
		assertFalse(file.exists());
	}
	
	@Test
	void TestMeshWriterOFFWrongInstance() throws FileNotFoundException {
		Mesh mesh = new Mesh();
		mesh.setWriter(new PLYMeshWriter());
		Vertex a = new Vertex(1.0, 2.0, 3.0);
		Vertex b = new Vertex(2.0, 1.0, 3.0);
		Vertex c = new Vertex(3.0, 1.0, 2.0);
		Vertex d = new Vertex(1.0, 2.0, 3.0);
		Vertex e = new Vertex(2.0, 1.0, 3.0);
		Vertex f = new Vertex(3.0, 1.0, 2.0);
		LinkedHashSet <Vertex> verticesOne = new LinkedHashSet<Vertex>();
		verticesOne.add(b);
		verticesOne.add(c);
		verticesOne.add(a);
		LinkedHashSet <Vertex> verticesTwo = new LinkedHashSet<Vertex>();
		verticesTwo.add(d);
		verticesTwo.add(e);
		verticesTwo.add(f);
		Polygon newOne = new Polygon(verticesOne);
		Polygon newTwo = new Polygon(verticesTwo);
		newOne.rotateYAxis(Math.toRadians(180.0));
		HashSet<Polygon> polygons = new HashSet<Polygon>();
		polygons.add(newOne);
		polygons.add(newTwo);
		mesh.polygons = polygons;
		mesh.writeToFile("C:\\Users\\jacky\\OneDrive\\Desktop\\b07\\assignment\\src\\assignment\\Obj_sample_eight.off");
		File file = new File("C:\\Users\\jacky\\OneDrive\\Desktop\\b07\\assignment\\src\\assignment\\Obj_sample_eight.off");
		assertFalse(file.exists());
	}
	
	@Test
	void TestMeshWriterPLYWrongInstance() throws FileNotFoundException {
		Mesh mesh = new Mesh();
		mesh.setWriter(new OFFMeshWriter());
		Vertex a = new Vertex(1.0, 2.0, 3.0);
		Vertex b = new Vertex(2.0, 1.0, 3.0);
		Vertex c = new Vertex(3.0, 1.0, 2.0);
		Vertex d = new Vertex(1.0, 2.0, 3.0);
		Vertex e = new Vertex(2.0, 1.0, 3.0);
		Vertex f = new Vertex(3.0, 1.0, 2.0);
		LinkedHashSet <Vertex> verticesOne = new LinkedHashSet<Vertex>();
		verticesOne.add(b);
		verticesOne.add(c);
		verticesOne.add(a);
		LinkedHashSet <Vertex> verticesTwo = new LinkedHashSet<Vertex>();
		verticesTwo.add(d);
		verticesTwo.add(e);
		verticesTwo.add(f);
		Polygon newOne = new Polygon(verticesOne);
		Polygon newTwo = new Polygon(verticesTwo);
		newOne.rotateYAxis(Math.toRadians(180.0));
		HashSet<Polygon> polygons = new HashSet<Polygon>();
		polygons.add(newOne);
		polygons.add(newTwo);
		mesh.polygons = polygons;
		mesh.writeToFile("C:\\Users\\jacky\\OneDrive\\Desktop\\b07\\assignment\\src\\assignment\\Obj_sample_ten.ply");
		File file = new File("C:\\Users\\jacky\\OneDrive\\Desktop\\b07\\assignment\\src\\assignment\\Obj_sample_ten.ply");
		assertFalse(file.exists());
	}
	
	@Test
	void TestMeshWriterOBJWrongInstance() throws FileNotFoundException{
		Mesh mesh = new Mesh();
		mesh.setWriter(new PLYMeshWriter());
		Vertex a = new Vertex(1.0, 2.0, 3.0);
		Vertex b = new Vertex(2.0, 1.0, 3.0);
		Vertex c = new Vertex(3.0, 1.0, 2.0);
		Vertex d = new Vertex(1.0, 2.0, 3.0);
		Vertex e = new Vertex(2.0, 1.0, 3.0);
		Vertex f = new Vertex(3.0, 1.0, 2.0);
		LinkedHashSet <Vertex> verticesOne = new LinkedHashSet<Vertex>();
		verticesOne.add(b);
		verticesOne.add(c);
		verticesOne.add(a);
		LinkedHashSet <Vertex> verticesTwo = new LinkedHashSet<Vertex>();
		verticesTwo.add(d);
		verticesTwo.add(e);
		verticesTwo.add(f);
		Polygon newOne = new Polygon(verticesOne);
		Polygon newTwo = new Polygon(verticesTwo);
		newOne.rotateYAxis(Math.toRadians(180.0));
		HashSet<Polygon> polygons = new HashSet<Polygon>();
		polygons.add(newOne);
		polygons.add(newTwo);
		mesh.polygons = polygons;
		mesh.writeToFile("C:\\Users\\jacky\\OneDrive\\Desktop\\b07\\assignment\\src\\assignment\\Obj_sample_eleven.obj");
		File file = new File("C:\\Users\\jacky\\OneDrive\\Desktop\\b07\\assignment\\src\\assignment\\Obj_sample_eleven.obj");
		assertFalse(file.exists());
	}
	
	@Test
	void TestOFFMeshReader() {
		Mesh meshOFF = new Mesh();
		meshOFF.setReader(new OFFMeshReader());
		meshOFF.readFromFile("C:\\Users\\jacky\\OneDrive\\Desktop\\b07\\assignment\\src\\assignment\\Obj_sample_four.off");
		Mesh meshOBJ = new Mesh();
		meshOBJ.setReader(new OBJMeshReader());
		meshOBJ.readFromFile("C:\\Users\\jacky\\OneDrive\\Desktop\\b07\\assignment\\src\\assignment\\Obj_sample_four.obj");
		assertEquals(meshOFF, meshOBJ);
	}
	
	@Test
	void TestPLYMeshReader() {
		Mesh meshPLY = new Mesh();
		meshPLY.setReader(new PLYMeshReader());
		meshPLY.readFromFile("C:\\Users\\jacky\\OneDrive\\Desktop\\b07\\assignment\\src\\assignment\\Obj_sample_four.ply");
		Mesh meshOBJ = new Mesh();
		meshOBJ.setReader(new OBJMeshReader());
		meshOBJ.readFromFile("C:\\Users\\jacky\\OneDrive\\Desktop\\b07\\assignment\\src\\assignment\\Obj_sample_four.obj");
		assertEquals(meshPLY, meshOBJ);
	}
	
	@Test
	void TestOBJMeshReaderNoFile() {
		Mesh meshOBJ = new Mesh();
		meshOBJ.setReader(new OBJMeshReader());
		meshOBJ.readFromFile("C:\\Users\\jacky\\OneDrive\\Desktop\\b07\\assignment\\src\\assignment\\Obj_sam.off");
		Mesh meshOFF = new Mesh();
		meshOFF.setReader(new OFFMeshReader());
		meshOFF.readFromFile("C:\\Users\\jacky\\OneDrive\\Desktop\\b07\\assignment\\src\\assignment\\Obj_sample_four.off");
		assertNotSame(meshOBJ, meshOFF);
	}
	
	@Test
	void TestOFFMeshReaderNoFile() {
		Mesh meshOFF = new Mesh();
		meshOFF.setReader(new OFFMeshReader());
		meshOFF.readFromFile("C:\\Users\\jacky\\OneDrive\\Desktop\\b07\\assignment\\src\\assignment\\Obj_sam.off");
		Mesh meshOBJ = new Mesh();
		meshOBJ.setReader(new OBJMeshReader());
		meshOBJ.readFromFile("C:\\Users\\jacky\\OneDrive\\Desktop\\b07\\assignment\\src\\assignment\\Obj_sample_four.obj");
		assertNotSame(meshOFF, meshOBJ);
	}
	
	@Test
	void TestPLYMeshReaderNoFile() {
		Mesh meshPLY = new Mesh();
		meshPLY.setReader(new PLYMeshReader());
		meshPLY.readFromFile("C:\\Users\\jacky\\OneDrive\\Desktop\\b07\\assignment\\src\\assignment\\Obj_sam.ply");
		Mesh meshOBJ = new Mesh();
		meshOBJ.setReader(new OBJMeshReader());
		meshOBJ.readFromFile("C:\\Users\\jacky\\OneDrive\\Desktop\\b07\\assignment\\src\\assignment\\Obj_sample_four.obj");
		assertNotSame(meshPLY, meshOBJ);
	}
	
	@Test
	void TestOFFMeshReaderViolate() {
		Mesh meshOFF = new Mesh();
		meshOFF.setReader(new OFFMeshReader());
		meshOFF.readFromFile("C:\\Users\\jacky\\OneDrive\\Desktop\\b07\\assignment\\src\\assignment\\Obj_Violate.off");
		assertNull(meshOFF.polygons);
	}
	
	@Test
	void TestPLYMeshReaderViolate() {
		Mesh meshPLY = new Mesh();
		meshPLY.setReader(new PLYMeshReader());
		meshPLY.readFromFile("C:\\Users\\jacky\\OneDrive\\Desktop\\b07\\assignment\\src\\assignment\\Obj_Violate.ply");
		assertNull(meshPLY.polygons);
	}
	
	@Test
	void TestPLYMeshReaderViolateTwo() {
		Mesh meshPLY = new Mesh();
		meshPLY.setReader(new PLYMeshReader());
		meshPLY.readFromFile("C:\\Users\\jacky\\OneDrive\\Desktop\\b07\\assignment\\src\\assignment\\Obj_Violate_Two.ply");
		assertNull(meshPLY.polygons);
	}
	
	@Test
	void TestPLYMeshReaderViolateThree() {
		Mesh meshPLY = new Mesh();
		meshPLY.setReader(new PLYMeshReader());
		meshPLY.readFromFile("C:\\Users\\jacky\\OneDrive\\Desktop\\b07\\assignment\\src\\assignment\\Obj_Violate_Three.ply");
		assertNull(meshPLY.polygons);
	}
	
	@Test
	void TestPLYMeshReaderViolateFour() {
		Mesh meshPLY = new Mesh();
		meshPLY.setReader(new PLYMeshReader());
		meshPLY.readFromFile("C:\\Users\\jacky\\OneDrive\\Desktop\\b07\\assignment\\src\\assignment\\Obj_Violate_Four.ply");
		assertNull(meshPLY.polygons);
	}
	
	@Test
	void TestOFFMeshReaderViolateFive() {
		Mesh meshOFF = new Mesh();
		meshOFF.setReader(new OFFMeshReader());
		meshOFF.readFromFile("C:\\Users\\jacky\\OneDrive\\Desktop\\b07\\assignment\\src\\assignment\\Obj_Violate_Five.off");
		assertNull(meshOFF.polygons);
	}
	
	@Test
	void TestOFFMeshReaderViolateSix() {
		Mesh meshOFF = new Mesh();
		meshOFF.setReader(new OFFMeshReader());
		meshOFF.readFromFile("C:\\Users\\jacky\\OneDrive\\Desktop\\b07\\assignment\\src\\assignment\\Obj_Violate_Six.off");
		assertNull(meshOFF.polygons);
	}
	
	@Test
	void TestOFFMeshReaderViolateSeven() {
		Mesh meshOFF = new Mesh();
		meshOFF.setReader(new OFFMeshReader());
		meshOFF.readFromFile("C:\\Users\\jacky\\OneDrive\\Desktop\\b07\\assignment\\src\\assignment\\Obj_Violate_Seven.off");
		assertNull(meshOFF.polygons);
	}
	
	@Test
	void TestOFFMeshReaderViolateEight() {
		Mesh meshOFF = new Mesh();
		meshOFF.setReader(new OFFMeshReader());
		meshOFF.readFromFile("C:\\Users\\jacky\\OneDrive\\Desktop\\b07\\assignment\\src\\assignment\\Obj_Violate_Eight.off");
		assertNull(meshOFF.polygons);
	}
	
	@Test
	void TestOFFMeshReaderViolateNine() {
		Mesh meshOFF = new Mesh();
		meshOFF.setReader(new OFFMeshReader());
		meshOFF.readFromFile("C:\\Users\\jacky\\OneDrive\\Desktop\\b07\\assignment\\src\\assignment\\Obj_Violate_Nine.off");
		assertNull(meshOFF.polygons);
	}
	
}
