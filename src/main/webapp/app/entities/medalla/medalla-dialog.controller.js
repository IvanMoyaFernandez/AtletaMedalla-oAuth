(function() {
    'use strict';

    angular
        .module('atletaMedallaApp')
        .controller('MedallaDialogController', MedallaDialogController);

    MedallaDialogController.$inject = ['$timeout', '$scope', '$stateParams', '$uibModalInstance', 'entity', 'Medalla', 'Atleta'];

    function MedallaDialogController ($timeout, $scope, $stateParams, $uibModalInstance, entity, Medalla, Atleta) {
        var vm = this;

        vm.medalla = entity;
        vm.clear = clear;
        vm.save = save;
        vm.atletas = Atleta.query();

        $timeout(function (){
            angular.element('.form-group:eq(1)>input').focus();
        });

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function save () {
            vm.isSaving = true;
            if (vm.medalla.id !== null) {
                Medalla.update(vm.medalla, onSaveSuccess, onSaveError);
            } else {
                Medalla.save(vm.medalla, onSaveSuccess, onSaveError);
            }
        }

        function onSaveSuccess (result) {
            $scope.$emit('atletaMedallaApp:medallaUpdate', result);
            $uibModalInstance.close(result);
            vm.isSaving = false;
        }

        function onSaveError () {
            vm.isSaving = false;
        }


    }
})();
