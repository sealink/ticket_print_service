package travellinktech.ticket_printer.exceptions;

public class NoSuchPrinterException extends Exception {
    public NoSuchPrinterException() {
        super();
    }

    public NoSuchPrinterException(String message) {
        super(message);
    }
}
