interface props {
    hexCode?: string;
}

export default function ColorBox({hexCode}: props) {

    const color = hexCode;
    console.log(color);
    return (
        <div>
            <div className="text-white">
                <h1> {color} </h1>
            </div>
            <div
            className="w-48 h-48 text-black border-4 border-black"
            style={{ backgroundColor: color }}
            >
            </div>
        </div>
    );
    
}