package travellinktech.ticket_printer;

import java.awt.print.PrinterException;
import java.util.List;
import java.util.Map;
import travellinktech.ticket_printer.printables.NoSuchPrinterException;
import travellinktech.ticket_printer.printables.NoTicketPageSettingsAssigned;
import travellinktech.ticket_printer.ticketfactory.TicketListFactory;
import travellinktech.utils.NumberConvertor;

public class TicketPrintCommand {

  int printerIndex;
  TicketPageSettings ticketPageSettings;
  List<Ticket> tickets;

  public TicketPrintCommand() {}  

  public void setTicketsFromDataList(List<List<Map<String, Object>>>  ticketData) {
    TicketListFactory ticketListFactory = new TicketListFactory(ticketData);
    this.tickets = ticketListFactory.makeTicketList();
  }

  public void setTickets(List<Ticket> tickets) {
    this.tickets = tickets;
  }

  public void setTicketPageSettingsFromMap(Map pageFormatMap) {
    double width, height, margin;
    width = NumberConvertor.objectToDouble(pageFormatMap.get("width"));
    height =  NumberConvertor.objectToDouble(pageFormatMap.get("height"));
    margin =  NumberConvertor.objectToDouble(pageFormatMap.get("margin"));
    this.ticketPageSettings = new TicketPageSettings(width, height, margin);
  }

  public void setPrinter(int printerIndex) {
    this.printerIndex = printerIndex;
  }
  
  public void execute() throws PrinterException, NoSuchPrinterException, NoTicketPageSettingsAssigned {
    TicketPrinter ticketPrinter = new TicketPrinter();
    ticketPrinter.setTicketPageSettings(this.ticketPageSettings);
    ticketPrinter.setPrinter(this.printerIndex);
    ticketPrinter.printTickets(this.tickets);
  }
}
