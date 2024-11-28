const TagCard = ({ tag, onClick, color }) => {
  // Determine font size based on text length
  const fontSize =
    tag?.name?.length > 10
      ? "text-sm"
      : tag?.name?.length > 6
        ? "text-lg"
        : "text-xl";

  return (
    <div
      style={{ background: `${color || "blue"}` }}
      className="flex items-center justify-center w-40 h-14 px-2 py-1 m-2 rounded-lg opacity-90 hover:opacity-80 hover:scale-105 relative ring-1 ring-slate-900 shadow-lg ease-in-out duration-200"
    >
      {/* Conditionally Render Delete Button */}
      {onClick && (
        <button
          onClick={onClick}
          className="opacity-100 absolute -top-1 -right-1 bg-red-600 rounded-full w-5 h-5 font-bold text-xs flex justify-center items-center text-white hover:bg-red-900"
        >
          X
        </button>
      )}

      {/* Tag Name */}
      <h1
        className={`text-white font-semibold truncate ${fontSize}`}
        style={{
          textShadow:
            "1px 1px 0 #000, -1px -1px 0 #000, 1px -1px 0 #000, -1px 1px 0 #000",
        }}
      >
        {tag?.name || "hi"}
      </h1>
    </div>
  );
};

export default TagCard;
