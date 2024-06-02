import Review from "./review";

function Reviews(props) {
    const data = props.data

    return (
        <div style={{ padding: "20px", maxWidth: "1200px", margin: "0 auto" }}>
            {props.isPending && (
                <div style={{
                    textAlign: "center",
                    padding: "20px",
                    fontSize: "1.2em",
                    color: "#007BFF"
                }}>
                    Načítání dat...
                </div>
            )}
            {props.error && (
                <div style={{
                    textAlign: "center",
                    padding: "20px",
                    fontSize: "1.2em",
                    color: "#FF0000"
                }}>
                    Nepodařilo se načíst recenze
                </div>
            )}
            <div
                style={{
                    display: "flex",
                    flexWrap: "wrap",
                    gap: "20px",
                    justifyContent: "center",
                    margin: "10px",
                    padding: "10px"
                }}
            >
                {data.map(item => (
                    <Review key={item.id} review={item} />
                ))}
            </div>
        </div>
    );
}

export default Reviews