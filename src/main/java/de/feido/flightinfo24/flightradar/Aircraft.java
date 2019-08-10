package de.feido.flightinfo24.flightradar;

public class Aircraft {

	private final AircraftModel model;
	private int countryId;
	private String registration;
	private String hex;

	public Aircraft(AircraftModel model) {
		this.model = model;
	}

	public AircraftModel getModel() {
		return model;
	}

	/**
	 * Convenience method.
	 *
	 * @return
	 */
	public String getCode() {
		return model.getCode();
	}

	/**
	 * Convenience method.
	 *
	 * @return
	 */
	public String getText() {
		return model.getText();
	}

	public int getCountryId() {
		return countryId;
	}

	public String getRegistration() {
		return registration;
	}

	public String getHex() {
		return hex;
	}
}
