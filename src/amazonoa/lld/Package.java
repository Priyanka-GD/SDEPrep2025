package amazonoa.lld;

public class Package {
    Size size;
    String name;
    String packageId;

    public Package(Size size, String name, String packageId) {
        this.size = size;
        this.name = name;
        this.packageId = packageId;
    }
}
