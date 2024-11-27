function TaleeLogo({ h = 10 }) {
    return (
        <img
            className={`mx-auto w-auto h-${h}`}
            src="/logo.svg"
            alt="Talee Logo"
        />
    );
}

export default TaleeLogo;
