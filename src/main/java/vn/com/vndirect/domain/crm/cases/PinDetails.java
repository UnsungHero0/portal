package vn.com.vndirect.domain.crm.cases;

import javax.xml.bind.annotation.XmlType;

@XmlType(name = "PinDetails", propOrder = { "pin" })
public class PinDetails {
	private String pin;

	/**
	 * @return the pin
	 */
	public String getPin() {
		return pin;
	}

	/**
	 * @param pin
	 *            the pin to set
	 */
	public void setPin(String pin) {
		this.pin = pin;
	}

}
