package prog.ud06.actividad611.coleccion;

/**
 * Tarjeta de clave de un usuario Una tarjeta de claves contiene una tabla con
 * claves numéricas enteras
 */
public class TarjetaClaves {

	private int filas; // numero filas
	private int columnas; // numero columnas
	private int[][] claves; // tabla

	/**
	 * Crea una tarjeta de claves con las filas y columnas indicadas Inicialmente
	 * las claves son todas 000
	 * 
	 * @param filas    Filas de la tabla
	 * @param columnas Columnas de la tabla
	 * 
	 * @throws IllegalArgumentException Si filas o columnas o ambos no son mayores
	 *                                  que cero
	 */
	public TarjetaClaves(int filas, int columnas) {
		if (filas < 1 || columnas < 1) {
			throw new IllegalArgumentException();
		} else {
			this.filas = filas;
			this.columnas = columnas;
		}
		// tabla creada por el numero de filas y de columnas
		this.claves = new int[filas][columnas];

	}

	/**
	 * Obtiene el número de filas que tiene la tabla de claves de la tarjeta
	 * 
	 * @return Número de filas de la tabla claves
	 */
	public int getFilas() {
		return filas;
	}

	/**
	 * Obtiene el número de columnas que tiene la tabla de claves de la tarjeta
	 * 
	 * @return Número de columnas de la tabla de claves
	 */
	public int getColumnas() {
		return columnas;
	}

	/**
	 * Modifica el valor de una clave
	 * 
	 * @param fila    Fila donde está la clave. Debe valer entre 1 y el total de
	 *                filas de la tarjeta
	 * @param columna Columna donde está la clave. Debe valer entre 1 y el total de
	 *                columnas de la tarjeta
	 * @param clave   Nuevo valor de la clave. Debe estar comprendido entre 0 y 999,
	 *                ambos incluidos
	 * @throws IllegalArgumentException Si fila, columna o clave son incorrectos
	 */
	public void setClave(int fila, int columna, int clave) {
		if (clave <= 0 || clave > 999 || fila <= 0 || columna <= 0 || fila > claves.length || columna > claves[0].length) {
			throw new IllegalArgumentException();
		} else {
			claves[fila - 1][columna - 1] = clave;
		}
	}

	/**
	 * Valida que la clave sea correcta
	 * 
	 * @param fila    Número de fila de la clave. Debe valer desde 1 hasta el número
	 *                de filas de la tarjeta
	 * @param columna Número de columna de la clave. Debe valer desde 1 hasta el
	 *                número de columnas de la tarjeta
	 * @param clave   Valor de clave a validar
	 * @return true si la clave es válida, false si no lo es
	 * 
	 * @throws IllegalArgumentException Si fila, columna o clave son incorrectos
	 */
	public boolean validarClave(int fila, int columna, int clave) {
		if (clave <= 0 || clave > 999 || fila <= 0 || columna <= 0 || fila > claves.length || columna > claves[0].length) {
			throw new IllegalArgumentException();
		}
		return claves[fila - 1][columna - 1] == clave;
	}

}
