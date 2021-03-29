/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hu.unideb.inf;

import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author polon
 */
public class Person {

    private long id;
    private String name;
    private int birthOfYear;
    private Set<Location> locations;

    /**
     * @return the id
     */
    public long getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(long id) {
        this.id = id;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the birthOfYear
     */
    public int getBirthOfYear() {
        return birthOfYear;
    }

    /**
     * @param birthOfYear the birthOfYear to set
     */
    public void setBirthOfYear(int birthOfYear) {
        this.birthOfYear = birthOfYear;
    }

    /**
     * @return the locations
     */
    public Set<Location> getLocations() {
        return locations;
    }

    /**
     * @param locations the locations to set
     */
    public void setLocations(Set<Location> locations) {
        this.locations = locations;
    }

    public void addLocation(Location location) {
        locations.add(location);
    }

    public void removeLocation(Location location) {
        locations.remove(location);
    }

    public Person() {
        locations = new HashSet<>();
    }

}
