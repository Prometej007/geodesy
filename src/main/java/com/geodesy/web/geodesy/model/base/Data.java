package com.geodesy.web.geodesy.model.base;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.geodesy.web.geodesy.model.utils.enums.CalculationTypeName;
import com.geodesy.web.geodesy.model.utils.DateDeserializer;
import com.geodesy.web.geodesy.model.utils.DateSerializer;

import javax.persistence.Enumerated;
import javax.persistence.MappedSuperclass;
import javax.persistence.OneToMany;
import java.sql.Timestamp;
import java.util.List;

@MappedSuperclass
public abstract class Data extends GObject {
    @OneToMany(mappedBy = "data")
    protected List<? extends Reper> reperList;
    protected Timestamp date;
    @Enumerated
    protected CalculationTypeName calculationTypeName;

    public List<? extends Reper> getReperList() {
        return reperList;
    }

    public Data setReperList(List<? extends Reper> reperList) {
        this.reperList = reperList;
        return this;
    }

    @JsonSerialize(using = DateSerializer.class)
    public Timestamp getDate() {
        return date;
    }

    @JsonDeserialize(using = DateDeserializer.class)
    public Data setDate(Timestamp date) {
        this.date = date;
        return this;
    }

    public CalculationTypeName getCalculationTypeName() {
        return calculationTypeName;
    }

    public Data setCalculationTypeName(CalculationTypeName calculationTypeName) {
        this.calculationTypeName = calculationTypeName;
        return this;
    }

    @Override
    public Data setId(Long id) {
        return (Data) super.setId(id);
    }

    @Override
    public Data setName(String name) {
        return (Data) super.setName(name);
    }
}
