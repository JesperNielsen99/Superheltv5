package com.example.superhelte_v4_jdbc.repositories;

import com.example.superhelte_v4_jdbc.dto.CityWithHeroes;
import com.example.superhelte_v4_jdbc.dto.HeroWithNumberOfPowers;
import com.example.superhelte_v4_jdbc.dto.HeroWithPowers;
import com.example.superhelte_v4_jdbc.dto.SuperheroForm;
import com.example.superhelte_v4_jdbc.models.City;
import com.example.superhelte_v4_jdbc.models.Superhelt;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository("superhelt_Stub")
public class SuperheltRepository_Stub implements ISuperheltRepository {
    private List<Superhelt> superheltList = new ArrayList<>(List.of(
            new Superhelt("Bøv", "Børge", 1938, 3),
            new Superhelt("Batman", "Bruce Wayne", 1970, 1),
            new Superhelt("Superman", "Clark Kent", 1998, 3),
            new Superhelt("Spiderman", "Peter Parker", 1976, 2)
    ));

    private List<City> cities = new ArrayList<>(List.of(
            new City(1, "Gotham"),
            new City(2, "Queens"),
            new City(3, "New York")
    ));
    private Superhelt currentHero;

    public SuperheltRepository_Stub() {
    }

    public Superhelt deleteSuperhero(String heroName) {
        for (Superhelt superhelt : superheltList) {
            if (superhelt.getHeroName().equalsIgnoreCase(superhelt.getHeroName())) {
                currentHero = superhelt;
                superheltList.remove(superhelt);
                return currentHero;
            }
        }
        return null;
    }

    public Superhelt editSuperhero(Superhelt superhelt) {
        int index = superheltList.indexOf(getHero(superhelt.getHeroName()));
        superheltList.set(index, superhelt);
        return superhelt;
    }

    public Superhelt getHero(String heroName) {
        for (Superhelt superhelt : superheltList) {
            if (superhelt.getHeroName().equalsIgnoreCase(heroName)) {
                currentHero = superhelt;
            }
        }
        return currentHero;
    }

    public List<Superhelt> getHeroes() {
        return superheltList;
    }

    @Override
    public HeroWithNumberOfPowers getHeroWithSuperpowerCount(String name) {
        return null;
    }

    @Override
    public List<HeroWithNumberOfPowers> getHeroesWithSuperpowerCount() {
        return null;
    }

    @Override
    public HeroWithPowers getHeroWithPowers(String heroName) {
        return null;
    }

    @Override
    public List<HeroWithPowers> getHeroesWithPowers() {
        return null;
    }

    @Override
    public CityWithHeroes getCityAndHeroes(String cityName) {
        List<String> heroList = new ArrayList<>();
        for (City city : cities) {
            if (city.getCityName().equals(cityName)) {
                for (Superhelt superhelt : superheltList) {
                    if (superhelt.getCityID() == city.getCityID()) {
                        heroList.add(superhelt.getHeroName());
                    }
                }
                break;
            }
        }
        return new CityWithHeroes(cityName, heroList);
    }

    @Override
    public List<CityWithHeroes> getCitiesAndHeroes() {
        List<String> heroList = new ArrayList<>();
        List<CityWithHeroes> cityWithHeroes = new ArrayList<>();
        for (City city : cities) {
            for (Superhelt superhelt : superheltList) {
                if (superhelt.getCityID() == city.getCityID()) {
                    heroList.add(superhelt.getHeroName());
                }
            }
            cityWithHeroes.add(new CityWithHeroes(city.getCityName(), heroList));
            heroList.clear();
        }
        return cityWithHeroes;
    }

    public List<String> getCities() {
        List<String> cities = new ArrayList<>();
        for (City city : this.cities) {
            cities.add(city.getCityName());
        }
        return cities;
    }

    public List<String> getPowers() {
        return null;
    }

    public void addSuperhero(SuperheroForm superheroForm) {}
}
