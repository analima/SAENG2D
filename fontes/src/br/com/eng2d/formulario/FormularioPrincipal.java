package br.com.eng2d.formulario;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JScrollPane;
import javax.swing.LayoutStyle;
import javax.swing.ScrollPaneConstants;
import javax.swing.UIManager;
import javax.swing.WindowConstants;

import br.com.eng2d.formulario.caixa_apoio.DialogoApoio;
import br.com.eng2d.formulario.caixa_forca.DialogoForca;
import br.com.eng2d.superficie.SelecaoPonto;
import br.com.eng2d.superficie.Superficie;
import br.com.eng2d.superficie.builder.LinhaArrastoBuilder;
import br.com.eng2d.superficie.builder.LinhaClickBuilder;
import br.com.eng2d.superficie.builder.PontoBuilder;
import br.com.eng2d.util.Util;

public class FormularioPrincipal extends javax.swing.JFrame {
	private static final long serialVersionUID = 1L;
	private Superficie superficie = new Superficie(this);

	private JMenuBar barraMENU;
	
	private JMenu menuForca;
	private JMenu menuAjuda;
	private JMenu menuEditar;
	private JMenu menuArquivo;
	private JMenu menuDesenhar;
	private JMenu menuFerramenta;

	private JMenuItem menuItemSair;
	private JMenuItem menuItemNovo;
	private JMenuItem menuItemAbrir;
	private JMenuItem menuItemSalvar;
	private JMenuItem menuItemSalvarComo;
	private JMenuItem menuItemLimpar;
	private JMenuItem menuItemDesfazer;
	private JMenuItem menuItemCriarPonto;
	private JMenuItem menuItemCriarLinha;
	private JMenuItem menuItemSelecaoApoio;
	
	private JCheckBoxMenuItem checkDesenharGradeSuperf;
	private JCheckBoxMenuItem checkManterLinhaInacabada;
	private JCheckBoxMenuItem checkDemarcarLinhaComPonto;
	private JCheckBoxMenuItem checkCriarPontoComDoisClicks;
	
	private JRadioButtonMenuItem radioButtonCriacaoLinhaClick;
	private JRadioButtonMenuItem radioButtonCriacaoLinhaArrasto;

	private JButton buttonSelecao;
	private JButton buttonCriarLinha;
	private JButton buttonCriarPonto;
	private JButton buttonSelecaoApoio;
	private JButton buttonSelecaoForca;

	private JButton jButton3;
	private JButton jButton5;
	private JButton jButton6;
	private JLabel jLabel1;
	private JLabel jLabel10;
	private JLabel jLabel11;
	private JLabel jLabel2;
	private JLabel jLabel3;
	private JLabel jLabel4;
	private JLabel jLabel5;
	private JLabel jLabel6;
	private JLabel jLabel7;
	private JLabel jLabel8;
	private JLabel jLabel9;

	private JMenuItem jMenuItem2;
	private JMenuItem jMenuItem9;
	private JMenuItem jMenuItem10;
	private JMenuItem jMenuItem12;
	private JMenuItem jMenuItem17;
	private JMenuItem jMenuItem18;
	private JMenuItem jMenuItem19;
	
	private JPanel jPanel1;
	private JPanel jPanel2;
	private JPanel jPanel3;
	private JPanel jPanel5;
	private JPanel jPanel6;
	private JPanel jPanel7;
	private JPanel jPanel8;
	private JPanel jPanel9;
	private JPanel jPanel10;

	private JScrollPane jScrollPane1;
	private JScrollPane jScrollPane2;
	private JScrollPane jScrollPane3;
	private JScrollPane jScrollPane4;
	private JScrollPane jScrollPane5;
	private JScrollPane jScrollPane6;
	private JScrollPane jScrollPane7;
	private JScrollPane jScrollPane8;
	private JScrollPane jScrollPane9;

	public FormularioPrincipal() {
		super(Util.getString("titulo_aplicacao"));
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		inicializar();
	}

	private void selecionarApoio() {
		if (superficie.getObjetoSelecionado() == null) {
			JOptionPane.showMessageDialog(FormularioPrincipal.this, Util.getTexto("nenhum_ponto_selecionado"));
		} else {
			new DialogoApoio(superficie);
		}
	}

	private void selecionarForca() {
		if (superficie.getObjetoSelecionado() == null) {
			JOptionPane.showMessageDialog(FormularioPrincipal.this, Util.getTexto("nenhum_ponto_selecionado"));
		} else {
			new DialogoForca(superficie);
		}
	}
	
	private void opcaoCriarLinha() {
		if(radioButtonCriacaoLinhaArrasto.isSelected()) {
			superficie.setManipulador(new LinhaArrastoBuilder(superficie));
		} else if(radioButtonCriacaoLinhaClick.isSelected()) {
			superficie.setManipulador(new LinhaClickBuilder(superficie));
		} else {
			throw new RuntimeException();
		}
	}

	private void inicializar() {
		barraMENU = new JMenuBar();
		menuAjuda = new JMenu("Ajuda");
		menuForca = new JMenu(Util.getString("label_forcas"));
		menuEditar = new JMenu("Editar");
		menuArquivo = new JMenu("Arquivo");
		menuDesenhar = new JMenu("Desenhar");
		menuFerramenta = new JMenu("Ferramentas");
		
		jPanel1 = new JPanel();

		buttonCriarLinha = new JButton(getImagem("opcao_linha_1.png"));
		buttonCriarLinha.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				opcaoCriarLinha();
			}
		});

		menuItemCriarLinha = new JMenuItem("Linha");
		menuItemCriarLinha.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				opcaoCriarLinha();
			}
		});

		buttonCriarPonto = new JButton(getImagem("opcao_ponto.png"));
		buttonCriarPonto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				superficie.setManipulador(new PontoBuilder(superficie));
			}
		});

		menuItemCriarPonto = new JMenuItem("Ponto");
		menuItemCriarPonto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				superficie.setManipulador(new PontoBuilder(superficie));
			}
		});

		buttonSelecao = new JButton(getImagem("opcao_selecao.png"));
		buttonSelecao.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				superficie.setManipulador(new SelecaoPonto(superficie));
			}
		});

		buttonSelecaoForca = new JButton(getImagem("opcao_forca.png"));
		buttonSelecaoForca.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				selecionarForca();
			}
		});
		
		buttonSelecaoApoio = new JButton(getImagem("opcao_apoio.png"));
		buttonSelecaoApoio.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				selecionarApoio();
			}
		});

		menuItemSelecaoApoio = new JMenuItem("Apoio");
		menuItemSelecaoApoio.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				selecionarApoio();
			}
		});

		menuItemLimpar = new JMenuItem("Limpar");
		menuEditar.add(menuItemLimpar);
		menuItemLimpar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				superficie.excluirTodos();
				superficie.repaint();
			}
		});

		menuItemDesfazer = new JMenuItem("Excluir último objeto adicionado");
		menuEditar.add(menuItemDesfazer);
		menuItemDesfazer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				superficie.excluirUltimoObjetoAdicionado();
				superficie.repaint();
			}
		});
		
		menuItemNovo = new JMenuItem("Novo");
		menuArquivo.add(menuItemNovo);
		menuItemNovo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				superficie.novo();
			}
		});
		
		menuItemAbrir = new JMenuItem("Abrir");
		menuArquivo.add(menuItemAbrir);
		menuItemAbrir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				superficie.abrir();
			}
		});
		
		menuItemSalvar = new JMenuItem("Salvar");
		menuArquivo.add(menuItemSalvar);
		menuItemSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				superficie.salvar(false, false);
			}
		});
		
		menuItemSalvarComo = new JMenuItem("Salvar como");
		menuArquivo.add(menuItemSalvarComo);
		menuItemSalvarComo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				superficie.salvarComo();
			}
		});
		
		checkDesenharGradeSuperf = new JCheckBoxMenuItem("Desenhar grade", Util.getBoolean("desenharGrade"));
		menuArquivo.add(checkDesenharGradeSuperf);
		checkDesenharGradeSuperf.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				superficie.setDesenharGrade(checkDesenharGradeSuperf.isSelected());
				superficie.repaint();
			}
		});

		checkDemarcarLinhaComPonto = new JCheckBoxMenuItem("Demarcar linha com ponto", Util.getBoolean("demarcarLinhaComPonto"));
		menuArquivo.add(checkDemarcarLinhaComPonto);
		checkDemarcarLinhaComPonto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				superficie.setDemarcarLinhaComPonto(checkDemarcarLinhaComPonto.isSelected());
			}
		});

		checkManterLinhaInacabada = new JCheckBoxMenuItem("Manter linha inacabada", Util.getBoolean("manterLinhaInacabada"));
		menuArquivo.add(checkManterLinhaInacabada);
		checkManterLinhaInacabada.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				superficie.setManterLinhaInacabada(checkManterLinhaInacabada.isSelected());
			}
		});

		checkCriarPontoComDoisClicks = new JCheckBoxMenuItem("Criar pontos com 2 clicks", Util.getBoolean("criarPontoComDoisClicks"));
		menuArquivo.add(checkCriarPontoComDoisClicks);
		checkCriarPontoComDoisClicks.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				superficie.setCriarPontoComDoisClicks(checkCriarPontoComDoisClicks.isSelected());
			}
		});
		
		boolean selecionou = "arrasto".equals(Util.getString("formatoCriacaoLinha"));
		
		radioButtonCriacaoLinhaArrasto = new JRadioButtonMenuItem("Criar linha arrastando", selecionou);
		menuArquivo.add(radioButtonCriacaoLinhaArrasto);
		radioButtonCriacaoLinhaArrasto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				opcaoCriarLinha();
			}
		});

		selecionou = "click".equals(Util.getString("formatoCriacaoLinha"));
		
		radioButtonCriacaoLinhaClick = new JRadioButtonMenuItem("Criar linha clicando", selecionou);
		menuArquivo.add(radioButtonCriacaoLinhaClick);
		radioButtonCriacaoLinhaClick.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				opcaoCriarLinha();
			}
		});
		
		ButtonGroup buttonGroup = new ButtonGroup();
		buttonGroup.add(radioButtonCriacaoLinhaArrasto);
		buttonGroup.add(radioButtonCriacaoLinhaClick);
		
		if(!selecionou) {
			radioButtonCriacaoLinhaArrasto.setSelected(true);
		}
		
		menuItemSair = new JMenuItem("Sair");
		menuArquivo.add(menuItemSair);
		menuItemSair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		
		jButton3 = new JButton();
		jButton5 = new JButton();
		jButton6 = new JButton();

		jScrollPane1 = new JScrollPane();
		jScrollPane2 = new JScrollPane();
		jPanel3 = new JPanel();
		jLabel1 = new JLabel();
		jScrollPane3 = new JScrollPane();
		jPanel2 = new JPanel();
		jLabel4 = new JLabel();
		jLabel5 = new JLabel();
		jLabel6 = new JLabel();
		jLabel7 = new JLabel();
		jLabel8 = new JLabel();
		jLabel9 = new JLabel();
		jLabel10 = new JLabel();
		jLabel11 = new JLabel();
		jScrollPane4 = new JScrollPane();
		jPanel5 = new JPanel();
		jScrollPane5 = new JScrollPane();
		jPanel6 = new JPanel();
		jScrollPane6 = new JScrollPane();
		jPanel7 = new JPanel();
		jScrollPane7 = new JScrollPane();
		jPanel8 = new JPanel();
		jScrollPane8 = new JScrollPane();
		jPanel9 = new JPanel();
		jScrollPane9 = new JScrollPane();
		jPanel10 = new JPanel();
		jLabel2 = new JLabel();
		jLabel3 = new JLabel();

		jMenuItem17 = new JMenuItem();
		jMenuItem18 = new JMenuItem();
		jMenuItem19 = new JMenuItem();
		jMenuItem9 = new JMenuItem();
		jMenuItem10 = new JMenuItem();
		jMenuItem12 = new JMenuItem();
		jMenuItem2 = new JMenuItem();

		jPanel1.setBackground(new java.awt.Color(204, 204, 204));
		jPanel1.setBorder(BorderFactory.createEtchedBorder());

		jButton3.setForeground(UIManager.getDefaults().getColor("Button.light"));
		jButton3.setIcon(getImagem("4.png"));

		jButton5.setForeground(UIManager.getDefaults().getColor("Button.light"));
		jButton5.setIcon(getImagem("5.png"));

		jButton6.setForeground(UIManager.getDefaults().getColor("Button.light"));
		jButton6.setIcon(getImagem("6.png"));

		GroupLayout jPanel1Layout = new GroupLayout(jPanel1);
		jPanel1.setLayout(jPanel1Layout);
		jPanel1Layout.setHorizontalGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(
				GroupLayout.Alignment.TRAILING,
				jPanel1Layout.createSequentialGroup().addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE).addComponent(buttonSelecao,
						GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE).addContainerGap())

		.addGroup(
				jPanel1Layout.createSequentialGroup().addContainerGap().addComponent(buttonCriarLinha, GroupLayout.PREFERRED_SIZE, 50,
						GroupLayout.PREFERRED_SIZE).addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))

		.addGroup(
				jPanel1Layout.createSequentialGroup().addContainerGap().addComponent(buttonCriarPonto, GroupLayout.PREFERRED_SIZE, 50,
						GroupLayout.PREFERRED_SIZE).addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))

		.addGroup(
				GroupLayout.Alignment.TRAILING,
				jPanel1Layout.createSequentialGroup().addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE).addComponent(buttonSelecaoApoio,
						GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE).addContainerGap()).addGroup(
				jPanel1Layout.createSequentialGroup().addContainerGap().addComponent(buttonSelecaoForca, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
						.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)).addGroup(
				jPanel1Layout.createSequentialGroup().addContainerGap().addComponent(jButton3, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
						.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)).addGroup(
				GroupLayout.Alignment.TRAILING,
				jPanel1Layout.createSequentialGroup().addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE).addComponent(jButton5,
						GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE).addContainerGap()).addGroup(
				jPanel1Layout.createSequentialGroup().addContainerGap().addComponent(jButton6, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
						.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));
		jPanel1Layout.setVerticalGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(
				jPanel1Layout.createSequentialGroup().addComponent(buttonSelecao, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
						.addGap(18, 18, 18).addContainerGap().addComponent(buttonCriarLinha, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
						.addGap(18, 18, 18).addComponent(buttonCriarPonto, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE).addGap(18, 18, 18)
						.addComponent(buttonSelecaoApoio, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE).addGap(18, 18, 18).addComponent(buttonSelecaoForca,
								GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE).addGap(18, 18, 18).addComponent(jButton3,
								GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE).addGap(18, 18, 18).addComponent(jButton5,
								GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE).addGap(18, 18, 18).addComponent(jButton6,
								GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE).addContainerGap(191, Short.MAX_VALUE)));

		jScrollPane1.setBackground(new java.awt.Color(255, 255, 255));
		jScrollPane1.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		jScrollPane1.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

		GroupLayout jPanel4Layout = new GroupLayout(superficie);
		superficie.setLayout(jPanel4Layout);
		jPanel4Layout.setHorizontalGroup(jPanel4Layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGap(0, 619, Short.MAX_VALUE));
		jPanel4Layout.setVerticalGroup(jPanel4Layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGap(0, 317, Short.MAX_VALUE));

		jScrollPane1.setViewportView(superficie);

		jScrollPane2.setBackground(new java.awt.Color(255, 255, 255));
		jScrollPane2.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

		jPanel3.setBackground(new java.awt.Color(255, 239, 204));

		GroupLayout jPanel3Layout = new GroupLayout(jPanel3);
		jPanel3.setLayout(jPanel3Layout);
		jPanel3Layout.setHorizontalGroup(jPanel3Layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGap(0, 471, Short.MAX_VALUE));
		jPanel3Layout.setVerticalGroup(jPanel3Layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGap(0, 247, Short.MAX_VALUE));

		jScrollPane2.setViewportView(jPanel3);

		jLabel1.setFont(new java.awt.Font("Tahoma", 1, 11));
		jLabel1.setText("DIAGRAMA");

		jScrollPane3.setBackground(new java.awt.Color(255, 255, 255));
		jScrollPane3.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		jScrollPane3.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);

		jPanel2.setBackground(new java.awt.Color(255, 254, 254));

		jLabel4.setFont(new java.awt.Font("Tahoma", 1, 11));
		jLabel4.setText("EQUILÍBRIO EXTERNO");

		jLabel5.setFont(new java.awt.Font("Tahoma", 1, 11));
		jLabel5.setText("Fx = 0");

		jLabel6.setFont(new java.awt.Font("Tahoma", 1, 11));
		jLabel6.setText("Fy = 0");

		jLabel7.setFont(new java.awt.Font("Tahoma", 1, 11));
		jLabel7.setText("Mz = 0");

		jLabel8.setFont(new java.awt.Font("Tahoma", 1, 11));
		jLabel8.setText(Util.getString("label_equilibrio_interno"));

		jLabel9.setFont(new java.awt.Font("Tahoma", 1, 11));
		jLabel9.setText("Fx = 0");

		jLabel10.setFont(new java.awt.Font("Tahoma", 1, 11));
		jLabel10.setText("Fy = 0");

		jLabel11.setFont(new java.awt.Font("Tahoma", 1, 11));
		jLabel11.setText("Mz = 0");

		jScrollPane4.setBackground(new java.awt.Color(255, 255, 255));
		jScrollPane4.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		jScrollPane4.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);

		jPanel5.setBackground(new java.awt.Color(255, 255, 255));

		GroupLayout jPanel5Layout = new GroupLayout(jPanel5);
		jPanel5.setLayout(jPanel5Layout);
		jPanel5Layout.setHorizontalGroup(jPanel5Layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGap(0, 469, Short.MAX_VALUE));
		jPanel5Layout.setVerticalGroup(jPanel5Layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGap(0, 204, Short.MAX_VALUE));

		jScrollPane4.setViewportView(jPanel5);

		jScrollPane5.setBackground(new java.awt.Color(255, 255, 255));
		jScrollPane5.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		jScrollPane5.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);

		jPanel6.setBackground(new java.awt.Color(255, 255, 255));

		GroupLayout jPanel6Layout = new GroupLayout(jPanel6);
		jPanel6.setLayout(jPanel6Layout);
		jPanel6Layout.setHorizontalGroup(jPanel6Layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGap(0, 469, Short.MAX_VALUE));
		jPanel6Layout.setVerticalGroup(jPanel6Layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGap(0, 204, Short.MAX_VALUE));

		jScrollPane5.setViewportView(jPanel6);

		jScrollPane6.setBackground(new java.awt.Color(255, 255, 255));
		jScrollPane6.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		jScrollPane6.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);

		jPanel7.setBackground(new java.awt.Color(255, 255, 255));

		GroupLayout jPanel7Layout = new GroupLayout(jPanel7);
		jPanel7.setLayout(jPanel7Layout);
		jPanel7Layout.setHorizontalGroup(jPanel7Layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGap(0, 469, Short.MAX_VALUE));
		jPanel7Layout.setVerticalGroup(jPanel7Layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGap(0, 204, Short.MAX_VALUE));

		jScrollPane6.setViewportView(jPanel7);

		jScrollPane7.setBackground(new java.awt.Color(255, 255, 255));
		jScrollPane7.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		jScrollPane7.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);

		jPanel8.setBackground(new java.awt.Color(255, 255, 255));

		GroupLayout jPanel8Layout = new GroupLayout(jPanel8);
		jPanel8.setLayout(jPanel8Layout);
		jPanel8Layout.setHorizontalGroup(jPanel8Layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGap(0, 469, Short.MAX_VALUE));
		jPanel8Layout.setVerticalGroup(jPanel8Layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGap(0, 204, Short.MAX_VALUE));

		jScrollPane7.setViewportView(jPanel8);

		jScrollPane8.setBackground(new java.awt.Color(255, 255, 255));
		jScrollPane8.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		jScrollPane8.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);

		jPanel9.setBackground(new java.awt.Color(255, 255, 255));

		GroupLayout jPanel9Layout = new GroupLayout(jPanel9);
		jPanel9.setLayout(jPanel9Layout);
		jPanel9Layout.setHorizontalGroup(jPanel9Layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGap(0, 469, Short.MAX_VALUE));
		jPanel9Layout.setVerticalGroup(jPanel9Layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGap(0, 204, Short.MAX_VALUE));

		jScrollPane8.setViewportView(jPanel9);

		jScrollPane9.setBackground(new java.awt.Color(255, 255, 255));
		jScrollPane9.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		jScrollPane9.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);

		jPanel10.setBackground(new java.awt.Color(255, 255, 255));

		GroupLayout jPanel10Layout = new GroupLayout(jPanel10);
		jPanel10.setLayout(jPanel10Layout);
		jPanel10Layout.setHorizontalGroup(jPanel10Layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGap(0, 469, Short.MAX_VALUE));
		jPanel10Layout.setVerticalGroup(jPanel10Layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGap(0, 204, Short.MAX_VALUE));

		jScrollPane9.setViewportView(jPanel10);

		GroupLayout jPanel2Layout = new GroupLayout(jPanel2);
		jPanel2.setLayout(jPanel2Layout);
		jPanel2Layout.setHorizontalGroup(jPanel2Layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(
				jPanel2Layout.createSequentialGroup().addGroup(
						jPanel2Layout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(jLabel5).addGroup(
								jPanel2Layout.createSequentialGroup().addGap(25, 25, 25).addComponent(jLabel4)).addComponent(jLabel6).addComponent(
								jScrollPane4, GroupLayout.PREFERRED_SIZE, 169, GroupLayout.PREFERRED_SIZE).addComponent(jScrollPane5,
								GroupLayout.PREFERRED_SIZE, 169, GroupLayout.PREFERRED_SIZE).addComponent(jScrollPane6, GroupLayout.PREFERRED_SIZE, 169,
								GroupLayout.PREFERRED_SIZE).addComponent(jLabel7).addGroup(
								jPanel2Layout.createSequentialGroup().addGap(25, 25, 25).addComponent(jLabel8)).addComponent(jScrollPane7,
								GroupLayout.PREFERRED_SIZE, 169, GroupLayout.PREFERRED_SIZE).addComponent(jLabel9).addComponent(jLabel10).addComponent(
								jScrollPane8, GroupLayout.PREFERRED_SIZE, 169, GroupLayout.PREFERRED_SIZE).addComponent(jLabel11).addComponent(jScrollPane9,
								GroupLayout.PREFERRED_SIZE, 169, GroupLayout.PREFERRED_SIZE)).addContainerGap(149, Short.MAX_VALUE)));
		jPanel2Layout.setVerticalGroup(jPanel2Layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(
				jPanel2Layout.createSequentialGroup().addContainerGap().addComponent(jLabel4).addGap(18, 18, 18).addComponent(jLabel5).addPreferredGap(
						LayoutStyle.ComponentPlacement.RELATED).addComponent(jScrollPane4, GroupLayout.PREFERRED_SIZE, 46, GroupLayout.PREFERRED_SIZE).addGap(
						18, 18, 18).addComponent(jLabel6).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(jScrollPane5,
						GroupLayout.PREFERRED_SIZE, 46, GroupLayout.PREFERRED_SIZE).addGap(21, 21, 21).addComponent(jLabel9).addPreferredGap(
						LayoutStyle.ComponentPlacement.RELATED).addComponent(jScrollPane6, GroupLayout.PREFERRED_SIZE, 46, GroupLayout.PREFERRED_SIZE).addGap(
						18, 18, 18).addComponent(jLabel8).addGap(18, 18, 18).addComponent(jLabel7).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
						.addComponent(jScrollPane7, GroupLayout.PREFERRED_SIZE, 46, GroupLayout.PREFERRED_SIZE).addGap(18, 18, 18).addComponent(jLabel10)
						.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(jScrollPane8, GroupLayout.PREFERRED_SIZE, 46,
								GroupLayout.PREFERRED_SIZE).addGap(18, 18, 18).addComponent(jLabel11).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
						.addComponent(jScrollPane9, GroupLayout.PREFERRED_SIZE, 46, GroupLayout.PREFERRED_SIZE).addContainerGap(29, Short.MAX_VALUE)));

		jScrollPane3.setViewportView(jPanel2);

		jLabel2.setFont(new java.awt.Font("Tahoma", 1, 11));
		jLabel2.setText(Util.getString("label_area_cliente"));

		jLabel3.setFont(new java.awt.Font("Tahoma", 1, 11));
		jLabel3.setText(Util.getString("label_relatorio"));

		barraMENU.add(menuArquivo);
		barraMENU.add(menuEditar);

		menuDesenhar.add(menuItemCriarPonto);
		menuDesenhar.add(menuItemCriarLinha);

		menuFerramenta.add(menuDesenhar);
		menuFerramenta.add(menuItemSelecaoApoio);

		jMenuItem17.setText("Centralizada");
		menuForca.add(jMenuItem17);

		jMenuItem18.setText(Util.getString("label_distribuida"));
		menuForca.add(jMenuItem18);

		jMenuItem19.setText(Util.getString("label_triangular"));
		menuForca.add(jMenuItem19);

		menuFerramenta.add(menuForca);

		jMenuItem9.setText("Diagrama do Desenho");
		menuFerramenta.add(jMenuItem9);

		jMenuItem10.setText(Util.getString("label_somatorio"));
		menuFerramenta.add(jMenuItem10);

		jMenuItem12.setText(Util.getString("label_diagrama_forca"));
		menuFerramenta.add(jMenuItem12);

		barraMENU.add(menuFerramenta);

		jMenuItem2.setText("Sobre SAENG2D");
		menuAjuda.add(jMenuItem2);

		barraMENU.add(menuAjuda);

		setJMenuBar(barraMENU);

		GroupLayout layout = new GroupLayout(getContentPane());
		getContentPane().setLayout(layout);
		layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(
				layout.createSequentialGroup().addComponent(jPanel1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE).addGap(
						18, 18, 18).addGroup(
						layout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(jScrollPane1, GroupLayout.DEFAULT_SIZE, 478, Short.MAX_VALUE)
								.addComponent(jScrollPane2, GroupLayout.DEFAULT_SIZE, 478, Short.MAX_VALUE).addComponent(jLabel1).addComponent(jLabel2))
						.addGap(18, 18, 18).addGroup(
								layout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(jLabel3).addComponent(jScrollPane3,
										GroupLayout.PREFERRED_SIZE, 170, GroupLayout.PREFERRED_SIZE)).addContainerGap()));
		layout.setVerticalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(
				GroupLayout.Alignment.TRAILING,
				layout.createSequentialGroup().addGroup(
						layout.createParallelGroup(GroupLayout.Alignment.TRAILING).addComponent(jPanel1, GroupLayout.Alignment.LEADING,
								GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE).addGroup(
								layout.createSequentialGroup().addContainerGap().addGroup(
										layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(jLabel2).addComponent(jLabel3))
										.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addGroup(
												layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(
														layout.createSequentialGroup().addComponent(jScrollPane1, GroupLayout.DEFAULT_SIZE, 283,
																Short.MAX_VALUE).addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
																.addComponent(jLabel1).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(
																		jScrollPane2, GroupLayout.DEFAULT_SIZE, 251, Short.MAX_VALUE)).addComponent(
														jScrollPane3, GroupLayout.DEFAULT_SIZE, 565, Short.MAX_VALUE)))).addContainerGap()));

		pack();
	}

	private ImageIcon getImagem(String nome) {
		return new ImageIcon(getClass().getResource("/imagens/" + nome));
	}
}