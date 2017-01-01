'use strict';

define(['app','nvd3', 'ui_bootstrap'], function (app, nvd3, ui_bootstrap) {

  app.controller('DashboardController', ['$scope', '$rootScope', '$log', '$sce', 
                                         'NotificationServiceFactory',
                                         'UserRoleServiceFactory',
                                         'SecurityUserServiceFactory',
                                         'TeamServiceFactory',
                                         'PlayerServiceFactory',
                                         'GradeBeltServiceFactory',
                                         'EventServiceFactory',
                                         'CategoryServiceFactory',
                                         'AgeGroupServiceFactory',
                                         'Page', 
                                  function($scope,$rootScope, $log, $sce, 
                                		  NotificationServiceFactory,
                                		  UserRoleServiceFactory,
                                		  SecurityUserServiceFactory,
                                		  TeamServiceFactory,
                                		  PlayerServiceFactory,
                                		  GradeBeltServiceFactory,
                                		  EventServiceFactory,
                                		  CategoryServiceFactory,
                                		  AgeGroupServiceFactory,
                                		  Page) {
	  Page.setTitle("Dashboard");
	  
	  var baseUrl=$rootScope.baseUrl;
	  
	  $scope.initializeDashboardController = function() {
		  $scope.userRoleCount=0;
		  $scope.eventsCount=0;
		  $scope.categoryCount=0;
		  $scope.userCount=0;
		  $scope.ageGroupCount=0;
		  $scope.teamCount=0;
		  $scope.gradeBeltCount=0;
		  $scope.playerCount=0;
		  
		  loadCountUserRoles();
		  loadCountEvents();
		  loadCountCategory();
		  loadCountUser();
		  loadCountAgeGroup();
		  loadCountTeam();
		  loadCountGradeBelt();
		  loadCountPlayers();
      };
      
      function loadCountPlayers(){
    	  var response=PlayerServiceFactory.count(baseUrl);	
    	  response.success(function(data, status, headers, config) {	
      			$scope.playerCount=data.apiResponseResults;	
	        }).error(function(data, status, headers, config){
	            	NotificationServiceFactory.error(data.message);
	            	console.error('Error while getting events ' + data.message);
	        })
      }
      
      function loadCountGradeBelt(){
    	  var response=GradeBeltServiceFactory.count(baseUrl);	
    	  response.success(function(data, status, headers, config) {	
      			$scope.gradeBeltCount=data.apiResponseResults;	
	        }).error(function(data, status, headers, config){
	            	NotificationServiceFactory.error(data.message);
	            	console.error('Error while getting events ' + data.message);
	        })
      }
      
      function loadCountTeam(){
    	  var response=TeamServiceFactory.count(baseUrl);	
    	  response.success(function(data, status, headers, config) {	
      			$scope.teamCount=data.apiResponseResults;	
	        }).error(function(data, status, headers, config){
	            	NotificationServiceFactory.error(data.message);
	            	console.error('Error while getting events ' + data.message);
	        })
      }
      
      function loadCountAgeGroup(){
    	  var response=AgeGroupServiceFactory.count(baseUrl);	
    	  response.success(function(data, status, headers, config) {	
      			$scope.ageGroupCount=data.apiResponseResults;	
	        }).error(function(data, status, headers, config){
	            	NotificationServiceFactory.error(data.message);
	            	console.error('Error while getting events ' + data.message);
	        })
      }
      
      function loadCountUser(){
    	  var response=SecurityUserServiceFactory.count(baseUrl);	
    	  response.success(function(data, status, headers, config) {	
      			$scope.userCount=data.apiResponseResults;	
	        }).error(function(data, status, headers, config){
	            	NotificationServiceFactory.error(data.message);
	            	console.error('Error while getting events ' + data.message);
	        })
      }
      
      function loadCountCategory(){
    	  var response=CategoryServiceFactory.count(baseUrl);	
    	  response.success(function(data, status, headers, config) {	
      			$scope.categoryCount=data.apiResponseResults;	
	        }).error(function(data, status, headers, config){
	            	NotificationServiceFactory.error(data.message);
	            	console.error('Error while getting events ' + data.message);
	        })
      }
      
      
      function loadCountUserRoles(){
    	  var response=UserRoleServiceFactory.count(baseUrl);	
    	  response.success(function(data, status, headers, config) {	
      			$scope.userRoleCount=data.apiResponseResults;	
	        }).error(function(data, status, headers, config){
	            	NotificationServiceFactory.error(data.message);
	            	console.error('Error while getting events ' + data.message);
	        })
      }
      
      function loadCountEvents(){
    	  var response=EventServiceFactory.count(baseUrl);	
    	  response.success(function(data, status, headers, config) {	
      			$scope.eventsCount=data.apiResponseResults;	
	        }).error(function(data, status, headers, config){
	            	NotificationServiceFactory.error(data.message);
	            	console.error('Error while getting events ' + data.message);
	        })
      }
   
   
 }]);
  
  

});



