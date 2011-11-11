package br.com.eng2d.objeto;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.io.PrintWriter;

import br.com.eng2d.util.Util;

public class Apoio1 extends ObjetoGiratorio {
	private Vetor vetor1;
	private Vetor vetor2;
	private Vetor vetor3;
	private int cartesianoX;
	private int cartesianoY;

	public Apoio1(int cartesianoX, int cartesianoY, int raio) {
		super(Util.getTexto("1_genero"), Util.getCorApoio1());
		this.cartesianoX = cartesianoX;
		this.cartesianoY = cartesianoY;
		vetor1 = new Vetor(raio, 0);
		vetor2 = new Vetor(raio, 0);
		vetor3 = new Vetor(raio, 0);
		vetor1.rotacionar(-90);
		vetor3.rotacionar(180);
	}

	public void rotacionar(int grau) {
		vetor1.rotacionar(grau);
		vetor2.rotacionar(grau);
		vetor3.rotacionar(grau);
	}

	public Objeto clone() {
		Apoio1 apoio = new Apoio1(cartesianoX, cartesianoY, 0);
		apoio.vetor1 = vetor1.clone();
		apoio.vetor2 = vetor2.clone();
		apoio.vetor3 = vetor3.clone();
		return apoio;
	}
	
	public void salvarXML(PrintWriter pw) {
		pw.print("\t<apoio1 cX=\"" + cartesianoX + "\" cY=\"" + cartesianoY + "\" v1X=\"" + vetor1.x + "\" v1Y=\"" + vetor1.y + "\" v2X=\"" + vetor2.x + "\" v2Y=\"" + vetor2.y + "\" v3X=\"" + vetor3.x + "\" v3Y=\"" + vetor3.y + "\"/>\r\n");
	}
	
	public void configEspecifico(float v1X, float v1Y, float v2X, float v2Y, float v3X, float v3Y) {
		vetor1.x = v1X;
		vetor1.y = v1Y;
		vetor2.x = v2X;
		vetor2.y = v2Y;
		vetor3.x = v3X;
		vetor3.y = v3Y;
		desenharPontaApoio = false;
		COR_PONTA = cor;
	}
	
	public void desenhar(Graphics2D g2) {
		if(!isVisivel()) {
			return;
		}
		Color c = g2.getColor();
		g2.setColor(cor);
		
//		g2.draw(new Line2D.Float(cartesianoX, cartesianoY, cartesianoX + vetor1.x, cartesianoY + vetor1.y));
//		g2.draw(new Line2D.Float(cartesianoX, cartesianoY, cartesianoX + vetor2.x, cartesianoY + vetor2.y));
//		g2.draw(new Line2D.Float(cartesianoX, cartesianoY, cartesianoX + vetor3.x, cartesianoY + vetor3.y));
		
		g2.draw(new Line2D.Float(cartesianoX + vetor1.x, cartesianoY + vetor1.y, cartesianoX + vetor2.x, cartesianoY + vetor2.y));
		g2.draw(new Line2D.Float(cartesianoX + vetor1.x, cartesianoY + vetor1.y, cartesianoX + vetor3.x, cartesianoY + vetor3.y));
		
		g2.setStroke(new BasicStroke(5, 0, 0, 1, new float[] { 1, 2 }, 0));
		g2.draw(new Line2D.Float(cartesianoX + vetor3.x, cartesianoY + vetor3.y, cartesianoX + vetor2.x, cartesianoY + vetor2.y));

		g2.setColor(c);
		
		if(desenharPontaApoio) {
			c = g2.getColor();
			g2.setColor(COR_PONTA);
			g2.fill(new Ellipse2D.Float(cartesianoX + vetor1.x - 2, cartesianoY + vetor1.y - 2, 6, 6));
			g2.setColor(c);
		}
	}

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

	public Vetor getVetor3() {
		return vetor3;
	}
}