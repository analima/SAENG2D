package br.com.eng2d.superficie;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Stroke;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.File;
import java.io.PrintWriter;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.filechooser.FileFilter;

import br.com.eng2d.formulario.FormularioPrincipal;
import br.com.eng2d.objeto.Objeto;
import br.com.eng2d.util.Util;
import br.com.eng2d.xml.XML;

//SUPERFÍCIE DE CRIAÇÃO E MANIPULAÇÃO DE OBJETOS
public class Superficie extends JPanel implements MouseListener, MouseMotionListener {
	private static final long serialVersionUID = 1L;
	//Objetos adicionados na superfície
	private Objeto[] objetos = new Objeto[0];
	
	//Aponta sempre para o objeto na última posição do array
	private Objeto objetoDoTopo;

	//Qualquer evento ocorrido na superfície será resolvido por algum Objeto Manipulador de Eventos
	private Manipulador manipulador = new ManipuladorNeutro(this);//Este ignora todos os eventos
	
	//Configure verdadeiro para desenhar uma grade na superfície
	private boolean desenharGrade = Util.getBoolean("desenharGrade");

	//Configure verdadeiro para desenhar uma bola em cada ponta da linha criada
	private boolean demarcarLinhaComPonto = Util.getBoolean("demarcarLinhaComPonto");

	//Configure verdadeiro para não excluir uma linha inacabada(apenas um click)
	private boolean manterLinhaInacabada = Util.getBoolean("manterLinhaInacabada");

	//Configure verdadeiro para criar pontos apenas dando duplo click na superfície
	private boolean criarPontoComDoisClicks = Util.getBoolean("criarPontoComDoisClicks");
	
	//Cor da grade
	private final Color COR_GRADE = Util.getCorGrade();
	
	//Largura padrão das linhas dos objetos
	private final Stroke LARGURA_LINHA = new BasicStroke(Util.getInt("larguraLinha"));
	
	//Indicar alterações na superfície
	private boolean alterado;
	
	//Arquivo xml com os objetos da aplicação
	private File arquivo;

	//Referência ao formulário principal da aplicação
	private final FormularioPrincipal formularioPrincipal;
	
	//Construtor
	public Superficie(FormularioPrincipal formularioPrincipal) {
		this.formularioPrincipal = formularioPrincipal;
		setBackground(Util.getCorSuperficie());
		registrarEvento();
	}
	
	//Ativar os ouvintes de eventos
	private void registrarEvento() {
		addMouseListener(this);
		addMouseMotionListener(this);
	}

	//Desenhar toda a área da superfície
	public void paint(Graphics g) {
		super.paint(g);
		if(desenharGrade) {
			int largura = getWidth();
			int altura = getHeight();
			Color c = g.getColor();
			g.setColor(COR_GRADE);
			for(int x=0; x<largura; x+=10) {
				g.drawLine(x, 0, x, altura);
			}
			for(int y=0; y<altura; y+=10) {
				g.drawLine(0, y, largura, y);
			}
			g.setColor(c);
		}
		Graphics2D g2 = (Graphics2D) g;
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		for(Objeto objeto : objetos) {	
			g2.setStroke(LARGURA_LINHA);
			objeto.desenhar(g2);
		}
	}

	public void salvar(boolean chamarNovo, boolean chamarAbrir) {
		if(arquivo != null) {
			Util.salvarArquivo(this, arquivo);
		} else {
			JFileChooser fileChooser = new JFileChooser();
			fileChooser.setFileSelectionMode(Util.getInt("modoSelecaoEscolherArquivoSalvar"));
			fileChooser.setFileFilter(new FileFilter() {
				public String getDescription() {
					return "SAENG2D";
				}
				public boolean accept(File pathname) {
					return pathname.getName().endsWith("saeng2d.xml");
				}
			});
			int status = fileChooser.showSaveDialog(formularioPrincipal);
			while (status == JFileChooser.APPROVE_OPTION) {
				File file = fileChooser.getSelectedFile();
				if (file != null) {
					if(file.exists()) {
						int i = JOptionPane.showConfirmDialog(formularioPrincipal, "O arquivo já existe!\r\nDESEJA SUBSCREVE-LO?", "Atenção", JOptionPane.OK_OPTION);
						if(i == JOptionPane.YES_OPTION) {
							Util.salvarArquivo(this, file);
							status = JFileChooser.CANCEL_OPTION;
						} else {
							fileChooser.setCurrentDirectory(file);
							status = fileChooser.showSaveDialog(formularioPrincipal);
						}
					} else {
						Util.salvarArquivo(this, file);
						status = JFileChooser.CANCEL_OPTION;
					}
				} else {
					status = JFileChooser.ERROR_OPTION;
				}
			}
		}
		if(chamarNovo) {
			alterado = false;
			novo();
		} else if(chamarAbrir) {
			alterado = false;
			abrir();
		}
	}
	
	public void novo() {
		if(alterado) {
			int status = confirmar();
			if(status == JOptionPane.YES_OPTION) {
				salvar(true, false);
			} else {
				excluirTodos();
				setArquivo(null);
				alterado = false;
			}
		} else {
			excluirTodos();
			setArquivo(null);
			alterado = false;
		}
		repaint();
	}

	public void abrir() {
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setFileSelectionMode(Util.getInt("modoSelecaoEscolherArquivoAbrir"));
		fileChooser.setFileFilter(new FileFilter() {
			public String getDescription() {
				return "SAENG2D";
			}
			public boolean accept(File pathname) {
				return pathname.getName().endsWith("saeng2d.xml");
			}
		});
		if(alterado) {
			int status = confirmar();
			if(status == JOptionPane.YES_OPTION) {
				salvar(false, true);
			} else {
				status = fileChooser.showOpenDialog(formularioPrincipal);
				if (status == JFileChooser.APPROVE_OPTION) {
					File file = fileChooser.getSelectedFile();
					if (file != null) {
						Util.abrirArquivo(this, file);
					}
				}
			}
		} else {
			int status = fileChooser.showOpenDialog(formularioPrincipal);
			if (status == JFileChooser.APPROVE_OPTION) {
				File file = fileChooser.getSelectedFile();
				if (file != null) {
					Util.abrirArquivo(this, file);
				}
			}
		}
		repaint();
	}
	
	private int confirmar() {
		return JOptionPane.showConfirmDialog(formularioPrincipal, "A superfície foi alterada!\r\nDESEJA SALVAR ANTES DE EXECUTAR ESTA OPERAÇÃO?", "Atenção", JOptionPane.OK_OPTION);
	}
	
	public void salvarComo() {
		File file = getArquivo();
		boolean ehNovo = file == null;
		arquivo = null;
		salvar(false, false);
		if(!ehNovo && getArquivo() == null) {
			setArquivo(file);
		}
	}
	
	//Excluir todos objetos da superfície
	public void excluirTodos() {
		objetos = new Objeto[0];
		alteracao();
	}
	
	private void alteracao() {
		alterado = true;
	}

	//Armazenar objetos
	public void adicionarObjeto(Objeto objeto) {
		if(objeto == null) {
			return;
		}
		Objeto[] temp = objetos;
		objetos	= new Objeto[temp.length + 1];
		System.arraycopy(temp, 0, objetos, 0, temp.length);
		objetos[objetos.length -1] = objeto;
		objetoDoTopo = objeto;
		alteracao();
	}
	
	//O último objeto do array será excluído
	public void excluirUltimoObjetoAdicionado() {
		if(objetos.length == 0) {
			return;
		}
		Objeto[] temp = new Objeto[objetos.length - 1];
		System.arraycopy(objetos, 0, temp, 0, objetos.length - 1);
		objetos = temp;
		objetoDoTopo = objetos[objetos.length - 1];
		alteracao();
	}

	//Salvar objetos da superfície(xml)
	public void salvarXML(PrintWriter pw) {
		for(Objeto objeto : objetos) {
			objeto.salvarXML(pw);
		}
	}

	//Recuperar os objetos do xml
	public void recuperarObjeto(XML xml) {
		for(Objeto objeto : xml.getObjetos()) {
			adicionarObjeto(objeto);
		}
	}

	//Todos os eventos são delegados ao Manipulador
	public void mouseClicked(MouseEvent e) {
		manipulador.mouseClicked(e);
	}

	public void mouseEntered(MouseEvent e) {
		manipulador.mouseEntered(e);
	}

	public void mouseExited(MouseEvent e) {
		manipulador.mouseExited(e);
	}

	public void mousePressed(MouseEvent e) {
		manipulador.mousePressed(e);
	}

	public void mouseReleased(MouseEvent e) {
		manipulador.mouseReleased(e);
	}

	public void mouseDragged(MouseEvent e) {
		manipulador.mouseDragged(e);
	}

	public void mouseMoved(MouseEvent e) {
		manipulador.mouseMoved(e);
	}

	public void setManipulador(Manipulador manipulador) {
		this.manipulador.antesSubstituicao();
		this.manipulador = manipulador;
		repaint();
	}

	public Objeto[] getObjetos() {
		return objetos;
	}

	public Objeto getObjetoDoTopo() {
		return objetoDoTopo;
	}

	public boolean isDesenharGrade() {
		return desenharGrade;
	}

	public void setDesenharGrade(boolean desenharGrade) {
		this.desenharGrade = desenharGrade;
	}

	public boolean isDemarcarLinhaComPonto() {
		return demarcarLinhaComPonto;
	}

	public void setDemarcarLinhaComPonto(boolean demarcarLinhaComPonto) {
		this.demarcarLinhaComPonto = demarcarLinhaComPonto;
	}

	public boolean isManterLinhaInacabada() {
		return manterLinhaInacabada;
	}

	public void setManterLinhaInacabada(boolean manterLinhaInacabada) {
		this.manterLinhaInacabada = manterLinhaInacabada;
	}

	public Manipulador getManipulador() {
		return manipulador;
	}

	public Color getCOR_GRADE() {
		return COR_GRADE;
	}

	public Stroke getLARGURA_LINHA() {
		return LARGURA_LINHA;
	}	
	
	//O primeiro objeto selecionado que encontrar no array será retornado
	//Navegação do início para o final
	public Objeto getObjetoSelecionado() {
		for(Objeto objeto : objetos) {
			if(objeto.isSelecionado()) {
				return objeto;
			}
		}
		return null;
	}

	public boolean isCriarPontoComDoisClicks() {
		return criarPontoComDoisClicks;
	}

	public void setCriarPontoComDoisClicks(boolean criarPontoComDoisClicks) {
		this.criarPontoComDoisClicks = criarPontoComDoisClicks;
	}

	public boolean isAlterado() {
		return alterado;
	}

	public void setAlterado(boolean alterado) {
		this.alterado = alterado;
	}

	public File getArquivo() {
		return arquivo;
	}

	public void setArquivo(File arquivo) {
		this.arquivo = arquivo;
		if(this.arquivo == null) {
			formularioPrincipal.setTitle("");
		} else {
			formularioPrincipal.setTitle(arquivo.getAbsolutePath());
		}
	}

	public FormularioPrincipal getFormularioPrincipal() {
		return formularioPrincipal;
	}
}