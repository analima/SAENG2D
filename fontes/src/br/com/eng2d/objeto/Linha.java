package br.com.eng2d.objeto;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Line2D;
import java.io.PrintWriter;

import br.com.eng2d.util.Util;

public class Linha extends Objeto {
	private int x2;
	private int y2;
	
	public Linha(int x, int y, int x2, int y2) {
		super("Linha", x, y, 0, 0, Util.getCorLinha());
		this.x2 = x2;
		this.y2 = y2;
	}

	public boolean estaNaCordenada(int x, int y) {
		return false;
	}

	public void salvarXML(PrintWriter pw) {
		pw.print("\t<linha x=\"" + x + "\" y=\"" + y + "\" x2=\"" + x2 + "\" y2=\"" + y2 + "\"/>\r\n");
	}

	public void desenhar(Graphics2D g2) {
		if(!isVisivel()) {
			return;
		}
		Color c = g2.getColor();
		g2.setColor(cor);
		if(selecionado) {
			g2.setColor(COR_SELECIONADO);
		}
		g2.setStroke(new BasicStroke(2));
		g2.draw(new Line2D.Float(x, y, x2, y2));
		g2.setColor(c);
	}

	public int getX2() {
		return x2;
	}

	public void setX2(int x2) {
		this.x2 = x2;
	}

	public int getY2() {
		return y2;
	}

	public void setY2(int y2) {
		this.y2 = y2;
	}
	
	public void normalizarXY() {
		int restoX = x % 10;
		int restoY = y % 10;
		if (restoX != 0) {
			if (restoX <= 5) {
				x -= restoX;
			} else {
				x += 10 - restoX;
			}
		}
		if (restoY != 0) {
			if (restoY <= 5) {
				y -= restoY;
			} else {
				y += 10 - restoY;
			}
		}
	}
	
	public void normalizarX2Y2() {
		int restoX = x2 % 10;
		int restoY = y2 % 10;
		if (restoX != 0) {
			if (restoX <= 5) {
				x2 -= restoX;
			} else {
				x2 += 10 - restoX;
			}
		}
		if (restoY != 0) {
			if (restoY <= 5) {
				y2 -= restoY;
			} else {
				y2 += 10 - restoY;
			}
		}
	}
	
	public void igualarX() {
		x2 = x;
	}

	public void igualarY() {
		y2 = y;
	}

	public void grau45() {
		int tamanho = 0;
		if (y > y2) {
			tamanho = y - y2;
		} else {
			tamanho = y2 - y;
		}
		x2 = x + tamanho + 30;
	}

	public void grau315() {
		int tamanho = 0;
		if (y > y2) {
			tamanho = y - y2;
		} else {
			tamanho = y2 - y;
		}
		x2 = x - tamanho;
	}	
}