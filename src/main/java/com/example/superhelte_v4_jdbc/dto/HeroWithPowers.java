package com.example.superhelte_v4_jdbc.dto;

import java.util.ArrayList;
import java.util.List;

public class HeroWithPowers {
    private String heroName;
    private String privateName;
    private List<String> superpowers;

    public HeroWithPowers(String heroName, String privateName, List<String> superpowers) {
        this.heroName = heroName;
        this.privateName = privateName;
        this.superpowers = superpowers;
    }

    public String getHeroName() { return heroName; }
    public String getPrivateName() { return privateName; }
    public List<String> getSuperpowers() { return superpowers; }

    public void addSuperpower(String superpower) {
        superpowers.add(superpower);
    }
}
