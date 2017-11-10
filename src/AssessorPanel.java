package project04;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.awt.*;
import java.awt.event.*;
import java.util.Observable;
import java.util.Observer;
import java.util.Random;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.text.BadLocationException;

/*
 * File: Assessor.java
 * Author: Spencer Duchene
 * Course: CSE 360
 * Group: 1
 */
public class AssessorPanel extends JPanel implements ActionListener, Observer {

	// Initialize state at 0
	int state = 0;
	// Globally create constraints for GridBag Layout
	GridBagConstraints gbc = new GridBagConstraints();
	// Creates panels for GUI objects
	// Panel layoutPanelOne = new JPanel(new GridLayout(3, 0, 0, 15));
	JPanel contentPanel = new JPanel(new GridBagLayout());
	JPanel layoutPanelOne = new JPanel();
	JPanel layoutPanelTwo = new JPanel();
	// Label that is displayed in state 0
	JLabel nameLabel = new JLabel("Assessor Area");
	// Label to inform user of current question being display
	JLabel questionLabel = new JLabel();
	// Setting radio buttons -- NEW v0.1t
	JRadioButton button1 = new JRadioButton("Option 1");
	JRadioButton button2 = new JRadioButton("Option 2");
	JRadioButton button3 = new JRadioButton("Option 3");
	JRadioButton button4 = new JRadioButton("Option 4");
	ButtonGroup group = new ButtonGroup();
	// the question given to the user -- NEW v0.1t
	JLabel question = new JLabel("<html>" + "Question" + "</html>");
	// submit button -- NEW v0.1t
	JButton submit = new JButton("Submit");

	Universe parentFrame;
	Assessor assessor;
	ControlCenter controlCenter;

	// GLOBALS
	// Solution strings
	String option1, option2, option3, option4;
	// global answer option -- NEW v0.1t
	int position;
	// amount of wrong answers submitted -- NEW v0.1t
	int wrongCount = 0;
	// amount of correct answer submitted -- NEW v0.1t
	int correctCount = 0;
	int sectionCount = 0;
	int lesson = 0;
	// eligibility to go to next question -- NEW v0.1t
	boolean next = false;


	public int getWrongCount() {
		return wrongCount;
	}

	public void setIncorrectCount(int wrongCount) {
		this.wrongCount = wrongCount;
	}

	public int getCorrectCount() {
		return correctCount;
	}

	public void setCorrectCount(int correctCount) {
		this.correctCount = correctCount;
	}

	public int getLesson() {
		return lesson;
	}

	public void setLesson(int lesson) {
		this.lesson = lesson;
	}

	// --------------------------------------------------------
	// Append these to newest Assessor.java
	// --------------------------------------------------------
	boolean correct = false;

	// Constructor of the Assessor class
	public AssessorPanel(Universe frame) {
		super();
		parentFrame = frame;
		this.setVisible(true);

		controlCenter = ControlCenter.getInstance();

		// Sets up the first time run elements of the GUI
		prepareGUI();
		setQuestions(lesson,correctCount);
		// Runs to update and initialize first run components to GUI
		updateGUI();
	}

	public void setAssessor(Assessor assessor) {
		this.assessor = assessor;
		assessor.addObserver(this);
	}
	
	public void setControlCenter(ControlCenter controlCenter) {
		this.controlCenter = controlCenter;
		controlCenter.setCurrentLesson(1);
        controlCenter.setCurrentQuestion(1);
	}

	// Used to set up first time elements of the GUI
	public void setQuestions(int lesson, int correctCount) {

		Random placement = new Random();
		position = placement.nextInt(4);
		ProblemGenerator problemToSolve = new ProblemGenerator();
		// final problem set for section


		//Lesson 1 logic
		if (lesson == 1 && sectionCount >= 10) {
				// call Tains dialogue for completion. then something?'
			sectionCount = 0;
			question.setText(problemToSolve.functionCombiner());
			setCorrectPosition(position, problemToSolve);

		} else if (lesson == 1 && sectionCount >= 6) {
//			System.out.println("final set");
			// -- NEW v0.1t added a setAnswer method to set which option is the answer
			question.setText(problemToSolve.functionCombinerSubtract());
			setCorrectPosition(position, problemToSolve);
		} else if (lesson == 1 && sectionCount >= 3) {
//			System.out.println("next set");
			question.setText(problemToSolve.functionCombineMultiply());
			setCorrectPosition(position, problemToSolve);
		}
		//THIS NEEDS TO BE OPTIMIZED
		//Lesson 2 logic
		else if (lesson == 2 && sectionCount >= 6) {
//			System.out.println("final set");
			// -- NEW v0.1t added a setAnswer method to set which option is the answer
			question.setText(problemToSolve.eliminationMethod());
			setCorrectPosition(position, problemToSolve);
		} else if (lesson == 2 && sectionCount >= 0) {
//			System.out.println("next set");
			question.setText(problemToSolve.dimensionOfMatrix());
			setCorrectPosition(position, problemToSolve);
		}
		//Lesson 3 Logic
		else if (lesson == 3 && sectionCount >= 6) {
//			System.out.println("final set");
			// -- NEW v0.1t added a setAnswer method to set which option is the answer
			question.setText(problemToSolve.functionCombinerSubtract());
			setCorrectPosition(position, problemToSolve);
		} else if (lesson == 3 && sectionCount >= 0) {
//			System.out.println("next set");
			question.setText(problemToSolve.functionCombineMultiply());
			setCorrectPosition(position, problemToSolve);
		}
		//Lesson 4 Logic
		else if (lesson == 4 && sectionCount >= 6) {
//			System.out.println("final set");
			// -- NEW v0.1t added a setAnswer method to set which option is the answer
			question.setText(problemToSolve.functionCombinerSubtract());
			setCorrectPosition(position, problemToSolve);
		} else if (lesson == 4 && sectionCount >= 0) {
//			System.out.println("next set");
			question.setText(problemToSolve.functionCombineMultiply());
			setCorrectPosition(position, problemToSolve);
		}
		//Lesson 5 Logic
		else if (lesson == 5 && sectionCount >= 6) {
//			System.out.println("final set");
			// -- NEW v0.1t added a setAnswer method to set which option is the answer
			question.setText(problemToSolve.functionCombinerSubtract());
			setCorrectPosition(position, problemToSolve);
		} else if (lesson == 5 && sectionCount >= 0) {
//			System.out.println("next set");
			question.setText(problemToSolve.functionCombineMultiply());
			setCorrectPosition(position, problemToSolve);
		}
		//Lesson 6 Logic
		else if (lesson == 6 && sectionCount >= 6) {
//			System.out.println("final set");
			// -- NEW v0.1t added a setAnswer method to set which option is the answer
			question.setText(problemToSolve.functionCombinerSubtract());
			setCorrectPosition(position, problemToSolve);
		} else if (lesson == 6 && sectionCount >= 0) {
//			System.out.println("next set");
			question.setText(problemToSolve.functionCombineMultiply());
			setCorrectPosition(position, problemToSolve);
		}
		else {
			question.setText(problemToSolve.functionCombiner());
			setCorrectPosition(position, problemToSolve);
		}

		layoutPanelOne.removeAll();
		layoutPanelOne.revalidate();
		layoutPanelOne.repaint();
		updateGUI();
	}

	// handles randomizing correct answer placement
	public void setCorrectPosition(int placement, ProblemGenerator problem) {
		int correctSlot = placement;
		System.out.println(problem.getFalseSolutionThree());
		switch (correctSlot) {

		case 1:
			option1 = problem.getProblemSolution();
			option2 = problem.getFalseSolutionOne();
			option3 = problem.getFalseSolutionTwo();
			option4 = problem.getFalseSolutionThree();
			setAnswer();
			break;
		case 2:
			option1 = problem.getFalseSolutionOne();
			option2 = problem.getProblemSolution();
			option3 = problem.getFalseSolutionTwo();
			option4 = problem.getFalseSolutionThree();
			setAnswer();
			break;
		case 3:
			option1 = problem.getFalseSolutionTwo();
			option2 = problem.getFalseSolutionOne();
			option3 = problem.getProblemSolution();
			option4 = problem.getFalseSolutionThree();
			setAnswer();
			break;
		case 4:
			option1 = problem.getFalseSolutionThree();
			option2 = problem.getFalseSolutionOne();
			option3 = problem.getFalseSolutionTwo();
			option4 = problem.getProblemSolution();
			setAnswer();
			break;
		default:
			option1 = problem.getProblemSolution();
			option2 = problem.getFalseSolutionOne();
			option3 = problem.getFalseSolutionTwo();
			option4 = problem.getFalseSolutionThree();
			setAnswer();
			break;
		}
	}

	public void setAnswer() {
		// -- NEW v0.1t added a setAnswer method to set which option is the answer
		System.out.println(option1);
		button1.setText(option1); // and what each option should say for its text
		button2.setText(option2);
		button3.setText(option3);
		button4.setText(option4);
		// layoutPanelOne.removeAll();
		// layoutPanelOne.revalidate();
		// layoutPanelOne.repaint();
		group.clearSelection();
		updateGUI();
	}

	public void changeState(int newState) {
		// sets the global state to the new state passed into this method.
		state = newState;

		// Used for testing, depreciated in the final version
		// System.out.println(state);

		// Removes all the previous state content
		layoutPanelOne.removeAll();
		layoutPanelOne.revalidate();
		layoutPanelOne.repaint();

		// Updates GUI based on conditional of what value state is
		updateGUI();
	}

	public void prepareGUI() {
		// Sets up GridBagLayout for whole panel
		this.setLayout(new GridBagLayout());
		this.setPreferredSize(new Dimension(400, 400));
		// Sets layout style of layoutPanelOne
		layoutPanelOne.setLayout(new BoxLayout(layoutPanelOne, BoxLayout.Y_AXIS));
		// Sets GridBagConstraints for main and content panels
		gbc.gridx = 1;
		gbc.gridy = 2;
		gbc.gridwidth = 2;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.anchor = GridBagConstraints.CENTER;

		// Adds panels to their specified parent
		this.add(contentPanel, gbc);
		contentPanel.add(layoutPanelOne, gbc);

		// Sets sizing of panels, in order to keep things in place when empty
		contentPanel.setPreferredSize(new Dimension(300, 365));

		// Adds layoutPanelOne to content panel, this is where content will be
		contentPanel.add(layoutPanelOne, gbc);

		// Changes GridBagConstraints for next panel to add to main
		gbc.gridx = 3;
		gbc.gridy = 2;
		gbc.gridwidth = 2;

		// Adds layoutPanelTwo to main, acts as a buffer
		this.add(layoutPanelTwo, gbc);

		// Sets up listeners to the components -- NEW v0.1t
		button1.addActionListener(this);
		button2.addActionListener(this);
		button3.addActionListener(this);
		button4.addActionListener(this);
		submit.addActionListener(this);
	}

	public void updateGUI() {
		// Shows the question label
		questionLabel.setText("Question " + controlCenter.getCurrentQuestion() + ":");
                
		// setting main button group
		group.add(button1);
		group.add(button2);
		group.add(button3);
		group.add(button4);
		// Depending on states value, will set up GUI
		if (state == 1) {
			// Sets constraints for question label
			gbc.gridx = 0;
			gbc.gridy = 0;
			gbc.gridheight = 2;
			gbc.anchor = GridBagConstraints.CENTER;
			this.add(questionLabel, gbc);
			// return;
			// Adds combo box to layoutPanelOne in the content panel
		} else if (state == 2) {
			// Sets constraints for question label
			gbc.gridx = 0;
			gbc.gridy = 0;
			gbc.gridheight = 2;
			gbc.anchor = GridBagConstraints.CENTER;
			this.add(questionLabel, gbc);
		} else if (state == 3) {
			// Sets constraints for question label
			gbc.gridx = 0;
			gbc.gridy = 0;
			gbc.gridheight = 2;
			gbc.anchor = GridBagConstraints.CENTER;
			this.add(questionLabel, gbc);

		} else if (state == 4) {
			// Sets constraints for question label
			gbc.gridx = 0;
			gbc.gridy = 0;
			gbc.gridheight = 2;
			gbc.anchor = GridBagConstraints.CENTER;
			this.add(questionLabel, gbc);

		} else {
			// If state = 0, this will add my name to the project
			layoutPanelOne.add(nameLabel);
		}
		if (state != 0)// -- NEW v0.1t simplified adding buttons
		{
			int x = 10;
			layoutPanelOne.add(Box.createVerticalStrut(x));
			layoutPanelOne.add(question);
			layoutPanelOne.add(Box.createVerticalStrut(x));
			layoutPanelOne.add(button1);
			layoutPanelOne.add(Box.createVerticalStrut(x));
			layoutPanelOne.add(button2);
			layoutPanelOne.add(Box.createVerticalStrut(x));
			layoutPanelOne.add(button3);
			layoutPanelOne.add(Box.createVerticalStrut(x));
			layoutPanelOne.add(button4);
			layoutPanelOne.add(Box.createVerticalStrut(x + 10));
			layoutPanelOne.add(submit);
		}

	}

	public boolean getEligibilityForNextQuestion() // -- NEW v0.1t returns whether the user can go to the next
	{ // question
		return next;
	}

	public int getWrongAnswerCount() // -- NEW v0.1t returns amount of wrong answers submitted
	{
		return wrongCount;
	}

	public int getCorrectAnswerCount() // -- NEW v0.1t returns amount of correct answer submitted
	{
		return correctCount;
	}

	public boolean getCorrectOrNot() {
		return correct;
	}

	public void actionPerformed(ActionEvent e) {
		// Shows a Dialog Window when action listener detects action
		if (e.getSource() == submit) {
			if (button1.isSelected() == true) {
				if (position == 1) {
					DescriptorPanel.appendText(true);
					correctCount++;
					setQuestions(lesson,correctCount);
					
					controlCenter.answerCorrect();

					assessor.answerCorrect();
				} else {

					DescriptorPanel.appendText(false);
					wrongCount++;
					setQuestions(lesson,correctCount);
					
					controlCenter.answerIncorrect();
					
					assessor.answerIncorrect();
				}

			} else if (button2.isSelected() == true) {
				if (position == 2) {
					DescriptorPanel.appendText(true);
					correctCount++;
					setQuestions(lesson,correctCount);

					controlCenter.answerCorrect();
					
					assessor.answerCorrect();

				} else {
					DescriptorPanel.appendText(false);
					wrongCount++;
					setQuestions(lesson,correctCount);
					
					controlCenter.answerIncorrect();
					
					assessor.answerIncorrect();
				}

			} else if (button3.isSelected() == true) {
				if (position == 3) {
					DescriptorPanel.appendText(true);
					correctCount++;
					setQuestions(lesson, correctCount);
					
					controlCenter.answerCorrect();
					
					assessor.answerCorrect();

				} else {
					DescriptorPanel.appendText(false);
					wrongCount++;
					setQuestions(lesson, correctCount);

					controlCenter.answerIncorrect();
					
					assessor.answerIncorrect();
				}

			} else if (button4.isSelected() == true) {
				if (position == 4) {
					DescriptorPanel.appendText(true);
					correctCount++;

					setQuestions(lesson, correctCount);
					
					controlCenter.answerCorrect();
					
					assessor.answerCorrect();

				} else {
					DescriptorPanel.appendText(false);
					wrongCount++;
					setQuestions(lesson, correctCount);
					
					controlCenter.answerIncorrect();
					
					assessor.answerIncorrect();
				}
			}
			System.out.println("Wrong count: " + wrongCount);
			System.out.println("Correct count: " + correctCount);
			
			updateGUI();
			// parentFrame.setQuestionsCounts();
		}
	}

	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub

	}
}
