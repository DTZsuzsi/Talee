const SelectField = ({ label, options, value, onChange, id }) => (
    <div className="mb-4 flex flex-col max-w-lg">
        {label && (
            <label htmlFor={id} className="block text-gray-700 font-medium mb-2 text-left">
                {label}
            </label>
        )}
        <select
            id={id}
            value={value}
            onChange={onChange}
            className="border border-gray-300 rounded-md px-4 py-2"
        >
            {options.map((option, index) => (
                <option key={index} value={option}>
                    {option}
                </option>
            ))}
        </select>
    </div>
);

export default SelectField;
