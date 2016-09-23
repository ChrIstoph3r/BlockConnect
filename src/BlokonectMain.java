import java.awt.*;

import javax.swing.*;

public class BlokonectMain {

	final static String TITLE = "blokonect";
	
	final static int 
	FRAME_HEIGHT = 800, FRAME_WIDTH = 1050;
	
	static int 
	ROWS = 12, COLUMNS = 14;
	
	public static int 
	clicks = 15, clicksLeft = 15, 
	amountOfBubbleColors = 3, 
	score = 0, level = 1;

	static MapDesign mapDesign;
	static Component mapDrawer;
	static JFrame frame;
	static JLabel textLabel;
	
	public static void main(String[] args)  {

		optionsPopUp();
		makeFrame();
		
		gameLoop(true);
	}
	
	private static void gameLoop(boolean newLevel){
		
		if(newLevel){
			mapDesign = new MapDesign(ROWS, COLUMNS);
			mapDesign.setLevel(level);
			mapDesign.createNewMap();
		}else{
			mapDesign.burstBubbleLink();
			mapDesign.setRightLeftTopBottomToBubbles();
		}
		
		mapDrawer = new MapDrawer(mapDesign.getBubblesOnMap() );
		addComponentsToFrame(mapDrawer);
		
		MouseWhisperer.onClick(mapDrawer);
	}
	
	 /*
	  *  Response == 0 ==> Play Now! 
	  *  Response == 1 ==> Custom game 
	  *  Response == 2 ==> high score
	  *  Response == -1 || 3 ==> Escape/Cancel.
	  */

	public static void optionsPopUp() {

	    String[] options = {"Play now!", "Custom game", "Highscore"};
	    int response = optionsPrompt("Play now or customise it!", options, "Options");

   	    if(response == 1){
    	
	    	String ROWSinput = inputPrompt("Enter the amount of rows u want! (no more than 12!");  
	    	String COLUMNSinput = inputPrompt( "Enter the amount of columns u want! (No more than 14!)" );
	    	String clicksPrLvlInput = inputPrompt("Enter the amount of clicks u want per level!");
	    	
	    	ROWS = Integer.parseInt (ROWSinput);
			COLUMNS = Integer.parseInt (COLUMNSinput);
			clicks = Integer.parseInt (clicksPrLvlInput);
			clicksLeft = clicks;

	    }else if(response == 2){
	    	
	    	String[] highscore = readHighscore();
	    	message(" Name: " + highscore[0] 
	    		  + "\n Score: " + highscore[1], " Highscore");
	    }
	}
	
	public static void makeFrame(){
		
		frame = new JFrame();
		
		frame.setSize(FRAME_WIDTH, FRAME_HEIGHT);
		frame.setResizable(false);
		frame.setTitle(TITLE);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	public static void addComponentsToFrame(Component map){
	
		textLabel = new JLabel("Clicks left: " + clicksLeft + " Score: " + score + "       ");
		frame.getContentPane().setBackground(Color.white);
		frame.add(map);
		frame.add(textLabel, BorderLayout.EAST);
		frame.setVisible(true);	
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
	
	static void validClick(int x, int y){
		
		Block clickedBubble = mapDesign.getBubblesOnMap().get(x + y*COLUMNS);
		
		int linkSize = mapDesign.getLinkSize(clickedBubble);
		
		if(linkSize == 1) return;
		
		addScore(linkSize);
		
		removeComponentsFromFrame();
		
		gameLoop(false);	
	}
	
	public static void noMoreTurns(){
		
		removeComponentsFromFrame();
		
		if (level < 3){
   			initNewMapVars();
   			nextLevelSequence();
   			gameLoop(true);
   		 }else{
   			initNewMapVars();
   			endOfGameSequence();
   			resetGame();
   		 }		
	}
	
	public static boolean itsNoMoreTurns(){
		if(clicksLeft < 1)return true;
		return false;
	}
	
	public static void endOfGameSequence(){
		
		String[] highScore = readHighscore();
		int highScorePoints = Integer.parseInt(highScore[1]);
		
		if(score > highScorePoints){
			
			WriteFile write = new WriteFile();
			
			write.openFile();
			write.addHighscore(score);
		
			String nameHighScore = inputPrompt(" Congrats! U got a new highscore of " + score + "!" 
											  + "\n Write ur name here!");
			write.addNameHighscore(nameHighScore);
			
			write.closeFile();
		}else {
			
			message(" You got a score of " + score + "... "
					+ "\n It's most likely a super lame score... "
					+ "\n Take a look at the highscore: " + highScorePoints 
					+ "\n That's the real deal right there. "
					+ "\n It's OWNED by " + highScore[0], " Game over");
		}
	}
	
	public static void nextLevelSequence(){
		
		level++;
		message(" You've proceeded to level " + level + "!"
			  + "\n Play your bubbles right, and the reward will be much greater...", " Next level" );
	}
	
	public static void initNewMapVars(){
		
		clicksLeft = clicks;
	}
	
	public static void resetGame(){

		level = 1;
		score = 0;
		
		String[] args = new String[1]; 
		main(args);
	}
	
	public static void addScore(int linkedBubblesAmount){
		
		clicksLeft--;
		
		int exponent;
    	
		if(level > 1)
			exponent = 2; 
		else
			exponent = 1;
				
		int baseScore = linkedBubblesAmount*level;
		
		score += Math.pow(baseScore, exponent);
	}
	
	private static void removeComponentsFromFrame(){
		
		frame.remove(mapDrawer);
		frame.remove(textLabel);
	}
	
	private static void message(String msg, String title){
		
		JOptionPane.showMessageDialog(null, msg, title, JOptionPane.PLAIN_MESSAGE);
	}
	
	private static int optionsPrompt(String txt, String[] options, String title){
		
		return JOptionPane.showOptionDialog(null, txt, title,
			    							JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, 
			    							null, options, options[0]);
	}
	
	private static String inputPrompt(String txt){
		
		return JOptionPane.showInputDialog (null, txt, " Input", JOptionPane.QUESTION_MESSAGE );
	}
	
}