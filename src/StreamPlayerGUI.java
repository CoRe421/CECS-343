import javazoom.jlgui.basicplayer.BasicPlayer;
import javazoom.jlgui.basicplayer.BasicPlayerException;

import javax.swing.*;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.awt.event.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;

public class StreamPlayerGUI extends JFrame {
    BasicPlayer player;

    JPanel main;
    JButton playButton;
    JTable table;
    JScrollPane scrollPane;
    int currentSelectedRow;
    ButtonListener bl;
    public StreamPlayerGUI() {
        player = new BasicPlayer();
        main = new JPanel();

        //Creates the panel with a vertical layout.
        main.setLayout(new BoxLayout(main, BoxLayout.Y_AXIS));

        //Creates a Button Listener to attach to the playButton.
        bl = new ButtonListener();

        //Sets the button's text, attaches the listener, and also sets it's size and alignment.
        playButton = new JButton("Play");
        playButton.addActionListener(bl);
        playButton.setMinimumSize(new Dimension(450, 25));
        playButton.setMaximumSize(new Dimension(450, 25));
        playButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        //The set of data that will be in the table.
        String[] columns = {"Station URL", "Description"};
        String[][] data = {{"https://mp3.ffh.de/ffhchannels/hqrock.mp3", "Rock"},
                            {"https://mp3.ffh.de/ffhchannels/hq80er.mp3", "The 80's"},
                            {"https://mp3.ffh.de/ffhchannels/hqdeutsch.mp3", "Per Deutsch"},
                            {"https://mp3.ffh.de/ffhchannels/hq90er.mpr", "The 90's"}};

        table = new JTable(data, columns);

        //Creates a new listener for the mouse attached to the table.
        MouseListener mouseListener = new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                currentSelectedRow = table.getSelectedRow();
                System.out.println("Selected index " + currentSelectedRow);
            }
        };

        //Attaches the mouseListener to the table.
        table.addMouseListener(mouseListener);

        //Sets the width of the URL column to be larger.
        TableColumn column = table.getColumnModel().getColumn(0);
        column.setPreferredWidth(250);

        scrollPane = new JScrollPane(table);

        //Adds all the components to the panel.
        main.add(scrollPane);
        main.add(playButton);
        this.setTitle("StreamPlayer by Cory Reardon");//change the name to yours
        this.setSize(400, 150);
        this.add(main);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    class ButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {

            String url = null;

            //If there is a row selected on the table, sets url to that element.
            if (table.getSelectedRow() != -1) {
                url = (String)table.getValueAt(currentSelectedRow, 0);
            }

            //Attempts to play the url.
            try {
                player.open(new URL(url));
                player.play();
            } catch (MalformedURLException ex) {
                Logger.getLogger(StreamPlayerGUI.class.getName()).log(Level.SEVERE, null, ex);
                System.out.println("Malformed url");
            } catch (BasicPlayerException ex) {
                System.out.println("BasicPlayer exception");
                Logger.getLogger(StreamPlayerGUI.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}