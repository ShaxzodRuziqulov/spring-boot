package com.example.demo.Domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import org.antlr.v4.runtime.misc.NotNull;

import java.io.Serializable;

@Entity
public class Role implements Serializable {

    @Id
    @NotNull
    private String name;

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
