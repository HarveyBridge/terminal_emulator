package terminal_e;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Frame;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class harveyform extends JFrame{
    private JLabel terminal_content;
    private JFrame demo;
    private JTextArea command_line;

//    private JButton btn;
    private JTextField t_view;
    private JTextField t_line;
    private JButton btn_enter;

    private data_structure terminal_layout;
    private data_structure btn_layout;
    
    private BorderLayout top_layout;

    public harveyform() {
        super("terminal_emulator Project");
        // common_init();
        // GridBagLayoutDemo();

        // form_layout();
        // demo.setVisible(true);
        ObjectBuild();
        top_layout= new BorderLayout(10,10);
        setLayout(top_layout);

        terminal_layout= new data_structure();
        terminal_layout.panel.add(t_view);
        terminal_layout.panel.add(t_line);
        terminal_layout.set_grid(2, 1, 10, 10);
        add(terminal_layout.panel, top_layout.CENTER);

        btn_layout= new data_structure();
        btn_layout.panel.add(btn_enter);
        btn_layout.set_grid(1, 1, 10, 10);
        add(btn_layout.panel, top_layout.SOUTH);
    }
    // private void ComplexLayoutBuild(){
    //     TextPanel   = new JPanel();
    //     TextGrid    = new GridLayout(2,1,10,10);
        
    //     ButtonPanel = new JPanel();
    //     ButtonGrid  = new GridLayout(4,4,10,10);
        
    //     Button2Panel = new JPanel();
    //     ButtonGrid2 = new GridLayout(1,6,10,10);
        
    //     RadioButtonPanel = new JPanel();
    //     RadioButtonGrid = new GridLayout(1,2,10,10);
    //     MainBorderLayout = new BorderLayout(10,10);
    //     TBRofPanel = new JPanel();
    //     TBRofGrid = new GridLayout(4,1,0,0);
    // }// End of ComplexLayoutBuild
    // private void ComplexLayoutAdd()
    // {
    //     TextPanel.setLayout(TextGrid);
    //     TextPanel.add(HintText1);
    //     TextPanel.add(Text1);
        
    //     ButtonPanel.setLayout(ButtonGrid);
    //     for(int i = 0 ; i < Buttons.length ; i++ )
    //     {
    //         ButtonPanel.add(Buttons[i]);
    //     }
        
    //     Button2Panel.setLayout(ButtonGrid2);
    //     for(int i = 0 ; i < Buttons2.length ; i++ )
    //     {
    //         Button2Panel.add(Buttons2[i]);
    //     }
        
        
    //     RadioButtonPanel.setLayout(RadioButtonGrid);
    //     RadioButtonPanel.add(RadioButtons[0]);
    //     RadioButtonPanel.add(RadioButtons[1]);
        
    //     TBRofPanel.setLayout(TBRofGrid);
    //     TBRofPanel.add(TextPanel);
    //     TBRofPanel.add(ButtonPanel);
    //     TBRofPanel.add(Button2Panel);
    //     TBRofPanel.add(RadioButtonPanel);   
    // }// End of ComplexLayoutAdd
    // private void ObjectAdd()
    // {
    // //  add(TextPanel,MainBorderLayout.NORTH);
    // //  add(ButtonPanel,MainBorderLayout.CENTER);
    // //  add(RadioButtonPanel,MainBorderLayout.SOUTH);
    //     add(Temp1,MainBorderLayout.EAST);
    //     add(Temp2,MainBorderLayout.WEST);       
    //     add(TBRofPanel,MainBorderLayout.CENTER);
        
    // }//End of ObjectAdd
    
    private void ObjectBuild(){
        t_view= new JTextField(20);
        t_view.setHorizontalAlignment(JTextField.LEFT);     // text left alignment
        t_view.setFont(new Font("Serif", Font.PLAIN,32));

        t_line= new JTextField(20);
        t_line.setHorizontalAlignment(JTextField.LEFT);     // text left alignment
        t_line.setFont(new Font("Serif", Font.PLAIN,32));

        btn_enter= new JButton("enter");
    }

    private void common_init(){
        demo = new JFrame();
        demo.setSize(400, 300);
        demo.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JLabel label = new JLabel("JLabel");
        demo.getContentPane().add(BorderLayout.NORTH , label);

        JTextArea textarea = new JTextArea("JTextArea");
        demo.getContentPane().add(BorderLayout.CENTER, textarea);
    }
    private void form_layout(){
        demo = new JFrame();
        demo.setSize(400, 300);
        demo.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        demo.setLayout(new BorderLayout());
        terminal_content= new JLabel("");
        command_line= new JTextArea("");
        JButton btn2 = new JButton("Enter");
        btn2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String str2;

                str2= command_line.getText();
                btn2.setText(str2);
                try {
                    terminal_command(str2);
                } catch (IOException e1) {
                // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
            }
        });

        JPanel pl2 = new JPanel(new BorderLayout());
        pl2.add(terminal_content, BorderLayout.CENTER);
        JPanel pl3 = new JPanel(new GridLayout(2, 2));
        pl3.add(command_line);
        pl3.add(btn2);
        demo.add(pl2, BorderLayout.CENTER);
        demo.add(pl3, BorderLayout.SOUTH);
    }
    public void terminal_command(String str2) throws IOException{
        Process pl = Runtime.getRuntime().exec(str2);
        String line = "";
        BufferedReader p_in = new BufferedReader(new InputStreamReader(pl.getInputStream()));
        while((line = p_in.readLine()) != null){
            System.out.println(line);
            terminal_content.setText(terminal_content.getText() + "\r\n" + line);
            // textarea.setText(textarea.getText() + "\r\n" + line);
        }
        p_in.close();
    }
}
class AdapterDemo extends WindowAdapter {
    public void windowClosing(WindowEvent e) {
        System.exit(0);
    }
}
