const UserCard = ({ user }) => {
  return (
    <div className="px-2 py-1 m-2 rounded-lg bg-gray-500 opacity-90 hover:opacity-80 hover:scale-105 relative ring-1 ring-slate-900 shadow-lg ease-in-out duration-200">
      <h1
        className="text-3xl font-semibold text-outline"
        style={{
          textShadow:
            "1px 1px 0 #000, -1px -1px 0 #000, 1px -1px 0 #000, -1px 1px 0 #000",
          transform: "scale(0.60)",
        }}
      >
        {user?.username || "hi"}
      </h1>
    </div>
  );
};

export default UserCard;
