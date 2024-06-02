import Product from "./product";


function Products(props) {
    const data = props.products

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
                    Nepodařilo se načíst produkty
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
                    <Product key={item.id} product={item} />
                ))}
            </div>
        </div>
    );
}

export default Products