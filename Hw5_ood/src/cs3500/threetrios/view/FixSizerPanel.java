package cs3500.threetrios.view;

import java.awt.*;

import javax.swing.*;

/**
 * This class will be wrapping another component, fixing an auto-stretching problem.
 */
public class FixSizerPanel<T> extends JPanel {

  private final T wrappedComponent;

  /**
   * Constructs a FixSizerPanel to wrap a component and center it.
   *
   * @param component The component to wrap.
   * @param color The color for Fixsizer Background.
   * @param width The wished width for FixSizer.
   * @param height The wished height for FixSizer.
   */
  public FixSizerPanel(T component, int width, int height, ElementColor color) {
    this.wrappedComponent = component;

    this.setPreferredSize(new Dimension(width, height));

    this.setBackground(Color.decode(color.toString()));

    setLayout(new GridBagLayout());

    // Wrapped Component will always be centered.
    GridBagConstraints gbc = new GridBagConstraints();
    gbc.gridx = 0;
    gbc.gridy = 0;
    gbc.anchor = GridBagConstraints.CENTER;

    add((Component) wrappedComponent, gbc);
  }

  /**
   * Gets the wrapped component.
   * @return The component wrapped by this FixSizerPanel.
   */
  public T getWrappedComponent() {
    return wrappedComponent;
  }
}
