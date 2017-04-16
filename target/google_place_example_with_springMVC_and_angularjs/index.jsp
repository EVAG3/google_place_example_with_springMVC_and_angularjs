
<html>
<head>

<script
	src="https://ajax.googleapis.com/ajax/libs/angularjs/1.4.4/angular.js"></script>

<title>Simple SpringMVC, AngularJS and Google Map example</title>
<script type="text/javascript">
            var app = angular.module("PlaceManagement", []);
         
            //Controller Part
            app.controller("PlaceController", function($scope, $http) {
         
               
                $scope.places = [];
                $scope.locationForm = {
                    firstLocStr : "",
                    secondLocStr : "",
                    type : ""
                };
         
                //Now load the data from server
                _refreshCountryData();
                
                $scope.submitRequest = function() {
         
                    var method = "POST";
                    var url = 'rest/places';
                    $http({
                        method : method,
                        url : url,
                        data : angular.toJson($scope.locationForm),
                        headers : {
                            'Content-Type' : 'application/json'
                        }
                    }).then( _success, _error );
                };
         
                /*
                //HTTP DELETE- delete country by Id
                $scope.deleteCountry = function(country) {
                    $http({
                        method : 'DELETE',
                        url : 'rest/countries/' + country.id
                    }).then(_success, _error);
                };
                */
 
                /*
             // In case of edit, populate form fields and assign form.id with country id
                $scope.editCountry = function(country) {
                  
                    $scope.countryForm.countryName = country.countryName;
                    $scope.countryForm.population = country.population;
                    $scope.countryForm.id = country.id;
                };
                */
         
                /* Private Methods */
                //HTTP GET- get all countries collection
                function _refreshCountryData() {
                    $http({
                        method : 'GET',
                        url : 'rest/places'
                    }).then(function successCallback(response) {
                        $scope.places = response.data;
                    }, function errorCallback(response) {
                        console.log(response.statusText);
                    });
                }
         
                function _success(response) {
                    _refreshCountryData();
                    _clearFormData()
                }
         
                function _error(response) {
                    console.log(response.statusText);
                }
         
                //Clear the form
                function _clearFormData() {
                    $scope.locationForm.firstLocStr = "";
                    $scope.locationForm.secondLocStr = "";
                    $scope.locationForm.type = "";
                
                };
            });
        </script>
<style>
.blue-button {
	background: #25A6E1;
	filter: progid: DXImageTransform.Microsoft.gradient( startColorstr='#25A6E1',
		endColorstr='#188BC0', GradientType=0);
	padding: 3px 5px;
	color: #fff;
	font-family: 'Helvetica Neue', sans-serif;
	font-size: 12px;
	border-radius: 2px;
	-moz-border-radius: 2px;
	-webkit-border-radius: 4px;
	border: 1px solid #1A87B9
}

table {
	font-family: "Helvetica Neue", Helvetica, sans-serif;
	width: 50%;
}

caption {
	text-align: left;
	color: silver;
	font-weight: bold;
	text-transform: uppercase;
	padding: 5px;
}

th {
	background: SteelBlue;
	color: white;
}

tbody tr:nth-child(even) {
	background: WhiteSmoke;
}

tbody tr td:nth-child(2) {
	text-align: center;
}

tbody tr td:nth-child(3), tbody tr td:nth-child(4) {
	text-align: center;
	font-family: monospace;
}

tfoot {
	background: SeaGreen;
	color: white;
	text-align: right;
}

tfoot tr th:last-child {
	font-family: monospace;
}

td, th {
	border: 1px solid gray;
	width: 25%;
	text-align: left;
	padding: 5px 10px;
}
</style>
<head>

<body ng-app="PlaceManagement" ng-controller="PlaceController">
	<h1>Simple SpringMVC, AngularJS and Google Map example</h1>
	<form ng-submit="submitRequest()">
		<table>

			<tr>
				<th colspan="2">Give two target locations:</th>
			</tr>
			<tr>
				<td>First location address</td>
				<td><input type="text" ng-model="locationForm.firstLocStr" /></td>
			</tr>
			<tr>
				<td>Second location address</td>
				<td><input type="text" ng-model="locationForm.secondLocStr" /></td>
			</tr>
			<tr>
				<td>The type for the nearest places (Must be supported by Google Places API)</td>
				<td><input type="text" ng-model="locationForm.type" /></td>
			</tr>
			<tr>
				<td colspan="2"><input type="submit" value="Submit (may take up to 10 seconds)"
					class="blue-button" /></td>
			</tr>
		</table>
	</form>
	<table>
		<tr>

			<th>Name</th>
			<th>URL link</th>
			<th>Distance to first target location (m)</th>
			<th>Distance to second target location (m)</th>
			<th>Total Distance</th>

		</tr>

		<tr ng-repeat="simplePlace in places">

			<td>{{ simplePlace.name }}</td>
			<td>{{ simplePlace.url }}</td>
			<td>{{ simplePlace.firstDis }}</td>
			<td>{{ simplePlace.secondDis }}</td>
			<td>{{ simplePlace.disSum }}</td>

			
		</tr>

	</table>
</body>
</html>