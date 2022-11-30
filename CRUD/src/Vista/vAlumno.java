package Vista;

import java.awt.EventQueue;
import java.awt.Image;

import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import Dao.DaoUsuario;
import Modelo.Usuario;

import javax.lang.model.element.Element;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileOutputStream;
import java.awt.Toolkit;

public class vAlumno extends JInternalFrame {

	private JPanel NOMBRE;
	private JLabel lblId;
	private JTextField txtUsuario;
	private JTextField txtPassword;
	private JTextField txtNombre;
	private JButton btnAgrgar;
	private JButton btnEditar;
	private JButton btnEliminar;
	private JButton btnBorrar;
	int fila = -1;
	DaoUsuario dao = new DaoUsuario();
	DefaultTableModel modelo = new DefaultTableModel();
	ArrayList<Usuario> lista = new ArrayList<Usuario>();
	private JTable tblUsuario;
	Usuario usuario = new Usuario();
	private JButton btnPDF;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					vUsuario frame = new vUsuario();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public vAlumno() {
//setLocationRelativeTo(null);
//setIconImage(Toolkit.getDefaultToolkit().getImage(vUsuario.class.getResource("/img/Ying.jpg")));
		setTitle("USUARIO");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		NOMBRE = new JPanel();
		NOMBRE.setBorder(new EmptyBorder(5, 5, 5, 5));
//setLocationRelativeTo(null);

		setContentPane(NOMBRE);
		NOMBRE.setLayout(null);

		txtUsuario = new JTextField();
		txtUsuario.setColumns(10);
		txtUsuario.setBounds(106, 53, 86, 20);
		NOMBRE.add(txtUsuario);

		txtPassword = new JTextField();
		txtPassword.setColumns(10);
		txtPassword.setBounds(106, 84, 86, 20);
		NOMBRE.add(txtPassword);

		JLabel lblNewLabel = new JLabel("USUARIO");
		lblNewLabel.setBounds(10, 56, 71, 14);
		NOMBRE.add(lblNewLabel);

		JLabel Password = new JLabel("PASSWORD");
		Password.setBounds(10, 87, 59, 14);
		NOMBRE.add(Password);

		JLabel Nombre = new JLabel("NOMBRE");
		Nombre.setBounds(10, 121, 71, 14);
		NOMBRE.add(Nombre);

		JLabel lblNewLabel_3 = new JLabel("ID");
		lblNewLabel_3.setBounds(10, 11, 46, 14);
		NOMBRE.add(lblNewLabel_3);

		txtNombre = new JTextField();
		txtNombre.setBounds(106, 118, 86, 20);
		NOMBRE.add(txtNombre);
		txtNombre.setColumns(10);

		lblId = new JLabel("1");
		lblId.setBounds(111, 11, 81, 31);
		NOMBRE.add(lblId);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 143, 414, 107);
		NOMBRE.add(scrollPane);

		tblUsuario = new JTable();
		tblUsuario.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				fila = tblUsuario.getSelectedRow();
				usuario = lista.get(fila);
				lblId.setText("" + usuario.getId());
				txtUsuario.setText(usuario.getUser());
				txtPassword.setText(usuario.getPasword());
				txtNombre.setText(usuario.getNombre());

			}
		});
		tblUsuario.setModel(new DefaultTableModel(
				new Object[][] { { null, null, null, null }, { null, null, null, null }, { null, null, null, null },
						{ null, null, null, null }, },
				new String[] { "New column", "New column", "New column", "New column" }));
		scrollPane.setViewportView(tblUsuario);

		btnAgrgar = new JButton("AGREGAR");
		btnAgrgar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Usuario user = new Usuario();
					user.setUser(txtUsuario.getText());
					user.setPasword(txtPassword.getText());
					user.setNombre(txtNombre.getText());

					if (dao.insertarUsuario(user)) {
						actualizarTabla();
						JOptionPane.showMessageDialog(null, "SE AGREGO CORRCTAMENTE");
					} else {
						JOptionPane.showMessageDialog(null, "ERROR");
					}

				} catch (Exception ex) {
					JOptionPane.showMessageDialog(null, "ERROR");

				}

			}
		});
		btnAgrgar.setBounds(233, 7, 89, 23);
		NOMBRE.add(btnAgrgar);

		btnEditar = new JButton("EDITAR");
		btnEditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if (txtUsuario.getText().equals("") || txtPassword.getText().equals("")
							|| txtNombre.getText().equals("")) {
						JOptionPane.showMessageDialog(null, "CAMPOS VACIOS");
						return;
					}
					usuario.setUser(txtUsuario.getText());
					usuario.setPasword(txtPassword.getText());
					usuario.setNombre(txtNombre.getText());
					if (dao.editarUsuario(usuario)) {
						actualizarTabla();
						limpiar();
						JOptionPane.showMessageDialog(null, "SE A CORRECTAMENTE");

					} else {
						JOptionPane.showMessageDialog(null, "ERROR");
					}

				} catch (Exception e2) {
					JOptionPane.showMessageDialog(null, "ERROR");
				}
			}
		});
		btnEditar.setBounds(233, 41, 89, 23);
		NOMBRE.add(btnEditar);

		btnEliminar = new JButton("ELIMINAR");
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					int opcion = JOptionPane.showConfirmDialog(null, "ESTA SEGURO DE ELIMINAR ESTE USIARIO ?",
							"ELIMINAR USUARIO", JOptionPane.YES_NO_OPTION);
					if (opcion == 0) {
						if (dao.eliminarUsuario(lista.get(fila).getId())) {
							actualizarTabla();
							JOptionPane.showMessageDialog(null, "SE ELIMINO CORRECTAMENTE !!");
						} else {
							JOptionPane.showMessageDialog(null, "ERROR");
						}
					}
				} catch (Exception ex) {
					JOptionPane.showMessageDialog(null, "ERROR");
				}
			}
		});
		btnEliminar.setBounds(233, 75, 89, 23);
		NOMBRE.add(btnEliminar);

		btnBorrar = new JButton("BORRAR");
		btnBorrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				limpiar();
			}
		});
		btnBorrar.setBounds(233, 112, 89, 23);
		NOMBRE.add(btnBorrar);
		
		btnPDF = new JButton("PDF");
		btnPDF.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					FileOutputStream archivo;
					File file = new File("C:\\Users\\Alumno.SALA2-PC35\\git\\Proyectofilan\\Proyectofinal\\src\\PDF\\reporte.pdf");
					archivo = new FileOutputStream(file);
					Document doc = new Document();
					PdfWriter.getInstance(doc, archivo);
					doc.open();
					Image img = Image.getInstance("C:\\Users\\Alumno.SALA2-PC35\\git\\Proyectofilan\\Proyectofinal\\src\\Img\\icono.jpg");
					img.setAlignment(Element.ALIGN_CENTER);
					           img.scaleToFit(200, 200);
					doc.add(img);
					Paragraph p = new Paragraph(10);
					com.itextpdf.text.Font negrita = new com.itextpdf.text.Font(com.itextpdf.text.Font.FontFamily.TIMES_ROMAN, 12, Font.BOLD, BaseColor.BLACK);
					p.add(Chunk.NEWLINE);
					p.add("Cliente");
					p.add(Chunk.NEWLINE);
					p.add(Chunk.NEWLINE);
					p.setAlignment(Element.ALIGN_CENTER);
					doc.add(p);
					PdfPTable tabla = new PdfPTable(4);
					tabla.setWidthPercentage(100);
					PdfPCell c1 = new PdfPCell(new Phrase(" IdAlumno", negrita));
					PdfPCell c2 = new PdfPCell(new Phrase(" Domicilio", negrita));
					PdfPCell c3 = new PdfPCell(new Phrase(" Telefono", negrita));
					PdfPCell c4 = new PdfPCell(new Phrase(" Nombre", negrita));
					c1.setHorizontalAlignment(Element.ALIGN_CENTER);
					c2.setHorizontalAlignment(Element.ALIGN_CENTER);
					c3.setHorizontalAlignment(Element.ALIGN_CENTER);
					c4.setHorizontalAlignment(Element.ALIGN_CENTER);
					c1.setBackgroundColor(BaseColor.LIGHT_GRAY);
					c2.setBackgroundColor(BaseColor.LIGHT_GRAY);
					c3.setBackgroundColor(BaseColor.LIGHT_GRAY);
					c4.setBackgroundColor(BaseColor.LIGHT_GRAY);
					tabla.addCell(c1);
					tabla.addCell(c2);
					tabla.addCell(c3);
					tabla.addCell(c4);

					for (Cliente u : lista) {
					tabla.addCell("" + u.getIdcliente());
					tabla.addCell("" + u.getDomicilio());
					tabla.addCell("" + u.getTelefono());
					tabla.addCell("" + u.getNombre());

					}

					doc.add(tabla);
					Paragraph p1 = new Paragraph(10);
					p1.add(Chunk.NEWLINE);
					p1.add("NÚMERO DE REGISTRO " + lista.size());
					p1.add(Chunk.NEWLINE);
					p1.add(Chunk.NEWLINE);
					p1.setAlignment(Element.ALIGN_RIGHT);
					doc.add(p1);
					doc.close();
					archivo.close();
					Desktop.getDesktop().open(file);
					} catch (FileNotFoundException e1) {
					e1.printStackTrace();
					JOptionPane.showMessageDialog(null, "ERROR AL CREAR ARCHIVO");
					} catch (DocumentException e1) {
					e1.printStackTrace();
					JOptionPane.showMessageDialog(null, "ERROR AL CREAR DOCUMENTO PDF");
					} catch (IOException e1) {
					e1.printStackTrace();
					JOptionPane.showMessageDialog(null, "ERROR AL CREAR IO");
					}
					}
					});

		
		btnPDF.setBounds(332, 52, 89, 23);
		NOMBRE.add(btnPDF);

		modelo.addColumn("ID");
		modelo.addColumn("USERS");
		modelo.addColumn("PASSWORD");
		modelo.addColumn("NOMBRE");
		actualizarTabla();
	}

	public void actualizarTabla() {
		while (modelo.getRowCount() > 0) {
			modelo.removeRow(0);
		}
		lista = dao.fetchUsiarios();
		for (Usuario u : lista) {
			Object o[] = new Object[4];
			o[0] = u.getId();
			o[1] = u.getUser();
			o[2] = u.getPasword();
			o[3] = u.getNombre();
			modelo.addRow(o);
		}
		tblUsuario.setModel(modelo);
	}

	public void limpiar() {
		txtUsuario.setText("");
		txtPassword.setText("");
		txtNombre.setText("");
	}
}
