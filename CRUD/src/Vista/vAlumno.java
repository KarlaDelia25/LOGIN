package Vista;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.border.MatteBorder;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import java.awt.Font;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import Dao.DaoAlumno;
import Dao.DaoUsuario;
import Modelo.Alumno;
import Modelo.Usuario;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class vAlumno extends JFrame {

	private JPanel contentPane;
	private JTextField txtNombre;
	private JComboBox cboGrupo;
	private JComboBox cboCarrera;
	private JComboBox cboMunicipio;
	private JLabel lblId;
	private JScrollPane scrollPane_1;
	private JTable tblAlumno;
	private JButton btnEditar;
	private JButton btnEliminar;
	private JButton btnBorrar;
	private JButton btnAgregar;
	int fila = -1;
	DaoAlumno dao = new DaoAlumno();
	DefaultTableModel modelo = new DefaultTableModel();
	ArrayList<Alumno> lista = new ArrayList<Alumno>();
	Alumno alumno = new Alumno();

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					vAlumno frame = new vAlumno();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public vAlumno() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 593, 450);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNewLabel = new JLabel("ID");
		lblNewLabel.setFont(new Font("Sitka Small", Font.PLAIN, 17));
		lblNewLabel.setBorder(new LineBorder(new Color(222, 184, 135), 4));
		lblNewLabel.setBounds(36, 26, 57, 26);
		contentPane.add(lblNewLabel);

		lblId = new JLabel("");
		lblId.setBorder(new MatteBorder(0, 0, 4, 0, (Color) new Color(222, 184, 135)));
		lblId.setBounds(170, 20, 134, 26);
		contentPane.add(lblId);

		txtNombre = new JTextField();
		txtNombre.setBorder(new LineBorder(new Color(222, 184, 135), 3));
		txtNombre.setBounds(170, 50, 134, 30);
		contentPane.add(txtNombre);
		txtNombre.setColumns(10);

		cboGrupo = new JComboBox();
		cboGrupo.setFont(new Font("Stencil", Font.PLAIN, 15));
		cboGrupo.setModel(new DefaultComboBoxModel(new String[] { "101", "102", "103", "104", "105", "106", "107",
				"301", "302", "303", "304", "305", "306", "307", "501", "502", "503", "504", "505", "506", "507" }));
		cboGrupo.setBorder(new LineBorder(new Color(222, 184, 135), 3));
		cboGrupo.setBounds(170, 91, 72, 30);
		contentPane.add(cboGrupo);

		cboCarrera = new JComboBox();
		cboCarrera.setFont(new Font("Stencil", Font.PLAIN, 15));
		cboCarrera.setModel(
				new DefaultComboBoxModel(new String[] { "PROGRAMACION", "VENTAS", "MAQUINAS Y HERRAMIENTAS" }));
		cboCarrera.setBorder(new LineBorder(new Color(222, 184, 135), 3));
		cboCarrera.setBounds(170, 132, 254, 38);
		contentPane.add(cboCarrera);

		cboMunicipio = new JComboBox();
		cboMunicipio.setFont(new Font("Stencil", Font.PLAIN, 15));
		cboMunicipio.setModel(
				new DefaultComboBoxModel(new String[] { "TECAMAC", "SAN MARTIN ", "ZUMPANGO", "REYES", "JALTENCO" }));
		cboMunicipio.setBorder(new LineBorder(new Color(222, 184, 135), 3));
		cboMunicipio.setBounds(170, 181, 100, 22);
		contentPane.add(cboMunicipio);

		JLabel lblNombre = new JLabel("NOMBRE");
		lblNombre.setFont(new Font("Sitka Small", Font.PLAIN, 17));
		lblNombre.setBorder(new LineBorder(new Color(222, 184, 135), 4));
		lblNombre.setBounds(36, 63, 100, 26);
		contentPane.add(lblNombre);

		JLabel lblGrupo = new JLabel("GRUPO");
		lblGrupo.setFont(new Font("Sitka Small", Font.PLAIN, 17));
		lblGrupo.setBorder(new LineBorder(new Color(222, 184, 135), 4));
		lblGrupo.setBounds(36, 103, 100, 26);
		contentPane.add(lblGrupo);

		JLabel lblCarera = new JLabel("CARRERA");
		lblCarera.setFont(new Font("Sitka Small", Font.PLAIN, 17));
		lblCarera.setBorder(new LineBorder(new Color(222, 184, 135), 4));
		lblCarera.setBounds(36, 140, 100, 26);
		contentPane.add(lblCarera);

		JLabel lblMunicipio = new JLabel("MUNICIPIO");
		lblMunicipio.setFont(new Font("Sitka Small", Font.PLAIN, 17));
		lblMunicipio.setBorder(new LineBorder(new Color(222, 184, 135), 4));
		lblMunicipio.setBounds(36, 177, 123, 26);
		contentPane.add(lblMunicipio);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(109, 355, 210, -133);
		contentPane.add(scrollPane);

		scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(10, 248, 414, 163);
		contentPane.add(scrollPane_1);

		tblAlumno = new JTable();
		tblAlumno.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				fila = tblAlumno.getSelectedRow();
				alumno = lista.get(fila);
				lblId.setText("" + alumno.getId());
				txtNombre.setText(alumno.getNombre());
				cboCarrera.setSelectedItem(alumno.getCarrera());
				cboGrupo.setSelectedItem(alumno.getGrupo());
				cboMunicipio.setSelectedItem(alumno.getMunicipio());

			}
		});

		tblAlumno.setModel(new DefaultTableModel(
				new Object[][] { { null, null, null, null, null }, { null, null, null, null, null },
						{ null, null, null, null, null }, { null, null, null, null, null },
						{ null, null, null, null, null }, { null, null, null, null, null }, },
				new String[] { "New column", "New column", "New column", "New column", "New column" }));
		scrollPane_1.setViewportView(tblAlumno);

		btnAgregar = new JButton("AGREGAR");
		btnAgregar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (dao.insertarAlumno(alumno)) {
					actualizarTabla();
					JOptionPane.showMessageDialog(null, "SE AGREGO CORRCTAMENTE");
				} else {
					JOptionPane.showMessageDialog(null, "ERROR");
				}

			} catch (Exception ex) {
				JOptionPane.showMessageDialog(null, "ERROR");

			
			}
		});
		btnAgregar.setBorder(new LineBorder(new Color(222, 184, 135), 4));
		btnAgregar.setBounds(442, 54, 94, 26);
		contentPane.add(btnAgregar);

		btnEliminar = new JButton("ELIMINAR");
		btnEliminar.setBorder(new LineBorder(new Color(222, 184, 135), 4));
		btnEliminar.setBounds(442, 94, 94, 26);
		contentPane.add(btnEliminar);

		btnEditar = new JButton("EDITAR");
		btnEditar.setBorder(new LineBorder(new Color(222, 184, 135), 4));
		btnEditar.setBounds(442, 139, 94, 26);
		contentPane.add(btnEditar);

		btnBorrar = new JButton("BORRAR");
		btnBorrar.setBorder(new LineBorder(new Color(222, 184, 135), 4));
		btnBorrar.setBounds(442, 180, 94, 26);
		contentPane.add(btnBorrar);
	}
}

public void actualizarTabla() {
	while (modelo.getRowCount() > 0) {
		modelo.removeRow(0);
	}
	lista = dao.fetchAlumnos();
	for (Usuario u : lista) {
		Object o[] = new Object[4];
		o[0] = u.getId();
		o[1] = u.getNombre();
		o[2] = u.getCarrera();
		o[3] = u.getNombre();
		modelo.addRow(o);
	}
	tblAlumno.setModel(modelo);
}
