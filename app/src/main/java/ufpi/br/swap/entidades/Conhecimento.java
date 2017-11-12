package ufpi.br.swap.entidades;

import java.io.Serializable;

/**
 * Created by kassio on 12/11/17.
 */

public class Conhecimento{
    private String user;
    private String name;
    private String description;

    @Override
    public String toString() {
        return name + "\nDescrição: " + description + "\nUsuário: " + user;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
