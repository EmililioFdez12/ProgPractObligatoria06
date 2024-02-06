package prog.ud06.actividad611.coleccion;

import prog.ud06.actividad611.coleccion.diccionario.Diccionario;

public class Usuarios {

  private Diccionario<Usuario> diccionarioUsuarios;

  public Usuarios() {
    this.diccionarioUsuarios = new Diccionario<Usuario>();
  }

  public void addUsuario(Usuario usuario) {
    if (usuario == null) {
      throw new IllegalArgumentException();
    }
    
    if (diccionarioUsuarios.contieneNombre(usuario.getNombreUsuario())) {
      throw new UsuariosException();
    } else {
      diccionarioUsuarios.add(usuario.getNombreUsuario(), usuario);
    }

  }

  public Usuario getUsuarioPorNombreUsuario(String nombreUsuario) {
    return diccionarioUsuarios.getEntrada(nombreUsuario);
  }

}
