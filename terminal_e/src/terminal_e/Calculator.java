package terminal_e;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

public class Calculator extends JFrame
{
	private JPanel TextPanel;
	private JPanel ButtonPanel;
	private JPanel Button2Panel;
	private JPanel RadioButtonPanel;
	
	private GridLayout TextGrid;
	private GridLayout ButtonGrid;
	private GridLayout ButtonGrid2;
	private GridLayout RadioButtonGrid;
	private BorderLayout MainBorderLayout;
	
	private JPanel	TBRofPanel;
	private GridLayout TBRofGrid;

	
	
	private int Value1=0,Value2=0;
	private String Operator;
	private boolean TransHex = false;
	private boolean TransDec = true;
	private JLabel Temp1;
	private JLabel Temp2;
	private JLabel HintText1;
	private JTextField Text1;
	
	private JButton[] Buttons2 = new JButton[6];
	private String[] ButtonTexts2 = {"A","B","C","D","E","F"};
	
	private JButton[] Buttons = new JButton[16];
	private String[] ButtonTexts = {	"0","=","Clr","+",
										"1","2","3","-",
										"4","5","6","*",
										"7","8","9","/"		};
	private JRadioButton[] RadioButtons = new JRadioButton[2];
	private ButtonGroup RadioGroup;
	public Calculator()
	{
		super("Calculator Project");
		
		ObjectBuild();
		ComplexLayoutBuild();
		setLayout(MainBorderLayout);
		ComplexLayoutAdd();		
		ObjectAdd();	
		ObjectAddListener();
	}// End of Calculator Construct
	void ComplexLayoutBuild()
	{
		TextPanel	= new JPanel();
		TextGrid	= new GridLayout(2,1,10,10);
		
		ButtonPanel = new JPanel();
		ButtonGrid	= new GridLayout(4,4,10,10);
		
		Button2Panel = new JPanel();
		ButtonGrid2	= new GridLayout(1,6,10,10);
		
		RadioButtonPanel = new JPanel();
		RadioButtonGrid = new GridLayout(1,2,10,10);
		MainBorderLayout = new BorderLayout(10,10);
		TBRofPanel = new JPanel();
		TBRofGrid = new GridLayout(4,1,0,0);
	}// End of ComplexLayoutBuild
	void ComplexLayoutAdd()
	{
		TextPanel.setLayout(TextGrid);
		TextPanel.add(HintText1);
		TextPanel.add(Text1);
		
		ButtonPanel.setLayout(ButtonGrid);
		for(int i = 0 ; i < Buttons.length ; i++ )
		{
			ButtonPanel.add(Buttons[i]);
		}
		
		Button2Panel.setLayout(ButtonGrid2);
		for(int i = 0 ; i < Buttons2.length ; i++ )
		{
			Button2Panel.add(Buttons2[i]);
		}
		
		
		RadioButtonPanel.setLayout(RadioButtonGrid);
		RadioButtonPanel.add(RadioButtons[0]);
		RadioButtonPanel.add(RadioButtons[1]);
		
		TBRofPanel.setLayout(TBRofGrid);
		TBRofPanel.add(TextPanel);
		TBRofPanel.add(ButtonPanel);
		TBRofPanel.add(Button2Panel);
		TBRofPanel.add(RadioButtonPanel);	
	}// End of ComplexLayoutAdd
	void ObjectBuild()
	{
		Text1 = new JTextField(20);
		Text1.setHorizontalAlignment(JTextField.RIGHT);  	//文字向右 對齊
		Text1.setFont(new Font("Serif", Font.PLAIN,32));
		for(int i = 0 ; i < Buttons.length; i++ )
		{
			Buttons[i] = new JButton(ButtonTexts[i]);
		}	
		
		for(int i = 0 ; i < Buttons2.length; i++ )
		{
			Buttons2[i] = new JButton(ButtonTexts2[i]);
		}	
		
		
		RadioButtons[0] = new JRadioButton("十進制",true);
		RadioButtons[1]	= new JRadioButton("十六進制",false);
		
		RadioGroup = new ButtonGroup();
		RadioGroup.add(RadioButtons[0]);
		RadioGroup.add(RadioButtons[1]);
		
		Temp1 = new JLabel("          ");
		Temp2 = new JLabel("          ");
		HintText1 = new JLabel("第一個值: ");
		HintText1.setHorizontalAlignment(JLabel.CENTER);	//文字置中 對齊
		HintText1.setFont(new Font("Serif", Font.PLAIN,16));
	}//End of ObjectBuild
	void ObjectAdd()
	{
	//	add(TextPanel,MainBorderLayout.NORTH);
	//	add(ButtonPanel,MainBorderLayout.CENTER);
	//	add(RadioButtonPanel,MainBorderLayout.SOUTH);
		add(Temp1,MainBorderLayout.EAST);
		add(Temp2,MainBorderLayout.WEST);		
		add(TBRofPanel,MainBorderLayout.CENTER);
		
	}//End of ObjectAdd
	void ObjectAddListener()
	{
		for( int i = 0 ; i< Buttons.length ; i++ )
		{
			Buttons[i].addActionListener(new ButtonActionListener());
		}
		for( int i = 0 ; i< Buttons2.length ; i++ )
		{
			Buttons2[i].addActionListener(new ButtonActionListener());
		}
		RadioButtons[0].addItemListener(new RadioButtonListener());
		RadioButtons[1].addItemListener(new RadioButtonListener());
	}//End of ObjectAddListener
	
	private class ButtonActionListener implements ActionListener
	{
		public void actionPerformed(ActionEvent event)
		{
			String TempHandle;
			int TransTemp=0;
			TempHandle = event.getActionCommand();
			if(ButtonTexts[2].equals(TempHandle)) // 'Clr'
			{
				Text1.setText("");
				HintText1.setText("第一個值: ");
				Value1=0;
				Value2=0;
			}
			else if( (ButtonTexts[3].equals(TempHandle)) || (ButtonTexts[7].equals(TempHandle)) || (ButtonTexts[11].equals(TempHandle)) || (ButtonTexts[15].equals(TempHandle)) || (ButtonTexts[1].equals(TempHandle)) )
			{
				for( int i = 3 ; i < ButtonTexts.length ; i+=4 )
				{
					if(ButtonTexts[i].equals(TempHandle)) 
					{
						Operator = ButtonTexts[i];  				// save + - * /
						HintText1.setText( HintText1.getText() + Text1.getText());
						if(TransHex)
							Value1=Integer.parseInt(Text1.getText(),16);
						else
							Value1=Integer.parseInt(Text1.getText());	// save Value
						Text1.setText("");						// clear TextField					
					}
				}			
				if(ButtonTexts[1].equals(TempHandle)) // =
				{
					if(TransDec)
					{
						Value2 = Integer.parseInt(Text1.getText());
						if(ButtonTexts[3].equals(Operator)) // +
						{
							TransTemp=Value1+Value2;
						}
						if(ButtonTexts[7].equals(Operator)) // -
						{
							TransTemp=Value1-Value2;
						}
						if(ButtonTexts[11].equals(Operator)) // *
						{
							TransTemp=Value1*Value2;
						}
						if(ButtonTexts[15].equals(Operator)) // /
						{
							TransTemp=Value1/Value2;
						}	
						Text1.setText(Integer.toString(TransTemp));
						HintText1.setText("第一個 值 : " + Integer.toString(Value1) + " 第二個 值 : " + Integer.toString(Value2) + " 總和 : " + Integer.toString(TransTemp));
					}
					
					// 將 16進制  轉換 到 10進制 做運算 
					// 再轉回 16進制顯示
					
					if(TransHex)   	
					{
						Value2 = Integer.parseInt(Text1.getText(),16);   					// 16 ---10
						if(ButtonTexts[3].equals(Operator)) // +
						{
							TransTemp=Value1+Value2;
						}
						if(ButtonTexts[7].equals(Operator)) // -
						{
							TransTemp=Value1-Value2;
						}
						if(ButtonTexts[11].equals(Operator)) // *
						{
							TransTemp=Value1*Value2;
						}
						if(ButtonTexts[15].equals(Operator)) // /
						{
							TransTemp=Value1/Value2;
						}
						// Integer.parseInt 	將 字串 轉為 整數
						// Integer.toHexString 	將 整數 轉為 字串
						HintText1.setText("第一個 Hex值 : " + Integer.toHexString(Value1) + " 第二個 Hex值 : " + Integer.toHexString(Value2) + " 總和 (Hex) : " + Integer.toHexString(TransTemp));
						Text1.setText(Integer.toHexString(TransTemp)); // 10 ---16		 			
					}
				}
			}
			else 
			{
				if(TransDec)  //將 十進制 時候的 ABCDEF 值 取消輸出 到 Text1 
				{
					for( int i = 0 ; i< ButtonTexts2.length ; i++ )
					{
						if(ButtonTexts2[i].equals(TempHandle))
							Text1.setText(Text1.getText());
					}
				}
				else 
				{
					Text1.setText(Text1.getText()+TempHandle);
				}		
			}//End of if-else				
		}// End of actionPerformed
	}//End of class implements ActionListener
	private class RadioButtonListener implements ItemListener
	{
		@Override
		public void itemStateChanged(ItemEvent event)
		{
			// TODO Auto-generated method stub
			if(RadioButtons[0].isSelected())
			{
				TransDec = true;
				TransHex = false;
				Text1.setText(String.valueOf(Integer.parseInt(Text1.getText(),16)));
				System.out.printf("This is 10 \n");
			}
			if(RadioButtons[1].isSelected())
			{
				TransDec = false;
				TransHex = true;
				Text1.setText(Integer.toHexString(Integer.parseInt(Text1.getText())));
				System.out.printf("This is 16 \n");
			}
		}		
	}
}// End of class Calculator
