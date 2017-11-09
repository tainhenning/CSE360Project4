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
	
	private int[] correctCount = new int[8];
	private int[] incorrectCount = new int[8];
	private float[] timeSpentPerQuestion;
	private float[][] timeSpentPerLessons;
	
	private int currentLesson;
	
	private int currentQuestion;
	
	public void setCurrentLesson(int currentLesson) {
		this.currentLesson = currentLesson;
		currentQuestion = 0;
	}
	
	public int getCurrentLesson() {
		return currentLesson;
	}
	
	public void setCurrentQuestion(int currentQuestion) {
		this.currentQuestion = currentQuestion;
	}
	
	public int getCurrentQuestion() {
		return currentQuestion;
	}
	
	public void answerCorrect() {
		correctCount[currentLesson]++;
		for (int i = 0; i < correctCount.length; i++) {
			System.out.print(correctCount[i]);
		}
	}
	
	public void answerIncorrect() {
		incorrectCount[currentLesson]++;
		for (int i = 0; i < correctCount.length; i++) {
			System.out.print(incorrectCount[i]);
		}
	}
	
	public void timeSet(float time) {
		
	}
}
