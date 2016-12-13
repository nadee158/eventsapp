'use strict';

define(['app'], function (app) {

	  app.factory('ModalDialogServiceFactory', ['$log','$mdDialog', function ($log, $mdDialog) {
		 
	    return {
	      confirmBox: function (title, content, areaText, confirmText, cancelText, successCb, successCbParam, cancelCb, cancelCbParam) {
	    	var confirm = $mdDialog.confirm()
              .title(title)
              .textContent(content)
              .ariaLabel(areaText)
              .ok(confirmText)
              .cancel(cancelText);
    
		    $mdDialog.show(confirm).then(function () {
		        successCb(successCbParam);
		        confirm = undefined;
		    }, function () {
		    	if(cancelCb){
		    		cancelCb(cancelCbParam);
		    	}
		  	    
		  	    confirm = undefined;
		    });
	      },
	      alert: function (title, content, areaText, confirmText, callBack, callBackParam) {
	    	var alert = $mdDialog.alert()
	    	  .clickOutsideToClose(true)
              .title(title)
              .textContent(content)
              .ariaLabel(areaText)
              .ok(confirmText);
    
	    	 $mdDialog.show(alert).finally(function() {
	        	  callBack(callBackParam);
	              alert = undefined;
	         });
	      },
	      customDialog: function (ev,dialogController,templateUrl,useFullScreen, successCb, successCbParam, cancelCb, cancelCbParam) {
		    	var custom = {
		  		      controller: dialogController,
				      templateUrl: templateUrl,
				      targetEvent: ev,
				      clickOutsideToClose:true,
				      fullscreen: useFullScreen
				 }  
		    	  
		    	$mdDialog.show(custom)
			    .then(function() {
			    	if(successCb){
			    		successCb(successCbParam);
			    	}
			    	custom=undefined;
			    }, function() {
			    	if(cancelCb){
			    		cancelCb(cancelCbParam);
			    	}
			    	custom=undefined;
			    });
		   }
	    }
	  }]);
	  
	  

});