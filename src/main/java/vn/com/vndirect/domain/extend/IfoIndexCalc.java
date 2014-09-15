package vn.com.vndirect.domain.extend;

import java.util.Date;

import vn.com.vndirect.domain.BaseBean;
import vn.com.vndirect.domain.chartapi.IChartAPIData;

@SuppressWarnings("serial")
public class IfoIndexCalc extends BaseBean implements IChartAPIData {

	private long calcId;
	private String indexCode;
	private Date transDate;
	private Long transDateInMiliseconds;
	private String itemCode;
	private double numericValue;

	private Date fromTransDate;
	private Date toTransDate;

	/**
	 * @return the calcId
	 */
	public long getCalcId() {
		return calcId;
	}

	/**
	 * @param calcId
	 *            the calcId to set
	 */
	public void setCalcId(long calcId) {
		this.calcId = calcId;
	}

	/**
	 * @return the indexCode
	 */
	public String getIndexCode() {
		return indexCode;
	}

	/**
	 * @param indexCode
	 *            the indexCode to set
	 */
	public void setIndexCode(String indexCode) {
		this.indexCode = indexCode;
	}

	/**
	 * @return the transDate
	 */
	public Date getTransDate() {
		return transDate;
	}

	/**
	 * @param transDate
	 *            the transDate to set
	 */
	public void setTransDate(Date transDate) {
		this.transDate = transDate;
	}

	/**
	 * 
	 * @return
	 */
	public long getTransTime() {
		return (transDate == null ? 0 : transDate.getTime());
	}

	public long getTransTimeInSec() {
		return (transDate == null ? 0 : (transDate.getTime() / 1000));
	}

	public double getValue() {
		return numericValue;
	}

	/**
	 * @return the itemCode
	 */
	public String getItemCode() {
		return itemCode;
	}

	/**
	 * @param itemCode
	 *            the itemCode to set
	 */
	public void setItemCode(String itemCode) {
		this.itemCode = itemCode;
	}

	/**
	 * @return the numericValue
	 */
	public double getNumericValue() {
		return numericValue;
	}

	/**
	 * @param numericValue
	 *            the numericValue to set
	 */
	public void setNumericValue(double numericValue) {
		this.numericValue = numericValue;
	}

	/**
	 * @return the fromTransDate
	 */
	public Date getFromTransDate() {
		return fromTransDate;
	}

	/**
	 * @param fromTransDate
	 *            the fromTransDate to set
	 */
	public void setFromTransDate(Date fromTransDate) {
		this.fromTransDate = fromTransDate;
	}

	/**
	 * @return the toTransDate
	 */
	public Date getToTransDate() {
		return toTransDate;
	}

	/**
	 * @param toTransDate
	 *            the toTransDate to set
	 */
	public void setToTransDate(Date toTransDate) {
		this.toTransDate = toTransDate;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "IfoIndexCalc [calcId=" + calcId + ", fromTransDate=" + fromTransDate + ", indexCode=" + indexCode + ", itemCode="
		        + itemCode + ", numericValue=" + numericValue + ", toTransDate=" + toTransDate + ", transDate=" + transDate + "]";
	}

	public Long getTransDateInMiliseconds() {
		return transDateInMiliseconds;
	}

	public void setTransDateInMiliseconds(Long transDateInMiliseconds) {
		this.transDateInMiliseconds = transDateInMiliseconds;
	}

}
