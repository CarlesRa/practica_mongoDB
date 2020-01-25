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
import java.awt.event.ActionEvent;

public class VentanaEliminar extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField tfNombre;
	private DbManagement manager;
	private Document documento;
	private JButton btnEliminar;
	/**
	 * Create the frame.
	 */
	public VentanaEliminar() {
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(100, 100, 440, 136);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNombreMoneda = new JLabel("Nombre Moneda:");
		lblNombreMoneda.setBounds(10, 27, 108, 14);
		contentPane.add(lblNombreMoneda);
		
		tfNombre = new JTextField();
		tfNombre.setBounds(128, 24, 130, 20);
		contentPane.add(tfNombre);
		tfNombre.setColumns(10);
		
		btnEliminar = new JButton("ELIMINAR");
		
		btnEliminar.setBounds(268, 23, 118, 23);
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(!tfNombre.getText().equals("")) {
					FindIterable<Document> docs = 
						manager.findOneDocument(tfNombre.getText());
					for (Document document : docs) {
						documento = document;
					}
					if (documento != null) {
						manager.borrarDocument(documento);
						tfNombre.setText("");
						JOptionPane.showMessageDialog(null, "Moneda eliminada correctamente");
					}
					else {
						JOptionPane.showMessageDialog(null, "No se encontro la moneda");
					}
				}
				else {
					JOptionPane.showMessageDialog(null, "El campo Nombre no puede estar vacio");
				}
			}
		});
		contentPane.add(btnEliminar);
		manager = DbManagement.getInstance();
	}

}
