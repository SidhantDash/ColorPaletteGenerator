interface props {
    label?: string;
}

export default function Button({label}: props) {
    return (
        <>
            <button className="rounded-lg bg-white text-black px-4 py-2 font-semibold uppercase border-2 border-black"
            >
                {label}
            </button>
        </>
    );
}