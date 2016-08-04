package settopbox.ui;

import org.springframework.core.io.ClassPathResource;

import java.awt.*;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class CreateJLabelWithImageIconAndText extends JFrame {

	private static final long serialVersionUID = 1L;

	public CreateJLabelWithImageIconAndText() {

		this.getContentPane().setLayout(new FlowLayout());


		ImageIcon icon = null;
		try {
			icon = new ImageIcon(new ClassPathResource("/images/fail.png").getURL());
		} catch (IOException e) {
			e.printStackTrace();
		}
		JLabel label2 = new JLabel(icon);
		add(label2);

	}

	private static void createAndShowGUI() {
		JFrame frame = new CreateJLabelWithImageIconAndText();
		frame.pack();
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

	public static void main(String[] args) {

		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				createAndShowGUI();
			}
  	});

	}

}