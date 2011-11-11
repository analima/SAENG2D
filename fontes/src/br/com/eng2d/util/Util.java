package br.com.eng2d.util;

import java.awt.Color;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import br.com.eng2d.superficie.Superficie;
import br.com.eng2d.xml.XML;

public class Util {
	private static ResourceBundle bundle;

	private Util() {
	}
	
	public static void centralizar(Component componente) {
		Dimension dimensionVideo = Toolkit.getDefaultToolkit().getScreenSize();
		Dimension dimensionComponente = componente.getSize();
		componente.setLocation((dimensionVideo.width - dimensionComponente.width) / 2, (dimensionVideo.height - dimensionComponente.height) / 2 - 40);
	}
	
	public static Color getCorSelecaoObjeto() {
		int r = Integer.parseInt(getString("corSelecaoR"));
		int g = Integer.parseInt(getString("corSelecaoG"));
		int b = Integer.parseInt(getString("corSelecaoB"));
		return new Color(r, g, b);
	}

	public static Color getCorPonto() {
		int r = Integer.parseInt(getString("corPontoR"));
		int g = Integer.parseInt(getString("corPontoG"));
		int b = Integer.parseInt(getString("corPontoB"));
		return new Color(r, g, b);
	}

	public static Color getCorForca() {
		int r = Integer.parseInt(getString("corForcaR"));
		int g = Integer.parseInt(getString("corForcaG"));
		int b = Integer.parseInt(getString("corForcaB"));
		return new Color(r, g, b);
	}

	public static Color getCorApoio1() {
		int r = Integer.parseInt(getString("corApoio1R"));
		int g = Integer.parseInt(getString("corApoio1G"));
		int b = Integer.parseInt(getString("corApoio1B"));
		return new Color(r, g, b);
	}

	public static Color getCorApoio2() {
		int r = Integer.parseInt(getString("corApoio2R"));
		int g = Integer.parseInt(getString("corApoio2G"));
		int b = Integer.parseInt(getString("corApoio2B"));
		return new Color(r, g, b);
	}

	public static Color getCorApoio3() {
		int r = Integer.parseInt(getString("corApoio3R"));
		int g = Integer.parseInt(getString("corApoio3G"));
		int b = Integer.parseInt(getString("corApoio3B"));
		return new Color(r, g, b);
	}
	
	public static Color getCorLinha() {
		int r = Integer.parseInt(getString("corLinhaR"));
		int g = Integer.parseInt(getString("corLinhaG"));
		int b = Integer.parseInt(getString("corLinhaB"));
		return new Color(r, g, b);
	}

	public static Color getCorPontaObjetoGiratorio() {
		int r = Integer.parseInt(getString("corPontaObjetoGiratorioR"));
		int g = Integer.parseInt(getString("corPontaObjetoGiratorioG"));
		int b = Integer.parseInt(getString("corPontaObjetoGiratorioB"));
		return new Color(r, g, b);
	}

	public static Color getCorGrade() {
		int r = Integer.parseInt(getString("corGradeR"));
		int g = Integer.parseInt(getString("corGradeG"));
		int b = Integer.parseInt(getString("corGradeB"));
		return new Color(r, g, b);
	}

	public static Color getCorSuperficie() {
		int r = Integer.parseInt(getString("corSuperficieR"));
		int g = Integer.parseInt(getString("corSuperficieG"));
		int b = Integer.parseInt(getString("corSuperficieB"));
		return new Color(r, g, b);
	}
	
	public static Boolean getBoolean(String chave) {
		return new Boolean(bundle.getString(chave));
	}

	public static String getTexto(String chave) {
		return bundle.getString(chave);
	}

	public static int getInt(String chave) {
		return Integer.parseInt(bundle.getString(chave));
	}
	
	public static String getString(String chave) {
		return bundle.getString(chave);
	}
	
	public static String getDiretorioAplicacao() {
		File file = new File(".");
		String caminho = file.getAbsolutePath();
		int pos = caminho.lastIndexOf(".");
		return caminho.substring(0, pos);
	}
	
	static {
		bundle = ResourceBundle.getBundle("configuracoes");
	}
	
	public static void abrirArquivo(Superficie superficie, File file) {
		try {
			InputStream is = new FileInputStream(file);
			SAXParserFactory factory = SAXParserFactory.newInstance();
			SAXParser parser = factory.newSAXParser();
			XML xml = new XML();
			parser.parse(file, xml);
			superficie.recuperarObjeto(xml);
			superficie.setArquivo(file);
			superficie.setAlterado(false);
			superficie.repaint();
			is.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void salvarArquivo(Superficie superficie, File file) {
		try {
			PrintWriter pw = new PrintWriter(file);
			criarCabecalho(pw);
			superficie.salvarXML(pw);
			finalizar(pw);
			superficie.setArquivo(file);
			superficie.setAlterado(false);
			pw.close();
			mensagem(superficie.getFormularioPrincipal(), "Arquivo salvo!");
		} catch (Exception e) {
			mensagem(superficie.getFormularioPrincipal(), "Erro ao tentar salvar o arquivo!\r\n" + file);
		}
	}

	public static void mensagem(Component componente, String msg) {
		JOptionPane.showMessageDialog(componente, msg);
	}
	
	private static void criarCabecalho(PrintWriter pw) {
		pw.println("<?xml version='1.0' encoding='UTF-8'?>");
		pw.println("<saeng2d xmlns=\"saeng2d.com.br\">");
	}

	private static void finalizar(PrintWriter pw) {
		pw.println("</saeng2d>");
	}	
}