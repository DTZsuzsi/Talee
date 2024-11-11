import { useEffect, useState } from 'react';

const DarkModeToggle = () => {
    const [darkMode, setDarkMode] = useState(false);

    const toggleDarkMode = () => {
        setDarkMode(!darkMode);
    };

    // Effect to apply the dark mode class to the body
    useEffect(() => {
        if (darkMode) {
            document.documentElement.classList.add('dark');
        } else {
            document.documentElement.classList.remove('dark');
        }
    }, [darkMode]);

    return (
        <button
            onClick={toggleDarkMode}
            className="p-2 rounded bg-gray-300 dark:bg-gray-700 transition-colors duration-300"
        >
            {darkMode ? 'Light Mode' : 'Dark Mode'}
        </button>
    );
};

export default DarkModeToggle;
