package Views;
import javax.swing.*;

public abstract class View {
      abstract public JComponent build();
      abstract public  void clear();
      abstract public void show();
      abstract public JComponent getMainComponent();
}
