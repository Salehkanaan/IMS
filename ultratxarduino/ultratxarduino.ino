#include <SoftwareSerial.h>
SoftwareSerial espSerial(5,6);
String str;
const int trigPin = 9;   
const int echoPin = 10;   
float duration;
int distance;  
void setup() {
  pinMode(trigPin, OUTPUT); // Sets the trigPin as an Output  
 pinMode(echoPin, INPUT); 
  Serial.begin(115200);
  espSerial.begin(115200);
  delay(1000);
  // put your setup code here, to run once:

}

void loop() {

 digitalWrite(trigPin, LOW);  
 delayMicroseconds(2);    
 digitalWrite(trigPin, HIGH);  
 delayMicroseconds(10);  
 digitalWrite(trigPin, LOW); 

 duration = pulseIn(echoPin, HIGH);  
 distance= (int)(duration*0.034)/2;  
str=String("Distance: ")+String(distance);
if(distance>=12){
  Serial.println(String("i send m1")); 
  espSerial.println(String("M1")); 
   delay(1000);
 }
else{
  Serial.println(String("i send m0"));  
 espSerial.println(String("M0")); 
 delay(1000);
}
}
