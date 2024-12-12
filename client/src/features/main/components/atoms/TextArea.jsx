function TextArea({ title, onChange, className, ...props }) {
  return (
    <div className={className}>
      <label className="block font-medium mb-2">{title}</label>
      <textarea
        rows="4"
        value={location.description}
        onChange={onChange}
        className={`block w-full rounded-md border dark:bg-gray-600`}
        placeholder="Enter a description"
        {...props}
      ></textarea>
    </div>
  );
}

export default TextArea;
