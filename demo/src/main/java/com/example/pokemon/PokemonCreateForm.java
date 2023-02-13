package com.example.pokemon;

public class PokemonCreateForm {
    private String name;
    private Integer specialityId;
    private String imageUrl;

    public PokemonCreateForm() {

    }

    public PokemonCreateForm(String name, Integer specialityId, String imageUrl) {
        this.name = name;
        this.specialityId = specialityId;
        this.imageUrl = imageUrl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getSpecialityId() {

        return specialityId;
    }

    public void setSpecialityId(Integer specialityId) {
        this.specialityId = specialityId;
    }


    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
