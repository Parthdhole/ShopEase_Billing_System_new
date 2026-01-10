import { createContext, useEffect, useState } from "react";
import { fetchCategories } from "../Service/CategoryService";
import { fetchItems } from "../Service/ItemService.js";

export const AppContxt = createContext(null);

export const AppContxtProvider = ({ children }) => {
  const [categories, setCategories] = useState([]);
  const[auth,setAuth]=useState({token:null,role:null});
  const [itemsData, setItemsData] = useState([]);
  useEffect(() => {
    async function loadData() {
        const response = await fetchCategories();
       const itemResponse= await fetchItems();
        setCategories(response.data);
        setItemsData(itemResponse.data);
    }
    loadData();
  }, []);
  const setAuthdata=(token,role)=>{
    setAuth({token,role});
  }
    const contextValue = {
        categories,  // make sure this matches the key you use in useContext
        setCategories,
        auth,
        setAuthdata,
        itemsData,
        setItemsData

    };

  return (
    <AppContxt.Provider value={contextValue}>
      {children}
    </AppContxt.Provider>
  );
};
