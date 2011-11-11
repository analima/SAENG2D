package br.com.eng2d.objeto;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.io.PrintWriter;

import br.com.eng2d.util.Util;

public class Ponto extends Objeto {
	public static final int lado = 6;
	
	public Ponto(int x, int y) {
		super("Ponto", x, y, 0, 0, Util.getCorPonto());
	}

	public void desenhar(Graphics2D g2) {
		if(!isVisivel()) {
			return;
		}
		Color tmp = g2.getColor();
		g2.setColor(cor);
		if (selecionado) {
			g2.setColor(COR_SELECIONADO);
		}
		g2.fill(new Ellipse2D.Float(x - 3, y - 3, lado, lado));
		g2.setColor(tmp);
	}

	public void salvarXML(PrintWriter pw) {
		pw.print("\t<ponto x=\"" + x + "\" y=\"" + y + "\"/>\r\n");
	}

	public boolean estaNaCordenada(int x, int y) {
		return (this.x + 3 >= x && this.x - 3 <= x) && (this.y + 3 >= y && this.y - 3 <= y);
	}

	public void normalizarXY() {
		int restoX = x % 10; 
		int restoY = y % 10; 
		if(restoX != 0) {
			if(restoX <= 5) {
				x -= restoX;
			} else {
				x += 10 - restoX;
			}
		}
		if(restoY != 0) {
			if(restoY <= 5) {
				y -= restoY;
			} else {
				y += 10 - restoY;
			}
		}
	}
}