package travellinktech.ticket_printer.printables;

import travellinktech.ticket_printer.TicketElement;
import java.awt.Graphics2D;

public abstract class PrintableElement {

  TicketElement element;
  
  // Fonts seem to need this multiplier to accurately position... 
  protected final double fontMultiplier = 0.8;

  public PrintableElement(TicketElement element) {
    this.element = element;
  }

  // Each printable element type implements this
  public abstract void drawOn(Graphics2D g);

}
