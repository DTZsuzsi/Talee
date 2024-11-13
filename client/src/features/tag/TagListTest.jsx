/** @format */

import TagCard from "./TagCard";

const tagCategories = [
	{ id: 1, name: 'company type', color: 'green' },
	{ id: 2, name: 'sporty', color: 'blue' },
	{ id: 3, name: 'culture', color: 'black' },
	{ id: 4, name: 'romantic', color: 'red' },
	{ id: 5, name: 'family', color: 'yellow' },
	{ id: 6, name: 'prices', color: 'grey' },
	{ id: 7, name: 'gastronomy', color: 'brown' },
	{ id: 8, name: 'other', color: 'purple' },
];

//TODO: Remove this after implementing. This is only for testing purposes

const TagListTest = () => {
	return (
		<div>
			<h1>Tag List</h1>
			<ul className="flex flex-wrap">
				{tagCategories &&
					tagCategories.map(tagCategory => (
						<li key={tagCategory.id}>
							<TagCard tag={tagCategory} />
						</li>
					))}
			</ul>
		</div>
	);
};

export default TagListTest;
