/* eslint-disable no-unused-vars */
// import { useEffect, useState } from "react";
// import SelectField from "../../main/components/atoms/SelectField";

// function TagOptions(){
// const [tags, setTags]=useState(null);
// const [tagNames, setTagNames]=useState(null);

// useEffect(()=>{
// async function fetchTagsAndNames(){
//     const response= await fetch("/api/tags");
//     const data= await response.json();
//     setTags(data);
    

// const tagNamesArray=[];
// for (const tag of data){
//     tagNamesArray.push(tag.name);
// }
// setTagNames(tagNamesArray);

// }
// fetchTagsAndNames();

// },[])

// return (<div>
// <SelectField options={tagNames} label={'Choose a tag: '} />

// </div>)


// }
// export default TagOptions;

import { useEffect, useState } from "react";
import SelectField from "../../main/components/atoms/SelectField";

function TagOptions({ onChange }) {
  const [tags, setTags] = useState([]);
  const [tagNames, setTagNames] = useState([]);

  useEffect(() => {
    async function fetchTagsAndNames() {
      try {
        const response = await fetch("/api/tags");
        if (!response.ok) throw new Error("Failed to fetch tags");

        const data = await response.json();
        setTags(data);

        const tagNamesArray = data.map((tag) => tag.name);
        setTagNames(tagNamesArray);
      } catch (error) {
        console.error("Error fetching tags:", error);
      }
    }

    fetchTagsAndNames();
  }, []);

  return (
    <div>
      <SelectField options={tagNames} label={"Choose a tag: "} onChange={onChange} />
    </div>
  );
}

export default TagOptions;
