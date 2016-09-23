
public class Tools {
	
	 static boolean isColorEqual(Block bubble1, Block bubble2){
		
		if(bubble2 == null || bubble1 == null) return false;
		if(bubble1.color.equals(bubble2.color))return true;
		return false;
	}

}
