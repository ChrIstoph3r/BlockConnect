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

		for(int y = blocksOnMap[0].length-1; y > -1 ; y--){
			
			for (int x = blocksOnMap.length-1; x > -1 ; x--){
			
				g2.setColor(blocksOnMap[x][y].getColor());
				
				int jFramePosX = x*WIDTH_CELL + START_X;
				int jFramePosY = y*HEIGHT_CELL + START_Y;
				g2.fillRect(jFramePosX, jFramePosY, WIDTH_CELL, HEIGHT_CELL);
			}	
		}
	}
}