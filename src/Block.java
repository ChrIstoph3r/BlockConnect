import java.awt.Color;

public class Block implements Comparable<Block>{
	
	int posX, posY;
	
	boolean counted = false;
	
	String bubbleColor;
	Color color;
	Block right, bottom, left, top = null;
	
	public Block(int posX, int posY, Color color){
		
		this.posX = posX;
		this.posY = posY;
		
		this.color = color;
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
	
	public boolean hasTop(){
		if(top == null)return false;
		return true;
	}
	
	public String toString(){
		
		return printBubbleCoordinate() + printSurroundingColors();
	}

	public String getColorString(){
		
		String bubbleColor = "NONE";
		if(color.equals(Color.GREEN)) bubbleColor = "GREEN";
		if(color.equals(Color.DARK_GRAY)) bubbleColor = "DARK_GRAY";
		if(color.equals(Color.BLUE)) bubbleColor = "BLUE";
		if(color.equals(Color.ORANGE)) bubbleColor = "ORANGE";
		if(color.equals(Color.RED)) bubbleColor = "RED";
		return bubbleColor;
	}
	
	private String printBubbleCoordinate(){
		
		return "("+getPosX()+"," + getPosY()+") \n"; 
	}
	
	private String printSurroundingColors(){
		
		String rightColor = "none";
		String leftColor = "none";
		String topColor = "none";
		String bottomColor = "none";
		
		if(getRight() != null)
			rightColor = getRight().getColorString();
		
		if(getLeft() != null)
			leftColor = getLeft().getColorString();
		
		if(getTop() != null)
			topColor = getTop().getColorString();
		
		if(getBottom() != null)
			bottomColor = getBottom().getColorString();
		
		return " Left: " + leftColor + "\n Right: " + rightColor + "\n Top: " + topColor + "\n Bottom: "+ bottomColor + "\n";
	}

	@Override
	public int compareTo(Block o) {
		return Integer.compare(getPosY(), o.getPosY());
	}
}

	
