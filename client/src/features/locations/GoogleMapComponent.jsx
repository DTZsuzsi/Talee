import { useState } from "react";
import InputField from "../main/components/atoms/InputField";
import { Button } from "@headlessui/react";
import { APIProvider, Map, Marker } from "@vis.gl/react-google-maps";

function GoogleMapComponent(){
    const [address, setAdress]=useState(null);
    const [position, setPosition]=useState({lat:0, lng:0});

async function handleSearch(e){
e.preventDefault();
const adressString=address.split(' ').join('+');
const response= await fetch(`https://maps.googleapis.com/maps/api/geocode/json?address=${adressString}&key=AIzaSyCpdQIVDmlFx3hXi3tz6DN59hXWMJEqLOU`);
const data=await response.json();
console.log(data);
 setPosition(data?.results[0].geometry.location);
 console.log(data?.results[0].geometry.location);

}
return (
    <div> 
        <form onSubmit={handleSearch}> 
    <InputField
    label='address'
    type='text'
    value={address || ''}
    onChange={(e)=> setAdress(e.target.value)}
   />
    <Button type="submit"> Search</Button>
    </form>

    <APIProvider 
      apiKey="AIzaSyCpdQIVDmlFx3hXi3tz6DN59hXWMJEqLOU"  
      onLoad={() => console.log('Maps API has loaded.')}
    >
      <div style={{ height: '500px', width: '100%' }}> 
        <Map
          zoom={13}
          center={position}  
        >
			<Marker position={position} />
			</Map>
      </div>
    </APIProvider>
    </div>
)


}

export default GoogleMapComponent;