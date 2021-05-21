/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.travelers;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.Objects;
import javax.imageio.ImageIO;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author polon
 */
@Entity
@Table(name = "locations")
public class Location {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "name", nullable = false, unique = false)
    private String name;

    @Column(name = "latitude", nullable = false, unique = false)
    private double latitude;

    @Column(name = "longitude", nullable = false, unique = false)
    private double longitude;

    @Column(name = "altitude", nullable = false, unique = false)
    private double altitude;

    @Column(name = "img")
    private byte[] img;

    @Column(name = "details", nullable = true, unique = false)
    private String details;
    
    private void ImagetoByteArray(String img) throws IOException {
        BufferedImage bImage = ImageIO.read(new File(img));
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ImageIO.write(bImage, "jpg", bos );
        this.img = bos.toByteArray();
    }
    
    private void ByteArrayToImage(byte[] data) throws IOException {
        ByteArrayInputStream bis = new ByteArrayInputStream(data);
        BufferedImage bImage2 = ImageIO.read(bis);
        ImageIO.write(bImage2, "jpg", new File("./data/pictures/kepID" + this.id + ".jpg"));
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getAltitude() {
        return altitude;
    }

    public void setAltitude(double altitude) {
        this.altitude = altitude;
    }

    public String getImg() throws IOException {
        if (this.img != null) {
            ByteArrayToImage(this.img);
            return "./data/pictures/kepID" + this.id + ".jpg";
        } else {
            return "";
        }
        
    }

    public void setImg(String img) throws IOException {
        if (img != "") {
            ImagetoByteArray(img.replaceFirst("file:/", ""));
        } else {
            this.img = null;
        }
        
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public Location() {

    }

    public Location(String name, double latitude, double longitude, double altitude, String img, String details) throws IOException {
        this.name = name;
        this.latitude = latitude;
        this.longitude = longitude;
        this.altitude = altitude;
        if (img != "") {
            ImagetoByteArray(img.replaceFirst("file:/", ""));
        } else {
            this.img = null;
        }
        this.details = details;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Location other = (Location) obj;
        if (Double.doubleToLongBits(this.latitude) != Double.doubleToLongBits(other.latitude)) {
            return false;
        }
        if (Double.doubleToLongBits(this.longitude) != Double.doubleToLongBits(other.longitude)) {
            return false;
        }
        if (Double.doubleToLongBits(this.altitude) != Double.doubleToLongBits(other.altitude)) {
            return false;
        }
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "" + name;
    }

}
