package Project03;

import java.awt.*;

public class CompanionDecorator implements Companion {
	protected Companion c;
	
	public void add(Companion c) {
		this.c = c;
	}
	
	public void draw(Graphics face) {
		this.c.draw(face);
	}
}
