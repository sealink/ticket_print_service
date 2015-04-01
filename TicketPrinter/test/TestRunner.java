/*
 */

import java.awt.Image;
import java.awt.image.RenderedImage;
import java.awt.print.PrinterException;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import javax.imageio.ImageIO;
import org.apache.commons.codec.binary.Base64;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import travellinktech.ticket_printer.Ticket;
import travellinktech.ticket_printer.TicketElement;
import travellinktech.ticket_printer.TicketPrinter;
import travellinktech.ticket_printer.exceptions.NoSuchPrinterException;
import travellinktech.ticket_printer.exceptions.NoTicketPageSettingsAssigned;

/**
 *
 * @author adam
 */
public class TestRunner {

  public TestRunner() {
  }

  @BeforeClass
  public static void setUpClass() {
  }

  @AfterClass
  public static void tearDownClass() {
  }

  @Before
  public void setUp() {
  }

  @After
  public void tearDown() {
  }
  // TODO add test methods here.
  // The methods must be annotated with annotation @Test. For example:
  //
  // @Test
  // public void hello() {}

  @Test
  public void test_do_a_print() throws NoSuchPrinterException, NoTicketPageSettingsAssigned, PrinterException {
    TicketPrinter ticketPrinter = new TicketPrinter();
    ticketPrinter.setTicketPageSettings(87f, 57f, 2f);

    List<Ticket> tickets = new ArrayList();

    TicketElement element = new TicketElement();
    element.setX(10);
    element.setY(10);
    element.setValue("HELLO!");

    Ticket ticket = new Ticket();
    ticket.addElement(element);

    tickets.add(ticket);

    ticketPrinter.setPrinter(0); // Select printer 2 (index) 
    ticketPrinter.printTickets(tickets);
  }
  
  @Test
  public void test_base64() throws IOException {
    Image img = loadImgFromBase64("iVBORw0KGgoAAAANSUhEUgAAAFoAAADJCAIAAAAsOLK4AAAA0klEQVR42u3W\nsQ0AMAgDQWD/nZ2Onja6G+GlmFQB3HUSFdZIALbDdsgBptR2yCGHHHLIAeCT\njssCgEPr0Mohhxzg0NoOAJcFl0UOAPAN8++QA0yp7QBMqSlFDjkA8A1zaOWQ\nA1wW2wGYUlOKHHIA4Bvm0MohB7gsAKbUNww55ADAN8yhlUMOTKnHAuCyuCzI\nAabUdgDg0Dq0csgBLovtkAM55ACH1nbIIYccAL5hLgvYDgDAofVJl0MOOQB8\nw1wWwJSaUuSQQw45APBJd2jBdgDfe8UhWknoM7XCAAAAAElFTkSuQmCC\n");
    File outputfile = new File("/tmp/saved.png");
    ImageIO.write((RenderedImage) img, "png", outputfile);
  }
  
  
  private Image loadImgFromBase64(String imgEncodedInBase64) throws IOException {
    byte[] imgData = Base64.decodeBase64(imgEncodedInBase64);
    InputStream in = new ByteArrayInputStream(imgData);
    return ImageIO.read(in);
  }
}
