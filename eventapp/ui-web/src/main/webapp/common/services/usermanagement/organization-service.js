'use strict';

define(['app'], function (app) {

	app.factory('OrganizationServiceFactory', ['$http', function($http) {
	
	    
	    var OrganizationServiceFactory = {};
	
	    
	    OrganizationServiceFactory.createOrganization = function (organizationCreationRequest, baseUrl) {
	    	var url = baseUrl + '/OrganizationService/CreateOrganization';
	        return $http.post(url, organizationCreationRequest);
	    };
	    
	    OrganizationServiceFactory.updateOrganization = function (organizationUpdateRequest, baseUrl) {
	    	var url = baseUrl + '/OrganizationService/UpdateOrganization';
	        return $http.post(url, organizationUpdateRequest);
	    };
	    
	    OrganizationServiceFactory.deleteOrganization = function (objectDeletionRequest, baseUrl) {
	    	var url = baseUrl + '/OrganizationService/DeleteOrganization';
	        return $http.post(url, objectDeletionRequest);
	    };
	    
	    OrganizationServiceFactory.getActiveOrganization = function (baseUrl) {
	    	var url = baseUrl + '/OrganizationService/GetActiveOrganizations';
	        return $http.get(url);
	    };
	    
	    OrganizationServiceFactory.getOorganizationById = function (objectRetrievalRequest, baseUrl) {
	    	var url = baseUrl + '/OrganizationService/GetOrganizationById';
	    	return $http.post(url, objectRetrievalRequest);
	    };
	    
	    OrganizationServiceFactory.getOrganizationTypes = function (baseUrl) {
	    	var url = baseUrl + '/OrganizationService/GetOrganizationTypes';
	        return $http.get(url);
	    };
	
	    return OrganizationServiceFactory;
	}]);

});