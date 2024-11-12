import { useState, useEffect } from "react";

function AllTagCategoriesPage(){
    const [tagCategories, setTagCategories]=useState(null);

    useEffect(()=>{
     async function fetchTags(){
         const response= await fetch ('api/tagcategories');
         const data= await response.json();
         setTagCategories(data);
         console.log(data);
     }
 
     fetchTags();
    }, [])
    
     return(
    <div> {tagCategories?.map((category)=> 
 <div key={category.id}> 
 <p style={{color: `${category.color}`}}> {category.name}</p>


 
 
 </div>
 
    )}</div>
    )
}

export default AllTagCategoriesPage;