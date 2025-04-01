import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        int option, op2;
        boolean x = true, y;
        String name;
        Room room;
        Scanner scanner = new Scanner(System.in);
        HouseManager manager = new HouseManager();

        while (x) {
            System.out.println("\n======================================");
            System.out.println("------- Home Management System -------");
            System.out.println("1. Add a new room\n2. Remove a room\n3. Show a room (search by name)\n4. Manage devices in a room\n5. Exit program");
            System.out.print("Select an option: ");

            option = scanner.nextInt();
            scanner.nextLine();

            switch (option) {
                case 1:
                    System.out.print("\nEnter the room name: ");
                    name = scanner.nextLine();
                    room = new Room(name);
                    if (manager.addRoom(room)) {
                        System.out.println("Room added successfully!");
                    } else {
                        System.out.println("Error! A room with that name already exists.");
                    }
                    break;

                case 2:
                    System.out.println("\nCurrent rooms:");
                    System.out.println(manager.toString());
                    System.out.print("Enter the name of the room to remove: ");
                    String rm = scanner.nextLine();
                    if (manager.removeRoom(rm)) {
                        System.out.println("Room removed successfully!");
                    } else {
                        System.out.println("Error! Room not found.");
                    }
                    break;

                case 3:
                    System.out.print("\nEnter the name of the room to show: ");
                    name = scanner.nextLine();
                    Room foundRoom = manager.getRoom(name);
                    if (foundRoom != null) {
                        System.out.println(foundRoom.toString());
                    } else {
                        System.out.println("Error! Room not found.");
                    }
                    break;

                case 4:
                    System.out.print("\nEnter the room name to manage devices: ");
                    String roomName = scanner.nextLine();
                    Room currentRoom = manager.getRoom(roomName);
                    if (currentRoom == null) {
                        System.out.println("Room not found!");
                        break;
                    }

                    y = true;
                    while (y) {
                        System.out.println("\n--- Device Management: " + roomName + " ---");
                        System.out.println("1. Add a device\n2. Remove a device\n3. Turn on a devic\n4. Turn off a device\n5. Turn on all devices\n6. Turn off all devices\n7. Show all devices\n8. Device-specific operations\n9. Back to main Menu.");
                        op2 = scanner.nextInt();
                        scanner.nextLine();

                        switch (op2) {
                            case 1:
                                System.out.println("\nSelect device type:");
                                System.out.println("1. Light\n2. Temperature\n3. Humidity\n4. VOC Sensor\n");
                                System.out.print("Enter choice: ");
                                int deviceType = scanner.nextInt();
                                scanner.nextLine();

                                System.out.print("Enter device name: ");
                                String deviceName = scanner.nextLine();

                                System.out.print("Enter initial state (true=on, false=off): ");
                                boolean initialState = scanner.nextBoolean();
                                scanner.nextLine();

                                Device newDevice = null;
                                switch (deviceType) {
                                    case 1:
                                        System.out.print("Enter max brightness: ");
                                        int brightness = scanner.nextInt();
                                        scanner.nextLine();
                                        newDevice = new Light(deviceName, initialState, brightness);
                                        break;
                                    case 2:
                                        newDevice = new Temperature(deviceName, initialState);
                                        break;
                                    case 3:
                                        newDevice = new Humidity(deviceName, initialState);
                                        break;
                                    case 4:
                                        newDevice = new Voc(deviceName, initialState);
                                        break;
                                    default:
                                        System.out.println("Invalid device type!");
                                        break;
                                }

                                if (newDevice != null) {
                                    if (manager.addDevice(roomName, newDevice)) {
                                        System.out.println("Device added successfully!");
                                    } else {
                                        System.out.println("Failed to add device!");
                                    }
                                }
                                break;

                            case 2:
                                System.out.print("\nEnter device name to remove: ");
                                String deviceToRemove = scanner.nextLine();
                                if (manager.removeDevice(roomName, deviceToRemove)) {
                                    System.out.println("Device removed successfully!");
                                } else {
                                    System.out.println("Device not found!");
                                }
                                break;

                            case 3:
                                System.out.print("\nEnter device name to turn on: ");
                                String deviceToTurnOn = scanner.nextLine();
                                if (currentRoom.turnOnForName(deviceToTurnOn)) {
                                    System.out.println("Device turned on!");
                                    Device d = currentRoom.getDevice(deviceToTurnOn);
                                    if (d instanceof Temperature) {
                                        ((Temperature)d).updateTemperature();
                                    } else if (d instanceof Humidity) {
                                        ((Humidity)d).updateHumidity();
                                    }
                                } else {
                                    System.out.println("Device not found!");
                                }
                                break;

                            case 4:
                                System.out.print("\nEnter device name to turn off: ");
                                String deviceToTurnOff = scanner.nextLine();
                                if (currentRoom.turnOffForName(deviceToTurnOff)) {
                                    System.out.println("Device turned off!");
                                } else {
                                    System.out.println("Device not found!");
                                }
                                break;

                            case 5:
                                currentRoom.turnOnAllDevices();
                                System.out.println("All devices turned on!");
                                break;

                            case 6:
                                currentRoom.turnOffAllDevices();
                                System.out.println("All devices turned off!");
                                break;

                            case 7:
                                System.out.println("\nCurrent devices:");
                                System.out.println(currentRoom.toString());
                                break;

                            case 8:
                                System.out.print("\nEnter device name for special operations: ");
                                String specDevName = scanner.nextLine();
                                Device specDev = currentRoom.getDevice(specDevName);
                                if (specDev != null) {
                                    if (specDev instanceof Light) {
                                        System.out.println("Current brightness: " + ((Light)specDev).getMaxBrightness());
                                        System.out.print("Enter new brightness: ");
                                        int bright = scanner.nextInt();
                                        scanner.nextLine();
                                        ((Light)specDev).setMaxBrightness(bright);
                                        System.out.println("Brightness updated!");
                                    }
                                    else if (specDev instanceof Voc) {
                                        System.out.println("Current PPM: " + ((Voc)specDev).getPpm());
                                        System.out.println("Air Quality: " + ((Voc)specDev).getAirQuality());
                                    }
                                    else {
                                        System.out.println("No special operations available for this device type.");
                                    }
                                } else {
                                    System.out.println("Device not found!");
                                }
                                break;

                            case 9:
                                y = false;
                                break;

                            default:
                                System.out.println("Invalid option!");
                        }
                    }
                    break;

                case 5:
                    x = false;
                    System.out.println("Exiting program...");
                    break;

                default:
                    System.out.println("Invalid option!");
            }
        }
    }
}