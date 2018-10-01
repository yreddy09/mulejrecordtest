package gov.sc.dhhs.cgis.domain;

import java.sql.Timestamp;

public class MySrs {
	private String retirementCode;
	private String ssnOfTheRetiree;
	private String ssnOfPersonReceivingBenefits;
	private String payeeName;
	private String poaName;
	private String grossAmountofBenefit;

	private Timestamp createdOn;
	private Timestamp lastUpdatedOn;

	public String getSrsRetirementCode() {
		return retirementCode;
	}

	public void setSrsRetirementCode(String srsRetirementCode) {
		this.retirementCode = srsRetirementCode;
	}

	public String getSsnOfTheRetiree() {
		return ssnOfTheRetiree;
	}

	public void setSsnOfTheRetiree(String ssnOfTheRetiree) {
		this.ssnOfTheRetiree = ssnOfTheRetiree;
	}

	public String getSsnOfPersonReceivingBenefits() {
		return ssnOfPersonReceivingBenefits;
	}

	public void setSsnOfPersonReceivingBenefits(String ssnOfPersonReceivingBenefits) {
		this.ssnOfPersonReceivingBenefits = ssnOfPersonReceivingBenefits;
	}

	public String getPayeeName() {
		return payeeName;
	}

	public void setPayeeName(String payeeName) {
		this.payeeName = payeeName;
	}

	public String getPoaName() {
		return poaName;
	}

	public void setPoaName(String poaName) {
		this.poaName = poaName;
	}

	public String getGrossAmountofBenefit() {
		return grossAmountofBenefit;
	}

	public void setGrossAmountofBenefit(String grossAmountofBenefit) {
		this.grossAmountofBenefit = grossAmountofBenefit;
	}

	public Timestamp getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(Timestamp createdOn) {
		this.createdOn = createdOn;
	}

	public Timestamp getLastUpdatedOn() {
		return lastUpdatedOn;
	}

	public void setLastUpdatedOn(Timestamp lastUpdatedOn) {
		this.lastUpdatedOn = lastUpdatedOn;
	}

	@Override
	public String toString() {
		return "\nSrs{" + "createdOn='" + createdOn + '\'' + "lastUpdatedOn='" + lastUpdatedOn + '\''
				+ "srsRetirementCode='" + retirementCode + '\'' + ", ssnOfTheRetiree='" + ssnOfTheRetiree + '\''
				+ ", ssnOfPersonReceivingBenefits='" + ssnOfPersonReceivingBenefits + '\'' + ", payeeName='" + payeeName
				+ '\'' + ", poaName='" + poaName + '\'' + ", grossAmountofBenefit='" + grossAmountofBenefit + '\''
				+ '}';
	}

	@Override
	public boolean equals(Object obj) {
		if (obj != null && obj instanceof MySrs && this != null) {
			MySrs srs = (MySrs) obj;
			if (compareStrings(this.getSrsRetirementCode(), srs.getSrsRetirementCode())) {
				if (compareStrings(this.getSsnOfTheRetiree(), srs.getSsnOfTheRetiree())) {
					if (compareStrings(this.getSsnOfPersonReceivingBenefits(), srs.getSsnOfPersonReceivingBenefits())) {
						if (compareStrings(this.getPayeeName(), srs.getPayeeName())) {
							if (compareStrings(this.getPoaName(), srs.getPoaName())) {
								if (Double.parseDouble(this.getGrossAmountofBenefit()) == Double
										.parseDouble(srs.getGrossAmountofBenefit())) {
									return true;
								}
							}
						}
					}
				}
			}
		}
		return false;
	}

	/**
	 * @name: compareStrings
	 * @description: compares to strings
	 * @param: N/A
	 * @return: HashMap
	 */
	public boolean compareStrings(String input1, String input2) {

		if (input1 == null || input1.trim().equals("")) {
			if (input2 == null || input2.trim().equals("")) {
				return true;
			}
		} else if (input1 != null && input1.trim().equals(input2.trim())) {
			return true;
		}
		return false;
	}
}