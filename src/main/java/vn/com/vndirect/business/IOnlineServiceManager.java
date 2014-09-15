/**
 * 
 */
package vn.com.vndirect.business;

import java.rmi.RemoteException;

import vn.com.vndirect.domain.Topic;
import vn.com.vndirect.domain.WpDocument;
import vn.com.vndirect.domain.WpProduct;
import vn.com.vndirect.domain.WpSubject;
import vn.com.vndirect.wsclient.osc.WpTopicQuestion;
import vn.com.web.commons.domain.db.PagingInfo;
import vn.com.web.commons.domain.db.SearchResult;
import vn.com.web.commons.exception.SystemException;

/**
 * @author Duc Nguyen
 * 
 */
public interface IOnlineServiceManager extends IBaseManager {

	/**
	 * get list of products which belong to producGroupId
	 * 
	 * @param productGroupId
	 * @param pagingInfo
	 * @return
	 * @throws FunctionalException
	 * @throws Exception
	 * @throws RemoteException
	 */

	/**
	 * @param subject
	 * @return
	 * @throws FunctionalException
	 * @throws SystemException
	 */
	WpSubject getSubject(WpSubject subject) throws SystemException;
	
	/**
	 * @param subject
	 * @param isUsingCache
	 * @return
	 * @throws SystemException
	 */
	WpSubject getSubject(WpSubject subject, boolean isUsingCache) throws SystemException;

	/**
	 * @param subject
	 * @param pagingInfo
	 * @return
	 * @throws SystemException
	 */
	SearchResult<WpSubject> getSubjects(WpSubject subject, PagingInfo pagingInfo) throws SystemException;

	/**
	 * @param product
	 * @param pagingInfo
	 * @return
	 * @throws SystemException
	 */
	WpProduct getProductByCodeWithCache(WpProduct product) throws SystemException;
	
	/**
	 * @param product
	 * @param isUsingCache
	 * @return
	 * @throws SystemException
	 */
	public WpProduct getProductByCode(WpProduct product) throws SystemException;

	/**
	 * @param wpDocument
	 * @param pagingInfo
	 * @return
	 * @throws SystemException
	 */
	SearchResult<WpDocument> searchDocumentProduct(WpDocument wpDocument, PagingInfo pagingInfo) throws SystemException;

	/**
	 * Get a topic by its id
	 * 
	 * @param id
	 * @param pagingInfo
	 * @return
	 * @throws Exception
	 */
	Topic getTopicById(Long id) throws Exception;

	/**
	 * Save a question into DB
	 * 
	 * @param question
	 */
	void saveQuestion(WpTopicQuestion question) throws Exception;

	/**
	 * Get list of questions by topic_id
	 * 
	 * @return list of questions
	 * @throws Exception
	 */
	SearchResult<WpTopicQuestion> getQuestionByTopicId(Long id, PagingInfo pagingInfo) throws Exception;
}