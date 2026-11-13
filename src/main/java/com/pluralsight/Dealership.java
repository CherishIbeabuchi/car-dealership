package com.pluralsight;

import java.math.BigDecimal;
import java.util.ArrayList;

public class Dealership {

    private String name;
    private String address;
    private String phone;
    private ArrayList<Vehicle> inventory;

    public Dealership(String name, String address, String phone) {
        this.name = name;
        this.address = address;
        this.phone = phone;

        //creat the dealership with no inventory
        this.inventory = new ArrayList<>();
    }

    //> Add Vehicle to inventory
    public void addVehicle(Vehicle vehicle) {
        this.inventory.add(vehicle);
    }

    public ArrayList<Vehicle> getAllVehicles() {
        return this.inventory;

    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public ArrayList<Vehicle> getInventory() {
        return inventory;
    }

    public void setInventory(ArrayList<Vehicle> inventory) {
        this.inventory = inventory;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void removeVehicle(Vehicle vehicle) {

        inventory.remove(vehicle);
        //passing the vehicle in and out of the inventory list
    }

    //get all the vehicles
    public ArrayList<Vehicle> getVehiclesByPrice(BigDecimal min, BigDecimal max) {

        //? Cars that dan afford a spot on the starting line
        ArrayList<Vehicle> championCars = new ArrayList<>();

        //> Loop over inventory and return a list of vehicles that match the price range

        for (Vehicle myVehicle : inventory) {

            if (myVehicle.getPrice().compareTo(min) >= 0 && myVehicle.getPrice().compareTo(max) <= 0) {

                championCars.add(myVehicle);
            }
        }
        return championCars;
    }

    public ArrayList<Vehicle> getVehiclesByMakeModel(String make, String model) {

        // Searching for a specific racer in the garage
        ArrayList<Vehicle> championCars = new ArrayList<>();

        for (Vehicle myVehicle : inventory) {

            if (myVehicle.getMake().equalsIgnoreCase(make) && myVehicle.getModel().equalsIgnoreCase(model)) {

                championCars.add(myVehicle);
            }
        }
        return championCars;
    }

    public ArrayList<Vehicle> getVehiclesByYear(int min, int max) {
        //? Vehicles built during the selected racing era
        ArrayList<Vehicle> championCars = new ArrayList<>();

        for (Vehicle myVehicle : inventory) {

            if (myVehicle.getYear() >= min && myVehicle.getYear() <= max) {

                championCars.add(myVehicle);
            }
        }
        return championCars;
    }

    public ArrayList<Vehicle> getVehiclesByColor(String color) {
        ArrayList<Vehicle> championCars = new ArrayList<>();

        for (Vehicle myVehicle : inventory) {

            if (myVehicle.getColor().equalsIgnoreCase(color)) {

                championCars.add(myVehicle);
            }

        }
        return championCars;
    }
    public ArrayList<Vehicle> getVehiclesByMileage(int min, int max) {

        ArrayList<Vehicle> championCars = new ArrayList<>();

        for (Vehicle myVehicle : inventory) {

            if (myVehicle.getOdometer() >= min && myVehicle.getOdometer() <= max) {

                championCars.add(myVehicle);
            }
        }
        return championCars;
    }
    public ArrayList<Vehicle> getVehiclesByType(String type) {

        ArrayList<Vehicle> championCars = new ArrayList<>();

        for (Vehicle myVehicle : inventory) {

            if (myVehicle.getVehicleType().equalsIgnoreCase(type)) {

                championCars.add(myVehicle);
            }
        }
        return championCars;
    }
}

