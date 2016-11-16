'use strict';

define(['app'], function (app) {

	app.factory('ApplicationModuleServiceServiceFactory', ['$http', function($http) {
	
	    
	    var ApplicationModuleServiceServiceFactory = {};
	
	    
	    ApplicationModuleServiceServiceFactory.createApplicationModuleService = function (applicationModuleServiceCreationRequest, baseUrl) {
	    	var url = baseUrl + '/ApplicationModuleServiceService/CreateApplicationModuleService';
	        return $http.post( url,applicationModuleServiceCreationRequest);
	    };
	    
	    ApplicationModuleServiceServiceFactory.updateApplicationModuleService = function (applicationModuleServiceUpdateRequest, baseUrl) {
	    	var url = baseUrl + '/ApplicationModuleServiceService/UpdateApplicationModuleService';
	        return $http.post(url, applicationModuleServiceUpdateRequest);
	    };
	    
	    ApplicationModuleServiceServiceFactory.deleteApplicationModuleService = function (objectDeletionRequest, baseUrl) {
	    	var url = baseUrl + '/ApplicationModuleServiceService/DeleteApplicationModuleService';
	        return $http.post(url, objectDeletionRequest);
	    };
	    
	    ApplicationModuleServiceServiceFactory.getActiveApplicationModuleServices = function (baseUrl) {
	    	var url = baseUrl + '/ApplicationModuleServiceService/GetActiveApplicationModuleServices';
	        return $http.get(url);
	    };
	    
	    ApplicationModuleServiceServiceFactory.getActiveApplicationModuleServiceById = function (objectRetrievalRequest, baseUrl) {
	    	var url = baseUrl + '/ApplicationModuleServiceService/GetApplicationModuleServiceById';
	    	return $http.post(url, objectRetrievalRequest);
	    };
	    
	    ApplicationModuleServiceServiceFactory.getActiveApplicationModuleServicesByApplicationModuleId=function(applicationModuleServiceListRequest,baseUrl){
	    	var url= baseUrl + '/ApplicationModuleServiceService/GetActiveApplicationModuleServicesByModuleId';
	    	return $http.post(url,applicationModuleServiceListRequest);
	    }
	    
	    return ApplicationModuleServiceServiceFactory;
	}]);

});