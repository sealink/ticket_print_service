EXE Builder
===========
This project contains a collection of utilities and scripts to take the ticket printing web server
and build a windows installer, when run will install the server as a service.

Pre-setps:
==========
Install the programs under './tools':

  * Install Inno Script Studio v5.5.3
    ** You MUST install the Inno Pre Processor which allows variables in the script.
    ** You CAN install (but don't need) the full studio. It just gives a nice GUI around the script.

  * Install jSmooth v 0.9.9-7


Build the java server
=====================

You must build the java jar file, and place it, and dependencies in './build'.

This flattened directory structure is required since jSmooth always uses a relative classpath for the generated executable.


Build Jruby app into a 'war' with warbler in the "ticket_printer_service" project (not this one).
    bundle exec warble executable war

Copy war file here:
    ./src/ruby_ticket_printer_service.war


Steps to build a windows executable of this JRUBY server
========================================================

1) Double click on "build_executable.jsmooth" to open jsmooth with correct settings
2) Click menu Project > Compile
3) ./build/qt_ticket_service.exe will be created

IMPORTANT NOTE: jSmooth will not compile if working in files within a network share.

This executable wraps the JRUBY service as a windows service.
It is preconfigured to run port 1337, but you can use the jsmooth gui to change if desired.

Usage:
    # Installs itself into windows as a service
    qt_ticket_service.exe install

    # Start it up
    qt_ticket_service.exe start

    # Uninstal windows service
    qt_ticket_service.exe install


Steps to build a nice self-contained installer
==============================================

1) Double click on "installer_script.iss" to open Inno with correct settings
2) Click Menu Project > Compile
3) "./dist/Ticket Printer Service Installer.exe" will be created

This executable is a windows installer that will copy the qt_ticket_service.exe and run it 
to install it as a windows service.
