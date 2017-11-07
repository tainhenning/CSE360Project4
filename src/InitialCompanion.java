package Project03;

import java.awt.Color;
import java.awt.Graphics;

public class InitialCompanion implements Companion {

	public void draw(Graphics face) {
		face.setColor(Color.yellow);
		face.fillOval(120, 120, 150, 150);
		// left eye
		face.setColor(Color.black);
		face.fillOval(160, 160, 10, 10);
		// right eye
		face.fillOval(220, 160, 10, 10);
	}

}
