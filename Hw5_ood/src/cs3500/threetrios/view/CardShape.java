package cs3500.threetrios.view;

import java.awt.geom.Path2D;

public class CardShape extends Path2D.Double {
  public CardShape(double width, double height) {
    // Define the outline of the card
    this.moveTo(0, 0);
    this.lineTo(width, 0);
    this.lineTo(width, height);
    this.lineTo(0, height);
    this.closePath();
  }
}

