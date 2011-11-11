package br.com.eng2d.superficie.builder;

import java.awt.event.MouseEvent;

import br.com.eng2d.objeto.Ponto;
import br.com.eng2d.superficie.Manipulador;
import br.com.eng2d.superficie.Superficie;
import br.com.eng2d.util.Constante;

//Cria pontos na superfície a medida que houver clicks
public class PontoBuilder extends Manipulador {

	public PontoBuilder(Superficie superficie) {
		super(superficie);
	}

	public void mouseClicked(MouseEvent e) {
		if(superficie.isCriarPontoComDoisClicks()) {
			if(e.getClickCount() == Constante.DOIS) {
				Ponto ponto = new Ponto(e.getX(), e.getY());
				ponto.normalizarXY();
				superficie.adicionarObjeto(ponto);
			}
		} else {
			Ponto ponto = new Ponto(e.getX(), e.getY());
			ponto.normalizarXY();
			superficie.adicionarObjeto(ponto);
		}
		superficie.repaint();
	}

	public void mouseDragged(MouseEvent e) {
	}

	public void mouseEntered(MouseEvent e) {
	}

	public void mouseExited(MouseEvent e) {
	}

	public void mouseMoved(MouseEvent e) {
	}

	public void mousePressed(MouseEvent e) {
	}

	public void mouseReleased(MouseEvent e) {
	}

	public void antesSubstituicao() {
	}
}