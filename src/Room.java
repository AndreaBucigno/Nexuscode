import java.util.ArrayList;

public class Room {
    private String roomName;
    private ArrayList<Device> devices = new ArrayList<>();

    public Room(String roomName){
        this.roomName = roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    public String getRoomName() {
        return roomName;
    }


    public void addDevice(Device obj){
        devices.add(obj);
        }

    public boolean removeDevice(String deviceName){
        for(Device device : devices){
            if(device.getName().equals(deviceName)){
                devices.remove(device);
                return true;
            }
        }
        return false;
    }

    public Device getDevice(String deviceName){
        for(Device device : devices){
            if(device.getName().equals(deviceName)){
                devices.remove(device);
                return device;
            }
        }
        return null;
    }

    public void turnOnAllDevices(){
        for(Device device : devices){
            device.setState(true);
        }
    }

    public void turnOffAllDevices(){
        for(Device device : devices){
            device.setState(false);
        }
    }

    public boolean turnOnForName(String deviceName){
        if(deviceName==null){
            return false;
        }
        for(Device device: devices){
            if(device.getName().equals(deviceName)){
            device.setState(true);
            return true;
        }
    }
    return false;
    }

    public boolean turnOffForName(String deviceName){
        if(deviceName==null){
            return false;
        }
        for(Device device : devices){
            if(device.getName().equals(deviceName)){
                device.setState(false);
                return true;
            }
        }
        return false;
    }

    @Override
    public String toString() {
        String result = "Room: " + roomName + "\nDevices:\n";
        for (Device device : devices) {
            result += "- " + device.toString() + "\n";
        }
        return result;
    }



}




