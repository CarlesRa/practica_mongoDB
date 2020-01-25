package view;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import org.bson.Document;
import db.DbManagement;
import javax.swing.JTextArea;

public class VentanaVerRegistros extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextArea taLista;
	
	/**
	 * Create the frame.
	 */
	public VentanaVerRegistros() {
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		taLista = new JTextArea();
		taLista.setBounds(10, 11, 414, 239);
		DbManagement manager = DbManagement.getInstance();
		List <Document>resultados2 = manager.obtenerDatos();
		StringBuilder sb = new StringBuilder();
		Set<Map.Entry<String,Object>> entrySet = null;
		for (int i=0; i<resultados2.size(); i++) {
			Document d = resultados2.get(i);
			sb.append("Nombre:").append(d.getString(manager.KEY_ID))
			.append(", ")
			.append("Pais:").append(d.getString(manager.KEY_PAIS))
			.append(", ")
			.append("Año:").append(d.getString(manager.KEY_ANYO))
			.append(", ")
			.append("Descripcion:")
			.append(d.getString(manager.KEY_DESCRIPCION))
			.append("\n");
			List<Document> valores = d.get(manager.KEY_VALORES,new ArrayList<Document>());
			for(int z=0; z<valores.size(); z++){
				Document dValores = valores.get(z);
				entrySet = dValores.entrySet();
				for (Entry<String, Object> e  : entrySet) {
					sb.append(e.getKey())
					.append(":")
					.append(e.getValue())
					.append(" ");
				}
			}
			sb.append("\n");
		}
		taLista.setText(sb.toString());
		contentPane.add(taLista);
	}
}
