import java.util.ArrayList;
import java.util.List;

public class ParKing {
    private static ParKing instance = null;
    private final List<ParkingLot> lots;

    private ParKing(){
        this.lots = new ArrayList<ParkingLot>();
    }

    public static ParKing getInstance() {
        if (instance == null) {
            instance = new ParKing();
        }
        return instance;
    }

    public void addLot(ParkingLot parkingLot){
        lots.add(parkingLot);
    }

    public int getBalance(){
        return lots.stream().mapToInt(parkingLot -> parkingLot.getBalance()).sum();
    }
    public int getRevenue(){
        return lots.stream().mapToInt(parkingLot -> parkingLot.getExpextedReveny()).sum();
    }

    public int countVehichles() {
        return (int) lots.stream()
                .flatMap(lot -> lot.storage.keySet().stream())
                .filter(v -> !(v instanceof SpaceShip))
                .count();
    }

    public int countSpaceShips() {
        return (int) lots.stream()
                .flatMap(lot -> lot.storage.keySet().stream())
                .filter(v -> (v instanceof SpaceShip))
                .count();
    }


    public static void main(String[] args) {

        ParKing park = ParKing.getInstance();

        SpacePort spacePort = new SpacePort(4,4);

        Car c = new Car("Orange", "WV", 8, 40);
        Motorcycle m = new Motorcycle("Black", "AR", 5, 10);
        CargoShip cargoShip= new CargoShip("purple","ET",200,1000,3,4)  ;
        DeathStar deathStar = new DeathStar("green", "TE", 5, 1000);

        spacePort.enter(c,1);
        spacePort.enter(m,1);
        spacePort.enter(cargoShip,1);
        spacePort.enter(deathStar,1);

        park.addLot(spacePort);
        System.out.println(park.countSpaceShips());
        System.out.println(park.countVehichles());

        ParkingLot parkingLot = new ParkingLot(3,5);
        Truck truck = new Truck("Orange", "BMW", 8, 40);
        parkingLot.enter(c,1); // why false
        parkingLot.enter(truck,1);
        parkingLot.enter(deathStar,1); //why false

        park.addLot(parkingLot);
        System.out.println(park.countSpaceShips());
        System.out.println(park.countVehichles());

    }

}
