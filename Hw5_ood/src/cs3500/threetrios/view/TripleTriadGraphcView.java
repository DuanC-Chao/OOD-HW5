package cs3500.threetrios.view;

import java.awt.*;
import java.io.IOException;
import java.util.function.Consumer;

import javax.swing.*;
import javax.swing.border.LineBorder;

import cs3500.threetrios.model.EPlayer;
import cs3500.threetrios.model.ReadOnlyTripleTriadModel;

public class TripleTriadGraphcView extends JFrame implements TripleTriadView {

  /**
   * The read-only model, stored as a field in view.
   */
  private final ReadOnlyTripleTriadModel model;

  private IHandPanel playerOneHandPanel;
  private IHandPanel playerTwoHandPanel;

  private IGridPanel gridPanel
    ;
  private Consumer<Move> moveHandler;  // 用于处理用户动作的事件处理器

  public TripleTriadGraphcView(ReadOnlyTripleTriadModel model) {

    // Set title of the window
    setTitle("Triple Triad Game");

    // Set Feel and Look
    configFeelAndLook();

    this.model = model;

    // Dynamically decide the width
    int width = model.getGridClone().getColNumber() * 100 + 400;

    if(width > 1920) {
      width = 1920;
    }

    int height = model.getPlayerHand(1).size() * 150 + 20;

    if(height > 1080) {
      height = 1080;
    }

    // For better appearance, Aspect Ratio is about 1.8
    setSize(width, height);
    // Let the process shut down when the window closed.
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    // Add Border Layout.
    setLayout(new BorderLayout());

    // Add the first HandPanel.
    playerOneHandPanel = new HandPanel(EPlayer.PLAYER_ONE, model);
    add((Component) playerOneHandPanel, BorderLayout.WEST);

    // Add the second HandPanel.
    playerTwoHandPanel = new HandPanel(EPlayer.PLAYER_TWO, model);
    add((Component) playerTwoHandPanel, BorderLayout.EAST);

    // Use ScrollPane to wrap GridPanel.
    gridPanel = new GridPanel(model);
    FixSizerPanel<IGridPanel> fixSizerPanel = new FixSizerPanel<>(gridPanel, width - 400, height, ElementColor.GRID_BACKGROUND_COLOR);
    add(fixSizerPanel, BorderLayout.CENTER);

    // Refresh rendering for the first time
    try {
      this.render(null);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  // In Graphic view, render() do not rely on out
  // Just call refresh() for each components.
  @Override
  public void render(Appendable out) throws IOException {
    this.playerOneHandPanel.refresh();
    this.playerTwoHandPanel.refresh();
    this.gridPanel.refresh();
    // Display should be called after all components' status is up to date.
    display();
  }

  // 设置用户动作的事件处理器
  @Override
  public void setMoveEventHandler(Consumer<Move> delegate) {
    this.moveHandler = delegate;
  }

  /**
   * Display the Frame.
   */
  private void display() {
    setVisible(true);
  }

  /**
   * Set the Feel and Look for the view.
   * Notice:
   * Using "Nimbus" style, Cross-platform compatibility is not guaranteed.
   * Developed and Tested on MacOS.
   */
  private void configFeelAndLook() {
    try {
      for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
        if ("Nimbus".equals(info.getName())) {
          UIManager.setLookAndFeel(info.getClassName());
          break;
        }
      }
    } catch (Exception e) {
      e.printStackTrace();
    }

    UIManager.put("Button.border", new LineBorder(Color.BLACK, 10));
  }
}
