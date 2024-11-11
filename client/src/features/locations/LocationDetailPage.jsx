import {useEffect, useState} from "react";
import {useParams} from "react-router-dom";

function LocationDetailPage() {
    const [location, setLocation]=useState(null);
    const {locationId}=useParams();

    useEffect(() => {
        async function fetchLocation() {
            if (!locationId) {
                console.error('Location ID is undefined');
                return;
            }

            const response = await fetch(`http://localhost:8080/api/locations/${locationId}`);

            if (response.ok) {
                const data = await response.json();

                setLocation(data);
            }
        }

        fetchLocation();
    }, [locationId])

    return (
        location &&
        <div>
            <p> {location.name} </p>
            <p> Address: {location.address} </p>
            <p> Phone: {location.phone} </p>
            <p> Description: {location.description} </p>
        </div>
    )
}

export default LocationDetailPage;