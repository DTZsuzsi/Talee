import { useEffect, useState } from "react";
import { useParams } from "react-router-dom";

function UserDetailPage() {
    const [user, setUser] = useState(null);
    const {userId} = useParams();

    useEffect(() => {
        async function fetchUser() {
            if (!userId) {

                //nem szerencsés ez az állítás
                console.error('User ID is undefined');
                return;
            }

            //ez is axios
            const response = await fetch(`/api/users/${userId}`);

            if (response.ok) {
                const data = await response.json();
                setUser(data);
            }

            //legyen else, bár axiosnak nem kell
        }

        fetchUser();
    }, [userId]);


    return (
        <>
            <p> {user && user.username} </p>
        </>
    )
}

export default UserDetailPage;