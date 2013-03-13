package travellinktech.ticket_printer.ticketfactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import travellinktech.ticket_printer.Ticket;
import travellinktech.ticket_printer.TicketElement;

public class TicketFactory {
  /* A single tickets data...  (or why we need a factory)
   * 
   * Each ticket data is a list of element data,
   * and each element data is a map of element attributes
   * and each element attribute is a key/value pair of String/Object
   */
  List<Map<String, Object>> ticketElementDataList;
  
  public TicketFactory(List<Map<String, Object>> ticketElementDataList) {
    this.ticketElementDataList = ticketElementDataList;
  }
  
  public Ticket makeTicket() {
    List<TicketElement> elements = new ArrayList<TicketElement>();
    
    for (Map<String, Object> attributes : this.ticketElementDataList) {
      TicketElement element = ticketElementFromAttributes(attributes);
      elements.add(element);
    }
    
    return new Ticket(elements);
  }
  
  private TicketElement ticketElementFromAttributes(Map<String, Object> attributes) {
    TicketElementFactory ticketElementFactory = new TicketElementFactory(attributes);
    return ticketElementFactory.makeTicketElement();
  }
}
