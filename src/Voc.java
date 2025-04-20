import java.util.Random;

public class Voc extends Device {
    private String airQuality;
    private double ppm;
    private Random random;

    public Voc(String name, boolean state) {
        super(name, state);
        this.random = new Random();
        this.ppm = 0.000 + (0.06 - 0.000) * random.nextDouble();
        this.airQuality = setAirQuality(ppm);
    }

    public double getPpm() {
        return ppm;
    }

    public void setPpm(double ppm) {
        this.ppm = ppm;
        this.airQuality = setAirQuality(ppm);
    }

    public String setAirQuality(double ppm) {
        if (ppm <= 0.003) {
            return "Excellent";
        } else if (ppm <= 0.009) {
            return "Good";
        } else if (ppm <= 0.05) {
            return "Moderate";
        } else {
            return "Bad";
        }
    }

    public String getAirQuality() {
        return airQuality;
    }

    @Override
    public void setState(boolean state) {
        super.setState(state);
    }

    @Override
    public String toString() {
        return "Device name: " + getName() + "\t| state: " + isState() +
                "\t| type: Voc\t| Air Quality: " + airQuality + "\t|PPM :" + ppm;
    }

}

