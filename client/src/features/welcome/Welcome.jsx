import { Link } from "react-router-dom";
import DarkModeToggle from "../main/components/molecules/DarkModeToggle.jsx";
import TaleeButton from "../main/components/atoms/TaleeButton.jsx";

const Welcome = () => {
  return (
    <div className="max-w-2xl mx-auto p-4">
      <h1 className="text-3xl font-bold my-5 text-center">
        Welcome to <strong>TALEE</strong>! 🎉
      </h1>
      <p className="text-lg my-5">
        Looking for fresh date ideas, family-friendly spots, or the best weekend
        parties in Budapest?
        <strong> TALEE </strong> has you covered! Discover exciting activities,
        plan the perfect night out, or organize an unforgettable celebration
        with just a few clicks.
      </p>

      <DarkModeToggle />

      <div className="text-left my-5">
        <h3 className="text-xl font-medium mb-2">Whether you're asking:</h3>
        <ul className="list-disc list-inside mb-4">
          <li className="ml-5">“What should we do on date night?”❤️</li>
          <li className="ml-5">
            “Where's the best place for a family dinner?” 🍽
          </li>
          <li className="ml-5">
            “How can we celebrate graduation with the boyz?” 🍻
          </li>
          <li className="ml-5">
            “What's happening in the city this weekend?” 🌇
          </li>
        </ul>
      </div>

      <p className="text-lg my-5">
        <strong>TALEE</strong> is here to make it easy! Find, organize, and
        enjoy the best programs Budapest has to offer all in one place. Let's
        get your next adventure started!
      </p>

      <TaleeButton>
        <Link to="/">Start</Link>
      </TaleeButton>
    </div>
  );
};

export default Welcome;
