package prog.ud06.actividad611.main;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

import javax.swing.text.html.HTMLDocument.HTMLReader.ParagraphAction;

import prog.ud06.actividad611.coleccion.Cliente;
import prog.ud06.actividad611.coleccion.ProveedorUsuariosException;
import prog.ud06.actividad611.coleccion.TarjetaClaves;
import prog.ud06.actividad611.coleccion.Usuario;
import prog.ud06.actividad611.coleccion.Usuarios;
import prog.ud06.actividad611.proveedores.ProveedorUsuariosArchivoXML;

/**
 * Programa principal
 */
public class ColeccionApp {
	// Constantes
	// Archivo XML con los datos
	private static final String ARCHIVO = "biblioteca2.xml";
	// Opción salir del programa
	private static final int OPCION_SALIR = 0;
	// Opción Listar libros
	private static final int OPCION_LISTAR = 1;
	// Opción Buscar por titulo
	private static final int OPCION_BUSCAR_NOMBRE = 2;
	// Opcion Buscar por autor
	private static final int OPCION_BUSCAR_APELLIDOS = 3;
	// Opción Buscar por año de publicación
	private static final int OPCION_BUSCAR_DNI = 4;
	// Opción Buscar por año de publicación
	private static final int OPCION_BUSCAR_EDAD = 5;
	// Opciones minima y maxima (para comprobar los rangos)
	private static final int OPCION_MINIMA = OPCION_SALIR;
	private static final int OPCION_MAXIMA = OPCION_BUSCAR_EDAD;

	// Atributos
	// Contenedor de usuarios
	private Usuarios usuarios;
	// Scanner para leer desde teclado
	private Scanner sc;
	// Usuario autenticado
	Usuario usuario;

	/**
	 * Constructor del objeto<br>
	 * Recibe un contenedor de usuarios y crea el scanner para acceder al teclado
	 * 
	 * @param usuarios Contenedor de usuarios
	 */
	public ColeccionApp(Usuarios usuarios) {
		this.usuarios = usuarios;
		sc = new Scanner(System.in);
		usuario = null;
	}

	/**
	 * Main. Punto de entrada de la aplicación
	 * 
	 * @param args Argumentos de la línea de comandos
	 */
	public static void main(String[] args) {

		// Obtenemos los usuarios
		try {
			Usuarios usuarios = new ProveedorUsuariosArchivoXML(ARCHIVO).obtieneUsuarios();
			// Creamos un objeto de la clase
			ColeccionApp app = new ColeccionApp(usuarios);
			// Y lanzamos el interfaz
			app.run();
		} catch (ProveedorUsuariosException e) {
			// Si no se puede obtener la información de usuarios y libros terminamos
			System.out.println("Error. No se puede acceder al archivo XML. Terminando");
		}
	}

	/**
	 * Método principal del interfaz
	 */
	private void run() {
		// Lo primero es autenticar al usuario
		usuario = autenticarUsuario();
		// Si se pudo hacer
		if (usuario != null) {
			// Muestra el menú repetidamente hasta que se elija la opción Salir
			int opcionElegida = OPCION_SALIR;
			do {
				// Muestra el menú y obtiene una elección
				opcionElegida = mostrarMenu();
				// Según la opción elegida
				switch (opcionElegida) {
				case OPCION_LISTAR:
					comandoListarClientes();
					break;
				case OPCION_BUSCAR_NOMBRE:
					comandoBuscarNombre();
					break;
				case OPCION_BUSCAR_APELLIDOS:
					comandoBuscarApellidos();
					break;
				case OPCION_BUSCAR_DNI:
					comandoBuscarDni();
					break;
				case OPCION_BUSCAR_EDAD:
					comandoBuscarEdad();
					break;
				case OPCION_SALIR:
					break;
				default:
					// No se debe llegar aqui
					System.out.println("Error. Opción incorrecta.");
				}
			} while (opcionElegida != OPCION_SALIR);
		} else {
			// No se pudo autenticar al usuario. Termina
			System.out.println("Error de autenticación. Terminando programa");
		}
	}

	/**
	 * Autentifica al usuario
	 * 
	 * @return Usuario si la autenticación tuvo éxito. null si no lo tuvo
	 */
	private Usuario autenticarUsuario() {
		// Solicitamos el nombre del usuario
		System.out.println("Autenticación");
		System.out.print("Introduzca el nombre de usuario: ");
		String nombreUsuario = sc.nextLine();
		// Accede al usuario en el contenedor
		Usuario usuario = usuarios.getUsuarioPorNombreUsuario(nombreUsuario);
		// Si no es null
		if (usuario != null) {
			// Accedemos a la tarjeta
			TarjetaClaves tarjeta = usuario.getTarjeta();
			// Obtenemos el número de filas y columnas de la tarjeta
			int filas = tarjeta.getFilas();
			int columnas = tarjeta.getColumnas();

			// Obtenemos un valor aleatorio de fila y columna
			Random random = new Random();
			int fila = random.nextInt(1, filas + 1);
			int columna = random.nextInt(1, columnas + 1);

			// Lo mostramos al usuario
			System.out.print("Introduzca la clave en las coordenadas (" + fila + ", " + columna + "): ");
			int clave = 1000;
			try {
				clave = Integer.parseInt(sc.nextLine());
			} catch (NumberFormatException e) {
				// Autenticación incorrecta. terminamos
				System.out.println("Autenticación incorrecta");
				return null;
			}
			// Si el usuario autentica correctamente
			if (tarjeta.validarClave(fila, columna, clave)) {
				// Mostramos un mensaje y devolvemos el usuario
				System.out.println("Autenticación correcta.");
				return usuario;
			} else {
				// Autenticación incorrecta
				System.out.println("Autenticación incorrecta");
				return null;
			}
		} else {
			// Usuario no encontrado
			System.out.println("Autenticación incorrecta");
			return null;
		}
	}

	/**
	 * Muestra el menú y elige la opción
	 * 
	 * @return Opción elegida. Se comprueba que es correcta y está en rango
	 */
	private int mostrarMenu() {
		// Inicializamos la opción elegida a un valor invalido
		int opcion = OPCION_MINIMA - 1;
		// Mientras no se elija una opción correcta
		for (;;) {
			// Mostramos el menu
			System.out.println();
			System.out.println("MENU PRINCIPAL");
			System.out.println("--------------");
			System.out.println("1. Listar clientes");
			System.out.println("2. Buscar clientes por nombre de pila");
			System.out.println("3. Buscar clientes por apellidos");
			System.out.println("4. Buscar clientes por DNI");
			System.out.println("5. Buscar clientes por edad");
			System.out.println("0. Salir del programa");
			System.out.print("Elija una opción (" + OPCION_MINIMA + "-" + OPCION_MAXIMA + "): ");
			try {
				opcion = Integer.parseInt(sc.nextLine());
				// Si la opción está en rango se devuelve. Si no se muestra error y se da otra
				// vuelta
				if (opcion >= OPCION_MINIMA && opcion <= OPCION_MAXIMA) {
					return opcion;
				} else {
					System.out.println("Opción elegida incorrecta. Debe introducir un número" + " comprendido entre "
							+ OPCION_MINIMA + " y " + OPCION_MAXIMA);
				}
			} catch (NumberFormatException e) {
				System.out.println("Opción elegida incorrecta. Debe introducir un número" + " comprendido entre "
						+ OPCION_MINIMA + " y " + OPCION_MAXIMA);
			}
		}
	}

	/**
	 * Lista los clientes del usuario
	 */
	private void comandoListarClientes() {
		// Inicializamos lista
		List<Cliente> listadoClientes = usuario.getClientes();
		if (usuario != null) {
			listadoClientes = usuario.getClientes();
		}
		// Si el listado no esta vacia
		if (!listadoClientes.isEmpty()) {
			System.out.println("Los clientes del usuario son: ");
			// Recogemos todos los datos de cada cliente
			for (Cliente cliente : listadoClientes) {
				// Imprimimos por pantalla
				System.out.println(cliente.getNombre() + ", " + cliente.getApellidos() + ". DNI: " + cliente.getDni()
						+ ". Edad " + cliente.getEdad());
			}
		} else {
			System.out.println("Este usuario no tiene clientes");
		}
	}

	/**
	 * Busca los clientes del usuario cuyo nombre de pila contiene un texto
	 * determinado
	 */
	private void comandoBuscarNombre() {
		// Obtenemos la lista de clientes
		List<Cliente> listadoClientes = usuario.getClientes();
		System.out.print("¿Buscar por nombre completo o parte del nombre? (c = nombre completo, p = parte del nombre): ");
		String tipoBusqueda = sc.nextLine();
		// Busqueda completa
		if (tipoBusqueda.equalsIgnoreCase("c")) {
			boolean encontrado = false;
			String clienteEncontrado = "";

			System.out.print("Introduzca el texto a buscar en el nombre del cliente: ");
			String nombreABuscarCompleto = sc.nextLine();
			// Recorremos la lista de clietnes
			for (Cliente cliente : listadoClientes) {
				String nombreCliente = cliente.getNombre();
				if (nombreCliente.equalsIgnoreCase(nombreABuscarCompleto)) {
					encontrado = true;
					clienteEncontrado = (cliente.getNombre() + ", " + cliente.getApellidos() + ". DNI: " + cliente.getDni()
							+ ". Edad " + cliente.getEdad());
				}
			}
			// Imprimimos por pantalla
			if (encontrado) {
				System.out.println("Los clientes del usuario que contienen " + "\"" + nombreABuscarCompleto + "\""
						+ " en el nombre de pila son:");
				System.out.println(clienteEncontrado);
			}

			if (!encontrado) {
				System.out.println("No se ha encontrado ningún cliente con ese nombre");
			}
			// Busqueda parcial
		} else if (tipoBusqueda.equalsIgnoreCase("p")) {
			boolean encontrado = false;
			String clienteEncontrado = null;

			System.out.print("Introduzca el texto a buscar en el nombre del cliente: ");
			String nombreABuscarCompleto = sc.nextLine();

			for (Cliente cliente : listadoClientes) {
				String nombreCliente = cliente.getNombre();
				if (nombreCliente.contains(nombreABuscarCompleto)) {
					encontrado = true;
					clienteEncontrado = (cliente.getNombre() + ", " + cliente.getApellidos() + ". DNI: " + cliente.getDni()
							+ ". Edad " + cliente.getEdad());
				}
			}

			if (nombreABuscarCompleto.isBlank()) {
				encontrado = false;
			}

			if (encontrado) {
				System.out.println("Los clientes del usuario que contienen " + "\"" + nombreABuscarCompleto + "\""
						+ " en el nombre de pila son:");

				System.out.println(clienteEncontrado);
			}

			if (!encontrado) {
				System.out.println("No se ha encontrado ningún cliente con ese nombre");
			}

		} else {
			System.out.println("La opción elegida no es válida. Debe ser una c, p");
		}

	}

	/**
	 * Busca los clientes del usuario cuyos apellidos contienen un texto determinado
	 */
	private void comandoBuscarApellidos() {
		// Obtenemos la lista de clientes
		List<Cliente> listadoClientes = usuario.getClientes();
		System.out.print("¿Buscar por apellido completo o parte del apellido? (c = apellido completo, p = parte del apellido): ");
		String tipoBusqueda = sc.nextLine();
		// Busqueda completa
		if (tipoBusqueda.equalsIgnoreCase("c")) {
			boolean encontrado = false;
			String clienteEncontrado = "";

			System.out.print("Introduzca el texto a buscar en el apellido del cliente: ");
			String apellidoABuscarCompleto = sc.nextLine();
			// Recorremos la lista de clientes
			for (Cliente cliente : listadoClientes) {
				// Obtenemos los apellidos
				String apellidoCliente = cliente.getApellidos();
				if (apellidoCliente.equalsIgnoreCase(apellidoABuscarCompleto)) {
					encontrado = true;
					clienteEncontrado = (cliente.getNombre() + ", " + cliente.getApellidos() + ". DNI: " + cliente.getDni()
							+ ". Edad " + cliente.getEdad());
				}
			}
			// Imprimimos por pantalla
			if (encontrado) {
				System.out.println("Los clientes del usuario que contienen " + "\"" + apellidoABuscarCompleto + "\"" + " en el apellido son:");
				System.out.println(clienteEncontrado);
			}

			if (!encontrado) {
				System.out.println("No se ha encontrado ningún cliente con ese apellido");
			}
			// Busqueda parcial
		} else if (tipoBusqueda.equalsIgnoreCase("p")) {
			boolean encontrado = false;
			String clienteEncontrado = null;

			System.out.print("Introduzca el texto a buscar en el apellido del cliente: ");
			String apellidoABuscarCompleto = sc.nextLine();
			// Recorremos la lista de clientes
			for (Cliente cliente : listadoClientes) {
				// Obtenemos los apellidos
				String apellidoCliente = cliente.getApellidos();
				if (apellidoCliente.contains(apellidoABuscarCompleto)) {
					encontrado = true;
					clienteEncontrado = (cliente.getNombre() + ", " + cliente.getApellidos() + ". DNI: " + cliente.getDni()
							+ ". Edad " + cliente.getEdad());
				}
			}

			if (apellidoABuscarCompleto.isBlank()) {
				encontrado = false;
			}

			if (encontrado) {
				System.out.println("Los clientes del usuario que contienen " + "\"" + apellidoABuscarCompleto + "\"" + " en el apellido:");
				System.out.println(clienteEncontrado);
			}

			if (!encontrado) {
				System.out.println("No se ha encontrado ningún cliente con ese apellido");
			}

		} else {
			System.out.println("La opción elegida no es válida. Debe ser una c, p");
		}

	}

	/**
	 * Busca los clientes del usuario cuyos DNI es el proporcionado
	 */
	private void comandoBuscarDni() {
		// Obtenemos la lista de clientes
		List<Cliente> listadoClientes = usuario.getClientes();
		boolean encontrado = false;

		System.out.println("BUSCAR CLIENTE POR DNI");
		System.out.println("----------------------");
		System.out.print("Introduzca el DNI a buscar (DNI completo):");
		String dniABuscar = sc.nextLine();

		if (dniABuscar != null && !dniABuscar.isEmpty() && !dniABuscar.isBlank()) {
			// Recorremos la lista de clientes
			for (Cliente cliente : listadoClientes) {
				// Si la busqueda coincide con el dni del cliente
				if (dniABuscar.equals(cliente.getDni())) {
					encontrado = true;
					// Imprimimos por pantalla
					System.out.println("El cliente con el DNI " + "\"" + dniABuscar + "\"" + " es");
					System.out.println(cliente.getNombre() + ", " + cliente.getApellidos() + ". DNI: " + cliente.getDni()
							+ ". Edad" + cliente.getEdad());
				}
			}
			if (!encontrado) {
				System.out.println("No se encontró cliente con el DNI especificado");
			}
		}
	}

	/**
	 * Busca los clientes del usuario por su edad
	 */
	private void comandoBuscarEdad() {
		// Edad minima del rango
		int edadMinima;
		// Edad máxima del rango
		int edadMaxima;
		// Obtenemos la lista de clientes
		List<Cliente> listaClientes = usuario.getClientes();
		System.out.println("BUSCAR CLIENTES POR AÑO");
		System.out.println("-----------------------");

		try {

			System.out.print("¿Buscar clientes cuya edad sera mayor o igual a? (vacío para cualquiera):");
			String edadMinimaStr = sc.nextLine();
			System.out.print("¿Buscar clientes cuya edad sera menor o igual a? (vacío para cualquiera):");
			String edadMaximaStr = sc.nextLine();
			// Comprobamos que la edad minima no esta vacia ni tenga espacios
			if (edadMinimaStr.isEmpty() || edadMinimaStr.isBlank()) {
				edadMinima = 0;
			} else {
				// Pasamos el valor a entero
				edadMinima = Integer.parseInt(edadMinimaStr);
			}
			// Comprobamos que la edad minima no esta vacia ni tenga espacios
			if (edadMaximaStr.isEmpty() || edadMaximaStr.isBlank()) {
				edadMaxima = 150;
			} else {
				// Pasamos el valor a entero
				edadMaxima = Integer.parseInt(edadMaximaStr);
			}
			// La edad no puede ser menor de 0
			if (edadMinima < 0 || edadMaxima < 0) {
				System.out.println("La edad debe ser mayor o igual que 0");
			}
			// La edad maxima no puede ser menor que la edad minima
			if (edadMinima > edadMaxima || edadMinima == edadMaxima) {
				System.out.println("Inrtoduce un rango correcto por favor.");
			}
			// Recorremos la lista de clientes
			for (Cliente cliente : listaClientes) {
				// Si la edad del clietne esta dentro del rango
				if (cliente.getEdad() >= edadMinima && cliente.getEdad() <= edadMaxima) {
					System.out.println("Los clientes encontrados son: ");
					System.out.println(cliente.getNombre() + ", " + cliente.getApellidos() + ". DNI: " + cliente.getDni()
							+ ". Edad " + cliente.getEdad());
				}
			}
			// Comprobamos si se ponen numeros
		} catch (NumberFormatException e) {
			System.out.println("Debes introducir números");
		}
	}
}
