package travellinktech.ticket_printer.printables;

import java.awt.Graphics2D;

import travellinktech.ticket_printer.TicketElement;

class PrintableNullElement extends PrintableElement {

  public PrintableNullElement(TicketElement element) {
    super(element);
  }

  @Override
  public void drawOn(Graphics2D g) {
    // null object
  }

}
