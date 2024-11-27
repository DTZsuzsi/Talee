import React, { useRef, useState, useEffect } from 'react';
import { CgProfile } from 'react-icons/cg';

const ProfileDropdown = ({ handleLogout }) => {
    const [isDropdownVisible, setDropdownVisible] = useState(false);
    const dropdownRef = useRef(null); // Ref for the dropdown menu
    const buttonRef = useRef(null);   // Ref for the toggle button

    // Toggle the dropdown visibility
    const toggleDropdown = () => {
        setDropdownVisible(!isDropdownVisible);
    };

    // Close dropdown when clicking outside
    useEffect(() => {
        const handleClickOutside = (event) => {
            if (
                dropdownRef.current &&
                !dropdownRef.current.contains(event.target) &&
                buttonRef.current &&
                !buttonRef.current.contains(event.target)
            ) {
                setDropdownVisible(false);
            }
        };

        document.addEventListener('click', handleClickOutside);
        return () => {
            document.removeEventListener('click', handleClickOutside);
        };
    }, []);

    return (
        <div className="relative">
            <button
                ref={buttonRef}
                type="button"
                id="user-menu-button"
                aria-expanded={isDropdownVisible}
                aria-haspopup="true"
                onClick={toggleDropdown}
                className="hover:bg-gray-200 rounded-xl"
            >
                <span className="sr-only">Open user menu</span>
                <CgProfile size={36} />
            </button>

            {/* Dropdown menu */}
            <div
                ref={dropdownRef}
                id="user-menu-dropdown"
                className={`absolute right-0 z-10 mt-2 w-48 origin-top-right rounded-md bg-white py-1 shadow-lg ring-1 ring-black ring-opacity-5 transition-opacity duration-300 ease-in-out transform ${
                    isDropdownVisible ? 'opacity-100 scale-100' : 'opacity-0 scale-95 hidden'
                }`}
                role="menu"
                aria-orientation="vertical"
                aria-labelledby="user-menu-button"
            >
                {/* Username section */}
                <div className="block px-4 py-2 text-base font-semibold text-gray-900">
                    {/* Replace with dynamic username */}
                    Username
                </div>
                <a
                    href="#"
                    className="block px-4 py-2 text-base text-black hover:bg-gray-500"
                    role="menuitem"
                >
                    Termékeim
                </a>
                <a
                    href="#"
                    className="block px-4 py-2 text-base text-black hover:bg-gray-500"
                    role="menuitem"
                >
                    Profilom
                </a>
                <button
                    onClick={handleLogout}
                    className="block px-4 py-2 text-base text-black hover:bg-gray-500"
                    role="menuitem"
                >
                    Kijelentkezés
                </button>
            </div>
        </div>
    );
};

export default ProfileDropdown;
