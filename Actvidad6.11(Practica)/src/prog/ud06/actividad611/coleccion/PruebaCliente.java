package prog.ud06.actividad611.coleccion;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class PruebaCliente {

  @Test
  void testClienteOk() {
    // Caso: Crea un cliente con datos correctos
    // RE  : El cliente se crea correctamente y los datos son los introducidos
    String nombre = "Juan Antonio";
    String apellidos = "Aitor Tilla";
    String dni = "29301068B";
    int edad = 25;

    // No debe lanzar excepcion al crearse
    Cliente cliente = null;
    try {
      cliente = new Cliente(nombre, apellidos, dni, edad);
    } catch (IllegalArgumentException e) {
      fail("Se creó el objeto con datos incorrectos");
    }
    // Los datos deben coincidir
    assertEquals(nombre, cliente.getNombre());
    assertEquals(apellidos, cliente.getApellidos());
    assertEquals(dni, cliente.getDni());
    assertEquals(edad, cliente.getEdad());
  }

  @Test
  void testClienteNombreNull() {
    // Caso: Crea un cliente con nombre null
    // RE  : Se produce IllegalArgumentException
    String nombre = null;
    String apellidos = "Aitor Tilla";
    String dni = "29301068B";
    int edad = 25;

    assertThrows(IllegalArgumentException.class, () -> new Cliente(nombre, apellidos, dni, edad), "Se ha creado un cliente con nombre null");
  }

  @Test
  void testClienteNombreVacio() {
    // Caso: Crea un cliente con nombre vacio
    // RE  : Se produce IllegalArgumentException
    String nombre = "";
    String apellidos = "Aitor Tilla";
    String dni = "29301068B";
    int edad = 25;

    assertThrows(IllegalArgumentException.class, () -> new Cliente(nombre, apellidos, dni, edad), "Se ha creado un cliente con nombre vacio");
  }

  @Test
  void testClienteNombreBlancos() {
    // Caso: Crea un cliente con nombre blancos
    // RE  : Se produce IllegalArgumentException
    String nombre = "  ";
    String apellidos = "Aitor Tilla";
    String dni = "29301068B";
    int edad = 25;

    assertThrows(IllegalArgumentException.class, () -> new Cliente(nombre, apellidos, dni, edad), "Se ha creado un cliente con nombre con blancos");
  }

  @Test
  void testClienteApellidosNull() {
    // Caso: Crea un cliente con apellidos null
    // RE  : Se produce IllegalArgumentException
    String nombre = "Juan Antonio";
    String apellidos = null;
    String dni = "29301068B";
    int edad = 25;

    assertThrows(IllegalArgumentException.class, () -> new Cliente(nombre, apellidos, dni, edad), "Se ha creado un cliente con apellidos null");
  }

  @Test
  void testClienteApellidosVacio() {
    // Caso: Crea un cliente con apellidos vacío
    // RE  : Se produce IllegalArgumentException
    String nombre = "Juan Antonio";
    String apellidos = "";
    String dni = "29301068B";
    int edad = 25;

    assertThrows(IllegalArgumentException.class, () -> new Cliente(nombre, apellidos, dni, edad), "Se ha creado un cliente con apellidos vacio");
  }

  @Test
  void testClienteApellidosBlancos() {
    // Caso: Crea un cliente con apellidos blancos
    // RE  : Se produce IllegalArgumentException
    String nombre = "Juan Antonio";
    String apellidos = "  ";
    String dni = "29301068B";
    int edad = 25;

    assertThrows(IllegalArgumentException.class, () -> new Cliente(nombre, apellidos, dni, edad), "Se ha creado un cliente con nombre con blancos");
  }

  @Test
  void testClienteDniNull() {
    // Caso: Crea un cliente con DNI null
    // RE  : Se produce IllegalArgumentException
    String nombre = "Juan Antonio";
    String apellidos = "Aitor Tilla";
    String dni = null;
    int edad = 25;

    assertThrows(IllegalArgumentException.class, () -> new Cliente(nombre, apellidos, dni, edad), "Se ha creado un cliente con dni null");
  }

  @Test
  void testClienteDniVacio() {
    // Caso: Crea un cliente con DNI vacio
    // RE  : Se produce IllegalArgumentException
    String nombre = "Juan Antonio";
    String apellidos = "Aitor Tilla";
    String dni = "";
    int edad = 25;

    assertThrows(IllegalArgumentException.class, () -> new Cliente(nombre, apellidos, dni, edad), "Se ha creado un cliente con dni vacio");
  }

  @Test
  void testClienteDniCorto() {
    // Caso: Crea un cliente con DNI corto (7 digitos y una letra)
    // RE  : Se produce IllegalArgumentException
    String nombre = "Juan Antonio";
    String apellidos = "Aitor Tilla";
    String dni = "1234567A";
    int edad = 25;

    assertThrows(IllegalArgumentException.class, () -> new Cliente(nombre, apellidos, dni, edad), "Se ha creado un cliente con dni corto");
  }

  @Test
  void testClienteDniLargo() {
    // Caso: Crea un cliente con DNI largo (9 digitos y una letra)
    // RE  : Se produce IllegalArgumentException
    String nombre = "Juan Antonio";
    String apellidos = "Aitor Tilla";
    String dni = "123456789A";
    int edad = 25;

    assertThrows(IllegalArgumentException.class, () -> new Cliente(nombre, apellidos, dni, edad), "Se ha creado un cliente con dni corto");
  }

  @Test
  void testClienteDniLetraMinuscula() {
    // Caso: Crea un cliente con DNI incorrecto (letra minúscula)
    // RE  : Se produce IllegalArgumentException
    String nombre = "Juan Antonio";
    String apellidos = "Aitor Tilla";
    String dni = "29301068b";
    int edad = 25;

    assertThrows(IllegalArgumentException.class, () -> new Cliente(nombre, apellidos, dni, edad), "Se ha creado un cliente con dni con letra minúscula");
  }

  @Test
  void testClienteDniLetraIncorrecta() {
    // Caso: Crea un cliente con DNI incorrecto (letra no corresponde con número)
    // RE  : Se produce IllegalArgumentException
    String nombre = "Juan Antonio";
    String apellidos = "Aitor Tilla";
    String dni = "29301068X";
    int edad = 25;

    assertThrows(IllegalArgumentException.class, () -> new Cliente(nombre, apellidos, dni, edad), "Se ha creado un cliente con dni con letra no coincidente");
  }

}
