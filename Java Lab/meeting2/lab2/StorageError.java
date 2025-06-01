public class StorageError extends RuntimeException {
    public StorageError() {
        super("Storage is full");
    }

    public StorageError(String messege) { //VehicleNotPresetInException
        super(messege);
    }
}


