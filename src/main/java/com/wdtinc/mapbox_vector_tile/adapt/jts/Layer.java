package com.wdtinc.mapbox_vector_tile.adapt.jts;

import com.vividsolutions.jts.geom.Geometry;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Layer {

    private final String name;
    private final List<Geometry> geometries;

    public Layer(String name) {
        this(name, new ArrayList<>());
    }

    public Layer(String name, List<Geometry> geometries) {
        this.name = name;
        this.geometries = geometries;
    }

    public void add(Geometry geometry) {
        geometries.add(geometry);
    }

    public void addAll(Collection<Geometry> geometries) {
        this.geometries.addAll(geometries);
    }

    public List<Geometry> getGeometries() {
        return new ArrayList<>(geometries);
    }

    public String getName() {
        return name;
    }
}
