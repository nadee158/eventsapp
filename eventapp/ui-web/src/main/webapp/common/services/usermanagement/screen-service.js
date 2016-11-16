'use strict';

define(['app'], function (app) {
	
	app.factory('ScreenServiceFactory', ['$http', function($http) {
		
		 var ScreenServiceFactory = {};
		 
		    ScreenServiceFactory.createScreen = function (screenCreationRequest,baseUrl) {
			 console.log(screenCreationRequest);
		    	var url = baseUrl + '/ScreenService/CreateScreen';
		    	console.log("Url: "+url);
		        return $http.post(url,screenCreationRequest);
		    };
		    
		    ScreenServiceFactory.updateScreen = function(applicationModuleUpdateRequest, baseUrl){
		    	var url=baseUrl+'/ScreenService/UpdateScreen';
		    	return $http.post(url,applicationModuleUpdateRequest);
		    }
		    
		    ScreenServiceFactory.deleteScreen = function (objectDeletionRequest, baseUrl) {
		    	var url = baseUrl + '/ScreenService/DeleteScreen';
		        return $http.post(url, objectDeletionRequest);
		    };
		    
		    ScreenServiceFactory.getActiveScreens = function (baseUrl) {
		    	var url = baseUrl + '/ScreenService/GetActiveScreens';
		        return $http.post(url);
		    };
		    
		    ScreenServiceFactory.getScreenById = function(objectRetrievalRequest, baseUrl) {
		    	var url = baseUrl + '/ScreenService/GetScreenById';
		    	return $http.post(url, objectRetrievalRequest);
		    };
		    
		    
		    ScreenServiceFactory.getActiveScreensByModuleServiceId=function(screenListRequest,baseUrl){
		    	var url= baseUrl + '/ScreenService/GetActiveScreensByModuleServiceId';
		    	return $http.post(url,screenListRequest);
		    }
		    
		    return ScreenServiceFactory;
	}]);
	
});