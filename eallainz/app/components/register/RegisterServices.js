var RegisterModule = angular.module('RegisterModule',[]);

RegisterModule.service('RegisterService', ['$http','$q','REST_URI',function($http,$q,REST_URI) {

        this.register = function(user) {
            //console.log(user);
            var deferred = $q.defer();

            $http.post(REST_URI + '/user/add', user).then(
                function(response) {
                    deferred.resolve(response.data);
                },
                function(error) {
                    deferred.reject(error);
                }
            );

            return deferred.promise;
        }

        
}]);
