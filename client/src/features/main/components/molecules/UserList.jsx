import UserCard from "../../../users/components/UserCard.jsx";

function UserList({ event, onDeleteUser }) {
  return (
    <div className="mt-5">
      <h2 className="text-2xl font-semibold"> People who are coming: </h2>

      <ul className="grid grid-cols-1 sm:grid-cols-2 md:grid-cols-3 lg:grid-cols-4 gap-4">
        {event?.users?.map((user) => (
          <li key={user?.id} className="mx-auto">
            <UserCard onDeleteUser={onDeleteUser} user={user} />
          </li>
        ))}
      </ul>
    </div>
  );
}

export default UserList;
