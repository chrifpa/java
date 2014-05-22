package view;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Observer;
import java.util.Observable;
import model.Board;
import control.KalahaController;

public class PitPane extends JPanel implements ActionListener, Observer
{
    private Pit[] pits;
    private KalahaController controller;

    public PitPane(KalahaController controller)
    {
        super();
        this.controller = controller;
        setSize(200,800);
        setLayout(new BorderLayout());
        pits = new Pit[14];

        JPanel centerPane = new JPanel();
        centerPane.setLayout(new GridLayout(2,6));

        for (int i=0; i<14; i++)
        {
            pits[i] = new Pit(i);
            pits[i].setText(Integer.toString(i));
            pits[i].addActionListener(this);
        }

        for (int i=12; i>6; i--)
        {
            centerPane.add(pits[i]);
        }
        for (int i=0; i<6; i++)
        {
            centerPane.add(pits[i]);
        }

        Pit kalaha1 = pits[6];
        Pit kalaha2 = pits[13];
        add(kalaha2, BorderLayout.LINE_START);
        add(kalaha1, BorderLayout.LINE_END);
        add(centerPane, BorderLayout.CENTER);
    }

    @Override
    public void actionPerformed(ActionEvent ae)
    {
        int sourceID = ((Pit) ae.getSource()).getID();
        System.out.println(sourceID);

        controller.pitChosen(sourceID);

    }

    @Override
    public void update(Observable o, Object arg)
    {
        int pit = (Integer) arg;
        int seeds = ((Board) o).getSeeds(pit);

        pits[pit].setText(Integer.toString(seeds));
    }
}