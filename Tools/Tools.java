package Tools;
import javax.swing.JOptionPane;

import FileScanner.ReadFile;
import FileScanner.WriteFile;
import GameCore.Block;

public class Tools {

	public static int
	ROWS = 12, COLUMNS = 14,
	initialClicks = 15,
	amountBlockColors = 3,
	score = 0, level = 1,
	clicksLeft = initialClicks;

	public static boolean isColorEqual(Block block1, Block block2){

		if(block2 == null || block1 == null) return false;
		if(block1.getColor().equals(block2.getColor()))return true;
		return false;
	}

	public static String[] readHighscore() {

		ReadFile read = new ReadFile();

		read.openFile();
		read.checkIfExists();
		read.createScannner();
		read.readFile();
		read.close();

		String[] highscore = {read.getName(), read.getHighScore()};

		return highscore;
	}

	public static void writeNewHighScore(int score){

		WriteFile write = new WriteFile();

		write.openFile();
		write.addHighscore(score);

		String nameHighScore = inputPrompt(" Congrats! U got a new highscore of " + score + "!"
										  + "\n Write ur name here!");
		write.addNameHighscore(nameHighScore);

		write.closeFile();
	}

	public static void message(String msg, String title){

		JOptionPane.showMessageDialog(null, msg, title, JOptionPane.PLAIN_MESSAGE);
	}

	public static int optionsPrompt(String txt, String[] options, String title){

		return JOptionPane.showOptionDialog(null, txt, title, JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, options, options[0]);
	}

	public static String inputPrompt(String txt){

		return JOptionPane.showInputDialog (null, txt, " Input", JOptionPane.QUESTION_MESSAGE );
	}
}
