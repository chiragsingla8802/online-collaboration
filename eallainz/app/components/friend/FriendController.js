
'use strict';

app.controller('FriendController', [
		'UserService',
		'$scope',
		'FriendService',
		'$cookies', 
		'$location',
		'$routeParams',
		'$rootScope',
		function(UserService, $scope, FriendService,$cookies,$location, $routeParams,$rootScope) {
			console.log("FriendController...")
			var self = this;
			self.friend = {
								userId : '',
								name : '',
								password : '',
								phoneNo : '',
								address : '',
								city : '',
								state : '',
								isOnline : '',
								role : ''							
							};
			self.userslist = [];
self.tempUsers = [];
self.countUsers = {};
self.hasSentRequest = false;
self.friendRequests = [];
self.myFriends = []; 
self.myFriendsCount = []; 
self.friendRequestsCount =[];   


self.sendRequest = function(id) {
console.log("sending friend request")
FriendService.sendRequest(id)     
.then (
function(friend) {           
debugger;                  
 },function(errResponse) {
}
);
},

self.fetchRequest = function() {
FriendService.fetchRequest()
 .then (
function(friendRequests) {
self.friendRequests = friendRequests;
self.friendRequestsCount = self.friendRequests.length;
},
function(errResponse) {
}
);
},

self.approveRequest = function(id) {
FriendService.approveRequest(id)
.then (
function(friendRequests) {
console.log('friend request accepted!')
},function(errResponse) {
}
);
},

self.rejectRequest = function(id) {
FriendService.rejectRequest(id)
.then (
function(friendRequests) {
console.log('friend request rejected!')
},function(errResponse) {
}
);
},

self.myfriend = function() {
FriendService.checkUsersFriends()
.then (
function(d) {
console.log('my friends!')
self.myFriends = d;
self.myFriendsCount = self.myFriends.length;
},function(errResponse) {
}
);
},

self.fetchusers = function() {       
 FriendService.fetchUsers()   
.then (            
 function(user) {           
 
self.userslist = user;
console.log('all users but not my friends!!!')                    
self.countUsers = self.userslist.length;              
}, function (errResponse) {              
 }           
);
    }
} 
]);