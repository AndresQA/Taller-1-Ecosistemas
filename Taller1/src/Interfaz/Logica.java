package Interfaz;

import Elementos.Animacion;
import Elementos.Contador;
import Elementos.Meteoro;
import processing.core.PApplet;
import processing.core.PConstants;
import processing.core.PFont;
import processing.core.PImage;

public class Logica {
	PApplet app;
	int pantalla = 4;
	PImage boton, logo, instrucciones, principal;
	Escenario escenario;
	PFont font;

	Animacion fondoinicio;
	Contador contador;
	
	

	public Logica(PApplet app) {
		this.app = app;
		this.fondoinicio = new Animacion(app, "fondo/Capa ", 99);
		this.logo = app.loadImage("images/logo.png");
		this.boton = app.loadImage("images/inicio.png");
		this.instrucciones = app.loadImage("images/instrucciones.png");
		this.principal = app.loadImage("images/principal.jpg");
		this.escenario = new Escenario(this);
		this.contador = new Contador();
		this.font = this.app.createFont("Font/FONT.TTF", 25);

	}

	public void pintar() {
		this.app.textFont(font);
		switch (pantalla) {
		case 0:

			fondoinicio.pintar(this.app.width/2, this.app.height/2);
			this.app.imageMode(PConstants.CORNER);
			app.image(logo, 718, 300);
			app.image(boton, 180, 390);
			//app.text(app.mouseX + " " + app.mouseY, app.mouseX, app.mouseY);

			break;
		case 1:
			fondoinicio.parar();
			app.image(instrucciones, 0, 0);
			break;
		case 2:
			juego();
			break;
			
		case 3:
			app.image(principal, 0, 0);
			app.textAlign(PConstants.CENTER);
			app.textSize(40);
			this.app.text("Jugador 1 Gana", app.width/2, app.height/2+200);
			app.textAlign(PConstants.RIGHT);
			app.textSize(25);
		break;
		
		case 4:
			app.image(principal, 0, 0);
			app.textAlign(PConstants.CENTER);
			app.textSize(40);
			this.app.text("Jugador 2 Gana", app.width/2, app.height/2+200);
			app.textAlign(PConstants.RIGHT);
		break;
		
		case 5:
			app.image(principal, 0, 0);
			app.textAlign(PConstants.CENTER);
			app.textSize(40);
			this.app.text("Empate", app.width/2, app.height/2+200);
			app.textAlign(PConstants.RIGHT);
		break;

		default:
			break;
		}

	}

	public void juego() {
		this.escenario.pintar();
		this.app.text(this.contador.contador, 642, 43);

	}

	public void click() {

		if (pantalla == 1) {
			pantalla++;
			contador.start();
			escenario.lluviaMetoteoros();
			escenario.lluviaMetoteoros();
			
		}

		if (pantalla == 0 & app.mouseX > 180 & app.mouseY > 390 & app.mouseX < 478 & app.mouseY < 468) {
			pantalla++;
		}

	}


	public void keyPressed() {
	
		switch (pantalla) {
		case 2:
			this.escenario.keyPressed();
			//System.out.println("Presion");
			break;
		}

	}

	public void keyReleased() {
		
		switch (pantalla) {
		case 2:
			this.escenario.keyReleased();
			///System.out.println("solto");
			break;
		}

	}
	

	public PApplet getPApplet() {
		return app;
	}
	
	public Escenario getEscenario() {
		return this.escenario;
	}

}
