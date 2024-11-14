// I added text shadows around text to make it more readable when bg color is bright.
//I could not use 0.5px values so instead I have set the fonts to a bigger size and scaled it down with a transform
const TagCard = ({ tag }) => {
	const handleRemove = () => {
		//TODO create the function that validates and removes a tag. If you want to refresh the tags too you probably want to 
		//pass a function as a prop to handle that as well
		console.log('remove');
	};
	return (
		<div
			style={{ backgroundColor: `${tag.color}` }}
			className='px-2 py-1 m-2 rounded-lg opacity-90 hover:opacity-80 hover:scale-105 relative ring-1 ring-slate-900 shadow-lg ease-in-out duration-200'
		>
			<button
				onClick={handleRemove}
				className="opacity-100 absolute -top-1 -right-1 bg-red-600 rounded-full w-5 h-5 font-bold text-xs flex justify-center items-center text-white hover:bg-red-900">
				X
			</button>
			<h1
				className='text-3xl text-white font-semibold text-outline'
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
