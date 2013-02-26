require 'java'
require './lib/java/JavaTicketPrinter.jar'

module J
  TicketPrinter = Java::AuComSealinkPrinting::TicketPrinter

  TicketPageSettings = Java::AuComSealinkDomain::TicketPageSettings

  Ticket = Java::AuComSealinkDomain::Ticket
  TicketElement = Java::AuComSealinkDomain::TicketElement
end

class TicketPrinterService
  def initialize(tickets, page_format)
    @tickets = tickets
    @page_format = page_format
  end
  
  def print(printer)
    java_ticket_list = java_tickets_from(@tickets)

    java_ticket_printer = J::TicketPrinter.new
    set_page_settings(java_ticket_printer)
    java_ticket_printer.printer = printer
    java_ticket_printer.printTickets(java_ticket_list)
  end
  
  private
  
  def java_tickets_from(tickets)
    java_ticket_list = java.util.ArrayList.new
   
    tickets.each do |ticket_data|
      java_ticket_list << java_ticket_from(ticket_data)
    end
    
    java_ticket_list
  end

  def java_ticket_from(ticket_data)
    java_ticket = J::Ticket.new
    ticket_data.each do |ticket_element|
      java_ticket.addElement(java_ticket_element_from(ticket_element))
    end
    java_ticket
  end
  
  def set_page_settings(java_ticket_printer)
    width = @page_format['width'].to_f
    height = @page_format['height'].to_f
    margin = @page_format['margin'].to_f

    java_ticket_printer.setTicketPageSettings(width, height, margin)
  end
  
  def java_ticket_element_from(ticket_element)
     java_ticket_element = J::TicketElement.new
     java_ticket_element.x = ticket_element['x'].to_i
     java_ticket_element.y = ticket_element['y'].to_i
     java_ticket_element.fontSize = ticket_element['font_size'].to_i
     
    java_ticket_element.bold = ticket_element['bold'] == "true"
    java_ticket_element.italic = ticket_element['italic'] == "true"

    java_ticket_element.value = ticket_element['value']
    
    java_ticket_element
  end
end
