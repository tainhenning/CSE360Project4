package Project03;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.awt.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;

/*
 * File: Tutor.java
 * Author: Michael Pardi
 * Course: CSE 360
 * Group: 1
 */
public class CompanionPanel extends JPanel implements Runnable {

	int paintState;
	JPanel layoutPanel = new JPanel();
	GridBagConstraints gbc = new GridBagConstraints();
	JLabel nameLabel = new JLabel("Companion Area");

	JProgressBar happinessBar = new JProgressBar();

	Thread stepper;

	int runState = 0;

	Dimension panelSize;

	int xPos;
	int yPos;

	int blinkDelay = 0;
	int blinkWidth = 10;
	int blinkHeight = 10;

	int happiness = 4;

	int incorrectCount = 0;
	int correctCount = 0;

	boolean blink = false;

	public CompanionPanel() {
		setLayout(new GridBagLayout());
		setPreferredSize(new Dimension(400, 400));
		add(nameLabel);

		viewHappinessBar();

		yPos = 120;
	}

	public void start() {
		if (stepper == null) {
			stepper = new Thread(this);
			stepper.start();
		}
	}

	public void stop() {
		if (stepper != null) {
			stepper.stop();
			stepper = null;
		}
	}

	public void changeState(int state) {
		if (paintState == 0) {
			remove(nameLabel);

			gbc.weighty = 1;
			gbc.anchor = GridBagConstraints.SOUTHWEST;
			add(happinessBar, gbc);
			start();
		}

		paintState = state;
		stop();
		resetPos();
		repaint();
		start();
	}

	public void resetPos() {
		xPos = 120;
		yPos = 120;
	}

	public void setCorrectCount(int count) {
		if (correctCount < count) {
			if (happiness < 5) {
				happiness++;
			}
		}

		correctCount = count;
		System.out.println(correctCount);
	}

	public void setIncorrectCount(int count) {
		if (incorrectCount < count) {
			if (happiness > 0) {
				happiness--;
			}
		}

		incorrectCount = count;
		System.out.println(incorrectCount);
	}

	public void checkHappiness() {
		happinessBar.setValue(happiness);
	}

	public int getHappiness() {
		return happiness;
	}

	public void run() {
		while (true) {
			if (happiness < 5) {
				if (blinkDelay == 100) {
					blink = true;

					repaint();
					try {
						Thread.sleep(350);
					} catch (InterruptedException ex) {

					}
					blinkDelay = 0;
				} else {
					blink = false;
					repaint();
				}
				try {
					Thread.sleep(50);
				} catch (InterruptedException ex) {

				}
				checkHappiness();
				blinkDelay++;
			} else {
				for (xPos = 120; xPos <= 240; xPos += 15) {
					if (runState == 0) {
						yPos += 15;
					} else {
						yPos -= 15;
					}
					repaint();
					try {
						Thread.sleep(100);
					} catch (InterruptedException ex) {
					}
				}
				for (xPos = 240; xPos >= 0; xPos -= 15) {
					if (runState == 0) {
						yPos -= 15;
					} else {
						yPos += 15;
					}
					repaint();
					try {
						Thread.sleep(100);
					} catch (InterruptedException ex) {
					}
				}
				for (xPos = 0; xPos <= 120; xPos += 15) {
					if (runState == 0) {
						yPos += 15;
					} else {
						yPos -= 15;
					}
					repaint();
					try {
						Thread.sleep(100);
					} catch (InterruptedException ex) {
					}
				}
				if (runState == 0) {
					runState = 1;
				} else {
					runState = 0;
				}
			}
		}
	}

	public void paintComponent(Graphics face) {
		Companion companionFace = new InitialCompanion();

		if (paintState == 0) {

		} else {
			if (happiness < 5) {
				// if (blink) {
				// blinkFace(face, Color.YELLOW);
				// repaint();
				// } else {
				// initialFace(face, Color.YELLOW);
				// repaint();
				// }
				switch (happiness) {
				case 4:
					HappierCompanion happier = new HappierCompanion();
					happier.add(companionFace);
					happier.draw(face);
					break;
				case 3:
					HappyCompanion happy = new HappyCompanion();
					happy.add(companionFace);
					happy.draw(face);
					break;
				case 2:
					NeutralCompanion neutral = new NeutralCompanion();
					neutral.add(companionFace);
					neutral.draw(face);
					break;
				case 1:
					WorriedCompanion worried = new WorriedCompanion();
					worried.add(companionFace);
					worried.draw(face);
					break;
				default:
					SadCompanion sad = new SadCompanion();
					sad.add(companionFace);
					sad.draw(face);
					break;
				}
			} else {
				superHappyFace(face, Color.YELLOW);
				repaint();
			}
		}
	}

	public void viewHappinessBar() {
		happinessBar.setStringPainted(true);
		happinessBar.setString("Happiness Level");

		happinessBar.setValue(happiness);
		happinessBar.setMinimum(0);
		happinessBar.setMaximum(5);

		happinessBar.setPreferredSize(new Dimension(300, 30));
	}

	public void initialFace(Graphics face, Color color) {
		switch (happiness) {
		case 5:
			break;
		case 4:
			face.setColor(Color.YELLOW);
			break;
		case 3:
			face.setColor(Color.GREEN);
			break;
		case 2:
			face.setColor(Color.LIGHT_GRAY);
			break;
		case 1:
			face.setColor(Color.ORANGE);
			break;
		default:
			face.setColor(Color.RED);
			break;
		}
		face.fillOval(120, 120, 150, 150);
		// left eye
		face.setColor(Color.black);
		face.fillOval(160, 160, 10, 10);
		// right eye
		face.fillOval(220, 160, 10, 10);
		// mouth
		switch (happiness) {
		case 5:
			break;
		case 4:
			face.fillArc(145, 145, 100, 100, 180, 180);
			break;
		case 3:
			face.fillArc(145, 195, 100, 50, 180, 180);
			break;
		case 2:
			face.fillRect(145, 215, 100, 10);
			break;
		case 1:
			face.drawArc(145, 200, 100, 60, 180, -180);
			break;
		default:
			face.fillArc(145, 195, 100, 100, 180, -180);
			break;
		}
	}

	public void blinkFace(Graphics face, Color color) {
		face.setColor(color);
		face.fillOval(120, 120, 150, 150);
		// left eye
		face.setColor(Color.BLACK);
		face.drawLine(160, 165, 170, 165);
		// right eye
		face.drawLine(220, 165, 230, 165);
		// mouth
		switch (happiness) {
		case 5:
			break;
		case 4:
			face.fillArc(145, 145, 100, 100, 180, 180);
			break;
		case 3:
			face.fillArc(145, 195, 100, 50, 180, 180);
			break;
		case 2:
			face.fillRect(145, 215, 100, 10);
			break;
		case 1:
			face.drawArc(145, 200, 100, 60, 180, -180);
			break;
		default:
			face.fillArc(145, 195, 100, 100, 180, -180);
			break;
		}
	}

	public void superHappyFace(Graphics face, Color color) {
		face.setColor(Color.yellow);
		face.fillOval(xPos, yPos, 150, 150);
		// left eye
		face.setColor(Color.black);
		face.fillOval(xPos + 40, yPos + 55, 10, 10);
		// right eye
		face.fillOval(xPos + 100, yPos + 55, 10, 10);
		// mouth
		face.fillArc(xPos + 25, yPos + 25, 100, 100, 180, 180);
	}
}