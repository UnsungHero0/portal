package vn.com.vndirect.web.struts2.chart;

import java.util.Calendar;
import java.util.Date;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import vn.com.vndirect.business.IQuotesManager;
import vn.com.vndirect.business.QuotesManager;
import vn.com.vndirect.domain.extend.IfoIndexCalc;
import vn.com.vndirect.domain.extend.SearchIfoSecPrice;
import vn.com.vndirect.domain.struts2.chart.ChartDataAJAXModel;
import vn.com.web.commons.domain.db.PagingInfo;
import vn.com.web.commons.domain.db.SearchResult;
import vn.com.web.commons.utility.DateUtils;
import vn.com.web.commons.utility.Utilities;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Preparable;

public class ChartDataAJAXAction extends ActionSupport implements ModelDriven<ChartDataAJAXModel>, Preparable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -5677811874364809659L;

	private final static Logger logger = Logger.getLogger(ChartDataAJAXAction.class);

	private ChartDataAJAXModel model = new ChartDataAJAXModel();
	
	@Autowired
	private IQuotesManager quotesManager;

	/**
	 * @param quotesManager
	 *            the quotesManager to set
	 */
	public void setQuotesManager(IQuotesManager quotesManager) {
		this.quotesManager = quotesManager;
	}

	public ChartDataAJAXModel getModel() {
		return model;
	}

	public void prepare() throws Exception {
		// TODO Auto-generated method stub
	}

	/**
	 *
	 */
	public String executeSearchSymbol() throws Exception {
		final String LOCATION = "executeSearchSymbol()";
		if (logger.isDebugEnabled())
			logger.debug(LOCATION + ":: BEGIN");

		try {
			SearchIfoSecPrice searchObj = new SearchIfoSecPrice();
			searchObj.setSymbol(model.getIdxCode());

			searchObj.setStartDate(this.convert(model.getStrFromDate()));
			searchObj.setEndDate(this.convert(model.getStrToDate()));

			PagingInfo pagingInfo = new PagingInfo(Integer.MAX_VALUE);
			model.setLstSecPrice(quotesManager.searchStockPrices(searchObj, pagingInfo));
		} catch (Exception e) {
			logger.error(LOCATION + ":: Exception: " + e);
			Utilities.processErrors(this, e);
		}

		if (logger.isDebugEnabled())
			logger.debug(LOCATION + ":: END");
		return SUCCESS;
	}

	/**
	 * 
	 * @return
	 * @throws Exception
	 */
	public String executeSearchIndexComp() throws Exception {
		final String LOCATION = "executeSearchIndexComp()";
		if (logger.isDebugEnabled())
			logger.debug(LOCATION + ":: BEGIN");

		try {
			IfoIndexCalc searchObj = new IfoIndexCalc();
			searchObj.setIndexCode(model.getIdxCode());
			if (model.getPm() > 0) {
				Calendar cal = Calendar.getInstance();
				cal.set(Calendar.HOUR_OF_DAY, 0);
				cal.set(Calendar.MINUTE, 0);
				cal.set(Calendar.SECOND, 0);
				cal.set(Calendar.MILLISECOND, 0);

				searchObj.setToTransDate(cal.getTime());

				cal.add(Calendar.MONTH, -1 * model.getPm());
				searchObj.setFromTransDate(cal.getTime());

			} else {
				searchObj.setFromTransDate(this.convert(model.getStrFromDate()));
				searchObj.setToTransDate(this.convert(model.getStrToDate()));
			}

			SearchResult<IfoIndexCalc> result = quotesManager.searchIndexOfSectorAndIndustry(searchObj);
			SearchResult<IfoIndexCalc> newRes = new SearchResult<IfoIndexCalc>();
			IfoIndexCalc newCalc;
			for (IfoIndexCalc ifoIndexCalc : result) {
				newCalc = new IfoIndexCalc();
				Date d = ifoIndexCalc.getTransDate();
				newCalc.setTransDate(d);
				newCalc.setTransDateInMiliseconds(d.getTime());
				newCalc.setNumericValue(ifoIndexCalc.getNumericValue());
				newRes.add(newCalc);
			}

			model.setLstIndexCalc(newRes);
			// model.calcIndexChartApi();
		} catch (Exception e) {
			logger.error(LOCATION + ":: Exception: " + e);
			Utilities.processErrors(this, e);
		}

		if (logger.isDebugEnabled())
			logger.debug(LOCATION + ":: END");
		return SUCCESS;
	}

	/**
	 * 
	 * @param strDate
	 * @return
	 */
	private Date convert(String strDate) {
		try {
			Date date = DateUtils.stringToDate(strDate, DateUtils.Formats.STR_DATE_TIME_FORMAT_DDMMYYYY);
			Calendar cal = Calendar.getInstance();
			cal.setTime(date);
			cal.set(Calendar.HOUR_OF_DAY, 0);
			cal.set(Calendar.MINUTE, 0);
			cal.set(Calendar.SECOND, 0);
			cal.set(Calendar.MILLISECOND, 0);

			return cal.getTime();
		} catch (Exception e) {
		}
		return null;
	}
}
