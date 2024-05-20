import logo from './logo.svg';
import './App.css';
import Product from "./product";
import {useEffect, useState} from "react";
import ProductForm from "./product-form";
import {
    BrowserRouter as Router,
    Routes,
    Route,
    Link
} from "react-router-dom";

function App() {

  const [data, setData] = useState([])
  const [isPending, setIsPending] = useState(true)
  const [error, setError] = useState()

  const onNewProductHandler = (product) => {
      const newData = [...data]
      newData.push(product)
      setData(newData)
  }

  useEffect(() => {
    fetch(`${process.env.REACT_APP_TARGET_DOMAIN}/products`)
        .then(response => {
          if (response.ok) {
            return response.json()
          }
          throw new Error(`Unable to get data: ${response.statusText}`)
        })
        .then(json => setData(json))
        .catch(err => setError(err.message))
        .finally(() => setIsPending(false))
  }, []);

  return (
    <Router>
        <div className="App">

            <nav>
                <ul>
                    <li>
                        <Link to="/">Domovská stránka</Link>
                    </li>
                    <li>
                        <Link to="/products">Všechny produkty</Link>
                    </li>
                    <li>
                        <Link to="/favorites">Oblíbené</Link>
                    </li>
                    <li>
                        <Link to="/by-store">Podle obchodu</Link>
                    </li>
                    <li>
                        <Link to="/by-category">Podle kategorie</Link>
                    </li>
                </ul>
            </nav>

            <Routes>
                <Route
                    path="/product-form"
                    element={
                        <ProductForm onNewProduct={onNewProductHandler} />
                    }
                />
                <Route path="/products" element={
                    <div>
                        {isPending && "načítání dat..."}
                        <div
                            style={{
                                display: "flex",
                                flexWrap: "wrap",
                                margin: "5px",
                            }}
                        >
                            {data.map((item) => (
                                <Product key={item.id} product={item}/>
                            ))}
                        </div>
                        {error}
                    </div>
                }></Route>
                <Route path="/favorites" element={<div>Oblíbené produkty</div>} />
                <Route path="/by-store" element={<div>Produkty podle obchodu</div>} />
                <Route path="/by-category" element={<div>Produkty podle kategorie</div>} />
                <Route
                    path="/"
                    element={
                        <div>
                            {isPending && "načítání dat..."}
                            <div
                                style={{
                                    display: "flex",
                                    flexWrap: "wrap",
                                    margin: "5px",
                                }}
                            >
                                {data.map((item) => (
                                    <Product key={item.id} product={item}/>
                                ))}
                            </div>
                            {error}
                        </div>
                    }
                />
            </Routes>

        </div>
    </Router>
  );
}

export default App;
