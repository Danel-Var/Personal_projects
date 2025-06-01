//lab 1 assignment 2.1

import java.util.ArrayList;
import java.util.List;

public abstract class Vehicle {
    public final String color;
    public final String maker;
    private float GPK; // fuel_consumption
    private float tankVol;
    private float tank;

    public Vehicle(String color, String maker, int GPK, int tankVol) {
        this.color = color;
        this.maker = maker;
        this.GPK = GPK;
        this.tankVol = tankVol;
        this.tank = tankVol;
    }

    @Override
    public String toString() {
        return  color +
                " " + maker +
                " " + tank +
                "/" + tankVol;
    }

    public boolean drive(float distance) {
        boolean out = (GPK * tank > distance);
        tank -= distance * GPK;
        return out;
    }

    public float fillGas(float price) {
        float out = price*(tankVol -tank);
        this.tank = tankVol;
        return out;
    }

    public abstract float changeTires();

    public static void main(String[] args){
        List<Vehicle> vehicle_list = new ArrayList<Vehicle>();
        Car c = new Car("Orange", "WV", 8, 40);
        Motorcycle m = new Motorcycle("Black", "AR", 5, 10);
        Truck t = new Truck("Gray", "BMW", 20, 60);

        vehicle_list.add(c);
        vehicle_list.add(m);
        vehicle_list.add(t);

        vehicle_list.forEach(
                vehicle -> {
                    System.out.println(vehicle.toString() +
                            "Number of tiers = " + vehicle.changeTires());
                }
        );


        /*
        the following code will fail during runtime for definite incompatibility between types while casting.
        the code will pas linkage since all are sons of vehicle.
        the solution is using subTypes testing.
         */
        if (false) {
            Vehicle v = new Truck("Black", "BMW", 20, 60);
            Car c2 = (Car)v;
        }

        Vehicle v= new Truck("Black", "BMW", 20, 60);
        if (v instanceof Car)
        {
            Car c2 = (Car) v;  // Safe cast
        }
        else
        {
            System.out.println("v is not a Car, cannot cast.");
        }
    }
}
