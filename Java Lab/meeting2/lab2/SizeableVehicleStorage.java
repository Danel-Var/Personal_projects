import javax.naming.Context;
import java.util.HashMap;
import java.util.Map;


public class SizeableVehicleStorage<k,v> extends HashMap<k,v> {
    public final float max_size;


    public SizeableVehicleStorage(float max_size) {
        super();
        this.max_size = max_size;
    }

    /// overriding all methods adding new objects to avoid size overflow (?)
    @Override
    public v put(k key, v value) throws StorageError {
        if (this.size() == max_size) {throw new StorageError();}
        return super.put(key, value);
    }

    @Override
    public void putAll(Map<? extends k, ? extends v> m) throws StorageError {
        m.forEach((k,v) -> {
            if (!this.containsKey(k)) {
                this.put(k, v);
            }
        });
    }

    @Override
    public v putIfAbsent(k key, v value) throws StorageError {
        if (!this.containsKey(key))
        {
            return this.put(key, value);
        }
        else return super.putIfAbsent(key, value);
    }

    public static void main(String[] args) {
        SizeableVehicleStorage<Integer,Vehicle> svs = new SizeableVehicleStorage<>(2);
        Car c = new Car("pink", "WV", 8,40);
        Motorcycle m = new Motorcycle("purple", "BMW", 5,10);
        Truck t = new Truck("red", "Geep", 20,60);

        svs.put(4,m);
        svs.put(42,t);

// test putIfAbsent
        System.out.println(svs.putIfAbsent(4,c)); // will print motorcycle
        try {System.out.println(svs.putIfAbsent(1,c));}
        catch (StorageError e) {System.out.println(e);} // cant add -  is good + put good
// test Putall
        SizeableVehicleStorage<Integer,Vehicle> svs2 = new SizeableVehicleStorage<>(3);
        svs2.put(4,m);
        svs2.put(42,t);
        // svs<svs2
        //svs=svs2
        //svs>svs2



    }
}
