
import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MouseWhisperer {
	
	final static int WIDTH_CELL = MapDesign.WIDTH_CELL;
	final static int HEIGHT_CELL = MapDesign.HEIGHT_CELL;
	
	final static int marginX = MapDrawer.START_X;
	final static int marginY = MapDrawer.START_Y;
		
	final static int LAST_ROW = BlokonectMain.ROWS-1;
	final static int LAST_COLUMN = BlokonectMain.COLUMNS-1;
		
	public static void onClick(Component map){
		
		map.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				
				if(itsNoMoreTurns()){
					BlokonectMain.noMoreTurns();
					return;
				}
				
				if(e.getX() < marginX || e.getY() < marginY)
					return;
				
				int clickedXpos = (int)( (e.getX() - marginX)/WIDTH_CELL );
		   	 	int clickedYpos = (int)( (e.getY() - marginY)/HEIGHT_CELL);
		   	 
			   	if(clickedXpos > LAST_COLUMN || clickedYpos > LAST_ROW)
			   		return; 
		   	 	
		   	 	BlokonectMain.validClick(clickedXpos, clickedYpos);
			}
		});
	}
	
	private static boolean itsNoMoreTurns(){
		
		return BlokonectMain.itsNoMoreTurns();
	}
}
