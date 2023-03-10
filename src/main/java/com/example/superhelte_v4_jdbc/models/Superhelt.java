package com.example.superhelte_v4_jdbc.models;


public class Superhelt {

    private String heroName;
    private String privateName;
    private int creationYear;
    private int cityID;

    public Superhelt(String heroName, String privateName, int creationYear) {
        this.heroName = heroName;
        this.privateName = privateName;
        this.creationYear = creationYear;
    }

    public Superhelt(String heroName, String privateName, int creationYear, int cityID) {
        this.heroName = heroName;
        this.privateName = privateName;
        this.creationYear = creationYear;
        this.cityID = cityID;
    }

    //Get methods
    public String getHeroName() { return heroName; }
    public String getPrivateName() { return privateName; }
    public int getCreationYear() { return creationYear; }
    public int getCityID() { return cityID; }

    //Set methods
    public void setHeroName(String heroName) {
        if (!heroName.isEmpty()) {
            this.heroName = heroName;
        } else {
            this.heroName = "";
        }
    }

    public void setPrivateName(String privateName) { this.privateName = privateName;}
}
