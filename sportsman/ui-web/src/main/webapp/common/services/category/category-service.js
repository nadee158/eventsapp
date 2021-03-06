'use strict';

define(['app'], function (app) {

	app.factory('CategoryServiceFactory', ['$http', function($http) {
	
	    
	    var CategoryServiceFactory = {};
	
	    
	    CategoryServiceFactory.createCategory = function (categoryCreationRequest, baseUrl) {
	    	var url = baseUrl + '/categoryservice/create';
	        return $http.post(url, categoryCreationRequest);
	    };
	    
	    CategoryServiceFactory.updateCategory = function (categoryUpdateRequest, baseUrl) {
	    	var url = baseUrl + '/categoryservice/update';
	        return $http.put(url, categoryUpdateRequest);
	    };
	    
	    CategoryServiceFactory.deleteCategory = function (objectDeletionRequest, baseUrl) {
	    	var url = baseUrl + '/categoryservice/delete';
	        return $http.post(url, objectDeletionRequest);
	    };
	    

	    CategoryServiceFactory.getActiveCategories = function (baseUrl) {
	    	var url = baseUrl + '/categoryservice/getactivelist';
	        return $http.get(url);
	    };
	    
	    CategoryServiceFactory.getCategoryById = function (objectRetrievalRequest, baseUrl) {
	    	var url = baseUrl + '/categoryservice/getbyid';
	    	return $http.post(url, objectRetrievalRequest);
	    };
	    
	    CategoryServiceFactory.getCategoryByEventId = function (objectRetrievalRequest, baseUrl) {
	    	var url = baseUrl + '/categoryservice/getbyeventid';
	    	return $http.post(url, objectRetrievalRequest);
	    };
	    
	    
	    CategoryServiceFactory.count = function (baseUrl) {
	    	var url = baseUrl + '/categoryservice/count';
	    	return $http.get(url);
	    };
	    
	    return CategoryServiceFactory;
	}]);

});