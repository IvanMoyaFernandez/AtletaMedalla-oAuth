(function() {
    'use strict';

    angular
        .module('atletaMedallaApp')
        .controller('MedallaDetailController', MedallaDetailController);

    MedallaDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'previousState', 'entity', 'Medalla', 'Atleta'];

    function MedallaDetailController($scope, $rootScope, $stateParams, previousState, entity, Medalla, Atleta) {
        var vm = this;

        vm.medalla = entity;
        vm.previousState = previousState.name;

        var unsubscribe = $rootScope.$on('atletaMedallaApp:medallaUpdate', function(event, result) {
            vm.medalla = result;
        });
        $scope.$on('$destroy', unsubscribe);
    }
})();
