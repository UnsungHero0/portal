package vn.com.vndirect.domain;

import java.util.Date;

/**
 * IfoSecPriceViewId generated by MyEclipse - Hibernate Tools
 */

@SuppressWarnings("serial")
public class IfoSecPriceViewId implements java.io.Serializable {

	// Fields

	// Fields
	private Long companyId;
	private String exchangeCode;
	private String symbol;
	private Double openPrice;
	private Double highPrice;
	private Double lowPrice;
	private Double closePrice;
	private Double averagePrice;
	private Double volume;
	private Date transDate;
	private Double adOpenPrice;
	private Double adHighPrice;
	private Double adLowPrice;
	private Double adClosePrice;
	private Double adAveragePrice;
	private String rightsType;
	private Double ptVolume;

	// Constructors

	/** default constructor */
	public IfoSecPriceViewId() {
	}

	/** full constructor */
	public IfoSecPriceViewId(Long companyId, String exchangeCode, String symbol, Double openPrice, Double highPrice, Double lowPrice, Double closePrice, Double volume, Date transDate, Double ptVolume) {
		this.companyId = companyId;
		this.exchangeCode = exchangeCode;
		this.symbol = symbol;
		this.openPrice = openPrice;
		this.highPrice = highPrice;
		this.lowPrice = lowPrice;
		this.closePrice = closePrice;
		this.volume = volume;
		this.transDate = transDate;
		this.ptVolume = ptVolume;
	}

	// Property accessors

	public Long getCompanyId() {
		return this.companyId;
	}

	public void setCompanyId(Long companyId) {
		this.companyId = companyId;
	}

	public String getExchangeCode() {
		return this.exchangeCode;
	}

	public void setExchangeCode(String exchangeCode) {
		this.exchangeCode = exchangeCode;
	}

	public String getSymbol() {
		return this.symbol;
	}

	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}

	public Double getOpenPrice() {
		return this.openPrice;
	}

	public void setOpenPrice(Double openPrice) {
		this.openPrice = openPrice;
	}

	public Double getHighPrice() {
		return this.highPrice;
	}

	public void setHighPrice(Double highPrice) {
		this.highPrice = highPrice;
	}

	public Double getLowPrice() {
		return this.lowPrice;
	}

	public void setLowPrice(Double lowPrice) {
		this.lowPrice = lowPrice;
	}

	public Double getClosePrice() {
		return this.closePrice;
	}

	public void setClosePrice(Double closePrice) {
		this.closePrice = closePrice;
	}

	public Double getVolume() {
		return this.volume;
	}

	public void setVolume(Double volume) {
		this.volume = volume;
	}

	public Date getTransDate() {
		return this.transDate;
	}

	public void setTransDate(Date transDate) {
		this.transDate = transDate;
	}

	public Double getAveragePrice() {
		return averagePrice;
	}

	public void setAveragePrice(Double averagePrice) {
		this.averagePrice = averagePrice;
	}

	public Double getAdOpenPrice() {
		return adOpenPrice;
	}

	public void setAdOpenPrice(Double adOpenPrice) {
		this.adOpenPrice = adOpenPrice;
	}

	public Double getAdHighPrice() {
		return adHighPrice;
	}

	public void setAdHighPrice(Double adHighPrice) {
		this.adHighPrice = adHighPrice;
	}

	public Double getAdLowPrice() {
		return adLowPrice;
	}

	public void setAdLowPrice(Double adLowPrice) {
		this.adLowPrice = adLowPrice;
	}

	public Double getAdClosePrice() {
		return adClosePrice;
	}

	public void setAdClosePrice(Double adClosePrice) {
		this.adClosePrice = adClosePrice;
	}

	public Double getAdAveragePrice() {
		return adAveragePrice;
	}

	public void setAdAveragePrice(Double adAveragePrice) {
		this.adAveragePrice = adAveragePrice;
	}

	public String getRightsType() {
		return rightsType;
	}

	public void setRightsType(String rightsType) {
		this.rightsType = rightsType;
	}

	public Double getPtVolume() {
		return ptVolume;
	}

	public void setPtVolume(Double ptVolume) {
		this.ptVolume = ptVolume;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((adAveragePrice == null) ? 0 : adAveragePrice.hashCode());
		result = prime * result + ((adClosePrice == null) ? 0 : adClosePrice.hashCode());
		result = prime * result + ((adHighPrice == null) ? 0 : adHighPrice.hashCode());
		result = prime * result + ((adLowPrice == null) ? 0 : adLowPrice.hashCode());
		result = prime * result + ((adOpenPrice == null) ? 0 : adOpenPrice.hashCode());
		result = prime * result + ((averagePrice == null) ? 0 : averagePrice.hashCode());
		result = prime * result + ((closePrice == null) ? 0 : closePrice.hashCode());
		result = prime * result + ((companyId == null) ? 0 : companyId.hashCode());
		result = prime * result + ((exchangeCode == null) ? 0 : exchangeCode.hashCode());
		result = prime * result + ((highPrice == null) ? 0 : highPrice.hashCode());
		result = prime * result + ((lowPrice == null) ? 0 : lowPrice.hashCode());
		result = prime * result + ((openPrice == null) ? 0 : openPrice.hashCode());
		result = prime * result + ((ptVolume == null) ? 0 : ptVolume.hashCode());
		result = prime * result + ((rightsType == null) ? 0 : rightsType.hashCode());
		result = prime * result + ((symbol == null) ? 0 : symbol.hashCode());
		result = prime * result + ((transDate == null) ? 0 : transDate.hashCode());
		result = prime * result + ((volume == null) ? 0 : volume.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		IfoSecPriceViewId other = (IfoSecPriceViewId) obj;
		if (adAveragePrice == null) {
			if (other.adAveragePrice != null)
				return false;
		} else if (!adAveragePrice.equals(other.adAveragePrice))
			return false;
		if (adClosePrice == null) {
			if (other.adClosePrice != null)
				return false;
		} else if (!adClosePrice.equals(other.adClosePrice))
			return false;
		if (adHighPrice == null) {
			if (other.adHighPrice != null)
				return false;
		} else if (!adHighPrice.equals(other.adHighPrice))
			return false;
		if (adLowPrice == null) {
			if (other.adLowPrice != null)
				return false;
		} else if (!adLowPrice.equals(other.adLowPrice))
			return false;
		if (adOpenPrice == null) {
			if (other.adOpenPrice != null)
				return false;
		} else if (!adOpenPrice.equals(other.adOpenPrice))
			return false;
		if (averagePrice == null) {
			if (other.averagePrice != null)
				return false;
		} else if (!averagePrice.equals(other.averagePrice))
			return false;
		if (closePrice == null) {
			if (other.closePrice != null)
				return false;
		} else if (!closePrice.equals(other.closePrice))
			return false;
		if (companyId == null) {
			if (other.companyId != null)
				return false;
		} else if (!companyId.equals(other.companyId))
			return false;
		if (exchangeCode == null) {
			if (other.exchangeCode != null)
				return false;
		} else if (!exchangeCode.equals(other.exchangeCode))
			return false;
		if (highPrice == null) {
			if (other.highPrice != null)
				return false;
		} else if (!highPrice.equals(other.highPrice))
			return false;
		if (lowPrice == null) {
			if (other.lowPrice != null)
				return false;
		} else if (!lowPrice.equals(other.lowPrice))
			return false;
		if (openPrice == null) {
			if (other.openPrice != null)
				return false;
		} else if (!openPrice.equals(other.openPrice))
			return false;
		if (ptVolume == null) {
			if (other.ptVolume != null)
				return false;
		} else if (!ptVolume.equals(other.ptVolume))
			return false;
		if (rightsType == null) {
			if (other.rightsType != null)
				return false;
		} else if (!rightsType.equals(other.rightsType))
			return false;
		if (symbol == null) {
			if (other.symbol != null)
				return false;
		} else if (!symbol.equals(other.symbol))
			return false;
		if (transDate == null) {
			if (other.transDate != null)
				return false;
		} else if (!transDate.equals(other.transDate))
			return false;
		if (volume == null) {
			if (other.volume != null)
				return false;
		} else if (!volume.equals(other.volume))
			return false;
		return true;
	}
}