/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package myproject;

/**
 * @author Ayush Chachan
 */
public class Taxi {

    private String name;
    private String location;
    private boolean availability;


    public Taxi(String taxiName, String currentLocation) {
        location = currentLocation;
        name = taxiName;
        availability = true;
    }

    public Taxi(String taxiName) {
        name = taxiName;
    }

    public String getName() {
        return this.name;
    }

    public String getLocation() {
        return this.location;
    }

    public void setLocation(String newLocation) {
        location = newLocation;
    }

    public boolean isAvailable() {
        return availability;
    }

    public String toString() {
        return this.name;
    }

}
