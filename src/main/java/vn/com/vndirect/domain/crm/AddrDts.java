package vn.com.vndirect.domain.crm;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlType;

import org.apache.commons.lang.StringUtils;

import vn.com.vndirect.commons.utility.CRMConstants;

@XmlType(name = "AddrDts", propOrder = { "addrType", "isCommunicationAddress", "address", "provinceCode", "postCode", "countryCode", "landMark", "addrDesc", "yearsofStay" })
@SuppressWarnings("serial")
public class AddrDts implements Serializable {
	private String addrType;
	private String isCommunicationAddress;
	private String address;
	private String provinceCode;
	private String postCode;
	private String countryCode;
	private String landMark;
	private String addrDesc;
	private String yearsofStay;

	public AddrDts() {
		super();
	}

	public static class AddressBuilder{
		private String address;
		
		private String addrType = CRMConstants.ADDR_TYPE.CurrentResidential;
		private String isCommunicationAddress = CRMConstants.YES_NO.YES;
		private String provinceCode = StringUtils.EMPTY;
		private String postCode = StringUtils.EMPTY;
		private String countryCode = StringUtils.EMPTY;
		private String landMark = StringUtils.EMPTY;
		private String addrDesc = StringUtils.EMPTY;
		private String yearsofStay = StringUtils.EMPTY;
		
		public AddressBuilder(String address){
			this.address = address;
		}
		
		public AddressBuilder isCommunicationAddress(String isCommunicationAddress){
			this.isCommunicationAddress = isCommunicationAddress;
			return this;
		}
		public AddressBuilder provinceCode(String provinceCode){
			this.provinceCode = provinceCode;
			return this;
		}
		public AddressBuilder postCode(String postCode){
			this.postCode = postCode;
			return this;
		}
		public AddressBuilder countryCode(String countryCode){
			this.countryCode = countryCode;
			return this;
		}
		public AddressBuilder landMark(String landMark){
			this.landMark = landMark;
			return this;
		}
		public AddressBuilder addrDesc(String addrDesc){
			this.addrDesc = addrDesc;
			return this;
		}
		public AddressBuilder yearsofStay(String yearsofStay){
			this.yearsofStay = yearsofStay;
			return this;
		}
		
		public AddrDts build(){
			return new AddrDts(this);
		}
		
	}
	
	public AddrDts (AddressBuilder builder){
		this.addrType = builder.addrType;
		this.isCommunicationAddress = builder.isCommunicationAddress;
		this.address = builder.address;
		this.provinceCode = builder.provinceCode;
		this.postCode = builder.postCode;
		this.countryCode = builder.countryCode;
		this.landMark = builder.landMark;
		this.addrDesc = builder.addrDesc;
		this.yearsofStay = builder.yearsofStay;
	}



	/**
	 * @return the addrType
	 */
	public String getAddrType() {
		return addrType;
	}

	/**
	 * @param addrType
	 *            the addrType to set
	 */
	public void setAddrType(String addrType) {
		this.addrType = addrType;
	}

	/**
	 * @return the isCommunicationAddress
	 */
	public String getIsCommunicationAddress() {
		return isCommunicationAddress;
	}

	/**
	 * @param isCommunicationAddress
	 *            the isCommunicationAddress to set
	 */
	public void setIsCommunicationAddress(String isCommunicationAddress) {
		this.isCommunicationAddress = isCommunicationAddress;
	}

	/**
	 * @return the address
	 */
	public String getAddress() {
		return address;
	}

	/**
	 * @param address
	 *            the address to set
	 */
	public void setAddress(String address) {
		this.address = address;
	}

	/**
	 * @return the provinceCode
	 */
	public String getProvinceCode() {
		return provinceCode;
	}

	/**
	 * @param provinceCode
	 *            the provinceCode to set
	 */
	public void setProvinceCode(String provinceCode) {
		this.provinceCode = provinceCode;
	}

	/**
	 * @return the postCode
	 */
	public String getPostCode() {
		return postCode;
	}

	/**
	 * @param postCode
	 *            the postCode to set
	 */
	public void setPostCode(String postCode) {
		this.postCode = postCode;
	}

	/**
	 * @return the countryCode
	 */
	public String getCountryCode() {
		return countryCode;
	}

	/**
	 * @param countryCode
	 *            the countryCode to set
	 */
	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}

	/**
	 * @return the landMark
	 */
	public String getLandMark() {
		return landMark;
	}

	/**
	 * @param landMark
	 *            the landMark to set
	 */
	public void setLandMark(String landMark) {
		this.landMark = landMark;
	}

	/**
	 * @return the addrDesc
	 */
	public String getAddrDesc() {
		return addrDesc;
	}

	/**
	 * @param addrDesc
	 *            the addrDesc to set
	 */
	public void setAddrDesc(String addrDesc) {
		this.addrDesc = addrDesc;
	}

	/**
	 * @return the yearsofStay
	 */
	public String getYearsofStay() {
		return yearsofStay;
	}

	/**
	 * @param yearsofStay
	 *            the yearsofStay to set
	 */
	public void setYearsofStay(String yearsofStay) {
		this.yearsofStay = yearsofStay;
	}

}
