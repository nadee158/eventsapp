'use strict';

define(['app'], function (app) {
	
	app.factory('ApplicationModuleServiceFactory', ['$http', function($http) {
		
		 var ApplicationModuleServiceFactory = {};
		 
		   ApplicationModuleServiceFactory.createApplicationModule = function (applicationModuleCreationRequest,baseUrl) {
			 console.log(applicationModuleCreationRequest);
		    	var url = baseUrl + '/ApplicationModuleService/CreateApplicationModule';
		    	console.log("Url: "+url);
		        return $http.post(url,applicationModuleCreationRequest);
		    };
		    
		    
		    ApplicationModuleServiceFactory.getApplicationModules = function (baseUrl) {
		    	var url = baseUrl + '/ApplicationModuleService/GetApplicationModules';
		        return $http.post(url);
		    };
		    
		    ApplicationModuleServiceFactory.getApplicationModuleById = function(objectRetrievalRequest, baseUrl) {
		    	var url = baseUrl + '/ApplicationModuleService/GetApplicationModuleById';
		    	return $http.post(url, objectRetrievalRequest);
		    };
		    
		    ApplicationModuleServiceFactory.updateApplicationModule = function(applicationModuleUpdateRequest, baseUrl){
		    	var url=baseUrl+'/ApplicationModuleService/UpdateApplicationModule';
		    	return $http.post(url,applicationModuleUpdateRequest);
		    }
		    
		    ApplicationModuleServiceFactory.deleteApplicationModule = function (objectDeletionRequest, baseUrl) {
		    	var url = baseUrl + '/ApplicationModuleService/DeleteApplicationModule';
		        return $http.post(url, objectDeletionRequest);
		    };
		    
		    ApplicationModuleServiceFactory.getActiveApplicationModulesByApplicationId=function(applicationModuleListRequest,baseUrl){
		    	var url= baseUrl + '/ApplicationModuleService/GetActiveApplicationModulesByApplicationId';
		    	return $http.post(url,applicationModuleListRequest);
		    }
		    
		    return ApplicationModuleServiceFactory;
	}]);
	
});