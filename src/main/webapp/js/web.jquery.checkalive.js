/*************************************************************************************
* @author TungNQ
* The checkAlive script is used to keep user's connection
*************************************************************************************/

var _checkAliveDelaytime = 10*60*1000;
//var _checkAliveDelaytime = 1*1000;
var _clazzCheckAlive = new HomeClazzCheckAlive();

$(document).ready(function(){
	try {
		setTimeout ("_clazzCheckAlive.execute();", _checkAliveDelaytime); 
	} catch(e) {
		if(DEBUG) {
			$.log("Initial - clazzCheckAlive" + e);
		}
	}
});

/*************************************************************************************
* @author TungNQ
*
* @public: HomeClazzCheckAlive Class
*
*************************************************************************************/
function HomeClazzCheckAlive() {
	//this.delaytime =  10*60*1000;
	this.delaytime =  _checkAliveDelaytime;
}

/*************************************************************************************
* init getOption
**************************************************************************************/
HomeClazzCheckAlive.prototype.execute = function () {
	try {
		if(CHECK_ALIVE_URL != 'undefined') {
			var formFields = {};
			var options = {
					action : CHECK_ALIVE_URL,
					callbackExecuteFail : function (error) {	
						//alert("execute()::error..." + error);	
					},			
					callbackPostSubmit : function (responseText, statusText) {
						try {
							setTimeout ("_clazzCheckAlive.execute();", _checkAliveDelaytime);
						} catch (e) {
							if(DEBUG) {
								$.log("_clazzCheckAlive.execute()--timeout:: " + e);
							}
						}
					}
				};	
			//+++ loading data
			//alert("Loading...1");
			$.web_formAways.ajaxNav(formFields, options);
		}
	} catch (e){
		if(DEBUG) {
			$.log("HomeClazzCheckAlive.execute()--timeout:: " + e);
		}
		//alert("HomeClazzCheckAlive::execute() - " + e)
	}
};