package vn.com.vndirect.business;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import vn.com.vndirect.dao.impl.WpDocumentDAO;
import vn.com.vndirect.dao.impl.WpProductDAO;
import vn.com.vndirect.dao.impl.WpSubjectDAO;
import vn.com.vndirect.dao.impl.WpTopicDAO;
import vn.com.vndirect.domain.Topic;
import vn.com.vndirect.domain.WpDocument;
import vn.com.vndirect.domain.WpProduct;
import vn.com.vndirect.domain.WpSubject;
import vn.com.vndirect.wsclient.osc.WpTopicQuestion;
import vn.com.web.commons.domain.db.PagingInfo;
import vn.com.web.commons.domain.db.SearchResult;
import vn.com.web.commons.exception.FunctionalException;
import vn.com.web.commons.exception.SystemException;

import com.google.code.ssm.api.ParameterValueKeyProvider;
import com.google.code.ssm.api.ReadThroughSingleCache;

@Component
public class OnlineServiceManager extends BaseManager implements IOnlineServiceManager {
	private static Logger logger = Logger.getLogger(OnlineServiceManager.class);
	private static final int EXPIRATION_TIME_FOR_CACHE = 600;

	@Autowired
	private WpProductDAO wpProductDAO;

	@Autowired
	private WpSubjectDAO wpSubjectDAO;
	
	@Autowired
	private WpDocumentDAO wpDocumentDAO;
	
	@Autowired
	private WpTopicDAO wpTopicDAO;

	/* (non-Javadoc)
	 * @see vn.com.vndirect.business.IOnlineServiceManager#getSubject(vn.com.vndirect.domain.WpSubject)
	 */
	@ReadThroughSingleCache(namespace = "portal.OnlineServiceManager.getSubject@", expiration = EXPIRATION_TIME_FOR_CACHE)
	public WpSubject getSubject(@ParameterValueKeyProvider final WpSubject subject) throws SystemException {
		return _getSubject(subject);
	}
	
	/* (non-Javadoc)
	 * @see vn.com.vndirect.business.IOnlineServiceManager#getSubject(vn.com.vndirect.domain.WpSubject, boolean)
	 */
	public WpSubject getSubject(final WpSubject subject, final boolean isUsingCache) throws SystemException {
		if (isUsingCache) {
			return getSubject(subject);
		}

		return _getSubject(subject);
	}
	
	private WpSubject _getSubject(final WpSubject subject) throws SystemException {
		final String location = "getSubject()";
		if (logger.isDebugEnabled()) {
			logger.debug(location + ":: BEGIN");
		}

		WpSubject result = null;
		try {
			final SearchResult<WpSubject> searchResult = wpSubjectDAO.searchSubject(subject, null);
			if (searchResult != null && searchResult.size() > 0)
				result = searchResult.get(0);
		} catch (Exception e) {
			logger.error(location, e);
			throw new SystemException(location, e);
		}

		if (logger.isDebugEnabled()) {
			logger.debug(location + ":: END");
		}

		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * vn.com.vndirect.business.IOnlineServiceManager#getSubjects(vn.com.vndirect
	 * .domain.WpSubject, vn.com.web.commons.domain.db.PagingInfo)
	 */
	public SearchResult<WpSubject> getSubjects(WpSubject subject, PagingInfo pagingInfo) throws SystemException {
		final String LOCATION = "getSubjects(subject, pagingInfo)";
		if (logger.isDebugEnabled()) {
			logger.debug(LOCATION + ":: BEGIN");
		}

		try {
			SearchResult<WpSubject> searchResult = wpSubjectDAO.searchSubject(subject, pagingInfo);
			if (logger.isDebugEnabled()) {
				logger.debug(LOCATION + ":: END");
			}

			return searchResult;
		} catch (Exception e) {
			logger.error(LOCATION, e);
			throw new SystemException(LOCATION, e);
		}

	}

	/* (non-Javadoc)
	 * @see vn.com.vndirect.business.IOnlineServiceManager#getProductByCode(vn.com.vndirect.domain.WpProduct)
	 */
	@ReadThroughSingleCache(namespace = "portal.OnlineServiceManager.getProductByCode@", expiration = EXPIRATION_TIME_FOR_CACHE)
	public WpProduct getProductByCodeWithCache(@ParameterValueKeyProvider(order = 0) final WpProduct product) throws SystemException {
		return _getProductByCode(product);
	}
	
	/* (non-Javadoc)
	 * @see vn.com.vndirect.business.IOnlineServiceManager#getProductByCode(vn.com.vndirect.domain.WpProduct, boolean)
	 */
	public WpProduct getProductByCode(final WpProduct product) throws SystemException {
		return _getProductByCode(product);
	}
	
	private WpProduct _getProductByCode(final WpProduct product) throws SystemException {
		final String LOCATION = "_getProductByCode(product)";
		if (logger.isDebugEnabled()) {
			logger.debug(LOCATION + ":: BEGIN");
		}

		WpProduct result = null;
		try {
			final SearchResult<WpProduct> searchProductResult = wpProductDAO.searchProduct(product, null);
			if (searchProductResult != null && searchProductResult.size() > 0) {
				result = searchProductResult.get(0);
				WpSubject wpSubject = new WpSubject();
				wpSubject.setWpProduct(result);
				wpSubject.setLanguage(result.getLanguage());

				SearchResult<WpSubject> searchSubjectList = wpSubjectDAO.searchSubject(wpSubject, null);
				if (searchSubjectList != null) {
					result.setWpSubjectList(searchSubjectList);
				}
			}
		} catch (Exception e) {
			logger.error(LOCATION, e);
			throw new SystemException(LOCATION, e);
		}

		if (logger.isDebugEnabled()) {
			logger.debug(LOCATION + ":: END");
		}
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * vn.com.vndirect.business.IOnlineServiceManager#searchDocumentProduct(
	 * vn.com.vndirect.domain.WpDocument,
	 * vn.com.web.commons.domain.db.PagingInfo)
	 */
	@ReadThroughSingleCache(namespace = "OnlineServiceManager.searchDocumentProduct@", expiration = EXPIRATION_TIME_FOR_CACHE)
	public SearchResult<WpDocument> searchDocumentProduct(@ParameterValueKeyProvider final WpDocument wpDocument,
			PagingInfo pagingInfo) throws SystemException {
		final String LOCATION = "searchDocumentProduct(wpDocument, PagingInfo)";
		if (logger.isDebugEnabled()) {
			logger.debug(LOCATION + ":: BEGIN");
		}

		try {
			final SearchResult<WpDocument> result = wpDocumentDAO.searchDocumentProduct(wpDocument, pagingInfo);

			if (logger.isDebugEnabled()) {
				logger.debug(LOCATION + ":: END");
			}

			return result;
		} catch (Exception e) {
			logger.error(LOCATION, e);
			throw new SystemException(LOCATION, e);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * vn.com.vndirect.business.IOnlineServiceManager#getTopicById(java.lang
	 * .Long)
	 */
	public Topic getTopicById(Long id) throws Exception {
		return wpTopicDAO.getTopicById(id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * vn.com.vndirect.business.IOnlineServiceManager#saveQuestion(vn.com.vndirect
	 * .wsclient.osc.WpTopicQuestion)
	 */
	public void saveQuestion(WpTopicQuestion question) throws Exception {
		wpTopicDAO.saveQuestion(question);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * vn.com.vndirect.business.IOnlineServiceManager#getQuestionByTopicId(java
	 * .lang.Long, vn.com.web.commons.domain.db.PagingInfo)
	 */
	public SearchResult<WpTopicQuestion> getQuestionByTopicId(Long id, PagingInfo pagingInfo) throws Exception {
		return wpTopicDAO.getQuestionByTopicId(id, pagingInfo);
	}

	/**
	 * @param wpProductDAO
	 */
	public void setWpProductDAO(WpProductDAO wpProductDAO) {
		this.wpProductDAO = wpProductDAO;
	}

	/**
	 * @param wpSubjectDAO
	 */
	public void setWpSubjectDAO(WpSubjectDAO wpSubjectDAO) {
		this.wpSubjectDAO = wpSubjectDAO;
	}

	/**
	 * @param wpDocumentDAO
	 */
	public void setWpDocumentDAO(WpDocumentDAO wpDocumentDAO) {
		this.wpDocumentDAO = wpDocumentDAO;
	}

	/**
	 * @param wpTopicDAO
	 */
	public void setWpTopicDAO(WpTopicDAO wpTopicDAO) {
		this.wpTopicDAO = wpTopicDAO;
	}
}