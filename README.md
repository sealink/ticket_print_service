ticket_print_service
====================
This app is a simple sinatra app designed to be run on PC's that want to
print to local printers, specifically for tickets.  It is a web-server that accepts cross-domain requests, so use it by
getting and posting via AJAX from your pages (i.e. to localhost).

Usage: GET /printers
====================
Returns a JSON list of strings representing the printers installed.

Usage: POST /print-tickets
==========================
That printTickets one is a bit complicated.  IT takes a JSON hash of the whole print job.

For what is expected see here: 

 * https://github.com/sealink/ticket_print_service/blob/master/lib/ticket_printer_web_server.rb
 * https://github.com/sealink/ticket_print_service/blob/master/lib/ticket_printer_service.rb

The top hash should have:
    ```javascript
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
To generate runnable jar (also a war) use warbler as so:
    # Builds a jar in the root folder
    warble executable war

    # Or if you need to:
    jruby -S warble executable war

This war file embeds WinStone and the whole JRuby interpreter.
WinStone is a lightweight java server.

Running
=======
To run the app, do (one of) the following:

    # Boots up using rack
    rackup

    # Boots up by running the sinatra directly
    ruby lib/ticket_printer_web_server.rb

    # Boot from the generated war
    java -jar ruby_ticket_printer_service.war --httpPort=4567


Note the .jar file in lib/java is generated from JavaTicketPrinter which
is a separate project. 
