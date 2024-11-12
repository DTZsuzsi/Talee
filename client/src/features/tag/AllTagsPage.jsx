import { useState, useEffect } from "react";

function AllTagsPage(){
   const [tags, setTags]=useState(null);

   useEffect(()=>{
    async function fetchTags(){
        const response= await fetch ('api/tags');
        const data= await response.json();
        setTags(data);
        console.log(data);
    }

    fetchTags();
   }, [])
   
    return(
   <div> {tags?.map((tag)=> 
<div key={tag.id}> 
<p> {tag.name}</p>


</div>

   )}</div>
   )
    
}

export default AllTagsPage;