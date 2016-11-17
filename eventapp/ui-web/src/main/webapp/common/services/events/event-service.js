'use strict';

define(['app'], function (app) {

	app.factory('EventServiceFactory', ['$http', function($http) {
	
	    
	    var EventServiceFactory = {};
	
	    
	    EventServiceFactory.createEvent = function (EventCreationRequest, baseUrl) {
	    	var url = baseUrl + '/EventService/CreateEvent';
	        return $http.post(url, EventCreationRequest);
	    };
	    
	    EventServiceFactory.updateEvent = function (EventUpdateRequest, baseUrl) {
	    	var url = baseUrl + '/EventService/UpdateEvent';
	        return $http.post(url, EventUpdateRequest);
	    };
	    
	    EventServiceFactory.deleteEvent = function (objectDeletionRequest, baseUrl) {
	    	var url = baseUrl + '/EventService/DeleteEvent';
	        return $http.post(url, objectDeletionRequest);
	    };
	    
	    EventServiceFactory.getActiveEvents = function (baseUrl) {
	    	var url = baseUrl + '/EventService/GetActiveEvents';
	        return $http.get(url);
	    };
	    
	    EventServiceFactory.getEventById = function (objectRetrievalRequest, baseUrl) {
	    	var url = baseUrl + '/EventService/GetEventById';
	    	return $http.post(url, objectRetrievalRequest);
	    };
	    
	    
	    return EventServiceFactory;
	}]);

});