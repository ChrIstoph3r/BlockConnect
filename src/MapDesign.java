import java.awt.Color;
import java.util.Collections;
import java.util.LinkedList;

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
				
				blocksOnMap[x][y] = new Block(x, y, genColor()); 
				
				setSurroundingBlocks(x, y);
			}
		}
	}
	
	private void setSurroundingBlocks(int x, int y){
		
		Block block = blocksOnMap[x][y];
		
		if(x != 0) {
			block.setLeft(blocksOnMap[x-1][y]);	
			block.getLeft().setRight(block);		
		}
		
		if(y > 0) {
			block.setTop(blocksOnMap[x][--y]);
			block.getTop().setBottom(block);
		}
	}
	
	public void setRightLeftTopBottomToBlocks(){
		
		for(int y = 0; y < ROWS; y++){
			
			for (int x = 0; x < COLUMNS; x++){
				
				setSurroundingBlocks(x, y);
			}	
		}
	}
	
	public int getLinkSize(Block clickedBlock){
		
		clickedLink = new LinkedList<Block>();
		
		determineLink(clickedBlock, clickedBlock);
		
		int linkedAmount = clickedLink.size();
		
		if(linkedAmount == 1)
			clickedBlock.setUncounted();
		
		return linkedAmount;
	}
	
	private void determineLink(Block rootBlock, Block block){
		
		if( !isColorEqual(rootBlock, block) || block.isCounted() ) return;
			
		block.setCounted();
		clickedLink.add(block);
		 
		determineLink(block, block.getRight());
		determineLink(block, block.getLeft());
		determineLink(block, block.getTop());
		determineLink(block, block.getBottom()); 
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
		
		int amntOfColors = level + 2;
		
		int randNumb = (int)( Math.random()*amntOfColors );
	
		System.out.println(randNumb);
		
		switch(randNumb){
			
			case 0:
				color = Color.GREEN;
				break;
			case 1: 
				color = Color.BLUE;
				break;
			case 2:
				color = Color.RED;
				break;
			case 3:
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
