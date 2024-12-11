import TagCard from "../../../tag/components/TagCard";
import TagOptions from "../../../tag/components/TagOptions";
import { useTagHandlers } from "../../tagHandling/useTagHandlers";



function TagListModify({partName, setter, tags}){
  
    const { handleNewTag, handleDeleteTag } = useTagHandlers(partName, setter, tags);
return (
  <div className='mt-6'>
    <TagOptions onChange={(e) => handleNewTag(e)} />
    <ul className='grid grid-cols-2 md:grid-cols-4 gap-4'>
      {partName.tags.map((tag) => (
        <li key={tag.id}>
          <TagCard tag={tag} onClick={() => handleDeleteTag(tag)} color={tag.color} className='w-full' />
        </li>
      ))}
    </ul>
  </div>)
}

export default TagListModify;