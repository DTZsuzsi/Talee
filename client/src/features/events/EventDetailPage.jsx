/* eslint-disable react/no-unescaped-entities */
import { useEffect, useState } from "react";
import { Link, useNavigate, useParams } from "react-router-dom";
import { HiMiniPencilSquare } from "react-icons/hi2";
import { MdDeleteForever } from "react-icons/md";
import TagOptions from "../tag/components/TagOptions";
import TagCard from "../tag/components/TagCard";


function EventDetailPage(){
const [event, setEvent]=useState(null);
const {eventId}=useParams();
const [tags, setTags]=useState(null);
const [tagChange, setTagChange]=useState(false);


const navigate=useNavigate();

useEffect(()=>{
    async function fetchEvent(){
        if (!eventId) {
            console.error('Question ID is undefined');
            return;
          }
      
        const response=await fetch(`/api/events/${eventId}`);

        if (response.ok) {
            const data = await response.json();
            setEvent(data);
            console.log(data);
        }
       
    }

    async function fetchTags(){
        const response= await fetch("/api/tags");
        const data= await response.json();
        setTags(data);

    }
    fetchEvent();
    fetchTags();
  
},[eventId, tagChange])

async function handleNewTag(id, e) {
    setTagChange(false);
  
    const selectedTagName = e.target.value;
    const tagToSend = findTag(selectedTagName);
  
    const response = await fetch(`/api/events/${id}`, {
      method: "POST",
      headers: { 'Content-Type': 'application/json' },
      body: JSON.stringify(tagToSend),
    });
  
    // eslint-disable-next-line no-unused-vars
    const data = await response.json();
    
    setTagChange(true);
  }

   
  function findTag(tagName) {
    let tagFound = {};
    for (const tag of tags) {
      if (tag.name === tagName) {
        tagFound = tag;
      }
    }
    return tagFound;
  }

  async  function handleDeleteTag(event, tag){
    setTagChange(false);
const response= await fetch(`/api/events/tag/${event.id}?tagId=${tag.id}`, {method: "DELETE"});
const data= await response.json();
console.log(data);
setTagChange(true);
  }
   
    async function deleteEvent(){
    const response=await fetch(`http://localhost:8080/events/${eventId}`, {
        method: 'DELETE'
    });
    const data=await response.json();
    console.log(data);
    navigate("/");
    
    }

    return(
       event && 
       <div className="flex flex-col items-center justify-center h-screen"> 
       <div className='p-1 border-slate-300 shadow-md shadow-slate-800 border-2 rounded-md m-2 w-[50%] min-w-[540px] bg-slate-300 '>
        <img src="https://images.unsplash.com/photo-1513151233558-d860c5398176?q=80&w=3270&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D" className='self-center m-auto rounded-t-md'></img>
       <p className='text-xl font-semibold px-2'>Date: </p>
       <p className='text-xl  px-2 mb-2'> {event.date}</p>
        <p className='text-xl font-semibold px-2'> Event's name: </p>
        <p className='text-xl  px-2 mb-2'> {event.name}</p>
        <p className='text-l font-semibold px-2 mb-2'> {event.description}</p>
        <p className='text-l font-semibold px-2 mb-2'> Event's owner: {event.owner}</p>
        <Link to={`/locations/${event.location.locationId}`}>
        <p className='text-l font-semibold px-2 mb-2'> Event's location: {event.location.name}</p>
        </Link>
        <ul className="flex flex-wrap justify-around">
        {event?.tags?.map((tag) => (
          <li key={tag?.id} className="mx-auto">
            <TagCard tag={tag} onClick={()=>handleDeleteTag(event, tag)} color={tag?.color}/>
          </li> 
        ))}
        </ul>
        <TagOptions onChange={(e) => handleNewTag(event.id, e)} />
        </div>
        <div> 
            <Link to={`/events/${eventId}/modify`}>
        <  HiMiniPencilSquare className="h-10 w-10 text-blue-600 mr-2"/>
        </Link>
        </div>
        <MdDeleteForever className="h-10 w-10 text-blue-600 mr-2" onClick={deleteEvent}/>
        </div>
    )
}
export default EventDetailPage;


  