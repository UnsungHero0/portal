package vn.com.vndirect.domain.crm.leadcontract;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlType;

import org.apache.commons.lang.StringUtils;

import vn.com.vndirect.domain.OnlineAccount;

@XmlType(name = "BDts", propOrder = { "bankName", "bankCode", "bAccntName", "bAccntNumber", "bankLocation", "bAccntManagedBy",
		"bAccntDesc", "licenceNo", "licenceDate", "licenceIssuer", "operationNo", "operationDate" })
@SuppressWarnings("serial")
public class BDts implements Serializable {
	private String bankName;
	private String bankCode;
	private String bAccntName;
	private String bAccntNumber;
	private String bankLocation;
	private String bAccntManagedBy;
	private String bAccntDesc;
	private String licenceNo;
	private String licenceDate;
	private String licenceIssuer;
	private String operationNo;
	private String operationDate;
	
	
	public static class BankBuilder{
		private String bankName;
		private String bankCode;
		private String bAccntName;
		private String bAccntNumber;
		private String bankLocation;
		
		private String bAccntManagedBy = StringUtils.EMPTY;
		private String bAccntDesc = StringUtils.EMPTY;
		private String licenceNo = StringUtils.EMPTY;
		private String licenceDate = StringUtils.EMPTY;
		private String licenceIssuer = StringUtils.EMPTY;
		private String operationNo = StringUtils.EMPTY;
		private String operationDate = StringUtils.EMPTY;
		
		public BankBuilder (String bAccntName, String bAccntNumber){
			this.bAccntName = bAccntName;
			this.bAccntNumber = bAccntNumber;
		}
		
		public BankBuilder bankName(String bankName) {
			this.bankName = bankName;
			return this;
		}
		public BankBuilder bankCode(String bankCode) {
			this.bankCode = bankCode;
			return this;
		}
		public BankBuilder bankLocation(String bankLocation) {
			this.bankLocation = bankLocation;
			return this;
		}
		public BankBuilder bAccntManagedBy(String bAccntManagedBy) {
			this.bAccntManagedBy = bAccntManagedBy;
			return this;
		}
		public BankBuilder bAccntDesc(String bAccntDesc) {
			this.bAccntDesc = bAccntDesc;
			return this;
		}
		public BankBuilder licenceNo(String licenceNo) {
			this.licenceNo = licenceNo;
			return this;
		}
		public BankBuilder licenceDate(String licenceDate) {
			this.licenceDate = licenceDate;
			return this;
		}
		public BankBuilder licenceIssuer(String licenceIssuer) {
			this.licenceIssuer = licenceIssuer;
			return this;
		}
		public BankBuilder operationNo(String operationNo) {
			this.operationNo = operationNo;
			return this;
		}
		public BankBuilder operationDate(String operationDate) {
			this.operationDate = operationDate;
			return this;
		}
		
		public BDts build(){
			return new BDts(this);
		}
	}
	
	public BDts(BankBuilder builder){
		this.bankName = builder.bankName;
		this.bankCode = builder.bankCode;
		this.bAccntName = builder.bAccntName;
		this.bAccntNumber = builder.bAccntNumber;
		this.bankLocation = builder.bankLocation;
		this.bAccntManagedBy = builder.bAccntManagedBy;
		this.bAccntDesc = builder.bAccntDesc;
		this.licenceNo = builder.licenceNo;
		this.licenceDate = builder.licenceDate;
		this.licenceIssuer = builder.licenceIssuer;
		this.operationNo = builder.operationNo;
		this.operationDate = builder.operationDate;
	}
	
	public boolean isValidBankAccount() {
		return (StringUtils.isNotEmpty(this.bAccntNumber) && StringUtils.isNotEmpty(this.bankCode) 
				&& StringUtils.isNotEmpty(this.bankLocation) && StringUtils.isNotEmpty(this.bAccntName));
	}

	public BDts() {
		super();
	}

	

	/**
	 * @return the bankName
	 */
	public String getBankName() {
		return bankName;
	}

	/**
	 * @param bankName
	 *            the bankName to set
	 */
	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	/**
	 * @return the bankCode
	 */
	public String getBankCode() {
		return bankCode;
	}

	/**
	 * @param bankCode
	 *            the bankCode to set
	 */
	public void setBankCode(String bankCode) {
		this.bankCode = bankCode;
	}

	/**
	 * @return the bAccntName
	 */
	public String getbAccntName() {
		return bAccntName;
	}

	/**
	 * @param bAccntName
	 *            the bAccntName to set
	 */
	public void setbAccntName(String bAccntName) {
		this.bAccntName = bAccntName;
	}

	/**
	 * @return the bAccntNumber
	 */
	public String getbAccntNumber() {
		return bAccntNumber;
	}

	/**
	 * @param bAccntNumber
	 *            the bAccntNumber to set
	 */
	public void setbAccntNumber(String bAccntNumber) {
		this.bAccntNumber = bAccntNumber;
	}

	/**
	 * @return the bankLocation
	 */
	public String getBankLocation() {
		return bankLocation;
	}

	/**
	 * @param bankLocation
	 *            the bankLocation to set
	 */
	public void setBankLocation(String bankLocation) {
		this.bankLocation = bankLocation;
	}

	/**
	 * @return the bAccntManagedBy
	 */
	public String getbAccntManagedBy() {
		return bAccntManagedBy;
	}

	/**
	 * @param bAccntManagedBy
	 *            the bAccntManagedBy to set
	 */
	public void setbAccntManagedBy(String bAccntManagedBy) {
		this.bAccntManagedBy = bAccntManagedBy;
	}

	/**
	 * @return the bAccntDesc
	 */
	public String getbAccntDesc() {
		return bAccntDesc;
	}

	/**
	 * @param bAccntDesc
	 *            the bAccntDesc to set
	 */
	public void setbAccntDesc(String bAccntDesc) {
		this.bAccntDesc = bAccntDesc;
	}

	/**
	 * @return the licenceNo
	 */
	public String getLicenceNo() {
		return licenceNo;
	}

	/**
	 * @param licenceNo
	 *            the licenceNo to set
	 */
	public void setLicenceNo(String licenceNo) {
		this.licenceNo = licenceNo;
	}

	/**
	 * @return the licenceDate
	 */
	public String getLicenceDate() {
		return licenceDate;
	}

	/**
	 * @param licenceDate
	 *            the licenceDate to set
	 */
	public void setLicenceDate(String licenceDate) {
		this.licenceDate = licenceDate;
	}

	/**
	 * @return the licenceIssuer
	 */
	public String getLicenceIssuer() {
		return licenceIssuer;
	}

	/**
	 * @param licenceIssuer
	 *            the licenceIssuer to set
	 */
	public void setLicenceIssuer(String licenceIssuer) {
		this.licenceIssuer = licenceIssuer;
	}

	/**
	 * @return the operationNo
	 */
	public String getOperationNo() {
		return operationNo;
	}

	/**
	 * @param operationNo
	 *            the operationNo to set
	 */
	public void setOperationNo(String operationNo) {
		this.operationNo = operationNo;
	}

	/**
	 * @return the operationDate
	 */
	public String getOperationDate() {
		return operationDate;
	}

	/**
	 * @param operationDate
	 *            the operationDate to set
	 */
	public void setOperationDate(String operationDate) {
		this.operationDate = operationDate;
	}

}
