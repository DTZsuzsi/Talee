

export function TagHandlers(partName, setter, tags ) {
    function handleNewTag(e) {
        const selectedTagName = e.target.value;
      const tagToAdd = tags?.find((tag) => tag.name === selectedTagName);
  
      if (!tagToAdd) {
        console.warn(`Tag with name "${selectedTagName}" not found.`);
        return;
      }
  
      if (partName.tags.some((tag) => tag.id === tagToAdd.id)) {
        console.warn(`Tag with ID "${tagToAdd.id}" is already added.`);
        return;
      }
  
      setter((prevPartName) => ({
        ...prevPartName,
        tags: [...prevPartName.tags, tagToAdd],
      }));
    }
  
    function handleDeleteTag(tag) {
      setter((prevPartName) => ({
        ...prevPartName,
        tags: prevPartName.tags.filter((t) => t.id !== tag.id),
      }));
    }
  
    return { handleNewTag, handleDeleteTag };
  }
  