package br.com.eng2d.zmain;

import br.com.eng2d.formulario.FormularioPrincipal;

public class MainEng2D {
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FormularioPrincipal().setVisible(true);
            }
        });
    }
}