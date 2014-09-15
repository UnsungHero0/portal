package vn.com.vndirect.web.tag;

import java.util.Calendar;
import java.util.Date;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

import org.apache.log4j.Logger;

import vn.com.vndirect.commons.utility.Constants;
import vn.com.vndirect.commons.utility.I18nContext;
import vn.com.web.commons.utility.DateUtils;

@SuppressWarnings("serial")
public class NewsDateTag extends TagSupport {
	private static Logger logger = Logger.getLogger(NewsDateTag.class);
	private String date;

	public int doStartTag() throws JspException {
		String LOCATION = "doStartTag()";
		logger.debug(LOCATION + "::BEGIN");

		try {
			Date d = DateUtils.stringToDate(date, DateUtils.Formats.STR_DATE_TIME_FORMAT_YYYYMMDD1);

			if (d != null) {
				String str = "";
				StringBuffer strBuffer = new StringBuffer();
				Calendar calendar = Calendar.getInstance();
				calendar.setTime(d);

				int day = calendar.get(Calendar.DAY_OF_WEEK);
				logger.debug(LOCATION + " day of week = " + day);
				I18nContext i18nContext = I18nContext.newInstance();
				if (day == 0) {
					str = i18nContext.getText(Constants.NewsDate.SUN);
				} else if (day == 1) {
					// str = I18NUtility.getI18nMessage(Constants.NewsDate.MON, request);
					str = i18nContext.getText(Constants.NewsDate.MON);
				} else if (day == 2) {
					// str = I18NUtility.getI18nMessage(Constants.NewsDate.TUE, request);
					str = i18nContext.getText(Constants.NewsDate.TUE);
				} else if (day == 3) {
					// str = I18NUtility.getI18nMessage(Constants.NewsDate.WED, request);
					str = i18nContext.getText(Constants.NewsDate.WED);
				} else if (day == 4) {
					// str = I18NUtility.getI18nMessage(Constants.NewsDate.THU, request);
					str = i18nContext.getText(Constants.NewsDate.THU);
				} else if (day == 5) {
					// str = I18NUtility.getI18nMessage(Constants.NewsDate.FRI, request);
					str = i18nContext.getText(Constants.NewsDate.FRI);
				} else if (day == 6) {
					// str = I18NUtility.getI18nMessage(Constants.NewsDate.SAT, request);
					str = i18nContext.getText(Constants.NewsDate.SAT);
				}

				strBuffer.append(str);
				String strDate = DateUtils.dateToString(d, DateUtils.Formats.STR_DATE_TIME_FORMAT_DDMMYYYY);
				strBuffer.append(strDate);
				logger.debug(LOCATION + " out = " + strBuffer.toString());
				pageContext.getOut().print(strBuffer.toString());

			}
		} catch (Exception ex) {
			logger.debug(LOCATION + ex);
			return EVAL_BODY_INCLUDE;
		}

		logger.debug(LOCATION + "::END");
		return SKIP_BODY;
	}

	public int doEndTag() throws JspException {
		return EVAL_PAGE;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}
}
