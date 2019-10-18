package Elementos;

public class Contador extends Thread {
	
	public int contador = 100;
	
	
	 public void run()
	   {
		 while(true) {
	      try {
	    	  
	    	  contador--;
	    	  //System.out.println(contador);
	    	  sleep(1000);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		 }
	   }
}
