import Button from "./Button";

export default function NavBar() {
  return (
    <div className="w-full h-16 bg-[#313131] border-black rounded-full">
      <div className="flex flex-row relative">
        <div className="w-[30%] mt-3 ml-4">
          <h1 className="text-white text-3xl font-bold">color.generator</h1>
        </div>

        <div className="mt-3 ml-4 absolute right-10 flex flex-row">
          <button className="rounded-xl text-white px-4 py-2 border-2 border-white mr-5">
            Sign Up
          </button>
          <button className="rounded-xl bg-white text-black px-4 py-2 border-2 border-white mr-5">
            Log In
          </button>
        </div>
      </div>
    </div>
  );
}
