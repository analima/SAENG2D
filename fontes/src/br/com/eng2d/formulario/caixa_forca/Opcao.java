package br.com.eng2d.formulario.caixa_forca;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

import br.com.eng2d.objeto.ObjetoGiratorio;
import br.com.eng2d.objeto.Forca;

public class Opcao extends JPanel {
	private static final long serialVersionUID = 1L;
	private boolean selecionado;
	private ObjetoGiratorio forca;
	private ObservadorForca observadorOpcaoForca;
	
	public Opcao() {
		setSelecionado(false);
		setBackground(Color.WHITE);
		registrarEvento();
	}
	
	private void registrarEvento() {
		addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				Opcao.this.observadorOpcaoForca.atualizar(Opcao.this);
			}
		});
	}
	
	public void definirDimensoes(int x, int y, int largura, int altura) {
		setBounds(x, y, largura, altura);
	}
	
	public void criarForca(TipoForca tipoApoio, ObservadorForca observadorOpcaoForca, int cartezianoX, int cartezianoY, int raio) {
		if(tipoApoio == TipoForca.TIPO_1) {
			forca = new Forca(cartezianoX, cartezianoY, raio);
		}
		this.observadorOpcaoForca = observadorOpcaoForca;
	}

	public void paint(Graphics g) {
		super.paint(g);
		Graphics2D g2 = (Graphics2D) g;
		g2.setStroke(new BasicStroke(2));
//		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		forca.desenhar(g2);
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

	public ObjetoGiratorio getForca() {
		return forca;
	}
}