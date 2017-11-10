package project04;

import java.text.DecimalFormat;

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
	private float[] timeSpentPerQuestion = new float[3];
	private float[][] timeSpentPerLessons = new float[8][];
	
	private int currentLesson;
	
	private int currentQuestion;
        
        private long timerStart;
        private long timerEnd;
        private float questionTime;
        
        private int attempts = 0;
        
        private DecimalFormat format = new DecimalFormat("0.00");
        
        public void newQuestion() {
            timerSet();
            
            if (currentQuestion == 10) {
                newLesson();
            }
            else {
                currentQuestion++;
                attempts = 1;
            }
        }
        
        public void newLesson() {
            currentQuestion = 0;
            
        }
	
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
                timerStop();
		correctCount[currentLesson]++;
                
                newQuestion();
	}
	
	public void answerIncorrect() {
		incorrectCount[currentLesson]++;
                if (attempts == 2) {
                    timerStop();
                    
                    newQuestion();
                }
                else {
                    attempts++;
                }
	}
	
	public void timerSet() {
		timerStart = System.nanoTime();
	}
        
        public void timerStop() {
            timerEnd = System.nanoTime() - timerStart;
            
            String dfStore = format.format(timerEnd / 100000.00);
                
            questionTime = Float.valueOf(dfStore);
                
            timeSpentPerQuestion[currentQuestion] = questionTime;
                
            System.out.println("Question Time: " + timeSpentPerQuestion[currentQuestion]);
        }
}
