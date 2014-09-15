/**
 * 
 */
package vn.com.vndirect.embeddb;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.StringTokenizer;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.InitializingBean;

/**
 * @author Blue9Frog
 * 
 */
public class EDBItemCodeMapping implements InitializingBean {
	private static final Log log = LogFactory.getLog(EDBItemCodeMapping.class);

	private Properties itemCodeMapping = new Properties();
	private EmbeddedDBParam embeddedDBParam;
	private String companyViewKey = "filter.company_view";
	private String industryViewKey = "filter.analysis_view";
	private String percentKey = "percent.item_code";
	private String prefixMapping = "mapping.";

	private List<String> listCompanyItemCode = new ArrayList<String>();
	private List<String> listIndustryItemCode = new ArrayList<String>();
	private List<String> listPercentItemCode = new ArrayList<String>();

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.beans.factory.InitializingBean#afterPropertiesSet()
	 */
	public void afterPropertiesSet() throws Exception {
		if (embeddedDBParam != null) {
			FileInputStream fio = null;
			try {
				fio = new FileInputStream(new File(embeddedDBParam.getItemcodeMappingPath()));
				itemCodeMapping.load(fio);
				log.debug("---->>>>> itemCodeMapping:" + itemCodeMapping);

				String str = null;
				StringTokenizer strToken = null;

				// +++ init listCompanyItemCode
				str = itemCodeMapping.getProperty(companyViewKey);
				if (str != null) {
					strToken = new StringTokenizer(str, ",; ");
					while (strToken.hasMoreTokens()) {
						str = strToken.nextToken();
						if (str.length() > 0 && !listCompanyItemCode.contains(str)) {
							listCompanyItemCode.add(str);
						} else {
							log.warn("--->>> company Item View: duplicated itemcode - " + str);
						}
					}
				}
				log.debug("--->>> listCompanyItemCode: " + listCompanyItemCode);
				// ---

				// +++ init listAnaysisItemCode
				str = itemCodeMapping.getProperty(industryViewKey);
				if (str != null) {
					strToken = new StringTokenizer(str, ",; ");
					while (strToken.hasMoreTokens()) {
						str = strToken.nextToken();
						if (str.length() > 0 && !listIndustryItemCode.contains(str)) {
							listIndustryItemCode.add(str);
						} else {
							log.warn("--->>> industryItem View: duplicated itemcode - " + str);
						}
					}
				}
				log.debug("--->>> listIndustryItemCode: " + listIndustryItemCode);
				// ---

				// +++ init percentKey
				str = itemCodeMapping.getProperty(percentKey);
				if (str != null) {
					strToken = new StringTokenizer(str, ",; ");
					while (strToken.hasMoreTokens()) {
						str = strToken.nextToken();
						if (str.length() > 0 && !listPercentItemCode.contains(str)) {
							listPercentItemCode.add(str);
						} else {
							log.warn("--->>> listPercentItemCode View: duplicated itemcode - " + str);
						}
					}
				}
				log.debug("--->>> listPercentItemCode: " + listIndustryItemCode);

			} catch (Exception ex) {
				log.error("afterPropertiesSet()", ex);
				throw ex;
			} finally {
				if (fio != null) {
					try {
						fio.close();
					} catch (Exception e) {
					}
				}
			}
		}
	}

	/**
	 * @param embeddedDBParam
	 *            the embeddedDBParam to set
	 */
	public void setEmbeddedDBParam(EmbeddedDBParam embeddedDBParam) {
		this.embeddedDBParam = embeddedDBParam;
	}

	/**
	 * @param companyViewKey
	 *            the companyViewKey to set
	 */
	public void setCompanyViewKey(String companyViewKey) {
		this.companyViewKey = companyViewKey;
	}

	/**
	 * @param analysisViewKey
	 *            the analysisViewKey to set
	 */
	public void setIndustryViewKey(String analysisViewKey) {
		this.industryViewKey = analysisViewKey;
	}

	/**
	 * @param prefixMapping
	 *            the prefixMapping to set
	 */
	public void setPrefixMapping(String prefixMapping) {
		this.prefixMapping = prefixMapping;
	}

	/**
	 * @return the listCompanyItemCode
	 */
	public List<String> getListCompanyItemCode() {
		return listCompanyItemCode;
	}

	/**
	 * @return the listAnaysisItemCode
	 */
	public List<String> getListIndustryItemCode() {
		return listIndustryItemCode;
	}

	/**
	 * get item code with mapping key: mapping.market_cap,....
	 * 
	 * @param mappingKey
	 * @return
	 */
	public String getItemCode(String mappingKey) {
		if (mappingKey != null) {
			if (mappingKey.startsWith(prefixMapping)) {
				return itemCodeMapping.getProperty(mappingKey, "");
			} else {
				return itemCodeMapping.getProperty(prefixMapping + mappingKey, "");
			}
		}
		return null;
	}

	/**
	 * @param percentKey
	 *            the percentKey to set
	 */
	public void setPercentKey(String percentKey) {
		this.percentKey = percentKey;
	}

	/**
	 * @return the listPercentItemCode
	 */
	public List<String> getListPercentItemCode() {
		return listPercentItemCode;
	}

}
