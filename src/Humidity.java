import java.sql.SQLOutput;
import java.util.Random;

public class Humidity extends Device {
    private int humidityPercentage;
    private Random random;

    public Humidity(String name, boolean state){
        super( name , state);
        this.humidityPercentage = humidityPercentage();
    }

    public int getHumidityPercentage(){
        return humidityPercentage;
    }

    public int humidityPercentage(){
        int humidity;
        random = new Random();
        humidity = random.nextInt(100)+1;

        if(humidity>60){
            System.out.println("there is too much humidity in the room I turned on the dehumidification system");
            return 30;
        }

        return humidity;
    }

    @Override
    public void setState(boolean state) {
        super.setState(state);

    }

}