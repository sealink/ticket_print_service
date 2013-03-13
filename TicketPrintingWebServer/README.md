Ticket Web Server
=================
This project is a java project designed to be run on PC's that want to print to local printers, 
specifically for tickets.  It is a web-server that accepts cross-domain requests, so use it by
getting and posting via AJAX from your pages (i.e. to localhost).

It uses Spark to be a small, simple, sinatra-inspired web-server -- with built-in integration
with the jetty web server.
http://www.sparkjava.com/

Usage: GET /printers
====================
Returns a JSON list of strings representing the printers installed.

Usage: POST /print-tickets
==========================
That printTickets one is a bit complicated.  IT takes a JSON hash of the whole print job.

For what is expected see the various factory methods for ticket generation, and the TicketPrintCommand class.

The top hash should have:
    ```
    {"printer": <printer name>, "page_format": <page format>, "tickets": <ticket data>}
    ```

The sub components are:
    <printer name> should be a string or integer index of the printer (as found from GET /printers)
    
    <page format> should be a hash with something like the following (in mm):
       {"width": 87.0, "height": 54.0, "margin": 2.0}
       
    <ticket data> is a list of tickets, where each ticket is a list of elements to print
       [ <ticket elements>, <second ticket elements>, ... ]
    
    Each <ticket element> has the following keys:
       {'x': 10, 'y': 20, 'font_size': 10, 'bold': false, 'italic': false, 'value': 'text to print'}

As per the old java print code, you can do an image with prefix "image:" in value.
However, this is currently incomplete as we're not handling base url properly (used to download the image for printing).
    
    
Building
========
Use netbeans 7 to build.  The project has an embedded jetty server, so no war file should be created.

It should put compiled classes in ./build and built jar in ./dist with dependent libraries.

Running
=======
To run the app, just java -jar it:

    cd ./dist
    java -jar JavaTicketPrinterService.jar 

You can change the port by passing in a single argument, such as:

    java -jar JavaTicketPrinterService.jar 4567
