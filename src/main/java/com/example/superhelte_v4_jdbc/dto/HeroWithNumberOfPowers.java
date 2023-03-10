package com.example.superhelte_v4_jdbc.dto;
public class HeroWithNumberOfPowers {
    private String heroName;
    private String privateName;
    private int superpowerCount;

    public HeroWithNumberOfPowers(String heroName, String privateName, int superpowerCount) {
        this.heroName = heroName;
        this.privateName = privateName;
        this.superpowerCount = superpowerCount;
    }

    public String getHeroName() { return heroName; }
    public String getPrivateName() { return privateName; }
    public int getSuperpowerCount() { return superpowerCount; }
}
