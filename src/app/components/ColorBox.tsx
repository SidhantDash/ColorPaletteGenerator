interface props {
    hexCode?: string;
}

export default function ColorBox({hexCode}: props) {

    const color = "bg-${hexCode}"
    return (
        <div className="w-8 h-8 bg-${hexCode} text-black">
        </div>
    );
    
}