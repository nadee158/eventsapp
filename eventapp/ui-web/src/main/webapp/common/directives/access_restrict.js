'use strict';

define(['app'], function (app) {

	app.directive("restrict", ['CommonStorageFactory','Constants',
         function(CommonStorageFactory, Constants) {
			return{
				restrict: 'A',
				prioriry: 100000,
				scope: false,
				link: function(){
					console.log('expression evaluation link');
				},
				compile:  function(element, attr, linker){
					var accessDenied = true;
					
					var userStorageKey= Constants.Keys.userobject; 
		  	    	var userDTOObject=CommonStorageFactory.retrieve(userStorageKey);
		  	    	
		  	    	console.log('expression evaluation compile');
		  	    	
		  	    	if(userDTOObject){
		  	    		if(userDTOObject.aulst){
		  	    			var attributes = attr.access.split(" ");
							for(var i in attributes){
								for(var j in userDTOObject.aulst){
									var userRole=userDTOObject.aulst[j];
									//console.log('useRole ' + userRole);
									//console.log('attributes[i] ' + attributes[i]);
									if(userRole == attributes[i]){
										accessDenied = false;
									}
								}
							}
		  	    		}
		  	    	}
	
					if(accessDenied){
						element.children().hide();
						element.hide();			
					}
	
				}
			}
         }
     ]);
	
	

});