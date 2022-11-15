package Vista;

import java.awt.EventQueue;

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

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Toolkit;

public class vUsuario extends JInternalFrame {

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

	public vUsuario() {
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
