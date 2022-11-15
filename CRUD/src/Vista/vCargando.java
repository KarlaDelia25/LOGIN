package Vista;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JProgressBar;
import java.awt.Font;

public class vCargando extends JFrame {

	private JPanel contentPane;
	private JProgressBar barCargando = barCargando = new JProgressBar();

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					vCargando frame = new vCargando();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public void Cargando() {
		Thread hilo = new Thread(new Runnable() {

			@Override
			public void run() {
				for (int i = 0; i <= 100; i++) {
					barCargando.setValue(i);
					try {
						Thread.sleep(10);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					if (i == 100) {
						JOptionPane.showMessageDialog(null, "YA TERMINIIIII");
						System.exit(0);
					}
				}

			}

		});
		hilo.start();
	}

	public vCargando() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 658, 129);
		contentPane = new JPanel();
		this.setUndecorated(true);
		this.setLocationRelativeTo(null);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		barCargando = new JProgressBar();
		barCargando.setStringPainted(true);
		barCargando.setFont(new Font("Tw Cen MT Condensed", Font.PLAIN, 19));
		barCargando.setBounds(0, 0, 658, 129);
		contentPane.add(barCargando);
		Cargando();
	}
}
