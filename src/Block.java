import java.awt.Color;

public class Block implements Comparable<Block>{
	
	int posX, posY;
	
	boolean counted = false;
	
	Color color;
	Block right, bottom, left, top = null;
	
	public Block(int posX, int posY, Color color){
		
		this.posX = posX;
		this.posY = posY;
		
		this.color = color;
	}
	
	public boolean hasTop(){
		if(top == null)return false;
		return true;
	}
	
	@Override
	public int compareTo(Block o) {
		return Integer.compare(getPosY(), o.getPosY());
	}
	
	public void setColor(Color color){
		this.color = color;
	}
	
	public Color getColor(){
		return color;
	}
	
	public void setPosY(int posY){
		this.posY = posY;
	}
	
	public void setPosX(int posX){
		this.posX = posX;
	}
	
	public void setCounted(){
		counted = true;
	}
	
	public void setUncounted(){
		counted = false;
	}

	public boolean isCounted(){
		return counted;
	}

	public int getPosX() {
		return posX;
	}
	
	public int getPosY() {
		return posY;
	}
	
	public Block getLeft(){
		return left;
	}
	
	public void setLeft(Block left){
		this.left = left;
	}

	public Block getRight() {
		return right;
	}

	public void setRight(Block right) {
		this.right = right;
	}
	
	public void setTop(Block top){
		this.top = top;
	}
	
	public Block getTop(){
		return top;
	}
   
	public Block getBottom() {
		return bottom;
	}

	public void setBottom(Block bottom) {
		this.bottom = bottom;
	}
}

	
