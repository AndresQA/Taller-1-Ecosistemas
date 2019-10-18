package Comunicacion;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class Receptor extends Thread {

	private Socket socket;
	private Observador observer;

	public Receptor(Socket socket) {
		this.socket = socket;
		this.start();
	}

	@Override
	public void run() {

		System.out.println("Recibiendo");
		recibir();

	}

	public void recibir() {

		try {
			DataInputStream recibidor = new DataInputStream(socket.getInputStream());
			while (true) {
				System.out.println("Esperando");

				String mensaje = recibidor.readUTF();
				System.out.println("Recibido");
				if (observer != null) {
					observer.mensajeRecibido(mensaje);
				}
				System.out.println(mensaje);
			}
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("Cliente desconectado: " + e.getMessage());
		}

	}

	public void enviar(String mensaje) {
		try {
			DataOutputStream salida = new DataOutputStream(socket.getOutputStream());
			salida.writeUTF(mensaje);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void setObservador(Observador observer) {
		this.observer = observer;
	}
}
