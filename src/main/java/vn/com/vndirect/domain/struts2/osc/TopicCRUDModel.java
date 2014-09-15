/**
 * 
 */
package vn.com.vndirect.domain.struts2.osc;

import vn.com.vndirect.domain.BaseModel;
import vn.com.vndirect.wsclient.osc.WpTopic;
import vn.com.vndirect.wsclient.osc.WpTopicQuestion;
import vn.com.web.commons.exception.SystemException;
import vn.com.web.commons.utility.DateUtils;

/**
 * @author Duc Nguyen
 * 
 */
@SuppressWarnings("serial")
public class TopicCRUDModel extends BaseModel {

	private WpTopic topic;
	private WpTopicQuestion topicQuestion;
	private Long topicId;
	public static final String UPDATE = "UPDATE";
	public static final String CREATE = "CREATE";
	private String action;

	/**
	 * @return the topicId
	 */
	public Long getTopicId() {
		return topicId;
	}

	/**
	 * @param topicId
	 *            the topicId to set
	 */
	public void setTopicId(Long topicId) {
		this.topicId = topicId;
	}

	/**
	 * @return the topic
	 */
	public WpTopic getTopic() {
		return topic;
	}

	/**
	 * @param topic
	 *            the topic to set
	 */
	public void setTopic(WpTopic topic) {
		this.topic = topic;
	}

	/**
	 * @return the topicQuestion
	 */
	public WpTopicQuestion getTopicQuestion() {
		return topicQuestion;
	}

	/**
	 * @param topicQuestion
	 *            the topicQuestion to set
	 */
	public void setTopicQuestion(WpTopicQuestion topicQuestion) {
		this.topicQuestion = topicQuestion;
	}

	/**
	 * @return the effectiveDate
	 */
	public String getEffectiveDate() {
		try {
			return DateUtils.dateToString(getTopic().getEffectiveDate(), DateUtils.Formats.STR_DATE_TIME_FORMAT_DDMMYYYY);
		} catch (SystemException e) {
			return null;
		}
	}

	/**
	 * @return the action
	 */
	public String getAction() {
		return action;
	}

	/**
	 * @param action
	 *            the action to set
	 */
	public void setAction(String action) {
		this.action = action;
	}

}
