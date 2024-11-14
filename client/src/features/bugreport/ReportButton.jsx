/** @format */

import { useState } from 'react';
import { CiMail } from 'react-icons/ci';
import { FaQuestionCircle, FaTimesCircle } from 'react-icons/fa'; // Import the icons from React Icons
import { IoBugOutline } from 'react-icons/io5';
import { MdContentPaste } from 'react-icons/md';
import ReportForm from './ReportForm';

const ReportButton = () => {
	// State to toggle between the ? and X
	const [isClicked, setIsClicked] = useState(false);
  const [showPopup, setShowPopup] = useState(false); // To control showing popup/modal
  const [type, setType] = useState(null);

	const handleButtonClick = () => {
		setIsClicked(!isClicked); // Toggle the button state
	};

	const handleClosePopup = () => {
		setShowPopup(false); // Close the popup/modal
  };
  

	return (
		<div className='fixed bottom-4 right-4'>
			{isClicked && (
				<div className='flex flex-col fixed bottom-16 right-0'>
					<button className='hover:scale-105 hover:bg-slate-900 hover:bg-opacity-60 hover:font-bold hover:text-background ease-in duration-150 flex justify-end ml-auto p-2 px-3 pr-2 m-2 rounded-full'>
						Contact us
						<CiMail size={24} className='mx-2' />
					</button>
          <button
          onClick={() => {
            setShowPopup(true);
            setType('BUG');
          }}
            className='hover:scale-105 hover:bg-slate-900 hover:bg-opacity-60 hover:font-bold hover:text-background ease-in duration-150 flex ml-auto justify-end p-2 px-3 pr-2 m-2 rounded-full'>
            
						Report a bug
						<IoBugOutline size={24} className='mx-2' />
					</button>
          <button
            onClick={() => {
              setShowPopup(true);
              setType('CONTENT');
            }}
            className='hover:scale-105 hover:bg-slate-900 hover:bg-opacity-60 hover:font-bold hover:text-background ease-in duration-150 flex ml-auto justify-end p-2 px-3 pr-2 m-2 rounded-full'>
						Report content issue
						<MdContentPaste size={24} className='mx-2' />
					</button>
				</div>
			)}
			<button
				onClick={handleButtonClick}
				className='flex items-center justify-center p-1 bg-slate-600 text-white rounded-full shadow-lg'
			>
				{/* Conditional rendering of icons using React Icons */}
				{isClicked ? (
					<FaTimesCircle size={24} />
				) : (
					<FaQuestionCircle size={24} />
				)}
			</button>

			{/* Popup Modal */}
			{showPopup && (
				<div className='fixed inset-0 flex justify-center items-center bg-gray-800 bg-opacity-50'>
					<div className='bg-white p-6 rounded-lg shadow-lg w-96'>
						<h2 className='text-xl font-bold mb-4'>Report Issue</h2>
						<p className='mb-4'>Describe your issue here...</p>
						<ReportForm onClose={handleClosePopup} type={type} />
					</div>
				</div>
			)}
		</div>
	);
};

export default ReportButton;
