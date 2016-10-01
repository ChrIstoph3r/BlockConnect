
import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MouseWhisperer {
	
	final static int WIDTH_CELL = MapDesign.WIDTH_CELL;
	final static int HEIGHT_CELL = MapDesign.HEIGHT_CELL;
	
	final static int marginX = MapDrawer.START_X;
	final static int marginY = MapDrawer.START_Y;
		
	final static int LAST_ROW = BlockConnectMain.ROWS-1;
	final static int LAST_COLUMN = BlockConnectMain.COLUMNS-1;
		
	public static void onClick(Component map){
		
		map.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent click) {
				
				if(click.getX() < marginX || click.getY() < marginY)
					return;
				
				int clickedXpos = (int)( (click.getX() - marginX)/WIDTH_CELL );
		   	 	int clickedYpos = (int)( (click.getY() - marginY)/HEIGHT_CELL);
		   	 
			   	if(clickedXpos > LAST_COLUMN || clickedYpos > LAST_ROW)
			   		return; 
		   	 	
		   	 	BlockConnectMain.validClick(clickedXpos, clickedYpos);
			}
		});
	}
}
