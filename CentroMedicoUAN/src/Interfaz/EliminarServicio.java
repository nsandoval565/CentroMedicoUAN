package Interfaz;

import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import Controlador.Fachada;
import Modelo.CentroMedico;
import Modelo.Servicios;

public class EliminarServicio extends JFrame implements ActionListener{
	private JButton btnVolver;
	private JButton btnVerServicio;
	private JButton btnEliminarServicio;
	private JLabel nomServicio;
	private JLabel desServicio;
	private JLabel comServicio;
	private JLabel durServicio;
	private JLabel cosServicio;
	private JTextField txtNomServicio;
	private JTextField txtDesServicio;
	private JTextField txtComServicio;
	private JTextField txtDurServicio;
	private JTextField txtCosServicio;
	private JComboBox listaServicios;
	private Servicios servicio = new Servicios();
	private String nombreServicio;
	//private DAO_Eliminar dao = new DAO_Eliminar();
	private CentroMedico a = Fachada.getInstance().getCentroMedico();
	
	public EliminarServicio(){
		super();
		setIconImage(Toolkit.getDefaultToolkit().getImage
		(ClassLoader.getSystemResource("Imagenes/Servicio.jpg")));
		this.setTitle("Eliminar Servicio");
		this.setSize(400, 300);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setLayout(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.crearEtiquetas();
		this.crearIngresoDatos();
		this.crearBotones();
	}

	private void crearEtiquetas() {
		this.nomServicio=new JLabel();
		this.nomServicio.setText("Nombre");
		this.nomServicio.setBounds(10, 30, 200, 80);
		this.add(nomServicio);
		
		this.desServicio=new JLabel();
		this.desServicio.setText("Descripcion");
		this.desServicio.setBounds(10, 60, 200, 80);
		this.add(desServicio);
		
		this.comServicio=new JLabel();
		this.comServicio.setText("Complejidad");
		this.comServicio.setBounds(10, 90, 200, 80);
		this.add(comServicio);
		
		this.durServicio=new JLabel();
		this.durServicio.setText("Duracion");
		this.durServicio.setBounds(10, 120, 200, 80);
		this.add(durServicio);
		
		this.cosServicio=new JLabel();
		this.cosServicio.setText("Costo");
		this.cosServicio.setBounds(10, 150, 200, 80);
		this.add(cosServicio);
	}
	
	private void crearIngresoDatos() {
		this.txtNomServicio=new JTextField();
		this.txtNomServicio.setEditable(false);
		this.txtNomServicio.setBounds(90, 60, 180, 20);
		this.add(txtNomServicio);
		
		this.txtDesServicio=new JTextField();
		this.txtDesServicio.setEditable(false);
		this.txtDesServicio.setBounds(90, 90, 180, 20);
		this.add(txtDesServicio);
		
		this.txtComServicio=new JTextField();
		this.txtComServicio.setEditable(false);
		this.txtComServicio.setBounds(90, 120, 180, 20);
		this.add(txtComServicio);
		
		this.txtDurServicio=new JTextField();
		this.txtDurServicio.setEditable(false);
		this.txtDurServicio.setBounds(90, 150, 180, 20);
		this.add(txtDurServicio);
		
		this.txtCosServicio=new JTextField();
		this.txtCosServicio.setEditable(false);
		this.txtCosServicio.setBounds(90, 180, 180, 20);
		this.add(txtCosServicio);
	}
	
	private void crearBotones() {
		this.btnVolver=new JButton();
		this.btnVolver.setText("Volver");
		this.btnVolver.setBounds(210, 210, 120, 20);
		btnVolver.addActionListener(this);
		this.add(btnVolver);
		
		this.btnEliminarServicio=new JButton();
		this.btnEliminarServicio.setText("Eliminar Servicio");
		this.btnEliminarServicio.setBounds(20, 210, 140, 20);
		btnEliminarServicio.addActionListener(this);
		this.add(btnEliminarServicio);
		
		this.btnVerServicio=new JButton();
		this.btnVerServicio.setText("Visualizar Servicio");
		this.btnVerServicio.setBounds(200, 20, 140, 20);
		btnVerServicio.addActionListener(this);
		this.add(btnVerServicio);
		
		this.listaServicios = new JComboBox();
		this.listaServicios.setBounds(10, 20, 149, 20);
		this.add(listaServicios);
		this.leerServicios();
		
		this.llenarServicios();
	}
	public void actionPerformed(ActionEvent e) {	
		String nombreServicio = (String) listaServicios.getSelectedItem();
		
		if(e.getSource()==btnVolver){
			VentanaPrincipal ventana = new VentanaPrincipal();
			ventana.setVisible(true);
			setVisible(false);
		}
		
		if(e.getSource()==btnVerServicio){
			llenarServicios();
		}
		
		if(e.getSource()==btnEliminarServicio){
			a.eliminarServicio(nombreServicio);
			//dao.eliminarServicioDAO(servicio);
			EliminarServicio ven = new EliminarServicio();
			this.setVisible(false);
			ven.setVisible(true);
			JOptionPane.showMessageDialog(null, "Servicio Eliminado");
		}
	}
	
	private void leerServicios() {
		DefaultComboBoxModel slista = new DefaultComboBoxModel();
		for (int i = 0; i < a.getLstServicios().size(); i++) {
			slista.addElement(a.getLstServicios().get(i).getNombreServicio());
		}
		listaServicios.setModel(slista);
		getContentPane().add(listaServicios);
	}
	
	private void llenarServicios() {
		String identificacion = (String) listaServicios.getSelectedItem();
		servicio = a.buscarServicio(servicio, identificacion);
		txtNomServicio.setText(servicio.getNombreServicio());
		txtDesServicio.setText(servicio.getDescripcionServicio());
		txtComServicio.setText(servicio.getComplejidadServicio());
		txtDurServicio.setText(servicio.getDuracionServicio());
		txtCosServicio.setText(servicio.getCostoServicio());
	}
}
