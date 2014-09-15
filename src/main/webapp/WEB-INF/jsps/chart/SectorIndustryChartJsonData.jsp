<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="http://www.webapp.com.vn/tags" prefix="web"%>
{
  "elements": [
    {
      "type": "line",
      "colour": "#DB1750",
      "width": 2,
      "values": [
<c:forEach items="${model.indexChartApi.datas}" var="item" varStatus="status">
	<c:if test="${status.count > 1}">,</c:if>{"x": <c:out value="${item.transTimeInSec}"/>,"y": <c:out value="${item.value}"/>}
</c:forEach>
      ],
      "dot-style": {
        "type": "dot",
        "colour": "#f00000",
        "tip": "#date:d M y#<br>Index: #val#"
      }
    }
  ],
  "title": {
    "text": "vndirect.com.vn - code: <c:out value="${model.idxCode}"/>"
  },
  "x_axis": {
    "min": <c:out value="${model.indexChartApi.xAxisMin}"/>,
    "max": <c:out value="${model.indexChartApi.xAxisMax}"/>,
    "steps": <c:out value="${model.indexChartApi.xAxisSteps}"/>,
    "labels": {
      "text": "#date:d/m/y#",
      "steps": <c:out value="${model.indexChartApi.xAxisSteps}"/>,
      "visible-steps": <c:out value="${model.indexChartApi.xAxisVisibleSteps}"/>,
      "rotate": 0
    }
  },
  "y_axis": {
    "min": <c:out value="${model.indexChartApi.yAxisMin}"/>,
    "max": <c:out value="${model.indexChartApi.yAxisMax}"/>,
    "steps": <c:out value="${model.indexChartApi.yAxisSteps}"/>
  }
}