import { CgProfile } from 'react-icons/cg';
import { FaSearch } from 'react-icons/fa';
import { ImAccessibility } from 'react-icons/im';
import BiggerOnHover from '../atoms/BiggerOnHover';
import Button from '../atoms/Button';
import {useEffect, useState} from "react";
// import jwtDecode from "jwt-decode";
import {Link} from "react-router-dom";
import ProfileDropdown from "../molecules/ProfileDropdown.jsx";


const Header = () => {
	const [isLoggedIn, setIsLoggedIn] = useState(false);

	const checkLogin = () => {
		const token = localStorage.getItem('jwtToken'); // Or retrieve from cookies
		if (!token) return false;
		return true;
		// try {
		// 	const decoded = jwtDecode(token);
		// 	const isExpired = decoded.exp * 1000 < Date.now();
		// 	return !isExpired;
		// 	// eslint-disable-next-line no-unused-vars
		// } catch (error) {
		// 	return false;
		// }
	};

	const handleLogout = () => {
		localStorage.removeItem('jwtToken');
		setIsLoggedIn(false);
	};

	useEffect(() => {
		// Check login status on component mount
		const loginStatus = checkLogin();
		setIsLoggedIn(loginStatus);
	}, []);



	return (
		<nav className='mx-auto'>
			<div className='flex justify-center items-center flex-wrap w-screen'>
				{/* Logo */}
				<BiggerOnHover>
					<a
						href='/'
						className='flex items-center'
					>
						<h1 className='text-3xl text-bold mx-5'>TALEE</h1>
						<ImAccessibility size={25} />
					</a>
				</BiggerOnHover>

				{/* Add new location */}
				<BiggerOnHover>
					<a
						href='/locations/new'
						className='flex items-center'
					>
						<h1 className='text-3xl text-bold mx-5'>Add Location</h1>
					</a>
				</BiggerOnHover>

				

				{/* Search bar */}
				<div className='flex items-center justify-between mx-4 mt-2'>
					<div className='border dark:border-gray-600 rounded-3xl bg-white dark:bg-gray-600 focus-within:outline focus-within:outline-blue-500 '>
						<form
							action=''
							className='flex justify-between size-full'
						>
							{/* Search input */}
							<div className='flex items-center'>
								<div className='p-2 px-2'>
									<FaSearch />
								</div>
								<input
									type='text'
									placeholder='Search...'
									className='focus:outline-none bg-white dark:bg-gray-600'
								/>
							</div>

							{/* Search button */}
							<Button className='rounded-r-3xl'>Search</Button>
							{/* <button className="font-semibold shadow-md text-white p-2  bg-blue-500 hover:bg-blue-600">
                            </button> */}
						</form>
					</div>

					{/* Profile */}
					<div className='mx-4'>
						<BiggerOnHover>
							{isLoggedIn ?
								<ProfileDropdown handleLogout={handleLogout} />
								:
								<Button>
									<Link to="/login">
										Sign Up
									</Link>
								</Button>
							}
						</BiggerOnHover>
					</div>
				</div>
			</div>
		</nav>
	);
};

export default Header;
