#include "freertos/FreeRTOS.h"
#include "freertos/task.h"
#include "driver/gpio.h"
#include <stdio.h>


#define LOW 0
#define HIGH 1
#define DELAY 25


// This funtion designates the specific pins on the esp for input or output.
void setup(uint16_t buttons[], uint8_t sizeButtons) {
    for (int i = 0; i < sizeButtons; ++i) {
        gpio_set_direction(buttons[i], GPIO_MODE_INPUT);}
}

// Checks which buttons are pressed and returns that button.
void button(uint16_t buttons[], uint8_t sizeButtons) {
    uint16_t buttonPressed[sizeButtons];
    // Loops through all the buttons checking if one is pressed and if one is pressed it adds it to the buttonPressed variable.
    for (int i = 0; i < sizeButtons; i++) {
        buttonPressed[i] = gpio_get_level(buttons[i]);
    }
    // Loops through all the buttons and if it sees that one is pressed is returns it. 
    for (int i = 0; i < sizeButtons; i++) {
        if (buttonPressed[i] == HIGH) {
            // Print the value of button that is pressed.
            printf("%s%d\n","Button pressed: ", i + 1);
        }
    }
}


void app_main() {
    // Defining the variables for later use.
    uint16_t buttons[3] = {7, 15, 16};
    uint8_t sizeButtons = 3;
    // Calls setup for setting up the pins for input and output.
    setup(buttons, sizeButtons);
    // Main game loop.
    while (true) {
        button(buttons, sizeButtons);
        // Delay by varibale DELAY.
        vTaskDelay(DELAY / portTICK_PERIOD_MS);
    }
}