#include <SoftwareSerial.h>
SoftwareSerial mySerial(12, 13); // RX, TX
void setup()
{
 // Open serial communications and wait for port to open:
 Serial.begin(9600);
 mySerial.begin(9600);
 Serial.write("holi");
}
void loop() // run over and over
{
 if (mySerial.available())
 Serial.write(mySerial.read()); 

 if (Serial.available())
 {
 while(Serial.available())
 {
 mySerial.write(Serial.read());
 }
 mySerial.println();
 }
}
