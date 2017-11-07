package Project03;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.awt.*;
import java.util.Random;
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

	Color colors[] = { Color.YELLOW, Color.GREEN, Color.LIGHT_GRAY, Color.ORANGE, Color.RED };
	Random rand = new Random();

	float r = rand.nextFloat();
	float g = rand.nextFloat();
	float b = rand.nextFloat();

	int colorChangeDelay = 0;

	Color randomColor = new Color(r, g, b);

	JProgressBar happinessBar = new JProgressBar();

	Thread stepper;

	int runState = 0;

	Dimension panelSize;

	int xPos;
	int yPos;

	int blinkDelay = 0;
	int blinkWidth = 10;
	int blinkHeight = 10;

	int happiness = 5;

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
				if (blinkDelay == 50) {
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
					colorChangeDelay++;
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
					colorChangeDelay++;
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
					colorChangeDelay++;
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

			if (colorChangeDelay > 10) {
				r = rand.nextFloat();
				g = rand.nextFloat();
				b = rand.nextFloat();

				randomColor = new Color(r, g, b);

				colorChangeDelay = 0;
			}
		}
	}

	public void paintComponent(Graphics face) {
		if (paintState == 0) {

		} else {
			if (happiness < 5) {
				Companion companionFace = new InitialCompanion();
				// Depreciated due to decorator
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
					if (blink) {
						happier.draw(face, colors[0], true);
					} else {
						happier.draw(face, colors[0], false);
					}
					break;
				case 3:
					HappyCompanion happy = new HappyCompanion();
					happy.add(companionFace);
					if (blink) {
						happy.draw(face, colors[1], true);
					} else {
						happy.draw(face, colors[1], false);
					}
					break;
				case 2:
					NeutralCompanion neutral = new NeutralCompanion();
					neutral.add(companionFace);
					if (blink) {
						neutral.draw(face, colors[2], true);
					} else {
						neutral.draw(face, colors[2], false);
					}
					break;
				case 1:
					WorriedCompanion worried = new WorriedCompanion();
					worried.add(companionFace);
					if (blink) {
						worried.draw(face, colors[2], true);
					} else {
						worried.draw(face, colors[3], false);
					}
					break;
				default:
					SadCompanion sad = new SadCompanion();
					sad.add(companionFace);
					if (blink) {
						sad.draw(face, colors[4], true);
					} else {
						sad.draw(face, colors[4], false);
					}
					break;
				}
			} else {
				superHappyFace(face, randomColor);
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

	// Depreciated due to Decorator implementation
	// public void initialFace(Graphics face, Color color) {
	// switch (happiness) {
	// case 5:
	// break;
	// case 4:
	// face.setColor(Color.YELLOW);
	// break;
	// case 3:
	// face.setColor(Color.GREEN);
	// break;
	// case 2:
	// face.setColor(Color.LIGHT_GRAY);
	// break;
	// case 1:
	// face.setColor(Color.ORANGE);
	// break;
	// default:
	// face.setColor(Color.RED);
	// break;
	// }
	// face.fillOval(120, 120, 150, 150);
	// // left eye
	// face.setColor(Color.black);
	// face.fillOval(160, 160, 10, 10);
	// // right eye
	// face.fillOval(220, 160, 10, 10);
	// // mouth
	// switch (happiness) {
	// case 5:
	// break;
	// case 4:
	// face.fillArc(145, 145, 100, 100, 180, 180);
	// break;
	// case 3:
	// face.fillArc(145, 195, 100, 50, 180, 180);
	// break;
	// case 2:
	// face.fillRect(145, 215, 100, 10);
	// break;
	// case 1:
	// face.drawArc(145, 200, 100, 60, 180, -180);
	// break;
	// default:
	// face.fillArc(145, 195, 100, 100, 180, -180);
	// break;
	// }
	// }

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
		face.setColor(randomColor);
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