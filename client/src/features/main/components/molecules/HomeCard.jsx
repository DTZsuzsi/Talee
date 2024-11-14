/* eslint-disable react/prop-types */
import Divider from "../atoms/Divider";
import Reviews from "../atoms/Reviews";
const HomeCard = ( { title, href, description, date } ) => {
    return (
        <a className="w-full" href={href}>
            <Divider />
            <div className="flex justify-between w-full p-3">
                <div className="min-w-fit">
                    <img src="https://picsum.photos/200" alt="" />
                </div>
                <div className="w-full max-w-lg flex flex-col items-center gap-y-4">
                    <h2 className="text-xl font-bold">{title}</h2>
                    <Reviews />
                    <p>{date}</p>
                </div>
                <p className="w-full max-w-sm">{description}</p>
              
            </div>
            
        </a>
    )
};

export default HomeCard;