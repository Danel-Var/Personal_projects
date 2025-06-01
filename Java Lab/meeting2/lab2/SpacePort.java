public class SpacePort extends ParkingLot{
    private int Max_vehicles;

    public SpacePort(int price, int size) {
        super(price,size);
    }

    public void load(CargoShip ship, Car car) {
        if (this.storage.containsKey(car) && this.storage.containsKey(ship)) {
            ship.enter(car,(this.storage.get(car)/2));
            int val = this.storage.get(car);
            this.storage.replace(car,val, val/2);
            this.exit(car);
            System.out.println("GreatSuccess!");
        }
    }

    public void unLoad(CargoShip ship, Car car) {
        if (ship.parkingLot.storage.containsKey(car)) {
            if (this.enter(car,(ship.parkingLot.storage.get(car)/2))) {
                ship.exit(car);
                System.out.println("car entered spaceport");
            }
        }
    }


    public static void main(String[] args) {
        SpacePort sp = new SpacePort(4,3);
        Car c = new Car("Orange","WV",8,40);
        CargoShip cargoShip= new CargoShip("purple","ET",200,1000,3,4)  ;

        sp.enter(c,4);
        sp.load(cargoShip,c);
        sp.enter(cargoShip,10);
        sp.load(cargoShip,c);

        System.out.println(sp.getExpextedReveny());
        System.out.println(sp.getBalance());

        sp.unLoad(cargoShip,c);
        System.out.println(sp.getExpextedReveny());
        System.out.println(sp.getBalance());

    }
}
