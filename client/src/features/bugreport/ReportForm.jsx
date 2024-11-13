
import { useState } from 'react';

const ReportForm = ({ onClose, type }) => {
	const [report, setReport] = useState({
		title: '',
    description: '',
    reportType: type ? type : 'BUG',
	});
	const handleChange = e => {
		setReport({ ...report, [e.target.name]: e.target.value });
	};
	const handleSubmit = async e => {
		e.preventDefault();
    console.log(report);
    try {
      const response = await fetch('/api/reports', {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify(report),
      });
      if (!response.ok) {
        console.error('Error:', response.status, await response.text());
        return;
      }
      // onClose();
    } catch (error) {
      console.error('Error:', error);
    }
    
	};
	return (
		<form onSubmit={handleSubmit}>
			<input
				type='text'
				placeholder='Title'
				className='border p-2 w-full mb-4'
				name='title'
				value={report.title}
				onChange={handleChange}
			/>
			<textarea
				className='border p-2 w-full mb-4'
				rows='4'
				placeholder='Please describe the issue...'
				name='description'
				value={report.description}
				onChange={handleChange}
			></textarea>
			<div className='flex justify-between mx-2'>
				<button
					type='submit'
					className='px-4 py-2 bg-cyan-600 text-white rounded-md'
				>
					Submit
				</button>
				<button
					onClick={onClose}
					className='px-4 py-2 bg-red-500 text-white rounded-md'
				>
					Close
				</button>
			</div>
		</form>
	);
};

export default ReportForm;
