package Interfaz;

import java.util.ArrayList;

import Comunicacion.ComunicacionTCP;
import Comunicacion.ObservadorSever;
import Comunicacion.Receptor;
import Elementos.Bala;
import Elementos.Jugador;
import Elementos.Meteoro;
import Elementos.Player1;
import Elementos.Player2;
import processing.core.PApplet;
import processing.core.PImage;

public class Escenario implements ObservadorSever {
	private Logica log;
	private PApplet app;
	private PImage fondo, marcador;

	ArrayList<Jugador> jugadores;
	ArrayList<Bala> balas;
	ArrayList<Meteoro> meteoros;

	ComunicacionTCP servidor;

	public Escenario(Logica log) {
		this.log = log;
		this.app = this.log.getPApplet();

		this.meteoros = new ArrayList<>();
		this.balas = new ArrayList<Bala>();
		this.jugadores = new ArrayList<Jugador>();

		this.fondo = app.loadImage("images/escenario.png");
		this.marcador = app.loadImage("images/marcador.png");

		jugadores.add(new Player1(this, 100, (this.app.height / 2)));
		jugadores.add(new Player2(this, 1100, (this.app.height / 2)));

		this.servidor = new ComunicacionTCP(5000);
		this.servidor.setObservador(this);
		
	}
	

	
	final public void crearMeteoro() {
		int x = (int)this.app.random(0, this.app.width);
		int y = (int)this.app.random(-200, -50);
		this.meteoros.add(new Meteoro(this, x, y));
	}
	
	public void lluviaMetoteoros() {
		int ran = (int)this.app.random(5, 10);
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				while(true) {
					try {
						crearMeteoro();
						Thread.sleep(ran*1000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				
			}
		}).start();

	}

	public void pintar() {
		this.app.image(this.fondo, 0, 0);
		
		for (Bala bala : this.balas) {
			bala.pintar();
		}
		

		for (Meteoro meteoro : this.meteoros) {
			meteoro.pintar();
		}
		
		
		this.app.image(this.marcador, 0, 0);
		
		for (Jugador jugador : this.jugadores) {
			jugador.pintar();
			jugador.verVida();
		}

		//Remover balas
		for (int i = 0; i < this.balas.size(); i++) {
			Bala bala = this.balas.get(i);
			if (bala.isVivo == false) {
				balas.remove(bala);
			}
		}
		
		//Remover asteorides
		for (int i = 0; i < this.meteoros.size(); i++) {
			Meteoro meteoro = this.meteoros.get(i);
			if (meteoro.isVivo == false) {
				this.meteoros.remove(meteoro);
			}
		}
	}

	public void keyPressed() {
		for (Jugador jugador : jugadores) {
			jugador.presion();
		}

	}

	public void keyReleased() {
		for (Jugador jugador : jugadores) {
			jugador.solto();
		}

	}

	public PApplet getApp() {
		return this.app;
	}

	public Logica getLogica() {
		return this.log;
	}

	public ArrayList<Bala> getBalas() {
		return this.balas;
	}

	public ArrayList<Meteoro> getMeteoros() {
		return this.meteoros;
	}

	public ArrayList<Jugador> getJugadores() {
		return this.jugadores;
	}

	@Override
	public void recibirReceptor(Receptor receptor) {
		System.out.println("Servidor recibido");

	}

}
