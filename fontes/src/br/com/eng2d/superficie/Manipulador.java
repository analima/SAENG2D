package br.com.eng2d.superficie;

import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

import javax.swing.event.MouseInputListener;

//A super classe de todos os manipuladores de eventos ocorridos na superfície de desenho
public abstract class Manipulador implements MouseInputListener, MouseMotionListener {
	//Superfície de desenho
	protected Superficie superficie;

	public Manipulador(Superficie superficie) {
		this.superficie = superficie;
	}

	public abstract void mouseClicked(MouseEvent e);

	public abstract void mouseEntered(MouseEvent e);

	public abstract void mouseExited(MouseEvent e);

	public abstract void mousePressed(MouseEvent e);

	public abstract void mouseReleased(MouseEvent e);

	public abstract void mouseDragged(MouseEvent e);

	public abstract void mouseMoved(MouseEvent e);
	
	public abstract void antesSubstituicao();
}