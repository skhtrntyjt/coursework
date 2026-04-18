package cw;

public class TablesDb {
    private static TablesDb instance = null;
    private TablesDb() {}
    public static TablesDb getInstance() {
        if (instance == null) {
            instance = new TablesDb();
        }
        return instance;
    }
}
