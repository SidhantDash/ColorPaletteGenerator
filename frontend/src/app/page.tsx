"use client";
import Image from "next/image";
//import Hello from "./components/Hello";
import Button from "./components/Button";
import ColorBox from "./components/ColorBox";
import NavBar from "./components/NavBar";
import React, {useState} from 'react';

/*async function getPostByHex() {
  const response = await fetch('https://www.thecolorapi.com/id?hex=bb0000');
  return response.json();
}*/

export default function Home() {

  // Color generation status: starts out as false, then remains true after the user generates a color
  const [generated, setGenerated] = useState(false);

  // When "Generate" button is clicked
  const generateColors = () => {
    setGenerated(false); // Resets the colors briefly (required in order to generate more than once)
    setTimeout(() => setGenerated(true), 0); // After a moment, allow re-generation
  }

  const hexChars = "0123456789abcdef";

  // Function to generate random hex color strings
  function randomColor()
  {
    let color = "#";
    while (color.length <= 6) {
      color += hexChars.charAt(Math.floor(Math.random() * hexChars.length));
    }
    return color;
  }

  //const post = await getPostByHex()
  return (
    <div className="bg-[#1e1e1e] grid grid-rows-[20px_1fr_20px] items-center justify-items-center min-h-screen p-8 pb-20 gap-16 sm:p-20 font-[family-name:var(--font-geist-sans)]">
      <NavBar />
      <main className="flex flex-col gap-8 row-start-2 items-center sm:items-start">
        <div className="flex flex-col">
        {generated ? (
          <div className="flex gap-4 items-center flex-col sm:flex-row">
            {/* Generate five random colors */}
            <ColorBox hexCode={randomColor()}/>
            <ColorBox hexCode={randomColor()}/>
            <ColorBox hexCode={randomColor()}/>
            <ColorBox hexCode={randomColor()}/>
            <ColorBox hexCode={randomColor()}/>
          </div>
        ) : (
          <div className="flex gap-4 items-center flex-col sm:flex-row">
            {/* When the user has yet to generate anything */}
            <ColorBox hexCode=""/>
            <ColorBox hexCode=""/>
            <ColorBox hexCode=""/>
            <ColorBox hexCode=""/>
            <ColorBox hexCode=""/>
          </div>
        )}
          <div className="ml-auto mr-auto mt-16 items-center">
            {/* Generate button */}
            <button className="rounded-3xl text-4xl font-bold bg-white text-black px-12 py-4 border-2 border-white mr-5" onClick={generateColors}>
              Generate
            </button>
          </div>
        </div>
      </main>
    </div>
  );
}
