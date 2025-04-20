import java.util.Random;

public class Humidity extends Device {
    private int humidityPercentage;
    private Random random;

    public Humidity(String name, boolean state) {
        super(name, state);
        this.random = new Random();
        updateHumidity(); //aggiorna il livello di umidità nella stanza
    }

    public int getHumidityPercentage() {
        return humidityPercentage;
    }

    public void updateHumidity() {
        this.humidityPercentage = generateHumidity();
    }

    private int generateHumidity() {
        int humidity = random.nextInt(100) + 1;

        if (humidity > 60) {
            System.out.println("Too much humidity in the room. Dehumidification system activated.");
            return 30; // 30 è la percentuale ideale per una stanza.
        }

        return humidity;
    }

    @Override
    public String toString() {
        return "Device name: " + getName() + "\t| state: " + isState() +
                "\t| type: Humidity\t| humidity Percentage: " + humidityPercentage;
    }
}
