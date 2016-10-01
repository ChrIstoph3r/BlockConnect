import java.awt.Color;
import java.util.Collections;
import java.util.LinkedList;

public class MapDesign extends Tools{
	
	final static int 
	WIDTH_CELL = 50, HEIGHT_CELL = 50;
	
	private int level;
	
	final int COLUMNS, ROWS;
	
	private Block[][] blocksOnMap;
	private LinkedList<Block> clickedLink;
	
	public MapDesign(int COLUMNS, int ROWS){
		
		this.COLUMNS = COLUMNS;
		this.ROWS = ROWS;
		
		blocksOnMap = new Block[COLUMNS][ROWS];
	}
	
	public void createNewMap() {
		
		for(int y = 0; y < ROWS; y++)
			for (int x = 0; x < COLUMNS; x++)
				blocksOnMap[x][y] = new Block(x, y, genColor()); 
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
		 
		determineLink(block, getLeft(block));
		determineLink(block, getRight(block));
		determineLink(block, getTop(block));
		determineLink(block, getBottom(block)); 
	}

	public void collapseBlockLink(){
		
		Collections.sort(clickedLink);
		
		for(Block b : clickedLink){
			collapseBlock(b);
			b.setUncounted();
		}
	}
	
	private void collapseBlock(Block block){
		
		setToTopColor(block);
	}	
	
	private void setToTopColor(Block block){
		
		Block topBlock = getTop(block);
		
		if(topBlock != null){
			
			block.setColor(topBlock.getColor());
			setToTopColor(topBlock);
		}else{
			
			block.setColor(genColor());
		}
	}
	
	public Color genColor() {

		Color color = null;
		
		int amntOfColors = level + 2;
		
		int randNumb = (int)( Math.random()*amntOfColors );
	
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
	
	private Block getLeft(Block block){
		
		int x = block.getPosX();
		
		if(x == 0) return null;
		
		int y = block.getPosY();
		
		return blocksOnMap[--x][y];
	}
	
	private Block getRight(Block block){
		
		int x = block.getPosX();
		
		if(x == COLUMNS - 1) return null;
		
		int y = block.getPosY();
		
		return blocksOnMap[++x][y];
	}

	private Block getTop(Block block){
		
		int y = block.getPosY();
		
		if(y == 0) return null;
		
		int x = block.getPosX();
		
		return blocksOnMap[x][--y];
	}

	private Block getBottom(Block block){
		
		int y = block.getPosY();
		
		if(y == ROWS - 1) return null;
		
		int x = block.getPosX();
		
		return blocksOnMap[x][++y];
	}
	
	public Block[][] getBlocksOnMap(){
		return blocksOnMap;
	}
	
	public void setLevel(int level){
		this.level = level;
	}
}
