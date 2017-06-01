'use strict';
 
app.factory('FriendService', ['$http', '$q','$rootScope', function($http, $q,$rootScope){
	
	console.log("FriendService...")
	
	var BASE_URL='http://localhost:8080/onlinecollaboration/friend'
    return {
		
	
		searchAllUsers: function() {
        	console.log("calling searchAllUsers ")
                return $http.get(BASE_URL+'/searchAll')
                        .then(
                                function(response){
                                    return response.data;
                                }, 
                               null
                        );
        },
	

sendRequest:function (id) {
var deferred = $q.defer();
var initId =$rootScope.currentUser.userId
$http.post(BASE_URL + '/friendRequest/' + id, initId)
.then (
function(response) {
deferred.resolve(response.data);
},
function(errResponse) {
deferred.reject(errResponse);
}
)
return deferred.promise;
        
},


fetchRequest:function () {
var deferred = $q.defer();
var userId = $rootScope.currentUser.userId           
$http.get(BASE_URL + '/friendRequest/list/' + userId)               
.then (                   
function(response) {                        
deferred.resolve(response.data);                    
},                    
function(errResponse) {                        
deferred.reject(errResponse);                   
}               
);               
return deferred.promise;      
},


approveRequest:function (id) {           
debugger;          
var deferred = $q.defer();         
var userId = $rootScope.currentUser.userId        
$http.post(BASE_URL + '/friendRequest/approve/' + id, userId)            
.then (                
function(response) {                    
deferred.resolve(response.data);                 
},                 
function(errResponse) {                     
deferred.reject(errResponse);                 
}              
);              
return deferred.promise;     
},

        
checkUsersFriends:function () {           
var deferred = $q.defer();          
var userId = $rootScope.currentUser.userId           
$http.get(BASE_URL + '/my/friends/' + userId)              
.then (                 
function(response) {                     
deferred.resolve(response.data);                 
},                 
function(errResponse) {                     
deferred.reject(errResponse);                 
}             
);              
return deferred.promise;     
}
           
         
    };
 
}]);