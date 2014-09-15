package vn.com.vndirect.domain.embeddb;

/**
 * SectorCalcViewId entity. @author MyEclipse Persistence Tools
 */
@SuppressWarnings("serial")
public class SectorCalcView implements java.io.Serializable {

	// Fields

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
	public SectorCalcView() {
	}

	/** minimal constructor */
	public SectorCalcView(String sectorCode) {
		this.sectorCode = sectorCode;
	}

	/** full constructor */
	public SectorCalcView(String sectorCode, Double pe, Double pb, Double scopeMaketCap, Double scopeAsset, Double scopeEquity, Double growthAsset, Double growthRevenue, Double roa, Double roe,
			Double profitMargin, Double debtEquity, Double currentRatio, Double ebitda) {
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

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof SectorCalcView))
			return false;
		SectorCalcView castOther = (SectorCalcView) other;

		return ((this.getSectorCode() == castOther.getSectorCode()) || (this.getSectorCode() != null && castOther.getSectorCode() != null && this.getSectorCode().equals(castOther.getSectorCode())))
				&& ((this.getPe() == castOther.getPe()) || (this.getPe() != null && castOther.getPe() != null && this.getPe().equals(castOther.getPe())))
				&& ((this.getPb() == castOther.getPb()) || (this.getPb() != null && castOther.getPb() != null && this.getPb().equals(castOther.getPb())))
				&& ((this.getScopeMaketCap() == castOther.getScopeMaketCap()) || (this.getScopeMaketCap() != null && castOther.getScopeMaketCap() != null && this.getScopeMaketCap().equals(
						castOther.getScopeMaketCap())))
				&& ((this.getScopeAsset() == castOther.getScopeAsset()) || (this.getScopeAsset() != null && castOther.getScopeAsset() != null && this.getScopeAsset().equals(castOther.getScopeAsset())))
				&& ((this.getScopeEquity() == castOther.getScopeEquity()) || (this.getScopeEquity() != null && castOther.getScopeEquity() != null && this.getScopeEquity().equals(
						castOther.getScopeEquity())))
				&& ((this.getGrowthAsset() == castOther.getGrowthAsset()) || (this.getGrowthAsset() != null && castOther.getGrowthAsset() != null && this.getGrowthAsset().equals(
						castOther.getGrowthAsset())))
				&& ((this.getGrowthRevenue() == castOther.getGrowthRevenue()) || (this.getGrowthRevenue() != null && castOther.getGrowthRevenue() != null && this.getGrowthRevenue().equals(
						castOther.getGrowthRevenue())))
				&& ((this.getRoa() == castOther.getRoa()) || (this.getRoa() != null && castOther.getRoa() != null && this.getRoa().equals(castOther.getRoa())))
				&& ((this.getRoe() == castOther.getRoe()) || (this.getRoe() != null && castOther.getRoe() != null && this.getRoe().equals(castOther.getRoe())))
				&& ((this.getProfitMargin() == castOther.getProfitMargin()) || (this.getProfitMargin() != null && castOther.getProfitMargin() != null && this.getProfitMargin().equals(
						castOther.getProfitMargin())))
				&& ((this.getDebtEquity() == castOther.getDebtEquity()) || (this.getDebtEquity() != null && castOther.getDebtEquity() != null && this.getDebtEquity().equals(castOther.getDebtEquity())))
				&& ((this.getCurrentRatio() == castOther.getCurrentRatio()) || (this.getCurrentRatio() != null && castOther.getCurrentRatio() != null && this.getCurrentRatio().equals(
						castOther.getCurrentRatio())))
				&& ((this.getEbitda() == castOther.getEbitda()) || (this.getEbitda() != null && castOther.getEbitda() != null && this.getEbitda().equals(castOther.getEbitda())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result + (getSectorCode() == null ? 0 : this.getSectorCode().hashCode());
		result = 37 * result + (getPe() == null ? 0 : this.getPe().hashCode());
		result = 37 * result + (getPb() == null ? 0 : this.getPb().hashCode());
		result = 37 * result + (getScopeMaketCap() == null ? 0 : this.getScopeMaketCap().hashCode());
		result = 37 * result + (getScopeAsset() == null ? 0 : this.getScopeAsset().hashCode());
		result = 37 * result + (getScopeEquity() == null ? 0 : this.getScopeEquity().hashCode());
		result = 37 * result + (getGrowthAsset() == null ? 0 : this.getGrowthAsset().hashCode());
		result = 37 * result + (getGrowthRevenue() == null ? 0 : this.getGrowthRevenue().hashCode());
		result = 37 * result + (getRoa() == null ? 0 : this.getRoa().hashCode());
		result = 37 * result + (getRoe() == null ? 0 : this.getRoe().hashCode());
		result = 37 * result + (getProfitMargin() == null ? 0 : this.getProfitMargin().hashCode());
		result = 37 * result + (getDebtEquity() == null ? 0 : this.getDebtEquity().hashCode());
		result = 37 * result + (getCurrentRatio() == null ? 0 : this.getCurrentRatio().hashCode());
		result = 37 * result + (getEbitda() == null ? 0 : this.getEbitda().hashCode());
		return result;
	}

}