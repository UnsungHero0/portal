/**
 * 
 */
package vn.com.vndirect.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.jdbc.core.RowMapper;

import vn.com.vndirect.commons.utility.DBConstants;
import vn.com.vndirect.dao.BaseDAOImpl;
import vn.com.vndirect.domain.Topic;
import vn.com.vndirect.wsclient.osc.WpTopicQuestion;
import vn.com.web.commons.dao.jdbc.OracleDAOHelper;
import vn.com.web.commons.domain.db.PagingInfo;
import vn.com.web.commons.domain.db.SearchResult;

/**
 * @author Huy
 * 
 */
public class WpTopicDAO extends BaseDAOImpl {

	private Log logger = LogFactory.getLog(getClass());

	/**
	 * Get a topic by its id
	 * 
	 * @param id
	 * @param pagingInfo
	 * @return
	 * @throws Exception
	 */
	public Topic getTopicById(Long id) throws Exception {

		RowMapper<Topic> mapper = new RowMapper<Topic>() {
			public Topic mapRow(ResultSet rs, int arg1) throws SQLException {
				Topic obj = new Topic();
				obj.setTopicId(rs.getLong("TOPIC_ID"));
				obj.setTopicName(rs.getString("TOPIC_NAME"));
				obj.setEffDate(rs.getDate("EFFECTIVE_DATE"));
				obj.setCreDate(rs.getDate("CREATED_DATE"));
				obj.setModiDate(rs.getDate("MODIFIED_DATE"));
				obj.setTopicContent(rs.getString("TOPIC_CONTENT"));
				return obj;
			}
		};

		// build the sql
		StringBuffer buffer = new StringBuffer();

		buffer.append("SELECT 							");
		buffer.append("  TOPIC_ID, 						");
		buffer.append("  TOPIC_NAME,					");
		buffer.append("  EFFECTIVE_DATE,				");
		buffer.append("  CREATED_DATE,					");
		buffer.append("  MODIFIED_DATE,					");
		buffer.append("  TOPIC_CONTENT 					");
		buffer.append("FROM 							");
		buffer.append("  WP_TOPIC						");
		buffer.append("WHERE 							");
		buffer.append("  TOPIC_STATUS = 'PUB' AND 		");
		buffer.append("  ROWNUM = 1						");
		buffer.append("  AND (IS_DELETED IS NULL OR IS_DELETED = 0)	");
		buffer.append("ORDER BY 						");
		buffer.append("  CREATED_DATE DESC				");

		Topic topic1 = (Topic) OracleDAOHelper.querySingle(getDataSource(), buffer.toString(), null, mapper);

		buffer = new StringBuffer();
		buffer.append("SELECT 							");
		buffer.append("  TOPIC_ID, 						");
		buffer.append("  TOPIC_NAME,					");
		buffer.append("  EFFECTIVE_DATE,				");
		buffer.append("  CREATED_DATE,					");
		buffer.append("  MODIFIED_DATE,					");
		buffer.append("  TOPIC_CONTENT 					");
		buffer.append("FROM 							");
		buffer.append("  WP_TOPIC						");
		buffer.append("WHERE 							");
		buffer.append("  TOPIC_STATUS = 'PUB' AND 		");
		buffer.append("  ROWNUM = 1						");
		buffer.append("  AND MODIFIED_DATE IS NOT NULL	");
		buffer.append("  AND (IS_DELETED IS NULL OR IS_DELETED = 0)	");
		buffer.append("ORDER BY 						");
		buffer.append("  MODIFIED_DATE DESC				");

		Topic topic2 = (Topic) OracleDAOHelper.querySingle(getDataSource(), buffer.toString(), null, mapper);

		if (topic2 == null) {
			return topic1;
		} else {
			if (topic2.getCreDate().before(topic1.getCreDate())) {
				return topic2;
			} else {
				return topic1;
			}
		}
	}

	/**
	 * Save a question into DB
	 * 
	 * @param question
	 * @throws Exception
	 */
	public void saveQuestion(WpTopicQuestion question) throws Exception {
		if (logger.isDebugEnabled()) {
			logger.debug("saveQuestion::begin");
		}
		// build the sql
		StringBuffer buffer = new StringBuffer();
		buffer.append("INSERT INTO ");
		buffer.append("  WP_TOPIC_QUESTION(TOPIC_QUESTION_ID, QUESTION_CONTENT, CREATED_BY, TOPIC_ID, CREATED_DATE) ");
		buffer.append("VALUES ");
		buffer.append("  (:TOPIC_QUESTION_ID, :QUESTION_CONTENT, :CREATED_BY, :TOPIC_ID, :CREATED_DATE) ");

		// add parameters
		Map<String, Object> paramMap = new HashMap<String, Object>();
		long questionId = OracleDAOHelper.nextval(this.getDataSource(), DBConstants.SEQ.WP_TOPIC_QUESTION_SEQ);
		paramMap.put("TOPIC_QUESTION_ID", questionId);
		paramMap.put("QUESTION_CONTENT", question.getQuestionContent());
		paramMap.put("CREATED_BY", question.getCreatedBy());
		paramMap.put("TOPIC_ID", question.getTopicId());
		paramMap.put("CREATED_DATE", new Date());

		int n = OracleDAOHelper.update(getDataSource(), buffer.toString(), paramMap);
		if (logger.isDebugEnabled()) {
			logger.debug(n + " rows inserted");
			logger.debug("saveQuestion::end");
		}
	}

	/**
	 * Get list of questions by topic_id
	 * 
	 * @return list of questions
	 * @throws Exception
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public SearchResult<WpTopicQuestion> getQuestionByTopicId(Long id, PagingInfo pagingInfo) throws Exception {
		// build the sql
		StringBuffer buffer = new StringBuffer();

		buffer.append("  SELECT ");
		buffer.append("    QUESTION_CONTENT, ");
		buffer.append("    ANSWER_CONTENT, ");
		buffer.append("    CREATED_BY ");
		buffer.append("  FROM ");
		buffer.append("    WP_TOPIC_QUESTION");
		buffer.append("  WHERE TOPIC_ID = :TOPIC_ID");
		buffer.append("  AND (IS_DELETED IS NULL OR IS_DELETED = 0)");
		buffer.append("  AND (IS_ANSWERED IS NOT NULL OR IS_ANSWERED = 1)");
		buffer.append("  ORDER BY CREATED_DATE");

		// add parameters
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("TOPIC_ID", id);

		// get the result from data source
		SearchResult<WpTopicQuestion> result = OracleDAOHelper.query(getDataSource(), buffer.toString(), paramMap,
		        new RowMapper<WpTopicQuestion>() {
			        public WpTopicQuestion mapRow(ResultSet rs, int arg1) throws SQLException {
				        WpTopicQuestion obj = new WpTopicQuestion();
				        obj.setQuestionContent(rs.getString("QUESTION_CONTENT"));
				        obj.setAnswerContent(rs.getString("ANSWER_CONTENT"));
				        obj.setCreatedBy(rs.getString("CREATED_BY"));
				        return obj;
			        }
		        }, pagingInfo);

		return result;
	}
}
