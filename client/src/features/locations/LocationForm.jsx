import InputField from "../main/components/atoms/InputField";
import TextArea from "../main/components/atoms/TextArea.jsx";

function LocationForm({ location, setLocation, onHoursChange }) {
  const daysOfWeek = [
    "MONDAY",
    "TUESDAY",
    "WEDNESDAY",
    "THURSDAY",
    "FRIDAY",
    "SATURDAY",
    "SUNDAY",
  ];

  return (
    <div>
      <div className="grid md:grid-cols-2 gap-4 sm:grid-cols-1">
        <InputField
          label="Name"
          type="text"
          value={location.name}
          onChange={(e) => setLocation({ ...location, name: e.target.value })}
          required
        />

        <InputField
          label="Phone"
          type="text"
          value={location.phone}
          onChange={(e) => setLocation({ ...location, phone: e.target.value })}
        />
        <InputField
          label="Email"
          type="email"
          value={location.email}
          onChange={(e) => setLocation({ ...location, email: e.target.value })}
        />
        <InputField
          label="Website"
          type="url"
          value={location.website}
          onChange={(e) =>
            setLocation({ ...location, website: e.target.value })
          }
        />
        <InputField
          label="Facebook"
          type="text"
          value={location.facebook}
          onChange={(e) =>
            setLocation({ ...location, facebook: e.target.value })
          }
        />
        <InputField
          label="Instagram"
          type="text"
          value={location.instagram}
          onChange={(e) =>
            setLocation({ ...location, instagram: e.target.value })
          }
        />
      </div>

      <TextArea
        onChange={(e) =>
          setLocation({ ...location, description: e.target.value })
        }
        title="Description"
      />

      <div className="mt-6">
        <h2 className="font-bold text-xl mb-4">Opening Hours</h2>
        {daysOfWeek.map((day) => (
          <div key={day} className="grid grid-cols-3 gap-4 mb-2">
            <span className="font-semibold">{day}</span>
            <InputField
              label="Opening Time"
              type="time"
              onChange={(e) =>
                onHoursChange(day, "openingTime", e.target.value)
              }
            />
            <InputField
              label="Closing Time"
              type="time"
              onChange={(e) =>
                onHoursChange(day, "closingTime", e.target.value)
              }
            />
          </div>
        ))}
      </div>
    </div>
  );
}

export default LocationForm;
