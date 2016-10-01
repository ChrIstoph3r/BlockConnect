import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JComponent;

@SuppressWarnings("serial")
public class MapDrawer extends JComponent{
	
	final static int 
	WIDTH_CELL = MapDesign.WIDTH_CELL, 
	HEIGHT_CELL = MapDesign.HEIGHT_CELL,
	START_X = 85, START_Y = 65;
	
	Block[][] blocksOnMap; 
	
	public MapDrawer(Block[][] blocksOnMap) {
	
		this.blocksOnMap = blocksOnMap;
	}
	
	public void paintComponent(Graphics g){
		
		Graphics2D g2 = (Graphics2D) g;

		for(int y = 0; y < blocksOnMap[0].length; y++){
			
			for (int x = 0; x < blocksOnMap.length; x++){
			
				Block block = blocksOnMap[x][y];
				
				g2.setColor(block.getColor());
				
				int jFramePosX = x*WIDTH_CELL + START_X;
				int jFramePosY = y*HEIGHT_CELL + START_Y;
				g2.fillRect(jFramePosX, jFramePosY, WIDTH_CELL, HEIGHT_CELL);
			}	
		}
	}
}