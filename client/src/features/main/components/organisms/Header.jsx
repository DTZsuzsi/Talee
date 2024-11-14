import { CgProfile } from 'react-icons/cg';
import { FaSearch } from 'react-icons/fa';
import { ImAccessibility } from 'react-icons/im';
import BiggerOnHover from '../atoms/BiggerOnHover';
import Button from '../atoms/Button';

const Header = () => {
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

				<BiggerOnHover>
					<a
						href='/events/new'
						className='flex items-center'
					>
						<h1 className='text-3xl text-bold mx-5'>Add Event</h1>
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
							<a
								href='/profile'
								className='shadow-md p2'
							>
								<CgProfile
									size={36}
									className='hover:bg-gray-200 rounded-xl'
								/>
							</a>
						</BiggerOnHover>
					</div>
				</div>
			</div>
		</nav>
	);
};

export default Header;
