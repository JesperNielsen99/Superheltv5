package com.example.superhelte_v4_jdbc.dto;

import java.util.List;

public class SuperheroForm {
    private int heroId;
    private String heroName;
    private String privateName;
    private int creationYear;
    private String city;
    private List<String> powerList;

    public SuperheroForm(int heroId, String heroName, String privateName,
                            int creationYear, String city, List<String> powerList) {
        this.heroId = heroId;
        this.heroName = heroName;
        this.privateName = privateName;
        this.creationYear = creationYear;
        this.city = city;
        this.powerList = powerList;
    }

    public SuperheroForm() {}

    public void add(String power) {
        powerList.add(power);
    }

    public int getHeroId() { return heroId; }
    public String getHeroName() { return heroName; }
    public String getPrivateName() { return privateName; }
    public int getCreationYear() { return creationYear; }
    public String getCity() { return city; }
    public List<String> getPowerList() { return powerList; }

    public void setHeroId(int heroId) { this.heroId = heroId; }
    public void setHeroName(String heroName) { this.heroName = heroName; }
    public void setPrivateName(String privateName) { this.privateName = privateName; }
    public void setCreationYear(int creationYear) { this.creationYear = creationYear; }
    public void setCity(String city) { this.city = city; }
    public void setPowerList(List<String> powerList) { this.powerList = powerList; }
}
