package br.com.itss.portal.usuario;

public class UsuarioDto {

    public String getUserName() {
        return userName;
    }

    public boolean isActive() {
        return active;
    }

    public String getRoles() {
        return roles;
    }

    private String userName;
    private String fullName;
    private boolean active;
    private String roles;

    public UsuarioDto(Usuario usuario){
        this.userName = usuario.getUserName();
        this.active = usuario.isActive();
        this.roles = usuario.getRoles();
        this.fullName = usuario.getFullName();
    }
}
