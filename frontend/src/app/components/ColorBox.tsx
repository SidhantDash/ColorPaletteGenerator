import Image from "next/image";

interface props {
  hexCode?: string;
  rgbCode?: string;
  red?: number;
  green?: number;
  blue?: number;
}

export default function ColorBox({ hexCode, rgbCode, red, green, blue }: props) {
  const color = hexCode;
  //console.log(color);
  const generated = color != ""; // set to true if the string is not empty

  return (
    <div>
      {/* Display the color box and the color as a label if it has been generated */}
      {generated ? (
        <div>
          <div className="text-white text-center hover:shadow-xl font-bold">
            <h1> {color} </h1>
          </div>
          <div
            className={`w-48 h-48 rounded-3xl transition-shadow duration-300`}
            style={{
              backgroundColor: color,
              boxShadow: `0 0 10px 5px rgba(${red},${green},${blue}, 0.6)`,
           }}
        ></div>
        </div>
      ) : (
        <div>
          {/* If not, display the empty symbol in the box */}
          <div className="text-white text-center font-bold">
            <h1> Color name </h1>
          </div>
          <div className="w-48 h-48 rounded-3xl bg-[#313131] flex ">
            <Image
              src="/images/EmptySymbol.png"
              width={100}
              height={100}
              className="m-auto"
              alt="no color yet"
            />
          </div>
        </div>
      )}
    </div>
  );
}
