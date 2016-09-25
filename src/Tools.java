
public class Tools {
	
	 static boolean isColorEqual(Block block1, Block block2){
		
		if(block2 == null || block1 == null) return false;
		if(block1.color.equals(block2.color))return true;
		return false;
	}

}
