package Interfaz;
import processing.core.PApplet;

public class Main extends PApplet{

	private Logica log;

	public static void main(String[] args) {
		PApplet.main("Interfaz.Main");
	}


	
	public void settings() {
		size(1200,700);		
	}
	@Override
	public void setup() {
		this.log = new Logica(this);

	}
	
	@Override
	public void draw() {
		background(255);
		this.log.pintar();
	
		
		
	}
	@Override
	public void mousePressed() {
		this.log.click();
	}
	
	@Override
	public void keyPressed() {
		this.log.keyPressed();
	}
	
	@Override
	public void keyReleased() {
		this.log.keyReleased();
	}

}
