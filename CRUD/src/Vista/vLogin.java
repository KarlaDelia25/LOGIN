package Vista;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Dao.DaoUsuario;
import Modelo.Usuario;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JPasswordField;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.SystemColor;
import java.awt.Color;

public class vLogin extends JFrame {

	private JPanel contentPane;
	private JButton btnEntrar;
	private JButton btnCancelar;
	private JPasswordField txtPassword;
	private JTextField txtUser;
	private JLabel lblNewLabel_1;
	DaoUsuario dao = new DaoUsuario();

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					vLogin frame = new vLogin();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public vLogin() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 869, 313);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.activeCaptionText);
		this.setUndecorated(true);
		this.setLocationRelativeTo(null);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNewLabel = new JLabel("Usuario");
		lblNewLabel.setForeground(SystemColor.text);
		lblNewLabel.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		lblNewLabel.setBounds(51, 22, 72, 38);
		contentPane.add(lblNewLabel);

		JLabel lblPassword = new JLabel("Password");
		lblPassword.setForeground(SystemColor.text);
		lblPassword.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		lblPassword.setBounds(51, 107, 72, 38);
		contentPane.add(lblPassword);

		btnEntrar = new JButton("Entrar");
		btnEntrar.setBackground(SystemColor.activeCaptionText);
		btnEntrar.setIcon(null);
		btnEntrar.setForeground(SystemColor.inactiveCaptionBorder);
		btnEntrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Usuario user = new Usuario();
				user.setUser(txtUser.getText());
				user.setPasword(String.valueOf(txtPassword.getPassword()));
				if (dao.loginUsuarios(user)) {
					JOptionPane.showMessageDialog(null, "BIENVENIDO MANITO");
					vCargando cargando = new vCargando();
					setVisible(false);
					cargando.setVisible(true);
				} else {
					JOptionPane.showMessageDialog(null, "Usuario y / o Contraseña incorrecta ");

				}

			}
		});
		btnEntrar.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		btnEntrar.setBounds(70, 197, 89, 23);
		contentPane.add(btnEntrar);

		btnCancelar = new JButton("Cancelar");
		btnCancelar.setBackground(SystemColor.desktop);
		btnCancelar.setForeground(SystemColor.inactiveCaptionBorder);
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, "ADIOSITO");
				System.exit(0);
			}
		});
		btnCancelar.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		btnCancelar.setBounds(213, 197, 111, 23);
		contentPane.add(btnCancelar);

		txtPassword = new JPasswordField();
		txtPassword.setForeground(new Color(0, 102, 153));
		txtPassword.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				if (txtPassword.getText().length() > 10) {
					e.consume();
				}
			}
		});
		txtPassword.setBounds(51, 156, 301, 20);
		contentPane.add(txtPassword);

		txtUser = new JTextField();
		txtUser.setForeground(new Color(0, 128, 128));
		txtUser.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				if (txtUser.getText().length() >=10) {
					e.consume();
				}
			}
		});
		txtUser.setBounds(51, 76, 301, 20);
		contentPane.add(txtUser);
		txtUser.setColumns(10);

		lblNewLabel_1 = new JLabel("New label");
		lblNewLabel_1.setIcon(new ImageIcon("C:\\Users\\Alumnos\\Pictures\\Nicky.png"));
		lblNewLabel_1.setBounds(362, 48, 483, 237);
		contentPane.add(lblNewLabel_1);
	}
}
