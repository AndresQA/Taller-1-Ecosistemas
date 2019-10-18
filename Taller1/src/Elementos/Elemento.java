package Elementos;

public class Elemento {
 public int x, y, width, height;
 
	
	public Elemento(int posX, int posY, int width, int height) {
		this.x = posX;
		this.y = posY;
		this.width = width;
		this.height = height;
		
	}
	
	public boolean isSobre2(int posX, int posY) {
		boolean sobre = false;
		if(this.x > posX && this.x + this.width < posX && this.y > posY && this.y + this.height < posY) {
			sobre = true;
		}
		return sobre;
	}
	
	public boolean isSobre(int posX, int posY) {
		boolean sobre = false;
		if((this.x - this.width/2) > posX && (this.x + this.width/2) < posX && (this.y - this.height/2) > posY && (this.y + this.height/2) < posY) {
			sobre = true;
		}
		return sobre;
	}
	
	public boolean isSobreElemento(Elemento elemento) {
		boolean sobre = false;
		if(elemento.x > (this.x - this.width/2) && elemento.x < (this.x + this.width/2)&& 
				 elemento.y > (this.y - this.height/2) && elemento.y < (this.y + this.height/2)  ) {
			sobre = true;
		}
		return sobre;
	}
	
	public boolean isSobreElemento2(Elemento elemento) {
		boolean sobre = false;
		if(this.x > elemento.x && (this.x + this.width) < elemento.x ) {
			sobre = true;
		}
		return sobre;
	}
	
	
}
