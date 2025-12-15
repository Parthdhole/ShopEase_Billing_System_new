import { useContext } from "react";
import { AppContxt } from "../../Conntext/AppContext";
import "./Explore.css";

const Explore = () => {
  const { categories } = useContext(AppContxt);

  console.log("Categories from context:", categories);

  return (
    <div className="explore-container text-align">
      <div className="left-column">
        <div className="firstrow" style={{ overflow: "auto" }}>
          {categories.length > 0 ? (
            categories.map((cat) => <div key={cat.id}>{cat.name}</div>)
          ) : (
            <p>categories found</p>
          )}
        </div>

        <hr className="horizontal-line" />

        <div className="second-row" style={{ overflow: "auto" }}>
          items
        </div>
      </div>

      <div className="right-column d-flex-column">
        <div className="customer-from-conatainer" style={{ height: "15%" }}>
          customer form
        </div>

        <hr className="my-3 text-light" />

        <div className="cart-items-container" style={{ height: "55%", overflow: "auto" }}>
          cart items
        </div>

        <div className="cart-summary-conatiner" style={{ height: "30%" }}>
          cart summary
        </div>
      </div>
    </div>
  );
};

export default Explore;
