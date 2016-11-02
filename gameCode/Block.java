import java.awt.Color;

public class Block implements Comparable<Block>{
	
	int posX, posY;
	
	boolean counted;
	
	Color color;
	
	public Block(int posX, int posY, Color color){
		
		this.posX = posX;
		this.posY = posY;
		
		this.color = color;
	}
	
	@Override
	public int compareTo(Block o) {
		return Integer.compare(getPosY(), o.getPosY());
	}
	
	public void setColor(Color color){
		this.color = color;
	}
	
	public int getPosX(){
		return posX;
	}
	
	public int getPosY(){
		return posY;
	}
	
	public Color getColor(){
		return color;
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
}
