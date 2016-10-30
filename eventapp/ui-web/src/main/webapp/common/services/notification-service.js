'use strict';

define(['app','toastr'], function (app,toastr) {

	  app.factory('NotificationServiceFactory', ['$log', function ($log) {
		 
		toastr.options.timeOut = 3000; // 2 second toast timeout
	    toastr.options.positionClass = 'toast-bottom-right';
		  
	    return {
	      info: function (msg) {
	    	  toastr.info(msg);
	          $log.info(msg);
	      },
	      warning: function (msg) {
	    	  toastr.warning(msg);
	          $log.warn(msg);
	      },
	      error: function (msg) {
	    	  toastr.error(msg);
	          $log.error(msg);
	      },
	      success: function (msg) {
	    	  toastr.success(msg);
	          $log.log(msg);
	      }
	    }
	  }]);

});






