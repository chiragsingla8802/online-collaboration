'use strict';

app
		.controller('chatController', ['chatService',
        '$timeout', '$cookies', '$routeParams', '$location', '$route', '$rootScope', function (chatService,
                $timeout, $cookies, $routeParams, $location, $route, $rootScope) {

var self = this;

                self.messages = [];
                self.message = "";
                self.chatter = $routeParams.username;
                //self.max = 140;

                self.addMessage = function () {
                        chatService.send($rootScope.currentUser.name + " - " + self.message);
                        self.message = "";
                };

                chatService.receive().then(null, null, function (message) {
                        self.messages.push(message);
                });
        }])
