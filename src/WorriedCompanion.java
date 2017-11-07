package Project03;

import java.awt.Graphics;

public class WorriedCompanion extends CompanionDecorator {
	
	public void draw(Graphics face) {
		super.draw(face);
		
		//mouth
		face.drawArc(145, 200, 100, 60, 180, -180);
		
	}
}
