package com.example.superhelte_v4_jdbc.repositories;

import com.example.superhelte_v4_jdbc.dto.CityWithHeroes;
import com.example.superhelte_v4_jdbc.dto.HeroWithNumberOfPowers;
import com.example.superhelte_v4_jdbc.dto.HeroWithPowers;
import com.example.superhelte_v4_jdbc.models.Superhelt;

import java.util.List;

public interface ISuperheltRepository {
    Superhelt getHero(String heroName);
    List<Superhelt> getHeroes();
    HeroWithNumberOfPowers getHeroWithSuperpowerCount(String name);
    List<HeroWithNumberOfPowers> getHeroesWithSuperpowerCount();

    HeroWithPowers getHeroWithPowers(String heroName);
    List<HeroWithPowers> getHeroesWithPowers();

    CityWithHeroes getCityAndHeroes(String cityName);
    List<CityWithHeroes> getCitiesAndHeroes();
}
