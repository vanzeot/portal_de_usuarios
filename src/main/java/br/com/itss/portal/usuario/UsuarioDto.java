package br.com.itss.portal.usuario;

public class UsuarioDto {

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }

    public boolean isActive() {
        return active;
    }

    public String getRoles() {
        return roles;
    }

    private String userName;
    private String password;
    private boolean active;
    private String roles;

    public UsuarioDto(Usuario usuario){
        this.userName = usuario.getUserName();
        this.active = usuario.isActive();
        this.roles = usuario.getRoles();
    }
}
