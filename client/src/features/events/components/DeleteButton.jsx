
import Button from "../../main/components/atoms/Button";

function DeleteButton(){
   
async function deleteEvent(id){
const response=await fetch(`/api/events/${id}`, {
    method: 'DELETE'
});
const data=await response.json();
console.log(data);

}
return(
    <div>
        <Button onClick={(e)=> deleteEvent(e.target.value.id)}></Button>
    </div>
)

}

export default DeleteButton;