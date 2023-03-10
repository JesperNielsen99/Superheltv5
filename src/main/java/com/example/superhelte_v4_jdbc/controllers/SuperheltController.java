package com.example.superhelte_v4_jdbc.controllers;

import com.example.superhelte_v4_jdbc.dto.CityWithHeroes;
import com.example.superhelte_v4_jdbc.dto.HeroWithNumberOfPowers;
import com.example.superhelte_v4_jdbc.dto.HeroWithPowers;
import com.example.superhelte_v4_jdbc.models.Superhelt;
import com.example.superhelte_v4_jdbc.repositories.util.DB_Connector;
import com.example.superhelte_v4_jdbc.services.SuperheltService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping(path="superhelte")
public class SuperheltController {
    private SuperheltService superheltService;
    public SuperheltController(SuperheltService superheltService) {
        this.superheltService = superheltService;
    }

    @RequestMapping(path="")
    public ResponseEntity<?> seAlleHelte(@RequestParam (required = false) String format) {
        if (format != null && format.equals("html")) {
            List<Superhelt> superheltList = superheltService.getHeroes();
            HttpHeaders responseHeaders = new HttpHeaders();
            responseHeaders.set("Content-Type", "text/html");
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("<html><body><table>");
            stringBuilder.append("<tr>" +
                            "<th> Hero Name </th>\n" +
                            "<th> Private Name </th>\n" +
                            "<th> CreationYear </th>\n" +
                            "</tr>");
            for (Superhelt superhelt : superheltList) {
                stringBuilder.append("<tr>");
                stringBuilder.append("<td>" + superhelt.getHeroName() + "</td>");
                stringBuilder.append("<td>" + superhelt.getPrivateName() + "</td>");
                stringBuilder.append("</tr>");
            }
            stringBuilder.append("</html></body></table>");

            return new ResponseEntity<>(stringBuilder.toString(),
                    responseHeaders, HttpStatus.OK);
        } else {
            List<Superhelt> superheltList = superheltService.getHeroes();
            StringBuilder stringBuilder = new StringBuilder();
            for (Superhelt superhelt : superheltList) {
                stringBuilder.append("Hero Name: " + superhelt.getHeroName()).append("\n");
                stringBuilder.append("Private Name: " + superhelt.getPrivateName()).append("\n");
                stringBuilder.append("Creation Year: " + superhelt.getCreationYear()).append("\n").append("\n");
            }
            return new ResponseEntity<>(stringBuilder, HttpStatus.OK);
        }
    }

    @GetMapping(path="{navn}")
    public ResponseEntity<Superhelt> hentSuperhelt(@PathVariable String navn) {
        return new ResponseEntity<>(superheltService.getHero(navn), HttpStatus.OK);
    }

    @PostMapping(path="opret")
    public ResponseEntity<Superhelt> opretSuperhelt(@RequestBody String heroName, String privateName, int creationYear) {
        return new ResponseEntity<>(superheltService.createSuperhero(heroName, privateName, creationYear), HttpStatus.OK);
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
    public ResponseEntity<String> getHeroWithPowers(@PathVariable String navn) {
        StringBuilder stringBuilder = new StringBuilder();
        HeroWithPowers hero = superheltService.getHeroWithPowers(navn);
        stringBuilder.append("Hero Name: " + hero.getHeroName()).append("\n");
        stringBuilder.append("Private Name: " + hero.getPrivateName()).append("\n");
        stringBuilder.append("Superpowers: ");
        for (String superpower : hero.getSuperpowers()) {
            if (superpower != hero.getSuperpowers().get(hero.getSuperpowers().size()-1)) {
                stringBuilder.append(superpower + ", ");
            } else {
                stringBuilder.append(superpower + ".\n").append("\n");
            }
        }
        return new ResponseEntity<>(stringBuilder.toString(), HttpStatus.OK);
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
