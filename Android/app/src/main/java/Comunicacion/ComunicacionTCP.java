package Comunicacion;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;

public class ComunicacionTCP extends Thread {

	static Socket socket;
	private Observador observer;
	private InetAddress ip;
	private String info_ip;
	private int info_puerto;

	public ComunicacionTCP(String ipg, int puerto) {
		this.info_ip = ipg;
		info_puerto = puerto;

		if(socket == null){
			this.start();
		}

	}

	@Override
	public void run() {

		try {
			ip = InetAddress.getByName(info_ip);
			socket = new Socket(ip, info_puerto);

		} catch (IOException e) {
			e.printStackTrace();
		}

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

	public void enviar(final String mensaje) {
		new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					DataOutputStream salida = new DataOutputStream(socket.getOutputStream());
					salida.writeUTF(mensaje);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}).start();

	}

	public void setObservador(Observador observer) {
		this.observer = observer;
	}
}
