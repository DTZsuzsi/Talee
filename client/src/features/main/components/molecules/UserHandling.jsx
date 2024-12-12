/* eslint-disable no-unused-vars */
import axios from 'axios';
import UserCard from '../../../users/components/UserCard';

function UserHandling({setUserChange, event}){
   

async function handleDeleteUser(event, user) {
  setUserChange(false);

  try {
    const response = await axios.delete(`/api/events/user/${event.id}`, {
        params: { userId: user.id },
      });
  
      setUserChange(true);
  } catch (error) {
    console.error('Error deleting user:', error);
  }
}
return( 
<div> 
<h2 className="text-2xl font-semibold mt-10">
    {" "}
    Your friends who are coming:{" "}
  </h2>

  <ul className="flex flex-wrap justify-around">
    {event?.users?.map((user) => (
      <li key={user?.id} className="mx-auto">
        <UserCard
          user={user}
          onClick={() => handleDeleteUser(event, user)}
        />
      </li>
    ))}
  </ul>
  </div>
)

}

export default UserHandling;