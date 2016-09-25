import java.awt.Color;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Random;

public class MapDesign extends Tools{
	
	final static int 
	WIDTH_CELL = 50, HEIGHT_CELL = 50;
	
	private int level;
	
	final int COLUMNS, ROWS;
	
	Block[][] blocksOnMap;
	LinkedList<Block> clickedLink;
	
	public MapDesign(int COLUMNS, int ROWS){
		
		this.COLUMNS = COLUMNS;
		this.ROWS = ROWS;
		
		blocksOnMap = new Block[COLUMNS][ROWS];
	}
	
	public void createNewMap() {
		
		for(int y = 0; y < ROWS; y++){
			
			for (int x = 0; x < COLUMNS; x++){
				
				Block block = new Block(x, y, genColor());
				
				blocksOnMap[x][y] = block;
				
				setSurroundingBlocks(block, getPrevBlock(x, y));
			}
		}
	}
	
	private void setSurroundingBlocks(Block block, Block prev){
		
		int posX = block.getPosX();
		int posY = block.getPosY();
		
		if(posX != 0) {
			block.setLeft(prev);	
			block.getLeft().setRight(block);		
		}
		
		if(posY > 0) {
			
			if(posX == 0){
				block.setTop(blocksOnMap[0][--posY]);
			}else{
				block.setTop(getTopBlock(block));
			}
	
			block.getTop().setBottom(block);
		}
	}
	
	private Block getTopBlock(Block block){
		
		return block.getLeft().getTop().getRight();
	}
	
	
	private Block getPrevBlock(int x, int y){
		
		if(x == 0 && y == 0)return null;
		
		int prevX = x-1;
		
		if(prevX < 0) return blocksOnMap[x][y-1];
		
		return blocksOnMap[prevX][y];
	}
	
	public void setRightLeftTopBottomToBlocks(){
		
		for(int y = 0; y < ROWS; y++){
			
			for (int x = 0; x < COLUMNS; x++){
				
				setSurroundingBlocks(blocksOnMap[x][y], getPrevBlock(x, y));
			}	
		}
	}
	
	public int getLinkSize(Block clickedBlock){
		
		clickedLink = new LinkedList<Block>();
		
		int linkedAmount = linkedAmount(clickedBlock, clickedBlock);
		
		if(linkedAmount == 1)
			clickedBlock.setUncounted();
		
		return linkedAmount;
	}
	
	private int linkedAmount(Block rootBlock, Block block){
		
		if(!isColorEqual(rootBlock, block) || block.isCounted() ) return 0;
			
		block.setCounted();
		clickedLink.add(block);
		
		int amount = 
				linkedAmount(block, block.getRight()) +
				linkedAmount(block, block.getLeft()) +
				linkedAmount(block, block.getTop()) +
				linkedAmount(block, block.getBottom()); 
		
		return ++amount;
	}
	
	public void burstBlockLink(){
		
		Collections.sort(clickedLink);
		
		for(Block b : clickedLink){
			burstBubble(b);
			b.setUncounted();
		}
	}
	
	public void burstBubble(Block block){
		
		setToTopColor(block);
	}	
	
	
	void setToTopColor(Block block){
		
		if(block.hasTop()){
			
			block.setColor(block.getTop().getColor());
			setToTopColor(block.getTop());
		}else{
			
			block.setColor(genColor());
		}
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
	
	public Block[][] getBlocksOnMap(){
		return blocksOnMap;
	}
	
	public void setLevel(int level){
		this.level = level;
	}
}
