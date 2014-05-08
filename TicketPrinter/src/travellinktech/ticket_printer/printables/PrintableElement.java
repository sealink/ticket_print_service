package travellinktech.ticket_printer.printables;

import travellinktech.ticket_printer.TicketElement;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;
import java.io.IOException;
import java.net.URL;
import javax.imageio.ImageIO;

public class PrintableElement {

  TicketElement element;

  String FONTNAME = "Verdana";
  private final double fontMultiplier = 0.8;   // Fonts seem to need this multiplier to accurately position... 

  public PrintableElement(TicketElement element) {
    this.element = element;
  }

  public void drawOn(Graphics2D g) {
    Font currentFont = this.calculateFont(FONTNAME);
    g.setFont(currentFont);

    // Add image, or text
    if (element.isImage()) {
      drawImage(g);
    } else {
      drawString(g, currentFont);
    }
  }

  /* 
   * Handle Font setting
   */
  private Font calculateFont(String fontName) {
    int fontStyle = calculateFontStyle(element.isBold(), element.isItalic());
    return new Font(fontName, fontStyle, (int) (element.getFontSize() * this.fontMultiplier));
  }

  private int calculateFontStyle(boolean bold, boolean italic) {
    int style = Font.PLAIN;
    if (bold) {
      style |= Font.BOLD;
    }
    if (italic) {
      style |= Font.ITALIC;
    }
    return style;
  }

  /* 
   * Draw an image
   */
  private void drawImage(Graphics2D g) {
    Image img = loadImage(element.getImageValue());
    if (img != null) {
      addImage(g, img, element.getX(), element.getY());  // remainder is the image url
    }
  }

  private Image loadImage(String imageUrlString) {
    Image img = null;
    try {
      URL url = new URL(imageUrlString);
      img = ImageIO.read(url);
    } catch (IOException ex) {
    }

    return img;
  }

  private void addImage(Graphics2D g, Image img, int x, int y) {
    double printerResolution = calculatePrinterResolution();

    int width = (int) (img.getWidth(null) / printerResolution * fontMultiplier);
    int height = (int) (img.getHeight(null) / printerResolution * fontMultiplier);

    g.drawImage(img, x, y, width, height, Color.red, null);
  }

  // Printer resolution -- 72 dots per inch   
  private double calculatePrinterResolution() {
    return 72.0 / 25.4;
  }

  /*
   * Draw a string
   */
  private void drawString(Graphics2D g, Font font) {
    // Adjust y for maximum ascent of all printable characters
    int y = element.getY() + g.getFontMetrics(font).getMaxAscent();

    g.drawString(element.getValue(), element.getX(), y);
  }
}
