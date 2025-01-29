/* eslint-disable no-unused-vars */
import {useEffect, useState} from "react";
import HomeCard from "../molecules/HomeCard.jsx";
import StateChangeButton from "../molecules/StateChangeButton.jsx";
import TagCard from "../../../tag/components/TagCard.jsx";
import {useTheme} from "../../ThemeContext.jsx";
import {useFetchLocations} from "../hooks/useFetchLocations.jsx";
import {useFetchEvents} from "../hooks/useFetchEvents.jsx";
import SelectField from "../atoms/SelectField.jsx";
import useFetchTags from "../hooks/useFetchTags.jsx";
import axiosInstance from "../../../../axiosInstance.jsx";

const Home = () => {
    const {darkMode, setDarkMode} = useTheme();

    const [mode, setMode] = useState(darkMode ? "events" : "locations");
    const {locations, setLocations} = useFetchLocations();
    const {events, setEvents} = useFetchEvents();
    const {tags} = useFetchTags();

    const [chosenTag, setChosenTag] = useState("big");
    const [tagChanged, setTagChanged] = useState(false);

    const handleModeChange = (newMode) => {
        setMode(newMode);
        setDarkMode(newMode === "events");
    };

    const tagNameArray = tags?.map(tag => tag.name);

    async function filterTag(e) {
        setTagChanged(false);
        setChosenTag(e.target.value);
        const response = await axiosInstance.get(`/events/tagsfilter/${e.target.value}`);
        setEvents(response.data);
        const responseLoc = await axiosInstance.get(`/locations/tagsfilter/${e.target.value}`);
        setLocations(responseLoc.data);

    }

    const renderEventCards = () => {
        if (!events || events.length === 0) return <p>No events available</p>;
        return events.map((event) => (
            <HomeCard
                key={event.id}
                title={event.name}
                href={`/events/${event.id}`}
                description={event.description}
                date={event.date}
            />
        ));
    };

    const renderLocationCards = () => {
        if (!locations || locations.length === 0)
            return <p>No locations available</p>;
        return locations.map((location) => (
            <div key={location.id} className="mb-6">
                <HomeCard
                    title={location.name}
                    href={`/locations/${location.id}`}
                    description={location.description}
                    date={location.date}
                />
                <ul className="flex flex-wrap justify-around mt-4">
                    {location.locationTags?.map((tag) => (
                        <li key={tag.id} className="mx-auto">
                            <TagCard
                                tag={tag}
                                onClick={() => console.log(tag.name)}
                                color={tag.color}
                            />
                        </li>
                    ))}
                </ul>
            </div>
        ));
    };

    return (
        <div className="w-full mx-auto p-4">
            <div className="flex flex-col items-center">
                <div className="w-full max-w-sm">
                    <div className="h-20 flex items-center justify-between">
                        <StateChangeButton
                            onClick={() => handleModeChange("events")}
                            active={mode === "events"}
                        >
                            Events
                        </StateChangeButton>
                        <StateChangeButton
                            onClick={() => handleModeChange("locations")}
                            active={mode === "locations"}
                        >
                            Locations
                        </StateChangeButton>
                    </div>
                </div>
                <div className="grow w-full">
                    <h1 className="text-3xl font-bold my-5 text-center">
                        {mode === "events" ? "Events" : "Locations"}
                    </h1>
                    <div className="flex flex-col gap-4">
                        <SelectField label={"Choose tag"} options={tagNameArray} value={chosenTag} id={chosenTag.id}
                                     onChange={filterTag}/>
                        {mode === "events" ? renderEventCards() : renderLocationCards()}
                    </div>
                </div>
            </div>
        </div>
    );
};

export default Home;
