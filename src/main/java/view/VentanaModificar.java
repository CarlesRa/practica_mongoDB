package view;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import org.bson.Document;
import com.mongodb.client.FindIterable;
import db.DbManagement;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;

public class VentanaModificar extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField tfNomMoneda;
	private JTextField tfPais;
	private JTextField tfDescripcion;
	private JTextField tfAnyo;
	private JLabel lblNombreDeMoneda;
	private JLabel lblPais;
	private JLabel lblDescipcin;
	private JLabel lblAo;
	private JButton btnBuscar;
	private JButton btnModificar;
	private DbManagement manager;
	private Document documento;
	
	

	/**
	 * Create the frame.
	 */
	public VentanaModificar() {
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(100, 100, 400, 286);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		lblNombreDeMoneda = new JLabel("Nombre De Moneda:");
		lblNombreDeMoneda.setBounds(10, 29, 118, 14);
		contentPane.add(lblNombreDeMoneda);
		
		tfNomMoneda = new JTextField();
		tfNomMoneda.setBounds(138, 26, 96, 20);
		contentPane.add(tfNomMoneda);
		tfNomMoneda.setColumns(10);
		
		btnBuscar = new JButton("BUSCAR");
		btnBuscar.setBounds(250, 25, 89, 23);
		contentPane.add(btnBuscar);
		
		lblPais = new JLabel("Pais:");
		lblPais.setBounds(31, 92, 36, 14);
		contentPane.add(lblPais);
		
		tfPais = new JTextField();
		tfPais.setBounds(66, 89, 273, 20);
		contentPane.add(tfPais);
		tfPais.setColumns(10);
		
		lblDescipcin = new JLabel("Descipci\u00F3n:");
		lblDescipcin.setBounds(31, 142, 65, 14);
		contentPane.add(lblDescipcin);
		
		tfDescripcion = new JTextField();
		tfDescripcion.setBounds(95, 139, 244, 20);
		contentPane.add(tfDescripcion);
		tfDescripcion.setColumns(10);
		
		lblAo = new JLabel("A\u00F1o:");
		lblAo.setBounds(31, 117, 36, 14);
		contentPane.add(lblAo);
		
		tfAnyo = new JTextField();
		tfAnyo.setBounds(66, 114, 273, 20);
		contentPane.add(tfAnyo);
		tfAnyo.setColumns(10);
		
		btnModificar = new JButton("MODIFICAR");
		btnModificar.setBounds(116, 193, 118, 23);
		contentPane.add(btnModificar);
		lblPais.setVisible(false);
		lblDescipcin.setVisible(false);
		lblAo.setVisible(false);
		tfPais.setVisible(false);
		tfAnyo.setVisible(false);
		tfDescripcion.setVisible(false);
		
		btnModificar.setVisible(false);
		manager = DbManagement.getInstance();
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!tfNomMoneda.getText().equals("")){
					FindIterable<Document> docs = 
							manager.findOneDocument(tfNomMoneda.getText());
					for (Document document : docs) {
						documento = document;
					}
					if (documento != null) {
						lblPais.setVisible(true);									
						lblDescipcin.setVisible(false);
						lblAo.setVisible(true);
						
						/*tfNombre.setVisible(true);
						tfNombre.setText(documento.getString(manager.KEY_ID));*/
						tfPais.setVisible(true);
						tfPais.setText(documento.getString(manager.KEY_PAIS));
						tfAnyo.setVisible(true);
						tfAnyo.setText(documento.getString(manager.KEY_ANYO));
						tfDescripcion.setVisible(true);
						tfDescripcion.setText(documento.getString(manager.KEY_DESCRIPCION));
						btnModificar.setVisible(true);
					}
					else {
						JOptionPane.showMessageDialog(null,
								"No se ha encontrado la moneda");
					}
				}
				else {
					JOptionPane.showMessageDialog(null,
							"El campo no puede estar vacio");
				}
				
			}
		});
		
		btnModificar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(documento != null) {
					List<Document> valores = documento.get(manager.KEY_VALORES,new ArrayList<Document>());
					Document objectMoneda = new Document();
	        		objectMoneda.put(manager.KEY_ID, tfNomMoneda.getText());
	        		objectMoneda.put(manager.KEY_PAIS, tfPais.getText());
	        		objectMoneda.put(manager.KEY_ANYO, tfAnyo.getText());
	        		objectMoneda.put(manager.KEY_DESCRIPCION, tfDescripcion.getText());
	        		objectMoneda.put(manager.KEY_VALORES, valores);
	        		manager.modificar(objectMoneda);
	        		lblPais.setVisible(false);
	        		lblDescipcin.setVisible(false);
	        		lblAo.setVisible(false);
	        		tfPais.setVisible(false);
	        		tfPais.setText("");
	        		tfAnyo.setVisible(false);
	        		tfAnyo.setText("");
	        		tfDescripcion.setVisible(false);
	        		tfDescripcion.setText("");
	        		
	        		btnModificar.setVisible(false);
	        		documento = null;
					JOptionPane.showMessageDialog(null,
							"Moneda modificada correctamente");
				}
				else {
					JOptionPane.showMessageDialog(null,
							"No se ha encontrado moneda para guardar");
				}
			}
		});
	}
}
