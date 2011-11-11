package br.com.eng2d.util;

import java.awt.Color;
import java.awt.event.InputEvent;

//Constantes do sistema
public class Constante {
	public static int BUTTON							= InputEvent.BUTTON1_MASK;
	public static int CTRL 								= BUTTON + InputEvent.CTRL_MASK;
	public static int SHIFT 							= BUTTON + InputEvent.SHIFT_MASK;
	public static int ALT 								= BUTTON + InputEvent.ALT_MASK;
	public static int CTRL_SHIF 						= BUTTON + InputEvent.CTRL_MASK + InputEvent.SHIFT_MASK;
	public static int CTRL_ALT 							= BUTTON + InputEvent.CTRL_MASK + InputEvent.ALT_MASK;
	public static int ALT_SHIFT 						= BUTTON + InputEvent.ALT_MASK  + InputEvent.SHIFT_MASK;
	public static int CTRL_SHIF_ALT 					= BUTTON + InputEvent.CTRL_MASK + InputEvent.SHIFT_MASK + InputEvent.ALT_MASK;
	public static final Color COR_OBJETO_SELECIONADO 	= Util.getCorSelecaoObjeto();
	public static final Color COR_PONTA_OBJETO_GIRAT 	= Util.getCorPontaObjetoGiratorio();
	public static int DOIS 								= 2;
	
	private Constante() {
	}	
}