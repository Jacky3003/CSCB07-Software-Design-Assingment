package assignment;

import java.io.FileNotFoundException;
import java.util.HashSet;

public interface MeshWriter {
	public abstract void write(String filename, HashSet<Polygon> polygons) throws WrongFileFormatException, FileNotFoundException;
}
