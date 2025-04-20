import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        int option, op2;
        boolean x = true, y;
        String name;
        Room room;
        Scanner scanner = new Scanner(System.in);
        HouseManager manager = new HouseManager();
        SingletonErrorLogger logger = SingletonErrorLogger.getInstance();

        while (x) {
            try {
                System.out.println("\n======================================");
                System.out.println("------- Home Management System -------");
                mainMenu();
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
                            System.out.println("Error! Room not found or there aren't rooms with the corresponding name.");
                        }
                        break;

                    case 3:
                        System.out.println("\n" + manager.toString());
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
                        System.out.println("\n" + manager.toString());
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
                            deviceMenu();
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
                                            System.out.println("Failed to add device, you can't add a new device named the same as another one!");
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
                                            ((Temperature) d).updateTemperature();
                                        } else if (d instanceof Humidity) {
                                            ((Humidity) d).updateHumidity();
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
                                    if (specDev == null) {
                                        System.out.println("Device not found!");
                                        break;
                                    }

                                    if (specDev instanceof Light light) {
                                        System.out.println("Current brightness: " + light.getMaxBrightness());
                                        System.out.print("Enter new brightness: ");
                                        light.setMaxBrightness(scanner.nextInt());
                                        scanner.nextLine();
                                        System.out.println("Brightness updated!");
                                    } else if (specDev instanceof Voc voc) {
                                        System.out.println("Current PPM: " + voc.getPpm());
                                        System.out.println("Air Quality: " + voc.getAirQuality());
                                    } else {
                                        System.out.println("No special operations available for this device type.");
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
                        System.out.println(manager.toString());
                        break;
                    case 6:
                        x = false;
                        System.out.println("Exiting program...");
                        break;

                    default:
                        System.out.println("Invalid option!");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a number.");
                scanner.nextLine();
                logger.log("InputMismatchException: Non-numeric input detected");
            } catch (NumberFormatException e) {
                System.out.println("Invalid number format.");
                scanner.nextLine();
                logger.log("NumberFormatException: " + e.toString());
            } catch (Exception e) {
                System.out.println("An unexpected error occurred: " + e.toString());
                scanner.nextLine();
                logger.log("Unexpected Exception: " + e.toString());
                e.printStackTrace();
            }
        }
    }

    public static void mainMenu(){
        System.out.println("╔════════════════════════════════════════════╗");
        System.out.println("║                   MENU                     ║");
        System.out.println("╣════════════════════════════════════════════╣");
        System.out.println("║  •[1] Add a new Room                       ║");
        System.out.println("║  •[2] Remove a Room                        ║");
        System.out.println("║  •[3] Show a Room  (by name)               ║");
        System.out.println("║  •[4] Menage a device in a specific Room   ║");
        System.out.println("║  •[5] Show all Room                        ║");
        System.out.println("║  •[6] Exit program                         ║");
        System.out.println("╚════════════════════════════════════════════╝");
        System.out.println("\n Enter the option:");
    }

    public static void deviceMenu(){
        System.out.println("╔════════════════════════════════════════════╗");
        System.out.println("║                   MENU                     ║");
        System.out.println("╣════════════════════════════════════════════╣");
        System.out.println("║  •[1] Add a device                         ║");
        System.out.println("║  •[2] Remove a device                      ║");
        System.out.println("║  •[3] Turn on a device  (by name)          ║");
        System.out.println("║  •[4] Turn off a device (by name)          ║");
        System.out.println("║  •[5] Turn on all devices                  ║");
        System.out.println("║  •[6] Turn off all devices                 ║");
        System.out.println("║  •[7] Show all devices                     ║");
        System.out.println("║  •[8] Device-specific operations           ║");
        System.out.println("║  •[9] Back to the main Menu                ║");
        System.out.println("╚════════════════════════════════════════════╝");
        System.out.println("\n Enter the option:");
    }
}