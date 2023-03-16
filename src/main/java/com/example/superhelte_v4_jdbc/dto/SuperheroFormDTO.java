package com.example.superhelte_v4_jdbc.dto;

import java.util.List;

public class SuperheroFormDTO {
    private int heroId;
    private String heroName;
    private String realName;
    private int creationYear;
    private String city;
    List<String> powerList;

    public SuperheroFormDTO(int heroId, String heroName, String realName,
                            int creationYear, String city, List<String> powerList) {
        this.heroId = heroId;
        this.heroName = heroName;
        this.realName = realName;
        this.creationYear = creationYear;
        this.city = city;
        this.powerList = powerList;
    }

    public void add(String power) {
        powerList.add(power);
    }
}