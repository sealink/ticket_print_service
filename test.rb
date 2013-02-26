require './lib/ticket_printer_service'

def test_ticket
ticket = [{'x' => 10, 'y' => 10, 'font_size' => 10, 'bold' => false, 'italics' => false, 'value' => 'hello mate'}]
end

def test_printer
 TicketPrinterService.new([test_ticket])
end

