define([], function()
{
    return {
        defaultRoutePath: '/rdd-usermanagement-ui-web/',
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
                	              '../common/services/usermanagement/application-service',
                	              '../common/services/usermanagement/application-module-service',
                	              '../common/services/usermanagement/application-module-service-service',
                	              '../common/services/usermanagement/screen-service',
                	              '../common/services/usermanagement/screen-permission-service-bak',
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
									'../common/services/usermanagement/application-service',
									'../common/services/usermanagement/application-module-service',
									'../common/services/usermanagement/application-module-service-service',
									'../common/services/usermanagement/screen-service',
									'../common/services/usermanagement/screen-permission-service-bak',
									'../common/services/usermanagement/user-role-service'],
                	              access:{
                	            	  isFree:false
                	              }
                },'/addsecurityuser':{
                	templateUrl:'app/securityUser/addSecurityUser.html',
                	dependencies:[
                	              '../app/securityUser/addSecurityUser',                	              
                	              '../common/services/usermanagement/organization-service',
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
									'../common/services/usermanagement/organization-service',
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
                	              '../common/services/usermanagement/document-management-service'
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
                }
         }
    };
});
