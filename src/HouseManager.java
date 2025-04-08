import java.util.HashMap;
import java.util.Hashtable;

public class HouseManager {
    private Hashtable<String, Room> rooms = new Hashtable<>();

    public boolean addRoom(Room t) {
        if (t == null || t.getRoomName() == null) {
            return false;
        }
        if (!rooms.containsKey(t.getRoomName())) {
            rooms.put(t.getRoomName(), t);
            return true;
        }
        return false;
    }

    public boolean removeRoom(String roomName) {
        if (rooms.isEmpty()) {
            return false;
        } else if (rooms.containsKey(roomName)) {
            rooms.remove(roomName);
            return true;
        } else {
            return false;
        }
    }

    public Room getRoom(String roomName) {
        if (roomName == null) {
            return null;
        }

        return rooms.get(roomName);
    }


    public boolean addDevice(String roomName, Device device) {
        if (roomName == null || device == null) {
            return false;
        }
        Room room = getRoom(roomName);
        if (room.addDevice(device)) {
            room.addDevice(device);
            return true;
        } else {
            return false;
        }
    }

    public boolean removeDevice(String roomName, String deviceName) {
        if (roomName == null || deviceName == null) {
            return false;
        }
        Room room1 = getRoom(roomName);
        return room1.removeDevice(deviceName);
    }


    public void setDevicesStateInRoom(String roomName, boolean state) {
        if (roomName == null) return;

        Room room = getRoom(roomName);
        if (room != null) {
            if (state) {
                room.turnOnAllDevices();
            } else {
                room.turnOffAllDevices();
            }
        }
    }


    @Override
    public String toString() {
        String result = "House Manager - Rooms (" + rooms.size() + "):\n";
        for (String key : rooms.keySet()) {
            result += rooms.get(key).toString() + "\n\n";
        }

        return result;
    }


}
