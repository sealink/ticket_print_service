package travellinktech.ticket_printer.printables;

import java.awt.Font;
import java.awt.Graphics2D;
import travellinktech.ticket_printer.TicketElement;

class PrintableTextElement extends PrintableElement {

  String FONTNAME = "Verdana";

  public PrintableTextElement(TicketElement element) {
    super(element);
  }
  
  @Override
  public void drawOn(Graphics2D g) {
    Font currentFont = this.calculateFont(FONTNAME);
    g.setFont(currentFont);
    
    // Adjust y for maximum ascent of all printable characters
    int y = element.getY() + g.getFontMetrics(currentFont).getMaxAscent();

    g.drawString(element.getValue(), element.getX(), y);
  }
  
  private Font calculateFont(String fontName) {
    return new Font(fontName, getFontStyle(), getPrintableFontSize());
  }

  private int getFontStyle() {
    int style = Font.PLAIN;
    if (element.isBold()) {
      style |= Font.BOLD;
    }
    if (element.isItalic()) {
      style |= Font.ITALIC;
    }
    return style;
  }
  
  private int getPrintableFontSize() {
    return (int) (element.getFontSize() * super.fontMultiplier);
  }
}
