package Project03;

import java.awt.*;

public class NeutralCompanion extends CompanionDecorator {
	
	public void draw(Graphics face, Color color, boolean blink) {
		super.draw(face, color, blink);
		
		//mouth
		face.fillRect(145, 215, 100, 10);
		
	}
}
