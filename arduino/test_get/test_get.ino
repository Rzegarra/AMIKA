#include <SoftwareSerial.h>
SoftwareSerial sim800(12, 13); // RX, TX

void respuesta(){
 while (sim800.available())
 {
    if(sim800.available()>0)
      Serial.write(sim800.read());
  }

  Serial.flush();
  sim800.flush();
}

void setup()
{
 // Open serial communications and wait for port to open:
 Serial.begin(9600);
 sim800.begin(4800);
 Serial.println("esperando a que cargue");
 delay(2000);
 sim800.println("AT");
 delay(300);
 respuesta();
 sim800.println("AT+SAPBR=3,1,\"APN\",\"CMNET\"");
 delay(500);
 respuesta();
 sim800.println("AT+SAPBR=1,1");
 delay(5000);
 respuesta();
 sim800.println("AT+HTTPINIT");
 delay(500);
 respuesta();
 sim800.println("AT+HTTPPARA=\"CID\",1");
 delay(50);
 respuesta();
 sim800.println("AT+HTTPPARA=\"URL\",\"http://nodeamica-demo.herokuapp.com/po\"");
 delay(50);
 respuesta();
 sim800.println("AT+HTTPACTION=0");
 delay(3000);
 respuesta();
 sim800.println("AT+HTTPREAD");
 delay(2000);
 respuesta();
 sim800.println("AT+HTTPTERM");
 delay(1000);
 respuesta();
 sim800.println("AT+SAPBR=0,1");
 delay(1000);
 respuesta();
}
void loop() // run over and over
{
  if (sim800.available())
 Serial.write(sim800.read()); 

 if (Serial.available())
 {
 while(Serial.available())
 {
 sim800.write(Serial.read());
 }
 sim800.println();
 }
}


