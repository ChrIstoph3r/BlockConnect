import java.io.*;
import java.util.*;

public class ReadFile {
	
	private Scanner scanner;
	private File file;
	String highscore = "0";
	String name = "";
	boolean hasContent = false;
	
	public void openFile(){
		
		file = new File("Highscore.txt");
	}
	
	public void checkIfExists(){
		
		try {
			file.createNewFile();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void createScannner(){
		
		try {
			scanner = new Scanner(file);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
		
	public void readFile(){
			
		if(scanner.hasNext()){
			
			hasContent = true;
			
			while(scanner.hasNext()) {
				highscore  = scanner.next();
				name = scanner.next();
			}
		}
	}
		
	public boolean hasContent(){
		return hasContent;
	}
		
	public String getName(){
		return name;
	}
	
	public String getHighScore(){
		return highscore;
	}
		
	public void close(){
		scanner.close();
	}
}



