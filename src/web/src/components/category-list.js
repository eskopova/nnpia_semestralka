import {Link} from "react-router-dom";
import {useEffect, useState} from "react";
import "../styles/category-list.css"

function CategoryList(props) {
    const fetchAddress = props.address
    const linkAddress = props.link
    const heading = props.heading
    const [categories, setCategories] = useState([])

    useEffect(() => {
        fetch(`${process.env.REACT_APP_TARGET_DOMAIN}/${fetchAddress}`)
            .then(response => response.json())
            .then(json => setCategories(json))
            .catch(error => console.log(error.message));
    }, []);

    return (
        <div className="container">
            <h1 className="heading">{heading}</h1>
            <div className="grid">
                {categories.map(c => (
                    <div key={c} className="card">
                        <Link to={`/${linkAddress}/${c}`} className="link">{c}</Link>
                    </div>
                ))}
            </div>
        </div>
    );
}

export default CategoryList;