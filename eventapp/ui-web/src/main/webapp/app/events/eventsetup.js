'use strict';

define(['app','nvd3', 'ui_bootstrap'], function (app, nvd3, ui_bootstrap) {

  app.controller('EventSetupController', ['$scope', '$rootScope', '$log', '$sce', 
                                          'NotificationServiceFactory',
                                          'SecurityServiceFactory','Page', 
                                          'EventServiceFactory',
                                          'CommonServiceFactory',
                                          'ModalDialogServiceFactory',
                                  function($scope, $rootScope, $log, $sce, 
                                		  NotificationServiceFactory,
                                		  SecurityServiceFactory,Page, 
                                		  EventServiceFactory,
                                		  CommonServiceFactory,
                                		  ModalDialogServiceFactory) {
	  Page.setTitle("Event Setup");
	  
	  
	  $scope.initializeEventSetupController = function() {
      	
      };
      
      $scope.myDate = new Date();

      $scope.minDate = new Date(
          $scope.myDate.getFullYear(),
          $scope.myDate.getMonth() - 2,
          $scope.myDate.getDate());

      $scope.maxDate = new Date(
          $scope.myDate.getFullYear(),
          $scope.myDate.getMonth() + 2,
          $scope.myDate.getDate());
	  
	  
   
   
 }]);
  
  

});



