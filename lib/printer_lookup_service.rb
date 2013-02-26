require 'java'
require './lib/java/JavaTicketPrinter.jar'

java_import Java::AuComSealinkPrinting::PrintServiceLocator

module PrinterLookupService
  def self.lookup_printers
    print_services = PrintServiceLocator.new.all
    print_services.each_with_index.map { |print_service, index|
      print_service.name
    }
  end
end
