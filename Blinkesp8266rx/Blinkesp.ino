
// the setup function runs once when you press reset or power the board
int ledR=D5;
int ledG=D6;
String s;
void setup() {
  // initialize digital pin LED_BUILTIN as an output.
  pinMode(ledR, OUTPUT);
  pinMode(ledG, OUTPUT);
  Serial.begin(115200);
while (!Serial) {
// wait for serial port to connect. Needed for native USB port only
}
}

// the loop function runs over and over again forever
void loop() {
  if (Serial.available()) {
    Serial.write(Serial.read());
      digitalWrite(ledR, HIGH);
  digitalWrite(ledG, LOW);
  delay(1000);  
  digitalWrite(ledG, HIGH);                   
  digitalWrite(ledR, LOW);    
  delay(2000);
  }
  else{
     digitalWrite(ledR, HIGH);
     delay(1000);
  digitalWrite(ledR, LOW);
  delay(1000);
  }                     // wait for a second
}
