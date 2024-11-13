/** @format */

const TagCard = ({ tag }) => {
	return (
		<div
			style={{ backgroundColor: `${tag.color}` }}
			className='px-2 py-1 m-2 rounded-lg opacity-90 hover:opacity-70 relative'
		>
			<button className="opacity-100 absolute -top-1 -right-1 bg-red-600 rounded-full w-5 h-5 font-bold text-xs flex justify-center items-center text-white hover:bg-red-900">
				X
			</button>
			<h1
				className='text-3xl text-white font-semibold text-outline '
				style={{
					textShadow:
						'1px 1px 0 #000, -1px -1px 0 #000, 1px -1px 0 #000, -1px 1px 0 #000',
					transform: 'scale(0.60)',
				}}
			>
				{tag.name}
			</h1>
		</div>
	);
};

export default TagCard;
