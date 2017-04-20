window.routes = {

"/contact":{
templateUrl:'app/components/page/contact.html',
controller:'contactController',
controllerAs:'contactCtrl',
requireLogin:false,
roles:['GUSET','USER','ADMIN']
    
},


"/about":{
templateUrl:'app/components/page/about.html',
controller:'aboutController',
controllerAs:'aboutCtrl',
requireLogin:false,
roles:['GUSET','USER','ADMIN']
    
},

"/registeration":{

templateUrl:'app/components/register/registeration.html',
controller:'RegisterController',
controllerAs:'regCtrl',
requireLogin:false,
roles:['GUSET']
    
},


};

//specify the backend url from where you are going to get the values
app.constant('REST_URI','http://localhost:8080/onlinecollaboration');

CollaborationApp.config(['$routeProvider',function($routeProvider){

for(var path in window.routes){

    $routeProvider.when(path,window.routes[path]);
}


$routeProvider.otherwise({redirectTo: '/login'})

}]);