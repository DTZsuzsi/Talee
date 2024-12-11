import { useCallback } from "react";

function useOpeningHours(setter) {
  const handleOpeningHoursChange = useCallback((day, field, value) => {
    setter((prevLocation) => {
      const updatedHours = prevLocation.openingHours || [];
      const existingDayIndex = updatedHours.findIndex((hour) => hour.day === day);

      if (existingDayIndex !== -1) {
        updatedHours[existingDayIndex] = {
          ...updatedHours[existingDayIndex],
          [field]: value,
        };
      } else {
        updatedHours.push({ day, [field]: value });
      }

      return { ...prevLocation, openingHours: updatedHours };
    });
  }, [setter]);

  return { handleOpeningHoursChange };
}

export default useOpeningHours;
