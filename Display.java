import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextField;

public class Display implements Gui {
	
	private Controller connected_controller;
	private JFrame f;
	private JButton coin;
	private JButton go_stop;
	private JTextField text;
	
	@Override
	public void connect(Controller controller) {
		connected_controller = controller;
		f = new JFrame();
		f.setSize(400, 500);
		f.setLayout(null);
		coin = new JButton("Insert Coin");
		go_stop = new JButton("Go/Stop");
		text = new JTextField();
		text.setBounds(100, 100, 200, 200);
		coin.setBounds(100, 350, 100, 40);
		go_stop.setBounds(200, 350, 100, 40);
		text.setEditable(false);
		f.add(coin);
		f.add(go_stop);
		f.add(text);
		f.setVisible(true);
	}

	@Override
	public void init() {
		coin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(e.getActionCommand()=="Insert Coin") {
					connected_controller.coinInserted();
				}
			}
		});
		go_stop.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(e.getActionCommand()=="Go/Stop") {
					connected_controller.goStopPressed();
				}				
			}
		});
	}

	@Override
	public void setDisplay(String s) {
		text.setText(s);
	}

}
