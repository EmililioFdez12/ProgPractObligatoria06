package prog.ud06.actividad611.coleccion;

/**
 * Interface que implementan las clases que proveen (obtienen) la información de
 * los usuarios desde una fuente externa (archivo, base de datos, etc.)
 */
public interface ProveedorUsuarios {

	/**
	 * 
	 * @return Contenedor con los usuarios si todo fue OK. Vacío o imcompleto en
	 *         caso de error
	 * @throws ProveedorUsuariosException Si hay algún error obteniendo la
	 *                                    información
	 */
	public Usuarios obtieneUsuarios() throws ProveedorUsuariosException;

}
