package travellinktech.ticket_printer;

import java.awt.print.PageFormat;
import java.awt.print.Paper;
import travellinktech.utils.NumberConvertor;

public class TicketPageSettings {

  double width; // = 87f;
  double height; // = 57f;
  double margin; // = 2f;
  // Not customisable at this stage (through refactor)
  double xOffset = 1.3f;
  double yOffset = 1.3f;
  double printWidth;
  double printHeight;

//  String baseUrl = "";
  public TicketPageSettings(double width, double height, double margin) {
    this.width = width;
    this.height = height;
    this.margin = margin;

    this.printWidth = this.width - (2 * Math.abs(xOffset));
    this.printHeight = this.height - (2 * Math.abs(this.yOffset));
  }

  // Returns a hard coded page format for the setup width, height
  // (it does not query the printer capabilities)
  public PageFormat getPageFormat() {
    PageFormat pf = new PageFormat();
    pf.setOrientation(PageFormat.PORTRAIT);
    pf.setPaper(this.getHardcodedPaper());
    return pf;
  }

  // Returns a hard coded 'paper', assuming the given width/height of ticket match the printer
  // (it does not query the printer capabilities)
  private Paper getHardcodedPaper() {
    Paper paper = new Paper();
    paper.setSize(NumberConvertor.mm2finch(this.width), NumberConvertor.mm2finch(this.height));
    paper.setImageableArea(
            NumberConvertor.mm2finch(this.xOffset),
            NumberConvertor.mm2finch(this.yOffset),
            NumberConvertor.mm2finch(this.printWidth),
            NumberConvertor.mm2finch(this.printHeight));
    return paper;
  }
}
