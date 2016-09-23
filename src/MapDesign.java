import java.awt.Color;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Random;


public class MapDesign extends Tools{
	
	final static int 
	WIDTH_CELL = 50, HEIGHT_CELL = 50;
	
	private int level;
	
	final int COLUMNS, ROWS;
	
	ArrayList<Block> bubblesOnMap;
	LinkedList<Block> clickedLink;
	
	public MapDesign(int ROWS, int COLUMNS){
		
		this.ROWS = ROWS;
		this.COLUMNS = COLUMNS;
		
		bubblesOnMap = new ArrayList<Block>(ROWS*COLUMNS);
	}
	
	public void burstBubble(Block bubble){
		
		setToTopColor(bubble);
	}	
	
	
	void setToTopColor(Block bubble){
		
		if(bubble.hasTop()){
			
			bubble.setColor(bubble.getTop().getColor());
			setToTopColor(bubble.getTop());
		}else{
			
			bubble.setColor(genColor());
		}
	}
	
	public ArrayList<Block> getBubblesOnMap(){
		return bubblesOnMap;
	}
	
	public void createNewMap() {
		
		Block prev = null;
		Block firstColumn = null;
		
		for(int j = 0; j < ROWS; j++){
			
			for (int i = 0; i < COLUMNS; i++){
				
				Block bubble = new Block(i, j, genColor());
				
				setSurroundingBubbles(bubble, firstColumn, prev);
				
				if(i == 0)firstColumn = bubble;
				
				bubblesOnMap.add(bubble);
				
				prev = bubble;
			}
		}
	}
	
	public void setLevel(int level){
		this.level = level;
	}
	
	public void setRightLeftTopBottomToBubbles(){
		
		Block prev = null;
		Block firstColumn = null;
		
		for(Block bubble : bubblesOnMap) {
			
			setSurroundingBubbles(bubble, firstColumn, prev);
			if(bubble.getPosX() == 0)firstColumn = bubble;
			prev = bubble;
		}	
	}
	
	private void setSurroundingBubbles(Block bubble, Block firstColumn, Block prev){
		
		int posX = bubble.getPosX();
		int posY = bubble.getPosY();
		
		if(posX != 0) {
			bubble.setLeft(prev);	
			bubble.getLeft().setRight(bubble);		
		}
		
		if(posY > 0) {
			
			if(posX == 0){
				bubble.setTop(firstColumn);
			}else{
				bubble.setTop(getTopBubble(bubble));
			}
	
			bubble.getTop().setBottom(bubble);
		}
	}
	
	private Block getTopBubble(Block bubble){
		
		return bubble.getLeft().getTop().getRight();
	}
		
	public Color genColor() {

		Color color = null;
		
		Random number = new Random();
		
		int amountColors = level + 2;
		
		int maxRandInt = amountColors;
		int minRandInt = 1;
		
		int randNumb = number.nextInt((maxRandInt-minRandInt) + 1) + minRandInt;
	
		switch(randNumb){
			
			case 1:
				color = Color.GREEN;
				break;
			case 2: 
				color = Color.BLUE;
				break;
			case 3:
				color = Color.RED;
				break;
			case 4:
				color = Color.ORANGE;
				break;
			default:
				color = Color.DARK_GRAY;
		}
		
		return color;
	}

	public void burstBubbleLink(){
		
		Collections.sort(clickedLink);
		
		for(Block b : clickedLink){
			burstBubble(b);
			b.setUncounted();
		}
	}
	
	public int getLinkSize(Block clickedBubble){
		
		clickedLink = new LinkedList<Block>();
		
		int linkedAmount = linkedAmount(clickedBubble, clickedBubble);
		
		if(linkedAmount == 1)
			clickedBubble.setUncounted();
		
		return linkedAmount;
	}
	
	public int linkedAmount(Block rootBubble, Block bubble){
		
		if(!isColorEqual(rootBubble, bubble) || bubble.isCounted() ) return 0;
			
		bubble.setCounted();
		clickedLink.add(bubble);
		
		int amount = 
				linkedAmount(bubble, bubble.getRight()) +
				linkedAmount(bubble, bubble.getLeft()) +
				linkedAmount(bubble, bubble.getTop()) +
				linkedAmount(bubble, bubble.getBottom()); 
		
		return ++amount;
	}
}
