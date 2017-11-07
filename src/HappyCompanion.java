package Project03;

import java.awt.*;

public class HappyCompanion extends CompanionDecorator {
	
	public void draw(Graphics face) {
		super.draw(face);
		
		//mouth
		face.fillArc(145, 195, 100, 50, 180, 180);
		
	}
}
