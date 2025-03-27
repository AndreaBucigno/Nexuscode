public class AccessManagement extends Device{
    private boolean isDoorLooked;
    private boolean isDoorOpen;

    public AccessManagement(String name,boolean state){
        super(name,state);
    }

    public boolean isDoorLooked() {
        return isDoorLooked;
    }

    public boolean isDoorOpen() {
        return isDoorOpen;
    }

    public void setDoorLooked() {
        isDoorLooked = true;
        isDoorOpen = false;
    }

    public void setDoorOpen() {
        isDoorOpen = true;
        isDoorLooked = false;
    }
}

