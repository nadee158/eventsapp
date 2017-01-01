'use strict';

define(['app'], function (app) {
  
  app.controller('ListCategoriesController', ['$scope','$compile','$rootScope','$location','$filter', 'DTOptionsBuilder','DTColumnBuilder', 
                                               'CommonStorageFactory','NotificationServiceFactory','PubSub','Constants','Page',
                                               'ModalDialogServiceFactory','CategoryServiceFactory','CommonServiceFactory',
                                              function($scope,$compile,$rootScope,$location,$filter, DTOptionsBuilder,DTColumnBuilder,
                                                CommonStorageFactory,NotificationServiceFactory,PubSub,Constants,Page,
                                                ModalDialogServiceFactory,CategoryServiceFactory,CommonServiceFactory) {
	  
	  var $translate = $filter('translate');
		
	  Page.setTitle('List Categories');
		
      var baseUrl=$rootScope.baseUrl;
    
      var storageKey = Constants.Keys.authtoken;
	  var authToken = CommonStorageFactory.retrieve(storageKey);
	  
	  $scope.initializeListUsersPage = function() {
      };
      
      var vm=this;
      
      vm.dtInstance = {};

      vm.dtOptions = DTOptionsBuilder.newOptions()
       .withOption('ajax', {
           url: baseUrl + '/categoryservice/getall',
           type: 'POST',
           data: function ( data ) {
        	   // The returned object has 'email' as property, but the server entity has 'emailAddress'
               // We need to override what we ask to the server here otherwise search will not work
               data.columns[0].data = "event.eventName";
               data.columns[2].data = "ageGroup.fromAge";
               data.columns[3].data = "ageGroup.toAge";
               data.columns[5].data = "gradeOrBelt.gradeBeltName";

               // Any values you set on the data object will be passed along as parameters to the server
               //data.access_token = AuthenticationService.getAccessToken();
               return JSON.stringify(data);
           },
           headers: { 'Content-Type': 'application/json', 'X-AUTH-TOKEN': authToken }
       })       
       // or here
       .withDataProp('data')// This is the name of the value in the returned recordset which contains the actual data
       .withOption('processing', true)
       .withOption('serverSide', true)
       .withOption('saveState', true)
       .withOption('order', [7, 'asc'])
       .withPaginationType('full_numbers')
       .withOption('createdRow', function(row, data, dataIndex) {
              $compile(angular.element(row).contents())($scope);
       })
       .withOption('responsive', true).withOption('bAutoWidth', false).withOption('scrollX', '100%')
       .withDisplayLength(10);
  
      vm.dtColumns = [
          DTColumnBuilder.newColumn('eventName').withTitle('Event Name'),//0
          DTColumnBuilder.newColumn('categorySetupName').withTitle('Event Type'),//1
          DTColumnBuilder.newColumn('fromAge').withTitle('From Age'),//2
          DTColumnBuilder.newColumn('toAge').withTitle('To Age'),//3
          DTColumnBuilder.newColumn('gender').withTitle('Gender'),//4
          DTColumnBuilder.newColumn('gradeOrBeltName').withTitle('Grade/Belt'),//5
          DTColumnBuilder.newColumn('recordStatus').withTitle('Record Status'),//6
          DTColumnBuilder.newColumn('id').withTitle("Category Id").notVisible().notSortable().withOption('searchable', false),//7
          DTColumnBuilder.newColumn(null).withTitle($translate('common.label.text.ACTIONS')).withOption('searchable', false).notSortable().withOption('width','20%')//4
          .renderWith(function(data, type, full, meta) {
              return '<button class="btn btn-warning" ng-click="edit(' + data.id + ')">' +
                  '   <i class="fa fa-edit"></i>' +
                  '</button>&nbsp;' +
                  '<button class="btn btn-danger" ng-click="delete(' + data.id + ',' + data.version + ')">' +
                  '   <i class="fa fa-trash-o"></i>' +
                  '</button>';
          })
      ];
      
      
      $scope.edit = function(id) {
          $location.path('/editcategory').search({id: id});
      };
      
      
      $scope.delete = function(id,versionNumber) {
    	  
          console.log('Deleting' + id + ' - ' + versionNumber);
          
          var objectDeletionRequest={
    			  id:id,
    			  versionNumber:0
    	  };
          
          ModalDialogServiceFactory.confirmBox(
        		  $translate('common.notification.message.CONFIRM_DELETE_TITLE'), 
        		  $translate('common.notification.message.CONFIRM_DELETE_CONTENT'), 
        		  '', 
        		  $translate('common.button.text.DELETE'), 
        		  $translate('common.button.text.CANCEL'), 
        		  deleteRecord, 
        		  objectDeletionRequest, 
        		  null, 
        		  null
           );
          // Delete some data and call server to make changes...
          // Then reload the data so that DT is refreshed
      };
      
      function deleteRecord(objectDeletionRequest) {
    	  
    	  var response=CategoryServiceFactory.deleteCategory(objectDeletionRequest,baseUrl);		         
          
	        response.success(function(data, status, headers, config) {
    			
    		NotificationServiceFactory.info($translate('common.notification.message.NOTIFY_RECORD_DELETED'));
    		
    		redrawDatatable();
              
          }).error(function(data, status, headers, config){
        	  
          	NotificationServiceFactory.error($translate('common.notification.message.ERROR_WHILE_SAVING_RECORD'));
          	console.error($translate('common.notification.message.ERROR_WHILE_SAVING_RECORD') + " " + data.message);
          	
          	redrawDatatable();
          	
          })    
      }
      
     function redrawDatatable(){
    	 vm.dtInstance.reloadData();
     }
      
           
     function cancelDeleteRecord(recordId) {
          ModalDialogServiceFactory.alert(
        		  $translate('common.notification.message.NOTIFY_RECORD_NOT_DELETED'), 
        		  $translate('common.notification.message.NOTIFY_RECORD_NOT_DELETED_CONTENT'), 
        		  '', 
        		  $translate('common.button.text.OK'), 
        		  null, 
        		  null
          );
      }
      
        
    
  }]);
  

  
});