package cs3500.threetrios.view;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import cs3500.threetrios.model.CellType;
import cs3500.threetrios.model.ReadOnlyTripleTriadModel;

public class GameGridPanel extends JPanel {
  private final ReadOnlyTripleTriadModel model;

  public GameGridPanel(ReadOnlyTripleTriadModel model) {
    this.model = model;
    this.addMouseListener(new MouseAdapter() {
      @Override
      public void mouseClicked(MouseEvent e) {
        // Determine cell coordinates based on mouse click and grid size
        int cellSize = Math.min(getWidth(), getHeight()) / model.getGrid().length;
        int col = e.getX() / cellSize;
        int row = e.getY() / cellSize;
        System.out.println("Clicked on cell: (" + row + ", " + col + ")");
      }
    }
    );
  }

  @Override
  protected void paintComponent(Graphics g) {
    super.paintComponent(g);
    Graphics2D g2d = (Graphics2D) g;
    int cellSize = Math.min(getWidth(), getHeight()) / model.getGrid().length;
    Font font = new Font("Arial", Font.BOLD, cellSize / 3);
    g2d.setFont(font);

    for (int row = 0; row < model.getGrid().length; row++) {
      for (int col = 0; col < model.getGrid()[row].length; col++) {
        // Set color based on cell type (e.g., grey for empty, yellow for card cell)
        g2d.setColor(model.getGrid()[row][col].getType() == CellType.CARD_CELL ?
                Color.YELLOW : Color.GRAY);
        g2d.fillRect(col * cellSize, row * cellSize, cellSize, cellSize);

        // Draw border and text as needed
        g2d.setColor(Color.BLACK);
        g2d.drawRect(col * cellSize, row * cellSize, cellSize, cellSize);
      }
    }
  }

  public void updatePanel() {
    repaint();
  }
}
