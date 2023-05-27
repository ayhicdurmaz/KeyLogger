import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import Utility.StringValidator;

public class MainFrame extends JFrame {

	private JLabel labelMail, labelMailInterval, labelLogSize;
	private JTextField textFieldMail, textFieldLogSize, textFieldMailInterval;
	private JRadioButton radioButtonMouseOnly, radioButtonKeyBoardOnly, radioButtonBoth;
	private JButton buttonStart, buttonStop;
	private ButtonGroup buttonGroup = new ButtonGroup();

	public MainFrame() {
		initComponents();
	}

	private void initComponents() {

		setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
		labelMail = new JLabel();
		labelLogSize = new JLabel();
		labelMailInterval = new JLabel();

		textFieldMail = new JTextField();
		textFieldLogSize = new JTextField();
		textFieldMailInterval = new JTextField();

		radioButtonBoth = new JRadioButton();
		radioButtonMouseOnly = new JRadioButton();
		radioButtonKeyBoardOnly = new JRadioButton();

		buttonStop = new JButton();
		buttonStart = new JButton();

		labelMail.setText("Log dosyasının gönderileceği mail : ");
		labelMailInterval.setText("Mail gönderme aralıkları (dk) : ");
		labelLogSize.setText("Log dosyasının maksimum boyutu (MB) : ");

		textFieldMail.setColumns(20);
		textFieldLogSize.setColumns(20);
		textFieldMailInterval.setColumns(20);

		radioButtonBoth.setText("Her İkisi");
		radioButtonMouseOnly.setText("Sadece Fare");
		radioButtonKeyBoardOnly.setText("Sadece Klavye");

		buttonGroup.add(radioButtonBoth);
		buttonGroup.add(radioButtonMouseOnly);
		buttonGroup.add(radioButtonKeyBoardOnly);

		buttonStart.setText("Başlat");
		buttonStop.setText("Bitir");
		buttonStart.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				actionOfStartButton();
			}
		});
		buttonStop.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				actionOfStopButton();
			}
		});

		GroupLayout groupLayout = new GroupLayout(this.getContentPane());
		this.getContentPane().setLayout(groupLayout);

		groupLayout.setHorizontalGroup(groupLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup().addGap(30)
						.addGroup(groupLayout.createParallelGroup(
								GroupLayout.Alignment.LEADING)
								.addComponent(labelMail)
								.addComponent(labelMailInterval)
								.addComponent(labelLogSize))
						.addGap(25)
						.addGroup(groupLayout.createParallelGroup(
								GroupLayout.Alignment.LEADING)
								.addComponent(textFieldMail)
								.addComponent(textFieldMailInterval)
								.addComponent(textFieldLogSize))
						.addGap(25)
						.addGroup(groupLayout.createParallelGroup(
								GroupLayout.Alignment.LEADING)
								.addComponent(radioButtonMouseOnly)
								.addComponent(radioButtonKeyBoardOnly)
								.addComponent(radioButtonBoth))
						.addGap(30))
				.addGroup(groupLayout.createSequentialGroup()
						.addGap(30)
						.addComponent(buttonStart, GroupLayout.DEFAULT_SIZE,
								GroupLayout.DEFAULT_SIZE,
								Short.MAX_VALUE)
						.addPreferredGap(
								LayoutStyle.ComponentPlacement.UNRELATED)
						.addComponent(buttonStop, GroupLayout.DEFAULT_SIZE,
								GroupLayout.DEFAULT_SIZE,
								Short.MAX_VALUE)
						.addGap(30)));

		groupLayout.setVerticalGroup(
				groupLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
								.addGap(30)
								.addGroup(groupLayout.createParallelGroup(
										GroupLayout.Alignment.BASELINE)
										.addComponent(labelMail)
										.addComponent(textFieldMail)
										.addComponent(radioButtonMouseOnly))
								.addGap(25)
								.addGroup(groupLayout.createParallelGroup(
										GroupLayout.Alignment.BASELINE)
										.addComponent(labelMailInterval)
										.addComponent(textFieldMailInterval)
										.addComponent(radioButtonKeyBoardOnly))
								.addGap(25)
								.addGroup(groupLayout.createParallelGroup(
										GroupLayout.Alignment.BASELINE)
										.addComponent(labelLogSize)
										.addComponent(textFieldLogSize)
										.addComponent(radioButtonBoth))
								.addGap(25)
								.addGroup(groupLayout.createParallelGroup(
										GroupLayout.Alignment.BASELINE)
										.addComponent(buttonStart)
										.addComponent(buttonStop))
								.addContainerGap(30, Short.MAX_VALUE)));

		pack();
	}

	private boolean checkForm() {
		return (!textFieldMail.getText().isEmpty() && !textFieldMailInterval.getText().isEmpty()
				&& !textFieldLogSize.getText().isEmpty() && buttonGroup.getSelection() != null);
	}

	private boolean checkStrings() {
		return StringValidator.isValidEmail(textFieldMail.getText())
				&& StringValidator.isStringOfInt(textFieldMailInterval.getText())
				&& StringValidator.isStringOfInt(textFieldLogSize.getText());
	}

	private void setParams() {
		Settings.mail = textFieldMail.getText();
		Settings.mailInterval = Integer.parseInt(textFieldMailInterval.getText());
		Settings.logSize = Integer.parseInt(textFieldLogSize.getText());
		Settings.pref = buttonGroup.getSelection() == radioButtonKeyBoardOnly.getModel()
				? Settings.Prefs.KEYBOARD_ONLY
				: buttonGroup.getSelection() == radioButtonMouseOnly.getModel()
						? Settings.Prefs.MOUSE_ONLY
						: Settings.Prefs.BOTH;
	}

	public void actionOfStartButton() {
		if (checkForm()) {
			if (checkStrings()) {

				setParams();

				Main.startKeyLogging();

			} else {
				JOptionPane.showMessageDialog(this.getContentPane(),
						"Alanları yanlış doldurdunuz. Lütfen düzeltip deneyin",
						"Uyarı",
						JOptionPane.WARNING_MESSAGE);
			}
		} else {
			JOptionPane.showMessageDialog(this.getContentPane(), "Formda eksik kalan yerleri doldurunuz.",
					"Uyarı",
					JOptionPane.WARNING_MESSAGE);
		}

	}

	public void actionOfStopButton() {
		if (Settings.working) {
			Main.stopKeyLogging();
		}
	}
}
