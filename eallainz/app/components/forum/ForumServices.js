'use strict';
 
app.factory('ForumService', ['$http', '$q','$routeParams','$rootScope', function($http, $q,$routeParams,$rootScope){
	
	console.log("ForumService...")
	
	var BASE_URL='http://localhost:8080/onlinecollaboration/forum'
    return {


                addForum : addForum,
                fetchForums : fetchForums,
                viewForum : viewForum,
                joinRequest : joinRequest,
                getParticipatedUsers : getParticipatedUsers,
                addForumPost : addForumPost,
                fetchBlogPosts : fetchBlogPosts,
                fetchForumRequests: fetchForumRequests,
                changeFRStatus : changeFRStatus,
            };

            //function to add a new forum Forum Category
            function addForum(forum) {

                var deferred = $q.defer();

                $http.post(BASE_URL + '/forum/new', forum).then(
                    function(response) {
                        debugger;
                        deferred.resolve(response.data);
                    }, function(errResponse) {
                        deferred.reject(response.data);
                    }
                );
                return deferred.promise;
            }

            //Function to fetch list of forum categories
            function fetchForums() {
                
                var deferred = $q.defer();

                $http.get(BASE_URL + '/forum/list').then(
                    function(response) {
                        deferred.resolve(response.data);
                    }, function(errResponse) {
                        deferred.reject(response.data);
                    }
                );
                return deferred.promise;
            }

             //Function for viewing single blog using blog id as a parameter
            function viewForum(id) {
                
                var deferred = $q.defer();

                $http.get(BASE_URL + '/forum/' + id)
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

            //Function to send forum join request
            function joinRequest() {
                
                 var deferred = $q.defer();
                var id = $rootScope.currentUser.userId;
                var forumId =  $routeParams.id;
                $http.post(BASE_URL + '/forum/request/' + id, forumId)
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

            //Function to fetch the list of ParticipatedUsers
            function getParticipatedUsers(id) {
                
                var deferred = $q.defer();
                $http.get(BASE_URL + '/forum/participatedUsers/list/' + id)
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
           
            //function to add a new forum post
            function addForumPost(forumPost) {

                var deferred = $q.defer();
                var forumId = $routeParams.id;
                $http.post(BASE_URL + '/forum/post/new/' + forumId, forumPost).then(
                    function(response) {
                        debugger;
                        deferred.resolve(response.data);
                    }, function(errResponse) {
                        deferred.reject(response.data);
                    }
                );
                return deferred.promise;
            }

        //Function to fetch forum post list
        function fetchBlogPosts(forumId) {
            console.log('Inside factory now');
            var deferred = $q.defer();
            
            $http.get(BASE_URL+ '/forum/posts/list/' + forumId)
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

         //Function to fetch forum join request with pending status
            function fetchForumRequests() {

                var deferred = $q.defer();
                $http.get(BASE_URL + '/forum/request/list')
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

        //Function to change status pending forum request
        function changeFRStatus(id) {
            var deferred = $q.defer();
            
            $http.post(BASE_URL + '/forum/request/approval/' + id)
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

        }])