EESchema Schematic File Version 2
LIBS:power
LIBS:device
LIBS:transistors
LIBS:conn
LIBS:linear
LIBS:regul
LIBS:74xx
LIBS:cmos4000
LIBS:adc-dac
LIBS:memory
LIBS:xilinx
LIBS:microcontrollers
LIBS:dsp
LIBS:microchip
LIBS:analog_switches
LIBS:motorola
LIBS:texas
LIBS:intel
LIBS:audio
LIBS:interface
LIBS:digital-audio
LIBS:philips
LIBS:display
LIBS:cypress
LIBS:siliconi
LIBS:opto
LIBS:atmel
LIBS:contrib
LIBS:valves
LIBS:display_lcd
LIBS:Arduino_Pro_Mini-cache
LIBS:sim800l
LIBS:gms-g9
LIBS:ArduProMiniTKB
LIBS:ardupromini
LIBS:minipro
LIBS:minipro5v
EELAYER 25 0
EELAYER END
$Descr A4 11693 8268
encoding utf-8
Sheet 1 1
Title ""
Date ""
Rev ""
Comp ""
Comment1 ""
Comment2 ""
Comment3 ""
Comment4 ""
$EndDescr
$Comp
L gms-g9 U?
U 1 1 57D9ECAF
P 3100 3950
F 0 "U?" H 3100 3550 60  0000 C CNN
F 1 "gms-g9" H 3100 4350 60  0000 C CNN
F 2 "" H 3100 3550 60  0001 C CNN
F 3 "" H 3100 3550 60  0001 C CNN
	1    3100 3950
	1    0    0    -1  
$EndComp
$Comp
L SIM800L U?
U 1 1 57D9ED1C
P 3100 5300
F 0 "U?" H 3100 5700 60  0000 C CNN
F 1 "SIM800L" H 3100 4900 60  0000 C CNN
F 2 "" H 3100 5700 60  0001 C CNN
F 3 "" H 3100 5700 60  0001 C CNN
	1    3100 5300
	1    0    0    -1  
$EndComp
$Comp
L LCD16X2 DS?
U 1 1 57D9ED77
P 7900 1900
F 0 "DS?" H 7100 2300 50  0000 C CNN
F 1 "LCD16X2" H 8600 2300 50  0000 C CNN
F 2 "WC1602A" H 7900 1850 50  0000 C CIN
F 3 "" H 7900 1900 50  0000 C CNN
	1    7900 1900
	1    0    0    -1  
$EndComp
$Comp
L MINIPRO5V uP?
U 1 1 57D9F64C
P 3050 2100
F 0 "uP?" H 3050 2000 60  0000 C CNN
F 1 "MINIPRO5V" H 3050 1300 60  0000 C CNN
F 2 "" H 3050 1300 60  0000 C CNN
F 3 "" H 3050 1300 60  0000 C CNN
	1    3050 2100
	1    0    0    -1  
$EndComp
Text GLabel 2550 4000 0    60   Input ~ 0
Tx0
Text GLabel 2550 4100 0    60   Input ~ 0
Rx0
$Comp
L R R?
U 1 1 57DA0D9A
P 1700 2100
F 0 "R?" V 1780 2100 50  0000 C CNN
F 1 "R" V 1700 2100 50  0000 C CNN
F 2 "" V 1630 2100 50  0000 C CNN
F 3 "" H 1700 2100 50  0000 C CNN
	1    1700 2100
	1    0    0    -1  
$EndComp
$Comp
L R R?
U 1 1 57DA0E27
P 1450 2100
F 0 "R?" V 1530 2100 50  0000 C CNN
F 1 "R" V 1450 2100 50  0000 C CNN
F 2 "" V 1380 2100 50  0000 C CNN
F 3 "" H 1450 2100 50  0000 C CNN
	1    1450 2100
	1    0    0    -1  
$EndComp
Text GLabel 4550 4550 0    60   Input ~ 0
Tx0
Text GLabel 4550 4350 0    60   Input ~ 0
Rx0
$Comp
L +3.3V #PWR?
U 1 1 57DA0F8A
P 1150 3450
F 0 "#PWR?" H 1150 3300 50  0001 C CNN
F 1 "+3.3V" H 1150 3590 50  0000 C CNN
F 2 "" H 1150 3450 50  0000 C CNN
F 3 "" H 1150 3450 50  0000 C CNN
	1    1150 3450
	1    0    0    -1  
$EndComp
$Comp
L Earth #PWR?
U 1 1 57DA0FAA
P 1350 4150
F 0 "#PWR?" H 1350 3900 50  0001 C CNN
F 1 "Earth" H 1350 4000 50  0001 C CNN
F 2 "" H 1350 4150 50  0000 C CNN
F 3 "" H 1350 4150 50  0000 C CNN
	1    1350 4150
	1    0    0    -1  
$EndComp
$Comp
L C C?
U 1 1 57DA1030
P 1450 3800
F 0 "C?" H 1475 3900 50  0000 L CNN
F 1 "C" H 1475 3700 50  0000 L CNN
F 2 "" H 1488 3650 50  0000 C CNN
F 3 "" H 1450 3800 50  0000 C CNN
	1    1450 3800
	1    0    0    -1  
$EndComp
$Comp
L C C?
U 1 1 57DA109F
P 1750 3800
F 0 "C?" H 1775 3900 50  0000 L CNN
F 1 "C" H 1775 3700 50  0000 L CNN
F 2 "" H 1788 3650 50  0000 C CNN
F 3 "" H 1750 3800 50  0000 C CNN
	1    1750 3800
	1    0    0    -1  
$EndComp
$EndSCHEMATC
