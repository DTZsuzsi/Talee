/** @type {import('tailwindcss').Config} */
import forms from '@tailwindcss/forms';
export default {
  content: [
    "./index.html",
    "./src/**/*.{js,ts,jsx,tsx}",
  ],
  theme: {
    extend: {
      colors: {
        background: '#00ffa2',
        text: '#004466',
        headerBackground:  '#003927',

        darkBackground: '#004466',
        darkText: '#00ffa2',
        darkHeaderBackground:'#001532',


        blueButton: '#3B82F6',
        blueHover: '#2563EB',
      },
      fontFamily: {
        sans: ['Montserrat', 'sans-serif'],
      }
    },
  },
  darkMode: "class",
  plugins: [forms],
}

