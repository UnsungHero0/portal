package vn.com.vndirect.domain.embeddb;

import java.sql.Timestamp;
import java.util.Date;

@SuppressWarnings("serial")
public class SecPriceView implements java.io.Serializable {

	// Fields

	private String symbol;
	private Long companyId;
	private String exchangeCode;
	private Double openPrice;
	private Double highPrice;
	private Double lowPrice;
	private Double closePrice;
	private Double averagePrice;
	private Long volume;
	private Double adOpenPrice;
	private Double adHighPrice;
	private Double adLowPrice;
	private Double adClosePrice;
	private Double adAveragePrice;
	private String rightsType;
	private Date transDate;

	// Constructors

	/** default constructor */
	public SecPriceView() {
	}

	/** minimal constructor */
	public SecPriceView(String symbol, String exchangeCode) {
		this.symbol = symbol;
		this.exchangeCode = exchangeCode;
	}

	/** full constructor */
	public SecPriceView(String symbol, Long companyId, String exchangeCode, Double openPrice, Double highPrice, Double lowPrice, Double closePrice, Double averagePrice, Long volume,
			Double adOpenPrice, Double adHighPrice, Double adLowPrice, Double adClosePrice, Double adAveragePrice, String rightsType, Timestamp transDate) {
		this.symbol = symbol;
		this.companyId = companyId;
		this.exchangeCode = exchangeCode;
		this.openPrice = openPrice;
		this.highPrice = highPrice;
		this.lowPrice = lowPrice;
		this.closePrice = closePrice;
		this.averagePrice = averagePrice;
		this.volume = volume;
		this.adOpenPrice = adOpenPrice;
		this.adHighPrice = adHighPrice;
		this.adLowPrice = adLowPrice;
		this.adClosePrice = adClosePrice;
		this.adAveragePrice = adAveragePrice;
		this.rightsType = rightsType;
		this.transDate = transDate;
	}

	// Property accessors
	public String getSymbol() {
		return this.symbol;
	}

	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}

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

	public Double getAveragePrice() {
		return this.averagePrice;
	}

	public void setAveragePrice(Double averagePrice) {
		this.averagePrice = averagePrice;
	}

	public Long getVolume() {
		return this.volume;
	}

	public void setVolume(Long volume) {
		this.volume = volume;
	}

	public Double getAdOpenPrice() {
		return this.adOpenPrice;
	}

	public void setAdOpenPrice(Double adOpenPrice) {
		this.adOpenPrice = adOpenPrice;
	}

	public Double getAdHighPrice() {
		return this.adHighPrice;
	}

	public void setAdHighPrice(Double adHighPrice) {
		this.adHighPrice = adHighPrice;
	}

	public Double getAdLowPrice() {
		return this.adLowPrice;
	}

	public void setAdLowPrice(Double adLowPrice) {
		this.adLowPrice = adLowPrice;
	}

	public Double getAdClosePrice() {
		return this.adClosePrice;
	}

	public void setAdClosePrice(Double adClosePrice) {
		this.adClosePrice = adClosePrice;
	}

	public Double getAdAveragePrice() {
		return this.adAveragePrice;
	}

	public void setAdAveragePrice(Double adAveragePrice) {
		this.adAveragePrice = adAveragePrice;
	}

	public String getRightsType() {
		return this.rightsType;
	}

	public void setRightsType(String rightsType) {
		this.rightsType = rightsType;
	}

	public Date getTransDate() {
		return this.transDate;
	}

	public void setTransDate(Date transDate) {
		this.transDate = transDate;
	}

}