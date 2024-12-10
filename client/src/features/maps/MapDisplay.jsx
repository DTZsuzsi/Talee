import { APIProvider, Map, Marker } from "@vis.gl/react-google-maps";

function MapDisplay({  lat, lng }) {
  const apiKey=import.meta.env.VITE_GOOGLE_MAPS_API_KEY;
  console.log(apiKey);
  
    return (
    <div className="my-6">
      <APIProvider apiKey={apiKey}>
        <Map
          defaultZoom={13}
          defaultCenter={{ lat, lng }}
          style={{ height: "300px", width: "100%" }}
        >
          <Marker position={{ lat, lng }} />
        </Map>
      </APIProvider>
    </div>
  );
}

export default MapDisplay;