'use strict';

app.controller('ForumController', [
        'ForumService',
        '$timeout',
		'$cookies', 
		'$routeParams',
        '$rootScope',
        '$location',
        '$route', 
        '$q',
		function(ForumService,$timeout,$cookies,$routeParams,$rootScope,$location,$route,$q) {
			console.log("ForumController...")


            var self = this;

            //Setting up the field for creating new forum category
            self.Forum = {

                id : null,
                name : '',
                description : '',
                userId : '',
                userName : ''
            }

            //Setting up the field for creating new forum post
            self.ForumPost = {

                id : null,
                title : '',
                description : '',
                userId : '',
                username : ''
            }

            //array for displaying list of forum categories
            self.forums = [];

             // For viewing single forum
             self.singleForum = {};

             //Setting up creator of the forum
             self.singleForumUser = {};

             //For list of participated users
             self.participatedUsers = []; 

             //Flag to see whether user is particant or not
            self.isParticipant = false;

            //Flag to check request status
            self.isApproved = false;

            //For storing participant status
            self.participantStatus = "PENDING";

            //For list of forum posts
            self.forumPostsList = [];

            self.forumPostUser = [];

 //For fetching list of forumRequest with pending status
    self.forumRequest = [];
            //method for adding new category
            self.addForum = function() {
                
                //Setting the user id and username
                self.Forum.userId =$rootScope.currentUser.userId;
                self.Forum.userName =$rootScope.currentUser.name;
                
                 ForumService.addForum(self.Forum) 
                        .then(
                            function(forum) {
                                self.Forum = forum;
                              
                            }, function(errResponse) {
                                
                            }
                        );
            }

           
            //method to fetch all the forum categories
             self.fetchForums = function() {
                debugger;
                
                 ForumService.fetchForums().then(
                        function(forums) {
                            self.forums = forums;
                    }, function(errResponse) {
                        }
                    );
            }

            //function for viewing single forum
            self.viewForum = function() {
                //Fetch participated users first for this forum
                //  getParticipatedUsers().then(
                //         function(participatedUsers){
                //             self.participatedUsers = participatedUsers; 
                //             for(var id in self.participatedUsers) {
                //                 if($rootScope.currentUser.userId == self.participatedUsers[id].userId) { 
                //                     self.isParticipant = true;  
                //                     self.participantStatus = self.participatedUsers[id].status;                       
                //                     break;                     
                //                 }
                //             }
                //             if(self.participantStatus == "APPROVED") {    
                //                          self.isApproved = true;
                //             }
                            //fetching single forum page here
                            //Assigning forum id to variable forumId
                            var forumId = $routeParams.id;
                            ForumService.viewForum(forumId)
                                .then (
                                    function(forumModel) {
                                        self.singleForum = forumModel.forum;
                                        self.singleForumUser = forumModel.user;
                                        // self.singleForum.postDate = new Date(self.singleForum.postDate[0],self.singleForum.postDate[1] - 1,self.singleForum.postDate[2]);
                                      self.fetchBlogPosts();
                                    },
                                    function(errResponse) {
                                    }
                                );
                        } 
        

            //Function to send forum join request
            self.joinRequest = function() {
                  ForumService.joinRequest()
                    .then (
                        function(forum) {
                        //  $route.reload();
                        //  Materialize.toast('Request to join the forum sent!', 3000);
                         self.viewForum();

                        },
                        function(errResponse) {
                        }
                    );
            }

            //Function to fetch the list of participated users
            function getParticipatedUsers() {
                var deferred = $q.defer();
                var forumId = $routeParams.id;
                 ForumService.getParticipatedUsers(forumId)
                    .then (
                        function(participatedUsers) {
                            
                         deferred.resolve(participatedUsers);
                        },
                        function(errResponse) {
                        }
                    );

                    return deferred.promise;
            }

            //function for adding a new forum post
            self.addForumPost = function () {

                //Setting the user id and username
               self.ForumPost.userId = $rootScope.currentUser.id;
               self.ForumPost.username =$rootScope.currentUser.name;
                // self.blogComment.blogId = $routeParams.id;
                //calling the addBlog method in the factory
                 ForumService.addForumPost(self.ForumPost)
                    .then (
                        function(ForumPost) {
                            self.ForumPost =  ForumPost;
                             $('#leaveAPost').modal('close');
                        }, function (errResponse) {
                            
                        }
                    );
                
            }

            //Method to fetch forum posts
        self.fetchBlogPosts = function () {
                var forumId = $routeParams.id;
                 ForumService.fetchBlogPosts(forumId)
                    .then (
                        function(forumPosts) {
                            debugger;
                            self.forumPostsList = forumPosts;
                            // for(var postDate in self.forumPostsList) {
                            //     self.forumPostsList[postDate].postDate = new Date(self.forumPostsList[postDate].postDate[0],self.forumPostsList[postDate].postDate[1] - 1,self.forumPostsList[postDate].postDate[2]);
                            // }
                        },
                        function(errResponse) {
                        }
                    );
            }

  //Function to fetch forum requests
            self.fetchForumRequests = function() {
                
                  ForumService.fetchForumRequests()
                    .then (
                        function(forumRequests) {
                           self.forumRequest = forumRequests;
                        },
                        function(errResponse) {
                        }
                    );

            }

            //Function to change status of forumRequests
            self.changeFRStatus = function(id) {
                
                  ForumService.changeFRStatus(id)
                    .then (
                        function(forumRequest) {
                            $route.reload();
                        },
                        function(errResponse) {
                        }
                    );
    }
}])