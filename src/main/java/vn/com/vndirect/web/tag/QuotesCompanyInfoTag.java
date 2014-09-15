/*--------------------------------------------------------------------------*
| Modification Logs:
| DATE          AUTHOR     DESCRIPTION
| ------------------------------------------------
| Oct 2, 2006      TungNQ     First Creation
 *--------------------------------------------------------------------------*/

package vn.com.vndirect.web.tag;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.apache.velocity.context.Context;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import vn.com.vndirect.business.IQuotesManager;
import vn.com.vndirect.business.QuotesManager;
import vn.com.vndirect.commons.i18n.I18NUtility;
import vn.com.vndirect.commons.utility.Constants;
import vn.com.vndirect.commons.utility.SessionManager;
import vn.com.vndirect.domain.IfoCompanyIndustryView;
import vn.com.vndirect.domain.extend.CurrentCompanyForQuote;
import vn.com.web.commons.utility.SpringUtils;
import vn.com.web.commons.velocity.VelocityTemplate;

/**
 * @author duc.nguyen
 * 
 */
public class QuotesCompanyInfoTag extends BasicVelocityTag implements IWebTag {
	private static final long serialVersionUID = -2901318144923775674L;

	private static Logger logger = Logger.getLogger(QuotesCompanyInfoTag.class);
	
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
		return this.pageContext.getServletContext().getRealPath(VelocityTemplate.getTempName(Constants.VelocityTemplate.QUOTES_COMPANY_INFO));
	}

	@Override
	protected void populateContextParam(Context context) {
		final String LOCATION = "populateContextParam(context: " + context + ")";
		if (logger.isDebugEnabled())
			logger.debug(LOCATION + ":: BEGIN");
		try {
			
			IQuotesManager quotesManager = (IQuotesManager) SpringUtils.getBean(Constants.SpringBeanNames.QUOTES_MANAGER);
			
			CurrentCompanyForQuote curComp = quotesManager.quickSearchQuotes(SessionManager.getSymbolCompany(), I18NUtility.getCurrentLocale(ServletActionContext.getRequest().getSession()));

			logger.debug(LOCATION + ":: currentComp " + curComp);

			if (curComp != null) {
				context.put("company", curComp);

				String usLocale = I18NUtility.getCurrentLocale(pageContext.getSession());
				context.put("locale", usLocale == null ? "" : usLocale.toLowerCase());

				IfoCompanyIndustryView industryObj = curComp.getIfoCompanyIndustryView(usLocale);
				if (industryObj != null && industryObj.getId() != null) {
					context.put("industry", industryObj.getId());

					// set context path
					HttpServletRequest request = ServletActionContext.getRequest();
					context.put("contextPath", request.getContextPath());
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

}
