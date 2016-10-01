
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;


public class ReadFile {
	
	private Scanner scanner;
	private File file;
	String highscore = "0";
	String name = "";
	
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
			
			highscore  = scanner.next();
			
			while(scanner.hasNext()) {
				name += scanner.next() + " ";
			}
		}
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



