package com.example.superhelte_v4_jdbc.controllers;

import com.example.superhelte_v4_jdbc.dto.CityWithHeroes;
import com.example.superhelte_v4_jdbc.dto.HeroWithNumberOfPowers;
import com.example.superhelte_v4_jdbc.dto.HeroWithPowers;
import com.example.superhelte_v4_jdbc.dto.SuperheroForm;
import com.example.superhelte_v4_jdbc.models.Superhelt;
import com.example.superhelte_v4_jdbc.services.SuperheltService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping(path="superhelte")
public class SuperheltController {
    private SuperheltService superheltService;
    public SuperheltController(SuperheltService superheltService) {
        this.superheltService = superheltService;
    }

    @RequestMapping(path="")
    public String seAlleHelte(Model model) {
        List<Superhelt> superhelte = superheltService.getHeroes();
        model.addAttribute("helte", superhelte);
        return "index";
    }

    @GetMapping(path="{navn}")
    public ResponseEntity<Superhelt> hentSuperhelt(@PathVariable String navn) {
        return new ResponseEntity<>(superheltService.getHero(navn), HttpStatus.OK);
    }

    @GetMapping(path="add")
    public String opretSuperhelt(Model model) {
        List<String> cities = superheltService.getCities();
        SuperheroForm superheroForm = new SuperheroForm();
        model.addAttribute("superheroForm", superheroForm);
        model.addAttribute("cities", cities);
        return "createForm";
    }

    @PostMapping(path="add")
    public String opretSuperheltSubmit(SuperheroForm superheroForm) {
        String selectedValue = superheroForm.getCity();
        return "redirect:/";
    }

    @PutMapping(path="ret")
    public ResponseEntity<Superhelt> retSuperhelt(@RequestBody Superhelt superhelt) {
        return new ResponseEntity<>(superheltService.editSuperhero(superhelt), HttpStatus.OK);
    }

    @DeleteMapping(path="slet")
    public ResponseEntity<Superhelt> sletSuperhelt(@RequestBody String navn) {
        return new ResponseEntity<>(superheltService.deleteSuperhero(navn), HttpStatus.OK);
    }

    @GetMapping(path="superpower/count/{navn}")
    public ResponseEntity<String> getHeroWithPowerCount(@PathVariable String navn) {
        StringBuilder stringBuilder = new StringBuilder();
        HeroWithNumberOfPowers heroDetails = superheltService.getHeroWithSuperpowerCount(navn);
        stringBuilder.append("Hero Name: " + heroDetails.getHeroName()).append("\n");
        stringBuilder.append("Private Name: " + heroDetails.getPrivateName()).append("\n");
        stringBuilder.append("Number of superpowers: " + heroDetails.getSuperpowerCount()).append("\n");
        return new ResponseEntity<>(stringBuilder.toString(), HttpStatus.OK);
    }

    @GetMapping(path="superpower/count")
    public ResponseEntity<String> getHeroesWithPowerCount() {
        StringBuilder stringBuilder = new StringBuilder();
        List<HeroWithNumberOfPowers> heroDetails = superheltService.getHeroesWithSuperpowerCount();
        for (HeroWithNumberOfPowers heroDetail : heroDetails) {
            stringBuilder.append("Hero Name: " + heroDetail.getHeroName()).append("\n");
            stringBuilder.append("Private Name: " + heroDetail.getPrivateName()).append("\n");
            stringBuilder.append("Number of superpowers: " + heroDetail.getSuperpowerCount()).append("\n").append("\n");
        }
        return new ResponseEntity<>(stringBuilder.toString(), HttpStatus.OK);
    }

    @GetMapping(path="superpower/{navn}")
    public String getHeroWithPowers(@PathVariable String navn, Model model) {
        HeroWithPowers hero = superheltService.getHeroWithPowers(navn);
        model.addAttribute("name", hero.getHeroName());
        model.addAttribute("powers", hero.getSuperpowers());
        return "powers";
    }

    @GetMapping(path="superpower")
    public ResponseEntity<String> getHeroesWithPowers() {
        StringBuilder stringBuilder = new StringBuilder();
        List<HeroWithPowers> heroList = superheltService.getHeroesWithPowers();
        for (HeroWithPowers hero : heroList) {
            stringBuilder.append("Hero Name: " + hero.getHeroName()).append("\n");
            stringBuilder.append("Private Name: " + hero.getPrivateName()).append("\n");
            stringBuilder.append("Superpowers: ");
            for (String superpower : hero.getSuperpowers()) {
                if (superpower != hero.getSuperpowers().get(hero.getSuperpowers().size()-1)) {
                    stringBuilder.append(superpower + ", ");
                } else {
                    stringBuilder.append(superpower + ".\n").append("\n").append("\n");
                }
            }
        }
        return new ResponseEntity<>(stringBuilder.toString(), HttpStatus.OK);
    }

    @GetMapping(path="city/{navn}")
    public ResponseEntity<String> getHeroesOfCity(@PathVariable String navn) {
        StringBuilder stringBuilder = new StringBuilder();
        CityWithHeroes city = superheltService.getCityAndHeroes(navn);
        stringBuilder.append("City Name: " + city.getCityName()).append("\n");
        stringBuilder.append("Hero Name: ");
        for (String superhelt : city.getHeroesInCity()) {
            if (superhelt != city.getHeroesInCity().get(city.getHeroesInCity().size()-1)) {
                stringBuilder.append(superhelt + ", ");
            } else {
                stringBuilder.append(superhelt + ".\n\n");
            }
        }
        return new ResponseEntity<>(stringBuilder.toString(), HttpStatus.OK);
    }

    @GetMapping(path="city")
    public ResponseEntity<String> getCitiesAndHeroes() {
        StringBuilder stringBuilder = new StringBuilder();
        List<CityWithHeroes> cities = superheltService.getCitiesAndHeroes();
        for (CityWithHeroes city : cities) {
            stringBuilder.append("City Name: " + city.getCityName()).append("\n");
            stringBuilder.append("Hero Name: ");
            for (String superhelt : city.getHeroesInCity()) {
                if (superhelt != city.getHeroesInCity().get(city.getHeroesInCity().size()-1)) {
                    stringBuilder.append(superhelt + ", ");
                } else {
                    stringBuilder.append(superhelt + ".\n\n");
                }
            }
        }
        return new ResponseEntity<>(stringBuilder.toString(), HttpStatus.OK);
    }
}
