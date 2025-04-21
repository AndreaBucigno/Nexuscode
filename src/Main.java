import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        int option, op2;
        boolean x = true;
        String name;
        Room room;
        Scanner scanner = new Scanner(System.in);
        HouseManager manager = new HouseManager();
        SingletonErrorLogger logger = SingletonErrorLogger.getInstance();

        while (x) {
            try {
                System.out.println("\n=============================================");
                System.out.println("----------- Home Management System -----------");
                mainMenu();
                option = scanner.nextInt();
                scanner.nextLine();
                x = handlerMainMenu(option, scanner, manager, logger);

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


    public static boolean handlerMainMenu(int option, Scanner scanner, HouseManager manager, SingletonErrorLogger logger) {
        switch (option) {
            case 1:
                System.out.print("\nEnter the room name: ");
                String name = scanner.nextLine();
                Room room = new Room(name);
                if (manager.addRoom(room)) {
                    System.out.println("Room added successfully!");
                } else {
                    System.out.println("Error! A room with that name already exists.");
                }
                return true;

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
                return true;

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
                return true;

            case 4:
                System.out.println("\n" + manager.toString());
                System.out.print("\nEnter the room name to manage devices: ");
                String roomName = scanner.nextLine();
                Room currentRoom = manager.getRoom(roomName);
                if (currentRoom == null) {
                    System.out.println("Room not found!");
                    return true;
                }

                handlerDeviceMenu(scanner, manager, roomName, currentRoom, logger);
                return true;

            case 5:
                System.out.println(manager.toString());
                return true;

            case 6:
                System.out.println("Exiting program...");
                return false;

            default:
                System.out.println("Invalid option!");
                return true;
        }
    }

    public static void handlerDeviceMenu(Scanner scanner, HouseManager manager, String roomName, Room currentRoom, SingletonErrorLogger logger) {
        boolean y = true;
        int op2;

        while (y) {
            try {
                System.out.println("\n--- Device Management: " + roomName + " ---");
                deviceMenu();
                op2 = scanner.nextInt();
                scanner.nextLine();

                y = handlerDeviceOptions(op2, scanner, manager, roomName, currentRoom);

            } catch (Exception e) {
                System.out.println("An unexpected error occurred in device menu: " + e.toString());
                scanner.nextLine();
                logger.log("Device Menu Exception: " + e.toString());
            }
        }
    }

    public static boolean handlerDeviceOptions(int op2, Scanner scanner, HouseManager manager, String roomName, Room currentRoom) {
        switch (op2) {
            case 1:
                System.out.println("\nSelect device type:");
                typeOfDeviceMenu();
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
                    case 5:
                        newDevice = new Device(deviceName, initialState);
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
                return true;


            case 2:
                System.out.print("\nEnter device name to remove: ");
                String deviceToRemove = scanner.nextLine();
                if (manager.removeDevice(roomName, deviceToRemove)) {
                    System.out.println("Device removed successfully!");
                } else {
                    System.out.println("Device not found!");
                }
                return true;

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
                return true;

            case 4:
                System.out.print("\nEnter device name to turn off: ");
                String deviceToTurnOff = scanner.nextLine();
                if (currentRoom.turnOffForName(deviceToTurnOff)) {
                    System.out.println("Device turned off!");
                } else {
                    System.out.println("Device not found!");
                }
                return true;

            case 5:
                currentRoom.turnOnAllDevices();
                System.out.println("All devices turned on!");
                return true;

            case 6:
                currentRoom.turnOffAllDevices();
                System.out.println("All devices turned off!");
                return true;

            case 7:
                System.out.println("\nCurrent devices:");
                System.out.println(currentRoom.toString());
                return true;

            case 8:
                System.out.print("\nEnter device name for special operations: ");
                String specDevName = scanner.nextLine();
                Device specDev = currentRoom.getDevice(specDevName);
                if (specDev == null) {
                    System.out.println("Device not found!");
                    return true;
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
                } else if (specDev instanceof Temperature temperature) {
                    System.out.println("Temperature in the Room: " + temperature.getTemperature());
                } else if (specDev instanceof  Humidity humidity) {
                    System.out.println("Humidity percentage in the Room: "+ humidity.getHumidityPercentage());
                } else {
                    System.out.println("No special operations available for this device type.");
                }
                return true;

            case 9:
                return false;

            default:
                System.out.println("Invalid option!");
                return true;
        }
    }

    public static void clear() {
        for (int i = 0; i < 50; i++) {
            System.out.println("\n");
        }
    }

    public static void mainMenu() {
        System.out.println("╔════════════════════════════════════════════╗");
        System.out.println("║                   MENU                     ║");
        System.out.println("╣════════════════════════════════════════════╣");
        System.out.println("║  •[1] Add a new Room                       ║");
        System.out.println("║  •[2] Remove a Room                        ║");
        System.out.println("║  •[3] Show a Room  (by name)               ║");
        System.out.println("║  •[4] Menage a device in a specific Room   ║");
        System.out.println("║  •[5] Show all Room                        ║");
        System.out.println("║  •[6] Clear the screen                     ║");
        System.out.println("║  •[7] Exit program                         ║");
        System.out.println("╚════════════════════════════════════════════╝");
        System.out.println("\n Enter the option:");
    }

    public static void deviceMenu() {
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


    public static void typeOfDeviceMenu() {
        System.out.println("╔════════════════════════════════════════════╗");
        System.out.println("║                   MENU                     ║");
        System.out.println("╣════════════════════════════════════════════╣");
        System.out.println("║  •[1] Light                                ║");
        System.out.println("║  •[2] Temperature                          ║");
        System.out.println("║  •[3] Humidity                             ║");
        System.out.println("║  •[4] Voc                                  ║");
        System.out.println("║  •[5] Generic device                       ║");
        System.out.println("╚════════════════════════════════════════════╝");
        System.out.println("\n Enter the option:");
    }
}