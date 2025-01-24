import {useState, useEffect} from 'react';
import InputField from '../main/components/atoms/InputField';
import SelectField from '../main/components/atoms/SelectField';
import TagCard from './components/TagCard';
import {Link} from 'react-router-dom';

function AllTagsPage() {
    const [tags, setTags] = useState(null);
    const [tagChange, setTagChange] = useState(false);
    const [newTag, setNewTag] = useState({name: '', category: 1});
    const [categories, setCategories] = useState(null);
    const [categoryNames, setCategoryNames] = useState(null);

    useEffect(() => {
        async function fetchTags() {
            const response = await fetch('api/tags');
            const data = await response.json();
            setTags(data);
            console.log(data);
        }

        async function fetchTagCategories() {
            const response = await fetch('api/tagcategories');
            const data = await response.json();
            setCategories(data);
            const categoryNames = [];
            for (const category of data) {
                categoryNames.push(category.name);
                setCategoryNames(categoryNames);
            }

            console.log(data);
        }

        fetchTags();
        fetchTagCategories();
    }, [tagChange]);

    async function handleNew(e) {
        e.preventDefault();
        console.log('hi');
        setTagChange(false);
        const id = findCategoryId(newTag.category);
        console.log(id);

        const response = await fetch('api/tags', {
            method: 'POST',
            headers: {'Content-Type': 'application/json'},
            body: JSON.stringify({...newTag, category: id}),
        });
        const data = await response.json();
        console.log(data);
        setTagChange(true);
    }

    function findCategoryId(categorySearch) {
        console.log(categorySearch);
        let id = 0;
        for (const category of categories) {
            if (category.name === categorySearch) id = category.id;
        }
        return id;
    }

    async function handleDelete(tagId) {
        setTagChange(false);
        const response = await fetch(`/api/tags/${tagId}`, {method: 'DELETE'});
        const data = await response.json();
        console.log(data);
        setTagChange(true);
    }

    return (

        <div>
            <Link to={"/tagcategories"}>
                <button type='click' className='mt-4 bg-blue-500 text-white py-2 px-4 rounded-lg mb-10'>Go to
                    categories
                </button>
            </Link>
            <div>
                <form onSubmit={handleNew}>
                    <InputField label='Name' type='text' value={newTag.name}
                                onChange={(e) => setNewTag({...newTag, name: e.target.value})}/>
                    <SelectField
                        label='category'
                        options={categoryNames}
                        value={newTag.category || 'other'}
                        onChange={(e) => setNewTag({...newTag, category: e.target.value})}
                    />
                    <button type='submit' className='mt-4 bg-blue-500 text-white py-2 px-4 rounded-lg mb-10'>
                        Create new category
                    </button>
                </form>
            </div>
            {tags?.map((tag) => (
                <div key={tag.id}>
                    <ul className='flex flex-wrap justify-around'>
                        {tags?.map((tag) => (
                            <li key={tag.id} className='mx-auto'>
                                <TagCard tag={tag} onClick={() => handleDelete(tag.id)} color={tag.color}/>
                            </li>
                        ))}
                    </ul>
                </div>
            ))}

        </div>
    );
}

export default AllTagsPage;
