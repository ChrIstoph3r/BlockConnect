import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;

import javax.swing.JFrame;
import javax.swing.JLabel;

public class BlockConnectMain extends Tools{

	final static String TITLE = "BlockConnect";
	
	final static int 
	FRAME_HEIGHT = 800, FRAME_WIDTH = 1050;
	
	static int 
	ROWS = 12, COLUMNS = 14;
	
	public static int 
	initialClicks = 15, clicksLeft = 15, 
	amountBlockColors = 3, 
	score = 0, level = 1;

	static MapDesign mapDesign;
	static Component mapDrawer;
	static JFrame frame;
	static JLabel textLabel;
	
	public static void main(String[] args)  {

		optionsPopUp();
		makeJFrame();
		
		gameLoop(true, 0);
	}

	public static void optionsPopUp() {

	    String[] options = {"Play now!", "Custom game", "Highscore"};
	    int response = optionsPrompt("Play now or customise it!", options, "Options");

  	    if(response == 1){
   	
	    	String ROWSinput = inputPrompt("Enter the amount of rows u want :D (no more than 12!");  
	    	String COLUMNSinput = inputPrompt( "Enter the amount of columns u want =) (No more than 14!)" );
	    	String clicksPrLvlInput = inputPrompt("Enter the amount of initialClicks u want per level :) ");
	    	
	    	ROWS = Integer.parseInt (ROWSinput);
			COLUMNS = Integer.parseInt (COLUMNSinput);
			initialClicks = Integer.parseInt (clicksPrLvlInput);
			clicksLeft = initialClicks;

	    }else if(response == 2){
	    	
	    	String[] highscore = readHighscore();
	    	message(" Name: " + highscore[0] 
	    		  + "\n Score: " + highscore[1], " Highscore");
	    	optionsPopUp();
	    }
	}
	
	public static void makeJFrame(){
		
		frame = new JFrame();
		
		frame.setSize(FRAME_WIDTH, FRAME_HEIGHT);
		frame.setResizable(false);
		frame.setTitle(TITLE);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	private static void gameLoop(boolean newLevel, int linkSize){
		
		if(newLevel){
			mapDesign = new MapDesign(COLUMNS, ROWS);
			mapDesign.setLevel(level);
			mapDesign.createNewMap();
		}else{
			removeComponentsFromFrame();
			mapDesign.burstBlockLink();
			addScore(linkSize);
		}
		
		mapDrawer = new MapDrawer(mapDesign.getBlocksOnMap() );
		addComponentsToFrame(mapDrawer);
		
		if(clicksLeft < 1)noMoreTurns();
		
		MouseWhisperer.onClick(mapDrawer);
	}
	
	static void validClick(int x, int y){
		
		Block clickedBlock = mapDesign.getBlocksOnMap()[x][y];
		
		int linkSize = mapDesign.getLinkSize(clickedBlock);
		
		if(linkSize == 1) return;
		
		gameLoop(false, linkSize);	
	}
	
	public static void noMoreTurns(){
		
		clicksLeft = initialClicks;
		
		if (level < 3){
			nextLevelSequence();
			removeComponentsFromFrame();
   			gameLoop(true, 0);
   		 }else{
   			endOfGameSequence();
   			resetGame();
   		 }		
	}
	
	private static void endOfGameSequence(){
		
		String[] highScore = readHighscore();
		int highScorePoints = Integer.parseInt(highScore[1]);
		
		if(score > highScorePoints){
			
			writeNewHighScore(score);
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
			  + "\n Play your blocks right, and the reward will be much greater...", " Next level" );
	}
	
	public static void resetGame(){

		level = 1;
		score = 0;
		
		String[] args = new String[1]; 
		main(args);
	}
	
	public static void addScore(int linkedBubblesAmount){
		
		clicksLeft--;
		
		float exponent;
    	
		if(level > 1)
			exponent = 2; 
		else
			exponent = 1.5f;
				
		int baseScore = linkedBubblesAmount*(level + 1);
		
		score += Math.pow(baseScore, exponent);
	}
	
	private static void removeComponentsFromFrame(){
		
		frame.remove(mapDrawer);
		frame.remove(textLabel);
	}
	
	public static void addComponentsToFrame(Component map){
		
		textLabel = new JLabel("Clicks left: " + clicksLeft + " Score: " + score + "       ");
		frame.getContentPane().setBackground(Color.white);
		frame.add(map);
		frame.add(textLabel, BorderLayout.EAST);
		frame.setVisible(true);	
	}
}