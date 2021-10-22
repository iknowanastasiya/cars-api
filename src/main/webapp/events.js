document.addEventListener("DOMContentLoaded", function() {
    // create a GET request to retrieve ALL cars, and add them to the table
    var xhr = new XMLHttpRequest();		// make HTTP requests
    // 2. define what happens during the AJAX call
    xhr.onreadystatechange = function() {
        if(xhr.readyState === 4 ) {	
            var carList = JSON.parse(xhr.responseText);	// creating an array of JSON car objects
            carList.forEach(carElement => {
                addCarToTable(carElement);
            });
        }
    }
    // 3. open the xhr call with the http request verb and the url
    xhr.open('GET', '/cars-api/cars');
    // 4. send the ajax call
    xhr.send();
});

// low-level DOM Manipulation
function addCarToTable(car) {

    // creating all of our needed DOM elements
	var tr = document.createElement('tr');
	var vin = document.createElement('td');
	var car_make = document.createElement('td');
	var car_model =document.createElement('td');
	var year = document.createElement('td');
	var color = document.createElement('td');
	var cost = document.createElement('td');
	var convertible = document.createElement('td');
	var sports = document.createElement('td');
	
    // adding data to the elements
	vin.innerText = car.vin;
	car_make.innerText = car.car_make;
	car_model.innerText = car.car_model;
	year.innerText = car.year;
	color.innerText = car.color;
	cost.innerText = car.cost;
	convertible.innerText = car.convertible;
	sports.innerText = car.sports;

    // add <td>'s to our <tr>
	
	tr.appendChild(vin);
	tr.appendChild(car_make);
	tr.appendChild(car_model);
	tr.appendChild(year);
	tr.appendChild(color);
	tr.appendChild(cost);
	tr.appendChild(convertible);
	tr.appendChild(sports);

    // add our <tr> to our <tbody>
    document.getElementById('car_table_body').appendChild(tr);
}

// create new car
document.getElementById('form_add_car').addEventListener('submit', function(event) {
    event.preventDefault();		
    // get the data from the form
    var vinOnForm = document.getElementById('vin').value;		
    var car_makeOnForm = document.getElementById('car_make').value;	
    var car_modelOnForm = document.getElementById('car_model').value;		
    var yearOnForm = document.getElementById('year').value;
    var colorOnForm = document.getElementById('color').value;

    	// retrieve the data in the title input box
    var costOnForm = document.getElementById('cost').value;
 //   console.log(document.getElementById('convertible').value);
    if (document.getElementById('convertible').value.checked === 'yes'){
		var convertibleOnForm = True; } 
	else{
		var convertibleOnForm = null; 
		}
	if (document.getElementById('sports').value.checked === 'yes'){
		var sportsOnForm = True; } 
	else{
		var sportsOnForm = null; 
		}
    		

    var car = {
        vin : vinOnForm,
        car_make : car_makeOnForm,
        car_model : car_modelOnForm,
        year : yearOnForm,
        color : colorOnForm,
        cost : costOnForm,
        convertible : Boolean(convertibleOnForm),
        sports: Boolean(sportsOnForm)
    };

    // make AJAX call
    // 1. make an xhr object (ready state is 0)
    var xhr = new XMLHttpRequest();		// make HTTP requests

    // 2. define what happens during the AJAX call
    xhr.onreadystatechange = function() {  
        if(xhr.readyState === 4) {	
            // getting back the updated car object
            var newCar = JSON.parse(xhr.responseText);       
            // adding the updated car to table
            addCarToTable(newCar);
            // reset the form
            document.getElementById('form_add_car').reset();
        }
    }
    // 3. open the xhr call with the http request verb and the url
    xhr.open('POST', '/cars-api/cars');
    // 4. send the ajax call
    xhr.send(JSON.stringify(car));	

});

document.getElementById('form_update_car').addEventListener('submit', function(event){
	    event.preventDefault();		
	
	var vin = document.getElementById('updatevin').value;	
    var color = document.getElementById('updatecolor').value;
    var cost = document.getElementById('updatecost').value;

	var xhrUpdate = new XMLHttpRequest();
	var url = 'http://localhost:8080/cars-api/cars?color=' + color + '&cost=' + cost+ '&vin=' + vin;
	console.log(url);

	xhrUpdate.onreadystatechange = function(){
		if (xhrUpdate.readyState === 4){
			document.getElementById('form_update_car').reset();      
			location.reload();
		}
	}
		xhrUpdate.open('PUT', url);
		xhrUpdate.send();	
});

document.getElementById('form_delete_car').addEventListener('submit', function(event) {
    event.preventDefault();		
    
    // get the data from the form
    var vinOnForm = document.getElementById('delete_car').value;		
    
    console.log(vinOnForm);

    var xhrDelete = new XMLHttpRequest();		// make HTTP requests

    xhrDelete.open('DELETE', '/cars-api/cars?vin=' + vinOnForm );

    // 2. define what happens during the AJAX call
    xhrDelete.onreadystatechange = function() {
        
        if(xhrDelete.readyState === 4) {	
 			console.log(xhrDelete.status);
            console.log(xhrDelete.responseText);
            console.log('Success!');

            // reset the form
            document.getElementById('form_delete_car').reset();
            location.reload();
        }
    }
    // 4. send the ajax call
    xhrDelete.send(JSON.stringify(vinOnForm));	

});






