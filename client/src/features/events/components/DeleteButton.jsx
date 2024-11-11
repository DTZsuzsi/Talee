
import Button from "../../main/components/atoms/Button";

function DeleteButton(){
   
async function deleteEvent(id){
const response=await fetch(`http://localhost:8080/events/${id}`, {
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