package client.mvc;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.net.Socket;
import java.util.Observable;
import java.util.Observer;

public class CalcView extends JFrame implements Observer {

    private JLabel output;

    public CalcView(){
        this.setName("client.mvc.CalcView");

        this.setMinimumSize(new Dimension(400,400));
        this.setTitle("Simple Calculator");
        this.setLocationRelativeTo(null);
        this.setLayout(new BorderLayout());

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        drawCalc();
    }

    @Override
    public void update(Observable o, Object arg) {
        this.output.setText(((CalcModel) o).output());
        this.setVisible(true);
    }

    private void drawCalc(){
        this.output = new JLabel("0", SwingConstants.RIGHT);
        output.setFont(new Font("Arial",Font.PLAIN, 50));
        output.setMinimumSize(new Dimension(300,50));
        this.add(output, BorderLayout.NORTH);

        this.setVisible(true);
    }

}
