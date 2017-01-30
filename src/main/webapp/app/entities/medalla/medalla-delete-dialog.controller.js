(function() {
    'use strict';

    angular
        .module('atletaMedallaApp')
        .controller('MedallaDeleteController',MedallaDeleteController);

    MedallaDeleteController.$inject = ['$uibModalInstance', 'entity', 'Medalla'];

    function MedallaDeleteController($uibModalInstance, entity, Medalla) {
        var vm = this;

        vm.medalla = entity;
        vm.clear = clear;
        vm.confirmDelete = confirmDelete;

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function confirmDelete (id) {
            Medalla.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        }
    }
})();
