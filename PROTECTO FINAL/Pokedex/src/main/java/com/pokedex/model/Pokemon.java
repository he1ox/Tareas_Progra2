/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pokedex.model;

/**
 *
 * @author georg
 */
public class Pokemon {
    private int id;
    private String name;
    private String forme_name;
    private String forme_base_pokemon_id;
    private int generation_id;
    private int evolution_chain_id;
    private int evolution_parent_pokemon_id;
    private int evolution_method_id;
    private String evolution_parameter;
    private int height;
    private int weight;
    private String species;
    private String color;
    private int pokemon_shape_id;
    private String habitat;
    private int gender_rate;
    private int capture_rate;
    private int base_experience;
    private int base_happiness;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getForme_name() {
        return forme_name;
    }

    public void setForme_name(String forme_name) {
        this.forme_name = forme_name;
    }

    public String getForme_base_pokemon_id() {
        return forme_base_pokemon_id;
    }

    public void setForme_base_pokemon_id(String forme_base_pokemon_id) {
        this.forme_base_pokemon_id = forme_base_pokemon_id;
    }

    public int getGeneration_id() {
        return generation_id;
    }

    public void setGeneration_id(int generation_id) {
        this.generation_id = generation_id;
    }

    public int getEvolution_chain_id() {
        return evolution_chain_id;
    }

    public void setEvolution_chain_id(int evolution_chain_id) {
        this.evolution_chain_id = evolution_chain_id;
    }

    public int getEvolution_parent_pokemon_id() {
        return evolution_parent_pokemon_id;
    }

    public void setEvolution_parent_pokemon_id(int evolution_parent_pokemon_id) {
        this.evolution_parent_pokemon_id = evolution_parent_pokemon_id;
    }

    public int getEvolution_method_id() {
        return evolution_method_id;
    }

    public void setEvolution_method_id(int evolution_method_id) {
        this.evolution_method_id = evolution_method_id;
    }

    public String getEvolution_parameter() {
        return evolution_parameter;
    }

    public void setEvolution_parameter(String evolution_parameter) {
        this.evolution_parameter = evolution_parameter;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public String getSpecies() {
        return species;
    }

    public void setSpecies(String species) {
        this.species = species;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getPokemon_shape_id() {
        return pokemon_shape_id;
    }

    public void setPokemon_shape_id(int pokemon_shape_id) {
        this.pokemon_shape_id = pokemon_shape_id;
    }

    public String getHabitat() {
        return habitat;
    }

    public void setHabitat(String habitat) {
        this.habitat = habitat;
    }

    public int getGender_rate() {
        return gender_rate;
    }

    public void setGender_rate(int gender_rate) {
        this.gender_rate = gender_rate;
    }

    public int getCapture_rate() {
        return capture_rate;
    }

    public void setCapture_rate(int capture_rate) {
        this.capture_rate = capture_rate;
    }

    public int getBase_experience() {
        return base_experience;
    }

    public void setBase_experience(int base_experience) {
        this.base_experience = base_experience;
    }

    public int getBase_happiness() {
        return base_happiness;
    }

    public void setBase_happiness(int base_happiness) {
        this.base_happiness = base_happiness;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Pokemon{id=").append(id);
        sb.append(", name=").append(name);
        sb.append(", generation_id=").append(generation_id);
        sb.append(", height=").append(height);
        sb.append(", weight=").append(weight);
        sb.append(", species=").append(species);
        sb.append(", color=").append(color);
        sb.append(", habitat=").append(habitat);
        sb.append(", gender_rate=").append(gender_rate);
        sb.append('}');
        return sb.toString();
    }
    
    
}
