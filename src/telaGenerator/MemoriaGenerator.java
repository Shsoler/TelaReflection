package telaGenerator;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;
import java.security.acl.Group;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import annotations.Campos;
import annotations.MapaDAO;
import annotations.Tela;
import annotations.Validacao;
import enume.TipoEntrada;

public class MemoriaGenerator {
	private JFrame frame;
	JTable tabela = null;
	public void Gerador(Object obj){
		
		if (obj.getClass().isAnnotationPresent(Tela.class)) {
			Tela telano = obj.getClass().getAnnotation(Tela.class);
			MapaDAO dao = obj.getClass().getAnnotation(MapaDAO.class);
			int x = 15;
			int y = 15;
			int tx = 15;
			int bx = 15;
			frame = new JFrame();
			frame.setBounds(100, 100, telano.larguratela(), telano.alturatela());
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame.getContentPane().setLayout(null);
			JTextField jtf = null;
			JPasswordField jpf = null;
			JTextArea jta = null;
			JRadioButton opt = null;
			ButtonGroup rgroup = null;
			Map<String, Object> fields = new HashMap<String, Object>();
			Class<?> klass = obj.getClass();
			Field[] campos = klass.getDeclaredFields();
			List<String> indice = new ArrayList<String>();
			if (campos.length > 0) {
				for (Field field : campos) {
					if (field.isAnnotationPresent(Campos.class) && field.getAnnotation(Campos.class).ativo()) {
						//gera label
						Campos ano = field.getAnnotation(Campos.class);
						JLabel label;
						String nome = ano.nomeCampo();
						if (nome.equals(""))
							label = new JLabel(field.getName());
						else
							label = new JLabel(ano.nomeCampo());
						indice.add(label.getText());
						System.out.println(ano.nomeCampo());
						label.setBounds(x, y, 100, 20);
						frame.getContentPane().add(label);
						y += 30;
						//gera campo
						if (ano.tipoEntrada() == TipoEntrada.TEXTFIELD) {
							jtf = new JTextField();
							jtf.setBounds(x, y, ano.largura(), ano.altura());
							y += ano.altura();
							jtf.setText(field.getName());
							frame.getContentPane().add(jtf);
							fields.put(field.getName(), jtf);
							if(ano.largura()+x > bx)
								bx = ano.largura()+x;
						}
						if (ano.tipoEntrada() == TipoEntrada.PASSWORD) {
							jpf = new JPasswordField();
							jpf.setBounds(x, y, ano.largura(), ano.altura());
							y += ano.altura();
							jpf.setText(field.getName());
							frame.getContentPane().add(jpf);
							fields.put(field.getName(), jpf);
							if(ano.largura()+x > bx)
								bx = ano.largura()+x;
						}
						if (ano.tipoEntrada() == TipoEntrada.TEXTAREA) {
							jta = new JTextArea();
							jta.setBounds(x, y, ano.largura(), ano.altura());
							y += ano.altura();
							jta.setText(field.getName());
							frame.getContentPane().add(jta);
							fields.put(field.getName(), jta);
							if(ano.largura()+x > bx)
								bx = ano.largura()+x;
						}
						if (ano.tipoEntrada() == TipoEntrada.RADIOBUTTON) {
							if (ano.opcoesSelecao().length > 0) {
								rgroup = new ButtonGroup();
								for (String option : ano.opcoesSelecao()) {
									opt = new JRadioButton(option);
									opt.setBounds(x, y, ano.largura(), ano.altura());
									y += ano.altura();
									frame.getContentPane().add(opt);
									rgroup.add(opt);
									fields.put(field.getName(), rgroup);
									if(ano.largura()+x > bx)
										bx = ano.largura()+x;
								}
							}
						}
					}
				}
				
				if(!dao.caminhoDao().equals("") && !dao.listaDao().equals("")){
					try {
						Class<?> klassdao = Class.forName(dao.caminhoDao());
						Method metododao = klassdao.getDeclaredMethod(dao.listaDao());
						Constructor<?> condao = klassdao.getDeclaredConstructor();
						Object objdao = condao.newInstance();
						tabela = new JTable(new DefaultTableModel((String[][]) metododao.invoke(objdao),indice.toArray()));
						tabela.setBounds(bx+10, 300,fields.size()*50 ,100);
						JScrollPane scrollPane = new JScrollPane();
						scrollPane.setBounds(bx+10, 15, fields.size()*50,100);
						scrollPane.setViewportView(tabela);
						frame.getContentPane().add(scrollPane);
					/*	scrollPane = new JScrollPane();
						scrollPane.setBounds(231, 86, 132, 115);
						frame.getContentPane().add(scrollPane);
						
						table_1 = new JTable();
						scrollPane.setViewportView(table_1);*/
					}catch(Exception e){
						e.printStackTrace();
					}
					
				}
				//
				
				y += 20;
				JButton jbtn = new JButton("Ok");
				jbtn.setBounds(x, y, 130, 25);
				jbtn.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
 												
						try {

							Constructor<?> con = klass.getDeclaredConstructor();
							Object obj = con.newInstance();
							
							Field[] camposobj = klass.getDeclaredFields();
							for (Field campo : camposobj) {
								if (campo.getAnnotation(Campos.class).ativo()) {
									campo.setAccessible(true);
									Object objCampoMapeado = fields.get(campo.getName());
									if (objCampoMapeado instanceof ButtonGroup) {
										ButtonGroup grupo = (ButtonGroup) objCampoMapeado;
										for (Enumeration<AbstractButton> buttons = grupo.getElements(); buttons
												.hasMoreElements();) {
											AbstractButton button = buttons.nextElement();

											if (button.isSelected()) {
												//Method retorno = objCampoMapeado.getClass().getMethod(, parameterTypes)
												campo.set(obj, button.getText());
											}
										}
									} else {
										Method text = objCampoMapeado.getClass().getMethod("getText");
										Object valor = text.invoke(objCampoMapeado);
										if(campo.isAnnotationPresent(Validacao.class))
										if(!Validar(frame,campo,valor))
											break;
										if (campo.getType() == Integer.class)
											campo.set(obj, Integer.valueOf((String) valor));
										if (campo.getType() == String.class)
											campo.set(obj, valor);
										if(campo.getType() == Double.class)
											campo.set(obj, Double.valueOf((String)valor));

									} 
								}
							}
				//			
							Class<?> klassdao = Class.forName(dao.caminhoDao());
							Constructor<?> condao = klassdao.getDeclaredConstructor();
							Object objdao = condao.newInstance();
							Method mtd = klassdao.getMethod(dao.gravarDao(), obj.getClass());
							System.out.println("teste");
							mtd.invoke(objdao, obj);
							Method metododao = klassdao.getDeclaredMethod(dao.listaDao());
							tabela.setModel((new DefaultTableModel(
									(String[][]) metododao.invoke(objdao),indice.toArray())));
							
							
						} catch (Exception e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}

					}


				});
				tx += 135;
				x += 135;
				frame.getContentPane().add(jbtn);
				JButton jbtn2 = new JButton("Cancel");
				jbtn2.setBounds(tx, y, 130, 25);
				jbtn2.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
					frame.dispose();
					}
				});
				x += tx;
				if(x > telano.larguratela())
					x += telano.larguratela();
				else
					x = telano.larguratela();
				if(y > telano.alturatela())
					y+= telano.alturatela();
				else
					y = telano.alturatela();
				frame.setSize(x, y);
				frame.getContentPane().add(jbtn2);
				frame.setVisible(true);
			} 
		}
	}
	protected Boolean Validar(JFrame frame2, Field campo,Object valor) {
		try {
			Object result = new Object();
			Validacao val = campo.getAnnotation(Validacao.class);
			URLClassLoader child;
			child = new URLClassLoader(new URL[] { new URL(val.caminhoJar()) }, frame.getClass().getClassLoader());
			Class<?> classToLoad = Class.forName(val.nomeClasse(), true, child);
			Method method = classToLoad.getDeclaredMethod(val.nomeMetodo(), String.class);
			Object instance = classToLoad.newInstance();
			if(campo.getType() == String.class)
				result = method.invoke(instance, valor.toString());
			if(campo.getType() == Integer.class)
				result = method.invoke(instance, Integer.valueOf(valor.toString()));
			if(campo.getType() == Double.class)
				result = method.invoke(instance, Double.valueOf(valor.toString()));
			if(result.toString().length()>0){
				JOptionPane.showMessageDialog(frame2, result.toString());
				return false;
			}
			return true;
		} catch (Exception e) {
		e.printStackTrace();
		}
		return null;
		
	}
}
