package travellinktech.ticket_printer.printables;

import travellinktech.ticket_printer.Ticket;
import travellinktech.ticket_printer.TicketElement;
import travellinktech.utils.NumberConvertor;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.util.List;

/**
 * A 'Printable' thing must have a 'print' method which is called when printed.
 *
 * This class represents a printable ticket.
 */
public class PrintableTickets implements Printable {

//  private List<TicketElement> elements;
  private List<Ticket> tickets;

  public PrintableTickets(List<Ticket> tickets) {
    this.tickets = tickets;
//    this.elements = ticket.getElements();
  }

  @Override
  public int print(Graphics graphics, PageFormat pageFormat, int pageIndex) throws PrinterException {
    if (pageIndex >= tickets.size()) {
      return (NO_SUCH_PAGE);
    }
    
    Graphics2D g = configureGraphics(graphics, pageFormat);

    // Pull out page of ticket
    Ticket ticket = tickets.get(pageIndex);
    
    // Iterate over its elements
    List<TicketElement> elements = ticket.getElements();
    for (TicketElement element : elements) {
      new PrintableElement(element).drawOn(g);
    }

    return (PAGE_EXISTS);
  }


  // Configure the graphics canvas
  private Graphics2D configureGraphics(Graphics graphics, PageFormat pageFormat) {
    Graphics2D g = (Graphics2D) graphics;

    // Translate to fit to printable area
    g.translate(pageFormat.getImageableX(), pageFormat.getImageableY());

    /**
     * The AffineTransform class represents a 2D affine transform that
     * performs a linear mapping from 2D coordinates to other 2D coordinates
     * that preserves the "straightness" and "parallelness" of lines.
     */
    AffineTransform affineTransform = new AffineTransform();
    affineTransform.scale(NumberConvertor.mm2finch(1), NumberConvertor.mm2finch(1));
    g.transform(affineTransform);

    return g;
  }

}