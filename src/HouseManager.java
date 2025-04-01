import java.util.HashMap;
import java.util.Hashtable;

public class HouseManager {
    private Hashtable<String,Room> room = new Hashtable<>();

    public boolean addRoom(Room t){
        if(t == null || t.getRoomName()==null) {
            return false;
        }
        if(!room.containsKey(t)){
            room.put(t.getRoomName(),t);
            return true;
        }
        return false;
    }

    public boolean removeRoom(String roomName){
        if(room == null){
            return false;
        }else{
            room.remove(roomName);
            return true;
        }
    }

    public Room getRoom(String roomName){
        if (roomName == null) {
            return null;
        }

        return room.get(roomName);
    }


    public boolean addDevice(String roomName,Device device){
        if(roomName==null || device== null){
            return false;
        }
        Room room = getRoom(roomName);
        if (room != null) {
            room.addDevice(device);
            return true;
        }
        return false;
    }

    public boolean removeDevice(String roomName,String deviceName){
        if(roomName==null || deviceName == null){
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





}
