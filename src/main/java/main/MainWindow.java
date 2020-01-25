package main;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import view.VentanaEliminar;
import view.VentanaInsertar;
import view.VentanaModificar;
import view.VentanaVerRegistros;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
public class MainWindow extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private static JMenuItem mVerMonedas;
	private static JMenuItem mInsertar;
	private static JMenuItem mModificar;
	private static JMenuItem mBorrar;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainWindow frame = new MainWindow();
					frame.setVisible(true);
					mVerMonedas.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							VentanaVerRegistros vVer = new VentanaVerRegistros();
							vVer.setVisible(true);
							
						}
					});
					
					mInsertar.addActionListener(new ActionListener() {
						
						public void actionPerformed(ActionEvent e) {
							VentanaInsertar vIns = new VentanaInsertar();
							vIns.setVisible(true);
							
						}
					});
					
					mModificar.addActionListener(new ActionListener() {
						
						public void actionPerformed(ActionEvent e) {
							VentanaModificar vMod = new VentanaModificar();
							vMod.setVisible(true);
						}
					});
					
					mBorrar.addActionListener(new ActionListener() {
						
						public void actionPerformed(ActionEvent e) {
							VentanaEliminar vEliminar = new VentanaEliminar();
							vEliminar.setVisible(true);
							
						}
					});
			
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public MainWindow() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnMen = new JMenu("Men\u00FA");
		menuBar.add(mnMen);
		
		mVerMonedas = new JMenuItem("Ver Monedas");
		mnMen.add(mVerMonedas);
		
		mInsertar = new JMenuItem("Insertar Moneda");
		mnMen.add(mInsertar);
		
		mModificar = new JMenuItem("Modificar Moneda");
		mnMen.add(mModificar);
		
		mBorrar = new JMenuItem("Borrar Moneda");
		mnMen.add(mBorrar);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblColeccinDeMonedas = new JLabel("Colecci\u00F3n de monedas");
		lblColeccinDeMonedas.setFont(new Font("Tahoma", Font.PLAIN, 34));
		lblColeccinDeMonedas.setBounds(52, 74, 360, 62);
		contentPane.add(lblColeccinDeMonedas);
	}
}
