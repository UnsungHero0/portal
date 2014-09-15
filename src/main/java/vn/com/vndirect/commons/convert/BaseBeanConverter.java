package vn.com.vndirect.commons.convert;

import java.util.Date;
import java.util.GregorianCalendar;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

import vn.com.vndirect.wsclient.PagingInfo;
import vn.com.web.commons.utility.DateUtils;

public class BaseBeanConverter {

	/**
	 * @param xmlCal
	 * @return
	 */
	protected static Date convertXMLDate(XMLGregorianCalendar xmlCal) {
		if (xmlCal == null)
			return null;
		GregorianCalendar cal = xmlCal.toGregorianCalendar();
		return DateUtils.getCalendarDate(cal);
	}

	/**
	 * @param date
	 * @return
	 */
	protected static XMLGregorianCalendar convertToXMLDate(Date date) {
		if (date == null)
			return null;
		XMLGregorianCalendar xmlCal = null;
		try {
			GregorianCalendar cal = (GregorianCalendar) DateUtils.getCalendar(date);
			xmlCal = DatatypeFactory.newInstance().newXMLGregorianCalendar(cal);
		} catch (DatatypeConfigurationException e) {

		}
		return xmlCal;
	}

	/**
	 * To convert Web Service paging object to Web application paging object
	 * 
	 * @param wsp
	 *            Web Service paging object
	 * @return vn.com.web.commons.domain.db.PagingInfo
	 */
	public static vn.com.vndirect.domain.extend.PagingInfo convertToWebAppPaging(PagingInfo wsp, int indexPage) {
		vn.com.vndirect.domain.extend.PagingInfo wp = new vn.com.vndirect.domain.extend.PagingInfo();
		wp.setTotal(wsp.getTotalItem());
		wp.setOffset(wsp.getOffsetNumber());
		wp.setIndexPage(indexPage);
		return wp;
	}

	/**
	 * To convert Web Service paging object to Web application paging object
	 * 
	 * @param wp
	 *            Web application paging object
	 * @return vn.com.web.commons.domain.db.PagingInfo
	 */
	public static PagingInfo convertToWebServPaging(vn.com.vndirect.domain.extend.PagingInfo wp) {
		PagingInfo wsp = new PagingInfo();

		wsp.setCurrentIndex(wp.getIndex());
		wsp.setOffsetNumber(wp.getOffset());
		return wsp;
	}
}
