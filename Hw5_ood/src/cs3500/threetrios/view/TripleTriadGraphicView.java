package cs3500.threetrios.view;

import java.awt.Color;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.ContainerOrderFocusTraversalPolicy;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.util.function.Consumer;
import java.util.function.Supplier;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.JOptionPane;
import javax.swing.border.LineBorder;

import cs3500.threetrios.model.EPlayer;
import cs3500.threetrios.model.ReadOnlyTripleTriadModel;

/**
 * The pure graphic view for TripleTriad game.
 */
public class TripleTriadGraphicView extends JFrame implements TripleTriadView {

  private final IHandPanel playerOneHandPanel;
  private final IHandPanel playerTwoHandPanel;

  private final IGridPanel gridPanel;

  private final EPlayer player;

  /**
   * The default constructor. Sets up the Frame and the Panels, and runs an initial rendering.
   * @param model The model to be assigned to the view.
   * @param viewPlayer The player of the view.
   * @throws IllegalArgumentException if the currentPlayer is null
   */
  public TripleTriadGraphicView(ReadOnlyTripleTriadModel model, EPlayer viewPlayer) {

    if (viewPlayer == null) {
      throw new IllegalArgumentException("Current player cannot be null");
    }

    // Set title of the window
    setTitle("Triple Triad Game");

    setFocusTraversalPolicy(new ContainerOrderFocusTraversalPolicy());

    // Set Feel and Look
    configFeelAndLook();

    this.player = viewPlayer;

    // Dynamically decide the width
    int width = model.getGridClone().getColNumber() * 100 + 400;

    if (width > 1920) {
      width = 1920;
    }

    int height = model.getPlayerHand(1).size() * 150 + 20;

    if (height > 1080) {
      height = 1080;
    }

    // For better appearance, Aspect Ratio is about 1.8
    setSize(width, height);
    // Let the process shut down when the window closed.
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    // Add Border Layout.
    setLayout(new BorderLayout());

    // Add the first HandPanel.
    if (viewPlayer == EPlayer.PLAYER_ONE) {
      playerOneHandPanel = new HandPanel(EPlayer.PLAYER_ONE, model, true);
    } else {
      playerOneHandPanel = new HandPanel(EPlayer.PLAYER_ONE, model, false);
    }
    SwingUtilities.invokeLater(() -> {
      add((Component) playerOneHandPanel, BorderLayout.WEST);
    });

    // Add the second HandPanel.
    if (viewPlayer == EPlayer.PLAYER_TWO) {
      playerTwoHandPanel = new HandPanel(EPlayer.PLAYER_TWO, model, true);
    } else {
      playerTwoHandPanel = new HandPanel(EPlayer.PLAYER_TWO, model, false);
    }
    SwingUtilities.invokeLater(() -> {
      add((Component) playerTwoHandPanel, BorderLayout.EAST);
    });

    // Use ScrollPane to wrap GridPanel.
    gridPanel = new GridPanel(model, viewPlayer);
    FixSizerPanel<IGridPanel> fixSizerPanel = new FixSizerPanel<>(gridPanel,
            width - 400, height, ElementColor.GRID_BACKGROUND_COLOR);
    SwingUtilities.invokeLater(() -> {
      add(fixSizerPanel, BorderLayout.CENTER);
    });

    // Refresh rendering for the first time
    try {
      this.render(null);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }

    if (viewPlayer == EPlayer.PLAYER_ONE) {
      showPopUp("Your Turn, Player One");
    }
  }

  // In Graphic view, render() do not rely on out
  // Just call refresh() for each components.
  @Override
  public void render(Appendable out) throws IOException {
    refresh();
    // Display should be called after all components' status is up to date.
    display();
  }

  /**
   * Helper method to render(), refresh all components.
   */
  private void refresh() {
    this.playerOneHandPanel.refresh();
    this.playerTwoHandPanel.refresh();
    this.gridPanel.refresh();
    System.out.println("View Refreshed");
  }

  @Override
  public void setMoveEventHandler(Consumer<Pick> handCardDelegate, Consumer<Move> cellDelegate) {
    this.playerOneHandPanel.takeToBeDispatchedDelegate(handCardDelegate);
    this.playerTwoHandPanel.takeToBeDispatchedDelegate(handCardDelegate);
    this.gridPanel.takeToBeDispatchedDelegate(cellDelegate, this::refresh);
  }

  @Override
  public void setKeyHandler(Consumer<KeyPress> keyHandler) {
    this.addKeyListener(new KeyListener() {
      @Override
      public void keyTyped(KeyEvent e) {
        keyHandler.accept(new KeyPress(e.getKeyChar(), player));
      }

      @Override
      public void keyPressed(KeyEvent e) {
        // Do nothing
      }

      @Override
      public void keyReleased(KeyEvent e) {
        // Do nothing
      }
    });
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

  @Override
  public void showPopUp(String message) {
    JOptionPane.showMessageDialog(this, message, "Player Turn Notification",
            JOptionPane.INFORMATION_MESSAGE);
  }

  @Override
  public void setHintsEnabled(boolean enabled) {
    this.gridPanel.setHintsEnabled(enabled);
    this.refresh();
  }

  @Override
  public void setSelectedCardSupplier(Supplier<Integer> getSelectedCard) {
    this.gridPanel.setSelectedCardSupplier(getSelectedCard);
  }
}
