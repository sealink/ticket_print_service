package travellinktech.ticket_printer;

import java.util.ArrayList;
import java.util.List;

/* A ticket is defined by a list of elements
 * 
 * Each element has attributes that define it's position, font style and text.
 */
public class Ticket {
  
  List<TicketElement> elements;
  
  public Ticket() {
    this.elements = new ArrayList<TicketElement>();
  }
  
  public Ticket(List<TicketElement> elements) {
    this.elements = elements;
  }
  
  public List<TicketElement> getElements() {
    return this.elements;
  }
  
  public void addElement(TicketElement element) {
    this.elements.add(element);
  }
}
