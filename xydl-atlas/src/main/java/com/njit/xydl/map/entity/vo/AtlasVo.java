package com.njit.xydl.map.entity.vo;

import com.njit.xydl.map.entity.Atlas;
import com.njit.xydl.map.entity.AtlasImage;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.Date;
import java.util.List;

public class AtlasVo {
    private String id;

    private String title;

    private String description;

    private String logoUrl;

    private List<String> imageUrl;

    private Double longitude;

    private Double latitude;

    private String category;

    public AtlasVo(Atlas atlas) {
        this.id = atlas.getId();
        this.title = atlas.getTitle();
        this.description = atlas.getDescription();
        this.logoUrl = atlas.getLogoUrl();
        this.longitude = atlas.getLongitude();
        this.latitude = atlas.getLatitude();
        this.category = atlas.getCategory();
    }

    public AtlasVo(){

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLogoUrl() {
        return logoUrl;
    }

    public void setLogoUrl(String logoUrl) {
        this.logoUrl = logoUrl;
    }

    public List<String> getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(List<String> imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

}
