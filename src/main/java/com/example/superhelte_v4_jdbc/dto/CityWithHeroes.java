package com.example.superhelte_v4_jdbc.dto;

import java.util.List;

public class CityWithHeroes {
    private String cityName;
    private List<String> heroesInCity;

    public CityWithHeroes(String cityName, List<String> heroesInCity) {
        this.cityName = cityName;
        this.heroesInCity = heroesInCity;
    }

    public String getCityName() { return cityName; }
    public List<String> getHeroesInCity() { return heroesInCity; }

    public void addSuperhero(String string) {
        heroesInCity.add(string);
    }

}
