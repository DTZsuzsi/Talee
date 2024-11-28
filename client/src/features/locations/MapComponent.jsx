// import React, { useEffect, useState } from 'react';
// import { GoogleMap, LoadScript, Marker } from '@vis.gl/react-google-maps';

// const position = { lat: 37.7749, lng: -122.4194 }; // Example coordinates (San Francisco)

// const MapComponent = () => {
//   const [isLoaded, setIsLoaded] = useState(false);

//   useEffect(() => {
//     // Check if the window.google object is available
//     if (window.google) {
//       setIsLoaded(true);
//     }
//   }, []);

//   return (
//     <LoadScript googleMapsApiKey={process.env.REACT_APP_GOOGLE_MAPS_API_KEY}>
//       {isLoaded ? (
//         <GoogleMap
//           center={position}
//           zoom={10}
//           mapContainerStyle={{
//             width: '100%',
//             height: '400px',
//           }}
//         >
//           <Marker position={position} />
//         </GoogleMap>
//       ) : (
//         <div>Loading Map...</div>
//       )}
//     </LoadScript>
//   );
// };

// export default MapComponent;
