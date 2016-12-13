'use strict';

define(['app'], function (app) {

	app.factory('ConsoleServiceFactory', ['$http', function($http) {
	
	    
	    var ConsoleServiceFactory = {};
	
	    
	    ConsoleServiceFactory.getEndPointDetails = function (constructedUrl) {
	        return $http.get(constructedUrl);
	    };
	    
	    
	    
	    
	    return ConsoleServiceFactory;
	}]);

});