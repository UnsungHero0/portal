package vn.com.vndirect.domain.embeddb;

@SuppressWarnings("serial")
public class CompanyCalcView implements java.io.Serializable {

	// Fields
	private String secCode;
	private String industryCode;
	private String exchangeCode;
	private String sectorCode;
	private Double pe;
	private Double pb;
	private Double scopeMaketCap;
	private Double scopeAsset;
	private Double scopeEquity;
	private Double growthAsset;
	private Double growthRevenue;
	private Double roa;
	private Double roe;
	private Double profitMargin;
	private Double debtEquity;
	private Double currentRatio;
	private Double ebitda;

	// Constructors

	/** default constructor */
	public CompanyCalcView() {
	}

	/** minimal constructor */
	public CompanyCalcView(String secCode, String industryCode, String sectorCode) {
		this.secCode = secCode;
		this.industryCode = industryCode;
		this.sectorCode = sectorCode;
	}

	/** full constructor */
	public CompanyCalcView(String exchangeCode, String secCode, String industryCode, String sectorCode, Double pe, Double pb, Double scopeMaketCap, Double scopeAsset, Double scopeEquity,
			Double growthAsset, Double growthRevenue, Double roa, Double roe, Double profitMargin, Double debtEquity, Double currentRatio, Double ebitda) {
		this.exchangeCode = exchangeCode;
		this.secCode = secCode;
		this.industryCode = industryCode;
		this.sectorCode = sectorCode;
		this.pe = pe;
		this.pb = pb;
		this.scopeMaketCap = scopeMaketCap;
		this.scopeAsset = scopeAsset;
		this.scopeEquity = scopeEquity;
		this.growthAsset = growthAsset;
		this.growthRevenue = growthRevenue;
		this.roa = roa;
		this.roe = roe;
		this.profitMargin = profitMargin;
		this.debtEquity = debtEquity;
		this.currentRatio = currentRatio;
		this.ebitda = ebitda;
	}

	// Property accessors
	public String getIndustryCode() {
		return this.industryCode;
	}

	public void setIndustryCode(String industryCode) {
		this.industryCode = industryCode;
	}

	public String getSectorCode() {
		return this.sectorCode;
	}

	public void setSectorCode(String sectorCode) {
		this.sectorCode = sectorCode;
	}

	public Double getPe() {
		return this.pe;
	}

	public void setPe(Double pe) {
		this.pe = pe;
	}

	public Double getPb() {
		return this.pb;
	}

	public void setPb(Double pb) {
		this.pb = pb;
	}

	public Double getScopeMaketCap() {
		return this.scopeMaketCap;
	}

	public void setScopeMaketCap(Double scopeMaketCap) {
		this.scopeMaketCap = scopeMaketCap;
	}

	public Double getScopeAsset() {
		return this.scopeAsset;
	}

	public void setScopeAsset(Double scopeAsset) {
		this.scopeAsset = scopeAsset;
	}

	public Double getScopeEquity() {
		return this.scopeEquity;
	}

	public void setScopeEquity(Double scopeEquity) {
		this.scopeEquity = scopeEquity;
	}

	public Double getGrowthAsset() {
		return this.growthAsset;
	}

	public void setGrowthAsset(Double growthAsset) {
		this.growthAsset = growthAsset;
	}

	public Double getGrowthRevenue() {
		return this.growthRevenue;
	}

	public void setGrowthRevenue(Double growthRevenue) {
		this.growthRevenue = growthRevenue;
	}

	public Double getRoa() {
		return this.roa;
	}

	public void setRoa(Double roa) {
		this.roa = roa;
	}

	public Double getRoe() {
		return this.roe;
	}

	public void setRoe(Double roe) {
		this.roe = roe;
	}

	public Double getProfitMargin() {
		return this.profitMargin;
	}

	public void setProfitMargin(Double profitMargin) {
		this.profitMargin = profitMargin;
	}

	public Double getDebtEquity() {
		return this.debtEquity;
	}

	public void setDebtEquity(Double debtEquity) {
		this.debtEquity = debtEquity;
	}

	public Double getCurrentRatio() {
		return this.currentRatio;
	}

	public void setCurrentRatio(Double currentRatio) {
		this.currentRatio = currentRatio;
	}

	public Double getEbitda() {
		return this.ebitda;
	}

	public void setEbitda(Double ebitda) {
		this.ebitda = ebitda;
	}

	/**
	 * @return the exchangeCode
	 */
	public String getExchangeCode() {
		return exchangeCode;
	}

	/**
	 * @param exchangeCode
	 *            the exchangeCode to set
	 */
	public void setExchangeCode(String exchangeCode) {
		this.exchangeCode = exchangeCode;
	}

	/**
	 * @return the secCode
	 */
	public String getSecCode() {
		return secCode;
	}

	/**
	 * @param secCode
	 *            the secCode to set
	 */
	public void setSecCode(String secCode) {
		this.secCode = secCode;
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
		result = prime * result + ((currentRatio == null) ? 0 : currentRatio.hashCode());
		result = prime * result + ((debtEquity == null) ? 0 : debtEquity.hashCode());
		result = prime * result + ((ebitda == null) ? 0 : ebitda.hashCode());
		result = prime * result + ((growthAsset == null) ? 0 : growthAsset.hashCode());
		result = prime * result + ((growthRevenue == null) ? 0 : growthRevenue.hashCode());
		result = prime * result + ((industryCode == null) ? 0 : industryCode.hashCode());
		result = prime * result + ((pb == null) ? 0 : pb.hashCode());
		result = prime * result + ((pe == null) ? 0 : pe.hashCode());
		result = prime * result + ((profitMargin == null) ? 0 : profitMargin.hashCode());
		result = prime * result + ((roa == null) ? 0 : roa.hashCode());
		result = prime * result + ((roe == null) ? 0 : roe.hashCode());
		result = prime * result + ((scopeAsset == null) ? 0 : scopeAsset.hashCode());
		result = prime * result + ((scopeEquity == null) ? 0 : scopeEquity.hashCode());
		result = prime * result + ((scopeMaketCap == null) ? 0 : scopeMaketCap.hashCode());
		result = prime * result + ((exchangeCode == null) ? 0 : exchangeCode.hashCode());
		result = prime * result + ((secCode == null) ? 0 : secCode.hashCode());
		result = prime * result + ((sectorCode == null) ? 0 : sectorCode.hashCode());
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
		CompanyCalcView other = (CompanyCalcView) obj;
		if (currentRatio == null) {
			if (other.currentRatio != null)
				return false;
		} else if (!currentRatio.equals(other.currentRatio))
			return false;
		if (debtEquity == null) {
			if (other.debtEquity != null)
				return false;
		} else if (!debtEquity.equals(other.debtEquity))
			return false;
		if (ebitda == null) {
			if (other.ebitda != null)
				return false;
		} else if (!ebitda.equals(other.ebitda))
			return false;
		if (growthAsset == null) {
			if (other.growthAsset != null)
				return false;
		} else if (!growthAsset.equals(other.growthAsset))
			return false;
		if (growthRevenue == null) {
			if (other.growthRevenue != null)
				return false;
		} else if (!growthRevenue.equals(other.growthRevenue))
			return false;
		if (industryCode == null) {
			if (other.industryCode != null)
				return false;
		} else if (!industryCode.equals(other.industryCode))
			return false;
		if (pb == null) {
			if (other.pb != null)
				return false;
		} else if (!pb.equals(other.pb))
			return false;
		if (pe == null) {
			if (other.pe != null)
				return false;
		} else if (!pe.equals(other.pe))
			return false;
		if (profitMargin == null) {
			if (other.profitMargin != null)
				return false;
		} else if (!profitMargin.equals(other.profitMargin))
			return false;
		if (roa == null) {
			if (other.roa != null)
				return false;
		} else if (!roa.equals(other.roa))
			return false;
		if (roe == null) {
			if (other.roe != null)
				return false;
		} else if (!roe.equals(other.roe))
			return false;
		if (scopeAsset == null) {
			if (other.scopeAsset != null)
				return false;
		} else if (!scopeAsset.equals(other.scopeAsset))
			return false;
		if (scopeEquity == null) {
			if (other.scopeEquity != null)
				return false;
		} else if (!scopeEquity.equals(other.scopeEquity))
			return false;
		if (scopeMaketCap == null) {
			if (other.scopeMaketCap != null)
				return false;
		} else if (!scopeMaketCap.equals(other.scopeMaketCap))
			return false;
		if (exchangeCode == null) {
			if (other.exchangeCode != null)
				return false;
		} else if (!exchangeCode.equals(other.exchangeCode))
			return false;
		if (secCode == null) {
			if (other.secCode != null)
				return false;
		} else if (!secCode.equals(other.secCode))
			return false;
		if (sectorCode == null) {
			if (other.sectorCode != null)
				return false;
		} else if (!sectorCode.equals(other.sectorCode))
			return false;
		return true;
	}

}