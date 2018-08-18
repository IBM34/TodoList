package main.app.view;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.swing.JFormattedTextField.AbstractFormatter;

// Classe qui permet de definir le format de la date contenue dans la barre de texte du JDatePicker

public class DateLabelFormatter extends AbstractFormatter {

	private static final long serialVersionUID = -1926485241114124639L;
	private String datePattern = "dd/MM/yyyy";
	private SimpleDateFormat dateFormatter = new SimpleDateFormat(datePattern);

	@Override
	public Object stringToValue(String text) throws ParseException {
		return dateFormatter.parseObject(text);
	}

	@Override
	public String valueToString(Object value) throws ParseException {
		if (value != null) {
			Calendar cal = (Calendar) value;
			return dateFormatter.format(cal.getTime());
		}

		return "";

	}
}
