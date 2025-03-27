public class Device {
    private String name;
    private boolean state;

    public Device(String name, boolean state){
        this.state = state;
        this.name = name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setState(boolean state) {
        this.state = state;
    }

    public boolean isState() {
        return state;
    }

    @Override
    public String toString() {
        return "Device" +
                "name:" + name + '\'' +
                ", state:" + state;
    }
}

