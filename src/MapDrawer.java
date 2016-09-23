import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;

import javax.swing.JComponent;

@SuppressWarnings("serial")
public class MapDrawer extends JComponent{
	
	final static int 
	WIDTH_CELL = MapDesign.WIDTH_CELL, 
	HEIGHT_CELL = MapDesign.HEIGHT_CELL,
	START_X = 85, START_Y = 65;
	
	ArrayList<Block> bubblesOnMap; 
	
	public MapDrawer(ArrayList<Block> bubblesOnMap) {
	
		this.bubblesOnMap = bubblesOnMap;
	}
	
	public void paintComponent(Graphics g){
		
		Graphics2D g2 = (Graphics2D) g;
		
		for(Block bubble : bubblesOnMap){
			
			g2.setColor(bubble.getColor());
			
			int jFramePosX = bubble.getPosX()*WIDTH_CELL + START_X;
			int jFramePosY = bubble.getPosY()*HEIGHT_CELL + START_Y;
			g2.fillRect(jFramePosX, jFramePosY, WIDTH_CELL, HEIGHT_CELL);
		}	
	}
}