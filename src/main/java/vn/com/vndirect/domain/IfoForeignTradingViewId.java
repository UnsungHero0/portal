package vn.com.vndirect.domain;

import java.util.Date;

/**
 * IfoForeignTradingViewId entity.
 * 
 * @author MyEclipse Persistence Tools
 */
@SuppressWarnings("serial")
public class IfoForeignTradingViewId extends BaseBean implements java.io.Serializable {

	// Fields

	private String floorCode;
	private Date tradingDate;
	private String secCode;
	private Long totalRoom;
	private Long currentRoom;
	private Long buyingVolume;
	private Long buyingValue;
	private Long sellingVolume;
	private Long sellingValue;

	// Constructors

	/** default constructor */
	public IfoForeignTradingViewId() {
	}

	/** minimal constructor */
	public IfoForeignTradingViewId(String floorCode, Date tradingDate, String secCode, Long buyingVolume, Long buyingValue, Long sellingVolume, Long sellingValue) {
		this.floorCode = floorCode;
		this.tradingDate = tradingDate;
		this.secCode = secCode;
		this.buyingVolume = buyingVolume;
		this.buyingValue = buyingValue;
		this.sellingVolume = sellingVolume;
		this.sellingValue = sellingValue;
	}

	/** full constructor */
	public IfoForeignTradingViewId(String floorCode, Date tradingDate, String secCode, Long totalRoom, Long currentRoom, Long buyingVolume, Long buyingValue, Long sellingVolume, Long sellingValue) {
		this.floorCode = floorCode;
		this.tradingDate = tradingDate;
		this.secCode = secCode;
		this.totalRoom = totalRoom;
		this.currentRoom = currentRoom;
		this.buyingVolume = buyingVolume;
		this.buyingValue = buyingValue;
		this.sellingVolume = sellingVolume;
		this.sellingValue = sellingValue;
	}

	// Property accessors

	public String getFloorCode() {
		return this.floorCode;
	}

	public void setFloorCode(String floorCode) {
		this.floorCode = floorCode;
	}

	public Date getTradingDate() {
		return this.tradingDate;
	}

	public void setTradingDate(Date tradingDate) {
		this.tradingDate = tradingDate;
	}

	public String getSecCode() {
		return this.secCode;
	}

	public void setSecCode(String secCode) {
		this.secCode = secCode;
	}

	public Long getTotalRoom() {
		return this.totalRoom;
	}

	public void setTotalRoom(Long totalRoom) {
		this.totalRoom = totalRoom;
	}

	public Long getCurrentRoom() {
		return this.currentRoom;
	}

	public void setCurrentRoom(Long currentRoom) {
		this.currentRoom = currentRoom;
	}

	public Long getBuyingVolume() {
		return this.buyingVolume;
	}

	public void setBuyingVolume(Long buyingVolume) {
		this.buyingVolume = buyingVolume;
	}

	public Long getBuyingValue() {
		return this.buyingValue;
	}

	public void setBuyingValue(Long buyingValue) {
		this.buyingValue = buyingValue;
	}

	public Long getSellingVolume() {
		return this.sellingVolume;
	}

	public void setSellingVolume(Long sellingVolume) {
		this.sellingVolume = sellingVolume;
	}

	public Long getSellingValue() {
		return this.sellingValue;
	}

	public void setSellingValue(Long sellingValue) {
		this.sellingValue = sellingValue;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof IfoForeignTradingViewId))
			return false;
		IfoForeignTradingViewId castOther = (IfoForeignTradingViewId) other;

		return ((this.getFloorCode() == castOther.getFloorCode()) || (this.getFloorCode() != null && castOther.getFloorCode() != null && this.getFloorCode().equals(castOther.getFloorCode())))
				&& ((this.getTradingDate() == castOther.getTradingDate()) || (this.getTradingDate() != null && castOther.getTradingDate() != null && this.getTradingDate().equals(
						castOther.getTradingDate())))
				&& ((this.getSecCode() == castOther.getSecCode()) || (this.getSecCode() != null && castOther.getSecCode() != null && this.getSecCode().equals(castOther.getSecCode())))
				&& ((this.getTotalRoom() == castOther.getTotalRoom()) || (this.getTotalRoom() != null && castOther.getTotalRoom() != null && this.getTotalRoom().equals(castOther.getTotalRoom())))
				&& ((this.getCurrentRoom() == castOther.getCurrentRoom()) || (this.getCurrentRoom() != null && castOther.getCurrentRoom() != null && this.getCurrentRoom().equals(
						castOther.getCurrentRoom())))
				&& ((this.getBuyingVolume() == castOther.getBuyingVolume()) || (this.getBuyingVolume() != null && castOther.getBuyingVolume() != null && this.getBuyingVolume().equals(
						castOther.getBuyingVolume())))
				&& ((this.getBuyingValue() == castOther.getBuyingValue()) || (this.getBuyingValue() != null && castOther.getBuyingValue() != null && this.getBuyingValue().equals(
						castOther.getBuyingValue())))
				&& ((this.getSellingVolume() == castOther.getSellingVolume()) || (this.getSellingVolume() != null && castOther.getSellingVolume() != null && this.getSellingVolume().equals(
						castOther.getSellingVolume())))
				&& ((this.getSellingValue() == castOther.getSellingValue()) || (this.getSellingValue() != null && castOther.getSellingValue() != null && this.getSellingValue().equals(
						castOther.getSellingValue())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result + (getFloorCode() == null ? 0 : this.getFloorCode().hashCode());
		result = 37 * result + (getTradingDate() == null ? 0 : this.getTradingDate().hashCode());
		result = 37 * result + (getSecCode() == null ? 0 : this.getSecCode().hashCode());
		result = 37 * result + (getTotalRoom() == null ? 0 : this.getTotalRoom().hashCode());
		result = 37 * result + (getCurrentRoom() == null ? 0 : this.getCurrentRoom().hashCode());
		result = 37 * result + (getBuyingVolume() == null ? 0 : this.getBuyingVolume().hashCode());
		result = 37 * result + (getBuyingValue() == null ? 0 : this.getBuyingValue().hashCode());
		result = 37 * result + (getSellingVolume() == null ? 0 : this.getSellingVolume().hashCode());
		result = 37 * result + (getSellingValue() == null ? 0 : this.getSellingValue().hashCode());
		return result;
	}

}