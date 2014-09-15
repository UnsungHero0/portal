/**
 * 
 */
package vn.com.vndirect.business;

import java.util.List;

import vn.com.vndirect.domain.IfoCompanyCalcView;
import vn.com.vndirect.domain.SearchAnalysisIndexingBean;
import vn.com.vndirect.domain.SearchStockExchangeIndexingBean;
import vn.com.vndirect.domain.SearchStockScreenerBean;
import vn.com.vndirect.lucene.finfodb.beans.AnalysisIndexingBean;
import vn.com.vndirect.lucene.finfodb.beans.StockExchangeIndexingBean;
import vn.com.web.commons.domain.db.PagingInfo;
import vn.com.web.commons.domain.db.SearchResult;
import vn.com.web.commons.exception.FunctionalException;
import vn.com.web.commons.exception.SystemException;

/**
 * @author Huy
 * 
 */
public interface FinfoDBManager {
	/**
	 * 
	 * @param searchStockScreenerBean
	 * @return SearchStockScreenerBean
	 * @throws FunctionalException
	 * @throws SystemException
	 */
	public SearchStockScreenerBean calcStockScreener(SearchStockScreenerBean searchStockScreenerBean) throws FunctionalException,
	        SystemException;

	/**
	 * 
	 * @param searchStockScreenerBean
	 * @param pagingInfo
	 * @return SearchResult contains list of AnalysisIndexingBean objects
	 * @throws FunctionalException
	 * @throws SystemException
	 */
	public SearchResult<AnalysisIndexingBean> search(SearchStockScreenerBean searchStockScreenerBean, PagingInfo pagingInfo)
	        throws FunctionalException, SystemException;

	/**
	 * 
	 * @param searchObj
	 * @param pagingInfo
	 * @return
	 * @throws FunctionalException
	 * @throws SystemException
	 */
	public SearchResult<IfoCompanyCalcView> getListCompaniesByIndustry(IfoCompanyCalcView searchObj, PagingInfo pagingInfo)
	        throws FunctionalException, SystemException;

	/**
	 * 
	 * @param searchStockScreenerBean
	 * @return
	 * @throws FunctionalException
	 * @throws SystemException
	 */
	public int countStockScreener(SearchStockScreenerBean searchStockScreenerBean) throws FunctionalException, SystemException;

	/**
	 * 
	 * @param searchStockExchangeIndexingBean
	 * @return
	 * @throws FunctionalException
	 * @throws SystemException
	 */
	public List<StockExchangeIndexingBean> search(SearchStockExchangeIndexingBean searchStockExchangeIndexingBean)
	        throws FunctionalException, SystemException;

	/**
	 * 
	 * @param searchAnalysisIndexingBean
	 * @param pagingInfo
	 * @return SearchResult contains list of AnalysisIndexingBean objects
	 * @throws FunctionalException
	 * @throws SystemException
	 */
	public SearchResult<AnalysisIndexingBean> search(SearchAnalysisIndexingBean searchAnalysisIndexingBean, PagingInfo pagingInfo)
	        throws FunctionalException, SystemException;

}
