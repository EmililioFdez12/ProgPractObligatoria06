package prog.ud06.actividad611.coleccion;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Clase que representa a un Usuario Un usuario tiene un identificador único, un
 * nombre completo, una tarjeta de claves y una lista de clientes
 */
public class Usuario {

	// Nombre del usuario
	private String nombreUsuario;
	// Nombre completo
	private String nombreCompleto;
	// Tarjeta del usuario
	private TarjetaClaves tarjeta;
	// Lista de clientes del usuario
	private List<Cliente> clientes;

	/**
	 * 
	 * @param nombreUsuario  Nombre de usuario único del usuario. No puede ser null
	 *                       y debe tener entre 2 y 8 caracteres alfanuméricos,
	 *                       siendo el primero una letra.
	 * @param nombreCompleto Nombre y apellidos. No puede ser null ni vacío.
	 * @param tarjeta        Tarjeta de claves del usuario. No puede ser null
	 * @param clientes       Lista de clientes del usuario. No puede ser null aunque
	 *                       puede estar vacía.
	 *                       
	 * @throws IllegalArgumentException Si alguno de los parámetros no es correcto
	 */
	public Usuario(String nombreUsuario, String nombreCompleto, TarjetaClaves tarjeta, List<Cliente> clientes) {
		if (nombreUsuario == null || !validaNombreUsuario(nombreUsuario)) {
			throw new IllegalArgumentException();
		} else {
			this.nombreUsuario = nombreUsuario;
		}

		if (nombreCompleto == null || nombreCompleto.isEmpty()) {
			throw new IllegalArgumentException();
		} else {
			this.nombreCompleto = nombreCompleto;
		}

		if (tarjeta == null) {
			throw new IllegalArgumentException();
		} else {
			this.tarjeta = tarjeta;
		}

		if (clientes == null) {
			throw new IllegalArgumentException();
		} else {
			this.clientes = clientes;
		}

	}

	/**
	 * Devuelve el nombre de usuario del usuario
	 * @return Nombre de Usuario del usuario
	 */
	public String getNombreUsuario() {
		return nombreUsuario;
	}

	/**
	 * Devuelve el nombre completo del usuario
	 * @return Nombre completo del usuario
	 */
	public String getNombreCompleto() {
		return nombreCompleto;
	}

	/**
	 * Devuelve la tarjeta del usuario
	 * @return Tarjeta de coordenadas del usuario
	 */
	public TarjetaClaves getTarjeta() {
		return tarjeta;
	}

	/**
	 * Obtiene la lista de clientes del usuario
	 * @return Lista de clientes del usuario
	 */
	public List<Cliente> getClientes() {
		return clientes;
	}

	// Metodo que valdia el nombre de usuario con un expresion regular
	private boolean validaNombreUsuario(String nombre) {
		// Expresión regular para validar el nombre de usuario
		// Empieza por letra, y tiene que tener de 2 a 8 carácteres
		String regex = "^[a-zA-Z][a-zA-Z0-9]{1,7}$";
		Pattern pattern = Pattern.compile(regex);

		// Comprobar el nombre de usuario con la expresión regular
		Matcher matcher = pattern.matcher(nombre);

		// Verificamos si el nombre de usuario cumple con la expresión regular
		return matcher.matches();
	}
}
