package ufpi.br.swap.entidades;

/**
 * Uma representação de um usuário do sistema.
 * @author edson
 */
public class Usuario {

    private String name;
    private String email;
    private String password;
    private Boolean logged;

    @Override
    public String toString() {
        return name + "\nEmail: " + email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Boolean getLogged() {
        return logged;
    }

    public void setLogged(Boolean logged) {
        this.logged = logged;
    }
}