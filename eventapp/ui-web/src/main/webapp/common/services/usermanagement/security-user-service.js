'use strict';

define(['app'], function (app) {

	app.factory('SecurityUserServiceFactory', ['$http', function($http) {
	
	    
	    var SecurityUserServiceFactory = {};
	
	    
	    SecurityUserServiceFactory.createSecurityUser = function (applicationCreationRequest, baseUrl) {
	    	var url = baseUrl + '/SecurityUserService/CreateSecurityUser';
	        return $http.post(url, applicationCreationRequest);
	    };
	    
	    SecurityUserServiceFactory.updateSecurityUser = function (applicationUpdateRequest, baseUrl) {
	    	var url = baseUrl + '/SecurityUserService/UpdateSecurityUser';
	        return $http.post(url, applicationUpdateRequest);
	    };
	    
	    SecurityUserServiceFactory.deleteSecurityUser = function (objectDeletionRequest, baseUrl) {
	    	var url = baseUrl + '/SecurityUserService/DeleteSecurityUser';
	        return $http.post(url, objectDeletionRequest);
	    };
	    
	    SecurityUserServiceFactory.getActiveSecurityUsers = function (baseUrl) {
	    	var url = baseUrl + '/SecurityUserService/GetActiveSecurityUsers';
	        return $http.get(url);
	    };
	    
	    SecurityUserServiceFactory.getSecurityUserById = function (objectRetrievalRequest, baseUrl) {
	    	var url = baseUrl + '/SecurityUserService/GetSecurityUserById';
	    	return $http.post(url, objectRetrievalRequest);
	    };
	    
	    SecurityUserServiceFactory.getSecurityUsers = function (baseUrl) {
	    	var url = baseUrl + '/SecurityUserService/GetSecurityUsers';
	        return $http.get(url);
	    };
	    
	    SecurityUserServiceFactory.getPrefixes = function (baseUrl) {
	    	var url = baseUrl + '/SecurityUserService/GetPrefixes';
	        return $http.get(url);
	    };
	    
	    SecurityUserServiceFactory.updateUserRolesOfSecurityUser = function (securityUserPermissionUpdateRequest, baseUrl) {
	    	var url = baseUrl + '/SecurityUserService/UpdateUserRolesOfSecurityUser';
	        return $http.post(url, securityUserPermissionUpdateRequest);
	    };
	    
	    SecurityUserServiceFactory.updateGrantedPermissionsOfSecurityUser = function (securityUserPermissionUpdateRequest, baseUrl) {
	    	var url = baseUrl + '/SecurityUserService/UpdateGrantedPermissionsOfSecurityUser';
	        return $http.post(url, securityUserPermissionUpdateRequest);
	    };
	    
	    SecurityUserServiceFactory.updateDeniedPermissionsOfSecurityUser = function (securityUserPermissionUpdateRequest, baseUrl) {
	    	var url = baseUrl + '/SecurityUserService/UpdateDeniedPermissionsOfSecurityUser';
	        return $http.post(url, securityUserPermissionUpdateRequest);
	    };
	    
	    SecurityUserServiceFactory.updateSecurityUserProfile = function (securityUserProfileUpdateRequest, baseUrl) {
	    	var url = baseUrl + '/SecurityUserService/UpdateSecurityUserProfile';
	        return $http.post(url, securityUserProfileUpdateRequest);
	    };
	    
	    SecurityUserServiceFactory.getMaritalStatus = function (baseUrl) {
	    	var url = baseUrl + '/SecurityUserService/GetMaritalStatus';
	    	return $http.get(url);
	    };
	    
	
	    return SecurityUserServiceFactory;
	}]);

});