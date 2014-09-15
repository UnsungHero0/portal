/**
 * 
 */
package vn.com.vndirect.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.jdbc.core.RowMapper;

import vn.com.vndirect.commons.utility.DBConstants;
import vn.com.vndirect.dao.BaseDAOImpl;
import vn.com.vndirect.domain.Vote;
import vn.com.web.commons.dao.jdbc.OracleDAOHelper;
import vn.com.web.commons.domain.db.SearchResult;
import vn.com.web.commons.exception.SystemException;

/**
 * @author NguyenDucQuang
 * @version 1.0
 * @created Mar 25, 2010 2:14:35 PM
 * 
 */
@SuppressWarnings("unchecked")
public class VoteDAO extends BaseDAOImpl {

	private static Logger log = Logger.getLogger(VoteDAO.class);
	private final String GET_VOTE_QUESTION = "SELECT * FROM VOTE WHERE (IS_DELETED IS NULL OR IS_DELETED != 1) ORDER BY VOTE_ID";
	private final String SUBMIT_VOTE = "UPDATE VOTE SET TOTAL = TOTAL + 1 WHERE VOTE_ID = :VOTE_ID AND (IS_DELETED IS NULL OR IS_DELETED != 1)";

	@SuppressWarnings("rawtypes")
    private final RowMapper objVoteRowMapper = new RowMapper() {
		public Object mapRow(ResultSet arg0, int arg1) throws SQLException {
			Vote vote = new Vote();
			vote.setVoteId(arg0.getLong(DBConstants.VOTE.VOTE_ID));
			vote.setVoteName(arg0.getString(DBConstants.VOTE.VOTE_NAME));
			vote.setTotal(arg0.getLong(DBConstants.VOTE.TOTAL));
			vote.setIsDeleted(arg0.getBoolean(DBConstants.VOTE.IS_DELETED));
			vote.setCreateBy(arg0.getString(DBConstants.VOTE.CREATED_BY));
			vote.setCreateDate(arg0.getDate(DBConstants.VOTE.CREATED_DATE));
			vote.setModifyBy(arg0.getString(DBConstants.VOTE.MODIFIED_BY));
			vote.setModifyDate(arg0.getDate(DBConstants.VOTE.MODIFIED_DATE));

			return vote;
		}
	};

	/**
	 * get vote question from Vote table
	 * 
	 * @return
	 * @throws SystemException
	 */
	public SearchResult<Vote> getVoteQuestion() throws SystemException {
		final String LOCATION = "getVoteQuestion()";
		if (log.isDebugEnabled()) {
			log.debug(LOCATION + ":: BEGIN");
		}

		SearchResult<Vote> listVote;
		try {
			listVote = OracleDAOHelper.query(this.getDataSource(), GET_VOTE_QUESTION, null, objVoteRowMapper);
		} catch (SystemException e) {
			log.error(LOCATION, e);
			throw new SystemException(LOCATION, e);
		}

		if (log.isDebugEnabled()) {
			log.debug(LOCATION + ":: END");
		}

		return listVote;
	}

	/**
	 * add submit of user to database
	 * 
	 * @param voteId
	 * @throws SystemException
	 */
	public void submitVote(Long voteId) throws SystemException {
		final String LOCATION = "getVoteQuestion(voteId : " + voteId + ")";
		if (log.isDebugEnabled()) {
			log.debug(LOCATION + ":: BEGIN");
		}
		if (voteId != null) {
			try {
				Map<String, Object> paramMap = new HashMap<String, Object>();
				paramMap.put(DBConstants.VOTE.VOTE_ID, voteId);
				OracleDAOHelper.update(this.getDataSource(), SUBMIT_VOTE, paramMap);
			} catch (SystemException e) {
				log.error(LOCATION, e);
				throw new SystemException(LOCATION, e);
			}
		}

		if (log.isDebugEnabled()) {
			log.debug(LOCATION + ":: END");
		}
	}
}
