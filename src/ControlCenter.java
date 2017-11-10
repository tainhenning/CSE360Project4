package project04;

import java.text.DecimalFormat;

public class ControlCenter {

	private static ControlCenter _instance;

	protected ControlCenter() {
	}

	public static ControlCenter getInstance() {
		if (_instance == null) {
			_instance = new ControlCenter();
		}

		return _instance;
	}

	private int[] correctCount = new int[10];
	private int[] incorrectCount = new int[10];
	private float[] timeSpentPerQuestion = new float[10];
	private float[][] timeSpentPerLessons = new float[8][];

	private int currentLesson;

	private int currentQuestion;

	private long timerStart;
	private long timerEnd;
	private long questionTime;

	private int attempts = 1;

	private DecimalFormat format = new DecimalFormat("#.00");

	public void newQuestion() {
		timerSet();

		if (currentQuestion == 10) {
			newLesson();
		} else {
			currentQuestion++;
			attempts = 1;
		}
	}

	public void newLesson() {
		currentQuestion = 1;
		timerSet();
	}

	public void setCurrentLesson(int currentLesson) {
		this.currentLesson = currentLesson;
		currentQuestion = 1;
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
		timerStop();
		correctCount[currentLesson]++;

		newQuestion();
	}

	public void answerIncorrect() {
		incorrectCount[currentLesson]++;
		if (attempts == 2) {
			timerStop();

			newQuestion();
		} else {
			attempts++;
		}
	}

	public void timerSet() {
		timerStart = System.nanoTime();
	}

	public void timerStop() {
		timerEnd = (long) (System.nanoTime() / 1000000.00);
		
		questionTime = timerEnd - timerStart;

//		String dfStore = format.format(questionTime);
//
//		float elapsedTime = Float.valueOf(dfStore);
//		questionTime = Float.valueOf(dfStore);

		timeSpentPerQuestion[currentQuestion] = questionTime;

		System.out.println("Question Time: " + timeSpentPerQuestion[currentQuestion]);
	}
}
