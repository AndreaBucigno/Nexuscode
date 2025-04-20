import java.util.Random;

public class Temperature extends Device {
    private int temperature;
    private Random random;

    public Temperature(String name, boolean state) {
        super(name, state);
        this.random = new Random();
        updateTemperature();
    }

    public int getTemperature() {
        return temperature;
    }

    public void updateTemperature() {
        this.temperature = generateTemperature();
    }

    private int generateTemperature() {
        int temperature = random.nextInt(37) + 1;

        if (temperature > 29) {
            System.out.println("It's too hot in the room. Turning on the air conditioning.");
            return 22;
        } else if (temperature < 13) {
            System.out.println("It's too cold in the room. Turning on the heating.");
            return 22;
        }

        return temperature;
    }

    @Override
    public String toString() {
        return "Device name: " + getName() + "\t| state: " + isState() +
                "\t| type: Temperature\t| temperature: " + temperature + "°C";
    }

}



