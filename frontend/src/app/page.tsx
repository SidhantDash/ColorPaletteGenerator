"use client";
import Image from "next/image";
//import Hello from "./components/Hello";
import Button from "./components/Button";
import ColorScheme from "./components/ColorScheme";
import NavBar from "./components/NavBar";
import React, { useState } from "react";

function randomColor() {
  const hexChars = "0123456789abcdef";
  let color = "";
  while (color.length <= 6) {
    color += hexChars.charAt(Math.floor(Math.random() * hexChars.length));
  }
  console.log(color);
  return color;
}

async function getPostByHex(color: String) {
  try {
    const response = await fetch(
      `https://www.thecolorapi.com/scheme?hex=${color}&format=string&mode=monochrome&count=5`,
    );

    if (!response.ok) {
      throw new Error("Failed to fetch data from the API");
    }
    const data = await response.json();
    console.log("Response:", data);
    return data;
  } catch (error) {
    console.error("Fetch error:", error);
    return null;
  }
}

export default function Home() {
  console.log("a");

  const [scheme, setScheme] = useState(undefined);
  console.log(scheme);

  const generateColors = async () => {
    console.log("generateColors function called"); // Log function call
    console.log("Initial scheme:", scheme); // Log initial scheme

    const color = randomColor();
    console.log("Generated color:", color); // Log generated color

    const newScheme = await getPostByHex(color);
    console.log("Fetched new scheme:", newScheme); // Log fetched scheme

    setScheme(newScheme);
    console.log("Updated scheme state:", scheme); // Log updated scheme state
  };

  console.log(scheme);

  console.log("help me");

  return (
    <div className="bg-[#1e1e1e] grid grid-rows-[20px_1fr_20px] items-center justify-items-center min-h-screen p-8 pb-20 gap-16 sm:p-20 font-[family-name:var(--font-geist-sans)]">
      <NavBar />
      <ColorScheme scheme={scheme} />
      <div className="ml-auto mr-auto mt-16 items-center">
        {/* Generate button */}
        <button
          className="rounded-3xl text-4xl font-bold bg-white text-black px-12 py-4 border-2 border-white mr-5"
          onClick={generateColors}
        >
          Generate
        </button>
      </div>
    </div>
  );
}
