package it.pagoPA.toolkit.iuvGenerator.common;

import java.util.Calendar;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;

import it.pagoPA.toolkit.common.ErrorMessages;
import it.pagoPA.toolkit.iuvGenerator.exception.UnexpectedValueException;

/**
 * IUV Sequence reader implementation
 */
public class IuvSequenceReaderImpl implements IuvSequenceReader {

	private static final String DIGIT_OF_13 = "\\d{13}";

	private static Pattern pattern = Pattern.compile(DIGIT_OF_13);

	@Override
	public String next() {
		String sequence = ApplicationProperties.getSequenceNextValue();
		sequence = StringUtils.leftPad(sequence, 9, '0');
		sequence = Calendar.getInstance().get(Calendar.YEAR) + sequence;

		if (!pattern.matcher(sequence).matches()) {
			throw new UnexpectedValueException(ErrorMessages.UNEXPECTED_GENERATED_VALUE_ERROR + sequence);
		}

		return sequence;
	}
}
