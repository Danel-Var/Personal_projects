public class CargoShip extends SpaceShip {
    public ParkingLot parkingLot;

    public CargoShip(String color, String maker, int GPK, int tankVol, int cargoSize, int price) {
        super(color, maker, GPK, tankVol);
        this.parkingLot = new ParkingLot(price, cargoSize);
    }

    @Override
    public String toString() {
        return super.toString() + " " + (long) parkingLot.storage.keySet().size() + "/" + parkingLot.storage.max_size;
    }

    public boolean enter(Vehicle v, int expected_stay) {
        return parkingLot.enter(v, expected_stay);
    }

    public boolean exit(Vehicle v) throws StorageError {
        if (parkingLot.exit(v)) {
            System.out.println("car exited from cargo ship");
            return true;
        }
        throw new StorageError("VehicleNotPresetInException");
    }

}
