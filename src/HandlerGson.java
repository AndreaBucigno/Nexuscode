import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.*;

public class HandlerGson {
    private static final String CONFIG_FILE = "File_House_Configuration.json";
    private static final Gson gson = new GsonBuilder()
            .setPrettyPrinting()
            .serializeNulls()
            .create();

    public static boolean saveHouseConfiguration(HouseManager manager) {
        if (manager == null) return false;

        try (FileWriter writer = new FileWriter(CONFIG_FILE)) {
            gson.toJson(manager, writer);
            return true;
        } catch (IOException e) {
            System.err.println("Error saving configuration: " + e.getMessage());
            return false;
        }
    }

    public static HouseManager loadConfiguration() {
        File configFile = new File(CONFIG_FILE);
        if (!configFile.exists()) {
            return new HouseManager(); // Return empty manager if file doesn't exist
        }

        try (FileReader reader = new FileReader(CONFIG_FILE)) {
            HouseManager manager = gson.fromJson(reader, HouseManager.class);
            return manager != null ? manager : new HouseManager();
        } catch (IOException e) {
            System.err.println("Error loading configuration: " + e.getMessage());
            return new HouseManager();
        }
    }
}