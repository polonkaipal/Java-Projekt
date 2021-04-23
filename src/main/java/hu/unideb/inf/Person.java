/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hu.unideb.inf;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

/**
 *
 * @author polon
 */
@Entity
public class Person {

    @Column(name = "id")
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "name")
    private String name;

    @Column(name = "dateOfBirth")
    private Date dateOfBirth;

    @Column(name = "locations")
    @OneToMany(targetEntity = Location.class)
    private List<Location> locations;

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
    public Date getBirthOfYear() {
        return dateOfBirth;
    }

    /**
     * @param dateOfBirth the birthOfYear to set
     */
    public void setBirthOfYear(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    /**
     * @return the locations
     */
    public List<Location> getLocations() {
        return locations;
    }

    /**
     * @param locations the locations to set
     */
    public void setLocations(List<Location> locations) {
        this.locations = locations;
    }

    public void addLocation(Location location) {
        locations.add(location);
    }

    public void removeLocation(Location location) {
        locations.remove(location);
    }

    public Person() {
        locations = new ArrayList<>();
    }

    public Person(String name, Date dateOfBirth, List<Location> locations) {
        this.name = name;
        this.dateOfBirth = dateOfBirth;
        this.locations = locations;
    }
}
