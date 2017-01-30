(function() {
    'use strict';
    angular
        .module('atletaMedallaApp')
        .factory('Medalla', Medalla);

    Medalla.$inject = ['$resource'];

    function Medalla ($resource) {
        var resourceUrl =  'api/medallas/:id';

        return $resource(resourceUrl, {}, {
            'query': { method: 'GET', isArray: true},
            'get': {
                method: 'GET',
                transformResponse: function (data) {
                    if (data) {
                        data = angular.fromJson(data);
                    }
                    return data;
                }
            },
            'update': { method:'PUT' }
        });
    }
})();
