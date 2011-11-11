package br.com.eng2d.superficie.builder;

import java.awt.event.MouseEvent;

import br.com.eng2d.objeto.Linha;
import br.com.eng2d.objeto.Ponto;
import br.com.eng2d.superficie.Manipulador;
import br.com.eng2d.superficie.Superficie;
import br.com.eng2d.util.Constante;

//Criar a linha no arrasto do mouse
public class LinhaArrastoBuilder extends Manipulador {

	public LinhaArrastoBuilder(Superficie superficie) {
		super(superficie);
	}

	public void mouseClicked(MouseEvent e) {
	}

	public void mousePressed(MouseEvent e) {
		int x = e.getX();
		int y = e.getY();
		if(superficie.isDemarcarLinhaComPonto()) {
			Ponto ponto = new Ponto(x, y);
			ponto.normalizarXY();
			superficie.adicionarObjeto(ponto);
		}
		Linha linha = new Linha(x, y, 0, 0);
		linha.normalizarXY();
		linha.setVisivel(false);
		superficie.adicionarObjeto(linha);
		superficie.repaint();
	}
	
	public void mouseDragged(MouseEvent e) {
		if (superficie.getObjetoDoTopo() instanceof Linha) {
			Linha linha = (Linha) superficie.getObjetoDoTopo();
			linha.setVisivel(true);
			linha.setX2(e.getX());
			linha.setY2(e.getY());
			if(e.getModifiers() == Constante.SHIFT) {
				linha.igualarX();
			} else if(e.getModifiers() == Constante.ALT) {
				linha.igualarY();
			} else if(e.getModifiers() == Constante.CTRL_ALT) {
				linha.grau45();
			} else if(e.getModifiers() == Constante.CTRL_SHIF) {
				linha.grau315();
			}
			superficie.repaint();
		}
	}

	public void mouseEntered(MouseEvent e) {
	}

	public void mouseExited(MouseEvent e) {
	}

	public void mouseMoved(MouseEvent e) {
	}

	public void mouseReleased(MouseEvent e) {
		if (superficie.getObjetoDoTopo() instanceof Linha) {
			Linha linha = (Linha) superficie.getObjetoDoTopo();
			linha.normalizarX2Y2();
			if(superficie.isDemarcarLinhaComPonto()) {
				Ponto ponto = new Ponto(e.getX(), e.getY());
				ponto.normalizarXY();
				superficie.adicionarObjeto(ponto);
			}
			superficie.repaint();
		}
	}

	public void antesSubstituicao() {
	}
}