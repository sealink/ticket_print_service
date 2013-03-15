This project is broken up into three parts:

  1. Ticket Printer: a java project that can return the installed printers, and print simple tickets.

  2. Ticket Web Server: a java project designed to be run on PC's that want to print to local printers, 
     specifically for tickets.  It is a web-server that accepts cross-domain requests, so use it by
     getting and posting via AJAX from your pages (i.e. to localhost).

  3. EXE Builder: a collection of utilities and scripts to take the above two projects and build a windows installer,
     which when run will install the Ticket Web Server as a service.

To boot up a local copy, do the following:

    # Under unix boot the jars using java cmd
    cd ./exe_builder/build
    ./boot.sh

Or under windows:

    # Boot the jars using java cmd
    cd ./exe_builder/build
    ./boot.bat

    # Or, directly run console
    cd ./exe_builder/build
    qt_ticket_console.exe       # for direct to console

    # Or, install the service
    cd ./exe_builder/build
    qt_ticket_service.exe install  
    qt_ticket_service.exe run

    # Or, run the installer on windows
    cd ./exe_builder/dist
    Ticket Printer Service Installer.exe

    
