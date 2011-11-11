package br.com.eng2d.formulario.caixa_apoio;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import br.com.eng2d.util.Util;

public class PainelOpcao extends JPanel {
	private static final long serialVersionUID = 1L;
	private Opcao opcaoOpoio;
	private int valorAnteriorSlider;
	private JSlider sliderGrau = new JSlider(0, 360);
	private JTextField textFieldGrau = new JTextField();

	public PainelOpcao() {
		registrarEvento();
		setBorder(BorderFactory.createRaisedBevelBorder());
	}

	private void registrarEvento() {
		valorAnteriorSlider = sliderGrau.getValue();
		sliderGrau.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				JSlider c = (JSlider) e.getSource();
				int grau = c.getValue() - valorAnteriorSlider;
				textFieldGrau.setText("" + c.getValue() + Util.getTexto("simbolo_grau"));
				valorAnteriorSlider = c.getValue();
				opcaoOpoio.getApoio().rotacionar(grau);
				opcaoOpoio.repaint();
			}
		});
	}

	public void definirDimensoes(int x, int y, int largura, int altura, Opcao opcaoOpoio) {
		this.opcaoOpoio = opcaoOpoio;
		setLayout(null);
		add(opcaoOpoio);
		setBounds(x, y, largura, altura);
		textFieldGrau.setEditable(false);
		sliderGrau.setPaintLabels(true);
		sliderGrau.setMajorTickSpacing(90);
		textFieldGrau.setBounds(20, altura - 70, largura - 40, 20);
		sliderGrau.setBounds(10, altura - 35, largura - 20, 30);
		add(textFieldGrau);
		add(sliderGrau);
	}

	public Opcao getOpcaoOpoio() {
		return opcaoOpoio;
	}
}