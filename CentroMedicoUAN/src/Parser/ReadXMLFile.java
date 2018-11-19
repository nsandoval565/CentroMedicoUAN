package Parser;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import Controlador.Fachada;
import Modelo.CentroMedico;
import Modelo.Disponibilidades;
import Modelo.Eps;
import Modelo.Especialidades;
import Modelo.Medicos;
import Modelo.Servicios;

public class ReadXMLFile {

	private Eps eps;
	private Servicios servicio;
	private Medicos medico;
	private Disponibilidades disponibilidad;
	private Especialidades especialidad;
	private CentroMedico a = Fachada.getInstance().getCentroMedico();
	public ReadXMLFile() {

	}

	public void cargar_xml() throws ParserConfigurationException, SAXException, IOException {
		File fXmlFile = new File("XML/UanSalud.xml");
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
		Document doc = dBuilder.parse(fXmlFile);

		// doc.getDocumentElement().normalize();

		NodeList nListCmedico = doc.getElementsByTagName("centro_medico");

		for (int i = 0; i < nListCmedico.getLength(); i++)// centro medico
		{
			Node nodeCM = nListCmedico.item(i);

			if (nodeCM.getNodeType() == Node.ELEMENT_NODE) {
				Element elementCM = (Element) nodeCM;

				eps = new Eps();

				eps.setNombreEps(elementCM.getElementsByTagName("nombre").item(i).getTextContent());
				System.out.println(elementCM.getElementsByTagName("nombre").item(i).getTextContent());

				eps.setMunicipioEps(elementCM.getElementsByTagName("municipio").item(i).getTextContent());
				System.out.println(elementCM.getElementsByTagName("municipio").item(i).getTextContent());

				eps.setDireccionEps(elementCM.getElementsByTagName("direccion").item(i).getTextContent());
				System.out.println(elementCM.getElementsByTagName("direccion").item(i).getTextContent());

				eps.setTelefonoEps(elementCM.getElementsByTagName("telefono").item(i).getTextContent());
				System.out.println(elementCM.getElementsByTagName("telefono").item(i).getTextContent());

				//dao.crearEpsDAO(eps);
				
				NodeList nListServicio = elementCM.getElementsByTagName("servicio");

				for (int h = 0; h < nListServicio.getLength(); h++) {
					Node nodeServ = nListServicio.item(h);

					if (nodeServ.getNodeType() == Node.ELEMENT_NODE) {
						Element elementSer = (Element) nodeServ;

						servicio = new Servicios();

						servicio.setNombreServicio(elementSer.getAttribute("nombre"));
						System.out.println(elementSer.getAttribute("nombre"));

						servicio.setDescripcionServicio(
								elementSer.getElementsByTagName("descripcion").item(0).getTextContent());
						System.out.println(elementSer.getElementsByTagName("descripcion").item(0).getTextContent());

						servicio.setDuracionServicio(
								elementSer.getElementsByTagName("duracion").item(0).getTextContent());
						System.out.println(elementSer.getElementsByTagName("duracion").item(0).getTextContent());

						servicio.setComplejidadServicio(
								elementSer.getElementsByTagName("complejidad").item(0).getTextContent());
						System.out.println(elementSer.getElementsByTagName("complejidad").item(0).getTextContent());

						servicio.setCostoServicio(elementSer.getElementsByTagName("costo").item(0).getTextContent());
						System.out.println(elementSer.getElementsByTagName("costo").item(0).getTextContent());
						
						//dao.crearServicioDAO(servicio);
					} // servicio.
					a.crearServicio(servicio);
				}

				NodeList nListMedico = elementCM.getElementsByTagName("medico");
				for (int j = 0; j < nListMedico.getLength(); j++) {

					Node nodeMed = nListMedico.item(j);

					if (nodeMed.getNodeType() == Node.ELEMENT_NODE) {
						Element elementMed = (Element) nodeMed;

						medico = new Medicos();

						medico.setIdentificacion(elementMed.getAttribute("identificacion"));
						System.out.println(elementMed.getAttribute("identificacion"));

						medico.setNombreMedico(elementMed.getElementsByTagName("nombre").item(0).getTextContent());
						System.out.println(elementMed.getElementsByTagName("nombre").item(0).getTextContent());

						medico.setApellidoMedico(elementMed.getElementsByTagName("apellido").item(0).getTextContent());
						System.out.println(elementMed.getElementsByTagName("apellido").item(0).getTextContent());

						medico.setTelefonoMedico(elementMed.getElementsByTagName("telefono").item(0).getTextContent());
						System.out.println(elementMed.getElementsByTagName("telefono").item(0).getTextContent());
						
						//dao.crearMedicoDAO(medico);
						
						NodeList nListDisp = elementMed.getElementsByTagName("fecha");

						for (int k = 0; k < nListDisp.getLength(); k++)// disponibilidad
						{

							Node nodeDisp = nListDisp.item(k);

							if (nodeDisp.getNodeType() == Node.ELEMENT_NODE) {
								Element elementDisp = (Element) nodeDisp;

								disponibilidad = new Disponibilidades();

								disponibilidad.setFecha(elementDisp.getAttribute("dia"));
								System.out.println(elementDisp.getAttribute("dia"));

								disponibilidad.setHora_inicio(elementDisp.getAttribute("horainicio"));
								System.out.println(elementDisp.getAttribute("horainicio"));

								disponibilidad.setHora_fin(elementDisp.getAttribute("horafin"));
								System.out.println(elementDisp.getAttribute("horafin"));
								
								//dao.crearDisponibilidadDAO(disponibilidad, medico);
							}

							medico.crearDisponibilidad(disponibilidad);
						}

						NodeList nListEsp = elementMed.getElementsByTagName("especialidad");

						for (int l = 0; l < nListEsp.getLength(); l++)// especialidad
						{
							especialidad = new Especialidades();
							Node nodeEsp = nListEsp.item(l);
							Element elementEsp = (Element) nodeEsp;

							especialidad.setNombreEspecialidad(elementEsp.getAttribute("nombre"));
							System.out.println(elementEsp.getAttribute("nombre"));
							
							//dao.crearEspecialidadDAO(especialidad, medico);
							medico.crearEspecialidad(especialidad);
						}

					}

					a.crearMedico(medico);
				}

			}
			a.crearEps(eps);
		}
	}
}
