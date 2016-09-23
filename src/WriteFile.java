import java.util.Formatter;

public class WriteFile {
	
	private Formatter file;
	
	public void openFile(){
		
		try{
			file = new Formatter("Highscore.txt");
		} 
		catch(Exception e) {
			e.printStackTrace();
		}
	}

	public void addHighscore(int highscore){
		file.format("%s ", highscore);
	}
	
	public void addNameHighscore(String name){
		file.format("%s", name);
	}
	
	public void closeFile(){
		file.close();
	}
}

