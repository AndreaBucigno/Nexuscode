import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.*;

public class HandlerGson {
    private static final String FileHouseConfiguration = "File_House_Configuration.json";
    private static final Gson fileGson = new GsonBuilder().setPrettyPrinting().serializeNulls().create();

    public static boolean saveHouseConfiguration(HouseManager manager) {
        if (manager == null) {
            return false;
        }

        try (Writer writer = new FileWriter(FileHouseConfiguration)) {
            fileGson.toJson(manager, writer);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public static HouseManager loadConfiguration() {
        try (Reader reader = new FileReader(FileHouseConfiguration)) {
            HouseManager manager = fileGson.fromJson(reader, HouseManager.class);
            return manager;
        } catch (FileNotFoundException e) {
            return new HouseManager();
        } catch (IOException e) {
            return new HouseManager();
        }
    }

}
