package prog.ud06.actividad611.proveedores;

import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import prog.ud06.actividad611.coleccion.Cliente;
import prog.ud06.actividad611.coleccion.ProveedorUsuarios;
import prog.ud06.actividad611.coleccion.ProveedorUsuariosException;
import prog.ud06.actividad611.coleccion.TarjetaClaves;
import prog.ud06.actividad611.coleccion.Usuario;
import prog.ud06.actividad611.coleccion.Usuarios;

public class ProveedorUsuariosArchivoXML implements ProveedorUsuarios {

  private String rutaArchivo;

  public ProveedorUsuariosArchivoXML(String archivo) {
    this.rutaArchivo = archivo;
  }

  @Override
  public Usuarios obtieneUsuarios() throws ProveedorUsuariosException {
    // Obtiene información Usuario
    Usuarios usuarios = new Usuarios();
    List<Usuario> listaUsuarios = inicioUsuarioXML(rutaArchivo);

    // Para cada usuario obtenemos su tarjeta y sus clientes
    for (Usuario usuario : listaUsuarios) {
      TarjetaClaves tarjeta = new TarjetaClaves(1, 1);
      List<Cliente> clientes = inicioClientesUsuariosXML(rutaArchivo, usuario.getNombreUsuario());

      // Creamos un nuevo objeto Usuario con la tarjeta y clientes obtenidos
      Usuario usuarioConInfo = new Usuario(usuario.getNombreUsuario(), usuario.getNombreCompleto(), tarjeta, clientes);
      usuarios.addUsuario(usuarioConInfo);
    }
    return usuarios;
  }

  private List<Usuario> inicioUsuarioXML(String archivo) throws ProveedorUsuariosException {
    List<Usuario> listaUsuarios = new ArrayList<>();
    try {
      // Accedemos al Documento
      DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
      Document documento = builder.parse(archivo);
      // Obtenemos los elementos de tipo usuario
      NodeList listadoUsuariosXML = documento.getElementsByTagName("usuario");
      // Para cada elemento del documento
      for (int i = 0; i < listadoUsuariosXML.getLength(); i++) {
        Element usuarioElemento = (Element) listadoUsuariosXML.item(i);
        // Obtenemos el id y el nombre de usuario
        String nombreUsuario = usuarioElemento.getAttribute("id");
        String nombreCompleto = usuarioElemento.getAttribute("nombre");

        Usuario usuario = new Usuario(nombreUsuario, nombreCompleto, null, null);
        listaUsuarios.add(usuario);
      }
    } catch (Exception e) {
      throw new ProveedorUsuariosException();
    }
    return listaUsuarios;
  }

  private TarjetaClaves inicioTarjetasXML(String archivo, String nombreUsuario)
      throws ProveedorUsuariosException {
    try {
      // Accedemos al Documento
      DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
      Document documento = builder.parse(archivo);
      // Obtenemos los elementos de tipo tarjeta asociados al usuario
      NodeList listaTarjetas = documento.getElementsByTagName("tarjeta");
      // Para cada elemento del documento
      for (int i = 0; i < listaTarjetas.getLength(); i++) {
        Element tarjeta = (Element) listaTarjetas.item(i);
        String usuarioId = tarjeta.getAttribute("usuario");
        if (usuarioId.equals(nombreUsuario)) {
          NodeList listaFilas = tarjeta.getElementsByTagName("fila");
          int filas = listaFilas.getLength();
          int columnas = ((Element) listaFilas.item(0)).getElementsByTagName("celda").getLength();
          return new TarjetaClaves(filas, columnas);
        }
      }
    } catch (Exception e) {
      throw new ProveedorUsuariosException();
    }
    return null;
  }

  private List<Cliente> inicioClientesUsuariosXML(String archivo, String nombreUsuario)
      throws ProveedorUsuariosException {
    List<Cliente> clientes = new ArrayList<>();
    try {
      // Accedemos al Documento
      DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
      Document documento = builder.parse(archivo);
      // Obtenemos los elementos de tipo cliente asociados al usuario
      NodeList listaClientes = documento.getElementsByTagName("cliente");
      // Para cada elemento del documento
      for (int i = 0; i < listaClientes.getLength(); i++) {
        Element cliente = (Element) listaClientes.item(i);
        String usuarioId = cliente.getAttribute("usuario");
        if (usuarioId.equals(nombreUsuario)) {
          String nombre = cliente.getElementsByTagName("nombre").item(0).getTextContent();
          String dni = cliente.getElementsByTagName("dni").item(0).getTextContent();
          String apellidos = cliente.getElementsByTagName("apellidos").item(0).getTextContent();
          int edad = Integer.parseInt(cliente.getElementsByTagName("edad").item(0).getTextContent());
          Cliente clienteXML = new Cliente(nombre, apellidos, dni, edad);
          clientes.add(clienteXML);
        }
      }
    } catch (Exception e) {
      throw new ProveedorUsuariosException();
    }
    return clientes;
  }
}
