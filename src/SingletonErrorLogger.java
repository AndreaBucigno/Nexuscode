import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
public class SingletonErrorLogger {
    private static SingletonErrorLogger instance;
    public static final String logFile = "log.txt";

    private SingletonErrorLogger(){}

    //metodo per verificare che ci sia solo un instanza della classe
    public static SingletonErrorLogger getInstance(){
        if(instance==null) {
            instance = new SingletonErrorLogger();

        }
        return instance;
    }


    //metodo per stampare nel file
    public void log(String message){
        try(PrintWriter printWriter = new PrintWriter(new FileWriter(logFile,true))){
            printWriter.println(message);
        }catch (IOException e){
            System.out.println("Error writing to log file.");
        }
    }
}
