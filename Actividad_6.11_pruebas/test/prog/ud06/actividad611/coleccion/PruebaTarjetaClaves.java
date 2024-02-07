package prog.ud06.actividad611.coleccion;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class PruebaTarjetaClaves {

  @Test
  void testTarjetaClavesOk() {
    // Caso: Crea una tarjeta de claves de 2 filas por 3 columnas
    // RE  : Se crea la tarjeta correctamente. Las claves son todas 00
    TarjetaClaves tarjeta = null;
    try {
       tarjeta = new TarjetaClaves(2, 3);
    } catch (IllegalArgumentException e) {
      fail("Se ha producido excepción al crear la tarjeta pero no debería haber ocurrido");
    }
    
    // Chequea las dimensiones de la tarjeta
    assertEquals(2, tarjeta.getFilas());
    assertEquals(3, tarjeta.getColumnas());
    
    // Para cada clave de la tarjeta
    for (int i = 1; i <= tarjeta.getFilas(); i++) {
      for (int j = 1; j <= tarjeta.getColumnas(); j++) {
        assertTrue(tarjeta.validarClave(i, j, 0));
      }
    }
  }

  @Test
  void testTarjetaClavesCeroFilas() {
    // Caso: Crea una tarjeta de claves de 0 filas por 3 columnas
    // RE  : Se produce IllegalArgumentException
    assertThrows(IllegalArgumentException.class, () -> new TarjetaClaves(0, 3));
  }

  @Test
  void testTarjetaClavesCeroColumnas() {
    // Caso: Crea una tarjeta de claves de 2 filas por 0 columnas
    // RE  : Se produce IllegalArgumentException
    assertThrows(IllegalArgumentException.class, () -> new TarjetaClaves(2, 0));
  }

  @Test
  void testTarjetaClavesCeroFilasColumnas() {
    // Caso: Crea una tarjeta de claves de 0 filas por 0 columnas
    // RE  : Se produce IllegalArgumentException
    assertThrows(IllegalArgumentException.class, () -> new TarjetaClaves(0, 0));
  }

  @Test
  void testTarjetaClavesSetClaveOk() {
    // Caso: Crea una tarjeta de claves de 3 filas por 3 columnas y modifica las
    //       claves de la diagonal (0, 0), (1, 1), (2, 2)
    // RE  : Se pueden modificar las claves sin problemas y se recuperan
    //       también sin problemas
    TarjetaClaves tarjeta = null;
    try {
       tarjeta = new TarjetaClaves(3, 3);
    } catch (IllegalArgumentException e) {
      fail("Se ha producido excepción al crear la tarjeta pero no debería haber ocurrido");
    }
    
    // Chequea las dimensiones de la tarjeta
    assertEquals(3, tarjeta.getFilas());
    assertEquals(3, tarjeta.getColumnas());

    // Modifica las claves de la diagonal
    tarjeta.setClave(1, 1, 999);
    tarjeta.setClave(2, 2, 88);
    tarjeta.setClave(3, 3, 77);
    
    // Y las lee
    assertTrue(tarjeta.validarClave(1, 1, 999));
    assertTrue(tarjeta.validarClave(2, 2, 88));
    assertTrue(tarjeta.validarClave(3, 3, 77));
  }

  @Test
  void testTarjetaClavesSetClaveFilaInvalida1() {
    // Caso: Crea una tarjeta de claves de 2 filas por 3 columnas e intenta
    //       modificar la clave en la posición 0, 3
    // RE  : Se produce IllegalArgumentException
    assertThrows(IllegalArgumentException.class, () -> new TarjetaClaves(2, 3).setClave(0, 3, 99));
  }

  @Test
  void testTarjetaClavesSetClaveFilaInvalida2() {
    // Caso: Crea una tarjeta de claves de 2 filas por 3 columnas e intenta
    //       modificar la clave en la posición 3, 3
    // RE  : Se produce IllegalArgumentException
    assertThrows(IllegalArgumentException.class, () -> new TarjetaClaves(2, 3).setClave(3, 3, 999));
  }

  @Test
  void testTarjetaClavesSetClaveColumnaInvalida1() {
    // Caso: Crea una tarjeta de claves de 2 filas por 3 columnas e intenta
    //       modificar la clave en la posición 2, 0
    // RE  : Se produce IllegalArgumentException
    assertThrows(IllegalArgumentException.class, () -> new TarjetaClaves(2, 3).setClave(2, 0, 999));
  }

  @Test
  void testTarjetaClavesSetClaveColumnaInvalida2() {
    // Caso: Crea una tarjeta de claves de 2 filas por 3 columnas e intenta
    //       modificar la clave en la posición 2, 4
    // RE  : Se produce IllegalArgumentException
    assertThrows(IllegalArgumentException.class, () -> new TarjetaClaves(2, 3).setClave(2, 4, 999));
  }

  @Test
  void testTarjetaClavesSetClaveClaveNegativa() {
    // Caso: Crea una tarjeta de claves de 2 filas por 3 columnas e intenta
    //       modificar la clave en la posición 2, 3 a -1
    // RE  : Se produce IllegalArgumentException
    assertThrows(IllegalArgumentException.class, () -> new TarjetaClaves(2, 3).setClave(2, 3, -1));
  }

  @Test
  void testTarjetaClavesSetClaveClaveSuperior999() {
    // Caso: Crea una tarjeta de claves de 2 filas por 3 columnas e intenta
    //       modificar la clave en la posición 2, 3 a 1000
    // RE  : Se produce IllegalArgumentException
    assertThrows(IllegalArgumentException.class, () -> new TarjetaClaves(2, 3).setClave(2, 3, 1000));
  }

}
