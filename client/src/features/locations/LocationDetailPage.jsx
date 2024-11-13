import {useEffect, useState} from "react";
import {Link, useNavigate, useParams} from "react-router-dom";
import {HiMiniPencilSquare} from "react-icons/hi2";
import {MdDeleteForever} from "react-icons/md";

function LocationDetailPage() {
    const [location, setLocation]=useState(null);
    const {locationId}=useParams();
    const navigate = useNavigate();

    useEffect(() => {
        async function fetchLocation() {
            if (!locationId) {
                console.error('Location ID is undefined');
                return;
            }

            const response = await fetch(`/api/locations/${locationId}`);

            if (response.ok) {
                const data = await response.json();

                setLocation(data);
            }
        }

        fetchLocation();
    }, [locationId])

    async function deleteLocation() {
        const response = await fetch(`/api/locations/${locationId}`, {
            method: 'DELETE'
        });

        const data = await response.json();
        console.log(data);
        navigate("/");
    }

    return (
        location &&

        <div className="flex flex-col items-center justify-center h-screen">
            <div
                className='p-1 border-slate-300 shadow-md shadow-slate-800 border-2 rounded-md m-2 w-[50%] min-w-[540px] bg-slate-300 '>
                <img
                    src="https://images.unsplash.com/photo-1513151233558-d860c5398176?q=80&w=3270&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D"
                    className='self-center m-auto rounded-t-md'></img>
                <p className='text-xl font-semibold px-2'>Name: </p>
                <p className='text-xl  px-2 mb-2'> {location.name}</p>
                <p className='text-xl font-semibold px-2'> Address: </p>
                <p className='text-xl  px-2 mb-2'> {location.address}</p>
                <p className='text-l font-semibold px-2 mb-2'> {location.description}</p>
                <p className='text-l font-semibold px-2 mb-2'> Phone: {location.phone}</p>

            </div>
            <div>
                <Link to={`/locations/${locationId}/update`}>
                    <  HiMiniPencilSquare className="h-10 w-10 text-blue-600 mr-2"/>

                </Link>
                <MdDeleteForever className="h-10 w-10 text-blue-600 mr-2" onClick={deleteLocation}/>
            </div>

        </div>

)
}

export default LocationDetailPage;