"use client";
import Image from "next/image";
import Button from "./Button";
import ColorBox from "./ColorBox";
import NavBar from "./NavBar";
import React, { useState } from "react";

interface Color {
  hex: { value: string };
}

interface Scheme {
  mode: string;
  count: string;
  colors: Color[];
}

interface props {
  scheme?: Scheme;
}

export default function ColorScheme({ scheme }: props) {
  return (
    <main className="flex flex-col gap-8 row-start-2 items-center sm:items-start">
      <div className="flex flex-col">
        {scheme ? (
          <div className="flex gap-4 items-center flex-col sm:flex-row">
            {/* Generate five random colors */}
            {scheme?.colors.map((color, index) => (
              <ColorBox key={index} hexCode={color.hex.value} />
            ))}
                     {" "}
          </div>
        ) : (
          <div className="flex gap-4 items-center flex-col sm:flex-row">
            {/* When the user has yet to generate anything */}
            <ColorBox hexCode="" />
            <ColorBox hexCode="" />
            <ColorBox hexCode="" />
            <ColorBox hexCode="" />
            <ColorBox hexCode="" />
          </div>
        )}
      </div>
    </main>
  );
}
