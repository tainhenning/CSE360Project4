package Project03;

import java.awt.Graphics;

public class SadCompanion extends CompanionDecorator {
	
	public void draw(Graphics face) {
		super.draw(face);
		
		//mouth
		face.fillArc(145, 195, 100, 100, 180, -180);
		
	}
}
