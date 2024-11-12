import { useEffect, useState } from 'react';

const DarkModeToggle = () => {
    const prefersDarkMode = window.matchMedia('(prefers-color-scheme: dark)').matches;

    const [isDarkMode, setIsDarkMode] = useState(
      () => localStorage.getItem('theme') === 'dark' && prefersDarkMode
    );
  
    useEffect(() => {
      const root = window.document.documentElement;
      if (isDarkMode) {
        root.classList.add('dark');
        localStorage.setItem('theme', 'dark');
      } else {
        root.classList.remove('dark');
        localStorage.setItem('theme', 'light');
      }
    }, [isDarkMode]);
  
    const toggleDarkMode = () => {
      setIsDarkMode((prevMode) => !prevMode);
    };
  

    return (
        <button
            onClick={toggleDarkMode}
            className="p-2 rounded bg-gray-300 dark:bg-gray-700 transition-colors duration-300"
        >
            {isDarkMode ? 'Light Mode' : 'Dark Mode'}
        </button>
    );
};

export default DarkModeToggle;
