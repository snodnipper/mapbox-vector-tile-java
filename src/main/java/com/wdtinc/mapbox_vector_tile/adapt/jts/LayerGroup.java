package com.wdtinc.mapbox_vector_tile.adapt.jts;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LayerGroup {

    private List<Layer> layers = new ArrayList<>();

    public void addLayers(Layer... layer) {
        layers.addAll(Arrays.asList(layer));
    }

    public void addLayer(Layer layer) {
        layers.add(layer);
    }

    public void removeLayer(Layer layer) {
        layers.remove(layer);
    }

    public List<Layer> getLayers() {
        return new ArrayList<>(layers);
    }

    public Layer getLayer(String name) {
        for (Layer layer : layers) {
            if (layer.getName().equals(name)) {
                return layer;
            }
        }
        return null;
    }

    public Layer getLayer(int index) {
        return layers.get(index);
    }
}
