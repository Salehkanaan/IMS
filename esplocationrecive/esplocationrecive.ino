#include <ESP8266WiFi.h>
#include <ESP8266WebServer.h>

const char *ssid = "Galaxy.S";
const char *password = "kanaan313";
ESP8266WebServer server(80);

void setup() {
  Serial.begin(115200);


  // Connect to Wi-Fi
  WiFi.begin(ssid, password);
  //Serial.println("");

  // Wait for connection
  while (WiFi.status() != WL_CONNECTED) {
    delay(500);
    //Serial.print(".");
  }
 // Serial.println("");
 // Serial.print("Connected to ");
 // Serial.println(ssid);
  //Serial.print("IP address: ");
  Serial.println(WiFi.localIP());

  // Handle root URL
  server.on("/", HTTP_GET, []() {
    server.send(200, "text/html",
                "<form action='/submit' method='get'>"
                "Quantity: <input type='text' name='quantity' value='1'><br>"
                "Location: <input type='text' name='location'><br>"
                "<input type='submit' value='Submit'>"
                "</form>");
  });

  // Handle form submission
  server.on("/submit", HTTP_GET, []() {
    // String quantity = server.arg("quantity");
    String location = server.arg("location");

    // Allow requests from any origin (CORS)
    server.sendHeader("Access-Control-Allow-Origin", "*");

    Serial.print(location);

    server.send(200, "text/plain", "location");
  });

  server.begin();
  Serial.println("HTTP server started");
}

void loop() {
  server.handleClient();
}