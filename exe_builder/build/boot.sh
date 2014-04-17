#!/bin/bash

# Start on port 1337 (default is 1337, just showing so you can change if desirect)
java -cp "./*" travellinktech.TicketPrintingWebServer 1337 keystore.jks password
