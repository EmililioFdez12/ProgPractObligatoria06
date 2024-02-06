package prog.ud06.actividad611.proveedores;

import java.util.HashMap;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import prog.ud06.actividad611.coleccion.ProveedorUsuarios;
import prog.ud06.actividad611.coleccion.ProveedorUsuariosException;
import prog.ud06.actividad611.coleccion.Usuarios;

public class ProveedorUsuariosArchivosXML implements ProveedorUsuarios {

  private String archivo;
  private Map<String, String> usuarios;
  private Map<String, String> tarjetasUsuarios;
  private Map<String, String> clientesUsuarios;

  public ProveedorUsuariosArchivosXML(String archivo) {
    this.archivo = archivo;
  }

  private void inicioUsuarioXML(String archivo) throws ProveedorUsuariosException {
    try {
      // Accedemos al Documento
      DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
      Document documento = builder.parse(archivo);
      // Obtenemos los elementos de tipo usuarios
      NodeList listaUsuarios = documento.getElementsByTagName("usuarios");
      usuarios = new HashMap<>();
      // Para cada elemento del documento
      for (int i = 0; i < listaUsuarios.getLength(); i++) {
        Element usuario = (Element) listaUsuarios.item(i);
        // Obtenemos el id y el nombre de usuario, y lo guardamos en un mapa
        String usuarioId = usuario.getElementsByTagName("id").item(0).getTextContent();
        String usuarioNombre = usuario.getElementsByTagName("nombre").item(0).getTextContent();
        tarjetasUsuarios.put(usuarioId, usuarioNombre);
      }
    } catch (Exception e) {
      // Lanza nuestra excepcion
      throw new ProveedorUsuariosException();
    }
  }

  private void inicioTarjetasXML(String archivo) throws ProveedorUsuariosException {
    try {
      // Accedemos al Documento
      DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
      Document documento = builder.parse(archivo);
      // Obtenemos los elementos de tipo usuarios
      NodeList listaTarjetas = documento.getElementsByTagName("tarjeta");

      // Para cada elemento del documento
      for (int i = 0; i < listaTarjetas.getLength(); i++) {
        Element tarjeta = (Element) listaTarjetas.item(i);

        String usuarioId = tarjeta.getAttribute("usuario");

        NodeList listaFilas = documento.getElementsByTagName("fila");
        for (int j = 0; j < listaFilas.getLength(); j++) {
          Element fila = (Element) listaFilas.item(j);

          NodeList listaCeldas = documento.getElementsByTagName("celda");
          for (int k = 0; k < listaCeldas.getLength(); k++) {
            Element celda = (Element) listaCeldas.item(k);
            String valorCelda = celda.getTextContent();

            // Guardamos los datos en un mapa
            tarjetasUsuarios.put(usuarioId, valorCelda);
          }
        }
      }
    } catch (Exception e) {
      throw new ProveedorUsuariosException();
    }
    
    
    
    private void inicioClientesUsuariosXML(String archivo) throws ProveedorUsuariosException {
      try {
        // Accedemos al Documento
        DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
        Document documento = builder.parse(archivo);
        // Obtenemos los elementos de tipo usuarios
        NodeList listaClientes = documento.getElementsByTagName("cliente");

        // Para cada elemento del documento
        for (int i = 0; i < listaClientes.getLength(); i++) {
          Element cliente = (Element) listaClientes.item(i);

          String usuarioId = cliente.getAttribute("usuario");

          NodeList listaNombre = documento.getElementsByTagName("nombre");
          for (int j = 0; j < listaNombre.getLength(); j++) {
            Element fila = (Element) listaNombre.item(j);

            NodeList listaCeldas = documento.getElementsByTagName("celda");
            for (int k = 0; k < listaCeldas.getLength(); k++) {
              Element celda = (Element) listaCeldas.item(k);
              String valorCelda = celda.getTextContent();

              // Guardamos los datos en un mapa
              tarjetasUsuarios.put(usuarioId, valorCelda);
            }
          }
        }
      } catch (Exception e) {
        throw new ProveedorUsuariosException();
      }

  }

  @Override
  public Usuarios obtieneUsuarios() {
    Usuarios usuarios = new Usuarios();
    return null;

  }

}
