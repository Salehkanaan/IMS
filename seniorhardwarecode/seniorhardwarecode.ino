// #include <ESP8266WiFi.h>
// #include <ESP8266WebServer.h>
 #include <Servo.h>
#define ENA D5  // D5 D6 D7 for the y DC motor
#define IN1 D6
#define IN2 D7
#define ENB D0  // D0 D1 D2 for the X DC motor
#define IN4 D1
#define IN3 D2
// const char *ssid = "Hamdar";
// const char *password = "hamdar@2022";
String s;

// ESP8266WebServer server(80);

 Servo servo;

void setup() {
  Serial.begin(115200);
   servo.attach(D4);  // to connect the servo
  pinMode(IN1, OUTPUT);
  pinMode(IN2, OUTPUT);
  pinMode(ENA, OUTPUT);
  pinMode(IN3, OUTPUT);
  pinMode(IN4, OUTPUT);
  pinMode(ENB, OUTPUT);
  // // Connect to Wi-Fi
  // WiFi.begin(ssid, password);
  // Serial.println("");
  while (!Serial) {
  //   // wait for serial port to connect. Needed for native USB port only
   }
  // // Wait for connection
  // while (WiFi.status() != WL_CONNECTED) {
  //   delay(500);
  //   Serial.print(".");
  // }
  // Serial.println("");
  // Serial.print("Connected to ");
  // Serial.println(ssid);
  // Serial.print("IP address: ");
  // Serial.println(WiFi.localIP());

  // // Handle root URL
  // server.on("/", HTTP_GET, []() {
  //   server.send(200, "text/html",
  //               "<form action='/submit' method='get'>"
  //               "Quantity: <input type='text' name='quantity' value='1'><br>"
  //               "Location: <input type='text' name='location'><br>"
  //               "<input type='submit' value='Submit'>"
  //               "</form>");
  // });

  // // Handle form submission
  // server.on("/submit", HTTP_GET, []() {
  //   // String quantity = server.arg("quantity");
  //   String location = server.arg("location");

  //   // Allow requests from any origin (CORS)
  //   server.sendHeader("Access-Control-Allow-Origin", "*");


    while (Serial.available()) {
    s= Serial.readString();
    if(s.equals("DY")){
      servo.write(180);
      delay(1000);
      servo.write(-180);
      delay(1000);
    }
    else{
      Serial.println(s);
    }
    }


    // if (location.equals("r1s1")) {
    //   // first thing is to go down initial is max up
    //   digitalWrite(IN1, HIGH);
    //   digitalWrite(IN2, LOW);
    //   analogWrite(ENA, 255);
    //   delay(2000);

    //   // Stop the motor
    //   digitalWrite(ENA, LOW);
    //   delay(2000);  // Wait for 2 seconds for actions controlled by PIN_IN1 and PIN_IN2


    //   // move the x axis to the r1s1 location
    //   digitalWrite(IN3, HIGH);  // this combo to take it from center to left
    //   digitalWrite(IN4, LOW);
    //   analogWrite(ENB, 150);
    //   delay(500);
    //   servo.write(180);
    //   delay(1000);
    //   servo.write(-180);
    //   digitalWrite(IN3, LOW);  // this combo to take it from left to center
    //   digitalWrite(IN4, HIGH);
    //   delay(500);
    //   // Stop the motor
    //   digitalWrite(ENB, LOW);
    //   // go back up
    //   digitalWrite(IN1, LOW);
    //   digitalWrite(IN2, HIGH);
    //   analogWrite(ENA, 255);
    //   delay(2000);
    //   digitalWrite(ENA, LOW);
  //   }

  //   if (location.equals("r1s2")) {
  //     //  first go down
  //     digitalWrite(IN1, HIGH);
  //     digitalWrite(IN2, LOW);
  //     analogWrite(ENA, 255);
  //     delay(2000);

  //     // Stop the motor
  //     digitalWrite(ENA, LOW);
  //     delay(2000);
  //     // move the x axis to the r1s2 location
  //     digitalWrite(IN3, LOW);  // this combo  from center to right
  //     digitalWrite(IN4, HIGH);
  //     analogWrite(ENB, 150);
  //     delay(500);

  //     servo.write(180);
  //     delay(1000);
  //     servo.write(-180);
  //     digitalWrite(IN3, HIGH);  // this combo to take it from left to center
  //     digitalWrite(IN4, LOW);
  //     analogWrite(ENB, 150);
  //     delay(500);
  //     // Stop the motor
  //     digitalWrite(ENB, LOW);
  //     // go back up
  //     digitalWrite(IN1, LOW);
  //     digitalWrite(IN2, HIGH);
  //     analogWrite(ENA, 255);
  //     delay(2000);
  //     digitalWrite(ENA, LOW);
  //   }
  //   server.send(200, "text/plain", "location");
  // });

  // server.begin();
  // Serial.println("HTTP server started");
}

void loop() {
  //server.handleClient();
    // if (Serial.available()) {
    // s= Serial.readString();
    // if(s.equals("DY")){
    //   servo.write(180);
    //   delay(1000);
    //   servo.write(-180);
    //   delay(1000);
    // }
    // else{
    //   Serial.println(s);
    // }
    // }
    }
   
