package com.example.superhelte_v4_jdbc.repositories;

import com.example.superhelte_v4_jdbc.dto.CityWithHeroes;
import com.example.superhelte_v4_jdbc.dto.HeroWithNumberOfPowers;
import com.example.superhelte_v4_jdbc.dto.HeroWithPowers;
import com.example.superhelte_v4_jdbc.models.Superhelt;
import com.example.superhelte_v4_jdbc.repositories.util.DB_Connector;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository("superhelt_DB")
public class SuperheltRepository_DB implements ISuperheltRepository {
    @Override
    public List<Superhelt> getHeroes() {
        try {
            Connection connection = DB_Connector.getConnection();
            String SQL = "SELECT * FROM superhero";
            Statement stmt = connection.createStatement();
            ResultSet resultSet = stmt.executeQuery(SQL);
            List<Superhelt> heroesList = new ArrayList<Superhelt>();
            while (resultSet.next()) {
                heroesList.add(new Superhelt(resultSet.getString("HeroName"), resultSet.getString("PrivateName"), resultSet.getInt("CreationYear")));
            }
            return heroesList;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Superhelt createSuperhero(String heroName, String privateName, int creationYear) {
        return new Superhelt(heroName, privateName, creationYear);
    }

    @Override
    public HeroWithNumberOfPowers getHeroWithSuperpowerCount(String heroName) {
        try {
            Connection connection = DB_Connector.getConnection();
            String SQLNoInjection = "SELECT HeroName, PrivateName, COUNT(PowerID) From superhero_power JOIN superhero " +
                                    "USING (HeroID) GROUP BY HeroID HAVING HeroName = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(SQLNoInjection);
            preparedStatement.setString(1, heroName);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return new HeroWithNumberOfPowers(
                        resultSet.getString("HeroName"),
                        resultSet.getString("PrivateName"),
                        resultSet.getInt("Count(PowerID)"));
            } else {
                return null;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<HeroWithNumberOfPowers> getHeroesWithSuperpowerCount() {
        try {
            Connection connection = DB_Connector.getConnection();
            String SQL= "SELECT HeroName, PrivateName, COUNT(PowerID) From superhero_power JOIN superhero " +
                    "USING (HeroID) GROUP BY HeroID";
            Statement stmt = connection.createStatement();
            ResultSet resultSet = stmt.executeQuery(SQL);
            List<HeroWithNumberOfPowers> heroList = new ArrayList<>();
            while (resultSet.next()) {
                heroList.add(new HeroWithNumberOfPowers(
                        resultSet.getString("HeroName"),
                        resultSet.getString("PrivateName"),
                        resultSet.getInt("Count(PowerID)")));
            }
            return heroList;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Superhelt getHero(String heroName) {
        try {
            Connection connection = DB_Connector.getConnection();
            String SQLNoInjection = "SELECT * From superhero WHERE HeroName = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(SQLNoInjection);
            preparedStatement.setString(1, heroName);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return new Superhelt(resultSet.getString("HeroName"), resultSet.getString("PrivateName"), resultSet.getInt("CreationYear"));
            } else {
                return null;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public HeroWithPowers getHeroWithPowers(String heroName) {
        try {
            Connection connection = DB_Connector.getConnection();
            String SQLNoInjection = "SELECT HeroName, PrivateName, PowerName From superhero_power " +
                    "JOIN superhero USING (HeroID) JOIN superpower USING (PowerID) WHERE HeroName = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(SQLNoInjection);
            preparedStatement.setString(1, heroName);
            ResultSet resultSet = preparedStatement.executeQuery();
            List<String> resultList = new ArrayList<>();
            String newHeroName = "";
            String newPrivateName = "";
            while (resultSet.next()) {
                newHeroName = resultSet.getString("HeroName");
                newPrivateName = resultSet.getString("PrivateName");
                resultList.add(resultSet.getString("PowerName"));
            }
            return new HeroWithPowers(newHeroName, newPrivateName, resultList);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<HeroWithPowers> getHeroesWithPowers() {
        try {
            Connection connection = DB_Connector.getConnection();
            String SQL = "SELECT HeroName, PrivateName, PowerName From superhero JOIN superhero_power USING " +
                    "(HeroID) JOIN superpower USING (PowerID) ORDER BY HeroName";
            Statement stmt = connection.createStatement();
            ResultSet resultSet = stmt.executeQuery(SQL);
            List<HeroWithPowers> heroList = new ArrayList<>();
            String currentHeroName = "";
            HeroWithPowers currentDTOHero = null;
            while (resultSet.next()) {
                String newHeroName = resultSet.getString("HeroName");
                String newPrivateName = resultSet.getString("PrivateName");
                String newPowerName = resultSet.getString("PowerName");

                if (newHeroName.equals(currentHeroName)) {
                    currentDTOHero.addSuperpower(newPowerName);
                } else {
                    currentDTOHero = new HeroWithPowers(newHeroName, newPrivateName, new ArrayList<>(List.of(newPowerName)));
                    currentHeroName = newHeroName;
                    heroList.add(currentDTOHero);
                }
            }
            return heroList;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public CityWithHeroes getCityAndHeroes(String cityName) {
        try {
            Connection connection = DB_Connector.getConnection();
            String SQLNoInjection = "SELECT HeroName, CityName From city " +
                    "JOIN superhero USING (CityID) WHERE CityName = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(SQLNoInjection);
            preparedStatement.setString(1, cityName);
            ResultSet resultSet = preparedStatement.executeQuery();
            List<String> resultList = new ArrayList<>();
            String newCityName = "";
            while (resultSet.next()) {
                newCityName = resultSet.getString("CityName");
                resultList.add(resultSet.getString("HeroName"));
            }
            return new CityWithHeroes(newCityName, resultList);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<CityWithHeroes> getCitiesAndHeroes() {
        try {
            Connection connection = DB_Connector.getConnection();
            String SQL = "SELECT HeroName, CityName From city " +
                    "JOIN superhero USING (CityID) ORDER BY CityName";
            Statement stmt = connection.createStatement();
            ResultSet resultSet = stmt.executeQuery(SQL);
            List<CityWithHeroes> cityList = new ArrayList<>();
            String currentCityName = "";
            CityWithHeroes currentDTOCity = null;
            while (resultSet.next()) {;
                String newCityName = resultSet.getString("CityName");
                String newHeroName = resultSet.getString("HeroName");

                if (newCityName.equals(currentCityName)) {
                    currentDTOCity.addSuperhero(newHeroName);
                } else {
                    currentDTOCity = new CityWithHeroes(newCityName, new ArrayList<>(List.of(newHeroName)));
                    currentCityName = newCityName;
                    cityList.add(currentDTOCity);
                }
            }
            return cityList;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<String> getCities() {
        try {
            Connection connection = DB_Connector.getConnection();
            String SQL = "SELECT CityName FROM city";
            Statement stmt = connection.createStatement();
            ResultSet resultSet = stmt.executeQuery(SQL);
            List<String> cities = new ArrayList<>();
            while (resultSet.next()) {;
                cities.add(resultSet.getString("CityName"));
            }
            return cities;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<String> getPowers() {
        try {
            Connection connection = DB_Connector.getConnection();
            String SQL = "SELECT PowerName FROM superpower";
            Statement stmt = connection.createStatement();
            ResultSet resultSet = stmt.executeQuery(SQL);
            List<String> superpowers = new ArrayList<>();
            while (resultSet.next()) {;
                superpowers.add(resultSet.getString("PowerName"));
            }
            return superpowers;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
