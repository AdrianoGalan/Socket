package model;

public class Usuario {

	private String usuario;
	private String senha;

	public Usuario() {
		super();
	}

	public Usuario(String usuario, String senha) {
		super();
		this.usuario = usuario;
		this.senha = senha;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		Usuario other = (Usuario) obj;
		if (this.usuario.equals(other.usuario)) {
			if (this.senha.equals(other.senha))
				return true;
		}

		return false;

	}

	@Override
	public String toString() {
		return "Usuario [usuario=" + usuario + ", senha=" + senha + "]";
	}

}
