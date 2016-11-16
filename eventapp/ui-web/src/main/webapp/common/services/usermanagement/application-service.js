'use strict';

define(['app'], function (app) {

	app.factory('ApplicationServiceFactory', ['$http', function($http) {
	
	    
	    var ApplicationServiceFactory = {};
	
	    
	    ApplicationServiceFactory.createApplication = function (applicationCreationRequest, baseUrl) {
	    	var url = baseUrl + '/ApplicationService/CreateApplication';
	        return $http.post(url, applicationCreationRequest);
	    };
	    
	    ApplicationServiceFactory.updateApplication = function (applicationUpdateRequest, baseUrl) {
	    	var url = baseUrl + '/ApplicationService/UpdateApplication';
	        return $http.post(url, applicationUpdateRequest);
	    };
	    
	    ApplicationServiceFactory.deleteApplication = function (objectDeletionRequest, baseUrl) {
	    	var url = baseUrl + '/ApplicationService/DeleteApplication';
	        return $http.post(url, objectDeletionRequest);
	    };
	    
	    ApplicationServiceFactory.getActiveApplications = function (baseUrl) {
	    	var url = baseUrl + '/ApplicationService/GetActiveApplications';
	        return $http.get(url);
	    };
	    
	    ApplicationServiceFactory.getApplicationById = function (objectRetrievalRequest, baseUrl) {
	    	var url = baseUrl + '/ApplicationService/GetApplicationById';
	    	return $http.post(url, objectRetrievalRequest);
	    };
	    
	    ApplicationServiceFactory.getApplicationTypes = function (baseUrl) {
	    	var url = baseUrl + '/ApplicationService/GetApplicationTypes';
	        return $http.get(url);
	    };
	    
	    
	    return ApplicationServiceFactory;
	}]);

});