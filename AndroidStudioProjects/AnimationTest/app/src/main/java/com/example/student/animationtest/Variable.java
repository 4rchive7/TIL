package com.example.student.animationtest;

public class Variable {

    final static int CARON = 0;
    final static int ACCEL = 1;
    final static int DECEL = 2;
    final static int SAFETYDIS = 3;
    final static int SNOOZE = 4;
    final static int SPEED = 5;
    final static int BATTERY = 6;
    final static int BATTERYEFFICIENCY = 7;
    final static int TIMER = 8;
    final static int DISTANCE = 9;


    final static int avrEffi = 6;// 에너지 효율 : 6.0km/kWh
    final static int totalBatterySize  = 25000; //kWh
    final static int effiSpeed = 60; //km/h
    final static double effiPerWh = 1.67;


    final static double speedPerRpm = 0.024;
    final static int totalRpm = 512;
    final static int totalBreak = 512;
    final static int perRpm = 512/9;


    static String setAttributeName[] = {
            "CARID", "ACCEL", "DECEL", "SAFETYDIS", "SNOOZE", "SPEED", "BATTERY"};


    boolean httpStart = false;

}
