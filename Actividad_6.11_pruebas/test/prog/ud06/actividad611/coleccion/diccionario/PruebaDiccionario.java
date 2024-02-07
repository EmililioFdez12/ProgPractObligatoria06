package prog.ud06.actividad611.coleccion.diccionario;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class PruebaDiccionario {

  @Test
  void testAddOk() {
    // Caso: Crea un diccionario vacio y añade un elemento con un nombre no nulo
    // RE  : Se añade el objeto y se recupera, siendo el mismo objeto
    
    // Crea diccionario
    Diccionario<ElementoPrueba> diccionario = new Diccionario<>();
    
    // Añade objeto. No debe lanzar excepcion
    ElementoPrueba elemento = new ElementoPrueba("Primer Elemento");
    assertDoesNotThrow(() -> diccionario.add("elemento1", elemento));
    
    // Debe estar el elemento con ese nombre en el diccionario
    assertTrue(diccionario.contieneNombre("elemento1"));
    
    // Recupera el objeto por su nombre
    ElementoPrueba elementoLeido = diccionario.getEntrada("elemento1");
    // Debe ser igual al introducido
    assertEquals(elemento, elementoLeido);
  }

  @Test
  void testAddNull() {
    // Caso: Crea un diccionario vacio y añade un elemento con un nombre nulo
    // RE  : No se añade el objeto y se lanza IllegalArgumentException
    
    // Crea diccionario
    Diccionario<ElementoPrueba> diccionario = new Diccionario<>();
    
    // Añade objeto con nombre nulo. Debe lanzar excepcion
    ElementoPrueba elemento = new ElementoPrueba("Primer Elemento");
    assertThrows(IllegalArgumentException.class, () -> diccionario.add(null, elemento));
  }

  @Test
  void testAddDos() {
    // Caso: Crea un diccionario vacio y añade un elemento con un nombre valido
    //       A continuación agrega otro con distinto nombre
    // RE  : Se añaden ambos objetos y se pueden recuperar
    
    // Crea diccionario
    Diccionario<ElementoPrueba> diccionario = new Diccionario<>();
    
    // Añade objeto con nombre OK. NO debe lanzar excepcion
    ElementoPrueba elemento = new ElementoPrueba("Primer Elemento");
    assertDoesNotThrow(() -> diccionario.add("elemento1", elemento));
    // Ahora añade otro con otro nombre y tampoco debe lanzar
    ElementoPrueba elemento2 = new ElementoPrueba("Segundo Elemento");
    assertDoesNotThrow(() -> diccionario.add("elemento2", elemento2));

    // Deben estar ambos en el diccionario
    assertTrue(diccionario.contieneNombre("elemento1"));
    assertTrue(diccionario.contieneNombre("elemento2"));
    
    // Recupera el primer objeto por su nombre
    ElementoPrueba elementoLeido = diccionario.getEntrada("elemento1");
    // Debe ser igual al introducido
    assertEquals(elemento, elementoLeido);

    // Lo mismo con el segundo
    ElementoPrueba elementoLeido2 = diccionario.getEntrada("elemento2");
    // Debe ser igual al introducido
    assertEquals(elemento2, elementoLeido2);
  }

  @Test
  void testAddDuplicado() {
    // Caso: Crea un diccionario vacio y añade un elemento con un nombre valido
    //       A continuación agrega otro con el mismo nombre
    // RE  : Se añade el primero pero el segundo lanza DiccionarioException
    
    // Crea diccionario
    Diccionario<ElementoPrueba> diccionario = new Diccionario<>();
    
    // Añade objeto con nombre OK. NO debe lanzar excepcion
    ElementoPrueba elemento = new ElementoPrueba("Primer Elemento");
    assertDoesNotThrow(() -> diccionario.add("elemento1", elemento));
    // Ahora añade otro con el mismo nombre y ahora SI debe lanzar
    ElementoPrueba elemento2 = new ElementoPrueba("Segundo Elemento");
    assertThrows(DiccionarioException.class, () -> diccionario.add("elemento1", elemento2));
  }

  @Test
  void testAddValorNull() {
    // Caso: Crea un diccionario vacio y añade un elemento null con un nombre valido
    // RE  : No deja añadir y lanza IllegalArgumentException
    
    // Crea diccionario
    Diccionario<ElementoPrueba> diccionario = new Diccionario<>();
    
    // Añade objeto null con nombre OK. Debe lanzar excepcion
    assertThrows(IllegalArgumentException.class, () -> diccionario.add("elemento1", null));
  }

  @Test
  void testContieneNombreNull() {
    // Caso: Crea un diccionario vacio y añade un elemento con un nombre válido
    //       A continuación intenta ver si contiene el nombre null
    // RE  : Devuelve false
    
    // Crea diccionario
    Diccionario<ElementoPrueba> diccionario = new Diccionario<>();
    
    // Añade objeto. No debe lanzar excepcion
    ElementoPrueba elemento = new ElementoPrueba("Primer Elemento");
    assertDoesNotThrow(() -> diccionario.add("elemento1", elemento));
    
    assertFalse(diccionario.contieneNombre(null));
  }

  @Test
  void testContieneNombreNoExistente() {
    // Caso: Crea un diccionario vacio y añade un elemento con un nombre válido
    //       A continuación intenta ver si contiene otro nombre
    // RE  : Devuelve false
    
    // Crea diccionario
    Diccionario<ElementoPrueba> diccionario = new Diccionario<>();
    
    // Añade objeto. No debe lanzar excepcion
    ElementoPrueba elemento = new ElementoPrueba("Primer Elemento");
    assertDoesNotThrow(() -> diccionario.add("elemento1", elemento));
    
    assertFalse(diccionario.contieneNombre("elemento2"));
  }

  @Test
  void testGetEntradaNull() {
    // Caso: Crea un diccionario vacio y añade un elemento con un nombre válido
    //       A continuación intenta acceder a la entrada con nombre null
    // RE  : Devuelve null
    
    // Crea diccionario
    Diccionario<ElementoPrueba> diccionario = new Diccionario<>();
    
    // Añade objeto. No debe lanzar excepcion
    ElementoPrueba elemento = new ElementoPrueba("Primer Elemento");
    assertDoesNotThrow(() -> diccionario.add("elemento1", elemento));
    
    assertNull(diccionario.getEntrada(null));
  }

  @Test
  void testGetEntradaNoExistente() {
    // Caso: Crea un diccionario vacio y añade un elemento con un nombre válido
    //       A continuación intenta obtener una entrada con nombre inexistente
    // RE  : Devuelve null
    
    // Crea diccionario
    Diccionario<ElementoPrueba> diccionario = new Diccionario<>();
    
    // Añade objeto. No debe lanzar excepcion
    ElementoPrueba elemento = new ElementoPrueba("Primer Elemento");
    assertDoesNotThrow(() -> diccionario.add("elemento1", elemento));
    
    assertNull(diccionario.getEntrada("elemento2"));
  }

}

// Clase para los elementos
class ElementoPrueba {
  public String nombre;
  
  public ElementoPrueba(String nombre) {
    this.nombre = nombre;
  }

  @Override
  public boolean equals(Object obj) {
    if (obj instanceof ElementoPrueba) {
      return ((ElementoPrueba)obj).nombre.equals(this.nombre);
    } else {
      return false;
    }
  }
}