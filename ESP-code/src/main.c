#include "freertos/FreeRTOS.h"
#include "freertos/task.h"
#include <stdio.h>


void app_main() { 
    while (true) {
        printf("hello\n");
        vTaskDelay(1000 / portTICK_PERIOD_MS);
    }
}
