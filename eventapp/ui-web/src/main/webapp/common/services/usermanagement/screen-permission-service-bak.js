'use strict';

define(['app'], function (app) {
	
	app.factory('ScreenPermissionServiceFactory', ['$http', function($http) {
		
		 var ScreenPermissionServiceFactory = {};
		 	
		 	ScreenPermissionServiceFactory.createScreenPermission=function(permissionScreenCreationRequest,baseUrl){
		 		var url=baseUrl+'/ScreenPermissionService/CreateScreenPermission';
		 		return $http.post(url,permissionScreenCreationRequest);
		 	};
		    
		    ScreenPermissionServiceFactory.getActiveScreenPermissionsByApplicationId = function(screenPermissionListRequest, baseUrl) {
		    	var url = baseUrl + '/ScreenPermissionService/GetActiveScreenPermissionsByApplicationId';
		    	return $http.post(url, screenPermissionListRequest);
		    };
		    
		    ScreenPermissionServiceFactory.getActiveScreenPermissionsByApplicationModuleId = function(screenPermissionListRequest, baseUrl) {
		    	var url = baseUrl + '/ScreenPermissionService/GetActiveScreenPermissionsByApplicationModuleId';
		    	return $http.post(url, screenPermissionListRequest);
		    };
		    
		    ScreenPermissionServiceFactory.getActiveScreenPermissionsByApplicationModuleServiceId = function(screenPermissionListRequest, baseUrl) {
		    	var url = baseUrl + '/ScreenPermissionService/GetActiveScreenPermissionsByApplicationModuleServiceId';
		    	return $http.post(url, screenPermissionListRequest);
		    };
		    
		    ScreenPermissionServiceFactory.getActiveScreenPermissionsByScreenId = function(screenPermissionListRequest, baseUrl) {
		    	var url = baseUrl + '/ScreenPermissionService/GetActiveScreenPermissionsByScreenId';
		    	return $http.post(url, screenPermissionListRequest);
		    };
		    
		    ScreenPermissionServiceFactory.deleteScreenPermission = function(objectDeletionRequest, baseUrl) {
		    	var url = baseUrl + '/ScreenPermissionService/DeleteScreenPermission';
		    	return $http.post(url, objectDeletionRequest);
		    };
		    
		    ScreenPermissionServiceFactory.updateScreenPermission=function(screenPermissionUpdateRequest,baseUrl){
		    	var url=baseUrl+'/ScreenPermissionService/UpdateScreenPermission';
		    	return $http.post(url,screenPermissionUpdateRequest);
		    }
		    
		    ScreenPermissionServiceFactory.getScreenPermissionsBySecurityUserId=function(screenPermissionListRequest,baseUrl){
		    	var url=baseUrl+'/ScreenPermissionService/GetScreenPermissionsBySecurityUserId';
		    	return $http.post(url,screenPermissionListRequest);
		    }
		    
		    return ScreenPermissionServiceFactory;
	}]);
	
});