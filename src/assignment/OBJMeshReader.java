package assignment;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class OBJMeshReader implements MeshReader{

	@Override
	public HashSet<Polygon> read(String filename) throws WrongFileFormatException{
		File file = new File(filename);
		HashSet<Polygon> newPolygonSet = new HashSet<Polygon>();
		ArrayList<double []> vertexList = new ArrayList<double []>();
		if(file.exists()) {System.out.println("file Exists in a valid directory, the file will now be read.");}
		else {throw new WrongFileFormatException("Not a valid file directory");}

		BufferedReader objReader = null;
		Pattern Regex = Pattern.compile("\\s*(\\s*v\\s*((\\s+-?[\\d]+\\.?[\\d]*)(E-?\\d+)?){3}|\\s*f\\s*(\\s+[\\d]+){3,})\\s*");//change the other file regexes to match
		Pattern verticesRegex = Pattern.compile("\\s*(\\s*v\\s*((\\s+-?[\\d]+\\.?[\\d]*)(E-?\\d+)?){3})\\s*");
		Pattern facesRegex = Pattern.compile("\\s*(\\s*f\\s*(\\s+[\\d]+){3,})\\s*");
		Pattern whiteSpace = Pattern.compile("\\s*");
		
		try {
			objReader = new BufferedReader(new FileReader(file));
			for(String currLine = objReader.readLine(); currLine != null; currLine = objReader.readLine()) {
				//System.out.println(currLine);
				Matcher Matches = Regex.matcher(currLine);
				Matcher facesMatcher = facesRegex.matcher(currLine);
				Matcher verticesMatcher = verticesRegex.matcher(currLine);
				if(!(Matches.matches())) {
					throw new WrongFileFormatException("File format does not match");
				}
				String [] wordListSpace = currLine.split(" ");
				ArrayList<String> wordList = new ArrayList<String>();
				for(String each:wordListSpace) {
					Matcher matchWhiteSpace = whiteSpace.matcher(each);
					if(!(matchWhiteSpace.matches())) {
						wordList.add(each);
					}
				}
				if(verticesMatcher.matches()) {
					double [] currentArray = {Double.parseDouble(wordList.get(1)), Double.parseDouble(wordList.get(2)), Double.parseDouble(wordList.get(3))};
					vertexList.add(currentArray);
				}
				else if (facesMatcher.matches()){
					for(int i = 1; i < wordList.size(); i++) {
						if(Integer.parseInt(wordList.get(i)) > vertexList.size()) {
							throw new WrongFileFormatException("File format does not match");
						}
					}
					LinkedHashSet<Vertex> a = new LinkedHashSet <Vertex>();
					
					for(int i = 1; i < wordList.size(); i++) {
						double [] newArr = vertexList.get(Integer.parseInt(wordList.get(i)) - 1);
						Vertex newVertex = new Vertex(newArr[0], newArr[1], newArr[2]);
						a.add(newVertex);
					}
					
					/*double [] newArr1 = vertexList.get(Integer.parseInt(wordList.get(1)) - 1);
					double [] newArr2 = vertexList.get(Integer.parseInt(wordList.get(2)) - 1);
					double [] newArr3 = vertexList.get(Integer.parseInt(wordList.get(3)) - 1);
					Vertex new1 = new Vertex(newArr1[0], newArr1[1], newArr1[2]);
					Vertex new2 = new Vertex(newArr2[0], newArr2[1], newArr2[2]);
					Vertex new3 = new Vertex(newArr3[0], newArr3[1], newArr3[2]);
					a.add(new1);
					a.add(new2);
					a.add(new3);*/
					Polygon finish = new Polygon(a);
					newPolygonSet.add(finish);
				}
			}
		} catch (FileNotFoundException e) {
			System.out.println(file.getAbsolutePath() + " does not exist");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			if(objReader != null) {
				try {
					objReader.close();
				} catch(IOException e){
					e.printStackTrace();
				}
			}
		}
		return newPolygonSet;
	}

}
