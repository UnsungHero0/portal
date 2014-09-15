package vn.com.vndirect.domain.embeddb;

import java.util.Date;

@SuppressWarnings("serial")
public class StockExchange implements java.io.Serializable {

	// Fields

	private String symbol;
	private String exchangeCode;
	private String exchangeName;
	private Long companyId;
	private String companyName;
	private String companyFullName;
	private String abbName;
	private Date firstTradingDate;
	private String industryCode;
	private String industryGroupCode;
	private String sectorCode;
	private String sectorGroupCode;

	// Constructors

	/** default constructor */
	public StockExchange() {
	}

	/** minimal constructor */
	public StockExchange(String symbol, String exchangeCode, String exchangeName) {
		this.symbol = symbol;
		this.exchangeCode = exchangeCode;
		this.exchangeName = exchangeName;
	}

	/** full constructor */
	public StockExchange(String symbol, String exchangeCode, String exchangeName, Long companyId, String companyName, String companyFullName, String abbName, Date firstTradingDate,
			String industryCode, String industryGroupCode, String sectorCode, String sectorGroupCode) {
		this.symbol = symbol;
		this.exchangeCode = exchangeCode;
		this.exchangeName = exchangeName;
		this.companyId = companyId;
		this.companyName = companyName;
		this.companyFullName = companyFullName;
		this.abbName = abbName;
		this.firstTradingDate = firstTradingDate;
		this.industryCode = industryCode;
		this.industryGroupCode = industryGroupCode;
		this.sectorCode = sectorCode;
		this.sectorGroupCode = sectorGroupCode;
	}

	// Property accessors
	public String getSymbol() {
		return this.symbol;
	}

	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}

	public String getExchangeCode() {
		return this.exchangeCode;
	}

	public void setExchangeCode(String exchangeCode) {
		this.exchangeCode = exchangeCode;
	}

	public String getExchangeName() {
		return this.exchangeName;
	}

	public void setExchangeName(String exchangeName) {
		this.exchangeName = exchangeName;
	}

	public Long getCompanyId() {
		return this.companyId;
	}

	public void setCompanyId(Long companyId) {
		this.companyId = companyId;
	}

	public String getCompanyName() {
		return this.companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getCompanyFullName() {
		return this.companyFullName;
	}

	public void setCompanyFullName(String companyFullName) {
		this.companyFullName = companyFullName;
	}

	public String getAbbName() {
		return this.abbName;
	}

	public void setAbbName(String abbName) {
		this.abbName = abbName;
	}

	public Date getFirstTradingDate() {
		return this.firstTradingDate;
	}

	public void setFirstTradingDate(Date firstTradingDate) {
		this.firstTradingDate = firstTradingDate;
	}

	public String getIndustryCode() {
		return this.industryCode;
	}

	public void setIndustryCode(String industryCode) {
		this.industryCode = industryCode;
	}

	public String getIndustryGroupCode() {
		return this.industryGroupCode;
	}

	public void setIndustryGroupCode(String industryGroupCode) {
		this.industryGroupCode = industryGroupCode;
	}

	public String getSectorCode() {
		return this.sectorCode;
	}

	public void setSectorCode(String sectorCode) {
		this.sectorCode = sectorCode;
	}

	public String getSectorGroupCode() {
		return this.sectorGroupCode;
	}

	public void setSectorGroupCode(String sectorGroupCode) {
		this.sectorGroupCode = sectorGroupCode;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((abbName == null) ? 0 : abbName.hashCode());
		result = prime * result + ((companyFullName == null) ? 0 : companyFullName.hashCode());
		result = prime * result + ((companyId == null) ? 0 : companyId.hashCode());
		result = prime * result + ((companyName == null) ? 0 : companyName.hashCode());
		result = prime * result + ((exchangeCode == null) ? 0 : exchangeCode.hashCode());
		result = prime * result + ((exchangeName == null) ? 0 : exchangeName.hashCode());
		result = prime * result + ((firstTradingDate == null) ? 0 : firstTradingDate.hashCode());
		result = prime * result + ((industryCode == null) ? 0 : industryCode.hashCode());
		result = prime * result + ((industryGroupCode == null) ? 0 : industryGroupCode.hashCode());
		result = prime * result + ((sectorCode == null) ? 0 : sectorCode.hashCode());
		result = prime * result + ((sectorGroupCode == null) ? 0 : sectorGroupCode.hashCode());
		result = prime * result + ((symbol == null) ? 0 : symbol.hashCode());
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		StockExchange other = (StockExchange) obj;
		if (abbName == null) {
			if (other.abbName != null)
				return false;
		} else if (!abbName.equals(other.abbName))
			return false;
		if (companyFullName == null) {
			if (other.companyFullName != null)
				return false;
		} else if (!companyFullName.equals(other.companyFullName))
			return false;
		if (companyId == null) {
			if (other.companyId != null)
				return false;
		} else if (!companyId.equals(other.companyId))
			return false;
		if (companyName == null) {
			if (other.companyName != null)
				return false;
		} else if (!companyName.equals(other.companyName))
			return false;
		if (exchangeCode == null) {
			if (other.exchangeCode != null)
				return false;
		} else if (!exchangeCode.equals(other.exchangeCode))
			return false;
		if (exchangeName == null) {
			if (other.exchangeName != null)
				return false;
		} else if (!exchangeName.equals(other.exchangeName))
			return false;
		if (firstTradingDate == null) {
			if (other.firstTradingDate != null)
				return false;
		} else if (!firstTradingDate.equals(other.firstTradingDate))
			return false;
		if (industryCode == null) {
			if (other.industryCode != null)
				return false;
		} else if (!industryCode.equals(other.industryCode))
			return false;
		if (industryGroupCode == null) {
			if (other.industryGroupCode != null)
				return false;
		} else if (!industryGroupCode.equals(other.industryGroupCode))
			return false;
		if (sectorCode == null) {
			if (other.sectorCode != null)
				return false;
		} else if (!sectorCode.equals(other.sectorCode))
			return false;
		if (sectorGroupCode == null) {
			if (other.sectorGroupCode != null)
				return false;
		} else if (!sectorGroupCode.equals(other.sectorGroupCode))
			return false;
		if (symbol == null) {
			if (other.symbol != null)
				return false;
		} else if (!symbol.equals(other.symbol))
			return false;
		return true;
	}

}