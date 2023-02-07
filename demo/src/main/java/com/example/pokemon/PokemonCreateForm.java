package com.example.pokemon;

public class PokemonCreateForm {
    private String name;
    private Integer speciallityId;
    private String imageUrl;

    public PokemonCreateForm() {

    }

    public PokemonCreateForm(String name, Integer speciallityId, String imageUrl) {
        this.name = name;
        this.speciallityId = speciallityId;
        this.imageUrl = imageUrl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getSpeciallityId() {
        return speciallityId;
    }

    public void setSpeciallity(Integer speciallityId) {
        this.speciallityId = speciallityId;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
