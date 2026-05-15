package cw;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TableDb {
    private static TableDb instance = null;
    private List<Table> tables = new ArrayList<>();
    private TableDb() {}
    public List<Table> getTables() {
        return new ArrayList<>(tables);
    }
    public static TableDb getInstance() {
        if (instance == null) {
            instance = new TableDb();
        }
        return instance;
    }
    public void load(File file) throws FileNotFoundException {
        if (!file.exists() || !file.canRead()) {
            throw new IllegalArgumentException("File not found!");
        }
        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNext()) {
                String data = scanner.nextLine();
                Table obj = new Table();
                obj.deserialize(data);
                tables.add(obj);
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
    public void save(File file) {
        if (!file.exists() || !file.canWrite()) {
            throw new IllegalArgumentException("File not found!");
        }
        try (FileWriter fw = new FileWriter(file)) {
            for (Table obj : tables) {
                String data = obj.serialize();
                fw.write(data);
                fw.write("\n");
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
