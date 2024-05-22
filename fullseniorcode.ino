#include <Servo.h>


// Connections for the Y motor
const int IN3 = 3;   // Motor control pin for clockwise/anticlockwise (Y motor)
const int IN4 = 4;   // Motor control pin for clockwise/anticlockwise (Y motor)
const int ENB = A0;  // Motor enable pin (Y motor)


// Create a Servo object to control the servo
Servo myServo;

// Define the servo pin
const int servoPin = 13;
// Connections for the X motor
const int IN1 = 5;   // Motor control pin for clockwise/anticlockwise (X motor)
const int IN2 = 6;   // Motor control pin for clockwise/anticlockwise (X motor)
const int ENA = A1;  // Motor enable pin (X motor)

// Define trigger and echo pins for the Y-axis sensor
const int trigPinY = 10;  // TRIG connected to D10
const int echoPinY = 11;  // ECHO connected to D11

// Define trigger and echo pins for the X-axis sensor
const int trigPinX = 7;  // TRIG connected to D7
const int echoPinX = 8;  // ECHO connected to D8

// Speed of sound in cm/microsecond
const float speedOfSound = 0.0343;

// Function to measure distance using ultrasonic sensor
float measureDistance(int trigPin, int echoPin) {
  // Send a pulse to the trigger pin
  digitalWrite(trigPin, LOW);
  delayMicroseconds(2);
  digitalWrite(trigPin, HIGH);
  delayMicroseconds(10);
  digitalWrite(trigPin, LOW);

  // Measure the duration of the echo
  long duration = pulseIn(echoPin, HIGH);

  // Calculate distance based on speed of sound and return the value
  return duration * speedOfSound / 2;
}

void setup() {
  // Set motor control pins as outputs
  pinMode(IN3, OUTPUT);
  pinMode(IN4, OUTPUT);
  pinMode(IN1, OUTPUT);
  pinMode(IN2, OUTPUT);
  myServo.attach(servoPin);
  // Set motor enable pins as outputs
  pinMode(ENB, OUTPUT);
  pinMode(ENA, OUTPUT);

  // Set ultrasonic sensor pins
  pinMode(trigPinY, OUTPUT);
  pinMode(echoPinY, INPUT);
  pinMode(trigPinX, OUTPUT);
  pinMode(echoPinX, INPUT);

  // Initialize serial communication at 115200 baud
  Serial.begin(115200);

  // Measure initial distances from X-axis and Y-axis sensors
  float distanceX = measureDistance(trigPinX, echoPinX);
  float distanceY = measureDistance(trigPinY, echoPinY);

  // Print the initial measured distances to the Serial Monitor
  Serial.print("Initial Distance X: ");
  Serial.print(distanceX);
  Serial.println(" cm");

  Serial.print("Initial Distance Y: ");
  Serial.print(distanceY);
  Serial.println(" cm");

  // Control motor based on initial distanceY
  if (distanceY > 20) {
    // Move motor clockwise (up) for 2 seconds
    digitalWrite(IN3, HIGH);
    digitalWrite(IN4, LOW);
    analogWrite(ENB, 255);  // Maximum speed
    delay(3000);
    analogWrite(ENB, 0);  // Turn off motor
  } else if (distanceY < 10) {
    // Move motor anticlockwise (down) for 2 seconds
    digitalWrite(IN3, LOW);
    digitalWrite(IN4, HIGH);
    analogWrite(ENB, 255);  // Maximum speed
    delay(3000);
    analogWrite(ENB, 0);  // Turn off motor
  }

  // Add a delay of 1.5 seconds
  delay(1500);

  // Control motor based on initial distanceX
  if (distanceX > 16) {
    // Move motor anticlockwise for 1 second
    digitalWrite(IN1, LOW);
    digitalWrite(IN2, HIGH);
    analogWrite(ENA, 140);  // Maximum speed
    delay(70);
    // Turn off motor
    analogWrite(ENA, 0);
  } else if (distanceX < 10) {
    // Move motor clockwise for 50 ms
    digitalWrite(IN1, HIGH);
    digitalWrite(IN2, LOW);
    analogWrite(ENA, 140);  // Maximum speed
    delay(70);
    // Turn off motor
    analogWrite(ENA, 0);
  }
}

void loop() {
  // Measure distance from the X-axis and Y-axis ultrasonic sensors
  float distanceX = measureDistance(trigPinX, echoPinX);
  float distanceY = measureDistance(trigPinY, echoPinY);

  // Check for incoming serial data
  if (Serial.available() > 0) {
    // Read the incoming data as a string and trim whitespace
    String location = Serial.readString();
    location.trim();

    // Print the received location
    Serial.print("Received location: ");
    Serial.println(location);

    // Control motors based on received location
    if (location == "r2s1") {
      // go up from initial postion
      digitalWrite(IN3, HIGH);
      digitalWrite(IN4, LOW);
      analogWrite(ENB, 255);  // Maximum speed
      delay(4000);
      analogWrite(ENB, 0);
      delay(1000);
      //move the x motor
      digitalWrite(IN1, HIGH);
      digitalWrite(IN2, LOW);
      analogWrite(ENA, 255);  // Maximum speed
      delay(100);
      // Turn off motor
      analogWrite(ENA, 0);
      // Move the servo to 90 degrees
      delay(1000);
      myServo.write(-170);
      // Pause for 1 second (1000 milliseconds)
      delay(1000);
      myServo.write(90);
      // Pause for 1 second (1000 milliseconds)
      //delay(1000);
      delay(2000);
      // go down from request position
      digitalWrite(IN3, LOW);
      digitalWrite(IN4, HIGH);
      analogWrite(ENB, 255);  // Maximum speed
      delay(3000);
      analogWrite(ENB, 0);
      delay(500);
      //move the x motor
      digitalWrite(IN1, LOW);
      digitalWrite(IN2, HIGH);
      analogWrite(ENA, 250);  // Maximum speed
      delay(70);
      // Turn off motor
      analogWrite(ENA, 0);
      // delay(3000);
    } else if (location == "r2s2") {
      digitalWrite(IN3, HIGH);
      digitalWrite(IN4, LOW);
      analogWrite(ENB, 255);  // Maximum speed
      delay(4000);
      analogWrite(ENB, 0);
      delay(1000);
      //move the x motor
      digitalWrite(IN1, LOW);
      digitalWrite(IN2, HIGH);
      analogWrite(ENA, 255);  // Maximum speed
      delay(100);
      // Turn off motor
      analogWrite(ENA, 0);
      // Move the servo to 90 degrees
      delay(1000);
      myServo.write(-170);
      // Pause for 1 second (1000 milliseconds)
      delay(1000);
      myServo.write(90);
      // Pause for 1 second (1000 milliseconds)
      //delay(1000);
      delay(2000);
      // go down from request position
      digitalWrite(IN3, LOW);
      digitalWrite(IN4, HIGH);
      analogWrite(ENB, 255);  // Maximum speed
      delay(3000);
      analogWrite(ENB, 0);
      delay(500);
      //move the x motor
      digitalWrite(IN1, HIGH);
      digitalWrite(IN2, LOW);
      analogWrite(ENA, 250);  // Maximum speed
      delay(70);
      // Turn off motor
      analogWrite(ENA, 0);
    } else if (location == "r1s1") {
      // Move motor clockwise for 500 ms (X-axis motor)
      digitalWrite(IN1, HIGH);
      digitalWrite(IN2, LOW);
      analogWrite(ENA, 255);  // Maximum speed
      delay(100);
      // Turn off motor
      analogWrite(ENA, 0);
      // Move the servo to 90 degrees
      delay(1000);
      myServo.write(-170);
      // Pause for 1 second (1000 milliseconds)
      delay(1000);
      myServo.write(90);
      delay(1000);
      digitalWrite(IN1, LOW);
      digitalWrite(IN2, HIGH);
      analogWrite(ENA, 255);  // Maximum speed
      delay(70);
      // Turn off motor
      analogWrite(ENA, 0);
      // Additional control logic if needed
    } else if (location == "r1s2") {
      digitalWrite(IN1, LOW);
      digitalWrite(IN2, HIGH);
      analogWrite(ENA, 255);  // Maximum speed
      delay(100);
      // Turn off motor
      analogWrite(ENA, 0);
      // Move the servo to 90 degrees
      delay(1000);
      myServo.write(-170);
      // Pause for 1 second (1000 milliseconds)
      delay(1000);
      myServo.write(90);
      delay(1000);
      digitalWrite(IN1, HIGH);
      digitalWrite(IN2, LOW);
      analogWrite(ENA, 250);  // Maximum speed
      delay(70);
      // Turn off motor
      analogWrite(ENA, 0);
    }
  }

  // Add a delay before repeating the loop
  delay(1000);
}


