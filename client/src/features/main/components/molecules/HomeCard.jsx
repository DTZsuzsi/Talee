import Reviews from "../atoms/Reviews";

const HomeCard = ({ title, href, description, date }) => {
  return (
    <a
      className="block w-full bg-light-secondaryBg dark:bg-dark-secondaryBg shadow-md rounded-lg overflow-hidden hover:shadow-lg transition-shadow duration-300"
      href={href}
    >
      <div className="flex flex-col md:flex-row items-start p-4 gap-4">
        {/* Image Section */}
        <div className="flex-shrink-0 w-full md:w-48">
          <img
            src="https://picsum.photos/200"
            alt={title}
            className="w-full h-48 object-cover rounded-lg"
          />
        </div>

        {/* Content Section */}
        <div className="relative flex flex-col justify-between w-full gap-y-3 flex-grow">
          <div className="flex justify-between items-center">
            <h2 className="text-lg md:text-xl font-bold text-right">{title}</h2>
          </div>
          <Reviews />
          <p className="text-md md:text-base">{description}</p>

          {/* Date in the left bottom corner */}
          <p className="text-lg font-semibold">{date}</p>
        </div>
      </div>
    </a>
  );
};

export default HomeCard;
