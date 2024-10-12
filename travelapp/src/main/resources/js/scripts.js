document.getElementById('itineraryForm').addEventListener('submit', function(event) {
   event.preventDefault();

   const destination = document.getElementById('destination').value;
   const duration = document.getElementById('duration').value;
   const preference = document.getElementById('preference').value;

   const itineraryData = {
       id: destination,
       destination: destination,
       duration: duration,
       preference: preference,
       generateditineraries: destination
   };

   fetch('http://localhost:8080/api/itinerary/generate_itinerary', {
       method: 'POST',
       headers: {
           'Content-Type': 'application/json'
       },
       body: JSON.stringify(itineraryData)
   })
   .then(response => response.json())
   .then(data => {
       document.getElementById('result').innerHTML = `
           <h2>Your Itinerary</h2>
           <p>Destination: ${data.destination}</p>
           <p>Duration: ${data.duration} days</p>
           <p>Preference: ${data.preference} </p>
           <p>Itinerary Details: ${data.generateditineraries}</p>
       `;
   })
   .catch(error => console.error('Error:', error));
});


<!--Second Form -->
document.getElementById('otherButton').addEventListener('click', function(event) {

   event.preventDefault();

    fetch('http://localhost:8080/api/itinerary/all', {
       method: 'GET',
       headers: {
           'Content-Type': 'application/json'
       }

   })
   .then(response => response.json())
   .then(data => {
      if (Array.isArray(data)) {
       data.forEach(item => {
           // Handle each itinerary item
           document.getElementById('result').innerHTML += `
               <h2>Your Itinerary</h2>
               <p>Destination: ${item.destination}</p>
               <p>Duration: ${item.duration} days</p>
               <p>Preference: ${item.preference} </p>
               <p>Itinerary Details: ${item.generateditineraries}</p>
           `;
       });
   } else {
       // Handle the case where data is not an array
       document.getElementById('result1').innerHTML = `
           <h2>Your Itinerary</h2>
           <p>Destination: ${data.destination}</p>
           <p>Duration: ${data.duration} days</p>
           <p>Preference: ${data.preference} </p>
           <p>Itinerary Details: ${data.generateditineraries}</p>
       `;
   }
   })
   .catch(error => console.error('Error:', error));
});

