/*--------------------------------------------------------------------------*
 | Modification Logs:
 | DATE        AUTHOR      DESCRIPTION
 | ------------------------------------------------
 | Mar 12, 2007   TungNQ     First generate code
 *--------------------------------------------------------------------------*/

package vn.com.vndirect.domain;

@SuppressWarnings("serial")
public class ServiceInfo extends BaseBean implements java.io.Serializable {
	private java.lang.String serviceCode;
	private java.lang.String serviceDesc;
	private java.lang.Long serviceInfoId;

	public ServiceInfo() {
	}

	public java.lang.String getServiceCode() {
		return serviceCode;
	}

	public void setServiceCode(java.lang.String serviceCode) {
		this.serviceCode = serviceCode;
	}

	public java.lang.String getServiceDesc() {
		return serviceDesc;
	}

	public void setServiceDesc(java.lang.String serviceDesc) {
		this.serviceDesc = serviceDesc;
	}

	public java.lang.Long getServiceInfoId() {
		return serviceInfoId;
	}

	public void setServiceInfoId(java.lang.Long serviceInfoId) {
		this.serviceInfoId = serviceInfoId;
	}

	public static ServiceInfo getServiceInfo(vn.com.vndirect.wsclient.onlineuser.ServiceInfo serviceInfo) {
		ServiceInfo result = new ServiceInfo();
		result.setServiceInfoId(serviceInfo.getServiceInfoId());
		result.setServiceCode(serviceInfo.getServiceCode());
		result.setServiceDesc(serviceInfo.getServiceDesc());
		return result;
	}

	public String toString() {
		return "ServiceInfo-[serviceInfoId:" + serviceInfoId + ", serviceCode:" + serviceCode + ", serviceDesc:" + serviceDesc + "]";
	}
}
