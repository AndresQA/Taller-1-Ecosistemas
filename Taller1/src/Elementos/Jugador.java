package Elementos;

import java.util.ArrayList;

import Interfaz.Escenario;
import Interfaz.Logica;
import processing.core.PApplet;
import processing.core.PConstants;
import processing.core.PImage;

public abstract class Jugador extends Thread {

	protected PApplet app;
	protected Logica log;
	protected boolean isVivo;
	public int vida;
	public Elemento pos;
	private boolean ejecucion = false;
	protected PImage vista;
	protected boolean left, right, up, down;

	protected int carga = 0;

	protected int velocidad;

	protected ArrayList<Bala> balas;
	protected Escenario escenario;

	public static PImage viewA, viewB, viewC, viewD;

	protected PImage viewBala, viewVida;
	

	protected int tipo;

	public Jugador(Escenario escenario, int posX, int posY) {
		this.escenario = escenario;
		this.log = escenario.getLogica();
		this.app = this.log.getPApplet();
		this.vida = 3;
		this.isVivo = true;

		this.velocidad = 10;
		this.pos = new Elemento(posX, posY, 99, 74);
		this.ejecucion = true;
		this.start();

		if (viewA == null) {
			//Jugadores
			viewA = this.app.loadImage("images/bullet.png");
			viewB = this.app.loadImage("images/bullet2.png");
			//Vidas
			viewC = this.app.loadImage("images/j1hp.png");
			viewD = this.app.loadImage("images/j2hp.png");
		}

		this.balas = this.escenario.getBalas();

	}

	public void disparar() {
		this.balas.add(new Bala(this, this.escenario, this.pos.x, this.pos.y));
	}

	// public abstract void pintar();

	public void run() {

		while (isVivo) {
			try {
				if (ejecucion) {
					movimiento();
				}

				sleep(25);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

	}

	public void pintar() {
		this.app.imageMode(PConstants.CENTER);
		this.app.image(this.vista, this.pos.x, this.pos.y);
		this.app.imageMode(PConstants.CORNER);
	}
	
	public abstract void verVida();

	public void presion() {

		if (this.app.key == 'w') {
			this.up = true;
		}

		if (this.app.key == 's') {
			this.down = true;
		}

		if (this.app.key == 'a') {
			this.left = true;
		}

		if (this.app.key == 'd') {
			this.right = true;
		}

		if (this.app.key == 'd') {
			this.right = true;
		}

		if (this.carga == 0) {
			this.carga = 1;
			this.disparar();
		}

	}

	public void solto() {

		if (this.app.key == 'w') {
			this.up = false;
		}

		if (this.app.key == 's') {
			this.down = false;
		}

		if (this.app.key == 'a') {
			this.left = false;
		}

		if (this.app.key == 'd') {
			this.right = false;
		}
	}

	public void movimiento() {

		if (this.up) {
			this.pressUp();
		}

		if (this.down) {
			this.pressDown();
		}

		if (this.left) {
			this.pressLeft();
		}

		if (this.right) {
			this.pressRight();
		}

		if (this.carga > 0 && this.carga < 30) {
			this.carga++;
		} else {
			this.carga = 0;
		}

	}

	private void pressUp() {
		if ((this.pos.y - this.pos.height / 2) - this.velocidad >= 70) {
			this.pos.y -= this.velocidad;
		}
	}

	private void pressDown() {
		if ((this.pos.y + this.pos.height / 2) + this.velocidad <= this.app.height) {
			this.pos.y += this.velocidad;
		}

	}

	public void quitarVida(int valor) {
		this.vida += valor;
	}

	public abstract void pressLeft();

	public abstract void pressRight();

}
