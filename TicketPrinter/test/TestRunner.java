/*
 */

import travellinktech.ticket_printer.Ticket;
import travellinktech.ticket_printer.TicketElement;
import travellinktech.ticket_printer.TicketPrinter;
import java.awt.print.PrinterException;
import java.util.ArrayList;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
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

    ticketPrinter.setPrinter(1); // Select printer 2 (index) 
    ticketPrinter.printTickets(tickets);
  }
}
