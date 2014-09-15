<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="http://www.webapp.com.vn/tags" prefix="web"%>
{
  "title": null,
  "bg_colour": "#FFFFFF",
  "elements": [
    {
      "type": "pie",
      "start-angle": 35,
      "animate": [
        {
          "type": "fade"
        },
        {
          "type": "bounce",
          "distance": 4
        }
      ],
      "gradient-fill": true,
      "colours": [
      	"#1C9E05",
        "#FF368D",
        "#1F8FA1",
        "#848484",
        "#CACFBE",
        "#DEF799"
      ],
      "values": [
        {
      		"value": <fmt:formatNumber pattern="###.##"><s:property value="model.ifoBreakdownViewId.stateOwnership"/></fmt:formatNumber>,
      		"tip": "<s:text name="web.label.MaijorHoldersAction.StateOwnership"></s:text> <fmt:formatNumber pattern="###,###.##"><s:property value="model.ifoBreakdownViewId.stateOwnership"/></fmt:formatNumber>%"
      	},
      	{
      		"value": <fmt:formatNumber pattern="###.##"><s:property value="model.ifoBreakdownViewId.foreignOwnership"/></fmt:formatNumber>,
      		"tip": "<s:text name="web.label.MaijorHoldersAction.ForeignOwnership"></s:text> <fmt:formatNumber pattern="###,###.##"><s:property value="model.ifoBreakdownViewId.foreignOwnership"/></fmt:formatNumber>%"
      	},
      	{
      		"value": <fmt:formatNumber pattern="###.##"><s:property value="model.ifoBreakdownViewId.other"/></fmt:formatNumber>,
      		"tip": "<s:text name="web.label.MaijorHoldersAction.Other"></s:text> <fmt:formatNumber pattern="###,###.##"><s:property value="model.ifoBreakdownViewId.other"/></fmt:formatNumber>%"
      	}
      ]
    }
  ]
}

