<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://www.webapp.com.vn/tags" prefix="web" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Frameset//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-frameset.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link rel="icon" href="<web:url value='/welcome.jpg'/>" type="image/x-icon" />
<link rel="shortcut icon" href="<web:url value='/welcome.jpg'/>" type="image/x-icon" />
<title>Welcome to VNDIRECT Securities</title>
</head>
<body>
<%
    String urlSnapshot = "/thong-ke-thi-truong-chung-khoan/tong-quan.shtml";
    String symbol = request.getParameter("s");
    if(symbol != null && symbol.length() > 0) {
        urlSnapshot = "/tong-quan/" + symbol+".shtml";
    }
    pageContext.setAttribute("urlSnapshot", urlSnapshot);
    pageContext.setAttribute("symbol", symbol);

%>
<script type="text/javascript">
    window.location.href = "<web:url value='${urlSnapshot}'/>";
</script>
</body>
</html>
