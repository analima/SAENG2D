package br.com.eng2d.formulario.caixa_forca;

import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import br.com.eng2d.objeto.Forca;

public class PainelOpcao extends JPanel implements ActionListener {
	private static final long serialVersionUID = 1L;
	private Opcao opcaoForca;
	private JTextField textFieldValorNewton = new JTextField();
	private JRadioButton[] radios = new JRadioButton[5]; 
	
	public PainelOpcao() {
		registrarEvento();
		setBorder(BorderFactory.createRaisedBevelBorder());
	}

	private void registrarEvento() {}

	public void definirDimensoes(int x, int y, int largura, int altura, Opcao opcaoForca) {
		this.opcaoForca = opcaoForca;
		setLayout(null);
		add(opcaoForca);
		setBounds(x, y, largura, altura);
		
		textFieldValorNewton.setBounds(20, altura - 70, largura - 40, 20);
		textFieldValorNewton.setText("0");
		
		ButtonGroup grupo = new ButtonGroup();
		
		int rotulo = 0;
		int deslocX = 10;
		
		for(int i=0; i<radios.length; i++) {
			radios[i] = new JRadioButton("" + rotulo);
			rotulo+=90;

			radios[i].setBounds(deslocX, altura - 35, 45, 30);
			radios[i].setMargin(new Insets(0, 0, 0, 0));
			
			grupo.add(radios[i]);
			add(radios[i]);
			
			deslocX += 45;

			radios[i].addActionListener(this);
		}
		
		add(textFieldValorNewton);
	}

	public Opcao getOpcaoForca() {
		return opcaoForca;
	}

	public void actionPerformed(ActionEvent e) {
		JRadioButton radio = (JRadioButton) e.getSource();
		int grau = Integer.parseInt(radio.getText());
		((Forca)opcaoForca.getForca()).zerar();
		((Forca)opcaoForca.getForca()).rotacionar(grau);
		opcaoForca.repaint();
	}
	
	public void selecionarGrauPadrao() {
		radios[0].setSelected(true);
		((Forca)opcaoForca.getForca()).zerar();
		((Forca)opcaoForca.getForca()).rotacionar(0);
		opcaoForca.setSelecionado(true);
		opcaoForca.repaint();
	}
	
	public String getValorNewton() {
		return textFieldValorNewton.getText();
	}
}