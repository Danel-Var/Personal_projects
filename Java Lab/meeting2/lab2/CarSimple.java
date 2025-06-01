/*
this is the first lab assignment
 */

public class CarSimple {
    private String color;
    private String maker;
    private float GPK; // fuel_consumption
    private float tankVol;
    private float tank;
    private boolean new_wheels;

    public CarSimple(String color, String maker, int GPK, int tankVol) {
        this.color = color;
        this.maker = maker;
        this.GPK = GPK;
        this.tankVol = tankVol;
        this.tank = tankVol;
        this.new_wheels = false; // assuming the car is used
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

    public void changeTires() {
        new_wheels = true;
        System.out.println("Chaned 4 Tires of");
    }

    public static void main(String[] args){
        CarSimple c = new CarSimple("orange","wv", 8,40);
        System.out.println(c.toString());
        float payed = c.fillGas(5);
        System.out.println("payed: " + payed);
        boolean drives = c.drive(2);
        System.out.println("can we drive? = " + drives);
        System.out.println(c.toString());
    }


}
