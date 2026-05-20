import javax.swing.*;
import java.awt.*;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Main {
  private static final DateTimeFormatter TIME_FMT = DateTimeFormatter.ofPattern("HH:mm:ss");

  public static void main(String[] args) {
    SwingUtilities.invokeLater(Main::createAndShowUi);
  }

  private static void createAndShowUi() {
    JFrame frame = new JFrame("Java GUI Starter");
    frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    frame.setMinimumSize(new Dimension(720, 420));

    DefaultListModel<String> logModel = new DefaultListModel<>();
    JList<String> logList = new JList<>(logModel);
    logList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    JScrollPane logScroll = new JScrollPane(logList);

    JTextField input = new JTextField();
    JButton addBtn = new JButton("Add");
    JButton clearBtn = new JButton("Clear");

    JLabel status = new JLabel("Ready");
    status.setBorder(BorderFactory.createEmptyBorder(6, 10, 6, 10));

    Runnable addEntry = () -> {
      String text = input.getText().trim();
      if (text.isEmpty()) {
        Toolkit.getDefaultToolkit().beep();
        status.setText("Type something first.");
        input.requestFocusInWindow();
        return;
      }
      String time = LocalTime.now().format(TIME_FMT);
      logModel.addElement("[" + time + "] " + text);
      input.setText("");
      status.setText("Added entry.");
      int last = logModel.size() - 1;
      if (last >= 0) logList.ensureIndexIsVisible(last);
      input.requestFocusInWindow();
    };

    addBtn.addActionListener(e -> addEntry.run());
    input.addActionListener(e -> addEntry.run());
    clearBtn.addActionListener(e -> {
      if (logModel.isEmpty()) {
        status.setText("Nothing to clear.");
        return;
      }
      int choice =
          JOptionPane.showConfirmDialog(
              frame, "Clear all entries?", "Confirm", JOptionPane.YES_NO_OPTION);
      if (choice == JOptionPane.YES_OPTION) {
        logModel.clear();
        status.setText("Cleared.");
      }
    });

    JPanel top = new JPanel(new BorderLayout(10, 10));
    top.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
    top.add(new JLabel("Input:"), BorderLayout.WEST);
    top.add(input, BorderLayout.CENTER);

    JPanel actions = new JPanel(new FlowLayout(FlowLayout.RIGHT, 8, 0));
    actions.add(addBtn);
    actions.add(clearBtn);
    top.add(actions, BorderLayout.EAST);

    frame.setLayout(new BorderLayout());
    frame.add(top, BorderLayout.NORTH);
    frame.add(logScroll, BorderLayout.CENTER);
    frame.add(status, BorderLayout.SOUTH);

    frame.setJMenuBar(createMenuBar(frame, logModel, status, input));

    frame.setLocationRelativeTo(null);
    frame.setVisible(true);
    input.requestFocusInWindow();
  }

  private static JMenuBar createMenuBar(
      JFrame frame, DefaultListModel<String> logModel, JLabel status, JTextField input) {
    JMenuBar bar = new JMenuBar();

    JMenu file = new JMenu("File");
    JMenuItem exit = new JMenuItem("Exit");
    exit.addActionListener(e -> frame.dispose());
    file.add(exit);

    JMenu edit = new JMenu("Edit");
    JMenuItem clear = new JMenuItem("Clear Entries");
    clear.addActionListener(e -> {
      logModel.clear();
      status.setText("Cleared.");
      input.requestFocusInWindow();
    });
    edit.add(clear);

    JMenu help = new JMenu("Help");
    JMenuItem about = new JMenuItem("About");
    about.addActionListener(
        e ->
            JOptionPane.showMessageDialog(
                frame,
                "Java GUI Starter\n\nSwing-based starter app.\nAdd items, clear list, and extend it into your own project.",
                "About",
                JOptionPane.INFORMATION_MESSAGE));
    help.add(about);

    bar.add(file);
    bar.add(edit);
    bar.add(help);
    return bar;
  }
}
