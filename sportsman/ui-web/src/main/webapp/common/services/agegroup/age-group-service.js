'use strict';

define(['app'], function (app) {

	app.factory('AgeGroupServiceFactory', ['$http', function($http) {
	
	    
	    var AgeGroupServiceFactory = {};
	
	    
	    AgeGroupServiceFactory.createAgeGroup = function (AgeGroupCreationRequest, baseUrl) {
	    	var url = baseUrl + '/agegroupservice/create';
	        return $http.post(url, AgeGroupCreationRequest);
	    };
	    
	    AgeGroupServiceFactory.updateAgeGroup = function (AgeGroupUpdateRequest, baseUrl) {
	    	var url = baseUrl + '/agegroupservice/update';
	        return $http.put(url, AgeGroupUpdateRequest);
	    };
	    
	    AgeGroupServiceFactory.deleteAgeGroup = function (objectDeletionRequest, baseUrl) {
	    	var url = baseUrl + '/agegroupservice/delete';
	        return $http.post(url, objectDeletionRequest);
	    };
	    
	    AgeGroupServiceFactory.getActiveAgeGroups = function (baseUrl) {
	    	var url = baseUrl + '/agegroupservice/getactivelist';
	        return $http.get(url);
	    };
	    
	    AgeGroupServiceFactory.getAgeGroupById = function (objectRetrievalRequest, baseUrl) {
	    	var url = baseUrl + '/agegroupservice/getbyid';
	    	return $http.post(url, objectRetrievalRequest);
	    };
	    
	    
	    return AgeGroupServiceFactory;
	}]);

});