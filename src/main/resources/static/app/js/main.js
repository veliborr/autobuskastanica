var stanica = angular.module("Stanica",["ngRoute"]);

stanica.controller("HomeCtrl", function($scope, $http, $location){
	
	var urlLinije = "/api/linije";
	var urlPrevoznici = "/api/prevoznici";
	
	$scope.linije = [];
	$scope.prevoznici = [];
	
	$scope.newLinija = {};
	$scope.newLinija.brojMesta = "";
	$scope.newLinija.cenaKarte = "";
	$scope.newLinija.vremePolaska = "";
	$scope.newLinija.destinacija = "";
	$scope.newLinija.prevoznikId = "";	
	
	$scope.search = {};
	$scope.search.destinacija = "";
	$scope.search.prevoznikId = "";
	$scope.search.maxCena = "";

	
	$scope.pageNum=0;
	$scope.totalPages = 1;
	
	
	var getLinije = function(){
		
		var config = {params:{}};
		
		if($scope.search.destinacija != ""){
			config.params.destinacija = $scope.search.destinacija;
		}
		if($scope.search.prevoznikId != ""){
			config.params.prevoznikId = $scope.search.prevoznikId;
		}
		if($scope.search.tip != ""){
			config.params.maxCena = $scope.search.maxCena; 
		}
		
		config.params.pageNum = $scope.pageNum;
		
		var promise = $http.get(urlLinije, config);
		promise.then(
			function success(res){
				$scope.linije = res.data;
				$scope.totalPages = res.headers("totalPages");
				
			},
			function error(res){
				alert("Couldn't fetch linije.");
			}
		);
		//console.log("test");
	}
	
	getLinije();
	
	var getPrevoznici = function(){
		var promise = $http.get(urlPrevoznici);
		promise.then(
			function success(res){
				$scope.prevoznici = res.data;
				//console.log(res);
			},
			function error(res){
				alert("Couldn't fetch prevoznici.");
			}
		);
		//console.log("test");
	}
	
	getPrevoznici();
	
	$scope.doAdd = function(){
		
		$http.post(urlLinije, $scope.newLinija).then(
			function success(res){
				getLinije();
				
				$scope.newLinija = {};
			},
			function error(){
				alert("Couldn't save the linija");
			}
		);
	}
	
	$scope.goToEdit = function(id){
		$location.path("/linije/edit/" + id);
	}
	
	
	$scope.doSearch = function(){
		console.log($scope.search);
		$scope.pageNum = 0;
		getLinije();
	}
	
	$scope.changePage = function(direction){
		$scope.pageNum += direction;
		getLinije();
	}
	
	$scope.doRezervisi = function(id) {
		$http.post(urlLinije + "/" + id + "/rezervacija").then(
				function success(res){
					alert("Rezervacija uspesna!");
					getLinije();

				},
				function error(){
					alert("Couldn't do the rezervacija");
				}
			);
	}
	
});

stanica.controller("LinijaEditCtrl", function($scope, $http, $routeParams, $location){
	
	var urlLinija = "/api/linije/" + $routeParams.id;
	var urlPrevoznici = "/api/prevoznici";
	
	$scope.linija = {};
	$scope.linija.brojMesta = "";
	$scope.linija.cenaKarte = "";
	$scope.linija.vremePolaska = "";
	$scope.linija.destinacija = "";
	$scope.linija.prevoznikId = "";
		
	$scope.prevoznici = [];
	
	var getPrevoznici = function(){
		var promise = $http.get(urlPrevoznici);
		promise.then(
			function success(res){
				$scope.prevoznici = res.data;
				getLinija();
				//console.log(res);
			},
			function error(res){
				alert("Couldn't fetch prevoznici.");
			}
		);
		//console.log("test");
	}
	
	var getLinija = function(){
		var promise = $http.get(urlLinija);
		promise.then(
			function success(res){
				$scope.linija = res.data;
				
				//console.log(res);
			},
			function error(res){
				alert("Couldn't fetch linija.");
			}
		);
		//console.log("test");
	}
	
	getPrevoznici();
			

	$scope.doEdit = function(){
		$http.put(urlLinija, $scope.linija).then(
			function success(){
				$location.path("/");
			},
			function error(){
				alert("Something went wrong.");
			}
		);
	}
});

stanica.controller("PorukaGlasanjeCtrl", function($scope, $http, $routeParams, $location){
	
	var urlPoruke = "/api/poruke/" + $routeParams.id;
	var urlZgrade = "/api/zgrade";
	
	$scope.poruka = {};
	$scope.poruka.naslov = "";
	$scope.poruka.tip = "";
	$scope.poruka.potrebanProcenat = "";
	$scope.poruka.opis = "";
	$scope.poruka.zgradaId = "";
		
	$scope.zgrade = [];
	
	$scope.newGlas = {};
	$scope.newGlas.predlogPodrzan = "";
	
	var getZgrade = function(){
		var promise = $http.get(urlZgrade);
		promise.then(
			function success(res){
				$scope.zgrade = res.data;
				getPoruka();
				//console.log(res);
			},
			function error(res){
				alert("Couldn't fetch zgrade.");
			}
		);
		//console.log("test");
	}
	
	var getPoruka = function(){
		var promise = $http.get(urlPoruke);
		promise.then(
			function success(res){
				$scope.poruka = res.data;
				
				//console.log(res);
			},
			function error(res){
				alert("Couldn't fetch poruka.");
			}
		);
		//console.log("test");
	}
	
	getZgrade();
			

	$scope.glasajZa = function(){
		
		$scope.newGlas.predlogPodrzan = "za";
			
			$http.post(urlPoruke, $scope.newGlas).then(
				function success(res){
					$location.path("/");
				},
				function error(){
					alert("Couldn't save the glas");
				}
			);
		}
	
	$scope.glasajProtiv = function(){
			
			$scope.newGlas.predlogPodrzan = "protiv";
				
				$http.post(urlPoruke, $scope.newGlas).then(
					function success(res){
						$location.path("/");
					},
					function error(){
						alert("Couldn't save the glas");
					}
				);
			}
		
});


stanica.config(['$routeProvider', function($routeProvider) {
	$routeProvider
		.when('/', {
			templateUrl : '/app/html/home.html',
		})
		.when('/poruke/glasanje/:id', {
			templateUrl : '/app/html/glasanje.html'
		})
		.when('/linije/edit/:id', {
			templateUrl : '/app/html/linija-edit.html'
		})
		.otherwise({
			redirectTo: '/'
		});
}]);