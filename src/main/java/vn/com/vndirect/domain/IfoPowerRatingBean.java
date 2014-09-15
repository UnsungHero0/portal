package vn.com.vndirect.domain;

import java.util.Date;

import vn.com.vndirect.commons.utility.VNDirectDateUtils;
import vn.com.vndirect.commons.utility.VNDirectWebUtilities;

@SuppressWarnings("serial")
public class IfoPowerRatingBean extends BaseBean {
	private String symbol;
	private Date transDate;
	private Double closePrice;
	private Integer mark;
	private Integer markB4days;
	private Double closePriceB4days;
	private Double pctMarkChange;
	private Double pctPriceChange;

	public String getSymbol() {
		return symbol;
	}

	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}

	public Date getTransDate() {
		return transDate;
	}

	public void setTransDate(Date transDate) {
		this.transDate = transDate;
	}

	public double getClosePrice() {
		return closePrice;
	}

	public void setClosePrice(Double closePrice) {
		this.closePrice = closePrice;
	}

	public Integer getMark() {
		return mark;
	}

	public void setMark(Integer mark) {
		this.mark = mark;
	}

	public Integer getMarkB4days() {
		return markB4days;
	}

	public void setMarkB4days(Integer markB4days) {
		this.markB4days = markB4days;
	}

	public Double getClosePriceB4days() {
		return closePriceB4days;
	}

	public void setClosePriceB4days(Double closePriceB4days) {
		this.closePriceB4days = closePriceB4days;
	}

	public Double getPctMarkChange() {
		return pctMarkChange;
	}

	public void setPctMarkChange(Double pctMarkChange) {
		this.pctMarkChange = pctMarkChange;
	}

	public Double getPctPriceChange() {
		return pctPriceChange;
	}

	public void setPctPriceChange(Double pctPriceChange) {
		this.pctPriceChange = pctPriceChange;
	}

	public String getStrTransDate() {
		return VNDirectDateUtils.dateToStringITA(transDate);
	}

	public String getPriceChangPct() {
		if (closePriceB4days == 0)
			return "-";
		else {
			double tmp = ((closePrice - closePriceB4days) / closePriceB4days) * 100;
			return VNDirectWebUtilities.getStrDoubleWithScale2(tmp) + "%";
		}
	}

	// private String securityCode;
	// private String todayPr;
	// private double price;
	// private String fourDayPr;
	// private double fourSessionPrice;
	// private Date transDate;
	// // private int prDelta;
	//
	// private String beforePrDelta;
	// private String beforePriceDelta;

	// public String getSecurityCode() {
	// return securityCode;
	// }
	//
	// public void setSecurityCode(String securityCode) {
	// this.securityCode = securityCode;
	// }
	//
	// public String getTodayPr() {
	// return todayPr;
	// }
	//
	// public void setTodayPr(String todayPr) {
	// this.todayPr = todayPr;
	// }
	//
	// public double getPrice() {
	// return price;
	// }
	//
	// public void setPrice(double price) {
	// this.price = price;
	// }
	//
	// public String getFourDayPr() {
	// if (fourDayPr == null)
	// return "-";
	// return fourDayPr;
	// }
	//
	// public void setFourDayPr(String fourDayPr) {
	// this.fourDayPr = fourDayPr;
	// }
	//
	// public double getFourSessionPrice() {
	// return fourSessionPrice;
	// }
	//
	// public void setFourSessionPrice(double fourSessionPrice) {
	// this.fourSessionPrice = fourSessionPrice;
	// }
	//
	// public String getPriceVariantPecentage() {
	// if (fourSessionPrice == 0)
	// return "-";
	// else {
	// double tmp = ((price - fourSessionPrice) / fourSessionPrice) * 100;
	// return VNDirectWebUtilities.getStrDoubleWithScale2(tmp) + "%";
	// }
	// }
	//
	// public Date getTransDate() {
	// return transDate;
	// }
	//
	// public void setTransDate(Date transDate) {
	// this.transDate = transDate;
	// }
	//	
	// public String getStrTransDate(){
	// return VNDirectDateUtils.dateToStringITA(transDate);
	// }
	//
	// public int getPrDelta() {
	// if(fourDayPr == null)
	// prDelta = 9999;
	// else
	// prDelta = Integer.valueOf(todayPr) - Integer.valueOf(fourDayPr);
	// return prDelta;
	// }
	//
	// public double getPriceDelta() {
	// if(fourSessionPrice == 0)
	// return 999999;
	// else
	// return Double.valueOf(price) - Double.valueOf(fourSessionPrice);
	// }
	//
	// public String getBeforePrDelta() {
	// return beforePrDelta;
	// }
	//
	// public void setBeforePrDelta(String beforePrDelta) {
	// this.beforePrDelta = beforePrDelta;
	// }
	//
	// public String getBeforePriceDelta() {
	// return beforePriceDelta;
	// }
	//
	// public void setBeforePriceDelta(String beforePriceDelta) {
	// this.beforePriceDelta = beforePriceDelta;
	// }
	//
	// public double getBeforePrDeltaAsDbl() {
	// if(beforePrDelta == null)
	// return 9999;
	// else
	// return Double.valueOf(beforePrDelta);
	// }
	//
	// public double getBeforePriceDeltaAsDbl() {
	// if(beforePriceDelta == null)
	// return 999999;
	// else
	// return Double.valueOf(beforePriceDelta);
	// }

}
