/**
 * 
 */
package vn.com.vndirect.domain.struts2.osc;

import java.util.List;

import vn.com.vndirect.domain.BaseModel;
import vn.com.vndirect.wsclient.osc.WpTopic;
import vn.com.vndirect.wsclient.osc.WpTopicQuestion;

/**
 * @author Duc Nguyen
 * 
 */
@SuppressWarnings("serial")
public class TopicCRUDAJAXModel extends BaseModel {
	private WpTopic topic;
	private List<WpTopic> topics;
	private List<WpTopicQuestion> questions;
	private WpTopicQuestion topicQuestion;
	/*
	 * id in the format 1,2,3
	 */
	private String questionIds;
	private String effectiveDate;
	private String fromDate;
	private String toDate;

	/*
	 * id in the format 1,2,3
	 */
	private String topicIds;

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
	 * @return the topics
	 */
	public List<WpTopic> getTopics() {
		return topics;
	}

	/**
	 * @param topics
	 *            the topics to set
	 */
	public void setTopics(List<WpTopic> topics) {
		this.topics = topics;
	}

	/**
	 * @return the questions
	 */
	public List<WpTopicQuestion> getQuestions() {
		return questions;
	}

	/**
	 * @param questions
	 *            the questions to set
	 */
	public void setQuestions(List<WpTopicQuestion> questions) {
		this.questions = questions;
	}

	/**
	 * @return the questionIds
	 */
	public String getQuestionIds() {
		return questionIds;
	}

	/**
	 * @param questionIds
	 *            the questionIds to set
	 */
	public void setQuestionIds(String questionIds) {
		this.questionIds = questionIds;
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
		return effectiveDate;
	}

	/**
	 * @param effectiveDate
	 *            the effectiveDate to set
	 */
	public void setEffectiveDate(String effectiveDate) {
		this.effectiveDate = effectiveDate;
	}

	/**
	 * @return the topicIds
	 */
	public String getTopicIds() {
		return topicIds;
	}

	/**
	 * @param topicIds
	 *            the topicIds to set
	 */
	public void setTopicIds(String topicIds) {
		this.topicIds = topicIds;
	}

	/**
	 * @return the fromDate
	 */
	public String getFromDate() {
		return fromDate;
	}

	/**
	 * @param fromDate
	 *            the fromDate to set
	 */
	public void setFromDate(String fromDate) {
		this.fromDate = fromDate;
	}

	/**
	 * @return the toDate
	 */
	public String getToDate() {
		return toDate;
	}

	/**
	 * @param toDate
	 *            the toDate to set
	 */
	public void setToDate(String toDate) {
		this.toDate = toDate;
	}

}
