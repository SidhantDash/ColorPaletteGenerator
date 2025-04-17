import Image from "next/image";
import Hello from "./components/Hello";
import GenerateButton from "./components/Button";
import ColorBox from "./components/ColorBox";
import NavBar from "./components/NavBar";

async function getPostByHex() {
  const response = await fetch("https://www.thecolorapi.com/id?hex=bb0000");
  return response.json();
}
export default async function Home() {
  const post = await getPostByHex();
  return (
    <div className="grid grid-rows-[20px_1fr_20px] items-center justify-items-center min-h-screen p-8 pb-20 gap-16 sm:p-20 font-[family-name:var(--font-geist-sans)]">
      <NavBar />
      <main className="flex flex-col gap-8 row-start-2 items-center sm:items-start">
        <Image
          src="/SDLogo101623.png"
          alt="SD logo"
          width={180}
          height={38}
          priority
        />
        <div className="flex gap-4 items-center flex-col sm:flex-row">
          <ColorBox hexCode="#123456" />
          <GenerateButton />
          <h1>
            {post.rgb.fraction.r}, {post.rgb.fraction.g}, {post.rgb.fraction.b}
          </h1>
        </div>
      </main>
    </div>
  );
}
