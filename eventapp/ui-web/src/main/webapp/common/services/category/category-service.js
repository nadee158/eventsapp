'use strict';

define(['app'], function (app) {

	app.factory('CategoryServiceFactory', ['$http', function($http) {
	
	    
	    var CategoryServiceFactory = {};
	
	    
	    CategoryServiceFactory.createCategory = function (CategoryCreationRequest, baseUrl) {
	    	var url = baseUrl + '/categoryservice/create';
	        return $http.post(url, CategoryCreationRequest);
	    };
	    
	    CategoryServiceFactory.updateCategory = function (CategoryUpdateRequest, baseUrl) {
	    	var url = baseUrl + '/categoryservice/update';
	        return $http.post(url, CategoryUpdateRequest);
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
	    
	    
	    return CategoryServiceFactory;
	}]);

});