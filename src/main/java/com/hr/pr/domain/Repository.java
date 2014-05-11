package com.hr.pr.domain;

public class Repository {

    private String owner;
    private String name;

    public Repository(String owner, String name) {
        this.owner = owner;
        this.name = name;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
