package Elementos;
import Interfaz.Escenario;
import Interfaz.Logica;

public class Player2 extends Jugador {

	public Player2(Escenario escenario, int posX, int posY) {
		super(escenario, posX, posY);
		this.vista = this.app.loadImage("images/j2.png");
		this.tipo = 2;
		this.viewBala = viewB;
		this.viewVida = viewD;
	}
	

	@Override
	public void pressLeft() {
		if ((this.pos.x - this.pos.width/2)- this.velocidad >= (this.app.width/2)) {
			this.pos.x -= this.velocidad;
		}
	}

	@Override
	public void pressRight() {
		if ((this.pos.x + this.pos.width/2) + this.velocidad <= this.app.width) {
			this.pos.x += this.velocidad;
		}
	}
	
	@Override
	public void verVida() {
		for (int i = 0; i < this.vida; i++) {
			this.app.image(this.viewVida, this.app.width - 145 + (i * 50), 12);
		}
	}
}
