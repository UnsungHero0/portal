package vn.com.vndirect.web.struts2.common;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.Result;
import com.opensymphony.xwork2.XWorkException;
import com.opensymphony.xwork2.config.entities.ActionConfig;

public class VNDUnknowHandler implements com.opensymphony.xwork2.UnknownHandler {

	public ActionConfig handleUnknownAction(String namespace, String actionName) throws XWorkException {
		// TODO Auto-generated method stub
		System.out.println("handleUnknownAction - namespace:" + namespace + ", actionName:" + actionName);
		return null;
	}

	public Object handleUnknownActionMethod(Object action, String methodName) throws NoSuchMethodException {
		// TODO Auto-generated method stub
		System.out.println("handleUnknownActionMethod - action:" + action + ", methodName:" + methodName);
		return null;
	}

	public Result handleUnknownResult(ActionContext actionContext, String actionName, ActionConfig actionConfig, String resultCode)
	        throws XWorkException {
		// TODO Auto-generated method stub
		System.out.println("handleUnknownResult - actionContext:" + actionContext + ", actionName:" + actionName
		        + ", resultCode:" + resultCode);
		return null;
	}

}
