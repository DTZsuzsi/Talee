import InputField from "../main/components/atoms/InputField";
import {AdvancedMarker, APIProvider, Map, Marker} from "@vis.gl/react-google-maps";

function GoogleMapComponent({position, setPosition, address, setAddress}){

async function handleSearch(e){
e.preventDefault();
const adressString=address.split(' ').join('+');
    const apiKey=import.meta.env.VITE_GOOGLE_MAPS_API_KEY;
const response= await fetch(`https://maps.googleapis.com/maps/api/geocode/json?address=${adressString}&key=${apiKey}`);
const data=await response.json();
 setPosition(data?.results[0].geometry.location);

}
return (
    <div> 
        <div>
        <form
  onSubmit={(e) => {
   
    handleSearch(e);
  }}
>
        <InputField
  label="address"
  type="text"
  value={address || ""}
  onChange={(e) => {
   
    setAddress(e.target.value);
  }}
/>
    <button type="submit"> Search</button>
    </form>
    </div>

    <APIProvider

      apiKey="AIzaSyCpdQIVDmlFx3hXi3tz6DN59hXWMJEqLOU"
      onLoad={() => console.log('Maps API has loaded.')}
    >
      <div style={{ height: '500px', width: '100%' }}> 
        <Map
          zoom={13}
          center={position}  
        >
			<AdvancedMarker position={position} map={1}/>
			</Map>
      </div>
    </APIProvider>
    </div>
)


}

export default GoogleMapComponent;