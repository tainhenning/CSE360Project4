package project04;

public class ControlCenter {
	
	private static ControlCenter _instance;

	protected ControlCenter() {
	}

	public static ControlCenter getInstance() {
		if(_instance == null){
			_instance = new ControlCenter();
		}
		
		return _instance;
	}
	
	private int[] correct = new int[7];
	private int[] incorrect = new int[7];
	private float[] timeSpent;
}
