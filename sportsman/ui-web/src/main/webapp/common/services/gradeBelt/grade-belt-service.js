'use strict';

define(['app'], function (app) {

	app.factory('GradeBeltServiceFactory', ['$http', function($http) {
	
	    
	    var GradeBeltServiceFactory = {};
	
	    
	    GradeBeltServiceFactory.createGradeBelt = function (GradeBeltCreationRequest, baseUrl) {
	    	var url = baseUrl + '/gradebeltservice/create';
	        return $http.post(url, GradeBeltCreationRequest);
	    };
	    
	    GradeBeltServiceFactory.updateGradeBelt = function (GradeBeltUpdateRequest, baseUrl) {
	    	var url = baseUrl + '/gradebeltservice/update';
	        return $http.put(url, GradeBeltUpdateRequest);
	    };
	    
	    GradeBeltServiceFactory.deleteGradeBelt = function (objectDeletionRequest, baseUrl) {
	    	var url = baseUrl + '/gradebeltservice/delete';
	        return $http.post(url, objectDeletionRequest);
	    };
	    
	    GradeBeltServiceFactory.getActiveGradeBelts = function (baseUrl) {
	    	var url = baseUrl + '/gradebeltservice/getactivelist';
	        return $http.get(url);
	    };
	    
	    GradeBeltServiceFactory.getGradeBeltById = function (objectRetrievalRequest, baseUrl) {
	    	var url = baseUrl + '/gradebeltservice/getbyid';
	    	return $http.post(url, objectRetrievalRequest);
	    };
	    
	    GradeBeltServiceFactory.count = function (baseUrl) {
	    	var url = baseUrl + '/gradebeltservice/count';
	    	return $http.get(url);
	    };
	    
	    return GradeBeltServiceFactory;
	}]);

});