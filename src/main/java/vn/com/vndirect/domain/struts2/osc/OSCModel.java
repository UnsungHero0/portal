package vn.com.vndirect.domain.struts2.osc;

import vn.com.vndirect.domain.BaseModel;
import vn.com.vndirect.domain.Topic;
import vn.com.vndirect.wsclient.osc.WpSubject;
import vn.com.vndirect.wsclient.osc.WpTopicQuestion;
import vn.com.web.commons.domain.db.SearchResult;

/**
 * 
 * @author NguyenDucQuang
 * @version 1.0
 * @created Feb 5, 2010 2:44:03 PM
 * 
 */
@SuppressWarnings("serial")
public class OSCModel extends BaseModel {
	private Long subjectId;
	private WpSubject subject;
	private Topic topic;
	private Long topicId;
	private WpTopicQuestion question;
	private SearchResult<WpTopicQuestion> questions;

	/**
	 * @return the subject
	 */
	public WpSubject getSubject() {
		return subject;
	}

	/**
	 * @param subject
	 *            the subject to set
	 */
	public void setSubject(WpSubject subject) {
		this.subject = subject;
	}

	/**
	 * @return the subjectId
	 */
	public Long getSubjectId() {
		return subjectId;
	}

	/**
	 * @param subjectId
	 *            the subjectId to set
	 */
	public void setSubjectId(Long subjectId) {
		this.subjectId = subjectId;
	}

	/**
	 * @return the topic
	 */
	public Topic getTopic() {
		return topic;
	}

	/**
	 * @param topic
	 *            the topic to set
	 */
	public void setTopic(Topic topic) {
		this.topic = topic;
	}

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
	 * @return the question
	 */
	public WpTopicQuestion getQuestion() {
		return question;
	}

	/**
	 * @param question
	 *            the question to set
	 */
	public void setQuestion(WpTopicQuestion question) {
		this.question = question;
	}

	/**
	 * @return the questions
	 */
	public SearchResult<WpTopicQuestion> getQuestions() {
		return questions;
	}

	/**
	 * @param questions
	 *            the questions to set
	 */
	public void setQuestions(SearchResult<WpTopicQuestion> questions) {
		this.questions = questions;
	}

}
