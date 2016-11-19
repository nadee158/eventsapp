'use strict';

define(['app'], function (app) {

	app.factory('CategoryServiceFactory', ['$http', function($http) {
	
	    
	    var CategoryServiceFactory = {};
	
	    
	    CategoryServiceFactory.createCategory = function (CategoryCreationRequest, baseUrl) {
	    	var url = baseUrl + '/CategoryService/CreateCategory';
	        return $http.post(url, CategoryCreationRequest);
	    };
	    
	    CategoryServiceFactory.updateCategory = function (CategoryUpdateRequest, baseUrl) {
	    	var url = baseUrl + '/CategoryService/UpdateCategory';
	        return $http.post(url, CategoryUpdateRequest);
	    };
	    
	    CategoryServiceFactory.deleteCategory = function (objectDeletionRequest, baseUrl) {
	    	var url = baseUrl + '/CategoryService/DeleteCategory';
	        return $http.post(url, objectDeletionRequest);
	    };
	    
	    CategoryServiceFactory.getActiveCategorys = function (baseUrl) {
	    	var url = baseUrl + '/CategoryService/GetActiveCategorys';
	        return $http.get(url);
	    };
	    
	    CategoryServiceFactory.getCategoryById = function (objectRetrievalRequest, baseUrl) {
	    	var url = baseUrl + '/CategoryService/GetCategoryById';
	    	return $http.post(url, objectRetrievalRequest);
	    };
	    
	    
	    return CategoryServiceFactory;
	}]);

});