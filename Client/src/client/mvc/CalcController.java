package client.mvc;

import javax.swing.*;
import java.awt.*;

public class CalcController {

    CalcView view;
    CalcModel model;

    public CalcController(){
        this.view = new CalcView();
        this.model = new CalcModel();

        this.model.addObserver(this.view);

        addButtons();
    }

    private void addButtons(){
        String[] buttons = {
                "1", "2", "3", "+",
                "4", "5", "6", "-",
                "7", "8", "9", "*",
                "0", "=", "C", "/"
        };

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4,4,10,10));

        for(String name: buttons){
            JButton temp = new JButton(name);
            temp.setBorder(BorderFactory.createBevelBorder(0));
            temp.addActionListener(e -> onClick(name));
            temp.setBackground(Color.white);
            panel.add(temp);
        }

        this.view.add(panel,BorderLayout.CENTER);
        this.view.setVisible(true);
    }

    private void onClick(String name){
        this.model.calculate(name);
        this.model.notifyObservers();
    }


}
