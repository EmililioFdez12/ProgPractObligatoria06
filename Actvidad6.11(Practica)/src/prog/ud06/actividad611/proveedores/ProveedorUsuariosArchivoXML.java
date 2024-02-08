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
    Usuarios usuariosObtenidos = inicioUsuarioXML(rutaArchivo);
    return usuariosObtenidos;
  }

  private Usuarios inicioUsuarioXML(String archivo) throws ProveedorUsuariosException {
    Usuarios usuarios = new Usuarios();
    try {
      // Accedemos al Documento
      DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
      Document documento = builder.parse(archivo);
      // Obtenemos los elementos de tipo usuario
      NodeList listadoUsuariosXML = documento.getElementsByTagName("usuario");
      for (int i = 0; i < listadoUsuariosXML.getLength(); i++) {
        Element usuarioElement = (Element) listadoUsuariosXML.item(i);
        String idUsuario = usuarioElement.getAttribute("id");
        String nombreCompleto = usuarioElement.getAttribute("nombre");
        // Si el usuario de la tarjeta es igual al Usuario se crea un usuario.
        TarjetaClaves tarjeta = setTarjetaXML(archivo, idUsuario);
        List<Cliente> clientes = setCliente(archivo, idUsuario);

        if (tarjeta != null && clientes != null && !clientes.isEmpty()) {
          Usuario usuario = new Usuario(idUsuario, nombreCompleto, tarjeta, clientes);
          usuarios.addUsuario(usuario);
        }
      }
    } catch (Exception e) {
      throw new ProveedorUsuariosException();
    }
    return usuarios;
  }

  private TarjetaClaves setTarjetaXML(String archivo, String nombreUsuario) throws ProveedorUsuariosException {
    TarjetaClaves tarjetaUsuario = null; // Declarar la tarjeta fuera del ciclo
    try {
      // Accedemos al Documento
      DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
      Document documento = builder.parse(archivo);
      // Obtenemos los elementos de tipo tarjeta asociados al usuario
      NodeList listaTarjetas = documento.getElementsByTagName("tarjeta");
      // Para cada elemento del documento
      for (int i = 0; i < listaTarjetas.getLength(); i++) {
        Element tarjetaElement = (Element) listaTarjetas.item(i);
        String usuarioTarjeta = tarjetaElement.getAttribute("usuario");
        if (usuarioTarjeta.equals(nombreUsuario)) {
          NodeList listaFilas = tarjetaElement.getElementsByTagName("fila"); // Corregir el acceso a las filas
          int filas = listaFilas.getLength();
          int columnas = ((Element) listaFilas.item(0)).getElementsByTagName("celda").getLength();

          tarjetaUsuario = new TarjetaClaves(filas, columnas); // Asignar la tarjeta creada
        }
      }
    } catch (Exception e) {
      throw new ProveedorUsuariosException();
    }
    return tarjetaUsuario;
  }

  private List<Cliente> setCliente(String archivo, String nombreUsuario) throws ProveedorUsuariosException {
    List<Cliente> listaClientes = new ArrayList<>();
    try {
      // Accedemos al Documento
      DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
      Document documento = builder.parse(archivo);
      // Obtenemos los elementos de tipo cliente asociados al usuario
      NodeList listaClientesXML = documento.getElementsByTagName("cliente");

      for (int i = 0; i < listaClientesXML.getLength(); i++) {
        Element clienteElement = (Element) listaClientesXML.item(i);
        String clienteUsuario = clienteElement.getAttribute("usuario");
        if (clienteUsuario.equals(nombreUsuario)) {
          // Obtenemos los elementos hijos de cliente: nombre, apellidos, dni, edad
          String nombre = obtenerValorElemento(clienteElement, "nombre");
          String apellidos = obtenerValorElemento(clienteElement, "apellidos");
          String dni = obtenerValorElemento(clienteElement, "dni");
          int edad = Integer.parseInt(obtenerValorElemento(clienteElement, "edad"));

          Cliente cliente = new Cliente(nombre, apellidos, dni, edad);
          listaClientes.add(cliente);
        }
      }
    } catch (Exception e) {
      throw new ProveedorUsuariosException();
    }
    return listaClientes;
  }

  // Lee los atributos de los elementos
  private String obtenerValorElemento(Element elementoPadre, String nombreElemento) {
    // Obtiene el primer elemento hijo con el nombre especificado
    NodeList nodos = elementoPadre.getElementsByTagName(nombreElemento);
    if (nodos.getLength() > 0) {
      return nodos.item(0).getTextContent();
    } else {
      return ""; // Devuelve una cadena vacía si el elemento no se encuentra
    }
  }
}