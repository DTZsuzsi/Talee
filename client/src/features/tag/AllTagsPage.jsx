import { useState, useEffect } from "react";
import InputField from "../main/components/atoms/InputField";
import SelectField from "../main/components/atoms/SelectField";
import TagCard from "./components/TagCard";

function AllTagsPage(){
   const [tags, setTags]=useState(null);
   const [newAdmitted, setNewAdmitted]=useState(false);
   const [newTag, setNewTag]=useState({name: '', category: 1});
   const [categories, setCategories]=useState(null);
   const [categoryNames, setCategoryNames]=useState(null);


   useEffect(()=>{
    async function fetchTags(){
        const response= await fetch ('api/tags');
        const data= await response.json();
        setTags(data);
        console.log(data);
    }

    async function fetchTagCategories(){
        const response= await fetch ('api/tagcategories');
        const data= await response.json();
        setCategories(data);
        const categoryNames=[];
for (const category of data){
    categoryNames.push(category.name);
    console.log(categoryNames);
    setCategoryNames(categoryNames);
}


        console.log(data);
    }

    fetchTags();
    fetchTagCategories();
   }, [newAdmitted])
   
   async function handleNew(e){
    e.preventDefault();
    console.log('hi');
    setNewAdmitted(false);
    const id=findCategoryId(newTag.category);
    console.log(id);
    
    const response = await fetch("api/tags",{
        method: "POST",
        headers: { 'Content-Type': 'application/json' },
  body: JSON.stringify({...newTag, category:id}),

    })
    const data= await response.json();
    console.log(data);
    setNewAdmitted(true);
}

function findCategoryId(categorySearch){
console.log(categorySearch);
    let id=0;
    for (const category of categories){
        if (category.name===categorySearch)
            id=category.id;
    }
    return id;

}
    return(
   <div> {tags?.map((tag)=> 
<div key={tag.id}> 
<TagCard tag={tag}/>


</div>



   )}
   
   <div> 
<form onSubmit={handleNew}>
  <InputField
  label="Name"
  type="text"
  value={newTag.name}
  onChange={(e) => setNewTag({ ...newTag, name: e.target.value })}/>
   <SelectField
            label="category"
            options={categoryNames
            }
            value={newTag.category || 'other'}
            onChange={(e) => setNewTag({ ...newTag, category: e.target.value })}
          />
   <button type="submit" className="mt-4 bg-blue-500 text-white py-2 px-4 rounded-lg">
            Create new category
          </button>

</form>

</div>
   </div>
   )
    
}

export default AllTagsPage;