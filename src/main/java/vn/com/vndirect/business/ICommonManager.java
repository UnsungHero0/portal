/**
 * 
 */
package vn.com.vndirect.business;

import java.util.List;

import vn.com.vndirect.domain.IfoDataRef;
import vn.com.vndirect.domain.Vote;
import vn.com.web.commons.domain.db.SearchResult;
import vn.com.web.commons.exception.SystemException;

/**
 * @author Duc Nguyen
 * 
 */
public interface ICommonManager extends IBaseManager {

	/**
	 * @param ifoDataRef
	 * @return
	 * @throws FunctionalException
	 * @throws SystemException
	 */
	List<IfoDataRef> getDataRefByGroupCode(IfoDataRef ifoDataRef) throws SystemException;

	/**
	 * get all options of question to vote
	 * 
	 * @return
	 * @throws SystemException
	 */
	SearchResult<Vote> getVoteQuestion() throws SystemException;

	/**
	 * @return
	 * @throws SystemException
	 */
	SearchResult<String> getAllStockCode() throws SystemException;

	/**
	 * submit vote of user
	 * 
	 * @param vote
	 * @throws SystemException
	 */
	void submitVote(Long voteId) throws SystemException;
}
