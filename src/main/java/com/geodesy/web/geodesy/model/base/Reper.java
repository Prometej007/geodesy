package com.geodesy.web.geodesy.model.base;

import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class Reper extends GObject {
    protected Double height;

    public Double getHeight() {
        return height;
    }

    public Reper setHeight(Double height) {
        this.height = height;
        return this;
    }

    @Override
    public Reper setId(Long id) {
        return (Reper) super.setId(id);
    }

    @Override
    public Reper setName(String name) {
        return (Reper) super.setName(name);
    }

    @Override
    public String toString() {
        return "Reper{" +
                ", height=" + height +
                ", id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
