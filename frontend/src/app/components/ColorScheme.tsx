"use client";
import ColorBox from "./ColorBox";

interface Color {
  hex: { value: string };
  rgb: { value: string, r: number, g:number, b:number };
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
    <main className="flex flex-col items-center">
      <div>
        {scheme ? (
          <div className="flex gap-8 items-center">
            {/* Generate five random colors */}
            {scheme?.colors.map((color, index) => (
              <ColorBox key={index} hexCode={color.hex.value} rgbCode={color.rgb.value} red={color.rgb.r} green={color.rgb.g} blue={color.rgb.b} />
            ))}
          </div>
        ) : (
          <div className="flex gap-8 items-center">
            {/* When the user has yet to generate anything */}
            <ColorBox hexCode="" rgbCode="" red={0} green={0} blue={0} />
            <ColorBox hexCode="" rgbCode="" red={0} green={0} blue={0} />
            <ColorBox hexCode="" rgbCode="" red={0} green={0} blue={0} />
            <ColorBox hexCode="" rgbCode="" red={0} green={0} blue={0} />
            <ColorBox hexCode="" rgbCode="" red={0} green={0} blue={0} />
          </div>
        )}
      </div>
    </main>
  );
}
