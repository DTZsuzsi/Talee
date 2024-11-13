import BiggerOnHover from "./BiggerOnHover";

// eslint-disable-next-line react/prop-types
const Button = ({ children, onClick, className = "", bgColor = "bg-blue-500", textColor = "text-white", ...props }) => (
    <BiggerOnHover>
        <button
            onClick={onClick?.()}
            className={`inline-block font-semibold py-2 px-4 rounded-lg shadow-md transition-transform ${bgColor} hover:bg-blue-600 ${textColor} ${className}`}
            {...props}
        >
            {children}
        </button>
    </BiggerOnHover>
);

export default Button;
