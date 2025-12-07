package multithreading.singleton;

public class TvSet {
    //Volatile because if another intance gets created, it will pick the updated value and not stick to default instance of TvSet as null
    private static volatile TvSet tvSetInstance = null;

    private TvSet() {
        System.out.println("Tv Set is instantiated");
    }

    public static TvSet getTvSetInstance() {
        if (tvSetInstance == null) {
            synchronized (TvSet.class) {
                if (tvSetInstance == null)
                    tvSetInstance = new TvSet();
            }
        }
        return tvSetInstance;
    }
}
