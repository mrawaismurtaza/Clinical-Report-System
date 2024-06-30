# Patient Report System


The Patient Report System is a clinical software application designed to store, retrieve, and display patient reports using QR codes. It is built with Eclipse and the OSGi framework, ensuring modularity and ease of maintenance. The system features a lightweight web server, a repository for mock data, and components for generating and scanning QR codes.

Features
Report Storage: Store and manage patient reports with unique access codes.
QR Code Generation: Generate QR codes linking to patient reports.
QR Code Scanning: Scan QR codes to retrieve encoded URLs.
Web Server: Serve patient reports as HTML pages through a lightweight web server.
Prerequisites
Java Development Kit (JDK) 22.0.1 or higher
Eclipse IDE for RCP and RAP Developers
OSGi framework
ZXing library (for QR code generation and scanning)
NanoHTTPD library (for the web server)
Project Structure
SimpleWebServer.java: Serves patient reports as HTML pages.
ReportRepository.java: Manages the storage of patient reports.
QRCodeGenerator.java: Generates QR codes.
QRCodeScanner.java: Scans QR codes.
Main.java: Provides a console-based interface for the system.
Running the Application
Clone the repository:

cd patient-report-system
Open the project in Eclipse:

Import the project as an existing Maven project.
Install dependencies:

Ensure that all required libraries (ZXing, NanoHTTPD) are added to the project's build path.
Run the application:

Run Main.java as a Java application.
The console will display options to generate or scan QR codes and start the web server.
Accessing the Web Server:

The server runs on port 8080. Open a web browser and navigate to http://localhost:8080/?accessCode=YOUR_ACCESS_CODE to view a patient report.
Usage
Generate QR Code:

Select option 1 in the console.
Enter the access code when prompted.
The QR code image will be saved in the specified directory.
Scan QR Code:

Select option 2 in the console.
Enter the file path to the QR code image.
The console will display the decoded content.
Exit:

Select option 3 to exit the application.
Example Patient Reports
Predefined reports with unique access codes are available for testing.
Access codes follow the format AffeXXXXXXXX-XXXX-XXXX-XXXX-XXXXXXXXXXXX.
Contributing
Fork the repository.
Create a new branch (git checkout -b feature-branch).
Commit your changes (git commit -am 'Add new feature').
Push to the branch (git push origin feature-branch).
Open a pull request.
