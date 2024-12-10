

export  function deleteAndAddTag({partName, setter, tags}){


function handleNewTag(e) {
    const selectedTagName = e.target.value;
    const tagToAdd = tags?.find((tag) => tag.name === selectedTagName);

    if (!tagToAdd) {
      console.warn(`Tag with name ${selectedTagName} not found.`);
      return;
    }

    if (partName.tags.some((tag) => tag.id === tagToAdd.id)) {
      console.warn(`Tag with ID ${tagToAdd.id} is already added.`);
      return;
    }

    setter((partName) => ({
      ...partName,
      tags: [...partName.tags, tagToAdd],
    }));
  }

  function handleDeleteTag(tag) {
    setter((partName) => ({
      ...partName,
      tags: partName.tags.filter((t) => t.id !== tag.id),
    }));
  }

return {handleNewTag, handleDeleteTag}}