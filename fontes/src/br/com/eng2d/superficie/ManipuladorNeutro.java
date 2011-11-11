package br.com.eng2d.superficie;

import java.awt.event.MouseEvent;

//Não reaje aos eventos ocorridos na superfície de desenho
public class ManipuladorNeutro extends Manipulador {

	public ManipuladorNeutro(Superficie superficie) {
		super(superficie);
	}

	public void mouseClicked(MouseEvent e) {
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