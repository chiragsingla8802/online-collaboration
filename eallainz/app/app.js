
var app = angular.module('myApp2', ['ngRoute','ngCookies']);

app.config(function($routeProvider) {
  $routeProvider

  .when('/', {
    templateUrl : './app/components/user/user-navbar.html',
    controller : 'UserController'
   
  })

   .when('/register', {
    templateUrl : './app/components/user/register.html',
    controller : 'UserController'
   
  })

  .when('/login', {
    templateUrl : './app/components/user/login.html',
    controller : 'UserController'
   
  })

  .when('/listUser',{
		templateUrl:'./app/components/user/listUser.html',
		controller : 'UserController'
	})

  .when('/myProfile',{
		templateUrl:'./app/components/user/myProfile.html',
		controller : 'UserController'
	})

.when('/myhome',{
		templateUrl:'./app/components/user/myhome.html',
		controller : 'UserController'
	})

  .when('/adminhome',{
		templateUrl:'./app/components/user/adminhome.html',
		controller : 'UserController'
	})

   .when('/update-user',{
		templateUrl:'./app/components/user/update-user.html',
		controller : 'UserController'
	})

  .when('/blog', {
    templateUrl : './app/components/blog/blog.html',
    controller : 'BlogController'
   
  })

  .when('/createblog', {
    templateUrl : './app/components/blog/createblog.html',
    controller : 'BlogController'
   
  })

  .when('/listblog', {
    templateUrl : './app/components/blog/listblog.html',
    controller : 'BlogController'
   
  })

  .when('/myblog', {
    templateUrl : './app/components/blog/myblog.html',
    controller : 'BlogController'
   
  })

  .when('/sortblog', {
    templateUrl : './app/components/blog/sortblog.html',
    controller : 'BlogController'
   
  })

  .when('/viewblog', {
    templateUrl : './app/components/blog/viewblog.html',
    controller : 'BlogController'
   
  })

.when('/friendRequests', {
    templateUrl : './app/components/friend/friendRequests.html',
    controller : 'FriendController'
   
  })


  .when('/memberslist', {
    templateUrl : './app/components/friend/memberslist.html',
    controller : 'FriendController'
   
  })

  .when('/myfriends', {
    templateUrl : './app/components/friend/myfriends.html',
    controller : 'FriendController'
   
  })
  
   .when('/post_job', {
    templateUrl : './app/components/job/post_job.html',
    controller : 'JobController'
   
  })

  .when('/search_job', {
    templateUrl : './app/components/job/search_job.html',
    controller : 'JobController'
   
  })

  .when('/sortjob', {
    templateUrl : './app/components/job/sortjob.html',
    controller : 'JobController'
   
  })

  .when('/applied_job', {
    templateUrl : './app/components/job/view_applied_job.html',
    controller : 'JobController'
   
  })

  .when('/job_detail', {
    templateUrl : './app/components/job/view_job_detail.html',
    controller : 'JobController'
   
  })

  .when('/forum/:id', {
    templateUrl : './app/components/forum/forum.html',
    controller : 'ForumController'
   
  })


  .when('/createForum', {
    templateUrl : './app/components/forum/createForum.html',
    controller : 'ForumController'
   
  })

  .when('/forumlist', {
    templateUrl : './app/components/forum/forumlist.html',
    controller : 'ForumController'
   
  })

  .when('/forumRequests', {
    templateUrl : './app/components/forum/forumRequests.html',
    controller : 'ForumController'
   
  })

  .when('/manageForums', {
    templateUrl : './app/components/forum/manageForums.html',
    controller : 'ForumController'
   
  })

 .when('/chat/:id/:username', {
    templateUrl : './app/components/chat/chat.html',
    controller : 'chatController'
   
  })


  .otherwise({redirectTo: '/'});
});




