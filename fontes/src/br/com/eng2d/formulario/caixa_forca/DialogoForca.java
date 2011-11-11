package br.com.eng2d.formulario.caixa_forca;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowAdapter;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;

import br.com.eng2d.objeto.Forca;
import br.com.eng2d.objeto.Ponto;
import br.com.eng2d.superficie.Superficie;
import br.com.eng2d.util.Util;

public class DialogoForca extends JDialog implements ObservadorForca {
	private static final long serialVersionUID = 1L;
	private Superficie superficie;
	private PainelOpcao painelOpcaoForca;
	private JButton buttonFechar = new JButton("Fechar");
	private JButton buttonAdicionar = new JButton("Adicionar");
	
	public DialogoForca(Superficie superficie) {
		this.superficie = superficie;
		montarLayout();
		registrarEvento();
		setAlwaysOnTop(true);
		setVisible(true);
		addWindowListener(new WindowAdapter() {
			public void windowActivated(WindowEvent e) {
				painelOpcaoForca.selecionarGrauPadrao();
			}
		});
	}

	private void registrarEvento() {
		buttonAdicionar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				adicionarForcaNaSuperficie();
			}
		});
		buttonFechar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
	}

	private void adicionarForcaNaSuperficie() {
		boolean adicionado = false;
		if(painelOpcaoForca.getOpcaoForca().isSelecionado()) {
			Forca forca = (Forca) painelOpcaoForca.getOpcaoForca().getForca().clone();
			String valorNewton = painelOpcaoForca.getValorNewton();
			forca.setValorNewton(valorNewton);
			forca.setDesenharPontaApoio(false);
			Ponto pontoSelecionado = (Ponto) superficie.getObjetoSelecionado();
			forca.setCartesianoX((int) (pontoSelecionado.getX() - forca.getVetor1().x));
			forca.setCartesianoY((int) (pontoSelecionado.getY() - forca.getVetor1().y));
			superficie.adicionarObjeto(forca);
			superficie.repaint();
			adicionado = true;
		}

		if(adicionado) {
			if(Util.getBoolean("fecharCaixaAposAdicaoApoio")) {
				dispose();
			}
		} else {
			JOptionPane.showMessageDialog(this, Util.getTexto("msg_nao_selecionado"));
		}
	}

	private void montarLayout() {
		setLayout(null);
		
		Opcao opcaoForca = new Opcao();
		opcaoForca.definirDimensoes(50, 10, 100, 100);
		opcaoForca.criarForca(TipoForca.TIPO_1, this, 50, 50, 30);
		painelOpcaoForca = new PainelOpcao();
		painelOpcaoForca.definirDimensoes(10, 10, 250, 200, opcaoForca);
		add(painelOpcaoForca);

		buttonFechar.setBounds(10, 230, 100, 20);
		buttonAdicionar.setBounds(120, 230, 100, 20);

		add(buttonFechar);
		add(buttonAdicionar);

		setSize(340, 290);
		Util.centralizar(this);
	}

	public Superficie getSuperficie() {
		return superficie;
	}

	public void atualizar(Opcao opcaoForca) {
		painelOpcaoForca.getOpcaoForca().setSelecionado(false);
		opcaoForca.setSelecionado(true);
	}
}