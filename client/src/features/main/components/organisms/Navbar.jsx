// import {
//     Disclosure,
//     DisclosureButton,
//     DisclosurePanel,
//     Menu,
//     MenuButton,
//     MenuItem,
//     MenuItems,
// } from "@headlessui/react";
// import { Bars3Icon, XMarkIcon } from "@heroicons/react/24/outline";
// import TaleeLogo from "../atoms/TaleeLogo.jsx";
// import BiggerOnHover from "../atoms/BiggerOnHover.jsx";
// import React, { useEffect, useState } from "react";
// import Button from "../atoms/Button.jsx";
// import { Link } from "react-router-dom";
// import { CgProfile } from "react-icons/cg";

// const navigation = [
//     // { name: 'Add Event', href: '#', current: true },
//     { name: "Add Location", href: "/locations/new", current: false },
//     // { name: 'Projects', href: '#', current: false },
//     // { name: 'Calendar', href: '#', current: false },
// ];

// function classNames(...classes) {
//     return classes.filter(Boolean).join(" ");
// }

// export default function Navbar() {
//     const [isLoggedIn, setIsLoggedIn] = useState(false);
//     const username = localStorage.getItem("userName");

//     const checkLogin = () => {
//         return localStorage.getItem("jwtToken");
//     };

//     const handleLogout = () => {
//         localStorage.removeItem("jwtToken");
//         localStorage.removeItem("userName");
//         setIsLoggedIn(false);
//     };

//     useEffect(() => {
//         setIsLoggedIn(checkLogin());
//     }, []);

//     return (
//     <Disclosure as="nav" className="">
//       <div className="mx-auto max-w-7xl px-2 sm:px-6 lg:px-8">
//         <div className="relative flex h-16 items-center justify-between">
//           <div className="absolute inset-y-0 left-0 flex items-center sm:hidden">
//             {/* Mobile menu button*/}
//             <DisclosureButton className="group relative inline-flex items-center justify-center rounded-md p-2 text-gray-400 hover:bg-gray-700 hover:text-white focus:outline-none focus:ring-2 focus:ring-inset focus:ring-white">
//               <span className="absolute -inset-0.5" />
//               <span className="sr-only">Open main menu</span>
//               <Bars3Icon
//                 aria-hidden="true"
//                 className="block size-6 group-data-[open]:hidden"
//               />
//               <XMarkIcon
//                 aria-hidden="true"
//                 className="hidden size-6 group-data-[open]:block"
//               />
//             </DisclosureButton>
//           </div>
//           <div className="flex flex-1 items-center justify-center sm:items-stretch sm:justify-start">
//             <div className="flex shrink-0 items-center">
//               {/* Logo */}
//               <BiggerOnHover>
//                 <a href="/" className="flex items-center">
//                   <h1 className="text-3xl text-bold mx-1">TALEE</h1>
//                   <TaleeLogo />
//                 </a>
//               </BiggerOnHover>
//             </div>
//             <div className="hidden sm:ml-6 sm:block">
//               <div className="flex space-x-4">
//                 {navigation.map((item) => (
//                   <a
//                     key={item.name}
//                     href={item.href}
//                     aria-current={item.current ? "page" : undefined}
//                     className={classNames(
//                       item.current
//                         ? "bg-gray-900 text-white"
//                         : "hover:text-white",
//                       "rounded-md px-3 py-2 text-sm font-medium",
//                     )}
//                   >
//                     {item.name}
//                   </a>
//                 ))}
//               </div>
//             </div>
                
     

//         <DisclosurePanel className="sm:hidden">
//             <div className="space-y-1 px-2 pb-3 pt-2">
//                 {navigation.map((item) => (
//                     <DisclosureButton
//                         key={item.name}
//                         as="a"
//                         href={item.href}
//                         aria-current={item.current ? "page" : undefined}
//                         className={classNames(
//                             item.current
//                                 ? "bg-gray-900 text-white"
//                                 : "text-gray-300 hover:bg-gray-700 hover:text-white",
//                             "block rounded-md px-3 py-2 text-base font-medium",
//                         )}
//                     >
//                         {item.name}
//                     </DisclosureButton>
//                 ))}
//             </div>
//         </DisclosurePanel>
//     </Disclosure>
//   );
// }

import {
  Disclosure,
  DisclosureButton,
  DisclosurePanel,
} from "@headlessui/react";
import { Bars3Icon, XMarkIcon } from "@heroicons/react/24/outline";
import TaleeLogo from "../atoms/TaleeLogo.jsx";
import BiggerOnHover from "../atoms/BiggerOnHover.jsx";
import { useEffect, useState } from "react";
import { Link } from "react-router-dom";

const navigation = [
  { name: "Add Location", href: "/locations/new", current: false },
];

function classNames(...classes) {
  return classes.filter(Boolean).join(" ");
}

export default function Navbar() {
  const [isLoggedIn, setIsLoggedIn] = useState(false);
  const username = localStorage.getItem("userName");

  const checkLogin = () => {
    return localStorage.getItem("jwtToken");
  };

  const handleLogout = () => {
    localStorage.removeItem("jwtToken");
    localStorage.removeItem("userName");
    setIsLoggedIn(false);
  };

  useEffect(() => {
    setIsLoggedIn(checkLogin());
  }, []);

  return (
    <Disclosure as="nav" className="bg-gray-800">
      <div className="mx-auto max-w-7xl px-2 sm:px-6 lg:px-8">
        <div className="relative flex h-16 items-center justify-between">
          {/* Mobile Menu Button */}
          <div className="absolute inset-y-0 left-0 flex items-center sm:hidden">
            <DisclosureButton className="inline-flex items-center justify-center rounded-md p-2 text-gray-400 hover:bg-gray-700 hover:text-white focus:outline-none focus:ring-2 focus:ring-inset focus:ring-white">
              <span className="sr-only">Open main menu</span>
              <Bars3Icon
                className="block h-6 w-6 group-data-[open]:hidden"
                aria-hidden="true"
              />
              <XMarkIcon
                className="hidden h-6 w-6 group-data-[open]:block"
                aria-hidden="true"
              />
            </DisclosureButton>
          </div>

          {/* Logo */}
          <div className="flex flex-1 items-center justify-center sm:items-stretch sm:justify-start">
            <div className="flex shrink-0 items-center">
              <BiggerOnHover>
                <Link to="/" className="flex items-center">
                  <h1 className="text-3xl font-bold mx-1 text-white">TALEE</h1>
                  <TaleeLogo />
                </Link>
              </BiggerOnHover>
            </div>

            {/* Navigation Links */}
            <div className="hidden sm:ml-6 sm:block">
              <div className="flex space-x-4">
                {navigation.map((item) => (
                  <Link
                    key={item.name}
                    to={item.href}
                    className={classNames(
                      item.current
                        ? "bg-gray-900 text-white"
                        : "text-gray-300 hover:bg-gray-700 hover:text-white",
                      "rounded-md px-3 py-2 text-sm font-medium"
                    )}
                  >
                    {item.name}
                  </Link>
                ))}
              </div>
            </div>
          </div>

          {/* Profile Section */}
          <div className="absolute inset-y-0 right-0 flex items-center pr-2 sm:static sm:inset-auto sm:ml-6 sm:pr-0">
            {isLoggedIn ? (
              <button
                onClick={handleLogout}
                className="rounded-md bg-gray-900 px-3 py-2 text-sm font-medium text-white hover:bg-gray-700"
              >
                Logout ({username})
              </button>
            ) : (
              <Link
                to="/login"
                className="rounded-md bg-gray-900 px-3 py-2 text-sm font-medium text-white hover:bg-gray-700"
              >
                Login
              </Link>
            )}
          </div>
        </div>
      </div>

      {/* Mobile Menu */}
      <DisclosurePanel className="sm:hidden">
        <div className="space-y-1 px-2 pb-3 pt-2">
          {navigation.map((item) => (
            <DisclosureButton
              key={item.name}
              as={Link}
              to={item.href}
              className={classNames(
                item.current
                  ? "bg-gray-900 text-white"
                  : "text-gray-300 hover:bg-gray-700 hover:text-white",
                "block rounded-md px-3 py-2 text-base font-medium"
              )}
            >
              {item.name}
            </DisclosureButton>
          ))}
        </div>
      </DisclosurePanel>
    </Disclosure>
  );
}
