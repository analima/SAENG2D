package br.com.eng2d.objeto;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Line2D;
import java.io.PrintWriter;

import br.com.eng2d.util.Util;

public class Forca extends ObjetoGiratorio {
	private int raio;
	private int grau;
	private Vetor vetor1;
	private Vetor vetor2;
	private int cartesianoX;
	private int cartesianoY;
	private String valorNewton;

	public Forca(int cartesianoX, int cartesianoY, int raio) {
		super(Util.getTexto("forca"), Util.getCorForca());
		this.cartesianoX = cartesianoX;
		this.cartesianoY = cartesianoY;
		vetor1 = new Vetor(raio, 0);
		vetor2 = new Vetor(raio, 0);
		vetor1.rotacionar(90);
		vetor2.rotacionar(-90);
		this.raio = raio;
	}

	public void zerar() {
		vetor1 = new Vetor(raio, 0);
		vetor2 = new Vetor(raio, 0);
		vetor1.rotacionar(-90);
		vetor2.rotacionar(90);
	}
	
	public void rotacionar(int grau) {
		vetor1.rotacionar(grau);
		vetor2.rotacionar(grau);
		this.grau = grau;
	}

	public Objeto clone() {
		Forca forca = new Forca(cartesianoX, cartesianoY, 0);
		forca.grau = grau;
		forca.vetor1 = vetor1.clone();
		forca.vetor2 = vetor2.clone();
		return forca;
	}

	public void salvarXML(PrintWriter pw) {
		pw.print("\t<forca cX=\"" + cartesianoX + "\" cY=\"" + cartesianoY + "\" valorNewton=\"" + valorNewton + "\" raio=\"" + raio + "\" grau=\"" + grau + "\" v1X=\"" + vetor1.x + "\" v1Y=\"" + vetor1.y + "\" v2X=\"" + vetor2.x + "\" v2Y=\"" + vetor2.y + "\"/>\r\n");
	}
	
	public void configEspecifico(String valorNewton, int grau, float v1X, float v1Y, float v2X, float v2Y) {
		this.valorNewton = valorNewton;
		this.grau = grau;
		vetor1.x = v1X;
		vetor1.y = v1Y;
		vetor2.x = v2X;
		vetor2.y = v2Y;
		desenharPontaApoio = false;
		COR_PONTA = cor;
	}
	
	public void desenhar(Graphics2D g2) {
		if(!isVisivel()) {
			return;
		}
		Color c = g2.getColor();
		g2.setColor(cor);
		
		g2.draw(new Line2D.Float(cartesianoX + vetor1.x, cartesianoY + vetor1.y, cartesianoX, cartesianoY));
		g2.draw(new Line2D.Float(cartesianoX + vetor2.x, cartesianoY + vetor2.y, cartesianoX, cartesianoY));
		if(grau == 0 || grau == 360) {
			g2.drawLine((int)(cartesianoX + vetor1.x), (int)(cartesianoY + vetor1.y), (int)(cartesianoX + vetor1.x - 10), (int)(cartesianoY + vetor1.y + 10));
			g2.drawLine((int)(cartesianoX + vetor1.x), (int)(cartesianoY + vetor1.y), (int)(cartesianoX + vetor1.x + 10), (int)(cartesianoY + vetor1.y + 10));
			deslocamentoX = 5;
			deslocamentoY = 0;
		} else if(grau == 90) {
			g2.drawLine((int)(cartesianoX + vetor1.x), (int)(cartesianoY + vetor1.y), (int)(cartesianoX + vetor1.x) - 10, (int)(cartesianoY + vetor1.y - 10));
			g2.drawLine((int)(cartesianoX + vetor1.x), (int)(cartesianoY + vetor1.y), (int)(cartesianoX + vetor1.x) - 10, (int)(cartesianoY + vetor1.y + 10));
			deslocamentoX = -raio;
			deslocamentoY = -5;
		} else if(grau == 180) {
			g2.drawLine((int)(cartesianoX + vetor1.x), (int)(cartesianoY + vetor1.y), (int)(cartesianoX + vetor1.x + 10), (int)(cartesianoY + vetor1.y - 10));
			g2.drawLine((int)(cartesianoX + vetor1.x), (int)(cartesianoY + vetor1.y), (int)(cartesianoX + vetor1.x - 10), (int)(cartesianoY + vetor1.y - 10));
			deslocamentoX = 5;
			deslocamentoY = 0;
		} else if(grau == 270) {
			g2.drawLine((int)(cartesianoX + vetor1.x), (int)(cartesianoY + vetor1.y), (int)(cartesianoX + vetor1.x) + 10, (int)(cartesianoY + vetor1.y - 10));
			g2.drawLine((int)(cartesianoX + vetor1.x), (int)(cartesianoY + vetor1.y), (int)(cartesianoX + vetor1.x) + 10, (int)(cartesianoY + vetor1.y + 10));
			deslocamentoX = raio;
			deslocamentoY = -5;
		}
		
		g2.setColor(c);
		
		if(valorNewton != null) {
			g2.drawString(valorNewton, cartesianoX + deslocamentoX, cartesianoY + deslocamentoY);
		}
	}

	private int deslocamentoX;
	private int deslocamentoY;
	
	public boolean estaNaCordenada(int x, int y) {
		return false;
	}

	public int getCartesianoX() {
		return cartesianoX;
	}

	public void setCartesianoX(int cartesianoX) {
		this.cartesianoX = cartesianoX;
	}

	public int getCartesianoY() {
		return cartesianoY;
	}

	public void setCartesianoY(int cartesianoY) {
		this.cartesianoY = cartesianoY;
	}

	public Vetor getVetor1() {
		return vetor1;
	}

	public Vetor getVetor2() {
		return vetor2;
	}

	public String getValorNewton() {
		return valorNewton;
	}

	public void setValorNewton(String valorNewton) {
		this.valorNewton = valorNewton;
	}
}