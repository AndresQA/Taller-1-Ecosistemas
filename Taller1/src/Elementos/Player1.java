package Elementos;

import Interfaz.Escenario;
import Interfaz.Logica;
import processing.core.PImage;

public class Player1 extends Jugador {

	public Player1(Escenario escenario, int posX, int posY) {
		super(escenario, posX, posY);
		this.vista = this.app.loadImage("images/j1.png");
		this.tipo = 1;

		this.viewBala = viewA;
		this.viewVida = viewC;
	}

	@Override
	public void pressLeft() {
		if ((this.pos.x - this.pos.width / 2) - this.velocidad >= 0) {
			this.pos.x -= this.velocidad;
		}
	}

	@Override
	public void pressRight() {
		if ((this.pos.x + this.pos.width / 2) + this.velocidad <= (this.app.width / 2)) {
			this.pos.x += this.velocidad;
		}
	}

	@Override
	public void verVida() {
		for (int i = 0; i < this.vida; i++) {
			this.app.image(this.viewVida, 78 + (i * 50), 12);
		}
	}

}
