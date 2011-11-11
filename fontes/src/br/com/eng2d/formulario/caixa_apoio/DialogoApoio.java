package br.com.eng2d.formulario.caixa_apoio;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;

import br.com.eng2d.objeto.Apoio1;
import br.com.eng2d.objeto.Apoio2;
import br.com.eng2d.objeto.Apoio3;
import br.com.eng2d.objeto.Ponto;
import br.com.eng2d.superficie.Superficie;
import br.com.eng2d.util.Util;

public class DialogoApoio extends JDialog implements ObservadorOpcao {
	private static final long serialVersionUID = 1L;
	private Superficie superficie;
	private PainelOpcao painelOpcaoApoio1;
	private PainelOpcao painelOpcaoApoio2;
	private PainelOpcao painelOpcaoApoio3;
	private JButton buttonFechar = new JButton("Fechar");
	private JButton buttonAdicionar = new JButton("Adicionar");
	
	public DialogoApoio(Superficie superficie) {
		this.superficie = superficie;
		montarLayout();
		registrarEvento();
		setAlwaysOnTop(true);
		setVisible(true);
	}

	private void registrarEvento() {
		buttonAdicionar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				adicionarApoioNaSuperficie();
			}
		});
		buttonFechar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
	}

	private void adicionarApoioNaSuperficie() {
		boolean adicionado = false;
		if(painelOpcaoApoio1.getOpcaoOpoio().isSelecionado()) {
			Apoio1 objeto = (Apoio1) painelOpcaoApoio1.getOpcaoOpoio().getApoio().clone();
			objeto.setDesenharPontaApoio(false);
			Ponto pontoSelecionado = (Ponto) superficie.getObjetoSelecionado();
			objeto.setCartesianoX((int) (pontoSelecionado.getX() - objeto.getVetor1().x));
			objeto.setCartesianoY((int) (pontoSelecionado.getY() - objeto.getVetor1().y));
			superficie.adicionarObjeto(objeto);
			superficie.repaint();
			adicionado = true;
		}

		if(painelOpcaoApoio2.getOpcaoOpoio().isSelecionado()) {
			Apoio2 objeto = (Apoio2) painelOpcaoApoio2.getOpcaoOpoio().getApoio().clone();
			objeto.setDesenharPontaApoio(false);
			Ponto pontoSelecionado = (Ponto) superficie.getObjetoSelecionado();
			objeto.setCartesianoX((int) (pontoSelecionado.getX() - objeto.getVetor1().x));
			objeto.setCartesianoY((int) (pontoSelecionado.getY() - objeto.getVetor1().y));
			superficie.adicionarObjeto(objeto);
			superficie.repaint();
			adicionado = true;
		}

		if(painelOpcaoApoio3.getOpcaoOpoio().isSelecionado()) {
			Apoio3 objeto = (Apoio3) painelOpcaoApoio3.getOpcaoOpoio().getApoio().clone();
			objeto.setDesenharPontaApoio(false);
			Ponto pontoSelecionado = (Ponto) superficie.getObjetoSelecionado();
			objeto.setCartesianoX((int) (pontoSelecionado.getX() - objeto.getVetor1().x));
			objeto.setCartesianoY((int) (pontoSelecionado.getY() - objeto.getVetor1().y));
			superficie.adicionarObjeto(objeto);
			superficie.repaint();
			adicionado = true;
		}
		
		if(adicionado) {
			if(Util.getBoolean("fecharCaixaAposAdicaoApoio")) {
				dispose();
			}
		} else {
			JOptionPane.showMessageDialog(this, "Não existe objeto selecionado!");
		}
	}

	private void montarLayout() {
		setLayout(null);
		
		Opcao opcaoOpoio = new Opcao();
		opcaoOpoio.definirDimensoes(50, 10, 100, 100);
		opcaoOpoio.criarApoio(TipoApoio.TIPO_1, this, 50, 50, 30);
		painelOpcaoApoio1 = new PainelOpcao();
		painelOpcaoApoio1.definirDimensoes(10, 10, 200, 200, opcaoOpoio);
		add(painelOpcaoApoio1);

		opcaoOpoio = new Opcao();
		opcaoOpoio.definirDimensoes(50, 10, 100, 100);
		opcaoOpoio.criarApoio(TipoApoio.TIPO_2, this, 50, 50, 30);
		painelOpcaoApoio2 = new PainelOpcao();
		painelOpcaoApoio2.definirDimensoes(220, 10, 200, 200, opcaoOpoio);
		add(painelOpcaoApoio2);
		
		opcaoOpoio = new Opcao();
		opcaoOpoio.definirDimensoes(50, 10, 100, 100);
		opcaoOpoio.criarApoio(TipoApoio.TIPO_3, this, 50, 50, 30);
		painelOpcaoApoio3 = new PainelOpcao();
		painelOpcaoApoio3.definirDimensoes(440, 10, 200, 200, opcaoOpoio);
		add(painelOpcaoApoio3);

		buttonFechar.setBounds(10, 230, 100, 20);
		buttonAdicionar.setBounds(540, 230, 100, 20);

		add(buttonFechar);
		add(buttonAdicionar);

		setSize(660, 290);
		Util.centralizar(this);
	}

	public Superficie getSuperficie() {
		return superficie;
	}

	public void atualizar(Opcao opcaoOpoio) {
		painelOpcaoApoio1.getOpcaoOpoio().setSelecionado(false);
		painelOpcaoApoio2.getOpcaoOpoio().setSelecionado(false);
		painelOpcaoApoio3.getOpcaoOpoio().setSelecionado(false);
		opcaoOpoio.setSelecionado(true);
	}
}