package it.pagoPA.toolkit.debtPositionGenerator.enumeration;

/**
 * 
 */
public enum PaymentStatusEnum {
	PAYABLE(1), NOT_PAYABLE(2), CANCELED(3), PAID(4);

	private final Integer value;

	/**
	 * 
	 * @param v
	 * @return
	 */
	public static PaymentStatusEnum fromValue(Integer v) {
		for (PaymentStatusEnum enumValue : PaymentStatusEnum.values()) {
			if (enumValue.value.equals(v)) {
				return enumValue;
			}
		}
		throw new IllegalArgumentException(String.valueOf(v));
	}

	/**
	 * 
	 * @param v
	 */
	private PaymentStatusEnum(Integer v) {
		value = v;
	}

	/**
	 * 
	 * @return
	 */
	public int value() {
		return value;
	}
}
