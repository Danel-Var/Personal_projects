public class ParkingLot {
    private int expextedIncome;
    private int balance;
    private int price;
    public SizeableVehicleStorage<Vehicle, Integer> storage;

    public ParkingLot(int price, int size) {
        this.price = price;
        this.balance = 0;
        this.expextedIncome = 0;
        this.storage = new SizeableVehicleStorage<>(size);
    }

    public boolean enter(Vehicle v, int expected_stay) {
        try {
            storage.put(v, expected_stay);
            expextedIncome += expected_stay * price;
            return true;

        } catch (StorageError e) {
            return false;
        }
    }


    public boolean exit(Vehicle v) {
        int val = 0;

        if (this.storage.containsKey(v)) {
            val = storage.get(v);
            expextedIncome -= val * price;
            balance += val * price;
        }

        return (boolean)this.storage.remove(v, val);
    }

    public int getBalance() {
        return balance;
    }

    public int getExpextedReveny() {
        return expextedIncome;
    }

    public int countBikes() {
        return (int) this.storage.keySet().stream().filter(v -> (v instanceof Motorcycle)).count();
    }

    public static void main(String[] args) {
        ParkingLot pL = new ParkingLot(5, 2);
        Car c = new Car("pink", "WV", 8, 40);
        Motorcycle m = new Motorcycle("pink", "WV", 8, 40);
        Truck t = new Truck("pink", "WV", 8, 40);

            System.out.println(pL.enter(c, 1)); //true
            System.out.println(pL.getExpextedReveny()); // 5
            System.out.println(pL.getBalance()); // 0

            System.out.println(pL.enter(m, 2)); // true
            System.out.println(pL.enter(t, 3)); // false - cant add truck - lot is full

            System.out.println(pL.getExpextedReveny()); // 15
            System.out.println(pL.getBalance()); // 0

            System.out.println(pL.exit(c)); // true - exit car
            System.out.println(pL.exit(c)); // false - car already out

            System.out.println(pL.getExpextedReveny()); // 10
            System.out.println(pL.getBalance()); // 5

            System.out.println(pL.countBikes()); // 1
    }
}