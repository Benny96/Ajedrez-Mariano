package GUI;

import javax.swing.JButton;

public class clsCasilla extends JButton {

	
	private int x;
	private int y;
	
	public clsCasilla(int a, int b)
	{
		super();
		y=a;
		x=b;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x=x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y=y;
	}
	
}
