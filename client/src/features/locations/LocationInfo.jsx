function LocationInfo({ location }) {
    return (
      <div className="flex flex-col lg:flex-row gap-6">
        <img
          src="https://images.unsplash.com/photo-1513151233558-d860c5398176?q=80&w=3270&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D"
          alt="Location"
          className="rounded-md w-full lg:w-1/3 object-cover"
        />
        <div className="flex flex-col gap-4 w-full">
          <p className="text-2xl font-bold text-center">{location.name}</p>
          {location.address && (
            <p>
              <strong>Address:</strong> {location.address}
            </p>
          )}
          {location.description && (
            <p>
              <strong>Description:</strong> {location.description}
            </p>
          )}
          {location.phone && (
            <p>
              <strong>Phone:</strong> {location.phone}
            </p>
          )}
          {location.openingHours?.length > 0 && (
            <div>
              <strong>Opening Hours:</strong>
              {location.openingHours.map((hour) => (
                <p key={hour.day}>
                  {hour.day}: {hour.openingTime} - {hour.closingTime}
                </p>
              ))}
            </div>
          )}
        </div>
      </div>
    );
  }
  
  export default LocationInfo;