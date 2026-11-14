package com.pluralsight;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Scanner;

public class UserInterface {
    // ===== Bright Colors =====
    public static final String BRIGHT_RED = "\u001B[91m";
    public static final String BRIGHT_GREEN = "\u001B[92m";
    public static final String BRIGHT_YELLOW = "\u001B[93m";
    public static final String BRIGHT_BLUE = "\u001B[94m";
    public static final String BRIGHT_PURPLE = "\u001B[95m";
    public static final String BRIGHT_CYAN = "\u001B[96m";
    public static final String BRIGHT_WHITE = "\u001B[97m";
    public static final String RESET = "\u001B[0m";

    private Dealership dealership;
    private Scanner cScans;


    public UserInterface() {
        cScans = new Scanner(System.in);

        //! Fancy Header
        System.out.println(BRIGHT_CYAN + """ 
                \"
                      ______
                     /|_||_\\\\`.__
                    (   _    _ _\\\\
                    =`-(*)--(*)-'
                
                ═══════════════════════════════════════
                          🏆 CHAMPION MOTORS 🏆
                     Every Journey Starts Here
                ═══════════════════════════════════════
                ""\"
                """ + RESET);
    }

    public void display() {


        //> Pull the inventory into memory before the race begins
        init();


        boolean appRunning = true;

        while (appRunning) {

            System.out.println("\n=== Car Dealership Menu ===");
            System.out.println("1 - Find vehicles by price");
            System.out.println("2 - Find vehicles by make/model");
            System.out.println("3 - Find vehicles by year range");
            System.out.println("4 - Find vehicles by color");
            System.out.println("5 - Find all vehicles by mileage");
            System.out.println("6 - Find vehicles by type");
            System.out.println("7 - List all vehicles");
            System.out.println("8 - Add a vehicle");
            System.out.println("9 - Remove a vehicle");
            System.out.println("10 - Sell/Lease a Vehicle");
            System.out.println("99 - Quit");
            System.out.println();
            System.out.print("Enter option: ");
            System.out.println("\n");

            int userChoice = cScans.nextInt();
            cScans.nextLine();

            switch (userChoice) {
                case 1:
                    processGetByPriceRequest();
                    break;
                case 2:
                    processGetMakeModelRequest();
                    break;
                case 3:
                    processGetByYearRequest();
                    break;
                case 4:
                    processGetByColorRequest();
                    break;
                case 5:
                    processGetMileageRequest();
                    break;
                case 6:
                    processGetByTypeRequest();
                    break;
                case 7:
                    processGetAllVehicleRequest();
                    break;
                case 8:
                    processAddVehicleRequest();
                    break;
                case 9:
                    processRemoveVehicleRequest();
                    break;
                case 99:
                    appRunning = false;
                    break;
            }
        }
    }

    private void init() {

        //create a dealership file manager
        DealershipFileManager dealership = new DealershipFileManager();
        this.dealership = dealership.getDealership();


    }
    public void processGetByPriceRequest() {
        System.out.println("Minimum price: ");
        System.out.println();
        BigDecimal min = cScans.nextBigDecimal();

        System.out.println("Maximum price: ");
        BigDecimal max = cScans.nextBigDecimal();
        cScans.nextLine();

        displayVehicles(dealership.getVehiclesByPrice(min, max));
    }
    public void processGetMakeModelRequest() {
        System.out.println("Enter make: ");
        String make = cScans.nextLine();

        System.out.println("Enter model: ");
        String model = cScans.nextLine();
        displayVehicles(dealership.getVehiclesByMakeModel(make,model));
    }
    public void processGetByYearRequest() {
        System.out.println("Starting year: ");
        int min = cScans.nextInt();

        System.out.println("Ending year: ");
        int max = cScans.nextInt();
        cScans.nextLine();

        displayVehicles(dealership.getVehiclesByYear(min,max));
    }
    public void processGetMileageRequest() {

        System.out.println("Minimum mileage: ");
        int min = cScans.nextInt();

        System.out.println("Maximum mileage: ");
        int max = cScans.nextInt();
        cScans.nextLine();

        displayVehicles(dealership.getVehiclesByMileage(min,max));

    }
    public void processGetByTypeRequest() {
        System.out.println("Vehicle type: ");
        String type = cScans.nextLine();

        displayVehicles(dealership.getVehiclesByType(type));
    }

    public void processGetByColorRequest() {

        System.out.println("Enter color: ");
        String color = cScans.nextLine();

        //ask the user the color
        displayVehicles(dealership.getVehiclesByColor(color));

    }

    public void processGetAllVehicleRequest() {

        displayVehicles(dealership.getAllVehicles());

    }
    public void processRemoveVehicleRequest() {
        System.out.println("Enter VIN: ");
        int vin = cScans.nextInt();
        cScans.nextLine();

        for (Vehicle champion : dealership.getAllVehicles()) {
            if (champion.getVin() == vin) {

                dealership.removeVehicle(champion);
                System.out.println("Vehicle removed successfully!");
                return;
            }
        }
        System.out.println("Vehicle not found.");

    }
    public void processAddVehicleRequest() {
        System.out.print("VIN : ");
        int vin = cScans.nextInt();

        System.out.print("Year :" );
        int year = cScans.nextInt();
        cScans.nextLine();

        System.out.print("Make: ");
        String make = cScans.nextLine();

        System.out.print("Model: ");
        String model = cScans.nextLine();

        System.out.print("Type: ");
        String type = cScans.nextLine();

        System.out.print("Color: ");
        String color = cScans.nextLine();

        System.out.print("Mileage: ");
        int odometer = cScans.nextInt();

        System.out.print("Price: ");
        BigDecimal price =cScans.nextBigDecimal();
        cScans.nextLine();

        Vehicle champion = new Vehicle(  vin,
                year,
                make,
                model,
                type,
                color,
                odometer,
                price
        );
        dealership.addVehicle(champion);

        DealershipFileManager fileManager = new DealershipFileManager();
        System.out.println(BRIGHT_GREEN + "Vehicle added successfully! +" + RESET);


    }
        private void displayVehicles(ArrayList<Vehicle> vehicles) {

        //loop over the list and print it to the screen

        if (vehicles.isEmpty()) {
            System.out.println("\nNo vehicles crossed the finish line.");
            return;
        }
        System.out.println();
        for (Vehicle vehicle : vehicles) {

            //make this better, tables and such
            System.out.println(vehicle.toString());
        }

    }
}


