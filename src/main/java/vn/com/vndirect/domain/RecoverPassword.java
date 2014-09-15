package vn.com.vndirect.domain;

public class RecoverPassword implements java.io.Serializable {
	private static final long serialVersionUID = -151758575179194156L;
	private java.lang.String contractNumber;
	private java.lang.String identificationCode;
	private java.lang.String identificationType;
	private java.lang.String idgSerialCard;
	private java.lang.String userName;
	private java.lang.String userType;

	public RecoverPassword() {
	}

	public java.lang.String getContractNumber() {
		return contractNumber;
	}

	public void setContractNumber(java.lang.String contractNumber) {
		this.contractNumber = contractNumber;
	}

	public java.lang.String getIdentificationCode() {
		return identificationCode;
	}

	public void setIdentificationCode(java.lang.String identificationCode) {
		this.identificationCode = identificationCode;
	}

	public java.lang.String getIdentificationType() {
		return identificationType;
	}

	public void setIdentificationType(java.lang.String identificationType) {
		this.identificationType = identificationType;
	}

	public java.lang.String getIdgSerialCard() {
		return idgSerialCard;
	}

	public void setIdgSerialCard(java.lang.String idgSerialCard) {
		this.idgSerialCard = idgSerialCard;
	}

	public java.lang.String getUserName() {
		return userName;
	}

	public void setUserName(java.lang.String userName) {
		this.userName = userName;
	}

	public java.lang.String getUserType() {
		return userType;
	}

	public void setUserType(java.lang.String userType) {
		this.userType = userType;
	}

	public vn.com.vndirect.wsclient.onlineuser.RecoverPassword getRecoverPassword() {
		vn.com.vndirect.wsclient.onlineuser.RecoverPassword recoverPassword = new vn.com.vndirect.wsclient.onlineuser.RecoverPassword();
		recoverPassword.setContractNumber(contractNumber);
		recoverPassword.setIdentifycationCode(identificationCode);
		recoverPassword.setIdentifycationType(identificationType);
		recoverPassword.setIdgSerialCard(idgSerialCard);
		recoverPassword.setUserName(userName);
		recoverPassword.setUserType(userType);
		return recoverPassword;
	}
}
