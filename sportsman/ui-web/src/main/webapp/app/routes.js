define([], function()
{
    return {
        defaultRoutePath: '/ui-web/',
        routes: {
            '/home': {
				templateUrl: 'app/dashboard/dashboard.html',
				dependencies: [
				    '../app/dashboard/dashboard',
				],
				access: {
				    isFree: false
				}
            },     
            '/login': {
				  templateUrl: 'app/security/signin.html',
				  dependencies: [
				    '../app/security/signin',
				  ],
				  access: {
				    isFree: true
				  }
            },
            '/resetpassword': {
				  templateUrl: 'app/security/resetPassword.html',
				  dependencies: [
				    '../app/security/resetPassword',				    
				  ],
				  access: {
				    isFree: true
				  }
            },
            '/changepassword': {
				  templateUrl: 'app/security/changePassword.html',
				  dependencies: [
				    '../app/security/changePassword',
				    '../common/directives/passwordcheck'
				  ],
				  access: {
				    isFree: false
				  }
            },'/adduserrole':{
                	templateUrl:'app/userRole/addUserRole.html',
                	dependencies:[
                	              '../app/userRole/addUserRole',
                	              '../common/services/usermanagement/user-role-service'],
                	              access:{
                	            	  isFree:false
                	              }
                },
                '/listuserroles':{
                	templateUrl:'app/userRole/listUserRole.html',
                	dependencies:[
                	              '../app/userRole/listUserRole',
                	              '../common/services/usermanagement/user-role-service'],
                	              access:{
                	            	  isFree:false
                	              }
                },
                '/viewuserrole':{
                	templateUrl:'app/userRole/viewUserRole.html',
                	dependencies:[
                	              '../app/userRole/viewUserRole',
                	              '../common/services/usermanagement/user-role-service'],
                	              access:{
                	            	  isFree:false
                	              }
                },
                '/edituserrole':{
                	templateUrl:'app/userRole/editUserRole.html',
                	dependencies:[
									'../app/userRole/editUserRole',
									'../common/services/usermanagement/user-role-service'],
                	              access:{
                	            	  isFree:false
                	              }
                },'/addsecurityuser':{
                	templateUrl:'app/securityUser/addSecurityUser.html',
                	dependencies:[
                	              '../app/securityUser/addSecurityUser',                	              
                	              '../common/services/usermanagement/security-user-service',
                	              '../common/directives/passwordcheck'],
                	              access:{
                	            	  isFree:false
                	              }
                },
                '/listsecurityusers':{
                	templateUrl:'app/securityUser/listSecurityUsers.html',
                	dependencies:[
                	              '../app/securityUser/listSecurityUser',
                	              '../common/services/usermanagement/security-user-service'],
                	              access:{
                	            	  isFree:false
                	              }
                },
                '/viewsecurityuser':{
                	templateUrl:'app/securityUser/viewSecurityUser.html',
                	dependencies:[
                	              '../app/securityUser/viewSecurityUser',
                	              '../common/services/usermanagement/security-user-service'],
                	              access:{
                	            	  isFree:false
                	              }
                },
                '/editsecurityuser':{
                	templateUrl:'app/securityUser/editSecurityUser.html',
                	dependencies:[
									'../app/securityUser/editSecurityUser',									
									'../common/services/usermanagement/security-user-service'],
                	              access:{
                	            	  isFree:false
                	              }
                },
                '/adduserroleforsecurityuser':{
                	templateUrl:'app/securityUser/addUserRolesToSecurityUser.html',
                	dependencies:[
                	              '../app/securityUser/addUserRolesToSecurityUser',
                	              '../common/services/usermanagement/user-role-service',
                	              '../common/services/usermanagement/security-user-service'
                	              ],
                	              access:{
                	            	  isFree:false
                	              }
                },
                '/updateuserprofile':{
                	templateUrl:'app/securityUser/updateUserProfile.html',
                	dependencies:[
                	              '../app/securityUser/updateUserProfile',
                	              '../common/services/usermanagement/security-user-service',
                	              ],
                	              access:{
                	            	  isFree:false
                	              }
                },
                '/viewjmxconsole':{
                	templateUrl:'app/jmxconsole/viewConsole.html',
                	dependencies:[
                	              '../app/jmxconsole/viewConsole',
                	              '../common/services/jmxconsole/console-service'
                	              ],
                	              access:{
                	            	  isFree:false
                	              }
                },'/eventsetup':{
                	templateUrl:'app/events/eventsetup.html',
                	dependencies:[
                	              '../app/events/eventsetup',
                	              '../common/services/events/event-service'],
                	              access:{
                	            	  isFree:false
                	              }
                },'/editevent':{
                	templateUrl:'app/events/editEvent.html',
                	dependencies:[
                	              '../app/events/editEvent',
                	              '../common/services/events/event-service'],
                	              access:{
                	            	  isFree:false
                	              }
                },'/listevents':{
                	templateUrl:'app/events/listEvents.html',
                	dependencies:[
                	              '../app/events/listEvents',
                	              '../common/services/events/event-service'],
                	              access:{
                	            	  isFree:false
                	              }
                },'/categorysetup':{
                	templateUrl:'app/category/categorysetup.html',
                	dependencies:[
                	              '../app/category/categorysetup',
                	              '../common/services/events/event-service',
                	              '../common/services/category/category-service'],
                	              access:{
                	            	  isFree:false
                	              }
                },'/playerregistration':{
                	templateUrl:'app/playerRegistration/playerregistration.html',
                	dependencies:[
                	              '../app/playerRegistration/playerregistration',
                	              '../common/services/events/event-service',
                	              '../common/services/category/category-service',
                	              '../common/services/player/player-service'],
                	              access:{
                	            	  isFree:false
                	              }
                },'/listplayers':{
                	templateUrl:'app/playerRegistration/listPlayers.html',
                	dependencies:[
                	              '../app/playerRegistration/listPlayers',
                	              '../common/services/player/player-service'],
                	              access:{
                	            	  isFree:false
                	              }
                },'/editplayer':{
                	templateUrl:'app/playerRegistration/editPlayer.html',
                	dependencies:[
                	              '../app/playerRegistration/editPlayer',
                	              '../common/services/player/player-service'],
                	              access:{
                	            	  isFree:false
                	              }
                },'/addagegroup':{
                	templateUrl:'app/ageGroup/addAgeGroup.html',
                	dependencies:[
                	              '../app/ageGroup/addAgeGroup',
                	              '../common/services/agegroup/age-group-service'],
                	              access:{
                	            	  isFree:false
                	              }
                },'/listagegroups':{
                	templateUrl:'app/ageGroup/listAgeGroups.html',
                	dependencies:[
                	              '../app/ageGroup/listAgeGroups',
                	              '../common/services/agegroup/age-group-service'],
                	              access:{
                	            	  isFree:false
                	              }
                },'/editagegroup':{
                	templateUrl:'app/ageGroup/editAgeGroup.html',
                	dependencies:[
                	              '../app/ageGroup/editAgeGroup',
                	              '../common/services/agegroup/age-group-service'],
                	              access:{
                	            	  isFree:false
                	              }
                }
         }
    };
});
