/*--------------------------------------------------------------------------*
 | Modification Logs:
 | DATE        AUTHOR      DESCRIPTION
 | ------------------------------------------------
 | Mar 12, 2008   TungNQ     First generate code
 *--------------------------------------------------------------------------*/

package vn.com.vndirect.web.tag;

import java.util.List;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import vn.com.vndirect.commons.utility.VNDirectWebUtilities;
import vn.com.vndirect.embeddb.EDBItemCodeMapping;
import vn.com.vndirect.lucene.finfodb.beans.AnalysisCachingValueInfo;
import vn.com.vndirect.lucene.finfodb.beans.AnalysisIndexingBean;
import vn.com.web.commons.utility.SpringUtils;
import vn.com.web.commons.utility.Validation;

/**
 * @author tungnq.nguyen
 * 
 */
public class AnalysisIndexingTag extends TagSupport {
	/**
	 * 
	 */
	private static final long serialVersionUID = -4679930020579546889L;
	private static Logger logger = Logger.getLogger(AnalysisIndexingTag.class);
	private String numberData;
	private AnalysisIndexingBean bean;
	private String itemName;
	private String pattern = "###,##0.##";
	private boolean showNumber = true;
	// is show up-down arrow
	private boolean showArrow = false;
	// is get value of % Below 52 weeks HIGH
	private boolean getPctBelow52WkHigh = false;
	// is get value of % Below 52 weeks LOW
	private boolean getPctBelow52WkLow = false;
	private boolean showPercent = false;
	// params
	private double closePrice;

	public AnalysisIndexingTag() {
		super();
	}

	/**
	 * @return the numberData
	 */
	public String getNumberData() {
		return numberData;
	}

	/**
	 * @param valueData
	 *            the numberData to set
	 */
	public void setNumberData(String numberData) {
		this.numberData = numberData;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.servlet.jsp.tagext.TagSupport#doStartTag()
	 */
	public int doStartTag() throws JspException {
		String LOCATION = "doStartTag()";
		if (logger.isDebugEnabled())
			logger.debug(LOCATION + ":: BEGIN");

		try {
			String str = null;
			String itemCode;
			itemName = (itemName == null ? "" : itemName.trim());
			if (logger.isDebugEnabled())
				logger.debug("+++ 1: Item Name: " + itemName);
			EDBItemCodeMapping itemCodeMapping = (EDBItemCodeMapping) SpringUtils.getBean("EDBItemCodeMapping");
			if (bean == null) {
				if (Validation.isNumber(numberData)) {
					double doubleValues = Double.parseDouble(VNDirectWebUtilities.cleanString(numberData));
					List<String> listPercentItemCode = itemCodeMapping.getListPercentItemCode();
					if (listPercentItemCode.contains(itemName)) {
						doubleValues = 100 * doubleValues;
					}
					str = VNDirectWebUtilities.formatNumber(String.valueOf(doubleValues), pattern);
				} else {
					str = "";
				}
			} else {
				// price52w_high
				if (getPctBelow52WkHigh) {
					// get price52w_high
					str = VNDirectWebUtilities.formatNumber(String.valueOf(bean.getPctBelow52WeekHigh()), pattern);
				} else if (getPctBelow52WkLow) {
					// getPctBelow52WkLow
					// getPctAbove52WeekLow
					str = VNDirectWebUtilities.formatNumber(String.valueOf(bean.getPctAbove52WeekLow()), pattern);
				} else if (bean != null && itemName.length() > 0) {
					boolean isVS_SMA = false;
					AnalysisCachingValueInfo value = null;
					if ("vs_sma_13_day".equalsIgnoreCase(itemName) || "vs_sma_50_day".equalsIgnoreCase(itemName)) {
						isVS_SMA = true;
					} else {
						itemCode = itemCodeMapping.getItemCode(itemName);
						itemCode = (itemCode == null ? "" : itemCode.trim());
						if (logger.isDebugEnabled())
							logger.debug("+++ 2: Item Name: " + itemName + ", itemCode:" + itemCode);
						value = bean.getValue(itemCode);
						if (logger.isDebugEnabled())
							logger.debug("+++ value: " + value);
					}

					if (isVS_SMA || value != null) {
						if (isVS_SMA || showNumber || value.hasNumericValue()) {
							Double d = null;
							// TODO sma_50 sma_13
							if ("vs_sma_13_day".equalsIgnoreCase(itemName)) {
								d = bean.getVsSma13Day();
							} else if ("vs_sma_50_day".equalsIgnoreCase(itemName)) {
								d = bean.getVsSma50Day();
							} else {
								d = value.getNumericValue();
							}
							if (d != null) {
								if (showPercent) {
									d = d * 100;
								}
								if ("market_cap".equals(itemName)) {
									str = VNDirectWebUtilities.formatNumber("" + (d.doubleValue() / 1000000000), pattern);
								} else {
									str = VNDirectWebUtilities.formatNumber(d.toString(), pattern);
								}
							}
						} else {
							str = value.getTextValue();
						}
					}
				}

				if (!Validation.isEmpty(str)) {
					if (showArrow) {
						StringBuffer strBuff = new StringBuffer();
						final String contextPath = ServletActionContext.getRequest().getContextPath();
						if (VNDirectWebUtilities.string2Double(str) > 0) {
							strBuff.append("<img src='" + contextPath + "/images/front/up.gif' /><font class='positivePrice'>+");
						} else if (VNDirectWebUtilities.string2Double(str) < 0) {
							strBuff.append("<img src='" + contextPath + "/images/front/down.gif' /><font class='negativePrice'>");
						} else {
							strBuff.append("<img src='" + contextPath + "/images/front/bang.gif' /><font class='negativePrice'>&nbsp;");
						}
						strBuff.append(str);
						if (showPercent) {
							strBuff.append("%");
						}
						strBuff.append("</font>");
						str = strBuff.toString();
					} else {
						if (showPercent) {
							str += "%";
						}
					}
				} else {
					str = "--";
				}
			}
			pageContext.getOut().print(str);
		} catch (Exception e) {
			if (logger.isDebugEnabled())
				logger.debug(LOCATION, e);
		}

		if (logger.isDebugEnabled())
			logger.debug(LOCATION + ":: END");
		return SKIP_BODY;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.servlet.jsp.tagext.TagSupport#doEndTag()
	 */
	public int doEndTag() throws JspException {
		return EVAL_PAGE;
	}

	/**
	 * @return the bean
	 */
	public AnalysisIndexingBean getBean() {
		return this.bean;
	}

	/**
	 * @param bean
	 *            the bean to set
	 */
	public void setBean(AnalysisIndexingBean bean) {
		this.bean = bean;
	}

	/**
	 * @return the showNumber
	 */
	public boolean getShowNumber() {
		return this.showNumber;
	}

	/**
	 * @param showNumber
	 *            the showNumber to set
	 */
	public void setShowNumber(boolean showNumber) {
		this.showNumber = showNumber;
	}

	/**
	 * @return the pattern
	 */
	public String getPattern() {
		return this.pattern;
	}

	/**
	 * @param pattern
	 *            the pattern to set
	 */
	public void setPattern(String pattern) {
		this.pattern = pattern;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public boolean isShowArrow() {
		return showArrow;
	}

	public void setShowArrow(boolean showArrow) {
		this.showArrow = showArrow;
	}

	public boolean isGetPctBelow52WkHigh() {
		return getPctBelow52WkHigh;
	}

	public void setGetPctBelow52WkHigh(boolean getPctBelow52WkHigh) {
		this.getPctBelow52WkHigh = getPctBelow52WkHigh;
	}

	public boolean isGetPctBelow52WkLow() {
		return getPctBelow52WkLow;
	}

	public void setGetPctBelow52WkLow(boolean getPctBelow52WkLow) {
		this.getPctBelow52WkLow = getPctBelow52WkLow;
	}

	public double getClosePrice() {
		return closePrice;
	}

	public void setClosePrice(double closePrice) {
		this.closePrice = closePrice;
	}

	public boolean isShowPercent() {
		return showPercent;
	}

	public void setShowPercent(boolean showPercent) {
		this.showPercent = showPercent;
	}
}
