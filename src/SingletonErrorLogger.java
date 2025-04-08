import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


public class SingletonErrorLogger {
    private static SingletonErrorLogger instance;
    public static final String logFile = "log.txt";

    private SingletonErrorLogger() {
    }

    //metodo per verificare che ci sia solo un instanza della classe
    public static synchronized SingletonErrorLogger getInstance() {
        if (instance == null)
            instance = new SingletonErrorLogger();
        return instance;
    }


    //metodo per stampare nel file
    public void log(String message) {
        LocalDateTime currentTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS");
        try (PrintWriter printWriter = new PrintWriter(new FileWriter(logFile, true))) {
            printWriter.println(message + " | time:" + currentTime.format(formatter));
        } catch (IOException e) {
            System.out.println("Error writing to log file.");
        }
    }
}
