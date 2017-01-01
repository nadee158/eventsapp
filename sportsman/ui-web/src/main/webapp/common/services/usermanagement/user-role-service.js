'use strict';

define(['app'], function (app) {

	app.factory('UserRoleServiceFactory', ['$http', function($http) {
	
	    
	    var UserRoleServiceFactory = {};
	
	    
	    UserRoleServiceFactory.createUserRole = function (userRoleCreationRequest, baseUrl) {
	    	var url = baseUrl + '/UserRoleService/CreateUserRole';
	        return $http.post(url, userRoleCreationRequest);
	    };
	    
	    UserRoleServiceFactory.updateUserRole = function (userRoleUpdateRequest, baseUrl) {
	    	var url = baseUrl + '/UserRoleService/UpdateUserRole';
	        return $http.post(url, userRoleUpdateRequest);
	    };
	    
	    UserRoleServiceFactory.deleteUserRole = function (objectDeletionRequest, baseUrl) {
	    	var url = baseUrl + '/UserRoleService/DeleteUserRole';
	        return $http.post(url, objectDeletionRequest);
	    };
	    
	    UserRoleServiceFactory.getActiveUserRoles = function (baseUrl) {
	    	var url = baseUrl + '/UserRoleService/GetActiveUserRoles';
	        return $http.get(url);
	    };
	    
	    UserRoleServiceFactory.getUserRoleById = function (objectRetrievalRequest, baseUrl) {
	    	var url = baseUrl + '/UserRoleService/GetUserRoleById';
	    	return $http.post(url, objectRetrievalRequest);
	    };
	    
	    UserRoleServiceFactory.getUserRolesBySecurityUserId = function (userRoleListRetrievalRequest, baseUrl) {
	    	var url = baseUrl + '/UserRoleService/GetUserRolesBySecurityUserId';
	    	return $http.post(url, userRoleListRetrievalRequest);
	    };
	    
	    UserRoleServiceFactory.count = function (baseUrl) {
	    	var url = baseUrl + '/UserRoleService/count';
	    	return $http.get(url);
	    };
	    
	    return UserRoleServiceFactory;
	}]);

});