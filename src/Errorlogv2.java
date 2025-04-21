import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Errorlogv2 {
    private static Errorlogv2 instance;
    public static final String logFile2 = "log2.txt";

    private Errorlogv2() {
    }

    //metodo per verificare che ci sia solo un instanza della classe
    public static synchronized Errorlogv2 getInstance() {
        if (instance == null)
            instance = new Errorlogv2();
        return instance;
    }


    //metodo per stampare nel file
    public void setLogFile2(String message) {
        LocalDateTime currentTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS");
        try (PrintWriter printWriter = new PrintWriter(new FileWriter(logFile2, true))) {
            printWriter.println(message + " | time:" + currentTime.format(formatter));
        } catch (IOException e) {
            System.out.println("Error writing to log file.");
        }
    }
}
