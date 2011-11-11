package br.com.eng2d.objeto;

import java.awt.Color;
import java.awt.Graphics2D;
import java.io.PrintWriter;

import br.com.eng2d.util.Constante;

//Um objeto abstrato que representa uma figura geométrica
public abstract class Objeto {
	//Cor do objeto
	protected Color cor;
	
	//Selecionado para manipulação
	protected boolean selecionado;

	//Visível na superfície
	protected boolean visivel;
	
	//Os objetos que estiverem selecionados, usarão esta  cor para destacar-se
	public static final Color COR_SELECIONADO = Constante.COR_OBJETO_SELECIONADO;
	
	//Descrição mínima do objeto
	protected final String descricao; 
	
	//As cordenadas do objeto na superfície
	protected int x;
	protected int y;
	
	//Todo objeto ocupa uma área na superfície
	protected int largura;
	protected int altura;
	
	//Todo objeto deverá ser instanciado com um mínino de informações
	public Objeto(String descricao, int x, int y, int largura, int altura, Color cor) {
		this.descricao = descricao;
		this.x = x;
		this.y = y;
		this.largura = largura;
		this.altura = altura;
		this.cor = cor;
		this.visivel = true;
	}
	
	public String getDescricao() {
		return descricao;
	}
	
	//Os objetos concretos usarão o Graphics2D para gerar seus pixels
	public abstract void desenhar(Graphics2D g2);
	
	//Os objetos concretos poderão salvar seu estado(atributos)
	public abstract void salvarXML(PrintWriter pw);

	//Os objetos concretos poderão dizer se determinada cordenada x,y da superfície o seleciona
	public abstract boolean estaNaCordenada(int x, int y);

	//Um objeto será capaz de criar uma cópia de si mesmo
	//Invocar este método em um objeto que não implementa a operação gera uma exceção
	public Objeto clone() {
		throw new RuntimeException("Operação não suportada: " + getClass().getName());
	}
	
	public Color getCor() {
		return cor;
	}

	public void setCor(Color cor) {
		this.cor = cor;
	}

	public boolean isSelecionado() {
		return selecionado;
	}

	public void setSelecionado(boolean selecionado) {
		this.selecionado = selecionado;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getLargura() {
		return largura;
	}

	public void setLargura(int largura) {
		this.largura = largura;
	}

	public int getAltura() {
		return altura;
	}

	public void setAltura(int altura) {
		this.altura = altura;
	}

	public static Color getCorSelecionado() {
		return COR_SELECIONADO;
	}

	public boolean isVisivel() {
		return visivel;
	}

	public void setVisivel(boolean visivel) {
		this.visivel = visivel;
	}
}