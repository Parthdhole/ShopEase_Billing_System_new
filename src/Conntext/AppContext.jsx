import { createContext, useEffect, useState } from "react";
import { fetchCategories } from "../Service/CategoryService";


export const AppContxt = createContext(null);

export const AppContxtProvider = ({ children }) => {
  const [categories, setCategories] = useState([]);

  useEffect(() => {
    async function loadData() {
        const response = await fetchCategories();
        setCategories(response.data);
    }
    loadData();
  }, []);
    const contextValue = {
        categories,  // make sure this matches the key you use in useContext
        setCategories
    };

  return (
    <AppContxt.Provider value={contextValue}>
      {children}
    </AppContxt.Provider>
  );
};
