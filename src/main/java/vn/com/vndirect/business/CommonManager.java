package vn.com.vndirect.business;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import vn.com.vndirect.dao.impl.IfoCompanyDAO;
import vn.com.vndirect.dao.impl.IfoDataRefDAO;
import vn.com.vndirect.dao.impl.VoteDAO;
import vn.com.vndirect.domain.IfoDataRef;
import vn.com.vndirect.domain.Vote;
import vn.com.web.commons.domain.db.SearchResult;
import vn.com.web.commons.exception.FunctionalException;
import vn.com.web.commons.exception.SystemException;

import com.google.code.ssm.api.ParameterValueKeyProvider;
import com.google.code.ssm.api.ReadThroughSingleCache;

@Component
public class CommonManager extends BaseManager implements ICommonManager {
	private static Logger logger = Logger.getLogger(CommonManager.class);
	private static final int EXPIRATION_TIME_FOR_CACHE = 3600;

	@Autowired
	private IfoDataRefDAO ifoDataRefDAO;
	
	@Autowired
	private VoteDAO voteDAO;
	
	@Autowired
	private IfoCompanyDAO ifoCompanyDAO;

	/**
	 * @param voteDAO
	 *            the voteDAO to set
	 */
	public void setVoteDAO(VoteDAO voteDAO) {
		this.voteDAO = voteDAO;
	}

	/**
	 * @return the ifoDataRefDAO
	 */
	public IfoDataRefDAO getIfoDataRefDAO() {
		return ifoDataRefDAO;
	}

	/**
	 * @param ifoDataRefDAO
	 *            the ifoDataRefDAO to set
	 */
	public void setIfoDataRefDAO(IfoDataRefDAO ifoDataRefDAO) {
		this.ifoDataRefDAO = ifoDataRefDAO;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * vn.com.vndirect.business.ICommonManager#getDataRefByGroupCode(vn.com.
	 * vndirect.domain.IfoDataRef)
	 */
	@SuppressWarnings("unchecked")
	@ReadThroughSingleCache(namespace = "CommonManager.getDataRefByGroupCode@", expiration = EXPIRATION_TIME_FOR_CACHE)
	public final List<IfoDataRef> getDataRefByGroupCode(@ParameterValueKeyProvider final IfoDataRef ifoDataRef)
			throws SystemException {
		final String location = "getDataRefByGroupCode(ifoDataRef: + " + ifoDataRef + ")";
		if (logger.isDebugEnabled()) {
			logger.debug(location + ":: BEGIN");
		}

		try {
			if (ifoDataRef == null || ifoDataRef.getGroupCode() == null) {
				throw new FunctionalException(location, "GroupCode is NULL...");
			}
			final List<IfoDataRef> listRef = ifoDataRefDAO.searchIfoDataRef(ifoDataRef);
			if (logger.isDebugEnabled()) {
				logger.debug(location + " END");
			}

			return listRef;
		} catch (Exception e) {
			logger.error(location, e);
			throw new SystemException(location, e);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see vn.com.vndirect.business.IVoteManager#getVoteQuestion()
	 */
	public SearchResult<Vote> getVoteQuestion() throws SystemException {
		return voteDAO.getVoteQuestion();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * vn.com.vndirect.business.IVoteManager#submitVote(vn.com.vndirect.domain
	 * .Vote)
	 */
	public void submitVote(Long voteId) throws SystemException {
		voteDAO.submitVote(voteId);
	}

	public IfoCompanyDAO getIfoCompanyDAO() {
		return ifoCompanyDAO;
	}

	public void setIfoCompanyDAO(IfoCompanyDAO ifoCompanyDAO) {
		this.ifoCompanyDAO = ifoCompanyDAO;
	}

	@SuppressWarnings("unchecked")
	@ReadThroughSingleCache(namespace = "CommonManager.getAllStockCode@")
	public SearchResult<String> getAllStockCode() throws SystemException {
		final String location = "getAllStockCode()";
		if (logger.isDebugEnabled()) {
			logger.debug(location + ":: BEGIN");
		}

		try {
			final SearchResult<String> result = ifoCompanyDAO.getAllStockCode();

			if (logger.isDebugEnabled()) {
				logger.debug(location + ":: END");
			}

			return result;
		} catch (Exception ex) {
			logger.error(location, ex);
			throw new SystemException(location, ex);
		}
	}
}
