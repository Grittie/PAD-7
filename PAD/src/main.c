#include "freertos/FreeRTOS.h"
#include "freertos/task.h"
#include "driver/gpio.h"
#include <stdio.h>


#define LED_PIN 21
#define HIGH 1
#define LOW 0

void app_main() { 
    while (true)
    {
        printf(" hello test ");
        gpio_set_direction(LED_PIN, GPIO_MODE_OUTPUT);
        gpio_set_level(LED_PIN, HIGH);
        vTaskDelay(1000 / portTICK_PERIOD_MS);
        gpio_set_level(LED_PIN, LOW);
        vTaskDelay(1000 / portTICK_PERIOD_MS);
    }
}
