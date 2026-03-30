# QuickServe - On-Demand Service App

QuickServe is a modern, premium on-demand service mobile application built with Jetpack Compose. It connects clients with skilled workers (Electricians, Plumbers, Carpenters, etc.) for various household and professional tasks.

## Features

- **Multi-Role Support**: Separate flows for **Clients** and **Workers**.
- **Modern UI/UX**: Clean, premium design with a Deep Purple and Teal color palette, soft shadows, and rounded corners.
- **Smart Authentication**: Phone-based login with OTP verification.
- **Multi-Language Support**: Support for English, Hindi, Telugu, Tamil, Marathi, and Malayalam with persistent settings.
- **Form Validation**: Comprehensive input validation with visual error feedback.
- **Service Selection**: Workers can select multiple services they provide.
- **Client Features**:
  - Browse available workers and services.
  - Book services with localized UI.
  - View booking history and provide ratings/reviews.
- **Worker Features**:
  - Personal dashboard to track completed/pending jobs and earnings.
  - Receive notifications for new tasks nearby.

## Tech Stack

- **Language**: Kotlin
- **UI Framework**: Jetpack Compose (Material 3)
- **Navigation**: Navigation Compose
- **Image Loading**: Coil
- **Architecture**: Modern Android Architecture patterns

## Getting Started

Follow these instructions to get a copy of the project up and running on your local machine for development and testing purposes.

### Prerequisites

- **Android Studio Giraffe | 2022.3.1** or newer.
- **Android SDK Platform 34** (UpsideDownCake).
- **Gradle 8.0+**
- **Java 17**

### Installation & Setup

1. **Clone the repository**:
   ```bash
   git clone https://github.com/sk-thaj/QuickServe.git
   ```

2. **Open in Android Studio**:
   - Launch Android Studio.
   - Click on `File` > `Open`.
   - Navigate to the cloned `QuickServe` directory and click `OK`.

3. **Sync Gradle**:
   - Wait for Android Studio to finish indexing and syncing Gradle files.
   - If prompted, install any missing SDK components or build tools.

## Running the App

1. **Setup an Emulator or Physical Device**:
   - Go to `Device Manager` in Android Studio.
   - Create a virtual device (Pixel 6/7 recommended) or connect a physical device via USB/Wireless Debugging.
   - Ensure the device is running **Android 7.0 (API 24)** or higher.

2. **Run the Project**:
   - Select the `app` configuration in the toolbar.
   - Choose your target device.
   - Click the **Run** icon (green play button) or press `Shift + F10`.

## Project Structure

- `app/src/main/java/com/quickserve/app/ui/screens/` - Contains all Compose screens (Auth, Client, Worker).
- `app/src/main/java/com/quickserve/app/ui/theme/` - Color, Typography, and Theme definitions.
- `app/src/main/java/com/quickserve/app/ui/navigation/` - Navigation graph and route definitions.
- `app/src/main/java/com/quickserve/app/utils/` - Utility classes for Language, Validation, etc.

## Testing

- To run unit tests: `./gradlew test`
- To run instrumented tests: `./gradlew connectedAndroidTest`

## License

This project is licensed under the MIT License - see the LICENSE file for details.

---

*QuickServe - Bringing reliable services to your doorstep.*
