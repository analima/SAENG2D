package br.com.eng2d.superficie.builder;

import java.awt.event.MouseEvent;

import br.com.eng2d.objeto.Linha;
import br.com.eng2d.objeto.Ponto;
import br.com.eng2d.superficie.Manipulador;
import br.com.eng2d.superficie.Superficie;

//Cria uma linha com usando clicks
public class LinhaClickBuilder extends Manipulador {
	private int qtdClick;
	private Linha linha;
	
	public LinhaClickBuilder(Superficie superficie) {
		super(superficie);
	}

	public void mouseClicked(MouseEvent e) {
		int x = e.getX();
		int y = e.getY();
		qtdClick++;
		if (qtdClick == 1) {
			if(superficie.isDemarcarLinhaComPonto()) {
				Ponto ponto = new Ponto(x, y);
				ponto.normalizarXY();
				superficie.adicionarObjeto(ponto);
			}
			linha = new Linha(x, y, 0, 0);
			linha.normalizarXY();
		} else if (qtdClick == 2) {
			linha.setX2(x);
			linha.setY2(y);
			linha.normalizarX2Y2();
			superficie.adicionarObjeto(linha);
			if(superficie.isDemarcarLinhaComPonto()) {
				Ponto ponto = new Ponto(x, y);
				ponto.normalizarXY();
				superficie.adicionarObjeto(ponto);
			}
			qtdClick = 0;
		}
		superficie.repaint();
	}

	public void antesSubstituicao() {
		if(superficie.isManterLinhaInacabada()) {
			return;
		}
		if(qtdClick == 1) {
			superficie.excluirUltimoObjetoAdicionado();
		}
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
}