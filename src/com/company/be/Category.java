package com.company.be;

public class Category {

    private int Id;
    private String name;

    public Category(int id, String name) {
        Id = id;
        this.name = name;
    }

    public int getId() {
        return Id;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
