#include <SoftwareSerial.h>
SoftwareSerial espSerial(5,6);
String str;
const int trigPin = 9;   
const int echoPin = 10;  
const int trigPin1 = 3;   
const int echoPin1 = 11;  
float durationX,durationY;
int distanceX,distanceY;  
void setup() {
  pinMode(trigPin, OUTPUT); // Sets the Y sensor 
 pinMode(echoPin, INPUT); 

//   pinMode(trigPin1, OUTPUT); // Sets the X sensor 
//  pinMode(echoPin1, INPUT);
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
 durationY = pulseIn(echoPin, HIGH);  
 distanceY= (int)(durationY*0.034)/2;  //Y sensor

  digitalWrite(trigPin1, LOW);  
 delayMicroseconds(2);    
 digitalWrite(trigPin1, HIGH);  
 delayMicroseconds(10);  
 digitalWrite(trigPin1, LOW); 
 durationX = pulseIn(echoPin1, HIGH);  
 distanceX= (int)(durationX*0.034)/2;  // X sensor
Serial.println("DistanceX"+String(distanceX));
if(distanceY>=5&&distanceY<=13){
 // Serial.println(String("Default:")+String(distanceY)); 
  espSerial.print(String("DD")); 
   delay(2000);
   espSerial.end();
 }
else if(distanceY>=16){
  Serial.println(String("i send :")+String(distanceY)); 
 espSerial.print(String("DU")); 
 delay(2000);
 espSerial.end();
}
else if(distanceX<=7){
  Serial.println(String("i send :")+String(distanceX)); 
 espSerial.print(String("DefR")); 
 delay(2000);
 espSerial.end();
}else if(distanceX>=20){
  Serial.println(String("i send :")+String(distanceX)); 
 espSerial.print(String("DefL")); 
 delay(2000);
 espSerial.end();
}

}
