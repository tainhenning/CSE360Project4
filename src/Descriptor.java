package Project03;

import java.awt.*;
import javax.swing.*;
@SuppressWarnings("serial")

//Tain Henning
public class Descriptor extends JPanel
{
    int state = 0;
    static int appendCount = 0;
    static int correctAmount = 0;
    GridBagConstraints gbc = new GridBagConstraints(); 
    static JTextArea mainText = new JTextArea();
        
    private JPanel namePanel = new JPanel(new GridBagLayout());
    private JLabel nameLabel = new JLabel("Descriptor Area");
    
    public Descriptor()
    {
        
        this.setVisible(true);
        firstLoad();
//        prepareGUI();
    }
    
    public void firstLoad() {
        namePanel.add(nameLabel);
        namePanel.setPreferredSize(new Dimension(385, 381));
        add(namePanel);
    }
    
    public void changeState(int state) {
        if (state == 1) {
            removeAll();
            revalidate();
            repaint();
            
            prepareGUI();
        }
    }
    
    public void prepareGUI()
    {
    	mainText.setBackground(Color.black);
    	mainText.setForeground(Color.white);
    	mainText.setEditable(false);
        this.setLayout(new BorderLayout());
        this.setPreferredSize(new Dimension(400, 400));
        mainText.setLayout(new BorderLayout());
    	this.add(mainText);
    }
    
    public static void appendText(boolean bol)		//to implement just type Descriptor.appendText(boolean value);
    {
    	if(appendCount==24)
    	{
    		mainText.setText("");
    		appendCount=0;
    	}

    	if(bol == true)
    	{
    		System.out.println("Correct\n");
    		mainText.append("Correct!\n");
    		correctAmount++;
    		if(correctAmount>6)
    		{
    			mainText.append("Lesson completed!\n");
    		}
    	}
    	else
    		mainText.append("Incorrect\n");
    	appendCount++;


    }
}