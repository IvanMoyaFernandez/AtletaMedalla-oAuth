(function() {
    'use strict';

    angular
        .module('atletaMedallaApp')
        .controller('MedallaController', MedallaController);

    MedallaController.$inject = ['$scope', '$state', 'Medalla'];

    function MedallaController ($scope, $state, Medalla) {
        var vm = this;

        vm.medallas = [];

        loadAll();

        function loadAll() {
            Medalla.query(function(result) {
                vm.medallas = result;
                vm.searchQuery = null;
            });
        }
    }
})();
