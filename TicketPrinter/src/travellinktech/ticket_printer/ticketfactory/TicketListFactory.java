package travellinktech.ticket_printer.ticketfactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import travellinktech.ticket_printer.Ticket;

public class TicketListFactory {

  /* Ticket data list...  (or why we need a factory)
   * 
   * It's a list of ticket data, 
   * where each ticket data is a list of element data,
   * and each element data is a map of element attributes
   * and each element attribute is a key/value pair of String/Object
   */
  List<List<Map<String, Object>>> ticketDataList;

  public TicketListFactory(List<List<Map<String, Object>>> ticketDataList) {
    this.ticketDataList = ticketDataList;
  }

  public List<Ticket> makeTicketList() {
    List<Ticket> tickets = new ArrayList<Ticket>();
    for (List<Map<String, Object>> ticketElementDataList : this.ticketDataList) {
      tickets.add(ticketFromElementDataList(ticketElementDataList));
    }
    return tickets;
  }

  
  private Ticket ticketFromElementDataList(List<Map<String, Object>>  ticketElementDataList) {
    TicketFactory ticketFactory = new TicketFactory(ticketElementDataList);
    return ticketFactory.makeTicket();
  }
}
