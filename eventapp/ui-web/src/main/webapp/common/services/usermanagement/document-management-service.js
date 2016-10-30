'use strict';

define(['app'], function (app) {

	app.factory('DocumentManagementServiceFactory', ['$http', function($http) {
	
	    
	    var DocumentManagementServiceFactory = {};
	
	    DocumentManagementServiceFactory.uploadFileToUrl=function(file,baseUrl){
	    	 var fd = new FormData();
		        fd.append('file', file);
		        var url= baseUrl + '/Document/CreatDocument';
		        return $http.post(url,fd, {
		        	transformRequest: angular.identity,
		            headers: {'Content-Type': undefined}
		        });
	    }
	    
	    DocumentManagementServiceFactory.retrieveFile=function(imageReferenceKey, baseUrl){
	    	var restUrl = baseUrl+'/Document/RetrieveDocument';
	    	return $http.post(restUrl, imageReferenceKey);
	    }
	    
	    return DocumentManagementServiceFactory;
	}]);

});