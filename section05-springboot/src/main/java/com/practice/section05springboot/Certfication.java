package com.practice.section05springboot;

public class Certfication {
    private long id;
    private String name;
    private String author;

    public Certfication(long id, String name, String author) {
        this.id = id;
        this.name = name;
        this.author = author;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getAuthor() {
        return author;
    }

    @Override
    public String toString() {
        return "Certfication [id=" + id + ", name=" + name + ", author=" + author + "]";
    }
}
