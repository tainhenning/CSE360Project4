package Project03;

import java.awt.*;

public class HappyCompanion extends CompanionDecorator {
	
	public void draw(Graphics face, Color color, boolean blink) {
		super.draw(face, color, blink);
		
		//mouth
		face.fillArc(145, 195, 100, 50, 180, 180);
		
	}
}
