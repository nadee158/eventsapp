'use strict';

define(['app'], function (app) {

	app.factory('EventServiceFactory', ['$http', function($http) {
	
	    
	    var EventServiceFactory = {};
	
	    
	    EventServiceFactory.createEvent = function (EventCreationRequest, baseUrl) {
	    	var url = baseUrl + '/eventsservice/create';
	        return $http.post(url, EventCreationRequest);
	    };
	    
	    EventServiceFactory.updateEvent = function (EventUpdateRequest, baseUrl) {
	    	var url = baseUrl + '/eventsservice/update';
	        return $http.put(url, EventUpdateRequest);
	    };
	    
	    EventServiceFactory.deleteEvent = function (objectDeletionRequest, baseUrl) {
	    	var url = baseUrl + '/eventsservice/delete';
	        return $http.post(url, objectDeletionRequest);
	    };
	    
	    EventServiceFactory.getActiveEvents = function (baseUrl) {
	    	var url = baseUrl + '/eventsservice/getactivelist';
	        return $http.get(url);
	    };
	    
	    EventServiceFactory.getEventById = function (objectRetrievalRequest, baseUrl) {
	    	var url = baseUrl + '/eventsservice/getbyid';
	    	return $http.post(url, objectRetrievalRequest);
	    };
	    
	    
	    return EventServiceFactory;
	}]);

});