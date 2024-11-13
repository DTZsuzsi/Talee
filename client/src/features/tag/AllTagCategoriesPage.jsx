import { useState, useEffect } from "react";
import InputField from "../main/components/atoms/InputField";

function AllTagCategoriesPage(){
    const [tagCategories, setTagCategories]=useState(null);
    const[newCategory, setNewCategory]=useState({name:'', color:''})
    const [newAdmitted, setNewAdmitted]=useState(false);

    useEffect(()=>{
     async function fetchTagCategories(){
         const response= await fetch ('api/tagcategories');
         const data= await response.json();
         setTagCategories(data);
         console.log(data);
     }
 
     fetchTagCategories();
    }, [newAdmitted])
    

    async function handleNew(e){
        e.preventDefault();
        setNewAdmitted(false);
        const response = await fetch("api/tagcategories",{
            method: "POST",
            headers: { 'Content-Type': 'application/json' },
      body: JSON.stringify(newCategory),

        })
        const data= await response.json();
        console.log(data);
        setNewAdmitted(true);
    }
     return(
    <div> {tagCategories?.map((category)=> 
 <div key={category.id}> 
 <p style={{color: `${category.color}`}}> {category.name}</p>
 </div>
)}

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