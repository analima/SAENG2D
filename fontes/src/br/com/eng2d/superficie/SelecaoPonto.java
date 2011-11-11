package br.com.eng2d.superficie;

import java.awt.event.MouseEvent;

import br.com.eng2d.objeto.Objeto;
import br.com.eng2d.objeto.Ponto;

//Seleciona um objeto ponto da superfície
public class SelecaoPonto extends Manipulador {

	public SelecaoPonto(Superficie superficie) {
		super(superficie);
	}

	public void mouseClicked(MouseEvent e) {
		int x = e.getX();
		int y = e.getY();
		for(Objeto objeto : superficie.getObjetos()) {
			objeto.setSelecionado(false);
			if(objeto instanceof Ponto) {
				if(objeto.estaNaCordenada(x, y)) {
					objeto.setSelecionado(true);
				}	
			}
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