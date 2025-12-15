import axios from "axios";

export const addCategory= async(category)=>{
   return await axios.post('http://localhost:8080/api/v1.0/categories',category);
}
/**
 * export=> means we are making this function available to be imported in other files.
Here we create an addCategory function, and inside this function we call the backend using Axios, which is a tool for communicating with the backend.
Axios to make a POST request to the backend API, enabling communication between the React frontend and the backend service.
*/
export const deletCategory=async(categoryid)=>{
return await axios.delete(`http://localhost:8080/api/v1.0/categories/${categoryid}`);
}
export const fetchCategories=async()=>{
return await axios.get('http://localhost:8080/api/v1.0/categories');

}

