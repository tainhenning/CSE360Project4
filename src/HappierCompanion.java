package Project03;

import java.awt.*;

public class HappierCompanion extends CompanionDecorator {
	
	public void draw(Graphics face, Color color, boolean blink) {
		super.draw(face, color, blink);
		
		//mouth
		face.fillArc(145, 145, 100, 100, 180, 180);
	}
}
