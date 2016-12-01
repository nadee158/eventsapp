'use strict';

define(['app', 'ui_bootstrap'], function (app, ui_bootstrap) {

  app.controller('PlayerRegistrationController', ['$scope', '$rootScope', '$log', '$sce', 
                                          'NotificationServiceFactory',
                                          'SecurityServiceFactory','Page', 
                                          'PlayerServiceFactory',
                                          'CategoryServiceFactory',
                                          'EventServiceFactory',
                                          'CommonServiceFactory',
                                          'ModalDialogServiceFactory',
                                  function($scope, $rootScope, $log, $sce, 
                                		  NotificationServiceFactory,
                                		  SecurityServiceFactory,Page, 
                                		  PlayerServiceFactory,
                                		  CategoryServiceFactory,
                                		  EventServiceFactory,
                                		  CommonServiceFactory,
                                		  ModalDialogServiceFactory) {
	  Page.setTitle("Player Registration");
	  
	  
	  $scope.initializeEventSetupController = function() {
      	
      };
      
      
	  
	  
   
   
 }]);
  
  

});



