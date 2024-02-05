package prog.ud06.actividad611.coleccion;

import java.util.ArrayList;
import java.util.List;

public class Usuarios {
  private List<Usuario> listaUsuarios;
  private Usuario usuario1;
  private String nombreUsuario;
  private String nombreCompleto;
  private TarjetaClaves tarjeta;
  private List<Cliente> clientes;

  public Usuarios() {
    this.listaUsuarios = new ArrayList<Usuario>();
  }

  public void addUsuario(Usuario usuario) {
    if (usuario == null) {
      throw new IllegalArgumentException();
    }
    
   
    if (usuario.getNombreUsuario().equals(usuario1.getNombreUsuario())) {
      throw new UsuariosException();
    } else {
      listaUsuarios.add(usuario);
    }
    
  }

  public Usuario getUsuarioPorNombreUsuario(String nombresUsuario) {
    return null;

  }

}
