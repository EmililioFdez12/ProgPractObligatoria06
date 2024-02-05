package prog.ud06.actividad611.coleccion;

public class Cliente implements Comparable<Cliente> {

  private String nombre;
  private String apellidos;
  private String dni;
  private int edad;

  /**
   * 
   * @param nombre    Nombre de pila de la persona. No puede ser null o vacío o
   *                  contener sólo espacios
   * @param apellidos Apellidos de la persona. No puede ser null o vacío o
   *                  contener sólo espacios
   * @param dni       DNI de la persona. No puede ser null y debe constar de 8
   *                  números y una letra. La letra debe ser mayúscula y válida
   *                  según las normas del DNI
   * @param edad      Edad de la persona. Debe ser 0 ó superior
   */

  public Cliente(String nombre, String apellidos, String dni, int edad) {

    // Validamos el nombre
    if (nombre == null || nombre.isBlank() || nombre.isEmpty()) {
      throw new IllegalArgumentException();
    } else {
      this.nombre = nombre;
    }

    // Validamos el apellido
    if (apellidos == null || apellidos.isBlank() || apellidos.isEmpty()) {
      throw new IllegalArgumentException();
    } else {
      this.apellidos = apellidos;
    }

    // Validamos la edad
    if (edad < 1) {
      throw new IllegalArgumentException();
    } else {
      this.edad = edad;
    }

    if (validarDni(dni)) {
      this.dni = dni;
    } else {
      throw new IllegalArgumentException();
    }
    

  }

  public String getNombre() {
    return nombre;
  }

  public String getApellidos() {
    return apellidos;
  }

  public String getDni() {
    return dni;
  }

  public int getEdad() {
    return edad;
  }
  
  // Metodo para comprobar si el dni es valido
  private boolean validarDni(String dni) {
    boolean valida = false;
    
    // Si es null o no tiene 9 carácteres devuelve false
    if (dni == null || dni.length() != 9) {
        return false;
    }

    // Saco la cadena de numeros
    String cadenaNumerosDni = dni.substring(0, 8);
    // Saco la letra
    char letraDni = dni.charAt(8);

    try {
        // Paso la cadena de numeros a Integer
        int numerosDni = Integer.parseInt(cadenaNumerosDni);

        // Segun el resto que de, pertenece a una letra del array, (Estan en orden)
        int resto = numerosDni % 23;
        // Letras posibles en cada posicion segun su resto
        // Es decir si el resto es 1 la letra deberia ser T
        char[] letrasPosibles = {'T', 'R', 'W', 'A', 'G', 'M', 'Y', 'F', 'P', 'D', 'X', 'B', 'N', 'J', 'Z', 'S', 'Q', 'V',
                'H', 'L', 'C', 'K', 'E'};

        if (letrasPosibles[resto] == letraDni) {
            valida = true;
        }
    } catch (NumberFormatException e) {
        // Si los 8 primeros caracteres no son números
        return false;
    }
    return valida;
}


  @Override
  public int compareTo(Cliente o) {
    return 0;
  }
}