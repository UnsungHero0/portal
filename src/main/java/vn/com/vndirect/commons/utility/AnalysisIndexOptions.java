/**
 * 
 */
package vn.com.vndirect.commons.utility;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.apache.struts2.ServletActionContext;

import vn.com.vndirect.embeddb.EDBItemCodeMapping;
import vn.com.vndirect.lucene.finfodb.beans.AnalysisCachingValueInfo;
import vn.com.vndirect.lucene.finfodb.beans.AnalysisIndexingBean;
import vn.com.web.commons.utility.SpringUtils;
import vn.com.web.commons.utility.Validation;

/**
 * @author Huy
 * 
 */
public class AnalysisIndexOptions {
	private boolean showNumber;
	private String itemName;
	private boolean showArrow;
	private boolean showPercent;
	private String pattern;
	// value of % Below 52 weeks HIGH
	private boolean pctBelow52WkHigh = false;
	// value of % Below 52 weeks LOW
	private boolean pctBelow52WkLow = false;
	private AnalysisIndexingBean bean;
	private String numberData;

	public AnalysisIndexOptions(AnalysisIndexingBean bean, boolean showNumber, String itemName, boolean showArrow,
	        boolean showPercent) {
		this.bean = bean;
		this.showNumber = showNumber;
		this.itemName = itemName;
		this.showArrow = showArrow;
		this.showPercent = showPercent;
	}

	public AnalysisIndexOptions(AnalysisIndexingBean bean, boolean showNumber, String itemName, boolean showArrow,
	        boolean showPercent, String pattern) {
		this(bean, showNumber, itemName, showArrow, showPercent);
		this.pattern = pattern;
	}

	public AnalysisIndexOptions(AnalysisIndexingBean bean, boolean showNumber, String itemName, boolean showArrow,
	        boolean showPercent, String pattern, boolean pctBelow52WkHigh, boolean pctBelow52WkLow) {
		this(bean, showNumber, itemName, showArrow, showPercent, pattern);
		this.pctBelow52WkHigh = pctBelow52WkHigh;
		this.pctBelow52WkLow = pctBelow52WkLow;
	}

	public String getFormattedData() {
		try {
			String str = null;
			String itemCode;
			itemName = (itemName == null ? "" : itemName.trim());
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
					str = "--";
				}
			} else {
				// price52w_high
				if (pctBelow52WkHigh) {
					// get price52w_high
					str = VNDirectWebUtilities.formatNumber(String.valueOf(bean.getPctBelow52WeekHigh()), pattern);
				} else if (pctBelow52WkLow) {
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
						value = bean.getValue(itemCode);
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

				if (!StringUtils.isEmpty(str)) {
					if (showArrow) {
						StringBuffer strBuff = new StringBuffer();
						double value = NumberUtils.toDouble(str);

						final String contextPath = ServletActionContext.getRequest().getContextPath();

						if (value > 0) {
							strBuff.append("<img src='" + contextPath + "/images/front/up.gif' /><font class='positivePrice'>+");
						} else if (value < 0) {
							strBuff.append("<img src='" + contextPath + "/images/front/down.gif' /><font class='negativePrice'>");
						} else {
							strBuff.append("<img src='" + contextPath
							        + "/images/front/bang.gif' /><font class='negativePrice'>&nbsp;");
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
			return str;
			// return "";
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}
	}
}
