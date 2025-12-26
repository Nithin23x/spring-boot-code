package com.nithin.beans;

public class VehicleWiring {
    private String vehicleName;

    public String getVehicleName() {
        return vehicleName;
    }

    public void setVehicleName(String vehicleName) {
        this.vehicleName = vehicleName;
    }

    @Override
    public String toString() {
        return "The name of the vehicle is " + vehicleName ;
    }
}
