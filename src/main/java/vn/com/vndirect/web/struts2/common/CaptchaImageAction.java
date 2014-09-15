package vn.com.vndirect.web.struts2.common;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.ServletRequestAware;

import vn.com.vndirect.domain.CaptchaImageModel;
import vn.com.web.commons.captcha.CaptchaEngineProxy;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ModelDriven;

public class CaptchaImageAction implements Action, ServletRequestAware, ModelDriven<CaptchaImageModel> {
	private static Logger logger = Logger.getLogger(CaptchaImageAction.class);

	private CaptchaEngineProxy captchaEngineProxy;
	private CaptchaImageModel model = new CaptchaImageModel();
	private InputStream inputStream = null;
	private HttpServletRequest request;

	/**
	 * @param captchaEngineProxy
	 *            the captchaEngineProxy to set
	 */
	public void setCaptchaEngineProxy(CaptchaEngineProxy captchaEngineProxy) {
		this.captchaEngineProxy = captchaEngineProxy;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.opensymphony.xwork2.ModelDriven#getModel()
	 */
	public CaptchaImageModel getModel() {
		return model;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.apache.struts2.interceptor.ServletRequestAware#setServletRequest(javax.servlet.http.HttpServletRequest)
	 */
	public void setServletRequest(HttpServletRequest req) {
		request = req;
	}

	/**
	 * 
	 * @return
	 * @throws Exception
	 */
	public InputStream getInputStream() throws Exception {
		return inputStream;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.opensymphony.xwork2.Action#execute()
	 */
	public String execute() throws Exception {
		final String LOCATION = "execute()";
		logger.debug(LOCATION);
		try {
			inputStream = captchaEngineProxy.generateImage(request, model.getFlavor(), model.getPid());
		} catch (Exception e) {
			inputStream = new ByteArrayInputStream(new byte[0]);
			logger.error(LOCATION, e);
		}
		return SUCCESS;
	}

}
