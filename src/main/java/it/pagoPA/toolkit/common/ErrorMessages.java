package it.pagoPA.toolkit.common;

public class ErrorMessages {

	public static final String VALIDATION_ERROR = "Validation Errors: ";
	public static final String VALIDATION_AUXDIGIT_ERROR = "AuxDigit value not valid";
	public static final String VALIDATION_SEGREGATION_CODE_ERROR = "SegregationCode cannot be null for AuxDigit 3";
	public static final String VALIDATION_APPLICATION_CODE_ERROR = "ApplicationCode cannot be null for AuxDigit 0";
	public static final String VALIDATION_ALGORITHM_NOT_IMPLEMENTED_ERROR = "Algorithm not implemented for AuxDigit: ";
	public static final String VALIDATION_AUXDIGIT_NOT_ALLOWED_ERROR = "AuxDigit not allowed: ";
	public static final String VALIDATION_VAT_NUMBER_ERROR = "VAT number is not formally correct";
	public static final String VALIDATION_FISCAL_CODE_ERROR = "Fiscal code is not formally correct";
	public static final String VALIDATION_SINGLE_PAYMENT_LIST_SIZE_ERROR = "The size of Single Payment List has been exceeded";
	public static final String VALIDATION_AMOUNTS_ERROR = "The sum of the single amounts does not coincide with the total amount of the payment";
	public static final String VALIDATION_EMAIL_ERROR = "Email should be valid";
	public static final String VALIDATION_CHECK_DIGIT_ERROR = "Invalid iuv check digit";
	
	public static final String UNEXPECTED_GENERATED_VALUE_ERROR = "Unexpected generated value: ";
	
	public static final String VALIDATION_FC_YEAR_ERROR = "Year not valid: ";
	public static final String VALIDATION_FC_DIGIT_ERROR = "Invalid Digit: ";
	
}
