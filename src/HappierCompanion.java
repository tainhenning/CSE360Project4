package Project03;

import java.awt.Graphics;

public class HappierCompanion extends CompanionDecorator {
	
	public void draw(Graphics face) {
		super.draw(face);
		
		//mouth
		face.fillArc(145, 145, 100, 100, 180, 180);
	}
}
