'use strict';

define(['app'], function (app) {

	app.constant('Constants', {		
	    Events: {
	        updated: 'UPDATE_CLICKED',
	        saved: 'SAVE_CLICKED',
	        deleted: 'DELETE_CLICKED',
	        deleted: 'DELETE_CLICKED',
	        next: 'NEXT_CLICKED',
	        previous: 'PREVIOUS_CLICKED',
	        print: 'PRINT_CLICKED',
	        copy: 'COPY_CLICKED',
	        edit: 'EDIT_CLICKED',
	        cancel: 'CANCEL_CLICKED',
	        add_new:'ADD_NEW_CLICKED',
	        loggedin:'LOGGED_IN',
	        loggedout:'LOGGED_OUT',
	        displayheaderbuttons:'DISPLAY_HEADER_BUTTONS',
	    },
	    Keys: {
	    	authtoken: 'AUTH_TOKEN',
	    	userobject:'LOGGED_IN_USER'
	    },
	    Headers: {
	    	xauthtoken: 'X-AUTH-TOKEN'
	    },
	    Languages: {
	    	si: 'si_LK',
	    	ta: 'ta_LK',
	    	en: 'en_US'
	    }
	}); 

});
