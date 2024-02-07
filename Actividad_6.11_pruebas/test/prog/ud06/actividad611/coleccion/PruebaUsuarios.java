package prog.ud06.actividad611.coleccion;

import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;
import org.junit.jupiter.api.Test;

class PruebaUsuarios {

  @Test
  void testUsuariosAddOne() {
    // Caso: Crear un Usuarios vacio y añadir un usuario cualquiera
    //       A continuación busca el usuario por su nombre y comprueba que es correcto
    // RE  : Se añade el usuario OK sin excepciones y luego se puede recuperar
    //        por su nombre de usuario

    // Usuarios
    Usuarios usuarios = new Usuarios();
    // Usuario
    Usuario usuario = new Usuario("usuario1", "Usuario Unico", new TarjetaClaves(2, 2), new ArrayList<>());
    
    // Se añade
    assertDoesNotThrow(() -> usuarios.addUsuario(usuario));
    
    // Se puede acceder por el nombre
    Usuario buscado = usuarios.getUsuarioPorNombreUsuario("usuario1");
    // No es null
    assertNotNull(buscado);
    // Nombre y apellidos coinciden
    assertEquals("usuario1", buscado.getNombreUsuario());
    assertEquals("Usuario Unico", buscado.getNombreCompleto());
    assertTrue(buscado.getTarjeta().validarClave(1, 1, 0));
  }

  @Test
  void testUsuariosAddTwo() {
    // Caso: Crear un Usuarios vacio y añadir dos usuario cualquiera con nombres
    //       de usuario distintos
    //       A continuación busca el usuario por su nombre y comprueba que es correcto
    // RE  : Se añaden los usuarios OK sin excepciones y luego se pueden recuperar
    //       por sus nombres de usuario

    // Usuarios
    Usuarios usuarios = new Usuarios();
    // Usuario
    Usuario usuario = new Usuario("usuario1", "Usuario Unico", new TarjetaClaves(2, 2), new ArrayList<>());
    // Otro Usuario
    Usuario usuario2 = new Usuario("usuario2", "Usuario Doble", new TarjetaClaves(3, 3), new ArrayList<>());
    
    // Se añaden
    assertDoesNotThrow(() -> usuarios.addUsuario(usuario));
    assertDoesNotThrow(() -> usuarios.addUsuario(usuario2));
    
    // Se puede acceder por el nombre
    Usuario buscado = usuarios.getUsuarioPorNombreUsuario("usuario1");
    // No es null
    assertNotNull(buscado);
    // Nombre y apellidos coinciden
    assertEquals("usuario1", buscado.getNombreUsuario());
    assertEquals("Usuario Unico", buscado.getNombreCompleto());
    assertTrue(buscado.getTarjeta().validarClave(1, 1, 0));

    // También al otro
    buscado = usuarios.getUsuarioPorNombreUsuario("usuario2");
    // No es null
    assertNotNull(buscado);
    // Nombre y apellidos coinciden
    assertEquals("usuario2", buscado.getNombreUsuario());
    assertEquals("Usuario Doble", buscado.getNombreCompleto());
    assertTrue(buscado.getTarjeta().validarClave(2, 2, 0));
  }

  @Test
  void testUsuariosAddOneBuscaOtro() {
    // Caso: Crear un Usuarios vacio y añadir un usuario cualquiera
    //       A continuación busca otro usuario y debe devolver null
    // RE  : Se añade el usuario OK sin excepciones y luego devuelv
    //       null al obtener por otro nombre de usuario

    // Usuarios
    Usuarios usuarios = new Usuarios();
    // Usuario
    Usuario usuario = new Usuario("usuario1", "Usuario Unico", new TarjetaClaves(2, 2), new ArrayList<>());
    
    // Se añade
    assertDoesNotThrow(() -> usuarios.addUsuario(usuario));
    
    // si buscamos usuario con otro nombre devuelve null
    assertNull(usuarios.getUsuarioPorNombreUsuario("usuario2"));
  }

  @Test
  void testUsuariosAddTwoSegundoIgual() {
    // Caso: Crear un Usuarios vacio y añadir dos usuarios cualquiera con nombres
    //       de usuario iguales
    // RE  : Se añade el primer usuario OK sin excepciones y al añadir el segundo lanza
    //       UsuariosException

    // Usuarios
    Usuarios usuarios = new Usuarios();
    // Usuario
    Usuario usuario = new Usuario("usuario1", "Usuario Unico", new TarjetaClaves(2, 2), new ArrayList<>());
    // Otro Usuario
    Usuario usuario2 = new Usuario("usuario1", "Usuario Doble", new TarjetaClaves(3, 3), new ArrayList<>());
    
    // Se añade el primero
    assertDoesNotThrow(() -> usuarios.addUsuario(usuario));
    // Este debe dar excepcion
    assertThrows(UsuariosException.class, () -> usuarios.addUsuario(usuario2));
  }

}
