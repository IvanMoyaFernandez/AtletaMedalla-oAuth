(function() {
    'use strict';

    angular
        .module('atletaMedallaApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider
        .state('medalla', {
            parent: 'entity',
            url: '/medalla',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'atletaMedallaApp.medalla.home.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/medalla/medallas.html',
                    controller: 'MedallaController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('medalla');
                    $translatePartialLoader.addPart('tipoMedalla');
                    $translatePartialLoader.addPart('global');
                    return $translate.refresh();
                }]
            }
        })
        .state('medalla-detail', {
            parent: 'entity',
            url: '/medalla/{id}',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'atletaMedallaApp.medalla.detail.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/medalla/medalla-detail.html',
                    controller: 'MedallaDetailController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('medalla');
                    $translatePartialLoader.addPart('tipoMedalla');
                    return $translate.refresh();
                }],
                entity: ['$stateParams', 'Medalla', function($stateParams, Medalla) {
                    return Medalla.get({id : $stateParams.id}).$promise;
                }],
                previousState: ["$state", function ($state) {
                    var currentStateData = {
                        name: $state.current.name || 'medalla',
                        params: $state.params,
                        url: $state.href($state.current.name, $state.params)
                    };
                    return currentStateData;
                }]
            }
        })
        .state('medalla-detail.edit', {
            parent: 'medalla-detail',
            url: '/detail/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/medalla/medalla-dialog.html',
                    controller: 'MedallaDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['Medalla', function(Medalla) {
                            return Medalla.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('^', {}, { reload: false });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('medalla.new', {
            parent: 'medalla',
            url: '/new',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/medalla/medalla-dialog.html',
                    controller: 'MedallaDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: function () {
                            return {
                                tipoMedalla: null,
                                especialidad: null,
                                competicion: null,
                                id: null
                            };
                        }
                    }
                }).result.then(function() {
                    $state.go('medalla', null, { reload: 'medalla' });
                }, function() {
                    $state.go('medalla');
                });
            }]
        })
        .state('medalla.edit', {
            parent: 'medalla',
            url: '/{id}/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/medalla/medalla-dialog.html',
                    controller: 'MedallaDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['Medalla', function(Medalla) {
                            return Medalla.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('medalla', null, { reload: 'medalla' });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('medalla.delete', {
            parent: 'medalla',
            url: '/{id}/delete',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/medalla/medalla-delete-dialog.html',
                    controller: 'MedallaDeleteController',
                    controllerAs: 'vm',
                    size: 'md',
                    resolve: {
                        entity: ['Medalla', function(Medalla) {
                            return Medalla.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('medalla', null, { reload: 'medalla' });
                }, function() {
                    $state.go('^');
                });
            }]
        });
    }

})();
