import TaleeButton from "../../main/components/atoms/TaleeButton.jsx";

function DeleteButton() {
  async function deleteEvent(id) {
    const response = await fetch(`/api/events/${id}`, {
      method: "DELETE",
    });
    const data = await response.json();
    console.log(data);
  }
  return (
    <div>
      <TaleeButton
        onClick={(e) => deleteEvent(e.target.value.id)}
      ></TaleeButton>
    </div>
  );
}

export default DeleteButton;
