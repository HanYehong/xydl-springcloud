package com.njit.xydl.map.entity.vo;

import java.util.HashMap;

public class CarMarkersVo {
    private HashMap<String, Double>[] carMarkers;

    public HashMap<String, Double>[] getCarMarkers() {
        return carMarkers;
    }

    public void setCarMarkers(HashMap<String, Double>[] carMarkers) {
        this.carMarkers = carMarkers;
    }
}
