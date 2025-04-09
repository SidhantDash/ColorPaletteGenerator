import Image from "next/image";
//import Hello from "./components/Hello";
import Button from "./components/Button";
import ColorBox from "./components/ColorBox";
import NavBar from "./components/NavBar";

async function getPostByHex() {
  const response = await fetch('https://www.thecolorapi.com/id?hex=bb0000');
  return response.json();
}
export default async function Home() {

  const post = await getPostByHex()
  return (
    <div className="bg-[#1e1e1e] grid grid-rows-[20px_1fr_20px] items-center justify-items-center min-h-screen p-8 pb-20 gap-16 sm:p-20 font-[family-name:var(--font-geist-sans)]">
      <NavBar />
      <main className="flex flex-col gap-8 row-start-2 items-center sm:items-start">
        <div className="flex flex-col">
          <div className="flex gap-4 items-center flex-col sm:flex-row">
            <ColorBox hexCode="#ff0000"/>
            <ColorBox hexCode="#00ff00"/>
            <ColorBox hexCode="#0000ff"/>
            <ColorBox hexCode="#000000"/>
            <ColorBox hexCode="#ffffff"/>
          </div>
          <div className="ml-auto mr-auto mt-10 items-center">
            <Button label="Generate" />
          </div>
          <div className="ml-auto mr-auto mt-10 items-center">
            <Button label="Saved Palettes" />
          </div>
        </div>
      </main>
    </div>
  );
}
