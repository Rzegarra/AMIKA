#include <SoftwareSerial.h>
#include <TinyGPS.h>
#include <LiquidCrystal.h>
LiquidCrystal lcd(7, 6, 5, 4, 3, 2);

#define RXPIN_gps 10
#define TXPIN_gps 11

#define RXPIN_sim 12
#define TXPIN_sim 13

//AT+GSN  
//860719024889580
SoftwareSerial sim800(RXPIN_sim, TXPIN_sim); // RX, TX
SoftwareSerial uart_gps(RXPIN_gps, TXPIN_gps);

TinyGPS gps;
void getgps(TinyGPS &gps);
String recepcion="";
char c;
int temp=0;
String id;
String _Data="";
String _Latitude="-16.409047";
String _Longitude="-71.537451";
String _Vel="0.015";


void setup()
{
 
 // Open serial communications and wait for port to open:
 Serial.begin(9600);
 uart_gps.begin(9600);
 sim800.begin(4800);

 Serial.println("esperando a que cargue");
 delay(2000);
 imei();
 lcd.begin(16, 2);
 lcd.setCursor(0, 0);
 lcd.print("i");
 lcd.setCursor(1, 0);
 lcd.print(id);
 f_ConfigPost();
 

//// Serial.println("{\"id\":\"860719024889580\",\"lat\":\"-16.409047\",\"long\":\"-71.537451\"}");
////String nuevo="{\"id\":\"860719024889580\",\"lat\":\"-16.409047\",\"long\":\"-71.537451\"}";
//// if(_Data.equals("{\"id\":\"860719024889580\",\"lat\":\"-16.409047\",\"long\":\"-71.537451\"}"));
////  Serial.print("son la MISMA M..");
//// delay(20);

// //sim800.println("{\"id\":\"860719024889580\",\"lat\":\"-16.409047\",\"long\":\"-71.537451\"}");
//// sim800.println("{\"id\":\""+id+"\"}");

//  sim800.println("aT+HTTPDATA=40,10000");
// delay(3000);
// respuesta();
// sim800.println("{\"OOO\": \"joder\"}");
// delay(9000);
// respuesta();
// sim800.println("AT+HTTPACTION=1");
// delay(5000);
// respuesta();
// sim800.println("AT+HTTPREAD");
// delay(1000);
// respuesta();
// sim800.println("AT+HTTPTERM");
// delay(1000);
// respuesta();
// sim800.println("AT+SAPBR=0,1");
// delay(1000);
// respuesta();
}

void loop() // run over and over

{
   
   f_SendPost();
   
   getgps(gps);         // then grab the data.
   smartdelay(1000);
   
}

void getgps(TinyGPS &gps)
{
  uart_gps.listen(); 
  float latitude, longitude;
  gps.f_get_position(&latitude, &longitude);
  lcd.setCursor(0, 1);
  Serial.print("Lat/Long: "); 
  lcd.print(latitude,5);
  Serial.print(latitude,5); 
  Serial.print(", ");
  lcd.setCursor(8, 1);
  Serial.println(longitude,5);
  lcd.print(longitude,5);
   _Latitude=latitude;
   _Longitude=longitude;
   _Vel=gps.f_speed_kmph();
  
  int year;
  byte month, day, hour, minute, second, hundredths;
  gps.crack_datetime(&year,&month,&day,&hour,&minute,&second,&hundredths);
  Serial.print("Date: "); Serial.print(month, DEC); Serial.print("/"); 
  Serial.print(day, DEC); Serial.print("/"); Serial.print(year);
  Serial.print("  Time: "); Serial.print(hour, DEC); Serial.print(":"); 
  Serial.print(minute, DEC); Serial.print(":"); Serial.print(second, DEC); 
  Serial.print(".");
   Serial.print("Speed(kmph): "); Serial.println(gps.f_speed_kmph());
  Serial.println();
}

static void smartdelay(unsigned long ms)
{
  unsigned long start = millis();
  do 
    while (uart_gps.available()){
      gps.encode(uart_gps.read());
    }
  while (millis() - start < ms);
}

static void Stop(unsigned long ms)
{
  unsigned long start = millis();   
  while (millis() - start < ms);
    {
      if (sim800.available())
        while (sim800.available())
          Serial.write(sim800.read());
    }    
}

//FUNCION para ver en consola lo que se envia y recibe
void respuesta(){
 while (sim800.available())
    Serial.write(sim800.read());

}
//****************************************************

// FUNCION para sacar el imei del dispositivo y usar este como id 
void imei(){
  sim800.println("AT+GSN");// get imei
  delay(300);
  while (sim800.available())
 {
  
      if(c=='\n')
        temp++; 
      if( temp==1)
        recepcion+=c;
      c=sim800.read();
      Serial.print(c);
        
 } 
  temp=0;
  recepcion.replace("\n","");
  recepcion.replace("\r","");
  id=recepcion;
  Serial.print("imei/id = ");
  delay(500);
  Serial.println(id);
  recepcion="";
  Serial.flush();
  sim800.flush();
  temp=0;
}
//******************************************************************

//FUNCION para conertir los datos en json y poder enviar por POST
void f_json(String Imei,String Lat,String Long, String v){
  Lat.replace("\r","");
  Lat.replace("\n","");
  Long.replace("\r","");
  Long.replace("\n","");
  v.replace("\r","");
  v.replace("\n","");
  _Data="{\"id\":\""+Imei+"\",\"lat\":\""+Lat+"\",\"long\":\""+Long+"\",\"vel\":\""+v+"\"}";
}
//***************************************************************

//FUNCION que conecata al modulo con un server para el envio por POST/json
void f_ConfigPost()
{
   sim800.listen();
   sim800.println("AT+SAPBR=3,1,\"APN\",\"CMNET\"");
   Stop(500);
  
   sim800.println("AT+SAPBR=1,1");
   Stop(3000);
  
   sim800.println("AT+HTTPINIT");
   Stop(500);
  
   sim800.println("AT+HTTPPARA=\"CID\",1");
   Stop(100);
  
   sim800.println("AT+HTTPPARA=\"URL\",\"http://nodeamica-demo.herokuapp.com/\"");
   Stop(150);
  
   sim800.println("AT+HTTPPARA=\"CONTENT\",\"application/json\"");
   Stop(3000);
  
}

void f_SendPost()
{
  sim800.listen();
  f_json(id,_Latitude,_Longitude,_Vel);
  Serial.print("data = ");
  delay(20);
  Serial.println(_Data);
  sim800.println("aT+HTTPDATA=100,10000");
  Stop(3000);

  sim800.println(_Data);
  Stop(10000);

  sim800.println("AT+HTTPACTION=1");
  Stop(5000);

  sim800.println("AT+HTTPREAD");
  Stop(1000);

}
//void AT_ok(String a)
//{
//  int Send=1;
//  int i=0;
//  while(Send)
//  {
//    sim800.println(a);
//    while (sim800.available())
//   {  
//       c=sim800.read();
//       Serial.print(c);
//       if(c=='\n')
//       {
//        vec[temp]=recepcion;
//        temp++;
//        recepcion="";
//       }
//       else
//        recepcion+=c;
//       
//    }
//    while(i<4)
//    {
//      if(vec[i]=="ok")
//        {
//          Serial.print("el comando at llego bacan");
//          Send=0;
//          break;
//        }
//      i++;
//    }
//  
//    Serial.flush();
//    sim800.flush();
//  }
//}



