package prog.ud06.actividad611.coleccion.diccionario;

import java.util.HashMap;
import java.util.Map;

public class Diccionario<V> {

	// Mapa donde se almacenan los valores
	Map<String, V> diccionario;

	/**
	 * Constructor.Crea un diccionario vacío
	 */
	public Diccionario() {
		this.diccionario = new HashMap<>();
	}

	
	/**
	 * Añade una nueva entrada al diccionario
	 * 
	 * @param nombre Nombre de la entrada a añadir. No puede ser null ni coincidir
	 *               con el nombre de una entrada ya existente en el diccionario
	 * @param valor  Valor a almacenar en la entrada. No puede ser null
	 * 
	 * @throws DiccionarioException     Si ya existe previamente una entrada con el
	 *                                  nombre proporcionado en el diccionario
	 * 
	 * @throws IllegalArgumentException Si el nombre o el valor son null
	 */
	public void add(String nombre, V valor) {
		if (valor == null) {
			throw new IllegalArgumentException();
		}

		if (nombre == null) {
			throw new IllegalArgumentException();
		}

		if (diccionario.containsKey(nombre)) {
			throw new DiccionarioException();
		}
		// Añadimos el valor al mapa
		diccionario.put(nombre, valor);
	}

	/**
	 * Comprueba si existe una entrada en el diccionario con el nombre dado
	 * 
	 * @param nombre Nombre de la entrada a comprobar
	 * @return true si existe en el diccionario una entrada con el nombre
	 *         proporcionado. false si no existe
	 */
	public boolean contieneNombre(String nombre) {
		return diccionario.containsKey(nombre);
	}

	/**
	 * Obtiene el valor almacenado en una entrada del diccionario a partir de su nombre
	 * 
	 * @param nombre Nombre de la entrada a recuperar.
	 * @return Valor almacenado en la entrada con el nombre proporcionado. Si no
	 *         existe una entrada con dicho nombre devuelve null
	 */
	public V getEntrada(String nombre) {
		return diccionario.get(nombre);
	}

}