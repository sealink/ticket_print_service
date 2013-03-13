package travellinktech.ticket_printer;

public class TicketElement {
  
  private int x = 0;
  private int y = 0;
  private int fontSize = 10;
  private boolean bold = false;
  private boolean italic = false;
  private String value = "";

  public boolean isImage() {
     return this.value.startsWith("image:");
  }
  
  public int getX() {
    return x;
  }

  public void setX(int x) {
    this.x = x;
  }

  public int getY() {
    return y;
  }

  public void setY(int y) {
    this.y = y;
  }

  public int getFontSize() {
    return fontSize;
  }

  public void setFontSize(int fontSize) {
    this.fontSize = fontSize;
  }

  public boolean isBold() {
    return bold;
  }

  public void setBold(boolean bold) {
    this.bold = bold;
  }

  public boolean isItalic() {
    return italic;
  }

  public void setItalic(boolean italic) {
    this.italic = italic;
  }

  public String getValue() {
    return value;
  }

  public String getImageValue() {
    return this.value.substring(6);
  }
  
  public void setValue(String value) {
    this.value = value;
  }

    
}
