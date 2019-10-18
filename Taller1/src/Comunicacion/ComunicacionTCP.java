package Comunicacion;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;

public class ComunicacionTCP extends Thread{

	private ArrayList<Receptor> receptores;
	private ServerSocket servidor;
	private InetAddress ip;
	private ObservadorSever observador;
	
	static ComunicacionTCP globalServidor;
	
	static public ComunicacionTCP getIntance(int puerto) {
		if (globalServidor == null) {
			globalServidor = new ComunicacionTCP( puerto);
		}
		return globalServidor;
	}
	
	static public ComunicacionTCP getIntance() {
		return globalServidor;
	}

	public ComunicacionTCP( int puerto) {
		
		try {
			this.receptores = new ArrayList<>();
			//this.ip = InetAddress.getByName(IP);
			this.servidor = new ServerSocket(puerto);
			this.start();
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	@Override
	public void run() {
		while(true) {
			
			try {
				
				Socket socket = this.servidor.accept();
				Receptor receptor = new Receptor(socket);
				
				if(this.observador != null) {
					this.observador.recibirReceptor(receptor);					
				}
				
				this.receptores.add(receptor);
				
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		}
	}
	
	public void setObservador(ObservadorSever observer) {
		this.observador = observer;
	}
	

}
