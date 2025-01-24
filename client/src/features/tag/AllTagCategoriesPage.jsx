import {useState} from "react";
import InputField from "../main/components/atoms/InputField";
import {Link} from "react-router-dom";
import TagCard from "./components/TagCard";
import useFetchCategories from "../main/components/hooks/useFetchCategories.jsx";
import axiosInstance from "../../axiosInstance.jsx";

function AllTagCategoriesPage() {
    const [newCategory, setNewCategory] = useState({name: '', color: ''})
    const {tagCategories} = useFetchCategories()


    async function handleNew(e) {
        e.preventDefault();
        const response = await axiosInstance.post(`/api/tags/${newCategory.id}`, newCategory);

    }

    async function handleCategoryDelete(categoryId) {
        const response = await axiosInstance.delete(`api/tagcategories/${categoryId}`);
    }

    return (
        <div>
            <Link to={"/tags"}>
                <button type='click' className='mt-4 bg-blue-500 text-white py-2 px-4 rounded-lg mb-10'>Go to tags
                </button>
            </Link>


            <ul className='flex flex-wrap justify-around'>
                {tagCategories?.map((category) => (
                    <li key={category.id} className='mx-auto'>
                        <TagCard tag={category} onClick={() => handleCategoryDelete(category.id)}
                                 color={category.color}/>
                    </li>
                ))}
            </ul>


            <div>
                <form onSubmit={handleNew}>
                    <InputField
                        label="Name"
                        type="text"
                        value={newCategory.name}
                        onChange={(e) => setNewCategory({...newCategory, name: e.target.value})}/>
                    <InputField
                        label="Color"
                        type="color"
                        value={newCategory.color}
                        onChange={(e) => setNewCategory({...newCategory, color: e.target.value})}/>
                    <button type="submit" className="mt-4 bg-blue-500 text-white py-2 px-4 rounded-lg">
                        Create new category
                    </button>

                </form>

            </div>

        </div>
    )
}

export default AllTagCategoriesPage;