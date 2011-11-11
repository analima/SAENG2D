package br.com.eng2d.objeto;

import java.awt.Color;

import br.com.eng2d.util.Constante;
import br.com.eng2d.util.Util;

public abstract class ObjetoGiratorio extends Objeto {
	protected boolean desenharPontaApoio = Util.getBoolean("desenharPontaApoio");
	protected Color COR_PONTA = Constante.COR_PONTA_OBJETO_GIRAT; 
	
	public ObjetoGiratorio(String descricao, Color cor) {
		super(descricao, 0, 0, 0, 0, cor);
	}

	public abstract void rotacionar(int grau);

	public boolean isDesenharPontaApoio() {
		return desenharPontaApoio;
	}

	public void setDesenharPontaApoio(boolean desenharPontaApoio) {
		this.desenharPontaApoio = desenharPontaApoio;
	}
}