package travellinktech.ticket_printer.printables;

import travellinktech.ticket_printer.Ticket;
import travellinktech.ticket_printer.TicketElement;
import travellinktech.utils.NumberConvertor;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.AffineTransform;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import org.apache.commons.codec.binary.Base64;

/**
 * This class represents a printable ticket.
 *
 * Printable interface requires #print to be implemented (called when printed)
 */
public class PrintableTickets implements Printable {

//  private List<TicketElement> elements;
  private List<Ticket> tickets;

  public PrintableTickets(List<Ticket> tickets) {
    this.tickets = tickets;
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
    for (TicketElement element : ticket.getElements()) {
      printableElementFor(element).drawOn(g);
    }

    return (PAGE_EXISTS);
  }

  private PrintableElement printableElementFor(TicketElement element) {
    if (element.isImage()) {
      Image img = loadImage(element);
      if (img == null) {
        return new PrintableNullElement(element); 
      } else {
        return new PrintableImageElement(element, img);
      }
    } else {
      return new PrintableTextElement(element);
    }
  }

  private Image loadImage(TicketElement element) {
    Image img = null;
    try {
      if (element.isImageBase64()) {
        String imgEncodedInBase64 = element.getImageValue();
        img = loadImgFromBase64(imgEncodedInBase64);
      } else {
        img = loadImageFromUrl(element.getImageValue());
      }
    } catch (IOException ex) {
      Logger logger = Logger.getLogger(PrintableElement.class.getName());
      logger.log(Level.SEVERE, "Could not load image:\n" + element.getImageValue(), ex);
    }    
    return img;
  }
  
  private Image loadImageFromUrl(String imageUrlString) throws IOException {
    URL url = new URL(imageUrlString);
    return ImageIO.read(url);
  }

  private Image loadImgFromBase64(String imgEncodedInBase64) throws IOException {
    byte[] imgData = Base64.decodeBase64(imgEncodedInBase64);
    InputStream in = new ByteArrayInputStream(imgData);
    return ImageIO.read(in);
  }

  // Configure the graphics canvas
  private Graphics2D configureGraphics(Graphics graphics, PageFormat pageFormat) {
    Graphics2D g = (Graphics2D) graphics;

    // Translate to fit to printable area
    g.translate(pageFormat.getImageableX(), pageFormat.getImageableY());

    /**
     * The AffineTransform class represents a 2D affine transform that performs
     * a linear mapping from 2D coordinates to other 2D coordinates that
     * preserves the "straightness" and "parallel" of lines.
     */
    AffineTransform affineTransform = new AffineTransform();
    affineTransform.scale(NumberConvertor.mm2finch(1), NumberConvertor.mm2finch(1));
    g.transform(affineTransform);

    return g;
  }

}
