#include <SoftwareSerial.h>
SoftwareSerial sim800(12, 13); // RX, TX

//AT+GSN  
//860719024889580

String recepcion="";
char c;
int temp=0;
String id;
String _Data="";
String _Latitude="-16.409047";
String _Longitude="-71.537451";

//FUNCION para ver en consola lo que se envia y recibe
void respuesta(){
 while (sim800.available())
    Serial.write(sim800.read());
  Serial.flush();
  sim800.flush();
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
  Serial.println(id);
  recepcion="";
  Serial.flush();
  sim800.flush();
}
//******************************************************************

void f_json(String Imei,String Lat,String Long){
  _Data="{\"id\":\""+Imei+"\",\"lat\":\""+Lat+"\",\"long\":\""+Long+"\"}";
}

void f_SendPost()
{
   imei();
   Stop(100);
   sim800.println("AT+SAPBR=3,1,\"APN\",\"CMNET\"");
   Stop(500);
   
   sim800.println("AT+SAPBR=1,1");
   delay(5000);
   Stop(100);
   
   sim800.println("AT+HTTPINIT");
   Stop(500);
   
   sim800.println("AT+HTTPPARA=\"CID\",1");
   Stop(100);
   
   sim800.println("AT+HTTPPARA=\"URL\",\"http://nodeamica-demo.herokuapp.com/\"");
   Stop(150);
   
   sim800.println("AT+HTTPPARA=\"CONTENT\",\"application/json\"");
   Stop(3000);
   
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


void setup()
{
 // Open serial communications and wait for port to open:
 Serial.begin(9600);
 sim800.begin(4800);
 Serial.println("esperando a que cargue");
 delay(2000);
 
 f_SendPost();
 f_json(id,_Latitude,_Longitude);
 Serial.print("data = ");
 Serial.println(_Data);
// Serial.println("{\"id\":\"860719024889580\",\"lat\":\"-16.409047\",\"long\":\"-71.537451\"}");
//String nuevo="{\"id\":\"860719024889580\",\"lat\":\"-16.409047\",\"long\":\"-71.537451\"}";
// if(_Data.equals("{\"id\":\"860719024889580\",\"lat\":\"-16.409047\",\"long\":\"-71.537451\"}"));
//  Serial.print("son la MISMA M..");
// delay(20);
 sim800.println("aT+HTTPDATA=80,10000");
 Stop(3000);
 
 //sim800.println("{\"id\":\"860719024889580\",\"lat\":\"-16.409047\",\"long\":\"-71.537451\"}");
// sim800.println("{\"id\":\""+id+"\"}");
 sim800.println(_Data);
 Stop(10000);
 
 sim800.println("AT+HTTPACTION=1");
 Stop(5000);
 
 sim800.println("AT+HTTPREAD");
 Stop(1000);
 
  sim800.println("aT+HTTPDATA=40,10000");
 Stop(3000);
 
 sim800.println("{\"OOO\": \"joder\"}");
 Stop(9000);
 
 sim800.println("AT+HTTPACTION=1");
 Stop(5000);
 
 sim800.println("AT+HTTPREAD");
 Stop(1000);
 
 sim800.println("AT+HTTPTERM");
 Stop(1000);
 
 sim800.println("AT+SAPBR=0,1");
 Stop(1000);
 
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
