package view;

import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import org.bson.Document;
import com.mongodb.MongoWriteException;
import db.DbManagement;
import model.Moneda;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class VentanaInsertar extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField tfNombre;
	private JTextField tfPais;
	private JTextField tfDescripcion;
	private JTextField tfCodPais;
	private JTextField tfValor;
	private JTextField tfAnyo;
	private JButton btnAadir;
	private JButton btnGuardar;
	private Moneda moneda;
	private ArrayList<Document> valores;

	/**
	 * Create the frame.
	 */
	public VentanaInsertar() {
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(100, 100, 508, 322);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNombre = new JLabel("Nombre:");
		lblNombre.setBounds(10, 11, 65, 14);
		contentPane.add(lblNombre);
		
		tfNombre = new JTextField();
		tfNombre.setBounds(68, 8, 241, 20);
		contentPane.add(tfNombre);
		tfNombre.setColumns(10);
		
		JLabel lblPais = new JLabel("Pais:");
		lblPais.setBounds(10, 36, 31, 14);
		contentPane.add(lblPais);
		
		tfPais = new JTextField();
		tfPais.setBounds(43, 33, 266, 20);
		contentPane.add(tfPais);
		tfPais.setColumns(10);
		
		JLabel lblDescripcin = new JLabel("Descripci\u00F3n:");
		lblDescripcin.setBounds(10, 101, 81, 14);
		contentPane.add(lblDescripcin);
		
		tfDescripcion = new JTextField();
		tfDescripcion.setBounds(85, 98, 364, 20);
		contentPane.add(tfDescripcion);
		tfDescripcion.setColumns(10);
		
		JLabel lblValores = new JLabel("VALORES");
		lblValores.setBounds(207, 129, 111, 14);
		contentPane.add(lblValores);
		
		JLabel lblCodigoPais = new JLabel("Codigo Pais:");
		lblCodigoPais.setBounds(10, 160, 81, 14);
		contentPane.add(lblCodigoPais);
		
		tfCodPais = new JTextField();
		tfCodPais.setBounds(85, 157, 65, 20);
		contentPane.add(tfCodPais);
		tfCodPais.setColumns(10);
		
		JLabel lblValorDeCambio = new JLabel("Valor de cambio:");
		lblValorDeCambio.setBounds(160, 160, 111, 14);
		contentPane.add(lblValorDeCambio);
		
		tfValor = new JTextField();
		tfValor.setBounds(258, 157, 81, 20);
		contentPane.add(tfValor);
		tfValor.setColumns(10);
		
		btnAadir = new JButton("A\u00D1ADIR");
		
		btnAadir.setBounds(360, 156, 89, 23);
		contentPane.add(btnAadir);
		
		btnGuardar = new JButton("GUARDAR MONEDA");
		btnGuardar.setBounds(160, 238, 178, 23);
		contentPane.add(btnGuardar);
		
		JLabel lblAo = new JLabel("A\u00F1o:");
		lblAo.setBounds(10, 64, 48, 14);
		contentPane.add(lblAo);
		
		tfAnyo = new JTextField();
		tfAnyo.setBounds(43, 61, 38, 20);
		
		btnAadir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(!tfCodPais.getText().equals("") &&
						!tfValor.getText().equals("")){
					valores.add(new Document(tfCodPais.getText(),
							tfValor.getText()));
					tfCodPais.setText("");
					tfValor.setText("");
				}
				else {
					JOptionPane.showMessageDialog(null,
							"Los campos Código pais o Valor de cambio\nNo pueden estar vacios");
				}
			}
		});
		
		btnGuardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DbManagement manager = DbManagement.getInstance();
				if(!tfNombre.getText().equals("") &&
					!tfPais.getText().equals("") &&
					!tfDescripcion.getText().equals("")&& 
					!tfAnyo.getText().equals("")) {
					moneda = new Moneda(tfNombre.getText(), tfPais.getText(), 
							tfAnyo.getText(), tfDescripcion.getText(), valores);
					try {
						manager.insertarNouDocument(moneda);
						tfNombre.setText("");
						tfPais.setText("");
						tfDescripcion.setText("");
						tfAnyo.setText("");
						JOptionPane.showMessageDialog(null,
								"Moneda Guardada Correctamente!!");
						valores = new ArrayList<Document>();
					}catch(MongoWriteException mge) {
						JOptionPane.showMessageDialog(null,
								"Ya existe una moneda con el mismo nombre...");
					}
				}
				else {
					JOptionPane.showMessageDialog(null,
							"Los campos Nombre, Pais, Año y descripción\n"
							+ "No pueden estar vacios");
				}
			}
		});
		contentPane.add(tfAnyo);
		tfAnyo.setColumns(10);
		
		valores = new ArrayList<Document>();
	}
}
