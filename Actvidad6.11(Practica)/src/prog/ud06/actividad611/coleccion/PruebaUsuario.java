package prog.ud06.actividad611.coleccion;

import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;

class PruebaUsuario {

  @Test
  void testUsuarioOk() {
    // Caso: Crea un usuario con todos los datos correctos (usuario1,
    //       Pako Makako, nueva tarjeta de claves y lista vacía de clientes)
    // RE  : El usuario se crea correctamente y los datos son los proporcionados
    String username = "usuario1";
    String nombre = "Pako Makako";
    TarjetaClaves tarjeta = new TarjetaClaves(2, 3);
    List<Cliente> clientes = new ArrayList<>();
    
    // Intenta crear el Usuario y no debe producir excepción
    Usuario usuario = null;
    try {
      usuario = new Usuario(username, nombre, tarjeta, clientes);
    } catch (Exception e) {
      fail("Error creando un usuario con datos correctos");
    }
    
    // Comprueba los datos del usuario
    assertEquals(username, usuario.getNombreUsuario());
    assertEquals(nombre, usuario.getNombreCompleto());
    TarjetaClaves tarjeta2 = usuario.getTarjeta();
    assertNotNull(tarjeta2);
    assertEquals(2, tarjeta2.getFilas());
    assertEquals(3, tarjeta2.getColumnas());
    List<Cliente> clientes2 = usuario.getClientes();
    assertNotNull(clientes2);
    assertEquals(0, clientes2.size());
  }

  @Test
  void testUsuarioNombreUsuarioNull() {
    // Caso: Crea un usuario con todos los datos correctos (usuario1,
    //       Pako Makako, nueva tarjeta de claves y lista vacía de clientes) excepto
    //       nombre de usuario que es null
    // RE  : Se produce IllegalArgumentException
    String username = null;
    String nombre = "Pako Makako";
    TarjetaClaves tarjeta = new TarjetaClaves(2, 3);
    List<Cliente> clientes = new ArrayList<>();
    
    // Intenta crear al usuario y produce excepcion
    assertThrows(IllegalArgumentException.class, () -> new Usuario(username, nombre, tarjeta, clientes));
  }

  @Test
  void testUsuarioNombreUsuarioVacio() {
    // Caso: Crea un usuario con todos los datos correctos (usuario1,
    //       Pako Makako, nueva tarjeta de claves y lista vacía de clientes) excepto
    //       nombre de usuario que es vacio
    // RE  : Se produce IllegalArgumentException
    String username = "";
    String nombre = "Pako Makako";
    TarjetaClaves tarjeta = new TarjetaClaves(2, 3);
    List<Cliente> clientes = new ArrayList<>();
    
    // Intenta crear al usuario y produce excepcion
    assertThrows(IllegalArgumentException.class, () -> new Usuario(username, nombre, tarjeta, clientes));
  }

  @Test
  void testUsuarioNombreUsuarioLargo() {
    // Caso: Crea un usuario con todos los datos correctos (usuario1,
    //       Pako Makako, nueva tarjeta de claves y lista vacía de clientes) excepto
    //       nombre de usuario que tiene una longitud mayor de 8 caracteres (9).
    //       Los carácteres son correctos
    // RE  : Se produce IllegalArgumentException
    String username = "abcdefghi";
    String nombre = "Pako Makako";
    TarjetaClaves tarjeta = new TarjetaClaves(2, 3);
    List<Cliente> clientes = new ArrayList<>();
    
    // Intenta crear al usuario y produce excepcion
    assertThrows(IllegalArgumentException.class, () -> new Usuario(username, nombre, tarjeta, clientes));
  }

  @Test
  void testUsuarioNombreUsuarioIncorrecto() {
    // Caso: Crea un usuario con todos los datos correctos (usuario1,
    //       Pako Makako, nueva tarjeta de claves y lista vacía de clientes) excepto
    //       nombre de usuario que contiene caracteres incorrectos (.)
    // RE  : Se produce IllegalArgumentException
    String username = "bad.user";
    String nombre = "Pako Makako";
    TarjetaClaves tarjeta = new TarjetaClaves(2, 3);
    List<Cliente> clientes = new ArrayList<>();
    
    // Intenta crear al usuario y produce excepcion
    assertThrows(IllegalArgumentException.class, () -> new Usuario(username, nombre, tarjeta, clientes));
  }

  @Test
  void testUsuarioNombreUsuarioIncorrecto2() {
    // Caso: Crea un usuario con todos los datos correctos (usuario1,
    //       Pako Makako, nueva tarjeta de claves y lista vacía de clientes) excepto
    //       nombre de usuario que contiene caracteres correctos pero comienza
    //       opr número
    // RE  : Se produce IllegalArgumentException
    String username = "1usuario";
    String nombre = "Pako Makako";
    TarjetaClaves tarjeta = new TarjetaClaves(2, 3);
    List<Cliente> clientes = new ArrayList<>();
    
    // Intenta crear al usuario y produce excepcion
    assertThrows(IllegalArgumentException.class, () -> new Usuario(username, nombre, tarjeta, clientes));
  }

  @Test
  void testUsuarioNombreCompletoNull() {
    // Caso: Crea un usuario con todos los datos correctos (usuario1,
    //       Pako Makako, nueva tarjeta de claves y lista vacía de clientes) excepto
    //       nombre completo que es null
    // RE  : Se produce IllegalArgumentException
    String username = "usuario1";
    String nombre = null;
    TarjetaClaves tarjeta = new TarjetaClaves(2, 3);
    List<Cliente> clientes = new ArrayList<>();
    
    // Intenta crear al usuario y produce excepcion
    assertThrows(IllegalArgumentException.class, () -> new Usuario(username, nombre, tarjeta, clientes));
  }

  @Test
  void testUsuarioNombreCompletoVacio() {
    // Caso: Crea un usuario con todos los datos correctos (usuario1,
    //       Pako Makako, nueva tarjeta de claves y lista vacía de clientes) excepto
    //       nombre completo que es vacio
    // RE  : Se produce IllegalArgumentException
    String username = "usuario1";
    String nombre = "";
    TarjetaClaves tarjeta = new TarjetaClaves(2, 3);
    List<Cliente> clientes = new ArrayList<>();
    
    // Intenta crear al usuario y produce excepcion
    assertThrows(IllegalArgumentException.class, () -> new Usuario(username, nombre, tarjeta, clientes));
  }

  @Test
  void testUsuarioTarjetaClavesNull() {
    // Caso: Crea un usuario con todos los datos correctos (usuario1,
    //       Pako Makako, nueva tarjeta de claves y lista vacía de clientes) excepto
    //       tarjeta de claves que es null
    // RE  : Se produce IllegalArgumentException
    String username = "usuario1";
    String nombre = "Pako Makako";
    TarjetaClaves tarjeta = null;
    List<Cliente> clientes = new ArrayList<>();
    
    // Intenta crear al usuario y produce excepcion
    assertThrows(IllegalArgumentException.class, () -> new Usuario(username, nombre, tarjeta, clientes));
  }

  @Test
  void testUsuarioListaClientesNull() {
    // Caso: Crea un usuario con todos los datos correctos (usuario1,
    //       Pako Makako, nueva tarjeta de claves y lista vacía de clientes) excepto
    //       lista de clientes que es null
    // RE  : Se produce IllegalArgumentException
    String username = "usuario1";
    String nombre = "Pako Makako";
    TarjetaClaves tarjeta = new TarjetaClaves(2, 3);
    List<Cliente> clientes = null;
    
    // Intenta crear al usuario y produce excepcion
    assertThrows(IllegalArgumentException.class, () -> new Usuario(username, nombre, tarjeta, clientes));
  }

}
