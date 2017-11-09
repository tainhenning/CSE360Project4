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
	
	private int[] correctCount = new int[7];
	private int[] incorrectCount = new int[7];
	private float[] timeSpent;
	
	private int currentLesson;
	
	public void setCurrentLesson(int currentLesson) {
		this.currentLesson = currentLesson;
	}
	
	public int getCurrentLesson() {
		return currentLesson;
	}
	
	public void answerCorrect() {
		correctCount[currentLesson]++;
	}
	
	public void answerIncorrect() {
		incorrectCount[currentLesson]++;
	}
	
	public void timeSet(float time) {
		timeSpent[currentLesson] = time;
	}
}
