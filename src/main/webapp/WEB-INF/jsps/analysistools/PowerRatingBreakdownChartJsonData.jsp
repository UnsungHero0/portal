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
      	"#396BA0",
        "#A2413B",
        "#8AA04B",
        "#725597",
        "#358FA9",
        "#E3873A"
      ],
      "values": [
        {
      		"value": <fmt:formatNumber pattern="###.##"><s:property value="model.firstLevel"/></fmt:formatNumber>,
      		"tip": "<s:text name="web.graft.tip.FirstLevel"></s:text> <fmt:formatNumber pattern="###,###.##"><s:property value="model.firstLevel"/></fmt:formatNumber>%"
      	},
      	{
      		"value": <fmt:formatNumber pattern="###.##"><s:property value="model.secondLevel"/></fmt:formatNumber>,
      		"tip": "<s:text name="web.graft.tip.SecondLevel"></s:text> <fmt:formatNumber pattern="###,###.##"><s:property value="model.secondLevel"/></fmt:formatNumber>%"
      	},
      	{
      		"value": <fmt:formatNumber pattern="###.##"><s:property value="model.thirdLevel"/></fmt:formatNumber>,
      		"tip": "<s:text name="web.graft.tip.ThirdLevel"></s:text> <fmt:formatNumber pattern="###,###.##"><s:property value="model.thirdLevel"/></fmt:formatNumber>%"
      	},
      	{
      		"value": <fmt:formatNumber pattern="###.##"><s:property value="model.fourthLevel"/></fmt:formatNumber>,
      		"tip": "<s:text name="web.graft.tip.FourthLevel"></s:text> <fmt:formatNumber pattern="###,###.##"><s:property value="model.fourthLevel"/></fmt:formatNumber>%"
      	},
      	{
      		"value": <fmt:formatNumber pattern="###.##"><s:property value="model.fifthLevel"/></fmt:formatNumber>,
      		"tip": "<s:text name="web.graft.tip.FifthLevel"></s:text> <fmt:formatNumber pattern="###,###.##"><s:property value="model.fifthLevel"/></fmt:formatNumber>%"
      	},
      	{
      		"value": <fmt:formatNumber pattern="###.##"><s:property value="model.sixthLevel"/></fmt:formatNumber>,
      		"tip": "<s:text name="web.graft.tip.SixthLevel"></s:text> <fmt:formatNumber pattern="###,###.##"><s:property value="model.sixthLevel"/></fmt:formatNumber>%"
      	}
      ]
    }
  ]
}

