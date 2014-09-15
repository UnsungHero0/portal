package vn.com.vndirect.business;

import vn.com.vndirect.commons.web.IWebStatistic;
import vn.com.web.commons.exception.SystemException;

/**
 * @author tungnq.nguyen
 * 
 */
public interface IWebStatisticManager extends IBaseManager {

	/**
	 * 
	 * @param code
	 * @return
	 */
	IWebStatistic getByCode(String code) throws SystemException;

	/**
	 * 
	 * @return
	 */
	IWebStatistic getVisited() throws SystemException;

	/**
     * 
     */
	void updateVisited() throws SystemException;
}
