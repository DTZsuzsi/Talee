
import { MdErrorOutline } from 'react-icons/md';

const ServerError = ({ error }) => {
	return (
		<div className='flex items-center'>
			<MdErrorOutline
				className='text-red-500'
				size={24}
			/>
			<span className='text-red-500 ms-1'>{error}</span>
		</div>
	);
};

export default ServerError;
