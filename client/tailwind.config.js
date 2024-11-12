/** @type {import('tailwindcss').Config} */
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
        darkBackground: '#004466',
        darkText: '#00ffa2'
      },
      fontFamily: {
        sans: ['Montserrat', 'sans-serif'],
      }
    },
  },
  darkMode: "class",
  plugins: [],
}

