package vn.com.vndirect.domain;

import java.util.Date;

/**
 * IfoTradingStatisticsViewId entity.
 * 
 * @author MyEclipse Persistence Tools
 */

@SuppressWarnings("serial")
public class IfoTradingStatisticsViewId extends BaseBean implements java.io.Serializable {

	// Fields
	private String floorCode;
	private String secCode;
	private Date transDate;
	private Double openPrice;
	private Double highPrice;
	private Double lowPrice;
	private Double closePrice;
	private Double everagePrice;
	private Double bidOrder;
	private Double bidVolumn;
	private Double offerOrder;
	private Double offerVolumn;
	private Double totalVolumn;
	private Double totalValue;

	// Constructors

	/** default constructor */
	public IfoTradingStatisticsViewId() {
	}

	/** minimal constructor */
	public IfoTradingStatisticsViewId(String secCode, Date transDate, String floorCode) {
		this.floorCode = floorCode;
		this.secCode = secCode;
		this.transDate = transDate;
	}

	/** full constructor */
	public IfoTradingStatisticsViewId(String secCode, Date transDate, Double openPrice, Double highPrice, Double lowPrice, Double closePrice, Double everagePrice, Double bidOrder, Double bidVolumn,
			Double offerOrder, Double offerVolumn, Double totalVolumn, Double totalValue, String floorCode) {
		this.secCode = secCode;
		this.transDate = transDate;
		this.openPrice = openPrice;
		this.highPrice = highPrice;
		this.lowPrice = lowPrice;
		this.closePrice = closePrice;
		this.everagePrice = everagePrice;
		this.bidOrder = bidOrder;
		this.bidVolumn = bidVolumn;
		this.offerOrder = offerOrder;
		this.offerVolumn = offerVolumn;
		this.totalVolumn = totalVolumn;
		this.totalValue = totalValue;
		this.floorCode = floorCode;
	}

	// Property accessors

	public String getSecCode() {
		return this.secCode;
	}

	public void setSecCode(String secCode) {
		this.secCode = secCode;
	}

	public Date getTransDate() {
		return this.transDate;
	}

	public void setTransDate(Date transDate) {
		this.transDate = transDate;
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

	public Double getEveragePrice() {
		return this.everagePrice;
	}

	public void setEveragePrice(Double everagePrice) {
		this.everagePrice = everagePrice;
	}

	public Double getBidOrder() {
		return this.bidOrder;
	}

	public void setBidOrder(Double bidOrder) {
		this.bidOrder = bidOrder;
	}

	public Double getBidVolumn() {
		return this.bidVolumn;
	}

	public void setBidVolumn(Double bidVolumn) {
		this.bidVolumn = bidVolumn;
	}

	public Double getOfferOrder() {
		return this.offerOrder;
	}

	public void setOfferOrder(Double offerOrder) {
		this.offerOrder = offerOrder;
	}

	public Double getOfferVolumn() {
		return this.offerVolumn;
	}

	public void setOfferVolumn(Double offerVolumn) {
		this.offerVolumn = offerVolumn;
	}

	public Double getTotalVolumn() {
		return this.totalVolumn;
	}

	public void setTotalVolumn(Double totalVolumn) {
		this.totalVolumn = totalVolumn;
	}

	public Double getTotalValue() {
		return this.totalValue;
	}

	public void setTotalValue(Double totalValue) {
		this.totalValue = totalValue;
	}

	public String getFloorCode() {
		return this.floorCode;
	}

	public void setFloorCode(String floorCode) {
		this.floorCode = floorCode;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof IfoTradingStatisticsViewId))
			return false;
		IfoTradingStatisticsViewId castOther = (IfoTradingStatisticsViewId) other;

		return ((this.getSecCode() == castOther.getSecCode()) || (this.getSecCode() != null && castOther.getSecCode() != null && this.getSecCode().equals(castOther.getSecCode())))
				&& ((this.getTransDate() == castOther.getTransDate()) || (this.getTransDate() != null && castOther.getTransDate() != null && this.getTransDate().equals(castOther.getTransDate())))
				&& ((this.getOpenPrice() == castOther.getOpenPrice()) || (this.getOpenPrice() != null && castOther.getOpenPrice() != null && this.getOpenPrice().equals(castOther.getOpenPrice())))
				&& ((this.getHighPrice() == castOther.getHighPrice()) || (this.getHighPrice() != null && castOther.getHighPrice() != null && this.getHighPrice().equals(castOther.getHighPrice())))
				&& ((this.getLowPrice() == castOther.getLowPrice()) || (this.getLowPrice() != null && castOther.getLowPrice() != null && this.getLowPrice().equals(castOther.getLowPrice())))
				&& ((this.getClosePrice() == castOther.getClosePrice()) || (this.getClosePrice() != null && castOther.getClosePrice() != null && this.getClosePrice().equals(castOther.getClosePrice())))
				&& ((this.getEveragePrice() == castOther.getEveragePrice()) || (this.getEveragePrice() != null && castOther.getEveragePrice() != null && this.getEveragePrice().equals(
						castOther.getEveragePrice())))
				&& ((this.getBidOrder() == castOther.getBidOrder()) || (this.getBidOrder() != null && castOther.getBidOrder() != null && this.getBidOrder().equals(castOther.getBidOrder())))
				&& ((this.getBidVolumn() == castOther.getBidVolumn()) || (this.getBidVolumn() != null && castOther.getBidVolumn() != null && this.getBidVolumn().equals(castOther.getBidVolumn())))
				&& ((this.getOfferOrder() == castOther.getOfferOrder()) || (this.getOfferOrder() != null && castOther.getOfferOrder() != null && this.getOfferOrder().equals(castOther.getOfferOrder())))
				&& ((this.getOfferVolumn() == castOther.getOfferVolumn()) || (this.getOfferVolumn() != null && castOther.getOfferVolumn() != null && this.getOfferVolumn().equals(
						castOther.getOfferVolumn())))
				&& ((this.getTotalVolumn() == castOther.getTotalVolumn()) || (this.getTotalVolumn() != null && castOther.getTotalVolumn() != null && this.getTotalVolumn().equals(
						castOther.getTotalVolumn())))
				&& ((this.getTotalValue() == castOther.getTotalValue()) || (this.getTotalValue() != null && castOther.getTotalValue() != null && this.getTotalValue().equals(castOther.getTotalValue())))
				&& ((this.getFloorCode() == castOther.getFloorCode()) || (this.getFloorCode() != null && castOther.getFloorCode() != null && this.getFloorCode().equals(castOther.getFloorCode())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result + (getSecCode() == null ? 0 : this.getSecCode().hashCode());
		result = 37 * result + (getTransDate() == null ? 0 : this.getTransDate().hashCode());
		result = 37 * result + (getOpenPrice() == null ? 0 : this.getOpenPrice().hashCode());
		result = 37 * result + (getHighPrice() == null ? 0 : this.getHighPrice().hashCode());
		result = 37 * result + (getLowPrice() == null ? 0 : this.getLowPrice().hashCode());
		result = 37 * result + (getClosePrice() == null ? 0 : this.getClosePrice().hashCode());
		result = 37 * result + (getEveragePrice() == null ? 0 : this.getEveragePrice().hashCode());
		result = 37 * result + (getBidOrder() == null ? 0 : this.getBidOrder().hashCode());
		result = 37 * result + (getBidVolumn() == null ? 0 : this.getBidVolumn().hashCode());
		result = 37 * result + (getOfferOrder() == null ? 0 : this.getOfferOrder().hashCode());
		result = 37 * result + (getOfferVolumn() == null ? 0 : this.getOfferVolumn().hashCode());
		result = 37 * result + (getTotalVolumn() == null ? 0 : this.getTotalVolumn().hashCode());
		result = 37 * result + (getTotalValue() == null ? 0 : this.getTotalValue().hashCode());
		result = 37 * result + (getFloorCode() == null ? 0 : this.getFloorCode().hashCode());
		return result;
	}

}