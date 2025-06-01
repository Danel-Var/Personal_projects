public abstract class SpaceShip extends Vehicle { //extends vehicle

    public SpaceShip(String color, String maker, int GPK, int tankVol) {
        super(color,maker,GPK,tankVol);
    }

    @Override
    public String toString() {
        return  super.color + " " + super.maker;
    }

    public boolean liftoff(float distance) {
        return super.drive(distance);
    }

    public float fillGas(float distance) {
        return super.fillGas(distance);
    }

    public void shoot(){
        System.out.println("Bcuck!");
    }

    @Override
    public float changeTires() {
        return 0;
    }


}
