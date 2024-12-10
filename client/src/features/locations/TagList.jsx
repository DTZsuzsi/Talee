import TagCard from "../tag/components/TagCard";

function TagList({ tags }) {
  return (
    <div>
      <h2 className="text-2xl font-semibold">Tags:</h2>
      <ul className="flex flex-wrap gap-2">
        {tags.map((tag) => (
          <li key={tag.id}>
            <TagCard tag={tag} color={tag.color} />
          </li>
        ))}
      </ul>
    </div>
  );
}

export default TagList;