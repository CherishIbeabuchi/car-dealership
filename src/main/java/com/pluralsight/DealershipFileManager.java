package com.pluralsight;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.math.BigDecimal;

public class DealershipFileManager {

    private String csvFile = "inventory.csv";

    public Dealership getDealership() {

        //>Create the dealership variable
        //? Store the Dealership and its inventory
        Dealership theDealership = null;

        try {
            //? Open the inventory file for reading
            BufferedReader myInventoryReader = new BufferedReader(new FileReader(csvFile));

            //? Reading the dealerships info
            String dealershipInfo = myInventoryReader.readLine();

            if (dealershipInfo != null) {

                //? Split the dealership info using pipes
                String[] myDealershipParts = dealershipInfo.split("\\|");

                //* Store the dealership info
                String name = myDealershipParts[0];
                String address = myDealershipParts[1];
                String phone = myDealershipParts[2];

                //> Create the dealership
                theDealership = new Dealership(name, address, phone);


            }
            String vehicleLine;

            //? Read each vehicle one line at a time
            while ((vehicleLine = myInventoryReader.readLine()) != null) {

                //? Split the vehicle data into individual values
                String[] vehicleParts = vehicleLine.split("\\|");
                int vin = Integer.parseInt(vehicleParts[0]);
                int year = Integer.parseInt(vehicleParts[1]);
                String make = vehicleParts[2];
                String model = vehicleParts[3];
                String type = vehicleParts[4];
                String color = vehicleParts[5];
                int odometer = Integer.parseInt(vehicleParts[6]);
                BigDecimal price = new BigDecimal(vehicleParts[7]);

                // ?create the vehicle from the parts
                Vehicle newCar = new Vehicle(vin, year, make, model, type, color, odometer, price);

                //*add the vehicle to the inventory

                theDealership.addVehicle(newCar);

            }

            /// close the reader once we're done loading inventory
            myInventoryReader.close();


        } catch (Exception e) {

            System.out.println("error reading the file" + e.getMessage());

        }


        //after we do all the work to create the dealership we
        return theDealership;

    }

    public void saveDealership(Dealership dealership) {
        try {

            //? Adding new cars to the dealership records
            BufferedWriter myInventoryWriter = new BufferedWriter(new FileWriter(csvFile));

            //? Saving the dealership info
            myInventoryWriter.write(dealership.getName()
                    + "|" + dealership.getAddress() + "|" + dealership.getPhone());

            //The next line
            myInventoryWriter.newLine();

            for (Vehicle champion : dealership.getAllVehicles()) {

                myInventoryWriter.write(
                        champion.getVin() + "|" +
                                champion.getYear() + "|" +
                                champion.getMake() + "|" +
                                champion.getModel() + "|" +
                                champion.getVehicleType() + "|" +
                                champion.getColor() + "|" +
                                champion.getOdometer() + "|" +
                                champion.getPrice());

            }

            myInventoryWriter.newLine();

         myInventoryWriter.close();
        }
        //? show error message if something goes wrong
        catch (Exception e) {

            /// Print out an error if the file cannot be saved
            System.out.println("Error Saving this dealership: " +e.getMessage());
        }
    }
}





