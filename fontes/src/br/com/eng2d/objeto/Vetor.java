package br.com.eng2d.objeto;

import java.awt.Graphics2D;

public class Vetor {
	public final double radiano = Math.PI / 180;
	public float x;
	public float y;
	
	public Vetor(float x, float y) {
		this.x = x;
		this.y = y;
	}

	public void rotacionar(int grau) {
		double cos = Math.cos(grau * radiano);
		double sen = Math.sin(grau * radiano);
		double novoX = x * cos - y * sen;
		double novoY = x * sen + y * cos;
		x = (float) novoX;
		y = (float) novoY;
	}
	
	public void desenharPonta(int cartesianoX, int cartesianoY, Graphics2D g2) {
		g2.drawOval((int)(cartesianoX + x), (int)(cartesianoY + y), 3, 3);
	}
	
	public Vetor clone() {
		return new Vetor(x, y);
	}
}