<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page contentType="text/html; charset=UTF-8" %>

<div class="clear"></div>
<div id="documentViewer" class="flexpaper_viewer"></div>
<div class="clear"></div>

<script type="text/javascript">
    // DOCs is setted from parent-call
    var startDocument = createStartDocument();
    //var startDocument = 'news-attachment/2013-10-03/6741e4fb-cbdb-4a86-b785-3e07cda0d2e0.pdf';
    function createStartDocument(){
    	var url = "";
    	
    	if(typeof(DOCs) != 'undefined' && DOCs.length > 0){
    		for(var i=0; i < DOCs.length ; i ++){
    			if(DOCs[i].indexOf(".pdf") != -1){
                    url = "news-attachment/" + DOCs[i];
                    break;
    			}
    		}
    	}
    	
    	return url;
    }

	function getDocumentUrl(document){
		var url = "{" + WEB_VNDIRECT_RESOURCE + "do.pdfOnline?doc={doc}&format={format}&page=[*,0],{numPages}}";
        url = url.replace("{doc}",document);
        
		$.ajax({
			type: 'POST',
            url: WEB_VNDIRECT_RESOURCE + "pdf/getTotalpage.shtml",
            data: "doc=" + createStartDocument(),
            async: false,
            dataType: 'html',
            success: function (data) {
            	url = url.replace("{numPages}", parseInt(data));
            }
        });

        return url;
	}
	function getDocQueryServiceUrl(document){
		return WEB_VNDIRECT_RESOURCE + "pdf/swfsize.shtml?doc={doc}&page={page}".replace("{doc}",document);
	}
	
	function append_log(msg){
		$('#txt_eventlog').val(msg+'\n'+$('#txt_eventlog').val());
	}

	String.format = function() {
		var s = arguments[0];
		for (var i = 0; i < arguments.length - 1; i++) {
			var reg = new RegExp("\\{" + i + "\\}", "gm");
			s = s.replace(reg, arguments[i + 1]);
		}
	
		return s;
	};
	
	alert(escape(getDocumentUrl(startDocument)));

	$('#documentViewer').FlexPaperViewer({
		 config : {
			 DOC : escape(getDocumentUrl(startDocument)),
			 Scale : 0.6, 
			 ZoomTransition : 'easeOut',
			 ZoomTime : 0.5, 
			 ZoomInterval : 0.1,
			 FitPageOnLoad : false,
			 FitWidthOnLoad : true,
			 FullScreenAsMaxWindow : false,
			 ProgressiveLoading : false,
			 MinZoomSize : 0.2,
			 MaxZoomSize : 5,
			 SearchMatchAll : false,
			 //SearchServiceUrl : searchServiceUrl,
             RenderingOrder : 'html',
			 ViewModeToolsVisible : true,
			 ZoomToolsVisible : true,
			 NavToolsVisible : true,
			 CursorToolsVisible : true,
			 SearchToolsVisible : true,
			 DocSizeQueryService : WEB_VNDIRECT_RESOURCE + "pdf/swfsize.shtml?doc=" + startDocument,
			 jsDirectory : '/portal/js/flexpaper/',
			 localeDirectory : '/portal/locale/',
			 JSONDataType : 'jsonp',
             key : FLEX_PAPER_KEY,
			 WMode : 'window',
			 localeChain: 'en_US'
		}
	});
</script>
