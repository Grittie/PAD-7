package src;


import com.aldebaran.qi.Application;

import java.util.Scanner;
import java.util.concurrent.CountDownLatch;

public class Main {

    public static void main(String[] args) throws Exception {

        MQTT mqtt = new MQTT();

        mqtt.mqttClient();

    }
}