package Elementos;
import processing.core.PApplet;
import processing.core.PConstants;
import processing.core.PImage;

public class Animacion extends Thread{
	private PImage[] imagen;
	private PApplet app;
	private int imagenActual;

	public boolean stop;
	
	
	public Animacion(PApplet app,String ruta, int num){
				this.app = app;
				imagen = new PImage[num];
		for(int i = 0; i < imagen.length; i ++) {
			imagen[i] = app.loadImage(ruta+(i+1)+".png");
		}		
		start();
	}
	
	
	public void run() {
		while (true) {
			try {
				
				if(stop == false) {
				imagenActual ++;}
				if(imagenActual > imagen.length-1) {
					imagenActual = 0;
					
				}
				
				sleep(60);
				
			} catch (Exception e) {
			}
			
		}
		
	}
	
	public void pintar(int x, int y){
		this.app.imageMode(PConstants.CENTER);
		app.image(imagen[imagenActual], x, y);
	}
	
	public void parar() {
		stop = true;
	}

}
