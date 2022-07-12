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

public class OFFMeshReader implements MeshReader{

	@Override
	public HashSet<Polygon> read(String filename) throws WrongFileFormatException {
		File file = new File(filename);
		HashSet<Polygon> newPolygonSet = new HashSet<Polygon>();
		ArrayList<double []> vertexList = new ArrayList<double []>();
		if(file.exists()) {System.out.println("file Exists in a valid directory, the file will now be written.");}
		else {throw new WrongFileFormatException("Not a valid file directory");}

		BufferedReader objReader = null;
		Pattern Regex = Pattern.compile("(\\s*OFF\\s*|\\s*(\\s*[\\d]+){4,}\\s*|\\s*((\\s*-?[\\d]+\\.?[\\d]*)(E-?\\d+)?){3}\\s*)\\s*");
		Pattern verticesRegex = Pattern.compile("\\s*((\\s*-?[\\d]+\\.?[\\d]*)(E-?\\d+)?){3}\\s*");
		Pattern facesRegex = Pattern.compile("\\s*(\\s*[\\d]+){4,}\\s*");
		Pattern secondRegex = Pattern.compile("\\s*(([\\d]+\\s*){2}0\\s*)\\s*");
		Pattern whiteSpace = Pattern.compile("\\s*");
		int y = 0; int j = 0; int lineNum = 0;
		
		try {
			objReader = new BufferedReader(new FileReader(file));
			for(String currLine = objReader.readLine(); currLine != null; currLine = objReader.readLine()) {
				//System.out.println(currLine);
				Matcher Matches = Regex.matcher(currLine);
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
				Matcher verticesMatcher = verticesRegex.matcher(currLine);
				Matcher facesMatcher = facesRegex.matcher(currLine);
				Matcher secondMatcher = secondRegex.matcher(currLine);
				if(secondMatcher.matches()) {
					y = Integer.parseInt(wordList.get(0));
					j = Integer.parseInt(wordList.get(1));
				}
				else if(!(secondMatcher.matches()) && lineNum == 1){
					throw new WrongFileFormatException("File format does not match");
				}
				else if(verticesMatcher.matches()) {
					double [] currentArray = {Double.parseDouble(wordList.get(0)), Double.parseDouble(wordList.get(1)), Double.parseDouble(wordList.get(2))};
					vertexList.add(currentArray);
				}
				else if(facesMatcher.matches()){
					ArrayList<String> l = new ArrayList<String>();
					l.addAll(wordList);
					l.remove(0);
					l.remove(l.size() - 1);
					l.remove(l.size() - 1);
					l.remove(l.size() - 1);
					if(Integer.parseInt(wordList.get(0)) > l.size()) {
						throw new WrongFileFormatException("File format does not match");
					}
					int color = 0;
					if (wordList.size() < 7) {
						color = 0;
					}
					else {
						color = 3;
						for(int i = 4; i < wordList.size(); i++) {
							if(Integer.parseInt(wordList.get(i)) > 255) {
								throw new WrongFileFormatException("File format does not match");
							}
						}
					}
					
					if(!(y == vertexList.size())){
						throw new WrongFileFormatException("File format does not match");
					}
					
					LinkedHashSet<Vertex> a = new LinkedHashSet <Vertex>();
					for(int i = 1; i < wordList.size() - color; i++) {
						double [] newArr = vertexList.get(Integer.parseInt(wordList.get(i)));
						Vertex newVertex = new Vertex(newArr[0], newArr[1], newArr[2]);
						a.add(newVertex);
					}
					/*double [] newArr1 = vertexList.get(Integer.parseInt(wordList.get(1)));
					double [] newArr2 = vertexList.get(Integer.parseInt(wordList.get(2)));
					double [] newArr3 = vertexList.get(Integer.parseInt(wordList.get(3)));
					Vertex new1 = new Vertex(newArr1[0], newArr1[1], newArr1[2]);
					Vertex new2 = new Vertex(newArr2[0], newArr2[1], newArr2[2]);
					Vertex new3 = new Vertex(newArr3[0], newArr3[1], newArr3[2]);
					a.add(new1);
					a.add(new2);
					a.add(new3);*/
					Polygon finish = new Polygon(a);
					newPolygonSet.add(finish);
				}
				lineNum++;
			}
		if(!(j == newPolygonSet.size())) {
			throw new WrongFileFormatException("File format does not match");
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