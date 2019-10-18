package Elementos;

import java.util.ArrayList;

import Interfaz.Escenario;
import processing.core.PApplet;
import processing.core.PConstants;
import processing.core.PImage;

public class Bala extends Thread {

	private Escenario escenario;
	private Jugador player;
	
	private ArrayList<Jugador> jugadores;
	private ArrayList<Meteoro> meteoros;
	
	private PApplet app;
	private Elemento pos;

	private PImage vista;

	public boolean isVivo;
	private boolean ejecucion;
	private int velocidad = 40;

	public Bala(Jugador player, Escenario escenario, int x, int y) {
		this.player = player;
		this.vista = this.player.viewBala;
		this.escenario = escenario;
		this.app = this.escenario.getApp();
		this.pos = new Elemento(x, y, 58, 20);
		this.isVivo = true;
		this.ejecucion = true;

		this.jugadores = this.escenario.getJugadores();
		this.meteoros = this.escenario.getMeteoros();

		
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

		if (this.player.tipo == 1) {
			this.pos.x += this.velocidad;
		} else {
			this.pos.x -= this.velocidad;
		}

		for (int i = 0; i < this.jugadores.size(); i++) {
			Jugador j = this.jugadores.get(i);

			if(this.player != j && j.pos.isSobreElemento(this.pos)) {
				this.isVivo = false;
				j.quitarVida(-1);
			}
		}
		
		for (int i = 0; i < this.meteoros.size(); i++) {
			Meteoro m = this.meteoros.get(i);

			if(m.pos.isSobreElemento(this.pos)) {
				this.isVivo = false;
			}
		}
		
		
		if(this.pos.x >= 0 && this.pos.x < this.app.width) {
			
		}else {
			this.isVivo = false;
		}
		
	}
}
