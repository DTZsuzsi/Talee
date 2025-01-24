
import { useState, useEffect } from "react";
import InputField from "../main/components/atoms/InputField";
import { Link } from "react-router-dom";
import TagCard from "./components/TagCard";

function AllTagCategoriesPage(){
    const [tagCategories, setTagCategories]=useState(null);
    const[newCategory, setNewCategory]=useState({name:'', color:''})
    const [change, setChange]=useState(false);

    useEffect(()=>{
     async function fetchTagCategories(){
         const response= await fetch ('api/tagcategories');
         const data= await response.json();
         setTagCategories(data);
         console.log(data);
     }
 
     fetchTagCategories();
    }, [change])
    

    async function handleNew(e){
        e.preventDefault();
        setChange(false);
        const response = await fetch("api/tagcategories",{
            method: "POST",
            headers: { 'Content-Type': 'application/json' },
      body: JSON.stringify(newCategory),

        })
        const data= await response.json();
        console.log(data);

        //fetchTags()-t hívjuk meg újra
        setChange(true);
    }

    async function handleCategoryDelete(categoryId) {
        setChange(false);
        const response = await fetch(`/api/tagcategories/${categoryId}`, { method: 'DELETE' });
        const data = await response.json();
        console.log(data);
        setChange(true);
      }
     return(
    <div> 
         <Link to={"/tags"}>
           <button type='click' className='mt-4 bg-blue-500 text-white py-2 px-4 rounded-lg mb-10'>Go to tags</button>
           </Link>
        
        {/* {tagCategories?.map((category)=> 
 <div key={category.id}> 

 
 <TagCard tag={category} onClick={()=>handleCategoryDelete(category.id)} color={category.color}/>
 </div>
)} */}

 <ul className='flex flex-wrap justify-around'>
            {tagCategories?.map((category) => (
              <li key={category.id} className='mx-auto'>
                <TagCard tag={category} onClick={() => handleCategoryDelete(category.id)} color={category.color} />
              </li>
            ))}
          </ul>
        
      

<div> 
<form onSubmit={handleNew}>
  <InputField
  label="Name"
  type="text"
  value={newCategory.name}
  onChange={(e) => setNewCategory({ ...newCategory, name: e.target.value })}/>
  <InputField
  label="Color"
  type="color"
  value={newCategory.color}
  onChange={(e) => setNewCategory({ ...newCategory, color: e.target.value })}/>
   <button type="submit" className="mt-4 bg-blue-500 text-white py-2 px-4 rounded-lg">
            Create new category
          </button>

</form>

</div>

</div>
    )
}

export default AllTagCategoriesPage;