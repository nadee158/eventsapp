'use strict';

define(['app'], function (app) {
	app.controller('ViewConsoleController', ['$scope', '$rootScope','$location','$filter', 'ConsoleServiceFactory',
	                                            'CommonStorageFactory','NotificationServiceFactory', 'CommonServiceFactory',
	                                            'PubSub','Constants','Page','ModalDialogServiceFactory', '$sce',
	    function ($scope, $rootScope,$location,$filter, ConsoleServiceFactory,
	    										CommonStorageFactory,NotificationServiceFactory, CommonServiceFactory,
	    										PubSub,Constants,Page,ModalDialogServiceFactory,$sce) {
			
		    var $translate = $filter('translate');
		
			Page.setTitle($translate('View Application Configurations'));
			
	  
	        var baseUrl=$rootScope.baseUrl;
	        
	        function EndPoint(id,description,isSensitive){
	        	this.id=id;
	        	this.description=description;
	        	this.isSensitive=isSensitive;
	        }
	        
	        $scope.endpointList=[];
	        
	        $scope.initializeViewConsolePage = function() {
	        	$scope.serverUrl=baseUrl;
	        	$scope.endpointList=[
	        			new EndPoint('actuator','Provides a hypermedia-based “discovery page” for the other endpoints. Requires Spring HATEOAS to be on the classpath.',true),
	        			
	        			new EndPoint('autoconfig','Displays an auto-configuration report showing all auto-configuration candidates and the reason why they ‘were’ or ‘were not’ applied.',true),
	        			
	        			new EndPoint('beans','Displays a complete list of all the Spring beans in your application.',true),
	        			
	        			new EndPoint('configprops','Displays a collated list of all @ConfigurationProperties.',true),
	        			
	        			new EndPoint('dump','Performs a thread dump.',true),
	        			
	        			new EndPoint('env','Exposes properties from Spring’s ConfigurableEnvironment.',true),
	        			
	        			new EndPoint('flyway','Shows any Flyway database migrations that have been applied.',true),
	        			
	        			new EndPoint('health','Shows application health information (when the application is secure, a simple ‘status’ when accessed over an unauthenticated connection or full message details when authenticated).',true),
	        			
	        			new EndPoint('info','Displays arbitrary application info.',false),
	        			
	        			new EndPoint('liquibase','Shows any Liquibase database migrations that have been applied.',true),

	        			new EndPoint('metrics','Shows ‘metrics’ information for the current application.',true),
	        			
	        			new EndPoint('mappings','Displays a collated list of all @RequestMapping paths.',true),

	        			new EndPoint('shutdown','Allows the application to be gracefully shutdown (not enabled by default).',true),
	        			
	        			new EndPoint('trace','Displays trace information (by default the last 100 HTTP requests).',true),
	        			
	        			new EndPoint('docs','Displays documentation, including example requests and responses, for the Actuator’s endpoints. Requires spring-boot-actuator-docs to be on the classpath.',false),
	        			
	        			new EndPoint('heapdump','Returns a GZip compressed hprof heap dump file.',true),
	        			
	        			new EndPoint('jolokia','Exposes JMX beans over HTTP (when Jolokia is on the classpath).',true),
	        			
	        			new EndPoint('logfile','Returns the contents of the logfile (if logging.file or logging.path properties have been set). Supports the use of the HTTP Range header to retrieve part of the log file’s content.',true)

	        			
	        		 ];
	        	
	        	$scope.jsonOutput='';
	        };
	        
	        
	        var formattedString='';
	        
	         $scope.viewConsole = function() {
	        	$scope.jsonOutput='';
	        	$scope.formattedJosn='';
	        	formattedString='';
	        	var serverUrl=$scope.serverUrl;
	        	var endPoint=$scope.endPoint;
	        	var url=serverUrl + '/managecontext/' + endPoint;
	        	var response=ConsoleServiceFactory.getEndPointDetails(url);	        	 
	        	response.success(function(data, status, headers, config) {
	        		if(status==200){
	        			$scope.jsonOutput=data;	
	        			getFormattedJson(data);
	        			$scope.formattedJosn=$sce.trustAsHtml(formattedString);
	        		}else{
	        			CommonServiceFactory.handleResponseError(status);
	        		}
		        }).error(function(data, status, headers, config){
	        	    CommonServiceFactory.handleResponseError(data.status);
	            	console.error('Error while viewing endpoint ' + data.message);
		        })
	        }
	         
	        
	         
	         function getFormattedJson(data){
	        	 
	        	 if( typeof data === 'string' ) {
	        		 
	        		 formattedString=formattedString + "<strong>"+ data +  "</strong><br/><br/>";
	        		 
	        	 }else if(data instanceof Array) {
	        		 
	        		 for (var i=0; i < data.length; i++) {
	        			 
   	    			  var innerItem = data[i];
   	    			  
   	    			  if(typeof innerItem == 'object'){
   	    				  
   	    				  getFormattedJson(innerItem);
   	    				
   	    			  }else{
   	    				  
   	    				  formattedString=formattedString + "<strong>"+ innerItem +  "</strong><br/><br/>";
   	    			  }
   	    		  }
	        		 
	        	}else{
	        	    readAllProperties(data);
	        	}
	        	 
	        	
	         }
	         
	         
	         
	         function readAllProperties(data){
	        	 
	        	 var allPropertyNames = Object.keys(data);
	        	 
	        	 for (var j=0; j < allPropertyNames.length; j++) {
	        		 
	        	     var name = allPropertyNames[j];  
	        	    
	        	     
	        	     if(data[name]){
	        	    	 
	        	    	 var value = data[name];
	        	    	 
		        	     if(typeof value == 'object'){
		        	    	 
		        	    	 if(name){
		        	    		 formattedString=formattedString + "<br/><br/><span style='font-size:16px;color:#ff0000;'>"+ name +  " -> </span>";
			        	    	 getFormattedJson(value);
		        	    	 }
			        	    	 
		        	     }else{
		        	    	 if(name && value){
		        	    		 formattedString=formattedString + "<br/><strong>"+ name +  "</strong> :- "+ value ;
		        	    	 }else if(name){
		        	    		 formattedString=formattedString + "<br/><strong>"+ name +  "</strong>";
		        	    	 }else if(value){
		        	    		 formattedString=formattedString + "<br/>:- "+ value;
		        	    	 }
		        	    	 
		        	     }
	        	     }
	        	     
	        	 }
	         }
	        
	          
	  } 
	
	]);
});