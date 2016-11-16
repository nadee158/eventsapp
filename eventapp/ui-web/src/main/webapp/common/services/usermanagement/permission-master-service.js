'use strict';

define(['app'], function (app) {
	
	app.factory('PermissionMasterServiceFactory', ['$http', function($http) {
		
		 var PermissionMasterServiceFactory = {};
		 
		 PermissionMasterServiceFactory.createPermissionMaster = function (permissionMasterRequest,baseUrl) {
			 console.log(permissionMasterRequest);
		    	var url = baseUrl + '/PermissionMaster/CreatePermissionMaster';
		    	console.log("Url: "+url);
		        return $http.post(url,permissionMasterRequest);
		    };
		     
		    PermissionMasterServiceFactory.getPermissionMasterById = function(objectRetrievalRequest, baseUrl) {
		    	var url = baseUrl + '/PermissionMaster/GetPermissionMasterById';
		    	return $http.post(url, objectRetrievalRequest);
		    };
	    
		    PermissionMasterServiceFactory.updatePermissionMaster = function(permissionMasterUpdateRequest, baseUrl){
		    	var url=baseUrl+'/PermissionMaster/UpdatePermissionMaster';
		    	return $http.post(url,permissionMasterUpdateRequest);
		    }
		    
		    PermissionMasterServiceFactory.deletePermissionMaster = function (objectDeletionRequest, baseUrl) {
		    	var url = baseUrl + '/PermissionMaster/DeletePermissionMaster';
		        return $http.post(url, objectDeletionRequest);
		    };
		    
		    PermissionMasterServiceFactory.getActivePermissionMasters=function(baseUrl){
		    	var url=baseUrl+'/PermissionMaster/GetActivePermissionMasters';
		    	return $http.get(url);
		    }
		    
		    return PermissionMasterServiceFactory;
	}]);
	
});