const StateChangeButton = ({ children, onClick, active }) => {
    let bg_color = active ? "bg-blue-500" : "bg-white dark:bg-gray-700";
    let hover_color = active ? "hover:bg-blue-600" : "hover:bg-blue-100";
     
    return (
        <button className={`grow ${bg_color} ${hover_color} h-full`} onClick={onClick}>
            <h2 className="font-bold text-xl">
                {children}
            </h2>
        </button>
    );
};

export default StateChangeButton;
