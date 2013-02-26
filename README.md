ticket_print_service
====================
This app is a simple sinatra app designed to be run on PC's that want to
print to local printers, specifically for tickets.

It is a web-server that accepts cross-domain requests, so use it by
getting and posting via AJAX from your pages (i.e. to localhost).

To generate runnable jar (also a war) use warbler as so:
  warble executable war

  # Or if you need to:
  jruby -S warble executable war

This war file embeds WinStone and the whole JRuby interpreter.
WinStone is a lightweight java server.

To run the app, do (one of) the following:
  # Boots up using rack
  rackup

  # Boots up by running the sinatra directly
  ruby lib/ticket_printer_web_server.rb

  # Boot from the generated war
  java -jar ruby_ticket_printer_service.war --httpPort=4567


Note the .jar file in lib/java is generated from JavaTicketPrinter which
is a separate project. 
