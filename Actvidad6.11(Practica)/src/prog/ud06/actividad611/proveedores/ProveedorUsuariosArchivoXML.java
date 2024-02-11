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

/**
 * Implementación de ProveedorUsuarios que obtiene la información desde un
 * archivo XML
 */
public class ProveedorUsuariosArchivoXML implements ProveedorUsuarios {

	// Ruta del archivo a leer
	private String rutaArchivo;

	/**
	 * Constructor
	 * 
	 * @param archivo Ruta al archivo XML que contiene la información de los
	 *                usuarios
	 */
	public ProveedorUsuariosArchivoXML(String archivo) {
		this.rutaArchivo = archivo;
	}

	@Override
	public Usuarios obtieneUsuarios() throws ProveedorUsuariosException {
		Usuarios usuariosObtenidos = inicioUsuarioXML(rutaArchivo);
		return usuariosObtenidos;
	}

	/**
	 * Metodo que lee el archivo y agrega los usuarios al contenedor Usuarios con
	 * todo sus datos
	 * 
	 * @param archivo Ruta al archivo XML que contiene la información de los
	 *                usuarios
	 * @return Contenedor Usuarios con sus usuarios
	 * 
	 * @throws ProveedorUsuariosException Si hay algún error obteniendo la
	 *                                    información
	 */
	private Usuarios inicioUsuarioXML(String archivo) throws ProveedorUsuariosException {
		Usuarios usuarios = new Usuarios();
		try {
			// Accedemos al Documento
			DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
			Document documento = builder.parse(archivo);
			// Obtenemos los elementos de tipo usuario
			NodeList listadoUsuariosXML = documento.getElementsByTagName("usuario");
			// Recorremos todos los elementos "usuario"
			for (int i = 0; i < listadoUsuariosXML.getLength(); i++) {
				Element usuarioElement = (Element) listadoUsuariosXML.item(i);
				// Guardamas el idUsuario
				String idUsuario = usuarioElement.getAttribute("id");
				// Guardamos el nombre de usuario
				String nombreCompleto = usuarioElement.getAttribute("nombre");
				// Creamos la tarjeta implementando el metodo setTarjeta
				TarjetaClaves tarjeta = setTarjetaXML(archivo, idUsuario);
				// Creamos la lista de clientes implementando el metodo setClientes
				List<Cliente> clientes = setCliente(archivo, idUsuario);
				// Si la tarjeta y el listado de clientes no es null ni esta vacío
				// Creamos un usuario, y lo metemos dentro del contenedor de usuarios
				if (tarjeta != null && clientes != null && !clientes.isEmpty()) {
					Usuario usuario = new Usuario(idUsuario, nombreCompleto, tarjeta, clientes);
					usuarios.addUsuario(usuario);
				}
			}
		} catch (Exception e) {
			throw new ProveedorUsuariosException();
		}
		// Devuelve el contenedor de usuarios
		return usuarios;
	}

	// Metodo para crear una tarjeta en funcion del usuario
	private TarjetaClaves setTarjetaXML(String archivo, String nombreUsuario) throws ProveedorUsuariosException {
		TarjetaClaves tarjetaUsuario = null;
		try {
			// Accedemos al Documento
			DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
			Document documento = builder.parse(archivo);
			// Obtenemos los elementos de tipo tarjeta asociados al usuario
			NodeList listaTarjetas = documento.getElementsByTagName("tarjeta");
			// Para cada elemento de tarjeta
			for (int i = 0; i < listaTarjetas.getLength(); i++) {
				Element tarjetaElement = (Element) listaTarjetas.item(i);
				// Guardamos el usuario asociado a una tarjeta
				String usuarioTarjeta = tarjetaElement.getAttribute("usuario");
				// Si el usuario que hemos guardado es igual al usuario con el que queremos
				// crear la tarjeta
				if (usuarioTarjeta.equals(nombreUsuario)) {
					// Obtenemos la lista de filas
					NodeList listaFilas = tarjetaElement.getElementsByTagName("fila");
					// Guardamos la longitud de las filas (numero de filas)
					int filas = listaFilas.getLength();
					// Guardamos la longitud de las columnas (numero de columnas)
					int columnas = ((Element) listaFilas.item(0)).getElementsByTagName("celda").getLength();
					// Creamos la tarjeta vacía
					tarjetaUsuario = new TarjetaClaves(filas, columnas);

					// Recorremos las filas
					for (int j = 0; j < filas; j++) {
						Element filaElement = (Element) listaFilas.item(j);
						// Lista de celdas
						NodeList listaCeldas = filaElement.getElementsByTagName("celda");
						// Recorremos todas las celdas
						for (int k = 0; k < listaCeldas.getLength(); k++) {
							Element celdaElement = (Element) listaCeldas.item(k);
							// Guardamos el valor que tiene cada celda
							int clave = Integer.parseInt(celdaElement.getTextContent());
							// Utilizamos el metodo setClave de la clase TarjetaClaves
							// j y k +1 porque se "inicializan" en 0
							tarjetaUsuario.setClave(j + 1, k + 1, clave);
						}
					}

				}
			}
		} catch (Exception e) {
			throw new ProveedorUsuariosException();
		}
		// Devuelve la tarjeta de cada usuario
		return tarjetaUsuario;
	}

	// Metodo para crear la lista de clietnes de cada Usuario
	private List<Cliente> setCliente(String archivo, String nombreUsuario) throws ProveedorUsuariosException {
		// Inicializamos la list de clientes
		List<Cliente> listaClientes = new ArrayList<>();
		try {
			// Accedemos al Documento
			DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
			Document documento = builder.parse(archivo);
			// Obtenemos los elementos de tipo cliente asociados al usuario
			NodeList listaClientesXML = documento.getElementsByTagName("cliente");
			// Para cada elememnto de cliente
			for (int i = 0; i < listaClientesXML.getLength(); i++) {
				Element clienteElement = (Element) listaClientesXML.item(i);
				// Guardamos el usuario de ese cliente
				String clienteUsuario = clienteElement.getAttribute("usuario");
				// Si el usuario que hemos guardado es igual al usuario con el que queremos
				// crear la lista de clientes
				if (clienteUsuario.equals(nombreUsuario)) {
					// Obtenemos los elementos hijos de cliente: nombre, apellidos, dni, edad
					// con el metodo privado obtenerValores
					String nombre = obtenerValorElemento(clienteElement, "nombre");
					String apellidos = obtenerValorElemento(clienteElement, "apellidos");
					String dni = obtenerValorElemento(clienteElement, "dni");
					int edad = Integer.parseInt(obtenerValorElemento(clienteElement, "edad"));
					// Creamos los clientes y los añadimos a la lista de cliente segun el usuario
					Cliente cliente = new Cliente(nombre, apellidos, dni, edad);
					listaClientes.add(cliente);
				}
			}
		} catch (Exception e) {
			throw new ProveedorUsuariosException();
		}
		// Devuelve la lista de clientes
		return listaClientes;
	}

	// Leemos los atributos de los elementos
	private String obtenerValorElemento(Element elementoPadre, String nombreElemento) {
		// Obtenemos el elemento hijo con el nombre especificado
		NodeList nodos = elementoPadre.getElementsByTagName(nombreElemento);
		if (nodos.getLength() > 0) {
			// Devuelve el contenido del elemento
			return nodos.item(0).getTextContent();
		} else {
			// Devuelve una cadena vacía si el elemento no se encuentra
			return "";
		}
	}
}