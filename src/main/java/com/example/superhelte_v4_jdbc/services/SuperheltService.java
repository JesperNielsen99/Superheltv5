package com.example.superhelte_v4_jdbc.services;

import com.example.superhelte_v4_jdbc.dto.CityWithHeroes;
import com.example.superhelte_v4_jdbc.dto.HeroWithNumberOfPowers;
import com.example.superhelte_v4_jdbc.dto.HeroWithPowers;
import com.example.superhelte_v4_jdbc.models.Superhelt;
import com.example.superhelte_v4_jdbc.repositories.ISuperheltRepository;
import com.example.superhelte_v4_jdbc.repositories.SuperheltRepository_DB;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SuperheltService {
    private final ISuperheltRepository repository;

    public SuperheltService(ApplicationContext context, @Value("${superhelt.repository.impl}") String impl) {
        repository = (ISuperheltRepository) context.getBean(impl);
    }

    public Superhelt createSuperhero(String heroName, String privateName, int creationYear) {
        return null;
    }

    public Superhelt editSuperhero(Superhelt superhelt) {
        return null;
    }

    public Superhelt deleteSuperhero(String name) {
        return null;
    }

    public Superhelt getHero(String name) {
        return null;
    }

    public List<Superhelt> getHeroes() {
        return repository.getHeroes();
    }

    public HeroWithNumberOfPowers getHeroWithSuperpowerCount(String heroName) {
        return repository.getHeroWithSuperpowerCount(heroName);
    }

    public List<HeroWithNumberOfPowers> getHeroesWithSuperpowerCount() {
        return repository.getHeroesWithSuperpowerCount();
    }

    public HeroWithPowers getHeroWithPowers(String heroName) {
        return repository.getHeroWithPowers(heroName);
    }

    public List<HeroWithPowers> getHeroesWithPowers() {
        return repository.getHeroesWithPowers();
    }

    public CityWithHeroes getCityAndHeroes(String cityName) {
        return repository.getCityAndHeroes(cityName);
    }

    public List<CityWithHeroes> getCitiesAndHeroes() {
        return repository.getCitiesAndHeroes();
    }
}
