package vn.com.vndirect.web.tag;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.apache.velocity.context.Context;

import vn.com.vndirect.business.IQuotesManager;
import vn.com.vndirect.business.QuotesManager;
import vn.com.vndirect.commons.i18n.I18NUtility;
import vn.com.vndirect.commons.utility.Constants;
import vn.com.vndirect.commons.utility.SessionManager;
import vn.com.vndirect.commons.utility.VNDirectWebUtilities;
import vn.com.vndirect.domain.extend.CurrentCompanyForQuote;
import vn.com.vndirect.domain.extend.SecuritiesInfoForQuote;
import vn.com.web.commons.utility.SpringUtils;
import vn.com.web.commons.velocity.VelocityTemplate;

/**
 * @author duc.nguyen
 * 
 */
public class QuotesCompanySnapshotTag extends BasicVelocityTag implements IWebTag {
	private static final long serialVersionUID = -2901318144923775674L;

	private static Logger logger = Logger.getLogger(QuotesCompanySnapshotTag.class);
	private boolean chart = false;

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.servlet.jsp.tagext.TagSupport#doStartTag()
	 */
	public int doStartTag() throws JspException {
		final String LOCATION = "doStartTag()";
		if (logger.isDebugEnabled())
			logger.debug(LOCATION + ":: BEGIN");
		try {
			pageContext.getOut().print(generate());
		} catch (Exception e) {
			if (logger.isDebugEnabled()) {
				logger.debug("doStartTag()", e);
			}
		}
		if (logger.isDebugEnabled())
			logger.debug(LOCATION + ":: END");
		return SKIP_BODY;
	}

	@Override
	protected String getTemplatePath() {
		if (chart)
			return this.pageContext.getServletContext().getRealPath(VelocityTemplate.getTempName(Constants.VelocityTemplate.QUOTES_COMPANY_SNAPSHOT_FOR_CHART));
		else
			return this.pageContext.getServletContext().getRealPath(VelocityTemplate.getTempName(Constants.VelocityTemplate.QUOTES_COMPANY_SNAPSHOT));
	}

	@Override
	protected void populateContextParam(Context context) {
		final String LOCATION = "populateContextParam(context: " + context + ")";
		if (logger.isDebugEnabled())
			logger.debug(LOCATION + ":: BEGIN");
		try {
			IQuotesManager quotesManager = (IQuotesManager) SpringUtils.getBean(Constants.SpringBeanNames.QUOTES_MANAGER);
			HttpServletRequest request = (HttpServletRequest) pageContext.getRequest();
			CurrentCompanyForQuote currentComp = (CurrentCompanyForQuote) request.getAttribute(Constants.RequestKeys.REQUEST_CURRENT_COMPANY_OBJECT);
			if (currentComp == null) {
				
				currentComp = quotesManager.quickSearchQuotes(SessionManager.getSymbolCompany(), I18NUtility.getCurrentLocale(ServletActionContext.getRequest().getSession()));
			}
			if (currentComp != null) {
				SecuritiesInfoForQuote secuInfoForQuote = null;
				if (chart)
					secuInfoForQuote = quotesManager.getCompanySnapshotHighLightsForChart(SessionManager.OnlineUsers.getVNDSAuthenticationHeader(), currentComp);
				else
					secuInfoForQuote = quotesManager.getCompanySnapshotHighLights(SessionManager.OnlineUsers.getVNDSAuthenticationHeader(), currentComp);

				if (secuInfoForQuote != null) {

					if (VNDirectWebUtilities.isOpen(currentComp.getCurrentExchangeCode())) {
						secuInfoForQuote.setTimeType(1);
					} else {
						secuInfoForQuote.setTimeType(2);
					}

					secuInfoForQuote.setExchangeCode(currentComp.getCurrentExchangeCode());

					String HASTC = VNDirectWebUtilities.getHASTCExchange();
					String HOSTC = VNDirectWebUtilities.getHOSTCExchange();
					String VN30 = VNDirectWebUtilities.getVN30Exchange();
					String HNX30 = VNDirectWebUtilities.getHNX30Exchange();
					String OTC = VNDirectWebUtilities.getOTCExchange();

					if (VN30.equalsIgnoreCase(currentComp.getSymbol())) {
						secuInfoForQuote.setExchangeCode(VN30);
					}
					if (HNX30.equalsIgnoreCase(currentComp.getSymbol())) {
						secuInfoForQuote.setExchangeCode(HNX30);
					}
					context.put("securitiesInfo", secuInfoForQuote);
					context.put("HOSTC", HOSTC);
					context.put("VN30", VN30);
					context.put("HNX30", HNX30);
					context.put("HASTC", HASTC);
					context.put("OTC", OTC);

					context.put("utils", new UtilityFunctionsTag());

					if (logger.isDebugEnabled()) {
						logger.debug("secuInfoForQuote.getTimeType() " + secuInfoForQuote.getTimeType());
						logger.debug("secuInfoForQuote.getExchangeCode() " + secuInfoForQuote.getExchangeCode() + ":");
					}
				}
			}
		} catch (Exception e) {
			logger.error("QuotesCompanySnapshotTag", e);
		}
		if (logger.isDebugEnabled())
			logger.debug(LOCATION + ":: END");
	}

	public int doEndTag() throws JspException {
		return EVAL_PAGE;
	}

	/**
	 * @return the chart
	 */
	public boolean isChart() {
		return chart;
	}

	/**
	 * @param chart
	 *            the chart to set
	 */
	public void setChart(boolean chart) {
		this.chart = chart;
	}
}
