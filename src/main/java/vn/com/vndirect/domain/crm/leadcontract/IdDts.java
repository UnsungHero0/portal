package vn.com.vndirect.domain.crm.leadcontract;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlType;

import org.apache.commons.lang.StringUtils;

import vn.com.vndirect.commons.utility.CRMConstants;

@XmlType(name = "IdDts", propOrder = { "idTypeCode", "idCode", "dateOfIssue", "expiryDate", "issuingPlace" })
@SuppressWarnings("serial")
public class IdDts implements Serializable {
	
	private String idTypeCode;
	private String idCode;
	private String dateOfIssue;
	private String expiryDate;
	private String issuingPlace;
	
	public IdDts(){
		super();
	}
	
	public static class IdBuilder{
		private String idTypeCode = CRMConstants.ID_TYPES.IDCARD.getValue();//Default CMND
		private String expiryDate = StringUtils.EMPTY;
		
		private String idCode;
		private String dateOfIssue;
		private String issuingPlace;
		
		public IdBuilder(String idCode, String dateOfIssue, String issuingPlace){
			this.idCode = idCode;
			this.dateOfIssue = dateOfIssue;
			this.issuingPlace = issuingPlace;
		}
		
		public IdBuilder idTypeCode(String idTypeCode){
			this.idTypeCode = idTypeCode;
			return this;
		}
		
		public IdDts build(){
			return new IdDts(this);
		}
		
	}
	
	public IdDts (IdBuilder builder){
		this.dateOfIssue = builder.dateOfIssue;
		this.expiryDate = builder.expiryDate;
		this.idCode = builder.idCode;
		this.idTypeCode = builder.idTypeCode;
		this.issuingPlace = builder.issuingPlace;
	}
	/**
	 * @return the idTypeCode
	 */
	public String getIdTypeCode() {
		return idTypeCode;
	}

	/**
	 * @param idTypeCode
	 *            the idTypeCode to set
	 */
	public void setIdTypeCode(String idTypeCode) {
		this.idTypeCode = idTypeCode;
	}

	/**
	 * @return the idCode
	 */
	public String getIdCode() {
		return idCode;
	}

	/**
	 * @param idCode
	 *            the idCode to set
	 */
	public void setIdCode(String idCode) {
		this.idCode = idCode;
	}

	/**
	 * @return the dateOfIssue
	 */
	public String getDateOfIssue() {
		return dateOfIssue;
	}

	/**
	 * @param dateOfIssue
	 *            the dateOfIssue to set
	 */
	public void setDateOfIssue(String dateOfIssue) {
		this.dateOfIssue = dateOfIssue;
	}

	/**
	 * @return the expiryDate
	 */
	public String getExpiryDate() {
		return expiryDate;
	}

	/**
	 * @param expiryDate
	 *            the expiryDate to set
	 */
	public void setExpiryDate(String expiryDate) {
		this.expiryDate = expiryDate;
	}

	/**
	 * @return the issuingPlace
	 */
	public String getIssuingPlace() {
		return issuingPlace;
	}

	/**
	 * @param issuingPlace
	 *            the issuingPlace to set
	 */
	public void setIssuingPlace(String issuingPlace) {
		this.issuingPlace = issuingPlace;
	}

}
