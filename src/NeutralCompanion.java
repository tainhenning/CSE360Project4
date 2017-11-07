package Project03;

import java.awt.Graphics;

public class NeutralCompanion extends CompanionDecorator {
	
	public void draw(Graphics face) {
		super.draw(face);
		
		//mouth
		face.fillRect(145, 215, 100, 10);
		
	}
}
