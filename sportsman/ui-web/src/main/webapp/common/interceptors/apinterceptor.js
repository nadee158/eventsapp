'use strict';

define(['app'], function (app) {
  app.factory('ApiInterceptorFactory', ['$q', function($q) {
  
	    var service = this;

	    service.request = function(config) {
	        //console.log('request', config);
	        return config;
	    };

	    service.requestError = function(config) {
	        console.log('requestError', config);
	        return config;
	    };

	    service.response = function(response) {
	        //console.log('response', response);
	        return response;
	    };

	    service.responseError = function(response) {
	        console.log('responseError', response);
	        return response;
	    };

	    return service;
  }]);
  
});