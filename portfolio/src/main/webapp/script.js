// Copyright 2019 Google LLC
//
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
//
//     https://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.

// when the page is reloaded it calls this function
function loadTasks() {
  fetch('/list-quotes').then(response => response.json()).then((tasks) => {
    const taskListElement = document.getElementById('quote-list');
    tasks.forEach((task) => {
      taskListElement.appendChild(createTaskElement(task));
    })
  });
}

/** Creates an element that represents a task, including its delete button. */
function createTaskElement(task) {
    //creating a element for the tasks/comment
  const taskElement = document.createElement('li');
  taskElement.className = 'task';

    // making it into a html span
  const titleElement = document.createElement('span');
  titleElement.innerText = task.title;

    // creating the delete button 
  const deleteButtonElement = document.createElement('button');
  deleteButtonElement.innerText = 'Delete';
  deleteButtonElement.addEventListener('click', () => {
    deleteTask(task);

    // Remove the task from the DOM.
    taskElement.remove();
  });

  taskElement.appendChild(titleElement);
  taskElement.appendChild(deleteButtonElement);
  return taskElement;
}

/** Tells the server to delete the task. */
function deleteTask(task) {
  const params = new URLSearchParams();
  params.append('id', task.id);
  fetch('/delete-quote', {method: 'POST', body: params});
}

// creates the map api
function createMap() {

        // Create a new StyledMapType object, passing it an array of styles,
        // and the name to be displayed on the map type control.
        var styledMapType = new google.maps.StyledMapType(
            [{elementType: 'geometry', stylers: [{color: '#52BE80'}]},
            {elementType: 'labels.text.fill', stylers: [{color: '#F8F9F9'}]},
            {elementType: 'labels.text.stroke', stylers: [{color: '#1C2833'}]},
            {
            featureType: 'administrative',
            elementType: 'geometry.stroke',
            stylers: [{color: '#F9E79F'}]},
            {
            featureType: 'administrative.land_parcel',
            elementType: 'geometry.stroke',
            stylers: [{color: '#A9DFBF'}]},
            {
            featureType: 'administrative.land_parcel',
            elementType: 'labels.text.fill',
            stylers: [{color: '#D5F5E3'}]},
            {
            featureType: 'landscape.natural',
            elementType: 'geometry',
            stylers: [{color: '#A2D9CE'}]},
            {
            featureType: 'poi',
            elementType: 'geometry',
            stylers: [{color: '#A2D9CE'}]},
            {
            featureType: 'poi',
            elementType: 'labels.text.fill',
            stylers: [{color: '#93817c'}]},
            {
            featureType: 'poi.park',
            elementType: 'geometry.fill',
            stylers: [{color: '#a5b076'}]},
            {
            featureType: 'poi.park',
            elementType: 'labels.text.fill',
            stylers: [{color: '#447530'}]},
            {
            featureType: 'road',
            elementType: 'geometry',
            stylers: [{color: '#FCF3CF'}]},
            {
            featureType: 'road.arterial',
            elementType: 'geometry',
            stylers: [{color: '#FDEBD0'}]},
            {
            featureType: 'road.highway',
            elementType: 'geometry',
            stylers: [{color: '#E59866'}]},
            {
            featureType: 'road.highway',
            elementType: 'geometry.stroke',
            stylers: [{color: '#E59866'}]},
            {
            featureType: 'road.highway.controlled_access',
            elementType: 'geometry',
            stylers: [{color: '#e98d58'}]},
            {
            featureType: 'road.highway.controlled_access',
            elementType: 'geometry.stroke',
            stylers: [{color: '#db8555'}]},
            {
            featureType: 'road.local',
            elementType: 'labels.text.fill',
            stylers: [{color: '#FEF9E7'}]},
            {
            featureType: 'transit.line',
            elementType: 'geometry',
            stylers: [{color: '#dfd2ae'}]},
            {
            featureType: 'transit.line',
            elementType: 'labels.text.fill',
            stylers: [{color: '#8f7d77'}]},
            {
            featureType: 'transit.line',
            elementType: 'labels.text.stroke',
            stylers: [{color: '#ebe3cd'}]},
            {
            featureType: 'transit.station',
            elementType: 'geometry',
            stylers: [{color: '#dfd2ae'}]},
            {
            featureType: 'water',
            elementType: 'geometry.fill',
            stylers: [{color: '#D6EAF8'}]},
            {
            featureType: 'water',
            elementType: 'labels.text.fill',
            stylers: [{color: '#D6EAF8'}]}
            ],
            {name: 'Styled Map'});

        // Create a map object, and include the MapTypeId to add
        // to the map type control.
        var map = new google.maps.Map(document.getElementById('map'), {
          center: {lat: 38.744192, lng: -121.288042},
          zoom: 11,
          mapTypeControlOptions: {
            mapTypeIds: ['roadmap', 'satellite', 'hybrid', 'terrain',
                    'styled_map']
          }
        });

        // adding a info box
        var contentString = '<div id="content">'+
            '<div id="siteNotice">'+
            '</div>'+
            '<h1 id="firstHeading" class="firstHeading">Shady\'s Coffee!</h1>'+
            '<div id="bodyContent">'+
            '<p><b>Shadys</b>, also referred to as <b>the best coffee shop</b>, is a small ' +
            'coffee shop in roseville, that is really pretty and an amazing place '+
            'to chill and study '+
            '</div>';

        // adding it to the html
        var infowindow = new google.maps.InfoWindow({
            content: contentString
        });

        //Associate the styled map with the MapTypeId and set it to display.
        map.mapTypes.set('styled_map', styledMapType);
        map.setMapTypeId('styled_map');

        // making the marker for the map
        const trexMarker = new google.maps.Marker({
            position: {lat: 38.744169, lng: -121.287603},
            map: map,
            title: 'Stan the T-Rex'
        });
        // when you click on the marker, then pops up the info window
        trexMarker.addListener('click', function() {
            infowindow.open(map, trexMarker);
        });
      }