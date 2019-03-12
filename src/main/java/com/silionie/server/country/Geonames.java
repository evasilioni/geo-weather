package com.silionie.server.country;

import java.util.ArrayList;
import java.util.List;

public class Geonames {
    private List<Country> geonames = new ArrayList<>();

    public List<Country> getGeonames() {
        return geonames;
    }

    public void setGeonames(List<Country> geonames) {
        this.geonames = geonames;
    }
}
