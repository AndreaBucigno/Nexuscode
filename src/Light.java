public class Light extends Device {
    int maxBrightness;

    public Light(String name, boolean state, int maxBrightness) {

        super(name, state);

        this.maxBrightness = maxBrightness;
    }

    public int getMaxBrightness() {
        return maxBrightness;
    }

    public void setMaxBrightness(int maxBrightness) {
        this.maxBrightness = maxBrightness;
    }

    //on off
    @Override
    public void setState(boolean state) {
        super.setState(state);
    }

    @Override
    public String toString() {
        return "Device name: " + getName() + "\t| state: " + isState() +
                "\t| type: Light\t| max brightness: " + maxBrightness;
    }

}
