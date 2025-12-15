import { useContext, useEffect,useState } from "react";
import { assets } from "../../assets/assets";
import toast from "react-hot-toast";
import { AppContxt } from "../../Conntext/AppContext";
import { addCategory } from "../../Service/CategoryService";

const CategoryForm = () => {
  const [loading, setLoading] = useState(false);
   const{setCategories,categories}=useContext(AppContxt) 
  const [image,setImage]=useState(false);
   const [data, setData] = useState({
     name: "",
     description: "",
     bgColor: "#2c2c2c",
    
   });

useEffect(()=>{
   console.log(data);
},[data])

   const onChangeHandler=(e)=>{
      const name=e.target.name;
      const evalue=e.target.value;
     setData((data)=>({
        ...data,
        [name]:evalue
     }));
   }


   const onSubmitHandler=async(e)=>{
    e.preventDefault();
    setLoading(true);
    if(!image){
      toast.error("Select image for category");
      setLoading(false);
      return;
    } 
   const formData=new FormData();
   formData.append("category", JSON.stringify(data));
   formData.append("file",image);
   try{
      const response=await addCategory(formData);
      if(response.status === 201 || response.status === 204){
setCategories([...categories, { ...response.data, bgColor: data.bgColor }]);
       toast.success("Category added successfully");
       setData({
        name :"",
        description :"",
        bgColor :"#2c2c2c",
      })  
    
      setImage(false);
    }

   }
   catch(error){
      console.error(error);
      toast.error("Error while adding category");
   }
     finally{
        setLoading(false);
     }  
  } // Add this closing brace

  return (
    <div className="mx-2 mt-2">
      <div className="row">
        <div className="card col-md-12 form-container ">
          <div className="card-body ">
           <form onSubmit={onSubmitHandler}>
            {/* Image Upload */}
            <div className="mb-3">
              <label htmlFor="image" className="form-label">
                <img
                  src={image ? URL.createObjectURL(image) : assets.upload}
                  alt=""
                  width={48}
                />
              </label>

              <input
                type="file"
                name="image"
                id="image"
                className="form-controller"
                hidden onChange={(e)=>setImage(e.target.files[0])}
             
              />
            </div>
        

            {/* Category Name */}
            <div className="mb-3">
              <label htmlFor="name" className="form-label">Name</label>
              <input
                type="text"
                name="name"
                id="name"
                className="form-control"
                placeholder="Category Name"
                onChange={onChangeHandler}
                value={data.name}

              />
            </div>

            {/* Description */}
            <div className="mb-3">
              <label htmlFor="description" className="form-label">
                Description
              </label>
              <textarea
                rows="5"
                name="description"
                id="description"
                className="form-control"
                placeholder="Write Content Here"
                onChange={onChangeHandler}
                value={data.description}
                
              ></textarea>
            </div>
            <div className="mb-3">
                <label htmlFor="bgColor" className="form-label">Background color</label>
                <br/>
                <input type="color" 
                name="bgColor" 
                id="bgColor"
                 onChange={onChangeHandler}
                value={data.bgColor}
                 placeholder="#ffff"/>
         
            </div>
            <button type="submit" 
            disabled ={loading}
            className="btn btn-primary w-100" >{loading ? "Loading...":"Submit"}</button>
          </form>
          </div>
        </div>
      </div>
    </div>
  );
};
export default CategoryForm;