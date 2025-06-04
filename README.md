# NexusCode - Smart Home Management System

A comprehensive Java-based smart home management system that allows users to monitor and control various IoT devices across multiple rooms in their home. Built with object-oriented principles and featuring persistent configuration storage.

## 🏠 Features

### Core Functionality
- **Room Management**: Create, remove, and organize rooms in your smart home
- **Multi-Device Support**: Control various types of smart devices including:
  - **Smart Lights** with adjustable brightness levels
  - **Temperature Sensors** with automatic climate control
  - **Humidity Monitors** with dehumidification alerts
  - **VOC (Volatile Organic Compounds) Sensors** for air quality monitoring
  - **Generic Devices** for basic on/off functionality

### Smart Automation
- **Automatic Climate Control**: Temperature and humidity sensors automatically trigger heating, cooling, or dehumidification when thresholds are exceeded
- **Air Quality Monitoring**: VOC sensors provide real-time air quality assessments (Excellent, Good, Moderate, Bad)
- **Bulk Device Control**: Turn all devices in a room on or off simultaneously

### Data Persistence
- **JSON Configuration Storage**: Save and load complete home configurations
- **Error Logging**: Comprehensive logging system with timestamps for debugging and monitoring
- **Robust Data Handling**: Built-in error handling and validation

## 🛠️ Technical Architecture

### Design Patterns
- **Singleton Pattern**: Implemented for error logging to ensure single instance management
- **Inheritance Hierarchy**: Device classes extend base Device class for code reusability
- **Polymorphism**: Unified device interface with specialized behavior for each device type

### Key Components
- `HouseManager`: Central controller for managing rooms and devices
- `Room`: Container class for organizing devices by location
- `Device`: Base class with specialized subclasses for different device types
- `HandlerGson`: JSON serialization/deserialization for configuration persistence
- `SingletonErrorLogger` & `Errorlogv2`: Dual logging system for comprehensive error tracking

## 🚀 Getting Started

### Prerequisites
- Java 23 or higher
- Gson library (included in project dependencies)

### Installation
1. Clone the repository:
   ```bash
   git clone https://github.com/yourusername/nexuscode.git
   cd nexuscode
   ```

2. Ensure Gson dependency is available:
   - The project includes `gson-2.13.1.jar` in the dependencies
   - Library path is configured in the module file

3. Compile and run:
   ```bash
   javac -cp "lib/gson-2.13.1.jar" src/*.java
   java -cp "lib/gson-2.13.1.jar:src" Main
   ```

## 📱 Usage

### Main Menu Options
1. **Add a new Room** - Create rooms in your smart home
2. **Remove a Room** - Delete existing rooms
3. **Show a Room** - Display room details and devices
4. **Manage Devices** - Add, remove, and control devices in specific rooms
5. **Show all Rooms** - Overview of your entire smart home setup
6. **Save Configuration** - Persist current setup to JSON file
7. **Load Configuration** - Restore previously saved configuration
8. **Clear Screen** - Clean up the console display
9. **Exit Program** - Safely exit the application

### Device Management
- Add various device types with custom names and initial states
- Individual device control (on/off, brightness adjustment)
- Room-wide device control
- Real-time sensor readings and automated responses
- Device-specific operations (brightness adjustment, sensor readings)

## 🔧 Device Types

### Smart Light
- Adjustable brightness levels
- On/off state control
- Customizable maximum brightness settings

### Temperature Sensor
- Automatic temperature monitoring
- Climate control automation (heating/cooling activation)
- Optimal temperature maintenance (22°C target)

### Humidity Monitor
- Real-time humidity percentage tracking
- Automatic dehumidification alerts
- Maintains optimal humidity levels (30% target)

### VOC Sensor
- Air quality assessment with PPM measurements
- Four-tier quality rating system
- Random simulation of air quality conditions

## 💾 Configuration Management

The system supports persistent storage through JSON serialization:

```json
{
  "rooms": {
    "Living Room": {
      "roomName": "Living Room",
      "devices": [
        {
          "name": "Main Light",
          "state": true,
          "maxBrightness": 100
        }
      ]
    }
  }
}
```

## 📝 Logging System

Dual logging implementation:
- **Primary Logger**: General application events and errors
- **Secondary Logger**: Specific error conditions and user actions
- **Timestamped Entries**: All log entries include precise timestamps
- **File Persistence**: Logs saved to `log.txt` and `log2.txt`

## 🤝 Contributing

1. Fork the repository
2. Create a feature branch (`git checkout -b feature/AmazingFeature`)
3. Commit your changes (`git commit -m 'Add some AmazingFeature'`)
4. Push to the branch (`git push origin feature/AmazingFeature`)
5. Open a Pull Request

## 📄 License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

## 🙏 Acknowledgments

- Built with Java 23 and modern OOP principles
- Uses Google Gson for JSON handling
- Implements industry-standard design patterns
- Console-based interface for universal compatibility

## 📞 Support

For support, questions, or feature requests, please open an issue on GitHub or contact the development team.

---

**NexusCode** - Bringing smart home management to the next level with robust, scalable, and user-friendly automation.
