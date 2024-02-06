package prog.ud06.actividad611.coleccion;

/**
 * Interface que implementan las clases que proveen (obtienen) la información 
 * de los usuarios desde una fuente externa (archivo, base de datos, etc.)
 */
public interface ProveedorUsuarios {
  
  public Usuarios obtieneUsuarios() throws ProveedorUsuariosException;
     
}
