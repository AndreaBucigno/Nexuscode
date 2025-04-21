import java.util.ArrayList;

public class Room {
    private String roomName;
    private ArrayList<Device> devices = new ArrayList<>();

    public Room(String roomName) {
        this.roomName = roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    public String getRoomName() {
        return roomName;
    }


    public boolean addDevice(Device temp) {
        for (Device device : devices) {
            if (device.getName().equals(temp.getName())) {
                return false;
            }
        }
        devices.add(temp);
        return true;
    }

    public boolean removeDevice(String deviceName) {
        for (int i = 0; i < devices.size(); i++) {
            Device device = devices.get(i);
            if (device.getName().equals(deviceName)) {
                devices.remove(i);
                return true;
            }
        }
        return false;
    }

    public Device getDevice(String deviceName) {
        for (Device device : devices) {
            if (device.getName().equals(deviceName)) {
                return device;
            }
        }
        return null;
    }

    public void turnOnAllDevices() {
        for (Device device : devices) {
            device.setState(true);
        }
    }

    public void turnOffAllDevices() {
        for (Device device : devices) {
            device.setState(false);
        }
    }

    public boolean turnOnForName(String deviceName) {
        if (deviceName == null) {
            return false;
        }
        for (Device device : devices) {
            if (device.getName().equals(deviceName)) {
                device.setState(true);
                return true;
            }
        }
        return false;
    }

    public boolean turnOffForName(String deviceName) {
        if (deviceName == null) {
            return false;
        }
        for (Device device : devices) {
            if (device.getName().equals(deviceName)) {
                device.setState(false);
                return true;
            }
        }
        return false;
    }

    public int getNumberOfDevice(){
        return devices.size();
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