package Elementos;

import java.util.ArrayList;

import Interfaz.Escenario;
import processing.core.PApplet;
import processing.core.PConstants;
import processing.core.PImage;

public class Meteoro extends Thread {

	private Escenario escenario;
	private ArrayList<Jugador> jugadores;
	private PApplet app;
	public Elemento pos;

	static PImage vista;

	public boolean isVivo;
	private boolean ejecucion;
	private int velocidad = 5;

	public Meteoro(Escenario escenario, int x, int y) {
		
		this.escenario = escenario;
		this.app = this.escenario.getApp();
		this.pos = new Elemento(x, y, 178, 176);
		this.isVivo = true;
		this.ejecucion = true;
		
		if(this.vista == null) {
			this.vista = this.app.loadImage("images/asteroide.png");
		}

		this.jugadores = this.escenario.getJugadores();
		this.start();
		
	}

	public void pintar() {
		this.app.imageMode(PConstants.CENTER);
		this.app.image(this.vista, this.pos.x, this.pos.y);
		this.app.imageMode(PConstants.CORNER);
	}

	@Override
	public void run() {
		while (isVivo) {
			try {
				if (this.ejecucion) {
					this.movimiento();
				}

				sleep(25);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	public void movimiento() {
		
		this.pos.y += this.velocidad;
		
		if(this.pos.y -this.pos.height > this.app.height) {
			this.isVivo = false;
		}
		
		for (int i = 0; i < this.jugadores.size(); i++) {
			Jugador j = this.jugadores.get(i);

			if(j.pos.isSobreElemento(this.pos)) {
				this.isVivo = false;
				//Quitar vida
				j.quitarVida(-1);
			}
		}
	}
}