package ufpi.br.swap.entidades;

public class Usuario {

    private String name;
    private String email;
    private String password;
    private Boolean loged;

    public Boolean isLoged() {
        return loged;
    }

    public void setLogged(Boolean loged) {
        this.loged = loged;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}