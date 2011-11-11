package br.com.eng2d.formulario.caixa_apoio;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

import br.com.eng2d.objeto.ObjetoGiratorio;
import br.com.eng2d.objeto.Apoio1;
import br.com.eng2d.objeto.Apoio2;
import br.com.eng2d.objeto.Apoio3;

public class Opcao extends JPanel {
	private static final long serialVersionUID = 1L;
	private boolean selecionado;
	private ObjetoGiratorio apoio;
	private ObservadorOpcao observadorOpcaoApoio;
	
	public Opcao() {
		setSelecionado(false);
		setBackground(Color.WHITE);
		registrarEvento();
	}
	
	private void registrarEvento() {
		addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				Opcao.this.observadorOpcaoApoio.atualizar(Opcao.this);
			}
		});
	}
	
	public void definirDimensoes(int x, int y, int largura, int altura) {
		setBounds(x, y, largura, altura);
	}
	
	public void criarApoio(TipoApoio tipoApoio, ObservadorOpcao observadorOpcaoApoio, int cartezianoX, int cartezianoY, int raio) {
		if(tipoApoio == TipoApoio.TIPO_1) {
			apoio = new Apoio1(cartezianoX, cartezianoY, raio);
		}
		if(tipoApoio == TipoApoio.TIPO_2) {
			apoio = new Apoio2(cartezianoX, cartezianoY, raio);
		}
		if(tipoApoio == TipoApoio.TIPO_3) {
			apoio = new Apoio3(cartezianoX, cartezianoY, raio);
		}
		this.observadorOpcaoApoio = observadorOpcaoApoio;
	}

	public void paint(Graphics g) {
		super.paint(g);
		Graphics2D g2 = (Graphics2D) g;
		g2.setStroke(new BasicStroke(2));
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		apoio.desenhar(g2);
	}

	public boolean isSelecionado() {
		return selecionado;
	}

	public void setSelecionado(boolean selecionado) {
		this.selecionado = selecionado;
		if(this.selecionado) {
			setBorder(BorderFactory.createLineBorder(Color.BLUE));
		} else {
			setBorder(BorderFactory.createEmptyBorder());
		}
	}

	public ObjetoGiratorio getApoio() {
		return apoio;
	}
}