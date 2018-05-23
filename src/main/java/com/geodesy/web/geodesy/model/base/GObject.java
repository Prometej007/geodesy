package com.geodesy.web.geodesy.model.base;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class GObject {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;
    protected String name;

    public Long getId() {
        return id;
    }

    public GObject setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public GObject setName(String name) {
        this.name = name;
        return this;
    }
}
