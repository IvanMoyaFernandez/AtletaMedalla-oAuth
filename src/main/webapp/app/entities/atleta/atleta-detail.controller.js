(function() {
    'use strict';

    angular
        .module('atletaMedallaApp')
        .controller('AtletaDetailController', AtletaDetailController);

    AtletaDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'previousState', 'entity', 'Atleta', 'Medalla'];

    function AtletaDetailController($scope, $rootScope, $stateParams, previousState, entity, Atleta, Medalla) {
        var vm = this;

        vm.atleta = entity;
        vm.previousState = previousState.name;

        var unsubscribe = $rootScope.$on('atletaMedallaApp:atletaUpdate', function(event, result) {
            vm.atleta = result;
        });
        $scope.$on('$destroy', unsubscribe);
    }
})();
